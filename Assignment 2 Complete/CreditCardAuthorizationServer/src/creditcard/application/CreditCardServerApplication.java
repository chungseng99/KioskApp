package creditcard.application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import creditcard.database.CreditCardDatabaseController;
import creditcard.model.CreditCard;
import creditcard.view.CreditCardServerFrame;

/**
 * This class is the credit card server application
 * This class will check if credit card is valid
 * 
 * @author Group S
 *
 */
public class CreditCardServerApplication {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;
		CreditCardDatabaseController controller = new CreditCardDatabaseController();

		// Open GUI for credit card server
		CreditCardServerFrame frame = new CreditCardServerFrame();
		frame.setVisible(true);

		// Create socket for credit card server
		int portNo = 4229;
		serverSocket = new ServerSocket(portNo);

		while (true) {

			frame.updateServerStatus(false);
			// Wait for connection
			Socket clientSocket = serverSocket.accept();

			frame.updateServerStatus(true);

			// Get details of credit card
			CreditCard card = new CreditCard();
			ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
			ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

			try {

				// Get credit card detail from input stream and create credit card object
				card.setCreditCard((String) inputStream.readObject());
				card.setMonth((int) inputStream.readObject());
				card.setYear((int) inputStream.readObject());
				card.setSecurityCode((int) inputStream.readObject());

				frame.updateRequestStatus("Received credit card details from order server");

				// validate and send response back to order server
				frame.updateRequestStatus("Sending Response: " + controller.validateCreditCard(card));
				outputStream.writeObject(controller.validateCreditCard(card));

			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				serverSocket.close();
			}
		}
	}
}
