package hamcresttest;

public class TODO {
    private final int id;
    private final String summary;
    private final String important;

    public TODO(int id, String summary, String important) {
        this.id=id;
        this.summary=summary;
        this.important=important;
    }

    public int getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public String getImportant() {
        return important;
    }
}
