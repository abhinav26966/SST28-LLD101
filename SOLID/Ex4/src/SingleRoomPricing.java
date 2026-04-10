public class SingleRoomPricing implements RoomPricing {
    public int roomType() { return LegacyRoomTypes.SINGLE; }
    public Money baseMonthly() { return new Money(14000.0); }
}
