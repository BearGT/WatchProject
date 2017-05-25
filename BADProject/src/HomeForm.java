import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;



public class HomeForm extends JFrame implements ActionListener {
	
	JPanel p1 = new JPanel();
	ImageIcon logo = new ImageIcon("Image/logoAG.jpg");
	
	Image image = logo.getImage();
	Image newimg = image.getScaledInstance(420, 250,  Image.SCALE_SMOOTH);
	ImageIcon newLogo = new ImageIcon(newimg);
	
	JLabel Ag = new JLabel("",newLogo,SwingConstants.CENTER);
	JButton signIn = new JButton("Sign In");
	JButton signUp = new JButton("Sign Up");
	
	LoginForm loginForm = new LoginForm();
	RegisterForm registerForm = new RegisterForm();
	
	HomeForm(){
		
		
		this.setLayout(new BorderLayout());
		
		p1.setLayout(new GridLayout(2,1,8,8));
		p1.add(signIn);
		p1.add(signUp);
		this.add(Ag, BorderLayout.NORTH);
		this.add(p1, BorderLayout.SOUTH);
		
		signIn.addActionListener(this);
		signUp.addActionListener(this);
		
		this.setTitle("AGemo Watch Shop");
		this.setSize(420,330);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		new HomeForm();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == signIn){
			this.dispose();
			loginForm.setVisible(true);
			
		}else if(e.getSource() == signUp){
			this.dispose();
			registerForm.setVisible(true);
		}
	}

}
