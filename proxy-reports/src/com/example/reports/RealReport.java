package com.example.reports;

/**
 * RealSubject. Owns the expensive disk load. The loaded content is cached on
 * this instance so the disk read happens at most once per RealReport.
 */
public class RealReport implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private String content;

    public RealReport(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        if (content == null) {
            content = loadFromDisk();
        }
        System.out.println("REPORT -> id=" + reportId
                + " title=" + title
                + " classification=" + classification
                + " openedBy=" + user.getName());
        System.out.println("CONTENT: " + content);
    }

    public String getClassification() {
        return classification;
    }

    private String loadFromDisk() {
        System.out.println("[disk] loading report " + reportId + " ...");
        try { Thread.sleep(120); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return "Internal report body for " + title;
    }
}
