package customer;

public class CustomerServiceImpl {
	private static CustomerDaoImpl cd = new CustomerDaoImpl();

	public int insert(Customer customer) {
		int result = 0;
		Customer ct = cd.select(customer.getId());
		return result;
	}
}