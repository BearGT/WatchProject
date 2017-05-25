import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.MySQLConnector;

public class LoginForm extends JFrame implements ActionListener{
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	
	JLabel signInLabel = new JLabel("SIGN IN");
	
	JLabel usernameLabel = new JLabel("Username");
	JTextField usernameField = new JTextField();
	JLabel passwordLabel = new JLabel("Password");
	JPasswordField passwordField = new JPasswordField();
	
	JButton signInSubmit = new JButton("Sign In");
	JButton cancel = new JButton("Cancel");
	
	MySQLConnector con = new MySQLConnector();
	
	public LoginForm(){
		
		signInLabel.setFont(new Font("Arial", Font.BOLD, 18));
		
		p1.setLayout(new GridLayout(7, 1, 15, 15));
		p1.add(signInLabel);
		p1.add(usernameLabel);
		p1.add(usernameField);
		p1.add(passwordLabel);
		p1.add(passwordField);
		
		p2.setLayout(new GridLayout(2, 1, 15, 15));
		p2.add(signInSubmit);
		p2.add(cancel);
		
		this.setLayout(new BorderLayout());
		this.add(p1, BorderLayout.CENTER);
		this.add(p2, BorderLayout.SOUTH);
		
		signInSubmit.addActionListener(this);
		cancel.addActionListener(this);
		
		
		this.setTitle("Sign In");
		this.setSize(385,350);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == signInSubmit){
			String username = this.usernameField.getText();
			String password = this.passwordField.getText();
			
			if((username.length() > 0) && (password.length() > 0)){
				String query = "SELECT * from msuser where Username = '"+username+"' AND Password='"+password+"'";
				
				try{
					con.rs = con.st.executeQuery(query);
					if(con.rs.next()){
						JOptionPane.showMessageDialog(null, "Welcome, "+con.rs.getObject(2));
						this.dispose();
					}else{
						JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e1){
				}
			}	
		}else if(e.getSource() == cancel){
			this.dispose();
			super.setVisible(true);
		}
		
	}
	
}
