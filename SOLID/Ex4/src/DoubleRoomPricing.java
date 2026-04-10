public class DoubleRoomPricing implements RoomPricing {
    public int roomType() { return LegacyRoomTypes.DOUBLE; }
    public Money baseMonthly() { return new Money(15000.0); }
}
