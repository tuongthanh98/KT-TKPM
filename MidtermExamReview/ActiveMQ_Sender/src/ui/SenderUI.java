package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jms.JMSException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import activemq.Sender;
import entity.Student;
import activemq.XMLConvert;

public class SenderUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblMSSV, lblName, lblDate;
	private JTextField txtMSSV, txtName, txtDate;
	private JButton btnSender;
	
	Sender sender = new Sender();
	
	
	public SenderUI() {
		// TODO Auto-generated constructor stub		
		
		init();
		
		setTitle("Sender Message Queue");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(400, 400);
		setVisible(true);
	}
	
	
	private void init() {
		createNorthContent();
		createSouthContent();
	}
	
	/**
	 * @author Thanh Tường
	 * */
	private void createNorthContent() {
		JPanel pNorth = new JPanel();
		add(pNorth,BorderLayout.NORTH);
		
		Box box = Box.createVerticalBox();
		Box b1, b2, b3;
		
		box.add(b1 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		b1.add(lblMSSV = new JLabel("Mã sinh viên: "));
		b1.add(txtMSSV = new JTextField(15));
		
		box.add(b2 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		b2.add(lblName = new JLabel("Họ tên sinh viên: "));
		b2.add(txtName = new JTextField(15));
		
		
		box.add(b3 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		b3.add(lblDate = new JLabel("Ngày sinh: "));
		b3.add(txtDate = new JTextField(15));
		
		lblMSSV.setPreferredSize(lblName.getPreferredSize());
		lblDate.setPreferredSize(lblName.getPreferredSize());
		
		pNorth.add(box);
		
	}
	
	private void createSouthContent() {
		JPanel pSouth = new JPanel();
		add(pSouth, BorderLayout.SOUTH);
		
		
		btnSender = new JButton("Send Message");
		btnSender.addActionListener(this);
		
		pSouth.add(btnSender);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnSender)) {	
			if (checkText()) {
				try {
					Student student = new Student();
					student.setMssv(txtMSSV.getText().toString().trim());
					student.setName(txtName.getText().toString());
					student.setDate(txtDate.getText().toString());
					
					String xml = new XMLConvert<Student>(student).object2XML(student);
					
					sender.senderMQ(xml);
					JOptionPane.showMessageDialog(this , "Đúng rồi! (^_^)", "Thành công",JOptionPane.INFORMATION_MESSAGE);
				} catch (JMSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this , e1, "Lỗi", JOptionPane.ERROR_MESSAGE);
				}catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(this , e2, "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		}	
	}
	
	private boolean checkText() {
		if (txtMSSV.getText().toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã số sinh viên!", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
			return false;
		}if (txtName.getText().toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sinh viên!", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
			return false;
		}if (txtDate.getText().toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sinh sinh viên!", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
