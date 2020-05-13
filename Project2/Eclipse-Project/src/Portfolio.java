import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSplitPane;
import java.awt.Window.Type;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;

import org.json.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Portfolio extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Portfolio frame = new Portfolio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

//		makeMenuItems("AA");
	}

	/**
	 * Create the frame.
	 */
	public Portfolio() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Portfolio");
		lblNewLabel.setBounds(5, 5, 440, 30);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(15, 94, 215, 184);
		contentPane.add(panel);
															
		JMenuItem mntmNewMenuItem = new JMenuItem("12345 	12345678	12,456.89");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("HELLO WORLD");
			}
		});
		panel.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("IBM");
		panel.add(mntmNewMenuItem_1);
		
		textField = new JTextField();
		textField.setToolTipText("Search Stocks");
		textField.setBounds(15, 47, 150, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				makeMenuItems(textField.getText().toString());
			}
		});
		btnNewButton.setBounds(167, 47, 63, 35);
		contentPane.add(btnNewButton);

	}


}
//CPAAW	Conyers Park II Acquisition Corp.	1.45
//				 1         2         3         4
//12345 	1234567890123456789012345678901234567890	12,456.89