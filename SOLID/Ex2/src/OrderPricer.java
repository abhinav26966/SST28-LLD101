import java.util.*;

public class OrderPricer {
    private final Menu menu;

    public OrderPricer(Menu menu) { this.menu = menu; }

    public List<PricedLine> price(List<OrderLine> lines) {
        List<PricedLine> priced = new ArrayList<>();
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            priced.add(new PricedLine(item.name, l.qty, item.price * l.qty));
        }
        return priced;
    }

    public double subtotal(List<PricedLine> priced) {
        double s = 0.0;
        for (PricedLine p : priced) s += p.lineTotal;
        return s;
    }
}
