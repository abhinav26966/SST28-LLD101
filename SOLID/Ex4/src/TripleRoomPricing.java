public class TripleRoomPricing implements RoomPricing {
    public int roomType() { return LegacyRoomTypes.TRIPLE; }
    public Money baseMonthly() { return new Money(12000.0); }
}
