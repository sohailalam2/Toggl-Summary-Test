package in.sohailalam.togglsummary.utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public final class GoogleSheet {

    private static Logger logger = LoggerFactory.getLogger(GoogleSheet.class);
    
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "Toggl Summary Test";

    /**
     * Directory to store user credentials for this application.
     */
    private static final File DATA_STORE_DIR = new File(
            System.getProperty("user.home"),
            ".secrets/toggl-summary-test"
    );

    /**
     * Global instance of the {@link FileDataStoreFactory}.
     */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport HTTP_TRANSPORT;

    /**
     * Global instance of the scopes required by this quickstart.
     * <p>
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/sheets.googleapis.com-java-quickstart
     */
    private static final List<String> SCOPES =
            Arrays.asList(SheetsScopes.SPREADSHEETS);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            logger.error("Error with GoogleSheet API", t);
            System.exit(1);
        }
    }

    private final Environment ENV;
    private final String CLIENT_SECRET_FILE_PATH;

    @Autowired
    public GoogleSheet(Environment env) {
        this.ENV = env;
        this.CLIENT_SECRET_FILE_PATH = ENV.getProperty("client_secret", "/client_secret.json");
        logger.info("Loading Google client_secret.json from '{}'", this.CLIENT_SECRET_FILE_PATH);
    }

    /**
     * Creates an authorized Credential object.
     *
     * @return an authorized Credential object.
     * @throws IOException
     */
    private Credential authorize() throws IOException {
        // Load client secrets.
        InputStreamReader isr;
        GoogleClientSecrets clientSecrets;

        try {
            InputStream in = GoogleSheet.class.getResourceAsStream(CLIENT_SECRET_FILE_PATH);
            isr = new InputStreamReader(in);
        } catch (Exception e) {
            isr = new InputStreamReader(new FileInputStream(CLIENT_SECRET_FILE_PATH));
        }
        
        clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, isr);

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        logger.info("Credentials saved to {}", DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Sheets API client service.
     *
     * @return an authorized Sheets API client service
     * @throws IOException
     */
    public Sheets getSheetsService() throws IOException {
        Credential credential = this.authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static RowData createGoogleSheetRow(List<String> data) {
        return createGoogleSheetRow(data, false);
    }

    public static RowData createGoogleSheetRow(List<String> data, boolean isHeader) {
        final List<CellData> cellDataList = new ArrayList<>();
        data.forEach(d -> {
            CellData cellData = new CellData();
            cellData.setUserEnteredValue(new ExtendedValue()
                    .setStringValue(d));
            if(isHeader) {
                cellData.setUserEnteredFormat(new CellFormat()
                        .setTextFormat(new TextFormat().setBold(true)));
            }
            cellDataList.add(cellData);
        });
        return new RowData().setValues(cellDataList);
    }
}
