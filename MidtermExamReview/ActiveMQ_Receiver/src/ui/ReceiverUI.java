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

import activemq.Receiver;
import activemq.XMLConvert;
import entity.Student;


public class ReceiverUI extends JFrame implements ActionListener{

	private JLabel lblMSSV, lblName, lblDate;
	private JTextField txtMSSV, txtName, txtDate;
	private JButton btnReceiver;
	Receiver receiver = new Receiver();

	public ReceiverUI() {
		super();
		// TODO Auto-generated constructor stub

		init();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(400,400);
		setVisible(true);
	}


	private void init() {
		createNorthContent();
		createSouthContent();
	}


	private void createNorthContent() {
		JPanel pNorth = new JPanel();
		add(pNorth, BorderLayout.NORTH);

		Box box = Box.createVerticalBox();
		Box b1, b2, b3;

		box.add(b1 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		b1.add(lblMSSV = new JLabel("Mã sinh viên: "));
		b1.add(txtMSSV = new JTextField(15));


		box.add(b2 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		b2.add(lblName = new JLabel("Tên sinh viên: "));
		b2.add(txtName = new JTextField(15));


		box.add(b3 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(15));
		b3.add(lblDate = new JLabel("Ngày sinh: "));
		b3.add(txtDate = new JTextField(15));
		
		lblMSSV.setPreferredSize(lblName.getPreferredSize());
		lblDate.setPreferredSize(lblName.getPreferredSize());
		
		pNorth.add(box);

	}

	private void createSouthContent() {
		JPanel pSouth = new JPanel();
		add(pSouth, BorderLayout.SOUTH);
		
		btnReceiver = new JButton("Receive");
		btnReceiver.addActionListener(this);
		
		pSouth.add(btnReceiver);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
		if (o.equals(btnReceiver)) {			
			try {
				Student student = new Student();
				student = new XMLConvert<Student>(student).xml2Object(receiver.Receiver());	
				if (student!=null) {
					txtMSSV.setText(student.getMssv());
					txtName.setText(student.getName());
					txtDate.setText(student.getDate());
					JOptionPane.showMessageDialog(this, "Thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(this, "Đối tượng rỗng", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			} catch (JMSException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		

	}
}
