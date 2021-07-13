package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.LaoDong;

import javax.swing.JTextArea;

public class ThongTinLaoDong extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel pnlThongTinLaoDong;
	private JLabel txtHoTen;
	private JLabel txtGioiTinh;
	private JLabel txtCMND;
	private JLabel txtSDT;
	private JLabel lblNewLabel_7;
	private JLabel txtTrinhDo;
	private JLabel txtNgaySinh;
	/**
	 * Create the frame.
	 */
	public ThongTinLaoDong(LaoDong laoDong) {
		setResizable(false);
		setTitle("Chương Trình Quản Lí Lao Động");
		setBounds(100, 100, 566, 599);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(247, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 1035, 645);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Thông tin lao động");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(99, 11, 352, 50);
		panel.add(lblNewLabel);

		pnlThongTinLaoDong = new JPanel();
		pnlThongTinLaoDong.setBackground(Color.WHITE);
		pnlThongTinLaoDong.setBounds(27, 73, 502, 483);
		panel.add(pnlThongTinLaoDong);
		pnlThongTinLaoDong.setBorder(BorderFactory.createTitledBorder("Thông Tin Lao Động"));
		pnlThongTinLaoDong.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Họ tên:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_1.setBounds(12, 39, 107, 26);
		pnlThongTinLaoDong.add(lblNewLabel_1);

		txtHoTen = new JLabel();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtHoTen.setBounds(131, 39, 326, 31);
		pnlThongTinLaoDong.add(txtHoTen);
		txtHoTen.setText(laoDong.getTenLaoDong());
		JLabel lblNewLabel_2 = new JLabel("Ngày sinh:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_2.setBounds(12, 101, 117, 26);
		pnlThongTinLaoDong.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Giới tính:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_3.setBounds(12, 155, 107, 31);
		pnlThongTinLaoDong.add(lblNewLabel_3);

		txtGioiTinh = new JLabel();
		txtGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtGioiTinh.setBounds(131, 155, 326, 31);
		pnlThongTinLaoDong.add(txtGioiTinh);
		txtGioiTinh.setText(laoDong.isGioiTinh() ? "Nam" : "Nữ");
		JLabel lblNewLabel_4 = new JLabel("Số CMND:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_4.setBounds(12, 216, 117, 31);
		pnlThongTinLaoDong.add(lblNewLabel_4);

		txtCMND = new JLabel();
		txtCMND.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtCMND.setBounds(131, 216, 326, 31);
		pnlThongTinLaoDong.add(txtCMND);
		txtCMND.setText(laoDong.getCMND());

		JLabel lblNewLabel_5 = new JLabel("SĐT:");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_5.setBounds(12, 326, 107, 35);
		pnlThongTinLaoDong.add(lblNewLabel_5);

		txtSDT = new JLabel();
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSDT.setBounds(131, 326, 326, 31);
		pnlThongTinLaoDong.add(txtSDT);
		txtSDT.setText(laoDong.getSDT());

		JLabel lblNewLabel_6 = new JLabel("Địa chỉ:");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_6.setBounds(12, 385, 107, 31);
		pnlThongTinLaoDong.add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("Trình độ:");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_7.setBounds(12, 269, 107, 31);
		pnlThongTinLaoDong.add(lblNewLabel_7);

		txtTrinhDo = new JLabel();
		txtTrinhDo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTrinhDo.setBounds(130, 271, 327, 31);
		pnlThongTinLaoDong.add(txtTrinhDo);
		txtTrinhDo.setText(laoDong.getTrinhDo().toString());

		JTextArea txtDiaChi = new JTextArea();
		txtDiaChi.setEditable(false);
		txtDiaChi.setLineWrap(true);
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		txtDiaChi.setBounds(130, 385, 327, 67);
		txtDiaChi.setText(laoDong.getDiaChi().toString());
		pnlThongTinLaoDong.add(txtDiaChi);

		txtNgaySinh = new JLabel();
		txtNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtNgaySinh.setBounds(131, 96, 326, 31);
		pnlThongTinLaoDong.add(txtNgaySinh);
		txtNgaySinh.setText(laoDong.getNgaySinh().toString());
		laoDong.getMaLaoDong();
	}

	
	
}
