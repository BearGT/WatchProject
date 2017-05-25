import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.MySQLConnector;

public class RegisterForm extends JFrame implements MouseListener{
	JLabel signUpLabel = new JLabel("SIGN UP");
	
	JLabel userIDLabel = new JLabel("User ID");
	JTextField userIDField = new JTextField();
	
	JLabel usernameLabel = new JLabel("Username");
	JTextField usernameField = new JTextField();
	
	JLabel passwordLabel = new JLabel("Password");
	JPasswordField passwordField = new JPasswordField();
	
	JLabel emailLabel = new JLabel("Email");
	JTextField emailField = new JTextField();
	
	JLabel genderLabel = new JLabel("Gender");
	JRadioButton genderRadioButton = new JRadioButton();
	
	JLabel addressLabel = new JLabel("Email");
	JTextArea addressArea = new JTextArea();
	
	JButton submit = new JButton("Submit");
	JButton cancel = new JButton("Cancel");
	
	MySQLConnector con = new MySQLConnector();
	
	public RegisterForm(){
		userIDField.setEditable(false);
		
		
		this.setTitle("Sign Up");
		this.setSize(385,450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
