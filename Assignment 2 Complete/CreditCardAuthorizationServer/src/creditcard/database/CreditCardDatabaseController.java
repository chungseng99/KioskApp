package creditcard.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import creditcard.model.CreditCard;

/**
 * This class handles all the database connection and query for credit card
 * server
 * 
 * @author Group S
 *
 */
public class CreditCardDatabaseController {
	private String databaseName = "bank_db_creditcard";
    private String username = "root";
    private String password = "abc123";

    private String connectionPath = "jdbc:mysql://localhost:3306/"+databaseName+"?serverTimezone=GMT%2B8";

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		// get connection object from the database
		Connection connection = DriverManager.getConnection(connectionPath, username, password);
		return connection;
	}

	/**
	 * This function checks the database for the credit card detail
	 * @param card
	 * @return
	 */
	public boolean validateCreditCard(CreditCard card) {
		try {
			Connection con = getConnection();

			String sql = "select * from creditcard where CreditCard = ? and Month = ? and Year = ? and SecurityCode = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, card.getCreditCard());
			statement.setInt(2, card.getMonth());
			statement.setInt(3, card.getYear());
			statement.setInt(4, card.getSecurityCode());

			ResultSet results = statement.executeQuery();

			while (results.next()) {
				return true;
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
