
package business;

import business.book.BookDao;
import business.book.BookDaoJdbc;
import business.category.CategoryDao;
import business.category.CategoryDaoJdbc;
import business.customer.CustomerDao;
import business.customer.CustomerDaoJdbc;
import business.order.*;

public class ApplicationContext {

    private final CategoryDao categoryDao;
    private final BookDao bookDao;
    private final CustomerDao customerDao;
    private final OrderDao orderDao;
    private final LineItemDao lineItemDao;
    private final OrderService orderService;

    public static final ApplicationContext INSTANCE = new ApplicationContext();

    private ApplicationContext() {
        categoryDao = new CategoryDaoJdbc();
        bookDao = new BookDaoJdbc();
        customerDao = new CustomerDaoJdbc();
        orderDao = new OrderDaoJdbc();
        lineItemDao = new LineItemDaoJdbc();

        orderService = new DefaultOrderService();
        ((DefaultOrderService)orderService).setBookDao(bookDao);
        ((DefaultOrderService)orderService).setCustomerDao(customerDao);
        ((DefaultOrderService)orderService).setOrderDao(orderDao);
        ((DefaultOrderService)orderService).setLineItemDao(lineItemDao);
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }
    public BookDao getBookDao() { return bookDao; }
    public OrderService getOrderService() { return orderService; }
}
