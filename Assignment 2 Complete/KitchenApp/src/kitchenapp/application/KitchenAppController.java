package kitchenapp.application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import kioskapp.order.Order;
import kioskapp.ordereditem.OrderedItem;
import kitchenapp.view.KitchenFrame;
import kitchenapp.view.KitchenOrderPanel;

/**
 * This is the class for Kitchen Application Controller
 * This class will display GUI for kitchen and receive order from order server
 * @author Group S
 *
 */
public class KitchenAppController {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;

		// Create new socket for kitchen server
		int portNo = 4230;
		serverSocket = new ServerSocket(portNo);

		// Display GUI for kitchen frame
		KitchenFrame frame = new KitchenFrame();
		frame.setVisible(true);

		while (true) {
			// Wait for connection
			Socket clientSocket = serverSocket.accept();

			// Update the view
			try {

				// Read mode and order from order server
				ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
				String mode = (String) inputStream.readObject();
				Order order = (Order) inputStream.readObject();
				
				// Create new kitchen order panel component
				KitchenOrderPanel panel = new KitchenOrderPanel();
				panel.updateOrderIDField(order.getOrderId());
				for (OrderedItem orderedItem : order.getOrderedItems()) {
					panel.insertTableRow(orderedItem.getItemProduct().getName(), orderedItem.getQuantity());
				}
				
				// Add panel to respective area depending on mode
				if (mode.trim().equals("Dine In")) {
					frame.getPanelForAllDineInOrder().add(panel);
				} else {
					frame.getPanelForAllTakeAwayOrder().add(panel);
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
