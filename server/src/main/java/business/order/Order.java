package business.order;

import java.util.Date;

public class Order {

    private final long orderId;
    private final int amount;
    private final Date dateCreated;
    private final long confirmationNumber;
    private final long customerId;

    public Order(long orderId, int amount, Date dateCreated, long confirmationNumber, long customerId) {
        this.orderId = orderId;
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.confirmationNumber = confirmationNumber;
        this.customerId = customerId;
    }

    public long getOrderId() {
        return orderId;
    }

    public int getAmount() {
        return amount;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public long getConfirmationNumber() {
        return confirmationNumber;
    }

    public long getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", amount=" + amount +
                ", dateCreated=" + dateCreated +
                ", confirmationNumber=" + confirmationNumber +
                ", customerId=" + customerId +
                '}';
    }
}
