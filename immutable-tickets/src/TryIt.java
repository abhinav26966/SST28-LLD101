import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Demonstrates IncidentTicket immutability:
 *   - "updates" return new instances; the original is never mutated
 *   - the tags list exposed via getTags() cannot be modified from outside
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket original = service.createTicket(
                "TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created:  " + original);

        IncidentTicket assigned = service.assign(original, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nOriginal unchanged: " + original);
        System.out.println("Assigned (new):     " + assigned);
        System.out.println("Escalated (new):    " + escalated);

        List<String> leaked = escalated.getTags();
        try {
            leaked.add("HACKED_FROM_OUTSIDE");
            System.out.println("\nUnexpected: tag list was mutated!");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nExternal tag mutation rejected (list is unmodifiable).");
        }
        System.out.println("Escalated still:    " + escalated);

        IncidentTicket updated = escalated.toBuilder()
                .description("Retried after maintenance window")
                .slaMinutes(60)
                .build();
        System.out.println("\nUpdated via toBuilder(): " + updated);
    }
}
