package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.QuanLyLaoDong_DAO;
import entity.LaoDong;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.UIManager;

public class ChiTietLaoDong extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel txtMaLaoDong;
	private JLabel txtHoTen;
	private JLabel txtNgaySinh;
	private JLabel txtGioiTinh;
	private JLabel txtSDT;
	private JLabel txtSoCMND;
	private JLabel txtTrinhDo;
	private JButton btnThoat;

	/**
	 * Create the frame.
	 */
	public ChiTietLaoDong(String maLaoDong) {

		LaoDong ld = new QuanLyLaoDong_DAO().getLaoDong(maLaoDong);
		setBounds(100, 100, 601, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Chi Tiết Lao Động");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblNewLabel.setBounds(176, 10, 306, 43);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã lao động:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 62, 119, 33);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Họ tên:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 110, 110, 43);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Ngày sinh:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(10, 163, 110, 33);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Giới tính:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_4.setBounds(307, 163, 95, 33);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("SĐT:");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_5.setBounds(10, 218, 110, 33);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Số CMND:");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_6.setBounds(307, 218, 95, 33);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Trình độ:");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_7.setBounds(10, 272, 110, 33);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Địa chỉ:");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_8.setBounds(10, 327, 110, 33);
		contentPane.add(lblNewLabel_8);

		txtMaLaoDong = new JLabel();
		txtMaLaoDong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaLaoDong.setText(ld.getMaLaoDong());
		txtMaLaoDong.setBounds(133, 63, 436, 33);
		contentPane.add(txtMaLaoDong);

		txtHoTen = new JLabel();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHoTen.setText(ld.getTenLaoDong());
		txtHoTen.setBounds(130, 112, 439, 33);
		contentPane.add(txtHoTen);

		txtNgaySinh = new JLabel();
		txtNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNgaySinh.setBounds(130, 165, 147, 33);
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		txtNgaySinh.setText(df.format(ld.getNgaySinh()));
		contentPane.add(txtNgaySinh);

		txtGioiTinh = new JLabel();
		txtGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtGioiTinh.setText(ld.isGioiTinh() ? "Nam" : "Nữ");
		txtGioiTinh.setBounds(412, 165, 157, 33);
		contentPane.add(txtGioiTinh);

		txtSDT = new JLabel();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setText(ld.getSDT());
		txtSDT.setBounds(130, 218, 147, 33);
		contentPane.add(txtSDT);

		txtSoCMND = new JLabel();
		txtSoCMND.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoCMND.setText(ld.getSDT());
		txtSoCMND.setBounds(415, 218, 154, 33);
		contentPane.add(txtSoCMND);

		txtTrinhDo = new JLabel();
		txtTrinhDo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTrinhDo.setText(ld.getTrinhDo().getTenTrinhDo());
		txtTrinhDo.setBounds(130, 272, 439, 33);
		contentPane.add(txtTrinhDo);

		JTextArea txtDiaChi = new JTextArea();
		txtDiaChi.setWrapStyleWord(true);
		txtDiaChi.setLineWrap(true);
		txtDiaChi.setBackground(UIManager.getColor("Button.background"));
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDiaChi.setEditable(false);
		txtDiaChi.setText(String.format("%s, %s, %s", ld.getDiaChi().getPhuongXa(), ld.getDiaChi().getPhuongXa(),
				ld.getDiaChi().getTinhTP()));
		txtDiaChi.setBounds(130, 333, 436, 79);
		contentPane.add(txtDiaChi);

		btnThoat = new JButton("Thoát");
		btnThoat.setFocusPainted(false);
		btnThoat.setIcon(null);
		btnThoat.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThoat.setBounds(476, 443, 93, 33);
		contentPane.add(btnThoat);
		btnThoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
