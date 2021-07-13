package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DangNhap_DAO;
import dao.QuanLyLaoDong_DAO;
import entity.LaoDong;
import entity.QuanLy;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;

import javax.swing.ImageIcon;

public class DangNhap extends JFrame implements MouseListener, ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField txtPass;
	private JTextField txtUsername;
	private JButton btnThoat;
	private JButton btnDangNhap;
	private JLabel lblHienMatKhau;
	private JLabel lblAnMK;

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					DangNhap frame = new DangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public DangNhap() {
		
		ImageIcon img = new ImageIcon(DangNhap.class.getResource("/images/in-love.png"));
		setIconImage(img.getImage());
		
		setTitle("Đăng nhập");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Tài khoản:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsername.setBounds(28, 53, 109, 31);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Mật khẩu:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(29, 125, 108, 27);
		contentPane.add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setBounds(166, 53, 218, 31);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setFocusPainted(false);
		btnDangNhap.setIcon(new ImageIcon(DangNhap.class.getResource("/images/baseline_exit_to_app_white_36dp.png")));
		btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 14));

		
		btnDangNhap.setBackground(new Color(233, 150, 122));
		btnDangNhap.setBounds(261, 183, 155, 40);
		contentPane.add(btnDangNhap);

		btnThoat = new JButton("Thoát");
		btnThoat.setFocusPainted(false);
		btnThoat.setIcon(new ImageIcon(DangNhap.class.getResource("/images/baseline_close_white_24dp.png")));
		btnThoat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnThoat.setBackground(new Color(233, 150, 122));
		btnThoat.setBounds(61, 183, 123, 40);
		contentPane.add(btnThoat);
		btnDangNhap.addActionListener(this);
		txtUsername.addKeyListener(this);

		txtUsername.setText("QL000000");

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(166, 125, 218, 31);
		contentPane.add(panel);
		panel.setLayout(null);

		txtPass = new JPasswordField();
		txtPass.setBounds(0, 0, 178, 31);
		panel.add(txtPass);
		txtPass.setBorder(null);
		txtPass.setForeground(Color.BLACK);
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtPass.setEchoChar('*');
		txtPass.addKeyListener(this);
		txtPass.setText("12345678");

		lblAnMK = new JLabel("");
		lblAnMK.setBounds(177, 0, 41, 31);
		panel.add(lblAnMK);
		lblAnMK.setIcon(new ImageIcon(
				DangNhap.class.getResource("/images/z2209341349604_375ea1adb8f1d08c09b5ff540b0ed23f.jpg")));
		lblHienMatKhau = new JLabel("");
		lblHienMatKhau.setBounds(177, 0, 41, 31);
		panel.add(lblHienMatKhau);
		lblHienMatKhau.setIcon(new ImageIcon(
				DangNhap.class.getResource("/images/z2209341346369_0457443f3293444cef35b38f1f35144b.jpg")));

		lblHienMatKhau.addMouseListener(this);
		lblAnMK.setVisible(false);
		lblAnMK.addMouseListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		String ma = txtUsername.getText();
		String matKhau = new String(txtPass.getPassword());
		DangNhap_DAO dangNhap = new DangNhap_DAO();
		if (o.equals(btnDangNhap)) {
			new Thread(()-> {
				if (dangNhap.ktLaoDong(ma, matKhau)) {
					QuanLyLaoDong_DAO lDao = new QuanLyLaoDong_DAO();
					String maML = txtUsername.getText();
					LaoDong laoDong = lDao.getLaoDong(maML);
					String ten = laoDong.getTenLaoDong();
					Date ngaySinh = laoDong.getNgaySinh();
					Boolean gioiTinh = laoDong.isGioiTinh();
					String CMND = laoDong.getCMND();

					String sdt = laoDong.getSDT();
					setVisible(false);
					new TrangChuLaoDong(new LaoDong(laoDong.getMaLaoDong(), ten, ngaySinh, gioiTinh, CMND,
							laoDong.getDiaChi(), laoDong.getTrinhDo(), sdt, laoDong.getMatKhau())).setVisible(true);

				} else if (dangNhap.ktQuanLy(ma, matKhau)) {
					setVisible(false);
					new QuanLy_GUI(new QuanLy(ma)).setVisible(true);
				} else
					JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không chính xác");
			}).start();
			
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(lblHienMatKhau)) {
			txtPass.setEchoChar((char) 0);
			lblHienMatKhau.setVisible(false);
			lblAnMK.setVisible(true);
		} else if (o.equals(lblAnMK)) {
			txtPass.setEchoChar('*');
			lblAnMK.setVisible(false);
			lblHienMatKhau.setVisible(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object o = e.getSource();
		String ma = txtUsername.getText();
		String matKhau = new String(txtPass.getPassword());
		DangNhap_DAO dangNhap = new DangNhap_DAO();
		if (o.equals(txtUsername)) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				txtPass.requestFocus();
			}
		}
		if (o.equals(txtPass)) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				if (valid()) {
					if (dangNhap.ktLaoDong(ma, matKhau)) {
						QuanLyLaoDong_DAO lDao = new QuanLyLaoDong_DAO();
						String maML = txtUsername.getText();
						LaoDong laoDong = lDao.getLaoDong(maML);
						String ten = laoDong.getTenLaoDong();
						Date ngaySinh = laoDong.getNgaySinh();
						Boolean gioiTinh = laoDong.isGioiTinh();
						String CMND = laoDong.getCMND();
						String sdt = laoDong.getSDT();
						setVisible(false);
						new TrangChuLaoDong(new LaoDong(laoDong.getMaLaoDong(), ten, ngaySinh, gioiTinh, CMND,
								laoDong.getDiaChi(), laoDong.getTrinhDo(), sdt, laoDong.getMatKhau())).setVisible(true);

					} else if (dangNhap.ktQuanLy(ma, matKhau)) {
						setVisible(false);
						new QuanLy_GUI(new QuanLy(ma)).setVisible(true);
					} else
						JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không chính xác");
				}
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public Boolean valid() {
		String taiKhoan = txtUsername.getText().trim();
		if (taiKhoan.equals("")) {
			JOptionPane.showMessageDialog(this, "Tài khoản không được để trống");
			return false;
		} else if (new String(txtPass.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập mật khẩu");
			return false;
		}
		return true;
	}
}
