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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import dao.DiaDiem_DAO;
import dao.QuanLyCongTrinh_DAO;
import entity.CongTrinh;
import entity.DiaDiem;

import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Cursor;

public class QuanLiCongTrinhJPanel extends JPanel implements MouseListener, ActionListener, ItemListener, KeyListener {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JTextField txtTenCongTrinh;
	private DefaultTableModel modelCongTrinh;
	private JTable tableCongTrinh;
	private JComboBox<String> cboLoaiCongTrinh;
	private JComboBox<String> cboTinhTP;
	private JComboBox<String> cboQuanHuyen;
	private JComboBox<String> cboPhuongXa;
	private ArrayList<String> tinh = new DiaDiem_DAO().getTinh();
	private ArrayList<CongTrinh> listCT;
	private JTextField txtTimKiemMaCT;
	private JTextField txtTimKiemTenCT;
	private JComboBox<String> cboTimKiemTinh_Tp;
	private JComboBox<String> cboTimKiemQuanHuyen;
	private JComboBox<String> cboTimKiemPhuongXa;
	private JDateChooser ngayThiCong;
	private JDateChooser ngayDKHoanThanh;
	private JComboBox<String> cboTrangThai;
	private QuanLyCongTrinh_DAO congTrinhDAO = new QuanLyCongTrinh_DAO();
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnLamMoi;
	private JComboBox<String> cboHienThiTrangThai;
	private JLabel txtPage;
	private int soDau = 1;
	private int soCuoi = 7;
	private JDateChooser ngayHoanThanh;

	/**
	 * Create the panel.
	 */
	public QuanLiCongTrinhJPanel() {

		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblHoTenNV = new JLabel("Tên công trình:");
		lblHoTenNV.setBounds(21, 11, 143, 30);
		lblHoTenNV.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lblHoTenNV);

		txtTenCongTrinh = new JTextField();
		txtTenCongTrinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenCongTrinh.setBounds(190, 15, 813, 22);
		add(txtTenCongTrinh);
		txtTenCongTrinh.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 238, 1289, 271);
		add(scrollPane);
		String[] colHeader = { "Mã CT", "Tên CT", "Loại CT", "Địa điểm", "Ngày KC", "Ngày DK hoàn thành" };
		modelCongTrinh = new DefaultTableModel(colHeader, 0) {
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
		tableCongTrinh = new JTable(modelCongTrinh);
		tableCongTrinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane.setViewportView(tableCongTrinh);

		JTableHeader h = tableCongTrinh.getTableHeader();
		h.setForeground(new Color(31, 39, 191));
		h.setFont(new Font("Arial", Font.BOLD, 15));
		h.setBackground(new Color(116, 235, 52));

		tableCongTrinh.setRowHeight(tableCongTrinh.getRowHeight() + 20);

		tableCongTrinh.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableCongTrinh.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableCongTrinh.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableCongTrinh.getColumnModel().getColumn(3).setPreferredWidth(350);
		tableCongTrinh.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableCongTrinh.getColumnModel().getColumn(5).setPreferredWidth(50);

		JLabel lblNewLabel = new JLabel("Ngày thi công:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(21, 52, 160, 30);
		add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Ngày dự kiến hoàn thành:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(481, 52, 281, 30);
		add(lblNewLabel_2);

		JButton btnTurnTop = new JButton("");
		btnTurnTop.setFocusPainted(false);
		btnTurnTop.setIcon(new ImageIcon(
				QuanLiCongTrinhJPanel.class.getResource("/images/baseline_skip_previous_white_24dp.png")));
		btnTurnTop.setForeground(Color.DARK_GRAY);
		btnTurnTop.setBackground(Color.DARK_GRAY);
		btnTurnTop.setBounds(21, 520, 37, 25);
		add(btnTurnTop);

		JButton btnTurnLeft = new JButton("");
		btnTurnLeft.setFocusPainted(false);
		btnTurnLeft.setIcon(
				new ImageIcon(QuanLiCongTrinhJPanel.class.getResource("/images/baseline_fast_rewind_white_24dp.png")));
		btnTurnLeft.setBackground(Color.DARK_GRAY);
		btnTurnLeft.setBounds(61, 520, 37, 25);
		add(btnTurnLeft);

		JButton btnTurnRight = new JButton("");
		btnTurnRight.setFocusPainted(false);
		btnTurnRight.setIcon(
				new ImageIcon(QuanLiCongTrinhJPanel.class.getResource("/images/baseline_fast_forward_white_24dp.png")));
		btnTurnRight.setBackground(Color.DARK_GRAY);
		btnTurnRight.setBounds(156, 520, 37, 25);
		add(btnTurnRight);

		JButton btnTurnEnd = new JButton("");
		btnTurnEnd.setFocusPainted(false);
		btnTurnEnd.setIcon(
				new ImageIcon(QuanLiCongTrinhJPanel.class.getResource("/images/baseline_skip_next_white_24dp.png")));
		btnTurnEnd.setBackground(Color.DARK_GRAY);
		btnTurnEnd.setBounds(197, 520, 37, 25);
		add(btnTurnEnd);

		cboTinhTP = new JComboBox<String>();
		cboTinhTP.addItem("Tỉnh/Thành phố");
		cboTinhTP.setBounds(190, 141, 200, 22);
		add(cboTinhTP);

		for (String t : tinh) {
			cboTinhTP.addItem(t);
		}

		cboQuanHuyen = new JComboBox<String>();
		cboQuanHuyen.addItem("Quận/Huyện");
		cboQuanHuyen.setBounds(440, 141, 200, 22);
		add(cboQuanHuyen);

		cboPhuongXa = new JComboBox<String>();
		cboPhuongXa.addItem("Phường/Xã");
		cboPhuongXa.setBounds(690, 141, 200, 22);
		add(cboPhuongXa);

		JLabel lbaelmas = new JLabel("Địa điểm :");
		lbaelmas.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lbaelmas.setBounds(21, 137, 157, 34);
		add(lbaelmas);

		JLabel lblNewLabel_3 = new JLabel("Loại công trình:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(21, 93, 175, 33);
		add(lblNewLabel_3);

		cboLoaiCongTrinh = new JComboBox<String>();
		cboLoaiCongTrinh.setBounds(190, 96, 237, 22);
		cboLoaiCongTrinh.addItem("Giao thông");
		cboLoaiCongTrinh.addItem("Dân dụng");
		cboLoaiCongTrinh.addItem("Công nghiệp");
		cboLoaiCongTrinh.addItem("An ninh quốc phòng");
		cboLoaiCongTrinh.addItem("Nông nghiệp và phát triển nông thôn");
		add(cboLoaiCongTrinh);

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTrangThai.setBounds(484, 93, 106, 33);
		add(lblTrangThai);

		ngayThiCong = new JDateChooser();
		ngayThiCong.setBounds(189, 52, 238, 22);
		add(ngayThiCong);
		ngayThiCong.setDateFormatString("dd/MM/yyyy");

		ngayDKHoanThanh = new JDateChooser();
		ngayDKHoanThanh.setBounds(782, 52, 221, 22);
		add(ngayDKHoanThanh);
		ngayDKHoanThanh.setDateFormatString("dd/MM/yyyy");

		cboTrangThai = new JComboBox<String>();

		cboTrangThai.setBounds(592, 100, 160, 22);
		cboTrangThai.addItem("Chưa hoàn thành");
		cboTrangThai.addItem("Hoàn thành");
		cboTrangThai.addItem("Hủy");
		add(cboTrangThai);

		listCT = congTrinhDAO.phanTrang(1, 7, "Chưa hoàn thành");
		docDuLieuVaoModel();

		JSeparator separator = new JSeparator();
		separator.setBounds(354, 187, 603, 2);
		add(separator);

		JLabel lblTmKim = new JLabel("Tìm kiếm:");
		lblTmKim.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTmKim.setBounds(21, 187, 77, 14);
		add(lblTmKim);

		JLabel lblMaNV = new JLabel("Mã công trình:");
		lblMaNV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMaNV.setBounds(21, 203, 97, 30);
		add(lblMaNV);

		txtTimKiemMaCT = new JTextField();
		txtTimKiemMaCT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimKiemMaCT.setColumns(10);
		txtTimKiemMaCT.setBounds(118, 205, 116, 22);
		add(txtTimKiemMaCT);

		JLabel lblTnCngTrnh = new JLabel("Tên công trình");
		lblTnCngTrnh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTnCngTrnh.setBounds(243, 200, 97, 30);
		add(lblTnCngTrnh);

		txtTimKiemTenCT = new JTextField();
		txtTimKiemTenCT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimKiemTenCT.setColumns(10);
		txtTimKiemTenCT.setBounds(340, 205, 383, 22);
		add(txtTimKiemTenCT);

		JLabel lbaelmas_1 = new JLabel("Địa điểm :");
		lbaelmas_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbaelmas_1.setBounds(747, 200, 87, 30);
		add(lbaelmas_1);

		cboTimKiemTinh_Tp = new JComboBox<String>();
		cboTimKiemTinh_Tp.setBounds(845, 205, 151, 25);
		add(cboTimKiemTinh_Tp);
		cboTimKiemTinh_Tp.addItem("Tỉnh/TP");
		for (String t : tinh) {
			cboTimKiemTinh_Tp.addItem(t);
		}

		cboTimKiemQuanHuyen = new JComboBox<String>();
		cboTimKiemQuanHuyen.setBounds(1006, 205, 160, 25);
		add(cboTimKiemQuanHuyen);
		cboTimKiemQuanHuyen.addItem("Quận/huyện");

		cboTimKiemPhuongXa = new JComboBox<String>();
		cboTimKiemPhuongXa.setBounds(1176, 205, 134, 25);
		add(cboTimKiemPhuongXa);
		cboTimKiemPhuongXa.addItem("Phường/Xã");

		btnTurnLeft.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				denTrangTruoc();

			}
		});
		btnTurnTop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				denTrangDau();
			}
		});
		btnTurnRight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				denTrangSau();
			}
		});
		btnTurnEnd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				denTrangCuoi();
			}
		});

		tableCongTrinh.addMouseListener(this);
		cboTinhTP.addItemListener(this);
		cboQuanHuyen.addItemListener(this);
		cboPhuongXa.addItemListener(this);
		txtTimKiemMaCT.addKeyListener(this);
		txtTimKiemTenCT.addKeyListener(this);
		cboTimKiemTinh_Tp.addItemListener(this);
		cboTimKiemQuanHuyen.addItemListener(this);
		cboTimKiemPhuongXa.addItemListener(this);

		btnThem = new JButton("Thêm");
		btnThem.setFocusPainted(false);
		btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThem.setBounds(1051, 17, 114, 34);
		btnThem.setIcon(new ImageIcon(QuanLiCongTrinhJPanel.class.getResource("/images/them.png")));
		add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setFocusPainted(false);
		btnSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSua.setBounds(1176, 17, 114, 34);
		btnSua.setIcon(new ImageIcon(QuanLiCongTrinhJPanel.class.getResource("/images/cap_nhat.png")));
		add(btnSua);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLamMoi.setFocusPainted(false);
		btnLamMoi.setBounds(1176, 90, 114, 34);
		btnLamMoi.setIcon(new ImageIcon(QuanLiCongTrinhJPanel.class.getResource("/images/lam_moi.png")));
		add(btnLamMoi);

		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);

		cboTrangThai.setEnabled(false);

		JLabel lblHinTh = new JLabel("Hiển thị:");
		lblHinTh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblHinTh.setBounds(299, 515, 69, 30);
		add(lblHinTh);

		cboHienThiTrangThai = new JComboBox<String>();
		cboHienThiTrangThai.setBounds(364, 520, 221, 22);
		add(cboHienThiTrangThai);

		cboHienThiTrangThai.addItem("Chưa Hoàn thành");
		cboHienThiTrangThai.addItem("Hoàn thành");
		cboHienThiTrangThai.addItem("Hủy");

		cboHienThiTrangThai.addItemListener(this);

		txtPage = new JLabel("1");
		txtPage.setBackground(Color.LIGHT_GRAY);
		txtPage.setHorizontalAlignment(SwingConstants.CENTER);
		txtPage.setBounds(103, 520, 46, 25);
		txtPage.setOpaque(true);
		add(txtPage);

		JLabel lblNewLabel_2_1 = new JLabel("Ngày hoàn thành:");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(782, 95, 143, 30);
		add(lblNewLabel_2_1);

		ngayHoanThanh = new JDateChooser();
		ngayHoanThanh.setDateFormatString("dd/MM/yyyy");
		ngayHoanThanh.setBounds(932, 100, 221, 22);
		add(ngayHoanThanh);
		ngayHoanThanh.setEnabled(false);

		cboTrangThai.addItemListener(this);
	}

	public void docDuLieuVaoModel() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CongTrinh ct : listCT) {
			modelCongTrinh.addRow(new Object[] { ct.getMaCongTrinh(), ct.getTenCongTrinh(), ct.getLoaiCongTrinh(),
					ct.getDiaDiem(), df.format(ct.getNgayKhoiCong()), df.format(ct.getNgayDKHoanThanh()) });
		}
	}

	/**
	 * Hàm di chuyển đến trang đầu của bảng
	 */
	private void denTrangDau() {
		listCT = congTrinhDAO.phanTrang(1, 7, cboHienThiTrangThai.getSelectedItem().toString());
		xoaAllModel();
		docDuLieuVaoModel();
		tableCongTrinh.clearSelection();
		txtPage.setText("1");
	}

	/**
	 * Hàm di chuyển đến trang cuối của bảng
	 */
	private void denTrangCuoi() {

		int slDb = congTrinhDAO.demSluongDuLieuTrongDB(cboHienThiTrangThai.getSelectedItem().toString());
		int trangLonNhat;
		if (slDb % 7 == 0) {
			trangLonNhat = slDb / 7;
		} else {
			trangLonNhat = slDb / 7 + 1;
		}
		soDau = 7 * (trangLonNhat - 1) + 1;
		soCuoi = soDau + 6;
		xoaAllModel();
		listCT = congTrinhDAO.phanTrang(soDau, soCuoi, cboHienThiTrangThai.getSelectedItem().toString());
		xoaAllModel();
		docDuLieuVaoModel();
		tableCongTrinh.clearSelection();
		txtPage.setText(String.valueOf(trangLonNhat));

	}

	/**
	 * Hàm di chuyển đến trang thứ i-1 của bảng
	 */
	private void denTrangTruoc() {
		int trang = Integer.parseInt(txtPage.getText());
		if (trang > 1) {
			txtPage.setText(String.valueOf(trang - 1));
			soDau = 7 * (trang - 2) + 1;
			soCuoi = soDau + 6;
			xoaAllModel();
			listCT = congTrinhDAO.phanTrang(soDau, soCuoi, cboHienThiTrangThai.getSelectedItem().toString());
			xoaAllModel();
			docDuLieuVaoModel();
			tableCongTrinh.clearSelection();
		}
	}

	/**
	 * Hàm di chuyển đến trang thứ i+1 của bảng
	 */
	private void denTrangSau() {
		int slDb = congTrinhDAO.demSluongDuLieuTrongDB(cboHienThiTrangThai.getSelectedItem().toString());
		int trangLonNhat;
		int trang = Integer.parseInt(txtPage.getText());
		if (slDb % 7 == 0) {
			trangLonNhat = slDb / 7;
		} else {
			trangLonNhat = slDb / 7 + 1;
		}
		if (trang < trangLonNhat) {
			txtPage.setText(String.valueOf(trang + 1));
			soDau = 7 * (trang) + 1;
			soCuoi = soDau + 6;
			xoaAllModel();
			listCT = congTrinhDAO.phanTrang(soDau, soCuoi, cboHienThiTrangThai.getSelectedItem().toString());
			docDuLieuVaoModel();
			tableCongTrinh.clearSelection();
		}
	}

	public void xoaAllModel() {
		DefaultTableModel m = (DefaultTableModel) tableCongTrinh.getModel();
		m.getDataVector().removeAllElements();
	}

	private boolean validData() {

		if (txtTenCongTrinh.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Tên không được trống!");
			txtTenCongTrinh.requestFocus();
			return false;
		}

		java.util.Date ngayHT = ngayDKHoanThanh.getDate();
		java.util.Date ngayTC = ngayThiCong.getDate();

		if (ngayTC == null) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày thi công");
			return false;
		}

		if (ngayHT == null) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày hoàn thành");
			return false;
		}

		if (ngayHT.before(ngayTC)) {
			JOptionPane.showMessageDialog(null, "Ngày dự kiến hoàn thành phải sau ngày thi công");
			ngayDKHoanThanh.setDate(null);
			return false;
		}

		if (cboTinhTP.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn Tỉnh/TP");
			return false;
		}
		if (cboQuanHuyen.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn Quận/Huyện");
			return false;
		}
		if (cboPhuongXa.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn Phường/Xã");
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (tableCongTrinh.getSelectedRow() > -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn làm mới trước khi thêm");
				return;
			}
			if (validData()) {
				if (ngayThiCong.getDate().before(new java.util.Date())) {
					JOptionPane.showMessageDialog(null, "Ngày thi công phải sau ngày hiện tại");
					ngayThiCong.setDate(null);
					return;
				}
				String maCT = taoMaCT();
				Date ngayTH = chuyenNgay(ngayThiCong.getDate());
				Date ngayHT = chuyenNgay(ngayDKHoanThanh.getDate());
				String tenc = txtTenCongTrinh.getText();
				String loai = cboLoaiCongTrinh.getSelectedItem().toString();
				String dia1 = cboTinhTP.getSelectedItem().toString();
				String dia2 = cboQuanHuyen.getSelectedItem().toString();
				String dia3 = cboPhuongXa.getSelectedItem().toString();
				String tt = cboTrangThai.getSelectedItem().toString();
				CongTrinh nn = new CongTrinh(maCT, tenc, new DiaDiem(dia1, dia2, dia3), ngayTH, ngayHT, tt, loai);
				int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if (kt == JOptionPane.YES_OPTION) {
					boolean trinh = congTrinhDAO.themCT(nn);
					if (trinh)
						JOptionPane.showMessageDialog(null, "Thêm thành công");
					else
						JOptionPane.showMessageDialog(null, "Thêm không thành công");
				}
			}
		} else if (o.equals(btnSua)) {
			int row = tableCongTrinh.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Chưa chọn công trình");
				return;
			}
			if (validData()) {
				if(ngayHoanThanh.getDate()==null && cboTrangThai.getSelectedIndex()==1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày hoàn thành");
					return;
				}
				Date ngayTH = chuyenNgay(ngayThiCong.getDate());
				Date ngayHT = chuyenNgay(ngayDKHoanThanh.getDate());
				String tenc = txtTenCongTrinh.getText();
				String loai = cboLoaiCongTrinh.getSelectedItem().toString();
				String dia1 = cboTinhTP.getSelectedItem().toString();
				String dia2 = cboQuanHuyen.getSelectedItem().toString();
				String dia3 = cboPhuongXa.getSelectedItem().toString();
				String tt = cboTrangThai.getSelectedItem().toString();
				CongTrinh nn = new CongTrinh(listCT.get(row).getMaCongTrinh(), tenc, new DiaDiem(dia1, dia2, dia3),
						ngayTH, ngayHT, tt, loai);
				int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa", "Thông báo", JOptionPane.YES_NO_OPTION);
				if (kt == JOptionPane.YES_OPTION) {

					boolean trinh = congTrinhDAO.suaCT(nn);
					if (trinh) {
						if (cboTrangThai.getSelectedIndex() == 1) {
							Date ht = chuyenNgay(ngayHoanThanh.getDate());
							congTrinhDAO.setNgayHoanThanh(listCT.get(row).getMaCongTrinh(), ht);

						}
						if (cboTrangThai.getSelectedIndex() != 0)
							congTrinhDAO.capNhatTrangThai(listCT.get(row).getMaCongTrinh());

						xoaAllModel();
						listCT = congTrinhDAO.phanTrang(soDau, soCuoi,
								cboHienThiTrangThai.getSelectedItem().toString());

						docDuLieuVaoModel();
						xoaRong();
						JOptionPane.showMessageDialog(null, "Sửa thành công");
					} else
						JOptionPane.showMessageDialog(null, "Sửa không thành công");
				}
			}

		} else {
			xoaRong();
		}
	}

	public void xoaRong() {
		txtTenCongTrinh.setText("");
		cboLoaiCongTrinh.setSelectedIndex(0);
		cboTinhTP.setSelectedIndex(0);
		ngayThiCong.setDate(null);
		ngayDKHoanThanh.setDate(null);
		tableCongTrinh.clearSelection();
		txtTenCongTrinh.requestFocus();
		txtTimKiemMaCT.setText("");
		txtTimKiemTenCT.setText("");
		cboTimKiemTinh_Tp.setSelectedIndex(0);
		cboTrangThai.setSelectedIndex(0);
		cboTrangThai.setEnabled(false);

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

		} else if (o.equals(cboTimKiemTinh_Tp)) {
			String tinh = cboTimKiemTinh_Tp.getSelectedItem().toString();
			ArrayList<String> quan = new DiaDiem_DAO().getQuan(tinh);
			for (int i = cboTimKiemQuanHuyen.getItemCount() - 1; i >= 1; i--) {
				cboTimKiemQuanHuyen.removeItemAt(i);
			}
			for (String q : quan) {
				cboTimKiemQuanHuyen.addItem(q);
			}

			if (cboTimKiemTinh_Tp.getSelectedIndex() > 0) {

				listCT = congTrinhDAO.timKiemCongTrinh(
						new DiaDiem(cboTimKiemTinh_Tp.getSelectedItem().toString(), "", ""), txtTimKiemMaCT.getText(),
						txtTimKiemTenCT.getText(), cboTrangThai.getSelectedItem().toString().trim());
				xoaAllModel();
				docDuLieuVaoModel();
				modelCongTrinh.fireTableDataChanged();
				tableCongTrinh.clearSelection();
			} else if (cboTimKiemTinh_Tp.getSelectedIndex() == 0
					&& (!txtTimKiemMaCT.getText().equals("") || !txtTimKiemTenCT.getText().equals(""))) {
				xoaAllModel();
				listCT = congTrinhDAO.timKiemCongTrinh(new DiaDiem("", "", ""), txtTimKiemMaCT.getText(),
						txtTimKiemTenCT.getText(), cboTrangThai.getSelectedItem().toString().trim());
				docDuLieuVaoModel();
			} else {
				xoaAllModel();
				listCT = congTrinhDAO.phanTrang(1, 7, cboHienThiTrangThai.getSelectedItem().toString());
				docDuLieuVaoModel();
			}

		} else if (o.equals(cboTimKiemQuanHuyen)) {
			String tinh = cboTimKiemTinh_Tp.getSelectedItem().toString();
			String quan = cboTimKiemQuanHuyen.getSelectedItem().toString();
			ArrayList<String> phuong = new DiaDiem_DAO().getPhuong(tinh, quan);
			for (int i = cboTimKiemPhuongXa.getItemCount() - 1; i >= 1; i--) {
				cboTimKiemPhuongXa.removeItemAt(i);
			}
			for (String p : phuong) {
				cboTimKiemPhuongXa.addItem(p);
			}

			if (cboTimKiemQuanHuyen.getSelectedIndex() != 0) {
				xoaAllModel();
				listCT = congTrinhDAO.timKiemCongTrinh(
						new DiaDiem(cboTimKiemTinh_Tp.getSelectedItem().toString(),
								cboTimKiemQuanHuyen.getSelectedItem().toString(), ""),
						txtTimKiemMaCT.getText(), txtTimKiemTenCT.getText(),
						cboTrangThai.getSelectedItem().toString().trim());
				docDuLieuVaoModel();
				modelCongTrinh.fireTableDataChanged();
				tableCongTrinh.clearSelection();
			}

		} else if (o.equals(cboTimKiemPhuongXa)) {
			if (cboTimKiemPhuongXa.getSelectedIndex() > 0) {
				xoaAllModel();
				listCT = congTrinhDAO.timKiemCongTrinh(
						new DiaDiem(cboTimKiemTinh_Tp.getSelectedItem().toString(),
								cboTimKiemQuanHuyen.getSelectedItem().toString(),
								cboTimKiemPhuongXa.getSelectedItem().toString()),
						txtTimKiemMaCT.getText(), txtTimKiemTenCT.getText(),
						cboTrangThai.getSelectedItem().toString().trim());
				modelCongTrinh.fireTableDataChanged();
				docDuLieuVaoModel();
				tableCongTrinh.clearSelection();
			}
		} else if (o.equals(cboHienThiTrangThai)) {
			xoaAllModel();
			listCT = congTrinhDAO.phanTrang(1, 7, cboHienThiTrangThai.getSelectedItem().toString());
			docDuLieuVaoModel();
			txtPage.setText("1");
			xoaRong();
		} else if (o.equals(cboTrangThai)) {
			if (cboTrangThai.getSelectedIndex() == 1) {
				ngayHoanThanh.setEnabled(true);
			} else {
				ngayHoanThanh.setDate(null);
				ngayHoanThanh.setEnabled(false);
			}
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableCongTrinh.getSelectedRow();
		CongTrinh ct = listCT.get(row);

		String ten2 = ct.getTenCongTrinh();
		String loai1 = ct.getLoaiCongTrinh();

		String tinh = ct.getDiaDiem().getTinhTP();
		String huyen = ct.getDiaDiem().getQuanHuyen();
		String xa = ct.getDiaDiem().getPhuongXa();

		String ngayKC[] = modelCongTrinh.getValueAt(row, 4).toString().split("/");
		String ngayDK[] = modelCongTrinh.getValueAt(row, 5).toString().split("/");
		String trangthai = ct.getTrangThai();
		txtTenCongTrinh.setText(ten2);
		cboLoaiCongTrinh.setSelectedItem(loai1);

		cboTinhTP.setSelectedItem(tinh);
		cboQuanHuyen.setSelectedItem(huyen);
		cboPhuongXa.setSelectedItem(xa);

		ngayThiCong.setDate(new Date(Integer.parseInt(ngayKC[2]) - 1900, Integer.parseInt(ngayKC[1]) - 1,
				Integer.parseInt(ngayKC[0])));
		ngayDKHoanThanh.setDate(new Date(Integer.parseInt(ngayDK[2]) - 1900, Integer.parseInt(ngayDK[1]) - 1,
				Integer.parseInt(ngayDK[0])));
		cboTrangThai.setSelectedItem(trangthai);
		cboTrangThai.setEnabled(true);

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

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (txtTimKiemMaCT.getText().equals("") && txtTimKiemTenCT.getText().equals("")) {
			listCT = congTrinhDAO.phanTrang(soDau, soCuoi, cboHienThiTrangThai.getSelectedItem().toString());
		} else {
			String tinh = cboTimKiemTinh_Tp.getSelectedIndex() == 0 ? ""
					: cboTimKiemTinh_Tp.getSelectedItem().toString();
			String huyen = cboTimKiemQuanHuyen.getSelectedIndex() == 0 ? ""
					: cboTimKiemQuanHuyen.getSelectedItem().toString();
			String xa = cboTimKiemPhuongXa.getSelectedIndex() == 0 ? ""
					: cboTimKiemPhuongXa.getSelectedItem().toString();
			listCT = congTrinhDAO.timKiemCongTrinh(new DiaDiem(tinh, huyen, xa), txtTimKiemMaCT.getText(),
					txtTimKiemTenCT.getText(), cboHienThiTrangThai.getSelectedItem().toString().trim());

		}
		xoaAllModel();
		docDuLieuVaoModel();
		tableCongTrinh.clearSelection();

	}

	public String taoMaCT() {
		String maCuoi = congTrinhDAO.getCTCuoi().getMaCongTrinh();
		long so = Long.parseLong(maCuoi.substring(2)) + 100000000 + 1;
		String soMoi = ("" + so).substring(1);
		return "CT" + soMoi;
	}

	@SuppressWarnings("deprecation")
	public Date chuyenNgay(java.util.Date ngay) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String s[] = df.format(ngay).split("/");
		return new Date(Integer.parseInt(s[2]) - 1900, Integer.parseInt(s[1]) - 1, Integer.parseInt(s[0]));
	}
}
