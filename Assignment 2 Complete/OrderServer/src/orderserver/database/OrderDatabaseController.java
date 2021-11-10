package orderserver.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kioskapp.itemproduct.ItemProduct;
import kioskapp.order.Order;
import kioskapp.ordereditem.OrderedItem;
import kioskapp.ordertransaction.OrderTransaction;

/**
 * This class handles all the database connection and queries for order server
 * 
 * @author Group S
 *
 */
public class OrderDatabaseController {
	private String databaseName = "kioskappb_dev";
	private String username = "root";
	private String password = "abc123";

	private String connectionPath = "jdbc:mysql://localhost:3306/" + databaseName+"?serverTimezone=GMT%2B8";

	/**
	 * This function connects to the database and return the connection
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		// get connection object from the database
		Connection connection = DriverManager.getConnection(connectionPath, username, password);
		return connection;
	}
	
}
