package kioskapp.ordertransaction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kioskapp.itemproduct.ItemProduct;
import kioskapp.order.Order;
import kioskapp.ordereditem.OrderedItem;
import orderserver.database.OrderDatabaseController;

/**
 * This is the controller class for order transaction
 * @author Group S
 *
 */
public class OrderTransactionController {

	/**
	 * This function insert the order transaction into the database and get the id
	 * of this transaction
	 * 
	 * @param orderTransaction
	 * @return
	 */
	public int insertOrderTransaction(OrderTransaction orderTransaction) {

		try {
			OrderDatabaseController dbController = new OrderDatabaseController();
			Connection con = dbController.getConnection();
			String query = "insert into ordertransaction (TransactionDate,`Order`,AmountCharged,TransactionStatus,Last4Digits,OrderMode) values (?,?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1, orderTransaction.getTransactioDate());
			statement.setInt(2, orderTransaction.getOrder().getOrderId());
			statement.setFloat(3, orderTransaction.getAmountCharged());
			statement.setBoolean(4, true);
			statement.setInt(5, orderTransaction.getLast4Digits());
			statement.setString(6, orderTransaction.getOrderMode());

			int i = statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				return rs.getInt(1)+1;
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

	/**
	 * This function search for transaction of the given date and generate a reference number for order
	 * @param date
	 * @return
	 */
	public int generateReferenceNumber(Date date) {
		
		try {

			OrderDatabaseController controller = new OrderDatabaseController();
			Connection con = controller.getConnection();

			String query = "select count(*) from ordertransaction where TransactionDate = ?";

			PreparedStatement statement = con.prepareStatement(query);
			statement.setDate(1, date);

			ResultSet result = statement.executeQuery();

			int counter = 0;
			
			if(result.next()) {
				counter = result.getInt(1);
			}

			return counter;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * This function calculate the amount charged for the order
	 * @param order
	 * @return
	 */
	public float calculateAmountCharged(Order order) {
		// TODO Auto-generated method stub
		float total = 0;
		for(OrderedItem orderedItem : order.getOrderedItems()) {
			total += orderedItem.getSubTotalAmount();
		}
		return total;
	}

}
