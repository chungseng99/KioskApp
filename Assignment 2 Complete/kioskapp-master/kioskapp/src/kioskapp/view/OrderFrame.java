/**
 * 
 */
package kioskapp.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

/**
 * This is the view for kioskapp
 * 
 * @author Group S
 *
 */
public class OrderFrame extends JFrame {

	private JPanel menuPanel_1, menuPanel_2;
	private JPanel orderModePanel_1, orderModePanel_2;
	private JPanel orderPanel_1, orderPanel_2, orderPanel_3;
	private JPanel paymentPanel_1, paymentPanel_2;
	private JPanel buttonPanel_1, buttonPanel_2, buttonPanel_3;
	private JScrollPane scrollPane;

	private JLabel lblMenu, lblItem, lblQuantity;
	private JLabel lblOrderMode, lblMode;
	private JLabel lblOrder, lblTotal;
	private JLabel lblPayment, lblText, lblValidMonth, lblValidYear, lblCVV;

	private JTextField textFieldForTotal;
	private JTextField textFieldForCreditCard;
	private JTextField textFieldForYear;
	private JTextField textFieldForCVV;

	private JButton btnAdd, btnProcessPayment, btnOrder;

	private JComboBox comboBoxForItem, comboBoxForMode;
	private JSpinner spinnerForQuantity, spinnerForMonth;
	private JTable table;

	private String[] cols = { "Item Product", "Quantity", "SubTotal Amount" };
	private String[][] data = {};
	private DefaultTableModel model = new DefaultTableModel(data, cols);

	// Create the layout
	public OrderFrame() {

		// Title of Window
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setFont(new Font("Serif", Font.PLAIN, 10));
		setTitle("Customer Order");
		getContentPane().setLayout(null);

		// Start part of menu
		menuPanel_1 = new JPanel();
		menuPanel_1.setBounds(10, 10, 290, 26);
		getContentPane().add(menuPanel_1);
		menuPanel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(153, 204, 255), null),
				"", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		menuPanel_1.setLayout(null);

		lblMenu = new JLabel("Menu");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Serif", Font.BOLD, 20));
		lblMenu.setBounds(10, 1, 270, 23);
		menuPanel_1.add(lblMenu);

		menuPanel_2 = new JPanel();
		menuPanel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(153, 204, 255), null),
				"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		menuPanel_2.setBounds(10, 46, 290, 80);
		getContentPane().add(menuPanel_2);
		menuPanel_2.setLayout(null);

		lblItem = new JLabel(" Item:  ");
		lblItem.setBounds(10, 9, 42, 27);
		lblItem.setFont(new Font("Serif", Font.PLAIN, 15));
		menuPanel_2.add(lblItem);

		comboBoxForItem = new JComboBox();
		comboBoxForItem.setFont(new Font("Serif", Font.PLAIN, 15));
		comboBoxForItem.setBounds(58, 9, 222, 27);
		menuPanel_2.add(comboBoxForItem);

		lblQuantity = new JLabel(" Qty: ");
		lblQuantity.setFont(new Font("Serif", Font.PLAIN, 15));
		lblQuantity.setBounds(10, 46, 42, 27);
		menuPanel_2.add(lblQuantity);

		spinnerForQuantity = new JSpinner();
		spinnerForQuantity.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerForQuantity.setFont(new Font("Serif", Font.PLAIN, 15));
		spinnerForQuantity.setBounds(58, 46, 50, 27);
		menuPanel_2.add(spinnerForQuantity);

		buttonPanel_1 = new JPanel();
		buttonPanel_1.setLayout(null);
		buttonPanel_1.setBounds(10, 136, 290, 34);
		getContentPane().add(buttonPanel_1);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(215, 5, 65, 23);
		buttonPanel_1.add(btnAdd);
		btnAdd.setFont(new Font("Serif", Font.BOLD, 12));
		// End part of menu

		// Start part of order mode
		orderModePanel_1 = new JPanel();
		orderModePanel_1.setLayout(null);
		orderModePanel_1
				.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(153, 204, 255), null), "",
						TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		orderModePanel_1.setBounds(10, 180, 290, 26);
		getContentPane().add(orderModePanel_1);

		lblOrderMode = new JLabel("Order Mode");
		lblOrderMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderMode.setFont(new Font("Serif", Font.BOLD, 20));
		lblOrderMode.setBounds(10, 1, 270, 23);
		orderModePanel_1.add(lblOrderMode);

		orderModePanel_2 = new JPanel();
		orderModePanel_2.setLayout(null);
		orderModePanel_2
				.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(153, 204, 255), null), "",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		orderModePanel_2.setBounds(10, 216, 290, 48);
		getContentPane().add(orderModePanel_2);

		lblMode = new JLabel("Mode: ");
		lblMode.setBounds(10, 10, 42, 27);
		orderModePanel_2.add(lblMode);
		lblMode.setFont(new Font("Serif", Font.PLAIN, 15));

		comboBoxForMode = new JComboBox();
		comboBoxForMode.setBounds(62, 10, 222, 27);
		orderModePanel_2.add(comboBoxForMode);
		comboBoxForMode.setModel(new DefaultComboBoxModel(new String[] { "Dine In", "Take Away" }));
		comboBoxForMode.setFont(new Font("Serif", Font.PLAIN, 15));
		this.setSize(new Dimension(800, 645));

		buttonPanel_2 = new JPanel();
		buttonPanel_2.setBounds(10, 274, 290, 34);
		getContentPane().add(buttonPanel_2);
		buttonPanel_2.setLayout(null);

		btnProcessPayment = new JButton("Process Payment");
		btnProcessPayment.setBounds(140, 5, 140, 23);
		btnProcessPayment.setFont(new Font("Serif", Font.BOLD, 12));
		buttonPanel_2.add(btnProcessPayment);
		// End part of order mode

		// Start part of order list
		orderPanel_1 = new JPanel();
		orderPanel_1.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(64, 64, 64), new Color(64, 64, 64)),
						"", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(255, 255, 255)));
		orderPanel_1.setForeground(SystemColor.text);
		orderPanel_1.setBackground(Color.DARK_GRAY);
		orderPanel_1.setBounds(310, 10, 466, 26);
		getContentPane().add(orderPanel_1);
		orderPanel_1.setLayout(null);

		lblOrder = new JLabel("Your Order");
		lblOrder.setFont(new Font("Serif", Font.BOLD, 20));
		lblOrder.setForeground(SystemColor.textHighlightText);
		lblOrder.setBackground(Color.DARK_GRAY);
		lblOrder.setBounds(10, 1, 136, 23);
		orderPanel_1.add(lblOrder);

		orderPanel_2 = new JPanel();
		orderPanel_2.setForeground(Color.WHITE);
		orderPanel_2.setBackground(Color.BLACK);
		orderPanel_2.setBounds(310, 35, 466, 525);
		getContentPane().add(orderPanel_2);
		orderPanel_2.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 446, 505);
		orderPanel_2.add(scrollPane);

		table = new JTable();
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.setFont(new Font("Serif", Font.PLAIN, 15));
		scrollPane.setViewportView(table);

		orderPanel_3 = new JPanel();
		orderPanel_3.setBackground(Color.BLACK);
		orderPanel_3.setForeground(Color.WHITE);
		orderPanel_3.setBounds(310, 558, 466, 40);
		getContentPane().add(orderPanel_3);
		orderPanel_3.setLayout(null);

		lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Serif", Font.BOLD, 20));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setBackground(Color.BLACK);
		lblTotal.setBounds(230, 5, 91, 25);
		orderPanel_3.add(lblTotal);

		textFieldForTotal = new JTextField();
		textFieldForTotal.setBounds(306, 5, 150, 25);
		orderPanel_3.add(textFieldForTotal);
		textFieldForTotal.setColumns(10);
		textFieldForTotal.setEditable(false);
		// End part of order

		// Start part of payment
		paymentPanel_1 = new JPanel();
		paymentPanel_1.setLayout(null);
		paymentPanel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(153, 204, 255), null),
				"", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		paymentPanel_1.setBounds(10, 318, 290, 26);
		getContentPane().add(paymentPanel_1);

		lblPayment = new JLabel("Payment");
		lblPayment.setHorizontalAlignment(SwingConstants.CENTER);
		lblPayment.setFont(new Font("Serif", Font.BOLD, 20));
		lblPayment.setBounds(10, 1, 270, 23);
		paymentPanel_1.add(lblPayment);

		paymentPanel_2 = new JPanel();
		paymentPanel_2.setLayout(null);
		paymentPanel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(153, 204, 255), null),
				"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		paymentPanel_2.setBounds(10, 354, 290, 200);
		getContentPane().add(paymentPanel_2);

		lblText = new JLabel("Please enter your credit card number: ");
		lblText.setFont(new Font("Serif", Font.PLAIN, 15));
		lblText.setBounds(10, 10, 270, 20);
		paymentPanel_2.add(lblText);

		textFieldForCreditCard = new JTextField();
		textFieldForCreditCard.setBounds(10, 40, 270, 30);
		paymentPanel_2.add(textFieldForCreditCard);
		textFieldForCreditCard.setColumns(10);

		lblValidMonth = new JLabel("Valid Month: ");
		lblValidMonth.setFont(new Font("Serif", Font.PLAIN, 15));
		lblValidMonth.setBounds(10, 78, 85, 30);
		paymentPanel_2.add(lblValidMonth);

		spinnerForMonth = new JSpinner();
		spinnerForMonth.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinnerForMonth.setFont(new Font("Serif", Font.PLAIN, 15));
		spinnerForMonth.setBounds(105, 80, 50, 27);
		paymentPanel_2.add(spinnerForMonth);

		lblValidYear = new JLabel("Valid Year: ");
		lblValidYear.setFont(new Font("Serif", Font.PLAIN, 15));
		lblValidYear.setBounds(10, 118, 85, 30);
		paymentPanel_2.add(lblValidYear);

		textFieldForYear = new JTextField();
		textFieldForYear.setBounds(105, 122, 85, 28);
		paymentPanel_2.add(textFieldForYear);
		textFieldForYear.setColumns(10);

		lblCVV = new JLabel("CVV: ");
		lblCVV.setBounds(10, 158, 85, 30);
		paymentPanel_2.add(lblCVV);
		lblCVV.setFont(new Font("Serif", Font.PLAIN, 15));

		textFieldForCVV = new JTextField();
		textFieldForCVV.setBounds(105, 160, 85, 30);
		paymentPanel_2.add(textFieldForCVV);
		textFieldForCVV.setColumns(10);

		buttonPanel_3 = new JPanel();
		buttonPanel_3.setLayout(null);
		buttonPanel_3.setBounds(10, 564, 290, 34);
		getContentPane().add(buttonPanel_3);

		btnOrder = new JButton("Order");
		btnOrder.setFont(new Font("Serif", Font.BOLD, 12));
		btnOrder.setBounds(190, 5, 90, 23);
		buttonPanel_3.add(btnOrder);
		// End part of payment

	}

	/**
	 * @return the btnAdd
	 */
	public JButton getBtnAdd() {
		return btnAdd;
	}

	/**
	 * @param btnAdd the btnAdd to set
	 */
	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	/**
	 * @return the btnProcessPayment
	 */
	public JButton getBtnProcessPayment() {
		return btnProcessPayment;
	}

	/**
	 * @param btnProcessPayment the btnProcessPayment to set
	 */
	public void setBtnProcessPayment(JButton btnProcessPayment) {
		this.btnProcessPayment = btnProcessPayment;
	}

	/**
	 * @return the btnOrder
	 */
	public JButton getBtnOrder() {
		return btnOrder;
	}

	/**
	 * @param btnOrder the btnOrder to set
	 */
	public void setBtnOrder(JButton btnOrder) {
		this.btnOrder = btnOrder;
	}

	/**
	 * @return the comboBoxForItem
	 */
	public JComboBox getComboBoxForItem() {
		return comboBoxForItem;
	}

	/**
	 * @param comboBoxForItem the comboBoxForItem to set
	 */
	public void setComboBoxForItem(JComboBox comboBoxForItem) {
		this.comboBoxForItem = comboBoxForItem;
	}

	/**
	 * @return the spinnerFroQuantity
	 */
	public JSpinner getSpinnerFroQuantity() {
		return spinnerForQuantity;
	}

	/**
	 * @param spinnerFroQuantity the spinnerFroQuantity to set
	 */
	public void setSpinnerFroQuantity(JSpinner spinnerFroQuantity) {
		this.spinnerForQuantity = spinnerFroQuantity;
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * @return the textFieldForTotal
	 */
	public JTextField getTextFieldForTotal() {
		return textFieldForTotal;
	}

	/**
	 * @param textFieldForTotal the textFieldForTotal to set
	 */
	public void setTextFieldForTotal(JTextField textFieldForTotal) {
		this.textFieldForTotal = textFieldForTotal;
	}

	/**
	 * @return the textFieldForCreditCard
	 */
	public JTextField getTextFieldForCreditCard() {
		return textFieldForCreditCard;
	}

	/**
	 * @param textFieldForCreditCard the textFieldForCreditCard to set
	 */
	public void setTextFieldForCreditCard(JTextField textFieldForCreditCard) {
		this.textFieldForCreditCard = textFieldForCreditCard;
	}

	/**
	 * @return the textFieldForYear
	 */
	public JTextField getTextFieldForYear() {
		return textFieldForYear;
	}

	/**
	 * @param textFieldForYear the textFieldForYear to set
	 */
	public void setTextFieldForYear(JTextField textFieldForYear) {
		this.textFieldForYear = textFieldForYear;
	}

	/**
	 * @return the textFieldForCVV
	 */
	public JTextField getTextFieldForCVV() {
		return textFieldForCVV;
	}

	/**
	 * @param textFieldForCVV the textFieldForCVV to set
	 */
	public void setTextFieldForCVV(JTextField textFieldForCVV) {
		this.textFieldForCVV = textFieldForCVV;
	}

	/**
	 * @return the comboBoxForMode
	 */
	public JComboBox getComboBoxForMode() {
		return comboBoxForMode;
	}

	/**
	 * @param comboBoxForMode the comboBoxForMode to set
	 */
	public void setComboBoxForMode(JComboBox comboBoxForMode) {
		this.comboBoxForMode = comboBoxForMode;
	}

	/**
	 * @return the spinnerForMonth
	 */
	public JSpinner getSpinnerForMonth() {
		return spinnerForMonth;
	}

	/**
	 * @param spinnerForMonth the spinnerForMonth to set
	 */
	public void setSpinnerForMonth(JSpinner spinnerForMonth) {
		this.spinnerForMonth = spinnerForMonth;
	}

	/**
	 * @return the model
	 */
	public DefaultTableModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	/**
	 * This function resets the view to the initial state for new order
	 */
	public void resetView() {
		// reset table
		this.model = new DefaultTableModel(data, cols);
		getTable().setModel(model);
		getTextFieldForTotal().setText("");
		
		// reset fields
		getComboBoxForItem().setSelectedIndex(0);
		getSpinnerFroQuantity().setValue(1);
		getTextFieldForCreditCard().setText("");
		getSpinnerForMonth().setValue(1);
		getTextFieldForYear().setText("");
		getTextFieldForCVV().setText("");		
		
	}
}
