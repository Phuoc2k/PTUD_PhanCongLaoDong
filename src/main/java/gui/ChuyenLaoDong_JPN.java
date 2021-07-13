package gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;

import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietCVDAO;
import dao.ChuyenLD_DAO;
import dao.PhanCongLaoDong_DAO;
import dao.QLCongViec_DAO;
import dao.QuanLyCongTrinh_DAO;
import dao.QuanLyLaoDong_DAO;
import entity.ChiTietCV;
import entity.CongTrinh;
import entity.CongViec;
import entity.LaoDong;
import entity.QuanLy;
import javax.swing.JSeparator;
import java.awt.Cursor;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;

public class ChuyenLaoDong_JPN extends JPanel implements ActionListener, MouseListener, KeyListener {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JTextField btnPage;
	private JTable tbCongTrinh;
	private JTable tbLDChuyen;
	private JTextField txtTimTenCT;
	private JTable tbLDNhan;
	private DefaultTableModel modelCT;
	private DefaultTableModel modelLDChuyen;
	private ArrayList<CongTrinh> listCT = new ArrayList<CongTrinh>();
	private ArrayList<ChiTietCV> listLDChuyen = new ArrayList<ChiTietCV>();
	private ChiTietCVDAO chiTietDao = new ChiTietCVDAO();
	private ArrayList<CongViec> listCV = new QLCongViec_DAO().getCongViec();
	private JComboBox<String> cboCongViecChuyen;
	private JComboBox<String> cboMaChuyen;
	private ChuyenLD_DAO chuyenDao = new ChuyenLD_DAO();
	private int soLuongThem;
	private int soLuongXoa;
	private QuanLyLaoDong_DAO qlLaoDong_DAO = new QuanLyLaoDong_DAO();

	private DefaultTableModel modelLDNhan;
	private JButton btnXoaRong;
	private JDateChooser ngayHoanThanh;
	private JDateChooser ngayThucHien;
	private JButton btnChuyen;
	private List<ChiTietCV> listLDNhan = new ArrayList<ChiTietCV>();
	private JLabel lblChiTietCT;
	private JLabel lblChiTietLD;
	private JLabel lblThem;
	private JLabel lblXoa;
	private JSpinner sLThem;
	private JSpinner sLXoa;
	private JComboBox<String> cboTimKiemMaCT;
	private QuanLy quanLy;
	private int soDau = 1;
	private int soCuoi = 10;
	private JLabel lblTong;
	private int tongSL = 0;
	private JLabel lblTongChuyen;
	private JLabel lblTng_1;
	private boolean ktCbo = true;
	private JProgressBar progressBar;
	private JPanel pnLaoDong;

	public ChuyenLaoDong_JPN(QuanLy ql) {
		setLayout(null);
		quanLy = ql;
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Công trình chuyển",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(0, 0, 477, 593);
		add(panel_5);

		JButton btnDauTien = new JButton("");
		btnDauTien.setFocusPainted(false);
		btnDauTien.setIcon(
				new ImageIcon(ChuyenLaoDong_JPN.class.getResource("/images/baseline_skip_previous_white_24dp.png")));
		btnDauTien.setBackground(Color.BLACK);
		btnDauTien.setBounds(36, 522, 33, 23);
		panel_5.add(btnDauTien);
		btnDauTien.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				denTrangDau();
			}
		});

		JButton btnTruoc = new JButton("");
		btnTruoc.setFocusPainted(false);
		btnTruoc.setIcon(
				new ImageIcon(ChuyenLaoDong_JPN.class.getResource("/images/baseline_fast_rewind_white_24dp.png")));
		btnTruoc.setBackground(Color.BLACK);
		btnTruoc.setBounds(88, 522, 33, 23);
		panel_5.add(btnTruoc);
		btnTruoc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				denTrangTruoc();
			}
		});
		JButton btnSau = new JButton("");
		btnSau.setFocusPainted(false);
		btnSau.setIcon(
				new ImageIcon(ChuyenLaoDong_JPN.class.getResource("/images/baseline_fast_forward_white_24dp.png")));
		btnSau.setBackground(Color.BLACK);
		btnSau.setBounds(178, 522, 33, 23);
		panel_5.add(btnSau);
		btnSau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				denTrangSau();
			}
		});

		JButton btnCuoiCung = new JButton("");
		btnCuoiCung.setFocusPainted(false);
		btnCuoiCung.setIcon(
				new ImageIcon(ChuyenLaoDong_JPN.class.getResource("/images/baseline_skip_next_white_24dp.png")));
		btnCuoiCung.setBackground(Color.BLACK);
		btnCuoiCung.setBounds(221, 522, 33, 23);
		panel_5.add(btnCuoiCung);
		btnCuoiCung.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				denTrangCuoi();
			}
		});

		btnPage = new JTextField();
		btnPage.setText("1");
		btnPage.setHorizontalAlignment(SwingConstants.CENTER);
		btnPage.setEditable(false);
		btnPage.setColumns(10);
		btnPage.setBounds(135, 522, 33, 23);
		panel_5.add(btnPage);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 126, 457, 385);
		panel_5.add(scrollPane);

		modelCT = new DefaultTableModel(new String[] { "Mã công trình", "Tên công trình", "Địa điểm" }, 0) {
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
		tbCongTrinh = new JTable(modelCT);

		scrollPane.setViewportView(tbCongTrinh);

		JLabel lblTmKim = new JLabel("Tìm kiếm:");
		lblTmKim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTmKim.setBounds(26, 17, 85, 23);
		panel_5.add(lblTmKim);

		JLabel lblMCngTrnh = new JLabel("Mã công trình");
		lblMCngTrnh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMCngTrnh.setBounds(26, 44, 108, 14);
		panel_5.add(lblMCngTrnh);

		JLabel lblTnCngTrnh = new JLabel("Tên công trình");
		lblTnCngTrnh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTnCngTrnh.setBounds(26, 82, 108, 14);
		panel_5.add(lblTnCngTrnh);

		txtTimTenCT = new JTextField();
		txtTimTenCT.setColumns(10);
		txtTimTenCT.setBounds(127, 76, 163, 25);
		panel_5.add(txtTimTenCT);

		pnLaoDong = new JPanel();
		pnLaoDong.setLayout(null);
		pnLaoDong.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lao động", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnLaoDong.setBackground(Color.WHITE);
		pnLaoDong.setBounds(476, 0, 468, 593);
		add(pnLaoDong);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(17, 202, 440, 312);
		pnLaoDong.add(scrollPane_1);

		modelLDChuyen = new DefaultTableModel(new String[] { "Mã lao động", "Tên lao động", "Trình độ" }, 0) {
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
		tbLDChuyen = new JTable(modelLDChuyen);

		scrollPane_1.setViewportView(tbLDChuyen);

		JSeparator separator = new JSeparator();
		separator.setBounds(26, 194, 483, -8);
		pnLaoDong.add(separator);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Chuyển", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(943, 0, 377, 589);
		add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 202, 360, 312);
		panel.add(scrollPane_2);

		modelLDNhan = new DefaultTableModel(new String[] { "Mã lao động", "Họ tên", "Trình độ" }, 0) {
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
		tbLDNhan = new JTable(modelLDNhan);
		scrollPane_2.setViewportView(tbLDNhan);

		btnChuyen = new JButton("Chuyển");
		btnChuyen.setFocusPainted(false);
		btnChuyen.setBounds(184, 67, 89, 40);
		panel.add(btnChuyen);
		btnChuyen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnChuyen.setBackground(new Color(228, 237, 225));
		btnChuyen.addActionListener(this);

		btnXoaRong = new JButton("Xóa rỗng");
		btnXoaRong.setFocusPainted(false);
		btnXoaRong.setBounds(28, 67, 89, 40);
		panel.add(btnXoaRong);
		btnXoaRong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnXoaRong.setBackground(new Color(224, 255, 255));

		tbLDNhan.getTableHeader().setBackground(new Color(116, 235, 52));
		tbLDNhan.getTableHeader().setForeground(new Color(31, 39, 191));
		tbLDNhan.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));

		tbCongTrinh.getTableHeader().setBackground(new Color(116, 235, 52));
		tbCongTrinh.getTableHeader().setForeground(new Color(31, 39, 191));
		tbCongTrinh.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));

		tbLDChuyen.getTableHeader().setBackground(new Color(116, 235, 52));
		tbLDChuyen.getTableHeader().setForeground(new Color(31, 39, 191));
		tbLDChuyen.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));

		tbCongTrinh.setRowHeight(tbCongTrinh.getRowHeight() + 20);

		lblChiTietCT = new JLabel("");
		lblChiTietCT.setBackground(Color.WHITE);
		lblChiTietCT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblChiTietCT.setIcon(new ImageIcon(ChuyenLaoDong_JPN.class.getResource("/images/chi_tiet.png")));
		lblChiTietCT.setBounds(435, 522, 32, 32);
		panel_5.add(lblChiTietCT);

		cboTimKiemMaCT = new JComboBox<String>();
		cboTimKiemMaCT.setBounds(127, 40, 163, 25);
		panel_5.add(cboTimKiemMaCT);
		tbLDChuyen.setRowHeight(tbLDChuyen.getRowHeight() + 20);
		cboTimKiemMaCT.addItem("Tất cả");

		lblChiTietLD = new JLabel("");
		lblChiTietLD.setBackground(Color.WHITE);
		lblChiTietLD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblChiTietLD.setIcon(new ImageIcon(ChuyenLaoDong_JPN.class.getResource("/images/chi_tiet.png")));
		lblChiTietLD.setBounds(425, 518, 32, 32);
		pnLaoDong.add(lblChiTietLD);

		lblThem = new JLabel("");
		lblThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblThem.setIcon(new ImageIcon(ChuyenLaoDong_JPN.class.getResource("/images/right-arrow.png")));
		lblThem.setBounds(425, 166, 32, 32);
		pnLaoDong.add(lblThem);

		sLThem = new JSpinner();
		sLThem.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		sLThem.setBounds(346, 166, 69, 29);
		sLThem.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		pnLaoDong.add(sLThem);

		cboMaChuyen = new JComboBox<String>();
		cboMaChuyen.setBounds(141, 38, 195, 25);
		pnLaoDong.add(cboMaChuyen);

		JLabel lblMCngTrnh_2 = new JLabel("Mã công trình:");
		lblMCngTrnh_2.setBounds(25, 40, 98, 24);
		pnLaoDong.add(lblMCngTrnh_2);
		lblMCngTrnh_2.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblCngVic_1 = new JLabel("Công việc:");
		lblCngVic_1.setBounds(25, 83, 86, 24);
		pnLaoDong.add(lblCngVic_1);
		lblCngVic_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		cboCongViecChuyen = new JComboBox<String>();
		cboCongViecChuyen.setBounds(141, 79, 195, 25);
		pnLaoDong.add(cboCongViecChuyen);
		cboCongViecChuyen.addItem("");
		for (CongViec congViec : listCV) {
			cboCongViecChuyen.addItem(congViec.getTenCongViec());
		}

		JLabel lblCngVic_1_1_1 = new JLabel("Ngày thực hiện:");
		lblCngVic_1_1_1.setBounds(24, 122, 106, 24);
		pnLaoDong.add(lblCngVic_1_1_1);
		lblCngVic_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		ngayHoanThanh = new JDateChooser();
		ngayHoanThanh.setBounds(141, 166, 195, 25);
		pnLaoDong.add(ngayHoanThanh);
		ngayHoanThanh.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if ("date".equals(e.getPropertyName())) {
					Date ngayHT = ngayHoanThanh.getDate();
					Date ngayTH = ngayThucHien.getDate();
					if (ngayHT != null && ngayTH != null && ngayHT.after(ngayTH))
						xoaTrungLich(ngayTH, ngayHT);
				}
			}
		});

		ngayHoanThanh.setDateFormatString("dd/MM/yyyy");

		JLabel lblCngVic_1_1 = new JLabel("Ngày hoàn thành:");
		lblCngVic_1_1.setBounds(25, 166, 106, 24);
		pnLaoDong.add(lblCngVic_1_1);
		lblCngVic_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		ngayThucHien = new JDateChooser();
		ngayThucHien.setBounds(141, 122, 195, 25);
		pnLaoDong.add(ngayThucHien);
		ngayThucHien.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if ("date".equals(e.getPropertyName())) {
					Date ngayHT = ngayHoanThanh.getDate();
					Date ngayTH = ngayThucHien.getDate();

					if (ngayHT != null && ngayTH != null && ngayHT.after(ngayTH))
						xoaTrungLich(ngayTH, ngayHT);

				}

			}
		});
		ngayThucHien.setDateFormatString("dd/MM/yyyy");

		lblTongChuyen = new JLabel("0");
		lblTongChuyen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongChuyen.setBounds(66, 527, 46, 23);
		pnLaoDong.add(lblTongChuyen);

		lblTng_1 = new JLabel("Tổng:");
		lblTng_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTng_1.setBounds(17, 527, 49, 23);
		pnLaoDong.add(lblTng_1);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(106, 525, 280, 24);

		tbLDNhan.setRowHeight(tbLDNhan.getRowHeight() + 20);
		sLThem.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				soLuongThem = (int) ((JSpinner) e.getSource()).getValue();
			}
		});

		lblXoa = new JLabel("");
		lblXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblXoa.setIcon(new ImageIcon(ChuyenLaoDong_JPN.class.getResource("/images/left-arrow.png")));
		lblXoa.setBounds(10, 166, 32, 32);
		panel.add(lblXoa);

		sLXoa = new JSpinner();
		sLXoa.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		sLXoa.setBounds(46, 166, 69, 29);
		sLXoa.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		panel.add(sLXoa);

		JLabel lblTng = new JLabel("Tổng:");
		lblTng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTng.setHorizontalAlignment(SwingConstants.CENTER);
		lblTng.setBounds(247, 166, 49, 29);
		panel.add(lblTng);

		lblTong = new JLabel("0");
		lblTong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTong.setBounds(306, 166, 61, 29);
		panel.add(lblTong);
		sLXoa.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				soLuongXoa = (int) ((JSpinner) e.getSource()).getValue();
			}
		});

		listCT = chiTietDao.phanTrang(1, 10);
		docDuLieuCongTrinhVaoBang();

		for (CongTrinh ct : listCT) {
			cboTimKiemMaCT.addItem(ct.getMaCongTrinh());
		}
		cboTimKiemMaCT.setSelectedIndex(0);
		tbCongTrinh.addMouseListener(this);
		btnXoaRong.addActionListener(this);
		lblChiTietCT.addMouseListener(this);
		lblChiTietLD.addMouseListener(this);
		lblThem.addMouseListener(this);
		lblXoa.addMouseListener(this);
		txtTimTenCT.addKeyListener(this);
		cboCongViecChuyen.addActionListener(this);
		cboMaChuyen.addActionListener(this);
		cboTimKiemMaCT.addActionListener(this);
		

		JComponent comp0 = sLThem.getEditor();
		JFormattedTextField field0 = (JFormattedTextField) comp0.getComponent(0);
		DefaultFormatter formatter0 = (DefaultFormatter) field0.getFormatter();
		formatter0.setCommitsOnValidEdit(true);

		JComponent comp1 = sLXoa.getEditor();
		JFormattedTextField field1 = (JFormattedTextField) comp1.getComponent(0);
		DefaultFormatter formatter1 = (DefaultFormatter) field1.getFormatter();
		formatter1.setCommitsOnValidEdit(true);
	}

	/**
	 * 	
	 */
	public void docDuLieuCongTrinhVaoBang() {
		for (CongTrinh ct : listCT) {
			modelCT.addRow(new Object[] { ct.getMaCongTrinh(), ct.getTenCongTrinh(), ct.getDiaDiem() });
		}
	}

	public void docDLVaoBangLDNhan(LaoDong ld) {
		modelLDNhan.addRow(new Object[] { ld.getMaLaoDong(), ld.getTenLaoDong(), ld.getTrinhDo() });
	}

	/**
	 * 
	 */
	public void docDuLieuLaoDongVaoBang() {
		new Thread(() -> {
			for (ChiTietCV ct : listLDChuyen) {
				String maLD = ct.getLaoDong().getMaLaoDong();
				LaoDong laoDong = qlLaoDong_DAO.getLaoDong(maLD);
				modelLDChuyen
						.addRow(new Object[] { maLD, laoDong.getTenLaoDong(), laoDong.getTrinhDo().getTenTrinhDo(), });
			}
		}).start();

	}

	public void xoaAllModelLDChuyen() {
		DefaultTableModel m = (DefaultTableModel) tbLDChuyen.getModel();
		m.getDataVector().removeAllElements();
	}

	public void xoaAllModelCT() {
		DefaultTableModel m = (DefaultTableModel) tbCongTrinh.getModel();
		m.getDataVector().removeAllElements();
	}

	public void xoaAllModelLDNhan() {
		DefaultTableModel m = (DefaultTableModel) tbLDNhan.getModel();
		m.getDataVector().removeAllElements();
	}

	/**
	 * Hàm di chuyển đến trang đầu của bảng
	 */
	private void denTrangDau() {
		listCT = chiTietDao.phanTrang(1, 10);
		xoaAllModelCT();
		docDuLieuCongTrinhVaoBang();
		tbCongTrinh.setRowSelectionInterval(0, 0);
		btnPage.setText("1");
	}

	/**
	 * Hàm di chuyển đến trang cuối của bảng
	 */
	private void denTrangCuoi() {

		int slDb = chiTietDao.demSluongDuLieuTrongDB();
		int trangLonNhat;
		if (slDb % 10 == 0) {
			trangLonNhat = slDb / 10;
		} else {
			trangLonNhat = slDb / 10 + 1;
		}
		soDau = 9 * (trangLonNhat - 1) + 1;
		soCuoi = soDau + 9;
		xoaAllModelCT();
		listCT = chiTietDao.phanTrang(soDau, soCuoi);
		xoaAllModelCT();
		docDuLieuCongTrinhVaoBang();
		tbCongTrinh.setRowSelectionInterval(0, 0);
		btnPage.setText(String.valueOf(trangLonNhat));

	}

	/**
	 * Hàm di chuyển đến trang thứ i-1 của bảng
	 */
	private void denTrangTruoc() {

		int trang = Integer.parseInt(btnPage.getText());
		if (trang > 1) {
			btnPage.setText(String.valueOf(trang - 1));
			soDau = 10 * (trang - 2) + 1;
			soCuoi = soDau + 9;
			xoaAllModelCT();
			listCT = chiTietDao.phanTrang(soDau, soCuoi);
			xoaAllModelCT();
			docDuLieuCongTrinhVaoBang();
			tbCongTrinh.setRowSelectionInterval(0, 0);
		}
	}

	/**
	 * Hàm di chuyển đến trang thứ i+1 của bảng
	 */
	private void denTrangSau() {

		int slDb = chiTietDao.demSluongDuLieuTrongDB();
		int trangLonNhat;
		int trang = Integer.parseInt(btnPage.getText());
		if (slDb % 10 == 0) {
			trangLonNhat = slDb / 10;
		} else {
			trangLonNhat = slDb / 10 + 1;
		}
		if (trang < trangLonNhat) {
			btnPage.setText(String.valueOf(trang + 1));
			soDau = 10 * (trang) + 1;
			soCuoi = soDau + 9;
			xoaAllModelCT();
			listCT = chiTietDao.phanTrang(soDau, soCuoi);
			docDuLieuCongTrinhVaoBang();
			tbCongTrinh.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tbCongTrinh)) {
			new Thread(() -> {
				ngayHoanThanh.setDate(null);
				ngayThucHien.setDate(null);
				ktCbo = false;
				cboCongViecChuyen.setSelectedIndex(0);
				ktCbo = true;
				xoaAllModelLDNhan();
				modelLDNhan.fireTableDataChanged();
				int row = tbCongTrinh.getSelectedRow();
				String maCT = modelCT.getValueAt(row, 0).toString().trim();
				listLDChuyen = chiTietDao.getChiTietTheoMaCT(maCT);

				xoaAllModelLDChuyen();
				docDuLieuLaoDongVaoBang();
				cboMaChuyen.removeAllItems();
				chiTietDao.getCongTrinhDangLam(maCT);
				for (CongTrinh congTrinh : listCT) {
					if (!congTrinh.getMaCongTrinh().equals(maCT))
						cboMaChuyen.addItem(congTrinh.getMaCongTrinh());
				}

				lblTongChuyen.setText(listLDChuyen.size() + "");
			}).start();

		} else if (o.equals(lblChiTietCT)) {
			int row = tbCongTrinh.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công trình");
				return;
			}
			new ChiTietCongTrinh(listCT.get(row).getMaCongTrinh()).setVisible(true);

		} else if (o.equals(lblChiTietLD)) {
			int row = tbLDChuyen.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn lao động");
				return;
			}
			
			new ChiTietLaoDong(listLDChuyen.get(row).getLaoDong().getMaLaoDong()).setVisible(true);
		} else if (o.equals(lblThem)) {

			if (validata()) {
				if (soLuongThem > 0) {
					if (soLuongThem < modelLDChuyen.getRowCount() + 1) {
						if (cboCongViecChuyen.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Vui lòng chọn công việc");
							return;
						} else {
							for (int i = 0; i < soLuongThem; i++) {
								listLDNhan.add(listLDChuyen.get(0));
								LaoDong laoDong = new QuanLyLaoDong_DAO()
										.getLaoDong(listLDChuyen.get(0).getLaoDong().getMaLaoDong());
								docDLVaoBangLDNhan(laoDong);
								modelLDChuyen.removeRow(0);
								listLDChuyen.remove(0);
								tongSL += 1;
							}
							lblTong.setText("" + tongSL);
							sLThem.setValue(0);

							lblTongChuyen.setText(listLDChuyen.size() + "");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Không đủ lao động");
					}
				} else {
					int row = tbLDChuyen.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn lao động");
						return;
					} else if (cboCongViecChuyen.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn công việc");
						return;
					} else {
						listLDNhan = new ArrayList<ChiTietCV>();
						listLDNhan.add(listLDChuyen.get(row));
						LaoDong laoDong = new QuanLyLaoDong_DAO()
								.getLaoDong(listLDChuyen.get(row).getLaoDong().getMaLaoDong());

						docDLVaoBangLDNhan(laoDong);
						listLDChuyen.remove(row);
						modelLDChuyen.removeRow(row);
						tongSL += 1;
						lblTong.setText("" + tongSL);
					}
					lblTongChuyen.setText(listLDChuyen.size() + "");
				}

			}
		} else if (o.equals(lblXoa)) {

			if (soLuongXoa > 0) {
				if (soLuongXoa < modelLDNhan.getRowCount() + 1) {
					for (int i = 0; i < soLuongXoa; i++) {
						ChiTietCV cTCV = listLDNhan.get(0);
						LaoDong laoDong = new QuanLyLaoDong_DAO().getLaoDong(cTCV.getLaoDong().getMaLaoDong());
						CongViec congViec = new QLCongViec_DAO().getCongViec(cTCV.getCongViec().getMaCongViec());
						modelLDChuyen.addRow(new Object[] { laoDong.getMaLaoDong(), laoDong.getTenLaoDong(),
								laoDong.getTrinhDo(), congViec.getTenCongViec() });
						listLDChuyen.add(listLDNhan.get(0));
						listLDNhan.remove(0);
						xoaAllModelLDNhan();
						for (ChiTietCV ct : listLDNhan) {
							LaoDong ld = new QuanLyLaoDong_DAO().getLaoDong(ct.getLaoDong().getMaLaoDong());
							docDLVaoBangLDNhan(ld);
							tbLDNhan.clearSelection();
							
						}
						tongSL -= 1;
						lblTong.setText("" + tongSL);

					}
					modelLDNhan.fireTableDataChanged();
					sLXoa.setValue(0);
					lblTongChuyen.setText(listLDChuyen.size() + "");
				} else {
					JOptionPane.showMessageDialog(null, "Quá số lao động hiện có!");
				}
			} else {
				int row = tbLDNhan.getSelectedRow();
				if (row >= 0) {
					ChiTietCV cTCV = listLDNhan.get(row);
					LaoDong laoDong = new QuanLyLaoDong_DAO().getLaoDong(cTCV.getLaoDong().getMaLaoDong());
					CongViec congViec = new QLCongViec_DAO().getCongViec(cTCV.getCongViec().getMaCongViec());
					modelLDChuyen.addRow(new Object[] { laoDong.getMaLaoDong(), laoDong.getTenLaoDong(),
							laoDong.getTrinhDo(), congViec.getTenCongViec() });
					listLDChuyen.add(listLDNhan.get(row));
					listLDNhan.remove(row);
					modelLDNhan.removeRow(row);
					tongSL -= 1;
					lblTong.setText("" + tongSL);
					lblTongChuyen.setText(listLDChuyen.size() + "");
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn lao động");
				}
			}

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
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
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnXoaRong)) {
			xoaRong();
		} else if (o.equals(btnChuyen)) {
			if(listLDNhan.size()==0) {
				JOptionPane.showMessageDialog(null, "Chưa có lao động để chuyển");
				return;
			}
			java.sql.Date ngayTH = chuyenNgay(ngayThucHien.getDate());
			java.sql.Date ngayHT = chuyenNgay(ngayHoanThanh.getDate());

			if (modelLDNhan.getRowCount() >= 0) {
				int t = JOptionPane.showConfirmDialog(null, "Bạn có muốn chuyển", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if (t == JOptionPane.YES_OPTION) {

					for (int i = 0; i < tbLDNhan.getRowCount(); i++) {
						PhanCongLaoDong_DAO pcDao = new PhanCongLaoDong_DAO();
						QuanLyLaoDong_DAO ldDao = new QuanLyLaoDong_DAO();

						LaoDong ld = ldDao.getLaoDong(modelLDNhan.getValueAt(i, 0).toString());
						CongViec congViec = listCV.get(cboCongViecChuyen.getSelectedIndex() - 1);

						pcDao.phanCong(new LaoDong(ld.getMaLaoDong()),
								new CongTrinh(cboMaChuyen.getSelectedItem().toString()),
								new CongViec(congViec.getMaCongViec()), ngayTH, ngayHT, quanLy, false);
						ChuyenLD_DAO cLDDao = new ChuyenLD_DAO();
						cLDDao.updateTrangThai(modelLDNhan.getValueAt(i, 0).toString(),
								modelCT.getValueAt(tbCongTrinh.getSelectedRow(), 0).toString(),
								listLDNhan.get(i).getCongViec().getMaCongViec(), true);

					}
					xoaAllModelLDNhan();
					xoaAllModelLDChuyen();
					tbCongTrinh.clearSelection();
					modelLDChuyen.fireTableDataChanged();
					modelLDNhan.fireTableDataChanged();
					JOptionPane.showMessageDialog(null, "Chuyển thành công");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Chưa có lao động để chuyển");
			}

		} else if (o.equals(cboCongViecChuyen) && ktCbo) {
			xoaAllModelLDChuyen();
			modelLDChuyen.fireTableDataChanged();
			int row = tbCongTrinh.getSelectedRow();
			if (row != -1) {
				String maCT = listCT.get(row).getMaCongTrinh();
				if (cboCongViecChuyen.getSelectedIndex() == 0)
					listLDChuyen = chiTietDao.getChiTietTheoMaCT(maCT);
				else {
					String maCV = listCV.get(cboCongViecChuyen.getSelectedIndex() - 1).getMaCongViec();
					listLDChuyen = chuyenDao.getChiTietTheoCV(maCT, maCV);
				}
			}else
				listLDChuyen = new ArrayList<ChiTietCV>();
			if(ngayThucHien.getDate()!=null&&ngayHoanThanh.getDate()!=null && ngayHoanThanh.getDate().after(ngayThucHien.getDate()))
				xoaTrungLich(ngayThucHien.getDate(), ngayHoanThanh.getDate());
			else {
				lblTongChuyen.setText(listLDChuyen.size() + "");
				docDuLieuLaoDongVaoBang();
			}
			
		}

		else if (o.equals(cboTimKiemMaCT)) {
			if (cboTimKiemMaCT.getSelectedIndex() == 0) {
				listCT = chiTietDao.phanTrang(soDau, soCuoi);
			} else {
				CongTrinh ct = new QuanLyCongTrinh_DAO().getCTTheoMa(cboTimKiemMaCT.getSelectedItem().toString());
				listCT = new ArrayList<CongTrinh>();
				listCT.add(ct);
			}
			xoaAllModelCT();
			docDuLieuCongTrinhVaoBang();

		}
	}

	public boolean validata() {
		if(tbCongTrinh.getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn công trình");
			return false;
		}
		
		if (cboCongViecChuyen.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn công việc");
			return false;
		}
		if (ngayThucHien.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày thực hiện");
			return false;
		}

		if (ngayHoanThanh.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày hoàn thành");
			return false;
		}
		
		if(ngayThucHien.getDate().before(new Date())) {
			JOptionPane.showMessageDialog(null, "Ngày thực hiện phải sau ngày hiện tại");
			return false;
		}
		
		if(ngayThucHien.getDate().after(ngayHoanThanh.getDate())) {
			JOptionPane.showMessageDialog(null, "Ngày thực hiện phải trước ngày hoàn thành");
			return false;
		}

		return true;
	}

	public void xoaTrungLich(Date ngayTH, Date ngayHT) {
		java.sql.Date th = chuyenNgay(ngayTH);
		java.sql.Date ht = chuyenNgay(ngayHT);
		new Thread(() -> {
			progressBar.setMaximum(listLDChuyen.size());
			pnLaoDong.add(progressBar);
			int dem = 0;
			for (int i = listLDChuyen.size() - 1; i >= 0; i--) {
				dem++;
				progressBar.setValue(dem);
				ChiTietCV chiTiet = listLDChuyen.get(i);
				boolean check = true; // true là xóa

				ArrayList<ChiTietCV> listCV = new PhanCongLaoDong_DAO()
						.getChiTietCVTheoMaLD(chiTiet.getLaoDong().getMaLaoDong());
				listCV.remove(chiTiet);
				if (listCV.size() > 0) {
					ChiTietCV first = listCV.get(0);
					if (listCV.size() == 1) {
						if (th.after(first.getNgayThucHien()) && th.after(first.getNgayHoanThanh())
								&& ht.after(first.getNgayThucHien()) && ht.after(first.getNgayHoanThanh())
								|| th.before(first.getNgayThucHien()) && th.before(first.getNgayHoanThanh())
										&& ht.before(first.getNgayThucHien()) && ht.before(first.getNgayHoanThanh()))
							check = false;
					} else {
						for (int j = 0; j < listCV.size() - 2; j++) {
							if (th.after(listCV.get(j).getNgayHoanThanh())
									&& ht.before(listCV.get(j + 1).getNgayThucHien())) {
								check = false;
								break;
							}
						}

						if (ht.before(first.getNgayThucHien()) && ht.before(first.getNgayHoanThanh())) {
							check = false;
						}

						ChiTietCV last = listCV.get(listCV.size() - 1);
						if (th.after(last.getNgayHoanThanh()) && th.after(last.getNgayThucHien())) {
							check = false;
						}
					}
				}else
					check = false;
				if (check) {
					listLDChuyen.remove(chiTiet);
				}
			}
			xoaAllModelLDChuyen();
			modelLDChuyen.fireTableDataChanged();
			docDuLieuLaoDongVaoBang();
			tbLDChuyen.clearSelection();
			lblTongChuyen.setText(listLDChuyen.size() + "");
			
			Container p = progressBar.getParent();
			p.remove(progressBar);
			p.repaint();
			p.validate();
		}).start();
	}

	@SuppressWarnings("deprecation")
	public java.sql.Date chuyenNgay(Date ngay) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String s[] = df.format(ngay).split("/");
		return new java.sql.Date(Integer.parseInt(s[2]) - 1900, Integer.parseInt(s[1]) - 1, Integer.parseInt(s[0]));
	}

	public void xoaRong() {
		xoaAllModelLDChuyen();
		xoaAllModelLDNhan();
		modelLDChuyen.fireTableDataChanged();
		modelLDNhan.fireTableDataChanged();
		cboMaChuyen.removeAllItems();
		ktCbo = false;
		cboCongViecChuyen.setSelectedIndex(0);
		ktCbo = true;
		ngayThucHien.setDate(null);
		ngayHoanThanh.setDate(null);
		tbCongTrinh.clearSelection();
		lblTongChuyen.setText("0");
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
		Object o = e.getSource();
		if (o.equals(txtTimTenCT) && cboTimKiemMaCT.getSelectedIndex() == 0) {
			if (txtTimTenCT.getText().equals("")) {
				listCT = chiTietDao.phanTrang(soDau, soCuoi);
				xoaAllModelCT();
				docDuLieuCongTrinhVaoBang();
			} else {
				listCT = chiTietDao.timCTDangLamTheoTen(txtTimTenCT.getText());
				xoaAllModelCT();
				docDuLieuCongTrinhVaoBang();
			}
		}
	}
}
