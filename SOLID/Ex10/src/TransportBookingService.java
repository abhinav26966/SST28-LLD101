public class TransportBookingService {
    private final DistanceService distance;
    private final DriverAllocationService allocator;
    private final PaymentService payment;
    private final FarePolicy farePolicy;

    public TransportBookingService() {
        this(new DistanceCalculator(), new DriverAllocator(), new PaymentGateway(), new StandardFarePolicy());
    }

    public TransportBookingService(DistanceService distance, DriverAllocationService allocator,
                                   PaymentService payment, FarePolicy farePolicy) {
        this.distance = distance;
        this.allocator = allocator;
        this.payment = payment;
        this.farePolicy = farePolicy;
    }

    public void book(TripRequest req) {
        double km = distance.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = allocator.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = farePolicy.fareFor(km);

        String txn = payment.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}
