public class DeluxeRoomPricing implements RoomPricing {
    public int roomType() { return LegacyRoomTypes.DELUXE; }
    public Money baseMonthly() { return new Money(16000.0); }
}
