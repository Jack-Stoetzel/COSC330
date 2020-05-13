import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class Stock_view extends JFrame {

	/* Variables */
	private JPanel contentPane;
	private JPanel search_panel;
	private DefaultTableModel dtm = new DefaultTableModel(0, 0);
	private String[] column = { "Stock Name", "Symbol", "Value" }; 
	private Object[][] search_data = { { "A", "B", "C"} };
	private User_Portfolio USER = new User_Portfolio();
	private JTable table;
	private Boolean clear_flag = false;
	private JTextField searchtf;
	private JScrollPane scrollPane;
	private JTable stock_held_table;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock_view frame = new Stock_view();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void makeMenuItems(String search) {
		dtm.setRowCount(0); // Clears the stock search for the next search.
		String resultsString = "";
			try {
				URL searchUrl = new URL("https://financialmodelingprep.com/api/v3/search?query=" + search + "&limit=16");
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(searchUrl.openStream(), "UTF-8"))) {
					
					for (String line; (line = reader.readLine()) != null;)
						resultsString += line;
					
					 	JSONArray resultsJson = new JSONArray(resultsString);
		                for(int i = 0; i < resultsJson.length(); i++) {
		                	JSONObject info = (JSONObject)resultsJson.get(i);
		            		String ticker = info.getString("symbol");
		            		String messageJSONStr = "";

		            		try {
		            			URL profileUrl = new URL("https://financialmodelingprep.com/api/v3/company/profile/" + ticker);
		            			try (BufferedReader profileReader = new BufferedReader(new InputStreamReader(profileUrl.openStream(), "UTF-8"))) {
		            				for (String line; (line = profileReader.readLine()) != null;) {
		            					messageJSONStr += line;
		            				}

		            				JSONObject company = new JSONObject(messageJSONStr);
		            				// System.out.println(test.keySet());
		            				JSONObject profile = company.getJSONObject("profile");
		            				//System.out.println(profile.keySet());

		            				String item = company.getString("symbol") + "\t" + info.getString("name") + "\t"
		            						+ profile.getDouble("price");
		            				
		            				dtm.addRow(new Object[] { info.getString("name"), company.getString("symbol"), profile.getDouble("price") });
		            				
		            				scrollPane.setViewportView(table);
		            				System.out.println(item);
		            				
		            			} catch (Exception e) { System.out.println("ERROR 8998 - Failed to return BufferedReader"); }
		            			
		            		} catch (MalformedURLException e) { System.out.println("ERROR 404 - Bad URL"); }
		                } // End of the for-loop.
				} catch (Exception e) { System.out.println("ERROR 8998 - Failed to return BufferedReader"); }
				
			} catch (MalformedURLException e) { System.out.println("ERROR 404 - Bad URL"); }
	}

	/**
	 * Create the frame.
	 */
	
	public Stock_view() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 915);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/* ADD LOGO TO THE JFRAME */
		JLabel logo = new JLabel(new ImageIcon("C:\\Users\\Declan\\eclipse-workspace\\stock-application-330-project-2\\src\\img\\stonks.jpg")); // Needs to make a relative path.
		logo.setSize(1071, 300);
		logo.setLocation(10, 11);
		contentPane.add(logo);
		
		/* Add JPanel to hold search stock section */
		search_panel = new JPanel();
		search_panel.setBorder(new TitledBorder(null, "Search Stocks", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		search_panel.setBounds(10, 322, 585, 540);
		contentPane.add(search_panel);
		search_panel.setLayout(null);
		
		/* ScrollPane that surrounds the JTable */
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 566, 355);
		search_panel.add(scrollPane);
		
		/* Setup the table for adding rows */
		table = new JTable();
		scrollPane.setViewportView(table);
		dtm.setColumnIdentifiers(column);
		table.setRowHeight(30);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setModel(dtm);
		
		
		/* Add search text field */
		searchtf = new JTextField();
		searchtf.setBounds(10, 25, 240, 25);
		search_panel.add(searchtf);
		searchtf.setColumns(10);
		
		/* Search Icon "Button" */
		JLabel search_icon = new JLabel(new ImageIcon("C:\\Users\\Declan\\eclipse-workspace\\stock-application-330-project-2\\src\\img\\search_icon.png")); // Needs to be relative.
		search_icon.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) { makeMenuItems(searchtf.getText().toString()); } // Where the magic happens.

			@Override
			public void mouseClicked(MouseEvent e) { /* Sleep */ }

			@Override
			public void mousePressed(MouseEvent e) { /* Sleep */ }

			@Override
			public void mouseEntered(MouseEvent e) { /* Sleep */ }

			@Override
			public void mouseExited(MouseEvent e) { /* Sleep */ }
			
		});
		search_icon.setBounds(250, 25, 25, 25);
		search_panel.add(search_icon);
		
		/* Selection JPanel to contain the selection BUY/SELL Functions */
		JPanel selection_panel = new JPanel();
		selection_panel.setBounds(10, 424, 566, 105);
		search_panel.add(selection_panel);
		selection_panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selected Stock");
		lblNewLabel.setBounds(250, 10, 75, 15);
		selection_panel.add(lblNewLabel);
		
		JLabel stockName_header = new JLabel("Stock Name");
		stockName_header.setVerticalAlignment(SwingConstants.TOP);
		stockName_header.setBounds(25, 35, 150, 15);
		selection_panel.add(stockName_header);
		
		JLabel ss_name_lb = new JLabel("");
		ss_name_lb.setBounds(25, 50, 150, 15);
		selection_panel.add(ss_name_lb);
		
		JLabel symbol_header = new JLabel("Symbol");
		symbol_header.setBounds(185, 35, 75, 15);
		selection_panel.add(symbol_header);
		
		JLabel ss_symbol_lb = new JLabel("");
		ss_symbol_lb.setBounds(185, 50, 75, 15);
		selection_panel.add(ss_symbol_lb);
		
		JLabel value_header = new JLabel("Value");
		value_header.setBounds(260, 35, 75, 15);
		selection_panel.add(value_header);
		
		JButton btnNewButton = new JButton("Buy");
		btnNewButton.setBounds(375, 50, 75, 25);
		selection_panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sell");
		btnNewButton_1.setBounds(460, 50, 75, 25);
		selection_panel.add(btnNewButton_1);
		
		JLabel ss_value_lb = new JLabel("");
		ss_value_lb.setBounds(260, 50, 75, 15);
		selection_panel.add(ss_value_lb);
		
		ListSelectionModel rowSelectionModel = table.getSelectionModel();
		rowSelectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String sel_name = "", sel_symbol = "", sel_value = "";
				
				int[] selectedRow = table.getSelectedRows();
				int[] selectedColumn = { 0, 1, 2 };
				
				if(clear_flag == false) {
					sel_name = (String) table.getValueAt(selectedRow[0], selectedColumn[0]);
					sel_symbol = (String) table.getValueAt(selectedRow[0], selectedColumn[1]);
					sel_value = table.getValueAt(selectedRow[0], selectedColumn[2]).toString();
					
					ss_name_lb.setText(sel_name);
					ss_symbol_lb.setText(sel_symbol);
					ss_value_lb.setText(sel_value);
				}
				clear_flag = false;
			}
		});
		
		
		/* Clear button for stock search */
		JButton clear_btn = new JButton("Clear");
		clear_btn.addActionListener(new ActionListener() { // Clear the stock search engine.
			public void actionPerformed(ActionEvent e) {
				clear_flag = true;
				ss_name_lb.setText("");
				ss_symbol_lb.setText("");
				ss_value_lb.setText("");
				dtm.setRowCount(0);
			}
		});
		clear_btn.setBounds(475, 25, 100, 25);
		search_panel.add(clear_btn);
		
		/* JPanel to contain all user portfolio information */
		JPanel portfolio_panel = new JPanel();
		portfolio_panel.setBorder(new TitledBorder(null, "User Portfolio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		portfolio_panel.setBounds(595, 322, 486, 372);
		contentPane.add(portfolio_panel);
		portfolio_panel.setLayout(null);
		
		JPanel portfolio_subpanel = new JPanel();
		portfolio_subpanel.setBounds(161, 27, 315, 220);
		portfolio_panel.add(portfolio_subpanel);
		portfolio_subpanel.setLayout(null);
		
		JLabel portfolio_name_lb = new JLabel("Name:");
		portfolio_name_lb.setBounds(25, 25, 75, 15);
		portfolio_subpanel.add(portfolio_name_lb);
		
		JLabel total_value_lb = new JLabel("Total Value:");
		total_value_lb.setBounds(25, 50, 75, 15);
		portfolio_subpanel.add(total_value_lb);
		
		JLabel buying_power_lb = new JLabel("Buying Power");
		buying_power_lb.setBounds(25, 75, 75, 15);
		portfolio_subpanel.add(buying_power_lb);
		
		JLabel current_gain_lb = new JLabel("Current Gain");
		current_gain_lb.setBounds(25, 100, 75, 15);
		portfolio_subpanel.add(current_gain_lb);
		
		JLabel portfolio_name_tf = new JLabel(USER.getName());
		portfolio_name_tf.setBounds(225, 25, 50, 15);
		portfolio_subpanel.add(portfolio_name_tf);
		
		JLabel total_value_tf = new JLabel("$" + USER.getTotalValue().toString());
		total_value_tf.setBounds(225, 50, 50, 15);
		portfolio_subpanel.add(total_value_tf);
		
		JLabel buying_power_tf = new JLabel("$" + USER.getBuyingPower().toString());
		buying_power_tf.setBounds(225, 75, 50, 15);
		portfolio_subpanel.add(buying_power_tf);
		
		JLabel current_gain_tf = new JLabel("$" + USER.getCurrentGain().toString());
		current_gain_tf.setBounds(225, 100, 50, 15);
		portfolio_subpanel.add(current_gain_tf);
		
		JButton withdraw_btn = new JButton("Withdraw Funds");
		withdraw_btn.addActionListener(new ActionListener() { // Action listener to remove funds.
			public void actionPerformed(ActionEvent e) {
				Double withdrawAmount = 0.00;
				if(USER.getBuyingPower() == 0.00) {
					JOptionPane.showMessageDialog(getContentPane(), "Insufficient buying power to withdraw!");
				} else {
					withdrawAmount = -100.00;
					while(USER.getBuyingPower() < withdrawAmount || withdrawAmount == -100) {
						withdrawAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter how much money you would like to withdraw:"));
						if(withdrawAmount > USER.getBuyingPower())
							JOptionPane.showMessageDialog(getContentPane(), "You cannot withdraw more funds than you have available!");
					}
					USER.setBuyingPower(USER.getBuyingPower() - withdrawAmount);
					buying_power_tf.setText("$" + USER.getBuyingPower().toString());
				}
				
			}
		});
		withdraw_btn.setBounds(326, 303, 150, 50);
		portfolio_panel.add(withdraw_btn);
		
		JButton increase_bp_btn = new JButton("Increase Buying Power");
		increase_bp_btn.addActionListener(new ActionListener() { // Action listener to add funds.
			public void actionPerformed(ActionEvent e) {
				int returnedDeposit = -25;
				while(returnedDeposit < 0)
					returnedDeposit = Integer.parseInt(JOptionPane.showInputDialog("Enter how much money you wish to add:"));
				USER.setBuyingPower(USER.getBuyingPower() + returnedDeposit);
				buying_power_tf.setText("$" + USER.getBuyingPower().toString());
			}
		});
		increase_bp_btn.setBounds(161, 303, 150, 50);
		portfolio_panel.add(increase_bp_btn);
		/* End of portfolio JPanel content */
		
		
		
		/* JPanel to contain currently held stock */
		JPanel stock_held_panel = new JPanel();
		stock_held_panel.setBorder(new TitledBorder(null, "Current Stocks Held", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		stock_held_panel.setBounds(595, 695, 486, 170);
		contentPane.add(stock_held_panel);
		stock_held_panel.setLayout(null);
		
		stock_held_table = new JTable();
		stock_held_table.setBounds(6, 16, 470, 143);
		stock_held_panel.add(stock_held_table);
		
	}
}
