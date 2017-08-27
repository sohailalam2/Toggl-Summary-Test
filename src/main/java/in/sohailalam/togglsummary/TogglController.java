package in.sohailalam.togglsummary;

import in.sohailalam.togglsummary.db.schema.SummarySchema;
import in.sohailalam.togglsummary.db.schema.WorkspaceSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * The Toggl Controller for the REST services
 */
@RestController
public final class TogglController {

    /**
     * The Toggl API Service - dependency managed by Spring
     */
    private final TogglService togglService;

    @Autowired
    public TogglController(TogglService togglService) {
        this.togglService = togglService;
    }

    /**
     * HTTP GET /workspaces
     * Endpoint for getting all the workspaces from your Toggl Account
     *
     * @return A list of {@link WorkspaceSchema}
     */
    @RequestMapping(method = GET, value = "/workspaces")
    public List<WorkspaceSchema> getAllWorkspaces() {
        return this.togglService.getAllWorkspaces();
    }

    /**
     * HTTP GET /import
     * Endpoint for importing the summaries from all workspaces of your Toggl Account
     * 
     * @return A list of {@link SummarySchema}
     */
    @RequestMapping(method = GET, value = "/import")
    public List<SummarySchema> importSummary() {
        return this.togglService.importSummary();
    }

    /**
     * HTTP GET /export
     * Endpoint for exporting the summaries that were imported previously to your Google Spreadsheet
     *
     * NOTE: You will need to login to Google via OAuth2 login end point given in the console window,
     * upon firing this request. If you are already logged in once using this application, then this is not required.
     *
     * @return A link of the spreadsheet upon success, else an error message
     */
    @RequestMapping(method = GET, value = "/export")
    public String exportSummary() {
        return this.togglService.exportSummary();
    }
}
