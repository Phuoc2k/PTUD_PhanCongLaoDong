package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.QuanLyLaoDong_DAO;
import entity.LaoDong;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;

public class DoiMatKhau_Jfm extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnDoiMatKhau;
	private LaoDong laoDong;
	private JPasswordField txtMatKhauMoi;
	private JPasswordField txtXacNhanMatKhau;

	/**
	 * Create the frame.
	 */
	public DoiMatKhau_Jfm(LaoDong laoDong) {
		this.laoDong = laoDong;
		setTitle("Đổi mật khẩu");
		setBounds(100, 100, 453, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel_1 = new JLabel("Mật khẩu mới:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblNewLabel_1.setBounds(10, 30, 170, 25);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nhập lại mật khẩu:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblNewLabel_2.setBounds(10, 87, 186, 25);
		contentPane.add(lblNewLabel_2);

		btnDoiMatKhau = new JButton("Đổi");
		btnDoiMatKhau.setBackground(Color.GRAY);
		btnDoiMatKhau.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDoiMatKhau.setBounds(10, 139, 119, 39);
		contentPane.add(btnDoiMatKhau);

		txtMatKhauMoi = new JPasswordField();
		txtMatKhauMoi.setBounds(190, 22, 229, 34);
		contentPane.add(txtMatKhauMoi);

		txtXacNhanMatKhau = new JPasswordField();
		txtXacNhanMatKhau.setBounds(189, 80, 229, 32);
		contentPane.add(txtXacNhanMatKhau);
		txtMatKhauMoi.setEchoChar('*');
		txtXacNhanMatKhau.setEchoChar('*');

		btnDoiMatKhau.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		QuanLyLaoDong_DAO lDao = new QuanLyLaoDong_DAO();
		String matKhauMoi = new String(txtMatKhauMoi.getPassword());
		String xacNhanMK = new String(txtXacNhanMatKhau.getPassword());
		String ma = laoDong.getMaLaoDong();
		if (o.equals(btnDoiMatKhau)) {
			if (matKhauMoi.length() < 8) {
				JOptionPane.showMessageDialog(null, "Mật khẩu quá ngắn, tối thiểu 8 ký tự");
				txtMatKhauMoi.selectAll();
				txtMatKhauMoi.requestFocus();
				return;
			}
			if (matKhauMoi.equals(xacNhanMK)) {
				int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn đổi", "Thông báo", JOptionPane.YES_NO_OPTION);
				if (kt==JOptionPane.YES_OPTION) {
					lDao.doiMatKhau(matKhauMoi, ma);
					JOptionPane.showMessageDialog(null, "Đổi thành công");
					txtMatKhauMoi.setText("");
					txtXacNhanMatKhau.setText("");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Mật khẩu không khớp");
				return;
			}
		}

	}
}
