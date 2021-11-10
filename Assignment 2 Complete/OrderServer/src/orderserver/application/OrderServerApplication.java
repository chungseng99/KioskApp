package orderserver.application;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import kioskapp.itemproduct.ItemProduct;
import kioskapp.itemproduct.ItemProductController;
import kioskapp.order.Order;
import kioskapp.order.OrderController;
import kioskapp.ordereditem.OrderedItem;
import kioskapp.ordereditem.OrderedItemController;
import kioskapp.ordertransaction.OrderTransaction;
import kioskapp.ordertransaction.OrderTransactionController;
import orderserver.database.OrderDatabaseController;
import orderserver.view.OrderServerFrame;

/**
 * This is the class for order server application
 * This class will display the GUI and make connection with Kioskapp, Credit Card server, Kitchen and database.
 * @author Group S
 *
 */
public class OrderServerApplication {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// Display GUI for order server
		OrderServerFrame frame = new OrderServerFrame();
		frame.setVisible(true);

		ServerSocket serverSocket = null;

		// Create socket for order server
		int portNo = 4228;
		serverSocket = new ServerSocket(portNo);
		
		frame.updateServerStatus(false);

		// wait for connection from client
		Socket kioskSocket = serverSocket.accept();

		frame.updateServerStatus(true);

		// get list of item product
		ItemProductController itemProductController = new ItemProductController();
		List<ItemProduct> itemProductList = itemProductController.getListOfItemProduct();

		// use object output stream to output list of menu item to kiosk app
		ObjectOutputStream kioskOutputStream = new ObjectOutputStream(kioskSocket.getOutputStream());
		kioskOutputStream.writeObject(itemProductList);

		frame.updateRequestStatus("Sending list of item product to kiosk");

		kioskOutputStream.close();
		kioskSocket.close();

		while (true) {

			frame.updateServerStatus(false);

			// open the socket to wait for order
			kioskSocket = serverSocket.accept();

			frame.updateServerStatus(true);

			// get data from client request with object input stream
			ObjectInputStream kioskInputStream = new ObjectInputStream(kioskSocket.getInputStream());
			Order order = (Order) kioskInputStream.readObject();
			String mode = (String) kioskInputStream.readObject();
			String creditCardNumber = (String) kioskInputStream.readObject();
			int month = (int) kioskInputStream.readObject();
			int year = (int) kioskInputStream.readObject();
			int securityCode = (int) kioskInputStream.readObject();

			frame.updateRequestStatus("Received order, mode of order, credit card detail from kiosk");

			// connect to credit card server
			int creditCardPortNo = 4229;
			Socket creditCardServerSocket = new Socket(InetAddress.getLocalHost(), creditCardPortNo);

			frame.updateRequestStatus("Sending the data to server at port " + creditCardPortNo + " for validation");

			// send credit card detail to credit card server
			ObjectOutputStream creditCardOutputStream = new ObjectOutputStream(
					creditCardServerSocket.getOutputStream());
			creditCardOutputStream.writeObject(creditCardNumber);
			creditCardOutputStream.writeObject(month);
			creditCardOutputStream.writeObject(year);
			creditCardOutputStream.writeObject(securityCode);

			// read validation result from credit card server
			ObjectInputStream creditCardInputStream = new ObjectInputStream(creditCardServerSocket.getInputStream());
			if ((boolean) creditCardInputStream.readObject()) {
				frame.updateRequestStatus("Response received: Validation successful");

				// Close off the stream and port from credit card server
				creditCardInputStream.close();
				creditCardOutputStream.close();
				creditCardServerSocket.close();

				// Start database operation
				// insert order object to database
				OrderController orderController = new OrderController();
				OrderTransactionController transactionController = new OrderTransactionController();
				OrderedItemController orderedItemController = new OrderedItemController();
				
				orderController.calculateTotalAmount(order);
				order.setOrderReferenceNumber(transactionController.generateReferenceNumber(Date.valueOf(LocalDate.now())));
				order.setOrderId(orderController.insertOrder(order));

				// insert ordered item to database
				for (OrderedItem orderedItem : order.getOrderedItems()) {
					orderedItemController.insertOrderedItem(orderedItem, order.getOrderId());
				}

				// insert the order transaction to database
				OrderTransaction orderTransaction = new OrderTransaction();
				orderTransaction.setLast4Digits(Integer.valueOf(creditCardNumber.substring(creditCardNumber.length() - 4)));
				orderTransaction.setOrder(order);
				orderTransaction.setAmountCharged(transactionController.calculateAmountCharged(orderTransaction.getOrder()));
				orderTransaction.setOrderMode(mode);
				orderTransaction.setTransactioDate(Date.valueOf(LocalDate.now()));
				orderTransaction.setTransactionStatus(true);
				orderTransaction.setOrderTransactionId(transactionController.insertOrderTransaction(orderTransaction));
				// End of database operation
				
				// connect to kitchen
				int kitchenPort = 4230;
				Socket kitchenSocket = new Socket(InetAddress.getLocalHost(), kitchenPort);

				// send order to kitchen
				ObjectOutputStream kitchenOutputStream = new ObjectOutputStream(kitchenSocket.getOutputStream());
				frame.updateRequestStatus("Sending order mode to kitchen");
				kitchenOutputStream.writeObject(mode);
				frame.updateRequestStatus("Sending order to kitchen");
				kitchenOutputStream.writeObject(order);
				kitchenOutputStream.close();
				kitchenSocket.close();

				// Send order transaction to kiosk
				frame.updateRequestStatus("Sending order transaction to kiosk");
				kioskOutputStream = new ObjectOutputStream(kioskSocket.getOutputStream());
				kioskOutputStream.writeObject(orderTransaction);

				// Close off connection
				kioskOutputStream.close();
				kioskSocket.close();

			} else {

				// Exceptional route
				frame.updateRequestStatus("Response received: Validation failed");
				OrderTransaction orderTransaction = new OrderTransaction();
				orderTransaction.setTransactionStatus(false);
				
				frame.updateRequestStatus("Sending signal to kiosk for exceptional flow");
				
				// Send failed order transaction to kiosk
				kioskOutputStream = new ObjectOutputStream(kioskSocket.getOutputStream());
				kioskOutputStream.writeObject(orderTransaction);
			}
		}
	}
}
