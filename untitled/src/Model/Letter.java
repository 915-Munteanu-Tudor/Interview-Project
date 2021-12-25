package Model;

import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;

public class Letter {
    private final LocalDate date;
    private List<Item> items;

    public Letter(LocalDate date, List<Item> items) {
        this.date = date;
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Date: ").append(getDate().toString()).append("\n\t\t\t").append("Items:\n\t\t\t\t");

        ListIterator<Item> listIterator = getItems().listIterator(getItems().size());
        while (listIterator.hasPrevious()) {
            s.append(listIterator.previous().toString()).append("\n\t\t\t\t");
        }
        return s.toString();
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
