package business.order;

import java.sql.Connection;
import java.util.List;

public interface OrderDao {

    long create(Connection connection, int amount, int confirmationNumber, long customerId);

    List<Order> findAll();

    Order findByOrderId(long orderId);

    List<Order> findByCustomerId(long customerId);
}
