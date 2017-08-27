package in.sohailalam.togglsummary.db.schema;

import org.springframework.data.annotation.Id;


/**
 * The Workspace schema
 */
public class WorkspaceSchema {

    @Id
    private long id;
    private String name;
    private boolean admin;

    /**
     * Instantiates a new Workspace schema.
     */
    public WorkspaceSchema() {
    }

    /**
     * Instantiates a new Workspace schema.
     *
     * @param id    the id
     * @param name  the name
     * @param admin the admin
     */
    public WorkspaceSchema(long id, String name, boolean admin) {
        this.id = id;
        this.name = name;
        this.admin = admin;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Is admin boolean.
     *
     * @return the boolean
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets admin.
     *
     * @param admin the admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "WorkspaceSchema{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", admin=" + admin +
                '}';
    }
}
