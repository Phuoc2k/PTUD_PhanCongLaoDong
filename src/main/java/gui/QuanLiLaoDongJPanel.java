package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import dao.DiaDiem_DAO;
import dao.QuanLyLaoDong_DAO;
import dao.TrinhDo_DAO;
import entity.DiaDiem;
import entity.LaoDong;
import entity.TrinhDo;

import com.toedter.calendar.JDateChooser;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class QuanLiLaoDongJPanel extends JPanel implements ActionListener, MouseListener, ItemListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTenLD;
	private JTextField txtSdt;
	private JComboBox<String> cboTrinhDo;
	private JTextField txtCMND;
	private DefaultTableModel modelLaoDong;
	private JTable tableLaoDong;
	private JComboBox<String> cboGioiTinh;
	private JLabel lblPage;
	private JTextField txtMatKhau;
	private JComboBox<String> cboQuanHuyen;
	private JComboBox<String> cboPhuongXa;
	private JComboBox<String> cboTinhTP;
	private ArrayList<String> listTinh = new DiaDiem_DAO().getTinh();
	private QuanLyLaoDong_DAO lDao = new QuanLyLaoDong_DAO();
	private ArrayList<LaoDong> listLaoDong;
	private JTextField txtTimMa;
	private JTextField txtTimTenLD;
	private JDateChooser ngaySinh;
	private int soDau = 1;
	private int soCuoi = 7;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnXoaTrang;

	/**
	 * Create the panel.
	 */
	public QuanLiLaoDongJPanel() {

		setBackground(Color.WHITE);

		JLabel lblHoTenNV = new JLabel("H??? T??n:");
		lblHoTenNV.setBounds(33, 11, 106, 30);
		lblHoTenNV.setFont(new Font("Times New Roman", Font.BOLD, 16));

		JLabel lblSdt = new JLabel("S??? ??i???n tho???i :");
		lblSdt.setBounds(33, 52, 158, 30);
		lblSdt.setFont(new Font("Times New Roman", Font.BOLD, 16));

		cboTinhTP = new JComboBox<String>();
		cboTinhTP.setBounds(168, 98, 215, 22);
		cboTinhTP.addItem("T???nh/Th??nh ph???");
		for (String t : listTinh) {
			cboTinhTP.addItem(t);
		}

		cboQuanHuyen = new JComboBox<String>();
		cboQuanHuyen.setBounds(417, 98, 215, 22);
		cboQuanHuyen.addItem("Qu???n/Huy???n");

		cboPhuongXa = new JComboBox<String>();
		cboPhuongXa.setBounds(659, 98, 150, 22);
		cboPhuongXa.addItem("Ph?????ng/X??");

		txtTenLD = new JTextField();
		txtTenLD.setBounds(168, 15, 314, 22);
		txtTenLD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenLD.setColumns(10);

		txtSdt = new JTextField();
		txtSdt.setBounds(168, 56, 199, 22);
		txtSdt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSdt.setColumns(10);

		JLabel lblMatKhau = new JLabel("M???t kh???u:");
		lblMatKhau.setBounds(888, 90, 116, 30);
		lblMatKhau.setFont(new Font("Times New Roman", Font.BOLD, 18));

		txtMatKhau = new JTextField();
		txtMatKhau.setBounds(1004, 93, 199, 22);
		txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMatKhau.setColumns(10);

		JLabel lblNgaySinh = new JLabel("Ng??y sinh :");
		lblNgaySinh.setBounds(492, 52, 116, 30);
		lblNgaySinh.setFont(new Font("Times New Roman", Font.BOLD, 16));

		cboTrinhDo = new JComboBox<String>();
		cboTrinhDo.setBounds(1004, 57, 199, 22);

		cboTrinhDo.setFont(new Font("Tahoma", Font.PLAIN, 15));

		ArrayList<TrinhDo> trinhDo = new TrinhDo_DAO().getTrinhDo();
		for (TrinhDo t : trinhDo) {
			cboTrinhDo.addItem(t.getTenTrinhDo());
		}

		JLabel lblNewLabel = new JLabel("Tr??nh ?????");
		lblNewLabel.setBounds(888, 52, 116, 30);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));

		JLabel lblNewLabel_2 = new JLabel("S??? CMND:");
		lblNewLabel_2.setBounds(492, 11, 140, 30);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 16));

		txtCMND = new JTextField();
		txtCMND.setBounds(610, 15, 199, 22);
		txtCMND.setColumns(10);

		String[] colHeader = { "M?? lao ?????ng", "H??? T??n", "Ng??y sinh", "Gi???i T??nh", "S??? ??i???n tho???i", "S??? CMND", "?????a ch???",
				"Tr??nh ?????" };
		modelLaoDong = new DefaultTableModel(colHeader, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableLaoDong = new JTable(modelLaoDong);
		tableLaoDong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(tableLaoDong);
		scrollPane.setBounds(33, 238, 1255, 277);

		JTableHeader h = tableLaoDong.getTableHeader();
		h.setForeground(new Color(31, 39, 191));
		h.setFont(new Font("Arial", Font.BOLD, 15));
		h.setBackground(new Color(116, 235, 52));

		tableLaoDong.setRowHeight(tableLaoDong.getRowHeight() + 20);

		tableLaoDong.getColumnModel().getColumn(0).setPreferredWidth(35);
		tableLaoDong.getColumnModel().getColumn(1).setPreferredWidth(124);
		tableLaoDong.getColumnModel().getColumn(2).setPreferredWidth(20);
		tableLaoDong.getColumnModel().getColumn(3).setPreferredWidth(10);
		tableLaoDong.getColumnModel().getColumn(4).setPreferredWidth(55);
		tableLaoDong.getColumnModel().getColumn(5).setPreferredWidth(40);
		tableLaoDong.getColumnModel().getColumn(6).setPreferredWidth(350);
		tableLaoDong.getColumnModel().getColumn(7).setPreferredWidth(30);

		JLabel lblNewLabel_3 = new JLabel("Gi???i T??nh:");
		lblNewLabel_3.setBounds(888, 11, 106, 30);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));

		cboGioiTinh = new JComboBox<String>();
		cboGioiTinh.setBounds(1004, 15, 199, 22);
		cboGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cboGioiTinh.addItem("Nam");
		cboGioiTinh.addItem("N???");
		cboGioiTinh.setEditable(false);

		JButton btnTurnTop = new JButton("");
		btnTurnTop.setBounds(33, 527, 40, 25);
		btnTurnTop.setFocusPainted(false);
		btnTurnTop.setIcon(
				new ImageIcon(QuanLiLaoDongJPanel.class.getResource("/images/baseline_skip_previous_white_24dp.png")));
		btnTurnTop.setForeground(Color.DARK_GRAY);
		btnTurnTop.setBackground(Color.DARK_GRAY);

		JButton btnTurnLeft = new JButton("");
		btnTurnLeft.setBounds(77, 527, 40, 25);
		btnTurnLeft.setFocusPainted(false);
		btnTurnLeft.setIcon(
				new ImageIcon(QuanLiLaoDongJPanel.class.getResource("/images/baseline_fast_rewind_white_24dp.png")));
		btnTurnLeft.setBackground(Color.DARK_GRAY);

		lblPage = new JLabel("1");
		lblPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPage.setBackground(Color.LIGHT_GRAY);
		lblPage.setOpaque(true);
		lblPage.setBounds(120, 527, 55, 25);

		JButton btnTurnRight = new JButton("");
		btnTurnRight.setBounds(178, 527, 40, 25);
		btnTurnRight.setFocusPainted(false);
		btnTurnRight.setIcon(
				new ImageIcon(QuanLiLaoDongJPanel.class.getResource("/images/baseline_fast_forward_white_24dp.png")));
		btnTurnRight.setBackground(Color.DARK_GRAY);

		JButton btnTurnEnd = new JButton("");
		btnTurnEnd.setBounds(224, 527, 40, 25);
		btnTurnEnd.setFocusPainted(false);
		btnTurnEnd.setIcon(
				new ImageIcon(QuanLiLaoDongJPanel.class.getResource("/images/baseline_skip_next_white_24dp.png")));
		btnTurnEnd.setBackground(Color.DARK_GRAY);
		tableLaoDong.addMouseListener(this);

		btnTurnRight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				denTrangSau();
			}
		});
		btnTurnTop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				denTrangDau();
			}
		});
		btnTurnLeft.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				denTrangTruoc();
			}
		});
		btnTurnEnd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				denTrangCuoi();
			}
		});

		cboTinhTP.addItemListener(this);
		cboQuanHuyen.addItemListener(this);
		cboPhuongXa.addItemListener(this);

		ngaySinh = new JDateChooser();
		ngaySinh.setBounds(610, 57, 201, 22);
		ngaySinh.setDateFormatString("dd/MM/yyyy");

		JLabel lblTmKim = new JLabel("T??m ki???m:");
		lblTmKim.setBounds(33, 172, 63, 14);
		lblTmKim.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblMaNV_1 = new JLabel("M?? lao ?????ng:");
		lblMaNV_1.setBounds(33, 197, 98, 30);
		lblMaNV_1.setFont(new Font("Times New Roman", Font.BOLD, 16));

		txtTimMa = new JTextField();
		txtTimMa.setBounds(137, 203, 106, 22);
		txtTimMa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTimMa.setColumns(10);
		txtTimMa.addKeyListener(this);

		txtTimTenLD = new JTextField();
		txtTimTenLD.setBounds(344, 200, 288, 22);
		txtTimTenLD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTimTenLD.setColumns(10);
		txtTimTenLD.addKeyListener(this);

		JLabel lblMaNV_1_1 = new JLabel("H??? t??n:");
		lblMaNV_1_1.setBounds(283, 200, 71, 25);
		lblMaNV_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));

		JSeparator separator = new JSeparator();
		separator.setBounds(437, 184, 494, 2);
		setLayout(null);
		add(lblHoTenNV);
		add(lblSdt);
		add(cboTinhTP);
		add(cboQuanHuyen);
		add(cboPhuongXa);
		add(txtTenLD);
		add(txtSdt);
		add(lblMatKhau);
		add(txtMatKhau);
		add(lblNgaySinh);
		add(cboTrinhDo);
		add(lblNewLabel);
		add(lblNewLabel_2);
		add(txtCMND);
		add(scrollPane);
		add(lblNewLabel_3);
		add(cboGioiTinh);
		add(btnTurnTop);
		add(btnTurnLeft);
		add(lblPage);
		add(btnTurnRight);
		add(btnTurnEnd);
		add(ngaySinh);
		add(lblTmKim);
		add(lblMaNV_1);
		add(txtTimMa);
		add(txtTimTenLD);
		add(lblMaNV_1_1);
		add(separator);

		JLabel lblaCh = new JLabel("?????a ch???:");
		lblaCh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblaCh.setBounds(33, 93, 84, 30);
		add(lblaCh);

		btnThem = new JButton("Th??m");
		btnThem.setBounds(168, 143, 89, 27);
		btnThem.setIcon(new ImageIcon(QuanLiLaoDongJPanel.class.getResource("/images/them.png")));
		add(btnThem);

		btnSua = new JButton("C???p nh???t");
		btnSua.setBounds(293, 143, 106, 27);
		btnSua.setIcon(new ImageIcon(QuanLiLaoDongJPanel.class.getResource("/images/cap_nhat.png")));
		add(btnSua);

		btnXoaTrang = new JButton("L??m m???i");
		btnXoaTrang.setBounds(584, 143, 106, 27);
		btnXoaTrang.setIcon(new ImageIcon(QuanLiLaoDongJPanel.class.getResource("/images/lam_moi.png")));
		add(btnXoaTrang);

		btnXoa = new JButton("Ngh??? vi???c");
		btnXoa.setBounds(437, 143, 116, 27);
		btnXoa.setIcon(new ImageIcon(QuanLiLaoDongJPanel.class.getResource("/images/xoa.png")));
		add(btnXoa);

		txtMatKhau.setEditable(false);

		listLaoDong = new ArrayList<LaoDong>();

		listLaoDong = lDao.getAllLaoDong();
		listLaoDong = lDao.phanTrang(1, 7);
		docDuLieu();

		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnSua.addActionListener(this);
		txtCMND.addKeyListener(this);

	}

	private void docDuLieu() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (LaoDong ld : listLaoDong) {
			modelLaoDong.addRow(new Object[] { ld.getMaLaoDong(), ld.getTenLaoDong(), df.format(ld.getNgaySinh()),
					ld.isGioiTinh() ? "Nam" : "N???", ld.getSDT(), ld.getCMND(), ld.getDiaChi(),
					ld.getTrinhDo().getTenTrinhDo() });
		}

	}

	/**
	 * H??m di chuy???n ?????n trang th??? i-1 c???a b???ng th??ng tin linh ki???n
	 */
	private void denTrangTruoc() {

		int trang = Integer.parseInt(lblPage.getText());
		if (trang > 1) {
			lblPage.setText(String.valueOf(trang - 1));
			soDau = 7 * (trang - 2) + 1;
			soCuoi = soDau + 6;
			listLaoDong = lDao.phanTrang(soDau, soCuoi);
			xoaHetModel();
			docDuLieu();
		}
	}

	/**
	 * H??m di chuy???n ?????n trang th??? i+1 c???a b???ng th??ng tin linh ki???n
	 */
	public void denTrangSau() {

		int slDb = lDao.demSluongDuLieuTrongDB();
		int trangLonNhat;
		int trang = Integer.parseInt(lblPage.getText());
		if (slDb % 7 == 0) {
			trangLonNhat = slDb / 7;
		} else {
			trangLonNhat = slDb / 7 + 1;
		}
		if (trang < trangLonNhat) {
			lblPage.setText(String.valueOf(trang + 1));
			soDau = 7 * (trang) + 1;
			soCuoi = soDau + 6;
			listLaoDong = lDao.phanTrang(soDau, soCuoi);
			xoaHetModel();
			docDuLieu();
		}
	}

	/**
	 * H??m di chuy???n ?????n trang ?????u c???a b???ng th??ng tin linh ki???n
	 */
	private void denTrangDau() {
		listLaoDong = lDao.phanTrang(1, 7);
		xoaHetModel();
		docDuLieu();
		lblPage.setText("1");
	}

	/**
	 * H??m di chuy???n ?????n trang cu???i c???a b???ng th??ng tin linh ki???n
	 */
	private void denTrangCuoi() {

		int slDb = lDao.demSluongDuLieuTrongDB();
		int trangLonNhat;
		if (slDb % 7 == 0) {
			trangLonNhat = slDb / 7;
		} else {
			trangLonNhat = slDb / 7 + 1;
		}
		soDau = 7 * (trangLonNhat - 1) + 1;
		soCuoi = soDau + 6;
		listLaoDong = lDao.phanTrang(soDau, soCuoi);
		xoaHetModel();
		docDuLieu();
		lblPage.setText(String.valueOf(trangLonNhat));

	}

	private void xoaHetModel() {
		DefaultTableModel df = (DefaultTableModel) tableLaoDong.getModel();
		df.getDataVector().removeAllElements();
		tableLaoDong.clearSelection();
	}

	public boolean validata() {
		if (txtTenLD.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "T??n kh??ng ???????c r???ng");
			txtTenLD.requestFocus();
			return false;
		}
		if (!txtTenLD.getText()
				.matches("[aA????????????????????????????????????????????????????????????????????????????????????????????bBcCdD????eE????????????????????????????????????????????????????????????\r\n"
						+ "fFgGhHiI????????????????????????jJkKlLmMnNoO????????????????????????????????????????????????????????????????????????????????????????????pPqQrRsStTu\r\n"
						+ "U??????????????????????????????????????????????????????????vVwWxXyY????????????????????????????zZ ]+")) {
			JOptionPane.showMessageDialog(null, "T??n kh??ng ???????c ch???a k?? t??? ?????c bi???t");
			txtTenLD.selectAll();
			txtTenLD.requestFocus();
			return false;
		}

		if (txtCMND.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "CMND kh??ng ???????c r???ng");
			txtCMND.requestFocus();
			return false;
		}

		if (!txtCMND.getText().matches("[0-9]{9}|{12}")) {
			JOptionPane.showMessageDialog(null, "CMND ph???i bao g???m 9 ho???c 12 s???");
			txtCMND.selectAll();
			txtCMND.requestFocus();
			return false;
		}

		if (txtSdt.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "S??? ??i???n tho???i kh??ng ???????c r???ng");
			txtSdt.requestFocus();
			return false;
		}

		if (!txtSdt.getText().matches("^0[0-9]{9}")) {
			JOptionPane.showMessageDialog(null, "S??? ??i???n tho???i kh??ng h???p l???");
			txtSdt.selectAll();
			txtSdt.requestFocus();
			return false;
		}

		if (ngaySinh.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n ng??y sinh");
			return false;
		}
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String s[] = df.format(ngaySinh.getDate()).split("/");
		LocalDate ns = LocalDate.of(Integer.parseInt(s[2]), Integer.parseInt(s[1]), Integer.parseInt(s[0]));
		
		if(Period.between(ns, LocalDate.now()).getYears()<18) {
			JOptionPane.showMessageDialog(null, "Lao ?????ng ph???i ????? 18 tu???i");
			ngaySinh.setDate(null);
			return false;
		}

		if(cboGioiTinh.getSelectedIndex()==0) {
			if(Period.between(ns, LocalDate.now()).getYears()>65) {
				JOptionPane.showMessageDialog(null, "Lao ?????ng l?? nam ph???i nh??? h??n 60 tu???i");
				ngaySinh.setDate(null);
				return false;
			}
		}
		else {
			if(Period.between(ns, LocalDate.now()).getYears()>55) {
				JOptionPane.showMessageDialog(null, "Lao ?????ng n??? ph???i nh??? h??n 55 tu???i");
				ngaySinh.setDate(null);
				return false;
			}
		}
		
		if (cboTinhTP.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n t???nh/th??nh ph???");
			return false;
		}

		if (cboQuanHuyen.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n qu???n/huy???n");
			return false;
		}
		if (cboPhuongXa.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n ph?????ng/x??");
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (o.equals(btnThem)) {
			if (tableLaoDong.getSelectedRow() > -1) {
				JOptionPane.showMessageDialog(null, "Vui l??ng l??m m???i tr?????c khi th??m");
				return;
			}
			if (validata()) {
				String ma = taoMaLD();
				String ten = txtTenLD.getText();
				String cmnd = txtCMND.getText();
				String sdt = txtSdt.getText();
				boolean gt = cboGioiTinh.getSelectedIndex() == 0;
				Date d = ngaySinh.getDate();
				String s[] = df.format(d).split("/");
				@SuppressWarnings("deprecation")
				java.sql.Date ngay = new java.sql.Date(Integer.parseInt(s[2]) - 1900, Integer.parseInt(s[1]) - 1,
						Integer.parseInt(s[0]));
				String td = cboTrinhDo.getSelectedItem().toString();
				String tinh = cboTinhTP.getSelectedItem().toString();
				String huyen = cboQuanHuyen.getSelectedItem().toString();
				String xa = cboPhuongXa.getSelectedItem().toString();

				int x = JOptionPane.showConfirmDialog(null, "B???n c?? mu???n th??m", "Th??ng b??o", JOptionPane.YES_NO_OPTION);
				if (x == JOptionPane.YES_OPTION) {
					boolean t = lDao.themLD(new LaoDong(ma, ten, ngay, gt, cmnd, new DiaDiem(tinh, huyen, xa),
							new TrinhDo(td), sdt, "12345678"));
					if (t) {
						JOptionPane.showMessageDialog(this, "Th??m th??nh c??ng");
						xoaRong();
						
					}
					else
						JOptionPane.showMessageDialog(this, "Th??m kh??ng th??nh c??ng");
				}
			}
		} else if (o.equals(btnSua)) {
			int row = tableLaoDong.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui l??ng ch???n lao ?????ng");
				return;
			} else {
				if (validata()) {
					if (txtMatKhau.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "M???t kh???u kh??ng ???????c r???ng");
						return;
					}
					if (txtMatKhau.getText().length() < 8) {
						JOptionPane.showMessageDialog(null, "M???t kh???u ph???i > 8 k?? t???");
						txtMatKhau.selectAll();
						txtMatKhau.requestFocus();
						return;
					}
					LaoDong ld = listLaoDong.get(row);
					String ma = ld.getMaLaoDong();
					String ten = txtTenLD.getText();
					String cmnd = txtCMND.getText();
					String sdt = txtSdt.getText();
					boolean gt = cboGioiTinh.getSelectedIndex() == 0;
					Date d = ngaySinh.getDate();
					String s[] = df.format(d).split("/");
					@SuppressWarnings("deprecation")
					java.sql.Date ngay = new java.sql.Date(Integer.parseInt(s[2]) - 1900, Integer.parseInt(s[1]) - 1,
							Integer.parseInt(s[0]));
					String td = cboTrinhDo.getSelectedItem().toString();
					String tinh = cboTinhTP.getSelectedItem().toString();
					String huyen = cboQuanHuyen.getSelectedItem().toString();
					String xa = cboPhuongXa.getSelectedItem().toString();
					String matkhau = txtMatKhau.getText();
					ld = new LaoDong(ma, ten, ngay, gt, cmnd, new DiaDiem(tinh, huyen, xa), new TrinhDo(td), sdt,
							matkhau);
					int kt = JOptionPane.showConfirmDialog(null, "B???n c?? mu???n s???a", "Th??ng b??o",
							JOptionPane.YES_NO_OPTION);
					if (kt == JOptionPane.YES_OPTION) {
						boolean t = lDao.suaLD(ld);
						if (t) {
							JOptionPane.showMessageDialog(this, "S???a th??nh c??ng");
							modelLaoDong.setValueAt(ma, row, 0);
							modelLaoDong.setValueAt(ten, row, 1);
							modelLaoDong.setValueAt(df.format(ngay), row, 2);
							modelLaoDong.setValueAt(gt ? "Nam" : "N???", row, 3);
							modelLaoDong.setValueAt(sdt, row, 4);
							modelLaoDong.setValueAt(cmnd, row, 5);
							modelLaoDong.setValueAt(tinh + ", " + huyen + ", " + xa, row, 6);
							modelLaoDong.setValueAt(td, row, 7);
							listLaoDong.add(row, ld);
							listLaoDong.remove(row + 1);
							xoaRong();
						} else
							JOptionPane.showMessageDialog(this, "S???a kh??ng th??nh c??ng");
					}
				}
			}
		} else if (o.equals(btnXoa)) {
			int row = tableLaoDong.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui l??ng ch???n lao ?????ng");
				return;
			} else {
				LaoDong ld = listLaoDong.get(row);
				int a = JOptionPane.showConfirmDialog(null, "B???n c?? mu???n cho lao ?????ng ngh??? vi???c", "Th??ng b??o",
						JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					boolean t = lDao.capNhatTrangThai(ld, false);
					if (t) {
						JOptionPane.showMessageDialog(this, "Th??nh c??ng");
						listLaoDong = lDao.phanTrang(soDau, soCuoi);
						xoaRong();
						xoaHetModel();
						docDuLieu();
					} else
						JOptionPane.showMessageDialog(this, "Kh??ng th??nh c??ng");
				}
			}
		} else {
			xoaRong();
		}
	}

	public void xoaRong() {
		txtTenLD.setText("");
		txtCMND.setText("");
		txtSdt.setText("");
		cboGioiTinh.setSelectedIndex(0);
		ngaySinh.setDate(null);
		cboTrinhDo.setSelectedIndex(0);
		cboTinhTP.setSelectedIndex(0);
		txtMatKhau.setText("");
		tableLaoDong.clearSelection();
		txtMatKhau.setEditable(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableLaoDong.getSelectedRow();
		LaoDong ld = listLaoDong.get(row);
		txtTenLD.setText(ld.getTenLaoDong());
		txtCMND.setText(ld.getCMND());
		txtSdt.setText(ld.getSDT());
		ngaySinh.setDate(ld.getNgaySinh());
		cboTrinhDo.setSelectedItem(ld.getTrinhDo().getTenTrinhDo());
		cboTinhTP.setSelectedItem(ld.getDiaChi().getTinhTP());
		cboQuanHuyen.setSelectedItem(ld.getDiaChi().getQuanHuyen());
		cboPhuongXa.setSelectedItem(ld.getDiaChi().getPhuongXa());
		txtMatKhau.setText(ld.getMatKhau());
		cboGioiTinh.setSelectedItem(ld.isGioiTinh() ? "Nam" : "N???");
		txtMatKhau.setEditable(true);
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

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getSource();
		if (o.equals(cboTinhTP)) {
			String tinh = cboTinhTP.getSelectedItem().toString();
			ArrayList<String> quan = new DiaDiem_DAO().getQuan(tinh);
			for (int i = cboQuanHuyen.getItemCount() - 1; i >= 1; i--) {
				cboQuanHuyen.removeItemAt(i);
			}
			for (String q : quan) {
				cboQuanHuyen.addItem(q);
			}

		} else if (o.equals(cboQuanHuyen)) {
			String tinh = cboTinhTP.getSelectedItem().toString();
			String quan = cboQuanHuyen.getSelectedItem().toString();
			ArrayList<String> phuong = new DiaDiem_DAO().getPhuong(tinh, quan);
			for (int i = cboPhuongXa.getItemCount() - 1; i >= 1; i--) {
				cboPhuongXa.removeItemAt(i);
			}
			for (String p : phuong) {
				cboPhuongXa.addItem(p);
			}

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!txtTimMa.getText().equals("") || !txtTimTenLD.getText().equals("")) {
			listLaoDong = lDao.timKiemLaoDong(txtTimMa.getText(), txtTimTenLD.getText());
			xoaHetModel();
			docDuLieu();

		} else if ((!txtCMND.getText().equals(""))) {
			LaoDong ld = lDao.timLDDuaVaoCMND(txtCMND.getText().trim());
			System.out.println(txtCMND.getText());
			if (ld.getMaLaoDong() != null) {

				txtTenLD.setText(ld.getTenLaoDong());
				txtCMND.setText(ld.getCMND());
				txtSdt.setText(ld.getSDT());
				ngaySinh.setDate(ld.getNgaySinh());
				cboTrinhDo.setSelectedItem(ld.getTrinhDo().getTenTrinhDo());
				cboTinhTP.setSelectedItem(ld.getDiaChi().getTinhTP());
				cboQuanHuyen.setSelectedItem(ld.getDiaChi().getQuanHuyen());
				cboPhuongXa.setSelectedItem(ld.getDiaChi().getPhuongXa());
				txtMatKhau.setText(ld.getMatKhau());
				cboGioiTinh.setSelectedItem(ld.isGioiTinh() ? "Nam" : "N???");

				int kt = JOptionPane.showConfirmDialog(null, "Lao ?????ng ???? t???ng l??m vi???c b???n c?? mu???n th??m l???i",
						"Th??ng b??o", JOptionPane.YES_NO_OPTION);
				if (kt == JOptionPane.YES_OPTION) {
					lDao.capNhatTrangThai(ld, true);
					JOptionPane.showMessageDialog(null, "Th??m th??nh c??ng");
				}
				xoaRong();
				listLaoDong = lDao.phanTrang(soDau, soCuoi);
				xoaHetModel();
				docDuLieu();

			}
		} else {
			xoaHetModel();
			listLaoDong = lDao.phanTrang(1, 7);
			docDuLieu();
		}

	}

	public String taoMaLD() {
		String maCuoi = lDao.getLDCuoi().getMaLaoDong();
		long so = Long.parseLong(maCuoi.substring(2)) + 100000000 + 1;
		String soMoi = ("" + so).substring(1);
		return "LD" + soMoi;
	}
}
