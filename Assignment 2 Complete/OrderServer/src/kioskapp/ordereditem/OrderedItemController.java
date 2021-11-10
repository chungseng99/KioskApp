package kioskapp.ordereditem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import orderserver.database.OrderDatabaseController;

/**
 * This is the controller class for ordered item
 * @author Group S
 *
 */
public class OrderedItemController {

	/**
	 * This function insert the ordered item into database
	 * @param orderedItem
	 * @param orderID
	 */
	public void insertOrderedItem(OrderedItem orderedItem, int orderID) {

		try {
			OrderDatabaseController dbController = new OrderDatabaseController();
			Connection con = dbController.getConnection();

			// initialize query
			String query = "insert into ordereditem(ItemProduct,Quantity,SubTotalAmount,`Order`)" + " values(?,?,?,?)";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, orderedItem.getItemProduct().getItemProduct());
			statement.setInt(2, orderedItem.getQuantity());
			statement.setFloat(3, orderedItem.getSubTotalAmount());
			statement.setInt(4, orderID);

			// execute query
			int i = statement.executeUpdate();
			System.out.println(i + " row inserted");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
