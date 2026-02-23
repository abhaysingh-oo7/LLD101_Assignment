public class RoomPricing implements PricingComponent {

    int roomType;

    public RoomPricing(int roomType) {
        this.roomType = roomType;
    }

    @Override
    public Money monthlyFee() {
        double base;

        switch (roomType) {
            case LegacyRoomTypes.SINGLE -> base = 14000.0;
            case LegacyRoomTypes.DOUBLE -> base = 15000.0;
            case LegacyRoomTypes.TRIPLE -> base = 12000.0;
            default -> base = 16000.0;
        }

        return new Money(base);
    }
}