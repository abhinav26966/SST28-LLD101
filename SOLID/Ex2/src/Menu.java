import java.util.*;

public class Menu {
    private final Map<String, MenuItem> items = new LinkedHashMap<>();

    public void add(MenuItem i) { items.put(i.id, i); }
    public MenuItem get(String id) { return items.get(id); }
}
