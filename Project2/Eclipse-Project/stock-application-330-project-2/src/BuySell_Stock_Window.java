import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuySell_Stock_Window extends JFrame {

	private JPanel contentPane;
	private Stock stock;
	private ButtonGroup buttonGroup;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuySell_Stock_Window frame = new BuySell_Stock_Window("", new User_Portfolio(), new Held_Stock(0), new DefaultTableModel(0, 0));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Double calculateGain(User_Portfolio USER, DefaultTableModel hold_dtm) {
		Double netGain = USER.getCurrentGain();
		
		for(int count = 0; count < hold_dtm.getRowCount(); count++) {
			Object gain = hold_dtm.getValueAt(count, 2);
			String str = gain.toString();
			netGain += Double.valueOf(str);
		}
		return netGain;
	}
	
	public BuySell_Stock_Window(String ticker, User_Portfolio USER, Held_Stock hs, DefaultTableModel hold_dtm) {
		setBounds(100, 100, 605, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		stock = new Stock(ticker);
		
		/* Setup title of the mini window */
		JLabel lblNewLabel = new JLabel("BUY/SELL " + ticker + " | " + stock.getName());
		lblNewLabel.setBounds(200, 10, 200, 25);
		contentPane.add(lblNewLabel);
		
		/* # of stocks label and spinner */
		JLabel numStocks_lb = new JLabel("# Of Stocks");
		numStocks_lb.setBounds(190, 200, 100, 15);
		contentPane.add(numStocks_lb);
		
		JSpinner stocks_spinner = new JSpinner();
		stocks_spinner.setBounds(350, 200, 45, 20);
		contentPane.add(stocks_spinner);
		/* End of # of stocks label and spinner */
		
		/* Buy and sell radio button */
		JRadioButton buy_rbtn = new JRadioButton("Buy");
		buy_rbtn.setBounds(190, 250, 100, 25);
		contentPane.add(buy_rbtn);
		
		JRadioButton sell_rbtn = new JRadioButton("Sell");
		sell_rbtn.setBounds(350, 250, 109, 23);
		contentPane.add(sell_rbtn);
		
		buttonGroup = new ButtonGroup(); // Allows the radio buttons to be mutually exclusive.
		buttonGroup.add(buy_rbtn);
		buttonGroup.add(sell_rbtn);
		/* End of the buy/sell radio buttons */
		
		/* Commit button setup and handler */
		JButton commit_btn = new JButton("Commit");
		commit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((Integer) stocks_spinner.getValue() <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "Invalid number of stocks.");
				} else if(buy_rbtn.isSelected()) {
					if((Integer) stocks_spinner.getValue() * stock.getCurrent() > USER.getBuyingPower()) {
						JOptionPane.showMessageDialog(getContentPane(), "Insufficient buying power.");
					} else {
						if(hs.findHeld(ticker) == -1) { // If user does not own any of that particular stock.
							hs.addStock(ticker, (Integer) stocks_spinner.getValue());
							USER.setBuyingPower(USER.getBuyingPower() - ((Integer) stocks_spinner.getValue() * stock.getCurrent()));
							USER.setStockValue(USER.getStockValue() + ((Integer) stocks_spinner.getValue() * stock.getCurrent()));
							int position = hs.findHeld(ticker);
							hold_dtm.addRow(new Object[] { ticker, hs.getStockValueAt(position), hs.getGainLoss(position), hs.getStocksBoughtAt(position) });
							BuySell_Stock_Window.this.dispose();
						} else {
							hs.addToStock(ticker, (Integer) stocks_spinner.getValue());
							USER.setBuyingPower(USER.getBuyingPower() - ((Integer) stocks_spinner.getValue() * stock.getCurrent()));
							USER.setStockValue(USER.getStockValue() + ((Integer) stocks_spinner.getValue() * stock.getCurrent()));
							int position = hs.findHeld(ticker);
							hold_dtm.setValueAt(hs.getStocksBoughtAt(position), position, 3);
							BuySell_Stock_Window.this.dispose();
						}
					}
				} else if (sell_rbtn.isSelected()) {
					if(hs.findHeld(ticker) == -1) {
						JOptionPane.showMessageDialog(getContentPane(), "You do not own any of that stock currently.");
					} else {
						int position = hs.findHeld(ticker);
						if((Integer) stocks_spinner.getValue() < hs.getStocksBoughtAt(position)) {
							hs.removeFromtStock(ticker, (Integer) stocks_spinner.getValue());
							USER.setBuyingPower(USER.getBuyingPower() + ((Integer) stocks_spinner.getValue() * stock.getCurrent()));
							USER.setStockValue(USER.getStockValue() - ((Integer) stocks_spinner.getValue() * stock.getCurrent()));
							hold_dtm.setValueAt(hs.getStocksBoughtAt(position), position, 3);
							BuySell_Stock_Window.this.dispose();
						} else {
							hs.removeStock(ticker, (Integer) stocks_spinner.getValue());
							USER.setBuyingPower(USER.getBuyingPower() + calculateGain(USER, hold_dtm) + ((Integer) stocks_spinner.getValue() * stock.getCurrent()));
							System.out.println("Current: " + USER.getStockValue() + " - " + ((Integer) stocks_spinner.getValue() + " * " + stock.getCurrent()));
							USER.setStockValue(USER.getStockValue() - ((Integer) stocks_spinner.getValue() * stock.getCurrent()));
							hold_dtm.removeRow(position);
							BuySell_Stock_Window.this.dispose();
						}
					}
				} else {
					// Does nothing.
				}
				
			}
		});
		commit_btn.setBounds(250, 325, 100, 25);
		contentPane.add(commit_btn);
		
		/* JPanel to contain strategy results */
		JPanel stratResult_panel = new JPanel();
		stratResult_panel.setBorder(new TitledBorder(null, "Strategy Results", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		stratResult_panel.setBounds(10, 90, 570, 100);
		contentPane.add(stratResult_panel);
		stratResult_panel.setLayout(null);
		
		JTextArea results_tf = new JTextArea();
		results_tf.setText("Click on one of the above buttons to see whether you should buy/sell/hold.");
		results_tf.setEditable(false);
		results_tf.setBounds(10, 15, 550, 75);
		stratResult_panel.add(results_tf);
		/* End of strategy results JPanel */
		
		
		/* ========== Different strategy method - button setup and listeners ========== */
		JButton oneStrat_btn = new JButton("One Strategy");
		oneStrat_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OneStrategy oneStrat = new OneStrategy(stock);
				String decision = "One Strategy: It is preferable to ";
				int choice = oneStrat.determineAction();
				if(choice == 0) { // Buy
					results_tf.setText(decision + "buy the stock.");
				} else if(choice == 1) { // Sell
					results_tf.setText(decision + "sell the stock (If you currently hold any).");					
				} else { // Hold
					results_tf.setText(decision + "hold the stock (If you currently hold any).");
				}
			}
		});
		oneStrat_btn.setBounds(10, 50, 135, 25);
		contentPane.add(oneStrat_btn);
		
		JButton altStrat_btn = new JButton("Alternate Strategy");
		altStrat_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltStrategy altStrat = new AltStrategy(stock);
				String decision = "Alternative Strategy: It is preferable to ";
				int choice = altStrat.determineAction();
				if(choice == 0) {
					results_tf.setText(decision + "buy the stock.");
				} else if (choice == 1) {
					results_tf.setText(decision + "sell the stock (If you currently hold any).");
				} else {
					results_tf.setText(decision + "hold the stock (If you currently hold any).");
				}
			}
		});
		altStrat_btn.setBounds(155, 50, 135, 25);
		contentPane.add(altStrat_btn);
		
		JButton ownStrat_btn = new JButton("Own Strategy");
		ownStrat_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OwnStrategy ownStrat = new OwnStrategy(stock);
				String decision = "Own Strategy: It is preferable to ";
				int choice = ownStrat.determineAction();
				if(choice == 0) {
					results_tf.setText(decision + "buy the stock.\nThe current 5 biggest losers are: " + ownStrat.getLosers() + "\nThe current 5 biggest gainers are: " + ownStrat.getGainers());				
				} else if (choice == 1) {
					results_tf.setText(decision + "sell the stock (If you currently hold any).\nThe current 5 biggest losers are: " + ownStrat.getLosers() + "\nThe current 5 biggest gainers are: " + ownStrat.getGainers());					
				} else {
					results_tf.setText(decision + "hold the stock (If you currently hold any).\nThe current 5 biggest losers are: " + ownStrat.getLosers() + "\nThe current 5 biggest gainers are: " + ownStrat.getGainers());
				}
			}
		});
		ownStrat_btn.setBounds(300, 50, 135, 25);
		contentPane.add(ownStrat_btn);
		
		JButton randStrat_btn = new JButton("Random Strategy");
		randStrat_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RandomStrategy randStrat = new RandomStrategy(stock);
				String decision = "Random Strategy: It is preferable to ";
				String randomRules = "\nBUY if symbol is 4 characters.\nSELL if time is between 1:00PM and 3:22PM (13:00 and 15:22).\nHOLD if both OR none are true.";
				int choice = randStrat.determineAction();
				if(choice == 0) {
					results_tf.setText(decision + "buy the stock." + randomRules);
				} else if (choice == 1) {
					results_tf.setText(decision + "sell the stock (If you currently hold any)." + randomRules);
				} else {
					results_tf.setText(decision + "hold the stock (If you currently hold any)." + randomRules);
				}				
			}
		});
		randStrat_btn.setBounds(445, 50, 135, 25);
		contentPane.add(randStrat_btn);
		/* ========== End of the different strategy buttons ========== */
		
	}
}
