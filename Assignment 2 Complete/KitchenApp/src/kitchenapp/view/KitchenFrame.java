package kitchenapp.view;

/**
 * This view shows the request sent to the kitchen application and display it
 * @author Group S
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.LayoutManager2;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

public class KitchenFrame extends JFrame {

	private JPanel panelDineIn, panelForAllDineInOrder, panelForDineInOrderID;
	private JPanel panelForDineInOrderList, panelForDineInButtonDone;
	private JPanel panelTakeAway, panelForAllTakeAwayOrder, panelForTakeAwayOrderID;
	private JPanel panelForTakeAwayOrderList, panelForTakeAwayButtonDone;
	private JScrollPane scrollPanelForDineIn, scrollPanelForDineInOrderList;
	private JScrollPane scrollPanelForTakeAway, scrollPanelForTakeAwayOrderList;
	private JLayeredPane panelForEachDineInOrder;
	private JLayeredPane panelForEachTakeAwayOrder;

	private JLabel lblDineIn, lblOrderIDForDineIn;
	private JLabel lblTakeAway, lblOrderIDForTakeAway;

	private JButton btnDineInOrderDone;
	private JButton btnTakeAwayOrderDone;

	private JTextField textFieldForDineIn;
	private JTable tableForDineInList;
	private JTextField textFieldForTakeAway;
	private JTable tableForTakeAwayList;

	public KitchenFrame() {

		// Title of Window
		setTitle("Kitchen Display");
		getContentPane().setLayout(null);
		setSize(new Dimension(1920, 1080));

		// Start part of Dine In
		panelDineIn = new JPanel();
		panelDineIn.setLayout(null);
		panelDineIn.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(153, 204, 255), null),
				"", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		panelDineIn.setBounds(10, 10, 1520, 26);
		getContentPane().add(panelDineIn);

		lblDineIn = new JLabel("Dine In");
		lblDineIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblDineIn.setFont(new Font("Serif", Font.BOLD, 20));
		lblDineIn.setBounds(10, 1, 1500, 23);
		panelDineIn.add(lblDineIn);

		scrollPanelForDineIn = new JScrollPane();
		scrollPanelForDineIn.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanelForDineIn.setBounds(10, 46, 1520, 350);
		getContentPane().add(scrollPanelForDineIn);

		panelForAllDineInOrder = new JPanel();
		scrollPanelForDineIn.setViewportView(panelForAllDineInOrder);
		panelForAllDineInOrder.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelForAllDineInOrder.setSize(1520, 350);
		// End part of Dine In

		// Start part of Take Away
		panelTakeAway = new JPanel();
		panelTakeAway.setLayout(null);
		panelTakeAway.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(153, 204, 255), null),
				"", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		panelTakeAway.setBounds(10, 406, 1520, 26);
		getContentPane().add(panelTakeAway);

		lblTakeAway = new JLabel("Take Away");
		lblTakeAway.setHorizontalAlignment(SwingConstants.CENTER);
		lblTakeAway.setFont(new Font("Serif", Font.BOLD, 20));
		lblTakeAway.setBounds(10, 1, 1500, 23);
		panelTakeAway.add(lblTakeAway);

		scrollPanelForTakeAway = new JScrollPane();
		scrollPanelForTakeAway.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanelForTakeAway.setBounds(10, 442, 1520, 350);
		getContentPane().add(scrollPanelForTakeAway);

		panelForAllTakeAwayOrder = new JPanel();
		panelForAllTakeAwayOrder.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelForAllTakeAwayOrder.setSize(1520, 350);
		scrollPanelForTakeAway.setViewportView(panelForAllTakeAwayOrder);
		// End part of Take Away

	}

	/**
	 * @return the panelForAllDineInOrder
	 */
	public JPanel getPanelForAllDineInOrder() {
		return panelForAllDineInOrder;
	}

	/**
	 * @param panelForAllDineInOrder the panelForAllDineInOrder to set
	 */
	public void setPanelForAllDineInOrder(JPanel panelForAllDineInOrder) {
		this.panelForAllDineInOrder = panelForAllDineInOrder;
	}

	/**
	 * @return the panelForAllTakeAwayOrder
	 */
	public JPanel getPanelForAllTakeAwayOrder() {
		return panelForAllTakeAwayOrder;
	}

	/**
	 * @param panelForAllTakeAwayOrder the panelForAllTakeAwayOrder to set
	 */
	public void setPanelForAllTakeAwayOrder(JPanel panelForAllTakeAwayOrder) {
		this.panelForAllTakeAwayOrder = panelForAllTakeAwayOrder;
	}

}
