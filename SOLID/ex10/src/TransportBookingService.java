public class TransportBookingService {

    private final DistanceService distanceService;
    private final DriverService driverService;
    private final PaymentService paymentService;
    private final FarePolicy farePolicy;

    public TransportBookingService(
            DistanceService distanceService,
            DriverService driverService,
            PaymentService paymentService,
            FarePolicy farePolicy
        ){

        this.distanceService = distanceService;
        this.driverService = driverService;
        this.paymentService = paymentService;
        this.farePolicy = farePolicy;
    }

    
    // DIP violation: direct concretes
    public void book(TripRequest req) {
        // DistanceCalculator dist = new DistanceCalculator();
        // DriverAllocator alloc = new DriverAllocator();
        // PaymentGateway pay = new PaymentGateway();

        double km = distanceService.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = driverService.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        // double fare = 50.0 + km * 6.6666666667; // messy pricing
       
        double fare = farePolicy.calculate(km); // messy pricing
        fare = Math.round(fare * 100.0) / 100.0;

        String txn = paymentService.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}
