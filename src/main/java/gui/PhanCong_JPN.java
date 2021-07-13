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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;

import com.toedter.calendar.JDateChooser;

import dao.PhanCongLaoDong_DAO;
import dao.QLCongViec_DAO;
import dao.QuanLyCongTrinh_DAO;
import entity.ChiTietCV;
import entity.CongTrinh;
import entity.CongViec;
import entity.DiaDiem;
import entity.LaoDong;
import entity.QuanLy;
import entity.TrinhDo;

import javax.swing.JSeparator;
import java.awt.Cursor;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JProgressBar;

public class PhanCong_JPN extends JPanel implements ActionListener, ItemListener, MouseListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPageCT;
	private JTable tbLaoDong;
	private DefaultTableModel modelCT;
	private JTextField txtTimTenCT;
	private JTextField txtTimTenLD;
	private JTextField txtMaCT;
	private JTextField txtSoLuong;
	private JTable tbPhanCong;

	private DefaultTableModel modelLD;
	private DefaultTableModel modelPC;
	private JTable tbCongTrinh;
	private QuanLyCongTrinh_DAO congTrinhDAO = new QuanLyCongTrinh_DAO();
	private ArrayList<CongTrinh> listCT = congTrinhDAO.phanTrang(1, 8, "Chưa hoàn thành");
	private ArrayList<LaoDong> listPhanCong = new ArrayList<LaoDong>();
	private JComboBox<String> cboCongViec;
	private JComboBox<String> cboCongViecCT;
	private PhanCongLaoDong_DAO phanCongDAO = new PhanCongLaoDong_DAO();
	private ArrayList<LaoDong> listLD = phanCongDAO.getLaoDongTheoCV("CV001");
	private ArrayList<LaoDong> listTemp = listLD;
	private JButton btnPhanCong;
	private JButton btnLamMoi;
	private JDateChooser ngayThucHien;
	private JDateChooser ngayHoanThanh;
	private ArrayList<CongViec> listCV = new QLCongViec_DAO().getCongViec();
	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private JLabel lblChiTietCT;
	private JLabel lblChiTietLD;
	private JLabel lblThem;
	private JLabel lblXoa;
	private JSpinner soLuongThem;
	private int sLThem;
	private JSpinner soLuongXoa;
	private int sLXoa;
	private JComboBox<String> cboTimMaCT;
	private JComboBox<String> cboTimMaLD;
	private int soDau = 1;
	private int soCuoi = 8;
	private boolean kiemTraCbo = true;
	private JLabel lblTongNhan;
	private int tongSL = 0;
	private JLabel lblTongChuyen;
	private QuanLy quanLy;
	private JProgressBar progressBar;
	private JPanel pnLaoDong;

	/**
	 * Create the panel.
	 */
	public PhanCong_JPN(QuanLy ql) {
		this.quanLy = ql;
		setLayout(null);
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Công trình", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 0, 416, 587);
		add(panel_3);

		JButton btnCTTop = new JButton("");
		btnCTTop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCTTop.setFocusPainted(false);
		btnCTTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				denTrangDauCT();
			}
		});
		btnCTTop.setIcon(new ImageIcon(PhanCong_JPN.class.getResource("/images/baseline_fast_rewind_white_24dp.png")));
		btnCTTop.setBackground(Color.BLACK);
		btnCTTop.setBounds(10, 521, 33, 23);
		panel_3.add(btnCTTop);

		JButton btnCTLeft = new JButton("");
		btnCTLeft.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCTLeft.setFocusPainted(false);
		btnCTLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				denTrangTruocCT();
			}
		});
		btnCTLeft.setIcon(
				new ImageIcon(PhanCong_JPN.class.getResource("/images/baseline_skip_previous_white_24dp.png")));
		btnCTLeft.setBackground(Color.BLACK);
		btnCTLeft.setBounds(62, 521, 33, 23);
		panel_3.add(btnCTLeft);

		JButton btnCTRight = new JButton("");
		btnCTRight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCTRight.setFocusPainted(false);
		btnCTRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				denTrangSauCT();
			}
		});
		btnCTRight.setIcon(new ImageIcon(PhanCong_JPN.class.getResource("/images/baseline_skip_next_white_24dp.png")));
		btnCTRight.setBackground(Color.BLACK);
		btnCTRight.setBounds(152, 521, 33, 23);
		panel_3.add(btnCTRight);

		JButton btnCTBottom = new JButton("");
		btnCTBottom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCTBottom.setFocusPainted(false);
		btnCTBottom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				denTrangCuoiCT();
			}
		});
		btnCTBottom
				.setIcon(new ImageIcon(PhanCong_JPN.class.getResource("/images/baseline_fast_forward_white_24dp.png")));
		btnCTBottom.setBackground(Color.BLACK);
		btnCTBottom.setBounds(195, 521, 33, 23);
		panel_3.add(btnCTBottom);

		txtPageCT = new JTextField();
		txtPageCT.setText("1");
		txtPageCT.setHorizontalAlignment(SwingConstants.CENTER);
		txtPageCT.setEditable(false);
		txtPageCT.setColumns(10);
		txtPageCT.setBounds(109, 521, 33, 23);
		panel_3.add(txtPageCT);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 196, 397, 310);
		panel_3.add(scrollPane);

		modelCT = new DefaultTableModel(new String[] { "Tên công trình", "Ngày KC" }, 0) {

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

		tbCongTrinh.getColumnModel().getColumn(0).setPreferredWidth(250);

		txtTimTenCT = new JTextField();
		txtTimTenCT.setColumns(10);
		txtTimTenCT.setBounds(111, 167, 296, 22);
		panel_3.add(txtTimTenCT);

		JLabel lblTnCngTrnh = new JLabel("Tên công trình");
		lblTnCngTrnh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTnCngTrnh.setBounds(10, 173, 108, 14);
		panel_3.add(lblTnCngTrnh);

		JLabel lblMCngTrnh = new JLabel("Mã công trình");
		lblMCngTrnh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMCngTrnh.setBounds(10, 142, 108, 14);
		panel_3.add(lblMCngTrnh);

		JLabel lblTmKim = new JLabel("Tìm kiếm:");
		lblTmKim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTmKim.setBounds(10, 115, 85, 23);
		panel_3.add(lblTmKim);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(18, 115, 389, 2);
		panel_3.add(separator_1);

		cboCongViecCT = new JComboBox<String>();
		cboCongViecCT.setBounds(10, 84, 103, 22);
		panel_3.add(cboCongViecCT);

		for (CongViec c : listCV) {
			cboCongViecCT.addItem(c.getTenCongViec());
		}

		JLabel lblMCngTrnh_1 = new JLabel("Mã công trình:");
		lblMCngTrnh_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMCngTrnh_1.setBounds(10, 53, 103, 20);
		panel_3.add(lblMCngTrnh_1);

		txtMaCT = new JTextField();
		txtMaCT.setEditable(false);
		txtMaCT.setColumns(10);
		txtMaCT.setBounds(111, 51, 182, 22);
		panel_3.add(txtMaCT);

		txtSoLuong = new JTextField();
		txtSoLuong.setEditable(false);
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(207, 85, 86, 22);
		panel_3.add(txtSoLuong);

		JLabel lblSLung = new JLabel("Số lượng");
		lblSLung.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSLung.setBounds(137, 87, 60, 21);
		panel_3.add(lblSLung);

		JLabel lblSLngLao = new JLabel("Số lượng lao động đã phân công:");
		lblSLngLao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSLngLao.setBounds(10, 26, 218, 14);
		panel_3.add(lblSLngLao);

		pnLaoDong = new JPanel();
		pnLaoDong.setLayout(null);
		pnLaoDong.setBorder(new TitledBorder(null, "Lao động", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnLaoDong.setBackground(Color.WHITE);
		pnLaoDong.setBounds(414, 0, 460, 587);
		add(pnLaoDong);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(11, 195, 440, 310);
		pnLaoDong.add(scrollPane_1);

		modelLD = new DefaultTableModel(new String[] { "Mã lao động", "Tên lao động", "Trình độ" }, 0) {
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
		tbLaoDong = new JTable(modelLD);
		scrollPane_1.setViewportView(tbLaoDong);
		tbLaoDong.getColumnModel().getColumn(0).setPreferredWidth(70);
		tbLaoDong.getColumnModel().getColumn(1).setPreferredWidth(200);
		tbLaoDong.getColumnModel().getColumn(2).setPreferredWidth(80);

		JLabel lblMLaong = new JLabel("Mã lao động");
		lblMLaong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMLaong.setBounds(20, 140, 108, 14);
		pnLaoDong.add(lblMLaong);

		JLabel lblTnLaong = new JLabel("Tên lao động");
		lblTnLaong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTnLaong.setBounds(20, 170, 108, 14);
		pnLaoDong.add(lblTnLaong);

		txtTimTenLD = new JTextField();
		txtTimTenLD.setColumns(10);
		txtTimTenLD.setBounds(138, 164, 183, 22);
		pnLaoDong.add(txtTimTenLD);

		JLabel lblTmKim_1 = new JLabel("Tìm kiếm:");
		lblTmKim_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTmKim_1.setBounds(21, 116, 85, 23);
		pnLaoDong.add(lblTmKim_1);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Phân công", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(868, 0, 442, 587);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 195, 422, 312);
		panel.add(scrollPane_2);

		modelPC = new DefaultTableModel(new String[] { "Mã lao động", "Họ tên" }, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tbPhanCong = new JTable(modelPC);
		scrollPane_2.setViewportView(tbPhanCong);

		cboCongViec = new JComboBox<String>();
		cboCongViec.setBounds(138, 18, 183, 22);
		pnLaoDong.add(cboCongViec);
		cboCongViec.addItem("");
		for (CongViec congViec : listCV) {
			cboCongViec.addItem(congViec.getTenCongViec());
		}

		btnPhanCong = new JButton("Phân công");
		btnPhanCong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPhanCong.setFocusPainted(false);
		btnPhanCong.setBounds(140, 88, 105, 33);
		panel.add(btnPhanCong);
		btnPhanCong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPhanCong.setBackground(Color.ORANGE);

		tbCongTrinh.getTableHeader().setBackground(new Color(116, 235, 52));
		tbCongTrinh.getTableHeader().setForeground(new Color(31, 39, 191));
		tbCongTrinh.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		tbCongTrinh.setRowHeight(tbCongTrinh.getRowHeight() + 20);

		lblChiTietCT = new JLabel("");
		lblChiTietCT.setBackground(Color.WHITE);
		lblChiTietCT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblChiTietCT.setToolTipText("Xem chi tiết");
		lblChiTietCT.setIcon(new ImageIcon(PhanCong_JPN.class.getResource("/images/chi_tiet.png")));
		lblChiTietCT.setBounds(362, 515, 33, 32);
		lblChiTietCT.setOpaque(true);
		panel_3.add(lblChiTietCT);

		cboTimMaCT = new JComboBox<String>();
		cboTimMaCT.setBounds(109, 138, 163, 22);
		panel_3.add(cboTimMaCT);
		cboTimMaCT.addItem("Tất cả");
		ArrayList<CongTrinh> lCongTring = new QuanLyCongTrinh_DAO().getAllCongTrinh();
		for (CongTrinh congTrinh : lCongTring) {
			cboTimMaCT.addItem(congTrinh.getMaCongTrinh());
		}

		tbLaoDong.getTableHeader().setBackground(new Color(116, 235, 52));
		tbLaoDong.getTableHeader().setForeground(new Color(31, 39, 191));
		tbLaoDong.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		tbLaoDong.setRowHeight(tbLaoDong.getRowHeight() + 20);

		lblChiTietLD = new JLabel("");
		lblChiTietLD.setBackground(Color.WHITE);
		lblChiTietLD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblChiTietLD.setIcon(new ImageIcon(PhanCong_JPN.class.getResource("/images/chi_tiet.png")));
		lblChiTietLD.setToolTipText("Xem chi tiết");
		lblChiTietLD.setBounds(408, 518, 33, 32);
		lblChiTietLD.setOpaque(true);
		pnLaoDong.add(lblChiTietLD);

		lblThem = new JLabel("");
		lblThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblThem.setBackground(Color.WHITE);
		lblThem.setIcon(new ImageIcon(PhanCong_JPN.class.getResource("/images/right-arrow.png")));
		lblThem.setBounds(413, 155, 38, 39);
		pnLaoDong.add(lblThem);

		soLuongThem = new JSpinner();
		soLuongThem.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		soLuongThem.setBounds(331, 159, 78, 29);
		pnLaoDong.add(soLuongThem);

		cboTimMaLD = new JComboBox<String>();
		cboTimMaLD.setBounds(138, 137, 183, 22);
		pnLaoDong.add(cboTimMaLD);

		JLabel lblNgyThcHin = new JLabel("Ngày thực hiện:");
		lblNgyThcHin.setBounds(11, 48, 105, 22);
		pnLaoDong.add(lblNgyThcHin);
		lblNgyThcHin.setFont(new Font("Tahoma", Font.BOLD, 13));

		ngayThucHien = new JDateChooser();
		ngayThucHien.setBounds(138, 51, 183, 22);
		pnLaoDong.add(ngayThucHien);

		ngayThucHien.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if ("date".equals(e.getPropertyName())) {
					Date ngayTH = ngayThucHien.getDate();
					Date ngayHT = ngayHoanThanh.getDate();
					if (ngayHT != null && ngayTH != null && cboCongViec.getSelectedIndex() > 0
							&& ngayHT.after(ngayTH)) {
						listLD = phanCongDAO
								.getLaoDongTheoCV(listCV.get(cboCongViec.getSelectedIndex() - 1).getMaCongViec());
						xoaLaoDongTrung(ngayTH, ngayHT);
					}
				}
			}
		});
		
		ngayThucHien.setDateFormatString("dd/MM/yyyy");

		JLabel lblCngVic = new JLabel("Công Việc:");
		lblCngVic.setBounds(10, 20, 77, 17);
		pnLaoDong.add(lblCngVic);
		lblCngVic.setFont(new Font("Tahoma", Font.BOLD, 13));

		ngayHoanThanh = new JDateChooser();
		ngayHoanThanh.setBounds(138, 84, 183, 22);
		pnLaoDong.add(ngayHoanThanh);

		ngayHoanThanh.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if ("date".equals(e.getPropertyName())) {

					Date ngayTH = ngayThucHien.getDate();
					Date ngayHT = ngayHoanThanh.getDate();
					if (ngayTH != null && ngayHT != null && cboCongViec.getSelectedIndex() > 0
							&& ngayHT.after(ngayTH)) {
						listLD = phanCongDAO
								.getLaoDongTheoCV(listCV.get(cboCongViec.getSelectedIndex() - 1).getMaCongViec());
						xoaLaoDongTrung(ngayTH, ngayHT);
						
					}
				}
			}
		});

		ngayHoanThanh.setDateFormatString("dd/MM/yyyy");

		JLabel lblNgyThcHin_1 = new JLabel("Ngày hoàn thành:");
		lblNgyThcHin_1.setBounds(11, 84, 133, 22);
		pnLaoDong.add(lblNgyThcHin_1);
		lblNgyThcHin_1.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblTongChuyen = new JLabel("0");
		lblTongChuyen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongChuyen.setBounds(70, 518, 46, 23);
		pnLaoDong.add(lblTongChuyen);

		JLabel lblTng_1 = new JLabel("Tổng:");
		lblTng_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTng_1.setBounds(21, 518, 49, 23);
		pnLaoDong.add(lblTng_1);

		progressBar = new JProgressBar();
		progressBar.setBounds(114, 518, 246, 22);
		progressBar.setStringPainted(true);

		cboCongViec.addItemListener(this);
		cboTimMaLD.addItem("Tất cả");
		soLuongThem.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				sLThem = (int) ((JSpinner) e.getSource()).getValue();
			}
		});

		tbPhanCong.getTableHeader().setBackground(new Color(116, 235, 52));
		tbPhanCong.getTableHeader().setForeground(new Color(31, 39, 191));
		tbPhanCong.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));

		tbPhanCong.setRowHeight(tbPhanCong.getRowHeight() + 20);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLamMoi.setFocusPainted(false);
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLamMoi.setBackground(Color.ORANGE);
		btnLamMoi.setBounds(25, 88, 105, 33);
		panel.add(btnLamMoi);

		docDLTableCT();
		btnLamMoi.addActionListener(this);
		cboCongViecCT.addItemListener(this);
		tbCongTrinh.addMouseListener(this);
		btnPhanCong.addActionListener(this);

		lblXoa = new JLabel("");
		lblXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblXoa.setIcon(new ImageIcon(PhanCong_JPN.class.getResource("/images/left-arrow.png")));
		lblXoa.setBackground(Color.WHITE);
		lblXoa.setBounds(10, 155, 38, 39);
		panel.add(lblXoa);

		soLuongXoa = new JSpinner();
		soLuongXoa.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		soLuongXoa.setBounds(58, 159, 78, 29);
		panel.add(soLuongXoa);

		JLabel lblTng = new JLabel("Tổng:");
		lblTng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTng.setBounds(326, 161, 49, 23);
		panel.add(lblTng);

		lblTongNhan = new JLabel("" + tongSL);
		lblTongNhan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongNhan.setBounds(375, 161, 46, 23);
		panel.add(lblTongNhan);
		soLuongXoa.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				sLXoa = (int) ((JSpinner) e.getSource()).getValue();
			}
		});

		lblChiTietCT.addMouseListener(this);
		lblChiTietLD.addMouseListener(this);

		lblThem.addMouseListener(this);
		lblXoa.addMouseListener(this);

		cboTimMaCT.addItemListener(this);
		txtTimTenCT.addKeyListener(this);

		cboTimMaLD.addItemListener(this);
		txtTimTenLD.addKeyListener(this);

		JComponent comp0 = soLuongThem.getEditor();
		JFormattedTextField field0 = (JFormattedTextField) comp0.getComponent(0);
		DefaultFormatter formatter0 = (DefaultFormatter) field0.getFormatter();
		formatter0.setCommitsOnValidEdit(true);

		JComponent comp1 = soLuongXoa.getEditor();
		JFormattedTextField field1 = (JFormattedTextField) comp1.getComponent(0);
		DefaultFormatter formatter1 = (DefaultFormatter) field1.getFormatter();
		formatter1.setCommitsOnValidEdit(true);

	}

	public void docDLTableCT() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CongTrinh congTrinh : listCT) {
			modelCT.addRow(new Object[] { congTrinh.getTenCongTrinh(), df.format(congTrinh.getNgayKhoiCong()) });
		}

	}

	public void docDLTableLD(ArrayList<LaoDong> list) {
		new Thread(() -> {
			for (LaoDong ld : list) {
				modelLD.addRow(new Object[] { ld.getMaLaoDong(), ld.getTenLaoDong(), ld.getTrinhDo().getTenTrinhDo() });
			}
		}).start();

	}

	/**
	 * Hàm di chuyển đến trang đầu của bảng
	 */
	private void denTrangDauCT() {
		listCT = congTrinhDAO.phanTrang(1, 8, "Chưa hoàn thành");
		xoaAllModelCT();
		docDLTableCT();
		tbCongTrinh.clearSelection();
		txtPageCT.setText("1");
	}

	/**
	 * Hàm di chuyển đến trang cuối của bảng
	 */
	private void denTrangCuoiCT() {
		int slDb = congTrinhDAO.demSluongDuLieuTrongDB("Chưa hoàn thành");
		int trangLonNhat;
		if (slDb % 8 == 0) {
			trangLonNhat = slDb / 8;
		} else {
			trangLonNhat = slDb / 8 + 1;
		}
		soDau = 7 * (trangLonNhat - 1) + 1;
		soCuoi = soDau + 6;
		xoaAllModelCT();
		listCT = congTrinhDAO.phanTrang(soDau, soCuoi, "Chưa hoàn thành");
		xoaAllModelCT();
		docDLTableCT();
		tbCongTrinh.clearSelection();
		txtPageCT.setText(String.valueOf(trangLonNhat));

	}

	/**
	 * Hàm di chuyển đến trang thứ i-1 của bảng
	 */
	private void denTrangTruocCT() {
		int trang = Integer.parseInt(txtPageCT.getText());
		if (trang > 1) {
			txtPageCT.setText(String.valueOf(trang - 1));
			soDau = 8 * (trang - 2) + 1;
			soCuoi = soDau + 7;
			xoaAllModelCT();
			listCT = congTrinhDAO.phanTrang(soDau, soCuoi, "Chưa hoàn thành");
			xoaAllModelCT();
			docDLTableCT();
			tbCongTrinh.clearSelection();
		}
	}

	/**
	 * Hàm di chuyển đến trang thứ i+1 của bảng
	 */
	private void denTrangSauCT() {
		int slDb = congTrinhDAO.demSluongDuLieuTrongDB("Chưa hoàn thành");
		int trangLonNhat;
		int trang = Integer.parseInt(txtPageCT.getText());
		if (slDb % 8 == 0) {
			trangLonNhat = slDb / 8;
		} else {
			trangLonNhat = slDb / 8 + 1;
		}
		if (trang < trangLonNhat) {
			txtPageCT.setText(String.valueOf(trang + 1));
			soDau = 8 * trang + 1;
			soCuoi = soDau + 7;
			if (soCuoi > slDb)
				soCuoi = slDb - 1;
			xoaAllModelCT();
			listCT = congTrinhDAO.phanTrang(soDau, soCuoi, "Chưa hoàn thành");
			docDLTableCT();
			tbCongTrinh.clearSelection();
		}
	}

	public void xoaAllModelCT() {
		DefaultTableModel m = (DefaultTableModel) tbCongTrinh.getModel();
		m.getDataVector().removeAllElements();
	}

	public void xoaAllModelLD() {
		DefaultTableModel m = (DefaultTableModel) tbLaoDong.getModel();
		m.getDataVector().removeAllElements();
		tbLaoDong.clearSelection();
	}

	public void xoaAllModelPC() {
		DefaultTableModel m = (DefaultTableModel) tbPhanCong.getModel();
		m.getDataVector().removeAllElements();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnPhanCong)) {
			int row = tbCongTrinh.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công trình");
				return;
			}
			if (listPhanCong.size() == 0) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn lao động");
				return;
			}

			String s[];
			LocalDate th = null;
			java.sql.Date ngayTH = null;
			java.sql.Date ngayHT = null;
			try {
				Date d1 = ngayThucHien.getDate();
				s = df.format(d1).split("/");
				ngayTH = new java.sql.Date(Integer.parseInt(s[2]) - 1900, Integer.parseInt(s[1]) - 1,
						Integer.parseInt(s[0]));
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày thực hiện");
				return;
			}

			try {
				Date d2 = ngayHoanThanh.getDate();
				s = df.format(d2).split("/");
				ngayHT = new java.sql.Date(Integer.parseInt(s[2]) - 1900, Integer.parseInt(s[1]) - 1,
						Integer.parseInt(s[0]));
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày hoàn thành");
				return;
			}

			String maCT = txtMaCT.getText();
			CongTrinh ct = congTrinhDAO.getCTTheoMa(maCT);
			LocalDate kc = ct.getNgayKhoiCong().toLocalDate();
			th = ngayTH.toLocalDate();
			if (kc.isAfter(th)) {
				JOptionPane.showMessageDialog(null, "Ngày thực hiện phải sau ngày khởi công");
				return;
			}

			if (th.isAfter(ngayHT.toLocalDate())) {
				JOptionPane.showMessageDialog(null, "Ngày thực hiện phải trước ngày hoàn thành");
				return;
			}
			int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn phân công", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (kt == JOptionPane.YES_OPTION) {
				CongViec congViec = listCV.get(cboCongViec.getSelectedIndex()-1);
				for (LaoDong ld : listPhanCong) {
					phanCongDAO.phanCong(ld, new CongTrinh(txtMaCT.getText()), congViec, ngayTH, ngayHT, this.quanLy,
							false);

				}
				listPhanCong = new ArrayList<LaoDong>();
				xoaAllModelPC();
				xoaAllModelLD();
				modelLD.fireTableDataChanged();
				modelPC.fireTableDataChanged();
				tbCongTrinh.clearSelection();
				cboCongViec.setSelectedIndex(0);
				ngayThucHien.setDate(null);
				ngayHoanThanh.setDate(null);
				JOptionPane.showMessageDialog(null, "Đã phân công");
			}

		} else {
			txtMaCT.setText("");
			cboCongViecCT.setSelectedIndex(0);
			txtSoLuong.setText("");
			denTrangDauCT();
			txtTimTenCT.setText("");
			txtTimTenLD.setText("");
			xoaAllModelPC();
			cboCongViec.setSelectedIndex(0);
			ngayThucHien.setDate(null);
			ngayHoanThanh.setDate(null);
			tbCongTrinh.clearSelection();
			tongSL = 0;
			lblTongNhan.setText("" + tongSL);
			lblTongChuyen.setText("0");
			modelPC.fireTableDataChanged();
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getSource();
		if (o.equals(cboCongViec)) {
			if (cboCongViec.getSelectedIndex() == 0) {
				xoaAllModelLD();
				modelLD.fireTableDataChanged();
				kiemTraCbo = false;
				cboTimMaLD.removeAllItems();
				kiemTraCbo = true;
				return;
			}
			kiemTraCbo = false;
			String maCV = listCV.get(cboCongViec.getSelectedIndex() - 1).getMaCongViec();
			listLD = phanCongDAO.getLaoDongTheoCV(maCV);
			listTemp = listLD;
			Date ngayTH = ngayThucHien.getDate();
			Date ngayHT = ngayHoanThanh.getDate();
			if (ngayHT != null && ngayTH != null && ngayHT.after(ngayTH)) {
				listLD = phanCongDAO.getLaoDongTheoCV(listCV.get(cboCongViec.getSelectedIndex() - 1).getMaCongViec());
				xoaLaoDongTrung(ngayTH, ngayHT);
				kiemTraCbo = false;
				cboTimMaLD.removeAllItems();
				cboTimMaLD.addItem("Tất cả");
				for (LaoDong laoDong : listLD) {
					cboTimMaLD.addItem(laoDong.getMaLaoDong());
				}
				kiemTraCbo = true;
			} else {
				cboTimMaLD.removeAllItems();
				cboTimMaLD.addItem("Tất cả");
				for (LaoDong ld : listLD) {
					cboTimMaLD.addItem(ld.getMaLaoDong());
				}
			}
			listPhanCong = new ArrayList<LaoDong>();
			xoaAllModelPC();
			tbPhanCong.removeAll();
			modelPC.fireTableDataChanged();
			xoaAllModelLD();
			docDLTableLD(listLD);
			tbLaoDong.clearSelection();
			txtTimTenLD.setText("");
			kiemTraCbo = true;

			lblTongChuyen.setText(listLD.size() + "");

		} else if (o.equals(cboCongViecCT)) {
			String tenCV = cboCongViecCT.getSelectedItem().toString();
			String maCV = "";
			String maCT = txtMaCT.getText();
			for (CongViec congViec : listCV) {
				if (congViec.getTenCongViec().equals(tenCV)) {
					maCV = congViec.getMaCongViec();
					break;
				}
			}
			int soLuong = congTrinhDAO.demLaoDong(maCT, maCV);
			txtSoLuong.setText("" + soLuong);
		} else if (o.equals(cboTimMaCT)) {
			if (cboTimMaCT.getSelectedIndex() == 0)
				listCT = congTrinhDAO.phanTrang(soDau, soCuoi, "Chưa hoàn thành");
			else {
				listCT = new ArrayList<CongTrinh>();
				CongTrinh ct = congTrinhDAO.getCTTheoMa(cboTimMaCT.getSelectedItem().toString());
				listCT.add(ct);
			}
			xoaAllModelCT();
			docDLTableCT();
		} else if (o.equals(cboTimMaLD) && kiemTraCbo) {
			if (cboTimMaLD.getSelectedIndex() == 0) {
				listLD = listTemp;
			} else {
				LaoDong ld = listTemp.get(listTemp.indexOf(new LaoDong(cboTimMaLD.getSelectedItem().toString())));
				listLD = new ArrayList<LaoDong>();
				listLD.add(ld);
			}
			xoaAllModelLD();
			docDLTableLD(listLD);

			lblTongChuyen.setText(listLD.size() + "");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Object o = e.getSource();
		if (o.equals(tbCongTrinh)) {
			int row = tbCongTrinh.getSelectedRow();
			String ma = listCT.get(row).getMaCongTrinh();
			txtMaCT.setText(ma);

			String tenCV = cboCongViecCT.getSelectedItem().toString();
			String maCV = "";
			String maCT = txtMaCT.getText();
			for (CongViec congViec : listCV) {
				if (congViec.getTenCongViec().equals(tenCV)) {
					maCV = congViec.getMaCongViec();
					break;
				}
			}
			int soLuong = congTrinhDAO.demLaoDong(maCT, maCV);
			txtSoLuong.setText("" + soLuong);
			tongSL = 0;
			lblTongNhan.setText("" + tongSL);
		} else if (o.equals(lblChiTietCT)) {
			int row = tbCongTrinh.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công trình");
				return;
			}
			String ma = listCT.get(row).getMaCongTrinh();
			new ChiTietCongTrinh(ma).setVisible(true);
		} else if (o.equals(lblChiTietLD)) {
			int row = tbLaoDong.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn lao động");
				return;
			}
			String maLaoDong = listLD.get(row).getMaLaoDong();
			new ChiTietLaoDong(maLaoDong).setVisible(true);
		} else if (o.equals(lblThem)) {
			if (kiemTra()) {
				if (sLThem > 0) {
					if (sLThem < modelLD.getRowCount() + 1) {

						for (int i = 0; i < sLThem; i++) {
							String ma = modelLD.getValueAt(i, 0).toString();
							String ten = modelLD.getValueAt(i, 1).toString();
							String trinhDo = modelLD.getValueAt(i, 2).toString();
							listPhanCong.add(new LaoDong(ma, ten, new TrinhDo(trinhDo)));
							modelPC.addRow(new Object[] { ma, ten });
							listLD.remove(new LaoDong(ma));
							tongSL += 1;

						}
						lblTongNhan.setText("" + tongSL);
						listTemp = listLD;
						xoaAllModelLD();
						modelLD.fireTableDataChanged();
						docDLTableLD(listLD);
						lblTongChuyen.setText("" + listLD.size());
						tbLaoDong.clearSelection();
						soLuongThem.setValue(0);

					} else {
						JOptionPane.showMessageDialog(null, "Không đủ lao động!");
						return;
					}

				} else {
					int row = tbLaoDong.getSelectedRow();

					if (row == -1) {
						JOptionPane.showMessageDialog(null, "Bạn chưa chọn lao động");
						return;
					}
					String ma = modelLD.getValueAt(row, 0).toString();
					String ten = modelLD.getValueAt(row, 1).toString();
					String trinhDo = modelLD.getValueAt(row, 2).toString();
					listPhanCong.add(new LaoDong(ma, ten, new TrinhDo(trinhDo)));
					modelPC.addRow(new Object[] { ma, ten });
					listLD.remove(new LaoDong(ma));
					listTemp = listLD;
					xoaAllModelLD();
					modelLD.fireTableDataChanged();
					docDLTableLD(listLD);
					lblTongChuyen.setText(listLD.size() + "");
					tbLaoDong.clearSelection();
					tongSL += 1;
					lblTongNhan.setText("" + tongSL);
				}

			}

		} else if (o.equals(lblXoa)) {

			if (sLXoa > 0) {
				if (sLXoa < modelPC.getRowCount() + 1) {
					for (int i = 0; i < sLXoa; i++) {
						String ma = modelPC.getValueAt(0, 0).toString();
						for (LaoDong ld : listPhanCong) {
							if (ld.getMaLaoDong().equals(ma)) {
								listLD.add(ld);
								listPhanCong.remove(ld);
								break;
							}
						}
						tongSL -= 1;
						modelPC.removeRow(0);
					}

					lblTongNhan.setText("" + tongSL);
					listTemp = listLD;
					soLuongXoa.setValue(0);
					xoaAllModelLD();
					modelLD.fireTableDataChanged();
					docDLTableLD(listLD);
					lblTongChuyen.setText(listLD.size() + "");
					soLuongXoa.setValue(0);
				} else {
					JOptionPane.showMessageDialog(null, "Không đủ số lượng lao động để xóa!");
					return;
				}
			} else {
				int row = tbPhanCong.getSelectedRow();

				if (row == -1)
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn lao động");
				else {
					String ma = modelPC.getValueAt(row, 0).toString();
					for (LaoDong ld : listPhanCong) {
						if (ld.getMaLaoDong().equals(ma)) {
							listLD.add(ld);
							listPhanCong.remove(ld);
							break;
						}
					}
					tongSL -= 1;
					lblTongNhan.setText("" + tongSL);
					listTemp = listLD;
					modelPC.removeRow(row);

					xoaAllModelLD();
					docDLTableLD(listLD);

					lblTongChuyen.setText(listLD.size() + "");
				}
			}

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

	public boolean kiemTra() {

		if (ngayThucHien.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày thực hiện");
			return false;
		}

		if (ngayHoanThanh.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày hoàn thành");
			return false;
		}
		java.sql.Date ngayTH = chuyenNgay(ngayThucHien.getDate());
		java.sql.Date ngayHT = chuyenNgay(ngayHoanThanh.getDate());
		int row = tbCongTrinh.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn công trình");
			return false;
		}
		if (ngayTH.toLocalDate().isBefore(LocalDate.now())) {
			JOptionPane.showMessageDialog(null, "Ngày thực hiện phải sau ngày hiện tại");
			return false;
		}

		if (ngayTH.toLocalDate().isBefore(listCT.get(row).getNgayKhoiCong().toLocalDate())) {
			JOptionPane.showMessageDialog(null, "Ngày thực hiện phải sau ngày khởi công");
			return false;
		}

		if (ngayTH.toLocalDate().isAfter(ngayHT.toLocalDate())) {
			JOptionPane.showMessageDialog(null, "Ngày hoàn thành phải sau ngày thực hiện");
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public java.sql.Date chuyenNgay(Date ngay) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String s[] = df.format(ngay).split("/");
		return new java.sql.Date(Integer.parseInt(s[2]) - 1900, Integer.parseInt(s[1]) - 1, Integer.parseInt(s[0]));
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
		if (o.equals(txtTimTenCT)) {
			if (txtTimTenCT.getText().equals("")) {
				listCT = congTrinhDAO.phanTrang(soDau, soCuoi, "Chưa hoàn thành");
			} else {
				listCT = congTrinhDAO.timKiemCongTrinh(new DiaDiem("", "", ""), "", txtTimTenCT.getText(),
						"Chưa hoàn thành");
			}
			xoaAllModelCT();
			docDLTableCT();
			tbCongTrinh.clearSelection();
		} else if (o.equals(txtTimTenLD) && cboCongViec.getSelectedIndex() != 0) {

			if (txtTimTenLD.getText().equals("")) {
				listLD = listTemp;
			
			} else {
				listLD = new ArrayList<LaoDong>();
				for (LaoDong ld : listTemp) {
					if (ld.getTenLaoDong().toUpperCase().contains(txtTimTenLD.getText().toUpperCase()))
						listLD.add(ld);
				}
			}

			kiemTraCbo = false;
			cboTimMaLD.removeAllItems();
			cboTimMaLD.addItem("Tất cả");
			for (LaoDong laoDong : listLD) {
				cboTimMaLD.addItem(laoDong.getMaLaoDong());
			}
			kiemTraCbo = true;

		}
		xoaAllModelLD();
		docDLTableLD(listLD);
		lblTongChuyen.setText(listLD.size() + "");
	}

	public void xoaLaoDongTrung(Date ngayTH, Date ngayHT) {

		java.sql.Date th = chuyenNgay(ngayTH);
		java.sql.Date ht = chuyenNgay(ngayHT);

		new Thread(() -> {
			
			txtTimTenLD.setEditable(false);
			cboTimMaLD.setEnabled(false);
			cboCongViec.setEnabled(false);
			ngayThucHien.getDateEditor().setEnabled(false);
			ngayHoanThanh.getDateEditor().setEnabled(false);
			pnLaoDong.add(progressBar);
			int dem = 0;
			progressBar.setMaximum(listTemp.size());
			for (int i = listLD.size()-1; i>=0;i--) {
				LaoDong ld = listLD.get(i);
				boolean check = true;// true là xóa
				dem++;
				progressBar.setValue(dem);
				ArrayList<ChiTietCV> listChiTiet = phanCongDAO.getChiTietCVTheoMaLD(ld.getMaLaoDong());

				if (listChiTiet.size() > 0) {
					ChiTietCV first = listChiTiet.get(0);
					if (listChiTiet.size() == 1) {
						if (ht.after(first.getNgayThucHien()) && ht.after(first.getNgayHoanThanh())
								&& th.after(first.getNgayThucHien()) && th.after(first.getNgayHoanThanh())
								|| ht.before(first.getNgayThucHien()) && ht.before(first.getNgayHoanThanh())
										&& th.before(first.getNgayThucHien()) && th.before(first.getNgayHoanThanh())) {
							check = false;
						}
					} else {
						for (int j = 0; j < listChiTiet.size() - 2; j++) {
							if (th.after(listChiTiet.get(j).getNgayHoanThanh())
									&& ht.before(listChiTiet.get(j + 1).getNgayThucHien())) {
//							nếu nó nằm giữa
								check = false;
								break;
							}
						}
//						nếu không nằm giữa -> kiểm tra 2 biên
						if (check) {
//						nếu thỏa biên bên trái
							if (ht.before(first.getNgayThucHien()) && ht.before(first.getNgayHoanThanh())) {
								check = false;
							}
//						nếu thỏa biên bên phải
							ChiTietCV last = listChiTiet.get(listChiTiet.size() - 1);
							if (th.after(last.getNgayHoanThanh()) && th.after(last.getNgayThucHien())) {
								check = false;
							}
						}
					}

					if (check) {
						listLD.remove(ld);
					}
				}
			}
			listTemp = listLD;
			lblTongChuyen.setText(listLD.size() + "");
			
						
			xoaAllModelPC();
			modelPC.fireTableDataChanged();
			xoaAllModelLD();
			modelLD.fireTableDataChanged();
			docDLTableLD(listLD);
			listTemp = listLD;
			kiemTraCbo = false;
			cboTimMaLD.removeAllItems();
			cboTimMaLD.addItem("Tất cả");
			for (LaoDong laoDong : listLD) {
				cboTimMaLD.addItem(laoDong.getMaLaoDong());
			}
			kiemTraCbo = true;
			
			txtTimTenLD.setEditable(true);
			cboTimMaLD.setEnabled(true);
			cboCongViec.setEnabled(true);
			ngayThucHien.getDateEditor().setEnabled(true);
			ngayHoanThanh.getDateEditor().setEnabled(true);
			
			try {
				Container p = progressBar.getParent();
				p.remove(progressBar);
				p.validate();
				p.repaint();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}).start();
	}
}
