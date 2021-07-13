package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.QuanLy_DAO;
import entity.QuanLy;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Component;

public class QuanLy_GUI extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JLabel label;
	private JLabel lblQL;
	private JButton btnDangXuat;
	private JPanel panel;
	private QuanLy quanLy;
	private int kt = 0;
	private JButton btnTroGiup;
	private int page = 0;
	/**
	 * Create the frame.
	 */
	public QuanLy_GUI(QuanLy ql) {
		
		ImageIcon img = new ImageIcon(QuanLy_GUI.class.getResource("/images/in-love.png"));
		setIconImage(img.getImage());
		
		setResizable(false);
		quanLy = new QuanLy_DAO().getQuanLy(ql.getMaQuanLy());
		setTitle("Chương Trình Quản Lí Lao Động");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1350, 750);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 101, 1324, 600);
		panel.add(tabbedPane);

		tabbedPane.addTab("Quản lý lao động", new ImageIcon(QuanLy_GUI.class.getResource("/images/lao_dong.png")),
				new QuanLiLaoDongJPanel(), "Quản lý lao động");
		tabbedPane.addTab("Quản lý công trình", new ImageIcon(QuanLy_GUI.class.getResource("/images/cong_trinh.png")),
				null, "Quản lý công trình");
		tabbedPane.addTab("Phân công lao động", new ImageIcon(QuanLy_GUI.class.getResource("/images/phan_cong.png")),
				null, "Phân công lao động");
		tabbedPane.addTab("Chuyển lao động", new ImageIcon(QuanLy_GUI.class.getResource("/images/chuyen.png")), null,
				"Chuyển lao động");
		tabbedPane.addTab("Lịch làm việc", new ImageIcon(QuanLy_GUI.class.getResource("/images/lich_lam.png")), null,
				"Lịch làm việc");
		tabbedPane.addTab("Thống kê", new ImageIcon(QuanLy_GUI.class.getResource("/images/thong_ke.png")), null, null);
		tabbedPane.addTab("Quản lý công việc", new ImageIcon(QuanLy_GUI.class.getResource("/images/job_1.png")), null,
				null);
		tabbedPane.setBackground(new Color(50, 168, 82));

		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(QuanLy_GUI.class.getResource("/images/account.png")));
		label.setBounds(0, 0, 108, 90);
		panel.add(label);

		lblQL = new JLabel(quanLy.getTenQuanLy());
		lblQL.setHorizontalAlignment(SwingConstants.LEFT);
		lblQL.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQL.setBounds(118, 11, 242, 26);
		panel.add(lblQL);

		btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int t = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất", "Thông báo",
						JOptionPane.YES_NO_OPTION);

				if (t == JOptionPane.YES_OPTION) {
					setVisible(false);
					new DangNhap().setVisible(true);
				}
			}
		});
		btnDangXuat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDangXuat.setBorderPainted(false);
		btnDangXuat.setFocusPainted(false);
		btnDangXuat.setBounds(118, 57, 108, 33);
		btnDangXuat.setBackground(new Color(66, 135, 245));

		panel.add(btnDangXuat);

		btnTroGiup = new JButton("Trợ giúp");
		btnTroGiup.setAlignmentX(Component.CENTER_ALIGNMENT);
			
		btnTroGiup.setMnemonic(KeyEvent.VK_A);
		btnTroGiup.setFocusPainted(false);
		btnTroGiup.setBorderPainted(false);
		btnTroGiup.setBackground(new Color(66, 135, 245));
		btnTroGiup.setBounds(241, 57, 108, 33);
		panel.add(btnTroGiup);
		
		Action troGiup = new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				new TroGiup(page).setVisible(true);
				
			}
		};
		
		
		btnTroGiup.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "Trợ giúp");
		btnTroGiup.getActionMap().put("Trợ giúp", troGiup);		
		
		btnTroGiup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new TroGiup(page).setVisible(true);
				
			}
		});
		
		tabbedPane.addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int index = tabbedPane.getSelectedIndex();
		page = index;
		if (index == 0 && kt != index)
			tabbedPane.insertTab("Quản lý lao động",
					new ImageIcon(QuanLy_GUI.class.getResource("/images/lao_dong.png")), new QuanLiLaoDongJPanel(),
					"Quản lý lao động", index + 1);
		else if (index == 1 && kt != index)
			tabbedPane.insertTab("Quản lý công trình",
					new ImageIcon(QuanLy_GUI.class.getResource("/images/cong_trinh.png")), new QuanLiCongTrinhJPanel(),
					"Quản lý công trình", index + 1);
		else if (index == 2 && kt != index)
			tabbedPane.insertTab("Phân công lao động",
					new ImageIcon(QuanLy_GUI.class.getResource("/images/phan_cong.png")), new PhanCong_JPN(this.quanLy),
					"Phân công lao động", index + 1);
		else if (index == 3 && kt != index)
			tabbedPane.insertTab("Chuyển lao động", new ImageIcon(QuanLy_GUI.class.getResource("/images/chuyen.png")),
					new ChuyenLaoDong_JPN(quanLy), "Chuyển lao động", index + 1);
		else if (index == 4 && kt != index)
			tabbedPane.insertTab("Lịch làm việc", new ImageIcon(QuanLy_GUI.class.getResource("/images/lich_lam.png")),
					new QLLichLamViec(), "Lịch làm việc", index + 1);
		else if (index == 5 && kt != index)
			tabbedPane.insertTab("Thống kê", new ImageIcon(QuanLy_GUI.class.getResource("/images/thong_ke.png")),
					new ThongKe_Jpanel(), "Thống kê", index + 1);
		else if (index == 6 && kt != index)
			tabbedPane.insertTab("Quản lý công việc", new ImageIcon(QuanLy_GUI.class.getResource("/images/job_1.png")),
					new QLCongViec_GUI(), "Quản lý công việc", index + 1);
		if (kt != index) {
			tabbedPane.remove(index);
			kt = index;
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


}
