public class AddOnPricing implements PricingComponent {

    private final AddOn addOn;

    public AddOnPricing(AddOn addOn) {
        this.addOn = addOn;
    }

    @Override
    public Money monthlyFee() {
        double price = 0.0;

        if (addOn == AddOn.MESS) price = 1000.0;
        else if (addOn == AddOn.LAUNDRY) price = 500.0;
        else if (addOn == AddOn.GYM) price = 300.0;

        return new Money(price);
    }
}