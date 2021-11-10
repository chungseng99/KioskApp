package kioskapp.itemproduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import orderserver.database.OrderDatabaseController;

/**
 * This is the controller class for item product
 * @author Group S
 *
 */
public class ItemProductController {

	/**
	 * This function get the list of item product from database
	 * @return
	 */
	public List<ItemProduct> getListOfItemProduct() {
		ArrayList<ItemProduct> itemProductList = new ArrayList<ItemProduct>();
		try {

			OrderDatabaseController controller = new OrderDatabaseController();
			Connection con = controller.getConnection();

			String query = "select * from itemproduct";

			PreparedStatement statement = con.prepareStatement(query);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				itemProductList.add(getItemProductFromResultSet(result));
			}

			return itemProductList;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * This function get ItemProduct from a result set
	 * 
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	private ItemProduct getItemProductFromResultSet(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		ItemProduct itemProduct = new ItemProduct();
		itemProduct.setItemProduct(result.getInt(1));
		itemProduct.setName(result.getString(2));
		itemProduct.setPrice(result.getFloat(3));

		return itemProduct;
	}

}
