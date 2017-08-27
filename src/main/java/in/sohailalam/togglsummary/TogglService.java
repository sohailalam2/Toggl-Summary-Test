package in.sohailalam.togglsummary;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import in.sohailalam.togglsummary.db.SummaryRepository;
import in.sohailalam.togglsummary.db.schema.SummarySchema;
import in.sohailalam.togglsummary.db.schema.WorkspaceSchema;
import in.sohailalam.togglsummary.json.SummaryJson;
import in.sohailalam.togglsummary.utils.GoogleSheet;
import in.sohailalam.togglsummary.utils.Helpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.*;

import static in.sohailalam.togglsummary.db.schema.SummaryItemSchema.summaryItemSchemaHeaders;
import static in.sohailalam.togglsummary.utils.GoogleSheet.createGoogleSheetRow;
import static org.springframework.http.HttpMethod.GET;

/**
 * The Toggl Service helper class
 */
@Service
public final class TogglService implements TogglApi {

    private final static Logger logger = LoggerFactory.getLogger(TogglService.class);
    private final SummaryRepository summaryRepository;
    private final Helpers helpers;
    private final GoogleSheet googleSheet;

    /**
     * Dependencies managed by Spring
     *
     * @param summaryRepository The MongoDB repository for managing the summary reports
     * @param helpers A helpers class with some useful functions
     * @param googleSheet The google helper class to handle login and spreadsheet creation
     */
    @Autowired
    public TogglService(SummaryRepository summaryRepository, Helpers helpers, GoogleSheet googleSheet) {
        this.summaryRepository = summaryRepository;
        this.helpers = helpers;
        this.googleSheet = googleSheet;
    }

    /**
     * A private helper method to get a new {@link HttpEntity} object with some default headers
     * for Toggl API
     * 
     * @return the {@link HttpEntity} object
     */
    private HttpEntity createNewEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.AUTHORIZATION, this.helpers.getAuthHeaderValue());
        return new HttpEntity<>(headers);
    }

    /**
     * Private helper method to make the Toggl API request for getting summary data for a given date range
     * and workspace
     * 
     * @param workspaceId The given Toggl workspace id
     * @param startDate The start date for the summary
     * @param endDate The end date for the summary
     * @return The API response as {@link ResponseEntity}
     */
    private ResponseEntity<SummaryJson> downloadSummary(String workspaceId, String startDate, String endDate) {
        logger.info("Requesting summary from Toggl API for workspace {} and dates {} - {}",
                workspaceId, startDate, endDate);

        final RestTemplate req = new RestTemplate();
        // Query parameters
        final UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromUriString(SUMMARY_URL)
                .queryParam("workspace_id", workspaceId)
                .queryParam("since", startDate)
                .queryParam("until", endDate)
                .queryParam("user_agent", "api_test");

        return req.exchange(queryBuilder.buildAndExpand().toUri(), GET, createNewEntity(), SummaryJson.class);
    }

    /**
     * The helper method for getting all workspaces from your Toggl Account
     * 
     * @return A list of {{@link WorkspaceSchema}}
     */
    @Override
    public List<WorkspaceSchema> getAllWorkspaces() {
        logger.info("Requesting all workspaceSchemas from Toggl API");
        final RestTemplate req = new RestTemplate();
        final ResponseEntity<WorkspaceSchema[]> res = req.exchange(
                WORKSPACES_URL, GET, createNewEntity(), WorkspaceSchema[].class
        );

        return Arrays.asList(res.getBody());
    }

    /**
     * A helper method for getting all summary data from your Toggl Account
     * and save it into local database
     * 
     * @return A list of {{@link SummarySchema}}
     */
    @Override
    public List<SummarySchema> importSummary() {
        final List<WorkspaceSchema> workspaceSchemas = this.getAllWorkspaces();
        final List<SummarySchema> summary = new ArrayList<>();

        // get the data for all workspaces
        workspaceSchemas.forEach(workspace -> {
            logger.info("Processing Workspace : ", workspace);
            SummaryJson summaryJson;
            final Calendar calendar = GregorianCalendar.getInstance();
            calendar.roll(Calendar.MONTH, true);

            // get the data on a monthly basis until the grand total amount
            // for a given month is not zero
            do {
                calendar.roll(Calendar.MONTH, false);
                final Pair<Date, Date> dates = this.helpers.getDateRange(calendar);
                final ResponseEntity<SummaryJson> res = downloadSummary(
                        String.valueOf(workspace.getId()),
                        helpers.formatDate(dates.getFirst()),
                        helpers.formatDate(dates.getSecond())
                );
                summaryJson = res.getBody();

                logger.info("Found Summary :: {}", summaryJson);

                summaryJson.getData().forEach(data -> {
                    SummarySchema summarySchema = new SummarySchema(
                            helpers.getMonth(dates.getFirst()),
                            workspace.getId(), data
                    );
                    summary.add(summarySchema);
                });
            } while (summaryJson.getTotal_grand() > 0);
        });

        // Save the data into local database and return it
        return this.summaryRepository.save(summary);
    }

    /**
     * A helper method to export all the summary data from the local database
     * to a google spreadsheet
     *
     * The spreadsheet will have multiple sheet, each for a given month
     *
     * @return A link of the spreadsheet upon success, else an error message
     */
    @Override
    public String exportSummary() {
        final List<SummarySchema> summaries = this.summaryRepository.findAll();

        if (summaries.size() > 0) {
            try {
                // Build a new authorized API client service.
                Sheets sheetsService = this.googleSheet.getSheetsService();
                // List of sheets in the spreadsheet
                List<Sheet> sheets = new ArrayList<>();
                // Add summary data
                summaries.forEach(summary -> {
                    List<RowData> rows = new ArrayList<>();
                    rows.add(createGoogleSheetRow(summaryItemSchemaHeaders(), true));

                    summary.getItems().forEach(item -> {
                        rows.add(createGoogleSheetRow(item.summaryItemSchemaValues()));
                    });

                    rows.add(createGoogleSheetRow(new ArrayList<>()));
                    rows.add(createGoogleSheetRow(
                            Arrays.asList(
                                    "Total Time (milliseconds)",
                                    String.valueOf(summary.getTotal_time()))
                            )
                    );

                    // Create a new sheet, each for a given month
                    Sheet sheet = new Sheet()
                            .setProperties(new SheetProperties().setTitle(summary.getMonth()))
                            .setData(Collections.singletonList(new GridData().setRowData(rows)));
                    sheets.add(sheet);
                });
                // Create the spread sheet
                Spreadsheet spreadsheet = new Spreadsheet()
                        .setProperties(new SpreadsheetProperties().setTitle("Toggl Summary"))
                        .setSheets(sheets);
                Sheets.Spreadsheets.Create request = sheetsService.spreadsheets().create(spreadsheet);
                Spreadsheet response = request.execute();
                return "<a href=\"" + response.getSpreadsheetUrl() + "\">Open Sheet</a>";
            } catch (IOException e) {
                logger.error("Error with Google API", e);
                return "Error with Google API - Please check logs";
            }
        } else {
            return "Nothing to export, please import first";
        }
    }
}
