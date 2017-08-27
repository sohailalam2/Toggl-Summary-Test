package in.sohailalam.togglsummary;

import in.sohailalam.togglsummary.db.schema.SummarySchema;
import in.sohailalam.togglsummary.db.schema.WorkspaceSchema;

import java.util.List;

/**
 * The Toggl Api Endpoints and available methods
 */
public interface TogglApi {

    public static final String BASE_URL = "https://www.toggl.com";
    public static final String WORKSPACES_URL = BASE_URL + "/api/v8/workspaces";
    public static final String SUMMARY_URL = BASE_URL + "/reports/api/v2/summary";

    public List<WorkspaceSchema> getAllWorkspaces();

    public List<SummarySchema> importSummary();

    public String exportSummary();
}
