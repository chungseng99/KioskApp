package kioskapp.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import kioskapp.itemproduct.ItemProduct;
import kioskapp.order.Order;
import kioskapp.ordereditem.OrderedItem;
import kioskapp.ordertransaction.OrderTransaction;
import kioskapp.view.OrderFrame;

/**
 * This is the class for kiosk application
 * This class will display the kiosk GUI and connect to order server
 * @author Group S
 *
 */
public class KioskApplication {
	
	private static List<OrderedItem> orderedItems;
	private static List<String> menuItems;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		orderedItems = new ArrayList<OrderedItem>();
		menuItems = new ArrayList<String>();
		Order order = new Order();

		// Connect to server and get list of item product for menu display
		int portNo = 4228;
		Socket socket = new Socket(InetAddress.getLocalHost(), portNo);
		ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
		List<ItemProduct> itemProductList = (List<ItemProduct>) objectInputStream.readObject();
		OrderFrame frame = new OrderFrame();
		for (ItemProduct item : itemProductList) {
			menuItems.add(item.getName());
		}
		// Set the comboBox with menu choices
		frame.getComboBoxForItem().setModel(new DefaultComboBoxModel<Object>(menuItems.toArray()));

		// the add button will add the current item product selected into the list
		frame.getBtnAdd().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Create ordered item object
				OrderedItem orderedItem = new OrderedItem();
				orderedItem.setItemProduct(itemProductList.get(frame.getComboBoxForItem().getSelectedIndex()));
				orderedItem.setQuantity((int) frame.getSpinnerFroQuantity().getValue());
				orderedItem.setSubTotalAmount(orderedItem.getItemProduct().getPrice() * orderedItem.getQuantity());

				// Add to the list of ordered item
				orderedItems.add(orderedItem);
				
				String messageContent = "The following order has been added:\n"
						+ "Item Product: "+orderedItem.getItemProduct().getName().trim()+"\n"
						+ "Quantity: "+orderedItem.getQuantity()+"\n"
						+ "SubTotal: "+orderedItem.getSubTotalAmount()+"\n";
				
				// Pop up display to show order added message
				new JOptionPane().showMessageDialog(null, messageContent);
			}
		});

		// the process payment button will add the list of ordered item into order and
		// update the table and total
		frame.getBtnProcessPayment().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// populate the table with the ordered item and calculate the total amount
				float total = 0;
				order.setOrderedItems(orderedItems);
				for (OrderedItem orderedItem : order.getOrderedItems()) {
					total += orderedItem.getSubTotalAmount();
					Object row[] = { orderedItem.getItemProduct().getName(), orderedItem.getQuantity(),
							orderedItem.getSubTotalAmount() };
					frame.getModel().addRow(row);
				}
				frame.getTable().setModel(frame.getModel());
				total = (float) (Math.round(total * 100.0) / 100.0);
				frame.getTextFieldForTotal().setText(Float.toString(total));
			}

		});

		// The order button will send the order, mode and credit card detail to the
		// order server
		frame.getBtnOrder().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Socket socket;
				try {

					// Connect to the order server socket
					socket = new Socket(InetAddress.getLocalHost(), portNo);

					// write to the order server
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
					objectOutputStream.writeObject(order);
					objectOutputStream.writeObject(frame.getComboBoxForMode().getSelectedItem());
					objectOutputStream.writeObject(frame.getTextFieldForCreditCard().getText());
					objectOutputStream.writeObject(frame.getSpinnerForMonth().getValue());
					objectOutputStream.writeObject(Integer.valueOf(frame.getTextFieldForYear().getText()));
					objectOutputStream.writeObject(Integer.valueOf(frame.getTextFieldForCVV().getText()));

					// read order transaction sent from the order server
					ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
					OrderTransaction orderTransaction = (OrderTransaction) objectInputStream.readObject();
					
					// Check if credit card validation is successful
					if(orderTransaction.isTransactionStatus()) {
						// Generate receipt
						// Change the path here to change saving location
						String path = "C:\\Users\\scs99\\Desktop\\New folder\\DAD";
						String fileName = path + orderTransaction.getTransactioDate().toString() + "_"
								+ orderTransaction.getOrderTransactionId() + ".txt";
						FileOutputStream fout = new FileOutputStream(fileName);
						
						// Template for receipt
						String content = "===================================================================\n"
								+ "\t\t\t\tReceipt\n"
								+ "===================================================================\n"
								+ "Description\t\t\t\tQuantity\t   Subtotal\n"
								+ "===================================================================\n";
						for (OrderedItem orderedItem : orderTransaction.getOrder().getOrderedItems()) {
							content += rightpad(orderedItem.getItemProduct().getName().trim(), 40)
									+ rightpad(Integer.toString(orderedItem.getQuantity()), 5) + "\t\t"
									+ leftpad(Float.toString((float) orderedItem.getSubTotalAmount()), 10) + "\n";
						}
						content += "\n\n\n" + "===================================================================\n"
								+ "Total Amount\t\t\t\t\t\t"
								+ leftpad(Float.toString((float) orderTransaction.getAmountCharged()), 10) + "\n"
								+ "===================================================================\n"
								+ "Credit Card Number : **** **** **** " + orderTransaction.getLast4Digits() + "\n"
								+ "Transcation Date : " + orderTransaction.getTransactioDate() + "\n"
								+ "===================================================================\n"
								+ leftpad("Thank You", 35) + "\n"
								+ "===================================================================\n";

						// write the content to the file
						fout.write(content.getBytes());
						fout.close();
						
					}else {
						
						// Show message to user for exceptional flow
						String messageContent = "Invalid Credit Card details.";
						new JOptionPane().showMessageDialog(null, messageContent);
					}
					
					// Reset the data for new order
					orderedItems = new ArrayList<OrderedItem>();
					frame.resetView();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.setVisible(true);
	}
	
	// This function returns a fixed length string with whitespace padding on the left
	private static String leftpad(String text, int length) {
		return String.format("%" + length + "." + length + "s", text);
	}

	// This function returns a fixed length string with whitespace padding on the right
	private static String rightpad(String text, int length) {
		return String.format("%-" + length + "." + length + "s", text);
	}
	
}
