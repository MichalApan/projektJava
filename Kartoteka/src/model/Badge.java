package model;

/**
 *
 * @author Michał
 */
public class Badge {

    private int id;
    private String title;
    private String tasks;
    private final String[] columnNames = {"badgeId", "title", "tasks"};

    public Badge() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String taksk) {
        this.tasks = taksk;
    }

    public Badge(int id, String title, String tasks) {
        this.id = id;
        this.title = title;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "[" + id + "] - " + title + " - " + tasks;
    }

    public String getColumnAt(int column) {
        return columnNames[column];
    }
}
