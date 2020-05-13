import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	
	private JTextField username_tf;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	
	public Login() { // Constructor for the login JFrame
		
		setBounds(100, 100, 330, 225); // Sets size of the login window.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets the on-exit function.
		getContentPane().setLayout(null); // Sets layout.
		
		/* Create and setup the labels */
		JLabel username_label = new JLabel("Username:");
		username_label.setBounds(50, 50, 75, 20);
		getContentPane().add(username_label);
		
		JLabel password_label = new JLabel("Password");
		password_label.setBounds(50, 100, 75, 20);
		getContentPane().add(password_label);
		
		/* Create and setup the text fields */
		username_tf = new JTextField();
		username_tf.setBounds(150, 50, 100, 20);
		getContentPane().add(username_tf);
		username_tf.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(150, 100, 100, 20);
		getContentPane().add(password);
		
		/* Creates and defines the login button */
		JButton login_btn = new JButton("Login");
		login_btn.addActionListener(new ActionListener() { // Login button
			public void actionPerformed(ActionEvent e) {
				if(username_tf.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
					JOptionPane.showMessageDialog(getContentPane(), "Login Successful.");
					Login.this.dispose();
					Stock_view stock = new Stock_view();
					stock.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Bad Login Credentials.");
				}
			}
		});
		login_btn.setBounds(100, 145, 100, 20);
		getContentPane().add(login_btn);

	}
}
