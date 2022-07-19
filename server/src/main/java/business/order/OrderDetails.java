package business.order;

import business.book.Book;
import business.customer.Customer;

import java.util.Collections;
import java.util.List;

public class OrderDetails {

    private final Order order;
    private final Customer customer;
    private final List<LineItem> lineItems;
    private final List<Book> books;

    public OrderDetails(Order order, Customer customer,
                        List<LineItem> lineItems, List<Book> books) {
        this.order = order;
        this.customer = customer;
        this.lineItems = lineItems;
        this.books = books;
    }

    public Order getOrder() {
        return order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public List<LineItem> getLineItems() {
        return Collections.unmodifiableList(lineItems);
    }
}
