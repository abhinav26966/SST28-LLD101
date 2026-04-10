import java.util.*;

public class AddOnPricingCatalog {
    private final Map<AddOn, Money> prices;

    public AddOnPricingCatalog(Map<AddOn, Money> prices) { this.prices = prices; }

    public static AddOnPricingCatalog defaults() {
        Map<AddOn, Money> m = new EnumMap<>(AddOn.class);
        m.put(AddOn.MESS, new Money(1000.0));
        m.put(AddOn.LAUNDRY, new Money(500.0));
        m.put(AddOn.GYM, new Money(300.0));
        return new AddOnPricingCatalog(m);
    }

    public Money priceOf(AddOn a) {
        return prices.getOrDefault(a, new Money(0.0));
    }
}
