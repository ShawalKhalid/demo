package business.order;

import java.sql.Connection;
import java.util.List;

/**
 * Created by gregory on 4/7/17.
 */
public interface LineItemDao {

    void create(Connection connection, long bookId, long orderId, int quantity);

    List<LineItem> findByOrderId(long orderId);
}
