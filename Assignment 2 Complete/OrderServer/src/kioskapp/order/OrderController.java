package kioskapp.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kioskapp.ordereditem.OrderedItem;
import orderserver.database.OrderDatabaseController;

/**
 * This is the controller class for order
 * @author Group S
 *
 */
public class OrderController {

	/**
	 * This function calculates the total amount for the order
	 * @param order
	 */
	public void calculateTotalAmount(Order order) {
		float total = 0;
		for (OrderedItem orderedItem : order.getOrderedItems()) {
			total += orderedItem.getSubTotalAmount();
		}
		order.setTotalAmount(total);
	}

	/**
	 * This function insert order into the database and return the id
	 * @param order
	 * @return
	 */
	public int insertOrder(Order order) {

		try {
			OrderDatabaseController dbController = new OrderDatabaseController();
			Connection con = dbController.getConnection();
			String query = "insert into `order` (TotalAmount,OrderReferenceNumber) values(?,?)";
			PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setFloat(1, order.getTotalAmount());
			statement.setInt(2, order.getOrderReferenceNumber());

			int i = statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

}
