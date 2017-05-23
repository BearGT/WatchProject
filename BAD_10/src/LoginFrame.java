import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import util.MySQLConnector;

public class LoginFrame extends JInternalFrame implements ActionListener{
	
	JPanel loginPanel = new JPanel();
	JPanel titlePanel = new JPanel();
	JPanel submitPanel = new JPanel();
	
	JLabel loginTitle = new JLabel("Login User");
	
	JLabel usernameLabel = new JLabel("Username");
	JLabel passwordLabel = new JLabel("Password");
	
	JTextField username = new JTextField();
	JPasswordField password = new JPasswordField();
	
	JButton submitButton = new JButton("Login");
	
	MySQLConnector con = new MySQLConnector();
	
	MainFrame mainFrame;
	
	public LoginFrame(MainFrame mainFrame) {
		super("Login Form", true, true);
		this.mainFrame = mainFrame;
		this.setLayout(new BorderLayout());
		
		loginTitle.setFont(new Font("Arial", Font.BOLD, 18));
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(loginTitle);
		
		this.add(titlePanel, BorderLayout.NORTH);
		
		loginPanel.setLayout(new GridLayout(2, 2));
		loginPanel.add(usernameLabel);
		loginPanel.add(username);
		loginPanel.add(passwordLabel);
		loginPanel.add(password);
		loginPanel.setBorder(new EmptyBorder(20,20,20,20));
		this.add(loginPanel, BorderLayout.CENTER);
		
		submitButton.addActionListener(this);
		submitPanel.add(submitButton);
		this.add(submitPanel, BorderLayout.SOUTH);
		this.setSize(400,200);
		this.setLocation(1200/2-this.getWidth()/2, 800/2-this.getHeight()/2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String username = this.username.getText();
		String password = this.password.getText();
		
		String sql = "SELECT * from user where username = '"+username+"' AND password='"+password+"'";
		
		try{
			con.rs = con.st.executeQuery(sql);
			if(con.rs.next()){
				JOptionPane.showMessageDialog(null, "Welcome, "+con.rs.getObject(2));
				this.mainFrame.changeStateLogin(false);
				
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "Gagal", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception e1){
			
		}
	}
}
