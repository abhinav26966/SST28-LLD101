package com.example.tickets;

/**
 * Service layer that creates tickets. Never mutates tickets; "updates" return
 * new instances built from the existing one via the Builder.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        return IncidentTicket.builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        IncidentTicket.Builder b = t.toBuilder().priority("CRITICAL");
        if (!t.getTags().contains("ESCALATED")) {
            b.addTag("ESCALATED");
        }
        return b.build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        return t.toBuilder().assigneeEmail(assigneeEmail).build();
    }
}
