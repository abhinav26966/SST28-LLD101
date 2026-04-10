package com.example.reports;

/**
 * Depends only on the {@link Report} abstraction, so the viewer is unaware
 * of whether it is talking to a proxy or a real report.
 */
public class ReportViewer {

    public void open(Report report, User user) {
        report.display(user);
    }
}
