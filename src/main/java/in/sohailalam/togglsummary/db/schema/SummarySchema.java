package in.sohailalam.togglsummary.db.schema;

import in.sohailalam.togglsummary.json.SummaryDataJson;
import in.sohailalam.togglsummary.utils.Helpers;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * The Summary database schema.
 */
@Document(collection = "summary")
public class SummarySchema {

    @Id
    private String id;
    private String month;
    private long workspace_id;
    private String project_title;
    private long total_time;
    private List<SummaryItemSchema> items;

    /**
     * Instantiates a new Summary schema.
     */
    public SummarySchema() {
        id = getId();
    }

    /**
     * Instantiates a new Summary schema.
     *
     * @param month       the month
     * @param workspaceId the workspace id
     * @param json        the json
     */
    public SummarySchema(String month, long workspaceId, SummaryDataJson json) {
        this.month = month;
        this.workspace_id = workspaceId;
        this.project_title = json.getTitle().getProject();
        this.total_time = json.getTime();
        this.items = new ArrayList<>();
        json.getItems().forEach(item -> this.items.add(new SummaryItemSchema(item)));
        id = getId();
    }

    /**
     * Instantiates a new Summary schema.
     *
     * @param month         the month
     * @param workspace_id  the workspace id
     * @param project_title the project title
     * @param total_time    the total time
     * @param items         the items
     */
    public SummarySchema(String month, long workspace_id, String project_title, long total_time, List<SummaryItemSchema> items) {
        this.month = month;
        this.workspace_id = workspace_id;
        this.project_title = project_title;
        this.total_time = total_time;
        this.items = items;
        id = getId();
    }

    /**
     * Gets month.
     *
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets month.
     *
     * @param month the month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        if(id == null) {
            return Helpers.getBase64Encoding(this.workspace_id + "-" + this.month + "-" + this.project_title);
        }
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets workspace id.
     *
     * @return the workspace id
     */
    public long getWorkspace_id() {
        return workspace_id;
    }

    /**
     * Sets workspace id.
     *
     * @param workspace_id the workspace id
     */
    public void setWorkspace_id(long workspace_id) {
        this.workspace_id = workspace_id;
    }

    /**
     * Gets project title.
     *
     * @return the project title
     */
    public String getProject_title() {
        return project_title;
    }

    /**
     * Sets project title.
     *
     * @param project_title the project title
     */
    public void setProject_title(String project_title) {
        this.project_title = project_title;
    }

    /**
     * Gets total time.
     *
     * @return the total time
     */
    public long getTotal_time() {
        return total_time;
    }

    /**
     * Sets total time.
     *
     * @param total_time the total time
     */
    public void setTotal_time(long total_time) {
        this.total_time = total_time;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<SummaryItemSchema> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(List<SummaryItemSchema> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "SummarySchema{" +
                "id='" + id + '\'' +
                ", month='" + month + '\'' +
                ", workspace_id=" + workspace_id +
                ", project_title='" + project_title + '\'' +
                ", total_time=" + total_time +
                ", items=" + items +
                '}';
    }
}
