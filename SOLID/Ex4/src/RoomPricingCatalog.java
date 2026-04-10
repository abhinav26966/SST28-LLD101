import java.util.*;

public class RoomPricingCatalog {
    private final Map<Integer, RoomPricing> pricings = new HashMap<>();
    private final RoomPricing fallback;

    public RoomPricingCatalog(List<RoomPricing> all, RoomPricing fallback) {
        for (RoomPricing p : all) pricings.put(p.roomType(), p);
        this.fallback = fallback;
    }

    public static RoomPricingCatalog defaults() {
        DeluxeRoomPricing deluxe = new DeluxeRoomPricing();
        return new RoomPricingCatalog(
            List.of(new SingleRoomPricing(), new DoubleRoomPricing(), new TripleRoomPricing(), deluxe),
            deluxe
        );
    }

    public Money baseMonthly(int roomType) {
        return pricings.getOrDefault(roomType, fallback).baseMonthly();
    }
}
