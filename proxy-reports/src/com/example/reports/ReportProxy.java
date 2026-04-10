package com.example.reports;

/**
 * Protection + virtual proxy for a {@link RealReport}.
 *
 * Responsibilities:
 *   - enforce access control via {@link AccessControl}
 *   - lazy-load the {@link RealReport} only on the first authorized view
 *   - cache the loaded RealReport so repeated views through this proxy reuse it
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl;

    private RealReport cached;

    public ReportProxy(String reportId, String title, String classification) {
        this(reportId, title, classification, new AccessControl());
    }

    public ReportProxy(String reportId, String title, String classification, AccessControl accessControl) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
        this.accessControl = accessControl;
    }

    @Override
    public void display(User user) {
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED -> id=" + reportId
                    + " classification=" + classification
                    + " user=" + user.getName() + " (role=" + user.getRole() + ")");
            return;
        }
        if (cached == null) {
            cached = new RealReport(reportId, title, classification);
        }
        cached.display(user);
    }
}
