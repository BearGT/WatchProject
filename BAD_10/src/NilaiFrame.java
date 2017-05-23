import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.text.html.parser.DTD;

import util.MySQLConnector;

public class NilaiFrame extends JInternalFrame implements MouseListener{
	JPanel panelBawah = new JPanel();
	
	JPanel panelForm = new JPanel();
	JPanel panelSubmit = new JPanel();
	
	JLabel labelNIM = new JLabel("NIM");
	JLabel labelNama = new JLabel("Nama");
	JLabel labelNilai = new JLabel("Nilai");
	
	JTextField textNIM = new JTextField();
	JTextField textNama = new JTextField();
	JTextField textNilai= new JTextField();
	
	JButton buttonInsert = new JButton("Insert");
	JButton buttonUpdate = new JButton("Update");
	JButton buttonDelete = new JButton("Delete");
	
	Vector<Object> tHeader = new Vector<Object>();
	JTable tableData = new JTable(){ public boolean isCellEditable(int arg0, int arg1) {return false; };};
	JScrollPane scrollPane = new JScrollPane(tableData);
	
	DefaultTableModel tm;
	
	MySQLConnector con = new MySQLConnector();
	
	public void initData(){
		tHeader.add("SiswaID");
		tHeader.add("NIM");
		tHeader.add("Nama");
		tHeader.add("Nilai");
	}
	
	void fillData(){
		tm = new DefaultTableModel(tHeader, 0);
		String sql = "SELECT * from siswa";
		try{
			con.rs = con.st.executeQuery(sql);
			while(con.rs.next()){
				Vector<Object> tRow = new Vector<Object>();
				tRow.add(con.rs.getObject(1).toString());
				tRow.add(con.rs.getObject(2).toString());
				tRow.add(con.rs.getObject(3).toString());
				tRow.add(con.rs.getObject(4).toString());
				tm.addRow(tRow);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, "ada error");
		}
		
		tableData.setModel(tm);
	}
	
	public NilaiFrame() {
		// TODO Auto-generated constructor stub
		initData();
		fillData();
		
		tableData.getColumnModel().getColumn(0).setWidth(0);
		tableData.getColumnModel().getColumn(0).setMinWidth(0);
		tableData.getColumnModel().getColumn(0).setMaxWidth(0);
		
		panelSubmit.setLayout(new FlowLayout());;
		panelSubmit.add(buttonInsert);
		panelSubmit.add(buttonUpdate);
		panelSubmit.add(buttonDelete);
		
		//add your button here
		
		panelForm.setLayout(new GridLayout(3, 2));
		panelForm.setBorder(new EmptyBorder(50, 15, 15, 15));
		panelForm.add(labelNIM);
		panelForm.add(textNIM);
		panelForm.add(labelNama);
		panelForm.add(textNama);
		panelForm.add(labelNilai);
		panelForm.add(textNilai);
		
		panelBawah.setLayout(new BorderLayout());
		panelBawah.add(panelForm, BorderLayout.NORTH);
		panelBawah.add(panelSubmit, BorderLayout.SOUTH);
		
		this.setTitle("Management Nilai");
		this.setClosable(true);
		this.setLayout(new BorderLayout());
		this.add(panelBawah, BorderLayout.SOUTH);
		this.add(scrollPane, BorderLayout.NORTH);
		this.setSize(500,500);
		this.setLocation(1200/2-this.getWidth()/2, 800/2-this.getHeight()/2);
		
		//listener
		buttonInsert.addMouseListener(this);
		tableData.addMouseListener(this);
		
		buttonUpdate.addMouseListener(this);
		buttonDelete.addMouseListener(this);
	}
	
	public String generateID(){
		String query = "SELECT * from siswa ORDER BY siswaID DESC";
		String result = "";
		
		try {
			con.rs = con.st.executeQuery(query);
			if(!con.rs.next()){
				result = "US001";
			}else{
				String temp = con.rs.getObject(1).toString().substring(2,5);
				int newID = Integer.parseInt(temp)+1;
				result = "US"+String.format("%03d", newID);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent() == buttonInsert){
			String NIM = textNIM.getText();
			String Nama = textNama.getText();
			int Nilai = Integer.parseInt(textNilai.getText());
			String query = "INSERT into siswa (SiswaID, NIM, Nama, Nilai) VALUES ('"+generateID()+"','"+NIM+"', '"+Nama+"', '"+Nilai+"')";
			try{
				JOptionPane.showMessageDialog(null, query);
				con.st.executeUpdate(query);
				fillData();
			}catch(Exception ed){
				JOptionPane.showMessageDialog(null, "gagal insert");
			}
		}else if(e.getComponent()==tableData){
			if(tableData.getSelectedRow() != -1){//judul table selalu di -1, karena data input dimulai dari row 0
				String NIM = tableData.getValueAt(tableData.getSelectedRow(), 1).toString();
				String Nama = tableData.getValueAt(tableData.getSelectedRow(), 2).toString();
				String Nilai = tableData.getValueAt(tableData.getSelectedRow(), 3).toString();
				textNIM.setText(NIM);
				textNama.setText(Nama);
				textNilai.setText(Nilai);
			}
		}else if(e.getComponent() == buttonUpdate){
			if(tableData.getSelectedRow() != -1){
				String NIM = textNIM.getText();
				String Nama = textNama.getText();
				int Nilai = Integer.parseInt(textNilai.getText());
				
				String SiswaID = tableData.getValueAt(tableData.getSelectedRow(), 0).toString();
				
				String query = "UPDATE siswa SET NIM='"+NIM+"', Nama='"+Nama+"', Nilai='"+Nilai+"' WHERE SiswaID='"+SiswaID+"'";
				
				try{
					//JOptionPane.showMessageDialog(null, query);
					con.st.executeUpdate(query);
					fillData();
				}catch(Exception ed){
					//JOptionPane.showMessageDialog(null, "gagal update");
				}
			}
		}else if(e.getComponent()==buttonDelete){
			if(tableData.getSelectedRow() != -1){
				String SiswaID = tableData.getValueAt(tableData.getSelectedRow(), 0).toString();
				
				String query = "DELETE from siswa WHERE SiswaID='"+SiswaID+"'";
				try{
					//JOptionPane.showMessageDialog(null, query);
					con.st.executeUpdate(query);
					fillData();
				}catch(Exception ed){
					//JOptionPane.showMessageDialog(null, "gagal delete");
				}
			}
		}
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
