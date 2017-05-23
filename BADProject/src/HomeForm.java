import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class HomeForm extends JFrame implements ActionListener {
	
	JPanel p1 = new JPanel();
	ImageIcon logo = new ImageIcon("Image/logoAG.jpg");
	
	//ImageIcon imageIcon = new ImageIcon("./img/imageName.png"); // load the image to a imageIcon
	Image image = logo.getImage(); // transform it 
	Image newimg = image.getScaledInstance(420, 250,  Image.SCALE_SMOOTH); // scale it the smooth way  
	ImageIcon newLogo = new ImageIcon(newimg);  // transform it back
	
	JLabel Ag = new JLabel("",newLogo,SwingConstants.CENTER);
	JButton signIn = new JButton("Sign In");
	JButton signUp = new JButton("Sign Up");
	
	//JDesktopPane desktopPane = new JDesktopPane();
	
	HomeForm(){
		
		
		this.setLayout(new BorderLayout());
		
		p1.setLayout(new GridLayout(2,1,8,8));
		p1.add(signIn);
		p1.add(signUp);
		this.add(Ag, BorderLayout.NORTH);
		this.add(p1, BorderLayout.SOUTH);
		
		this.setTitle("AGemo Watch Shop");
		//this.setContentPane(desktopPane);
		this.setSize(420,330);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public static void main(String[] args) {
		new HomeForm();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
