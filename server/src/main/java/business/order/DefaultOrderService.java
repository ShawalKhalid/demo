package business.order;

import api.ApiException;
import business.BookstoreDbException;
import business.JdbcUtils;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.cart.ShoppingCartItem;
import business.customer.Customer;
import business.customer.CustomerDao;
import business.customer.CustomerForm;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DefaultOrderService implements OrderService {

	private BookDao bookDao;
	private CustomerDao customerDao;
	private OrderDao orderDao;
	private LineItemDao lineItemDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public void setCustomerDao(CustomerDao customerDao) { this.customerDao = customerDao; }
	public void setOrderDao(OrderDao orderDao) { this.orderDao = orderDao; }
	public void setLineItemDao(LineItemDao lineItemDao) { this.lineItemDao = lineItemDao; }

	@Override
	public OrderDetails getOrderDetails(long orderId) {
		Order order = orderDao.findByOrderId(orderId);
		Customer customer = customerDao.findByCustomerId(order.getCustomerId());
		List<LineItem> lineItems = lineItemDao.findByOrderId(orderId);
		List<Book> books = lineItems
				.stream()
				.map(lineItem -> bookDao.findByBookId(lineItem.getBookId()))
				.collect(Collectors.toList());
		return new OrderDetails(order, customer, lineItems, books);
	}

	@Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

		validateCustomer(customerForm);
		validateCart(cart);

		try (Connection connection = JdbcUtils.getConnection()) {
			Date date = getDate(
					customerForm.getCcExpiryMonth(),
					customerForm.getCcExpiryYear());
			return performPlaceOrderTransaction(
					customerForm.getName(),
					customerForm.getAddress(),
					customerForm.getPhone(),
					customerForm.getEmail(),
					customerForm.getCcNumber(),
					date, cart, connection);
		} catch (SQLException e) {
			throw new BookstoreDbException("Error during close connection for customer order", e);
		}
	}

	private long performPlaceOrderTransaction(
			String name, String address, String phone,
			String email, String ccNumber, Date date,
			ShoppingCart cart, Connection connection) {
		try {
			connection.setAutoCommit(false);
			long customerId = customerDao.create(
					connection, name, address, phone, email,
					ccNumber, date);
			long customerOrderId = orderDao.create(
					connection,
					cart.getComputedSubtotal() + cart.getSurcharge(),
					generateConfirmationNumber(), customerId);
			for (ShoppingCartItem item : cart.getItems()) {
				lineItemDao.create(connection, customerOrderId,
						item.getBookId(), item.getQuantity());
			}
			connection.commit();
			return customerOrderId;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new BookstoreDbException("Failed to roll back transaction", e1);
			}
			return 0;
		}
	}

	private int generateConfirmationNumber() {
		return new Random().nextInt(999999999);
	}

	private Date getDate(String monthString, String yearString) {
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.MONTH, Integer.parseInt(monthString) - 1);
		calendar.set(Calendar.YEAR, Integer.parseInt(yearString));
		calendar.set(Calendar.HOUR_OF_DAY,23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);

		return calendar.getTime();
	}

	private void validateCustomer(CustomerForm customerForm) {

    	String name = customerForm.getName();
		String address = customerForm.getAddress();
		String phone = customerForm.getPhone();
		String email = customerForm.getEmail();
		String ccNumber = customerForm.getCcNumber();

		if (name == null || name.equals("") || name.length() < 4 || name.length() > 45) {
			throw new ApiException.InvalidParameter("Invalid name field");
		}

		if (address == null || address.equals("") || address.length() < 4 || address.length() > 45) {
			throw new ApiException.InvalidParameter("Invalid address field");
		}

		if (phone == null || phone.equals("")) {
			throw new ApiException.InvalidParameter("Invalid phone field");
		}
		phone = phone.replace(" ", "");
		phone = phone.replace("-", "");
		phone = phone.replace("(", "");
		phone = phone.replace(")", "");
		if (phone.length() != 10) {
			throw new ApiException.InvalidParameter("Invalid phone field");
		}

		if (email == null || email.equals("") || email.contains(" ") || !email.contains("@") || email.endsWith(".")) {
			throw new ApiException.InvalidParameter("Invalid email field");
		}

		if (ccNumber == null || ccNumber.equals("")) {
			throw new ApiException.InvalidParameter("Invalid credit card number field");
		}
		ccNumber = ccNumber.replace(" ", "");
		ccNumber = ccNumber.replace("-", "");
		if (ccNumber.length() < 14 || ccNumber.length() > 16) {
			throw new ApiException.InvalidParameter("Invalid credit card number field");
		}

		if (expiryDateIsInvalid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
			throw new ApiException.InvalidParameter("Invalid expiry date");

		}
	}

	private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {

		if (ccExpiryMonth == null || ccExpiryMonth.equals("")
			|| ccExpiryYear == null || ccExpiryYear.equals("")) {
			return true;
		}

		YearMonth currentYearMonth = YearMonth.now();
		int ccExpiryMonthInt = Integer.parseInt(ccExpiryMonth);
		if (ccExpiryMonthInt < 1 || ccExpiryMonthInt > 12) {
			return true;
		}

		int ccExpiryYearInt = Integer.parseInt(ccExpiryYear);
		int currentYear = LocalDate.now().getYear();
		if (ccExpiryYearInt < currentYear || ccExpiryYearInt > (currentYear + 15)) {
			return true;
		}
		YearMonth ccExpiryYearMonth = YearMonth.of(ccExpiryYearInt, ccExpiryMonthInt);

		return !ccExpiryYearMonth.isAfter(currentYearMonth) && !ccExpiryYearMonth.equals(currentYearMonth);
	}

	private void validateCart(ShoppingCart cart) {

		if (cart.getItems().size() <= 0) {
			throw new ApiException.InvalidParameter("Cart is empty.");
		}

		cart.getItems().forEach(item-> {
			if (item.getQuantity() < 1 || item.getQuantity() > 99) {
				throw new ApiException.InvalidParameter("Invalid quantity");
			}

			Book databaseBook = bookDao.findByBookId(item.getBookId());
			if (item.getBookForm().getPrice() != databaseBook.getPrice()) {
				throw new ApiException.InvalidParameter("Invalid price");
			}

			if (item.getBookForm().getCategoryId() != databaseBook.getCategoryId()) {
				throw new ApiException.InvalidParameter("Invalid category");
			}
		});
	}
}
