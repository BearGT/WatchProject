import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainFrame extends JFrame implements ActionListener{
	
	JMenuBar menuBar = new JMenuBar();
	JMenu menuUser = new JMenu("User");
	JMenu menuNilai = new JMenu("Nilai");
	
	JMenuItem menuLogin = new JMenuItem("Login");
	JMenuItem menuLogout = new JMenuItem("Logout");
	JMenuItem menuNilaiSiswa = new JMenuItem("Management Nilai");
	
	LoginFrame loginFrame = new LoginFrame(this);
	NilaiFrame nilaiFrame = new NilaiFrame();
	
	JDesktopPane desktopPane = new JDesktopPane();
	
	MainFrame(){
		
		menuLogin.addActionListener(this);
		menuLogout.addActionListener(this);
		menuNilaiSiswa.addActionListener(this);
		
		changeStateLogin(true);
		menuNilai.add(menuNilaiSiswa);
		menuUser.add(menuLogin);
		menuUser.add(menuLogout);
		menuBar.add(menuUser);
		menuBar.add(menuNilai);
		
		this.setContentPane(desktopPane);
		this.setJMenuBar(menuBar);
		this.setTitle("My Apps");
		this.setSize(1200,800);
		/*this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setExtendedState(MAXIMIZED_BOTH);*/
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void changeStateLogin(boolean state){
		menuLogin.setEnabled(state);
		menuLogout.setEnabled(!state);
		menuNilai.setEnabled(!state);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// TODO: handle exception
		}
		new MainFrame();
		for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
			System.out.println(info.getClassName());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == menuLogin){
			desktopPane.add(loginFrame);
			loginFrame.setVisible(true);
		}else if(e.getSource() == menuLogout){
			desktopPane.removeAll();
			this.changeStateLogin(true);
		}else if(e.getSource() == menuNilaiSiswa){
			desktopPane.add(nilaiFrame);
			nilaiFrame.setVisible(true);
		}
	}

}
