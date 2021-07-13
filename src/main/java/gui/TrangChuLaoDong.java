package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.LaoDong;
import javax.swing.SwingConstants;

public class TrangChuLaoDong extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnThongTin;
	private JButton btnDoiMatKhau;
	private JButton btnDangXuat;
	private JLabel lblTaiKhoan;
	private JLabel lblChucVu;
	private LaoDong laoDong;
	private JButton btnLichLamViec;
	
	/**
	 * Create the frame.
	 */
	public TrangChuLaoDong(LaoDong ld) {
		laoDong = ld;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 521);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBounds(410, 235, 364, 245);
		pnlTacVu.setBackground(Color.WHITE);
		pnlTacVu.setBorder(BorderFactory.createTitledBorder("Tác Vụ"));
		contentPane.add(pnlTacVu);
		pnlTacVu.setLayout(null);

		btnThongTin = new JButton("Thông Tin Cá Nhân");
		btnThongTin.setHorizontalAlignment(SwingConstants.LEFT);
		btnThongTin.setFocusPainted(false);

		btnThongTin.setBounds(22, 24, 297, 49);
		(btnThongTin).setIcon(new ImageIcon(TrangChuLaoDong.class.getResource("/images/info.png")));
		btnThongTin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnThongTin.setBackground(new Color(176, 255, 177));
		pnlTacVu.add(btnThongTin);

		btnDoiMatKhau = new JButton(" Đổi mật khẩu");
		btnDoiMatKhau.setHorizontalAlignment(SwingConstants.LEFT);
		btnDoiMatKhau.setFocusPainted(false);
		btnDoiMatKhau.setBounds(22, 130, 297, 49);
		btnDoiMatKhau.setIcon(new ImageIcon(TrangChuLaoDong.class.getResource("/images/key.png")));
		btnDoiMatKhau.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnDoiMatKhau.setBackground(new Color(176, 255, 177));

		pnlTacVu.add(btnDoiMatKhau);

		btnDangXuat = new JButton("Đăng Xuất");
		btnDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
		btnDangXuat.setFocusPainted(false);
		btnDangXuat.setBounds(22, 185, 297, 49);
		btnDangXuat.setIcon(new ImageIcon(TrangChuLaoDong.class.getResource("/images/exit.png")));
		btnDangXuat.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnDangXuat.setBackground(new Color(176, 255, 177));

		pnlTacVu.add(btnDangXuat);
		
		btnLichLamViec = new JButton("Lịch làm việc");
		btnLichLamViec.setHorizontalAlignment(SwingConstants.LEFT);
		btnLichLamViec.setIcon(new ImageIcon(TrangChuLaoDong.class.getResource("/images/lich.png")));
		btnLichLamViec.setFocusPainted(false);
		btnLichLamViec.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnLichLamViec.setBackground(new Color(176, 255, 177));
		btnLichLamViec.setBounds(22, 78, 297, 49);
		pnlTacVu.add(btnLichLamViec);

		JLabel lblNewLabel = new JLabel("Công Ty Xây Dựng ABC");
		lblNewLabel.setBounds(185, 0, 437, 69);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TrangChuLaoDong.class.getResource("/images/phancong.jpg")));
		lblNewLabel_1.setBounds(23, 56, 278, 196);
		contentPane.add(lblNewLabel_1);

		JPanel pnlThongTin = new JPanel();
		pnlThongTin.setBackground(Color.WHITE);
		pnlThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin tài khoản"));
		pnlThongTin.setBounds(12, 277, 364, 129);
		contentPane.add(pnlThongTin);
		pnlThongTin.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Tài Khoản:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_2.setBounds(12, 25, 114, 28);
		pnlThongTin.add(lblNewLabel_2);

		lblTaiKhoan = new JLabel();
		lblTaiKhoan.setText(ld.getTenLaoDong());
		lblTaiKhoan.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblTaiKhoan.setBounds(150, 25, 230, 28);
		pnlThongTin.add(lblTaiKhoan);

		JLabel lblNewLabel_4 = new JLabel("Chức Vụ:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_4.setBounds(12, 66, 114, 28);
		pnlThongTin.add(lblNewLabel_4);

		lblChucVu = new JLabel("Lao động");
		lblChucVu.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblChucVu.setBounds(150, 66, 196, 28);
		pnlThongTin.add(lblChucVu);

		btnDangXuat.addActionListener(this);
		btnDoiMatKhau.addActionListener(this);
		btnThongTin.addActionListener(this);
		btnLichLamViec.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if (o.equals(btnThongTin)) {
			new ThongTinLaoDong(laoDong).setVisible(true);
		} else if (o.equals(btnDoiMatKhau)) {
			new DoiMatKhau_Jfm(laoDong).setVisible(true);
		} else if(o.equals(btnDangXuat)){
			int t=JOptionPane.showConfirmDialog(null, "Thông báo", "Bạn có muốn đăng xuất", JOptionPane.YES_NO_OPTION);
			if(t==JOptionPane.YES_OPTION) {
				setVisible(false);
				new DangNhap().setVisible(true);
			}
		}else {
			new LichLamViec_GUI(laoDong).setVisible(true);;
		}
	}
}
