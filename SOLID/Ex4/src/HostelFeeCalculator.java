import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final RoomPricingCatalog rooms;
    private final AddOnPricingCatalog addOns;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this(repo, RoomPricingCatalog.defaults(), AddOnPricingCatalog.defaults());
    }

    public HostelFeeCalculator(FakeBookingRepo repo, RoomPricingCatalog rooms, AddOnPricingCatalog addOns) {
        this.repo = repo;
        this.rooms = rooms;
        this.addOns = addOns;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        Money total = rooms.baseMonthly(req.roomType);
        for (AddOn a : req.addOns) {
            total = total.plus(addOns.priceOf(a));
        }
        return total;
    }
}
