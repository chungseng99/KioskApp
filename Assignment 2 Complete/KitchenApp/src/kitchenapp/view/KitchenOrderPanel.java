package kitchenapp.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 * This is a custom panel for kitchen order
 * @author Group S
 *
 */
public class KitchenOrderPanel extends JLayeredPane {

	private JPanel panelForOrderID;
	private JLabel lblOrderID;
	private JTextField textField;
	private JPanel panelForOrderList;
	private JScrollPane scrollPanelForOrderList;
	private JTable tableForOrderList;
	private JPanel panelForButtonDone;
	private JButton btnOrderDone;

	private String[] cols = { "Item Product", "Quantity" };
	private String[][] data = {};
	private DefaultTableModel model = new DefaultTableModel(data, cols);

	public KitchenOrderPanel() {
		this.setPreferredSize(new Dimension(443, 311));
		this.setLayout(null);

		panelForOrderID = new JPanel();
		panelForOrderID.setBounds(0, 0, 443, 35);
		this.add(panelForOrderID);
		panelForOrderID.setBackground(SystemColor.inactiveCaption);
		panelForOrderID.setLayout(null);

		lblOrderID = new JLabel("Order ID: ");
		lblOrderID.setForeground(new Color(255, 255, 255));
		lblOrderID.setFont(new Font("Serif", Font.PLAIN, 15));
		lblOrderID.setBounds(10, 5, 65, 25);
		panelForOrderID.add(lblOrderID);

		textField = new JTextField();
		textField.setBounds(85, 5, 96, 25);
		panelForOrderID.add(textField);
		textField.setColumns(10);

		panelForOrderList = new JPanel();
		panelForOrderList.setBounds(0, 34, 443, 242);
		this.add(panelForOrderList);
		panelForOrderList.setBackground(new Color(255, 255, 255));
		panelForOrderList.setLayout(null);

		scrollPanelForOrderList = new JScrollPane();
		scrollPanelForOrderList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanelForOrderList.setBounds(10, 10, 423, 222);
		panelForOrderList.add(scrollPanelForOrderList);

		tableForOrderList = new JTable();
		
		// Setup model
		tableForOrderList.setModel(model);
		tableForOrderList.getColumnModel().getColumn(0).setPreferredWidth(300);
		scrollPanelForOrderList.setViewportView(tableForOrderList);

		panelForButtonDone = new JPanel();
		panelForButtonDone.setBounds(0, 276, 443, 35);
		this.add(panelForButtonDone);
		panelForButtonDone.setBackground(SystemColor.inactiveCaption);
		panelForButtonDone.setLayout(null);

		btnOrderDone = new JButton("Done");
		btnOrderDone.setFont(new Font("Serif", Font.BOLD, 15));
		btnOrderDone.setBounds(348, 5, 85, 25);
		panelForButtonDone.add(btnOrderDone);
		btnOrderDone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeOrder();
			}

		});
	}

	/**
	 * This function updates the orderID field
	 * @param orderID
	 */
	public void updateOrderIDField(int orderID) {
		this.textField.setText(Integer.toString(orderID));
	}

	/**
	 * This function insert table row into the ordered item table
	 * @param itemName
	 * @param quantity
	 */
	public void insertTableRow(String itemName, int quantity) {
		Object[] row = { itemName, quantity };
		this.model.addRow(row);
		this.tableForOrderList.setModel(model);
	}

	/**
	 * This function remove the panel from the frame
	 */
	private void closeOrder() {
		this.setVisible(false);
	}

}
