package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietCVDAO;
import dao.QLCongViec_DAO;
import dao.QuanLyCongTrinh_DAO;
import dao.TrinhDo_DAO;
import entity.CongTrinh;
import entity.CongViec;
import entity.TrinhDo;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class QLCongViec_GUI extends JPanel implements ActionListener, MouseListener, ItemListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tbCongViec;
	private DefaultTableModel modelCV;
	private JTextField txtTenCV;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnLamMoi;
	private JComboBox<String> cboTrinhDo;
	private ArrayList<CongViec> listCongViec = new QLCongViec_DAO().getCongViec();
	private QLCongViec_DAO QLCV_DAO = new QLCongViec_DAO();
	private JPanel pnlCongViec;
	private JTextField txtPage;
	private JTextField txtTimTenCT;
	private DefaultTableModel modelCT;
	private JTable tbCongTrinh;
	private int soDau = 1;
	private int soCuoi = 8;
	private ChiTietCVDAO chiTietDao;
	private List<CongTrinh> listCT;
	private JComboBox<String> cboTimKiemMaCT;

	/**
	 * Create the panel.
	 */
	public QLCongViec_GUI() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(632, 164, 653, 364);
		add(scrollPane);

		modelCV = new DefaultTableModel(new String[] { "Mã công việc", "Tên công việc", "Trình độ yêu cầu" }, 0) {
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
		tbCongViec = new JTable(modelCV);
		scrollPane.setViewportView(tbCongViec);

		JLabel lblMCngVic = new JLabel("Tên công việc:");
		lblMCngVic.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMCngVic.setBounds(632, 20, 117, 24);

		tbCongViec.setRowHeight(tbCongViec.getRowHeight() + 20);
		docDuLieu();

		tbCongViec.getTableHeader().setForeground(new Color(31, 39, 191));
		tbCongViec.getTableHeader().setBackground(new Color(116, 235, 52));
		tbCongViec.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		tbCongViec.addMouseListener(this);

		pnlCongViec = new JPanel();
		pnlCongViec.setBackground(Color.WHITE);
		pnlCongViec.setBounds(604, 10, 701, 535);
		add(pnlCongViec);
		pnlCongViec.setLayout(null);
		pnlCongViec.setBorder(BorderFactory.createTitledBorder("Công việc"));

		btnThem = new JButton("Thêm");
		btnThem.setBounds(36, 95, 89, 23);
		pnlCongViec.add(btnThem);
		btnThem.setFocusPainted(false);

		btnThem.setIcon(new ImageIcon(QLCongViec_GUI.class.getResource("/images/them.png")));

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBounds(135, 95, 114, 23);
		pnlCongViec.add(btnCapNhat);
		btnCapNhat.setFocusPainted(false);
		btnCapNhat.setIcon(new ImageIcon(QLCongViec_GUI.class.getResource("/images/cap_nhat.png")));

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBounds(259, 94, 107, 23);
		pnlCongViec.add(btnLamMoi);
		btnLamMoi.setFocusPainted(false);
		btnLamMoi.setIcon(new ImageIcon(QLCongViec_GUI.class.getResource("/images/lam_moi.png")));

		txtTenCV = new JTextField();
		txtTenCV.setBounds(142, 45, 149, 24);
		pnlCongViec.add(txtTenCV);
		txtTenCV.setColumns(10);

		JLabel lblTrnh = new JLabel("Trình độ:");
		lblTrnh.setBounds(339, 43, 117, 24);
		pnlCongViec.add(lblTrnh);
		lblTrnh.setFont(new Font("Tahoma", Font.BOLD, 14));

		cboTrinhDo = new JComboBox<String>();
		cboTrinhDo.setBounds(413, 43, 188, 24);
		pnlCongViec.add(cboTrinhDo);

		ArrayList<TrinhDo> trinhDo = new TrinhDo_DAO().getTrinhDo();
		for (TrinhDo t : trinhDo) {
			cboTrinhDo.addItem(t.getTenTrinhDo());
		}

		JLabel lblTenCV = new JLabel("Tên công việc:");
		lblTenCV.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTenCV.setBounds(36, 43, 117, 24);
		pnlCongViec.add(lblTenCV);
		btnLamMoi.addActionListener(this);
		btnCapNhat.addActionListener(this);

		btnThem.addActionListener(this);

		JPanel pnlCT = new JPanel();
		pnlCT.setBackground(Color.WHITE);
		pnlCT.setBounds(10, 10, 571, 530);
		add(pnlCT);
		pnlCT.setBorder(BorderFactory.createTitledBorder("Công trình"));
		pnlCT.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 109, 529, 374);
		pnlCT.add(scrollPane_1);
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
		scrollPane_1.setViewportView(tbCongTrinh);
		chiTietDao = new ChiTietCVDAO();
		listCT = new ArrayList<CongTrinh>();
		listCT = chiTietDao.phanTrang(1, 8);
		docDuLieuCongTrinhVaoBang();

		tbCongTrinh.setRowHeight(tbCongTrinh.getRowHeight() + 20);
		tbCongTrinh.getTableHeader().setForeground(new Color(31, 39, 191));
		tbCongTrinh.getTableHeader().setBackground(new Color(116, 235, 52));
		tbCongTrinh.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));

		JButton btnDauTien = new JButton("");
		btnDauTien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				denTrangDau();
			}
		});
		btnDauTien.setIcon(
				new ImageIcon(QLCongViec_GUI.class.getResource("/images/baseline_skip_previous_white_24dp.png")));
		btnDauTien.setFocusPainted(false);
		btnDauTien.setBackground(Color.BLACK);
		btnDauTien.setBounds(21, 493, 33, 23);
		pnlCT.add(btnDauTien);

		JButton btnTruoc = new JButton("");
		btnTruoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				denTrangTruoc();
			}
		});
		btnTruoc.setIcon(
				new ImageIcon(QLCongViec_GUI.class.getResource("/images/baseline_fast_rewind_white_24dp.png")));
		btnTruoc.setFocusPainted(false);
		btnTruoc.setBackground(Color.BLACK);
		btnTruoc.setBounds(73, 493, 33, 23);
		pnlCT.add(btnTruoc);

		txtPage = new JTextField();
		txtPage.setBackground(Color.LIGHT_GRAY);
		txtPage.setText("1");
		txtPage.setHorizontalAlignment(SwingConstants.CENTER);
		txtPage.setEditable(false);
		txtPage.setColumns(10);
		txtPage.setBounds(120, 493, 33, 23);
		pnlCT.add(txtPage);

		JButton btnSau = new JButton("");
		btnSau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				denTrangSau();
			}
		});
		btnSau.setIcon(new ImageIcon(QLCongViec_GUI.class.getResource("/images/baseline_fast_forward_white_24dp.png")));
		btnSau.setFocusPainted(false);
		btnSau.setBackground(Color.BLACK);
		btnSau.setBounds(163, 493, 33, 23);
		pnlCT.add(btnSau);

		JButton btnCuoiCung = new JButton("");
		btnCuoiCung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				denTrangCuoi();
			}
		});
		btnCuoiCung
				.setIcon(new ImageIcon(QLCongViec_GUI.class.getResource("/images/baseline_skip_next_white_24dp.png")));
		btnCuoiCung.setFocusPainted(false);
		btnCuoiCung.setBackground(Color.BLACK);
		btnCuoiCung.setBounds(206, 493, 33, 23);
		pnlCT.add(btnCuoiCung);

		JLabel lblTnCngTrnh = new JLabel("Tên công trình");
		lblTnCngTrnh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTnCngTrnh.setBounds(47, 80, 108, 14);
		pnlCT.add(lblTnCngTrnh);

		txtTimTenCT = new JTextField();
		txtTimTenCT.setColumns(10);
		txtTimTenCT.setBounds(148, 74, 163, 25);
		pnlCT.add(txtTimTenCT);

		JLabel lblMCngTrnh = new JLabel("Mã công trình");
		lblMCngTrnh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMCngTrnh.setBounds(47, 42, 108, 14);
		pnlCT.add(lblMCngTrnh);

		cboTimKiemMaCT = new JComboBox<String>();
		cboTimKiemMaCT.setBounds(148, 38, 163, 25);
		pnlCT.add(cboTimKiemMaCT);

		JLabel lblTmKim_1 = new JLabel("Tìm kiếm:");
		lblTmKim_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTmKim_1.setBounds(47, 15, 85, 23);
		pnlCT.add(lblTmKim_1);

		cboTimKiemMaCT.addItem("Tất cả");
		for (CongTrinh ct : listCT) {
			cboTimKiemMaCT.addItem(ct.getMaCongTrinh());
		}

		tbCongTrinh.addMouseListener(this);
		cboTimKiemMaCT.addItemListener(this);
		txtTimTenCT.addKeyListener(this);
	}

	public void docDuLieuCongTrinhVaoBang() {
		for (CongTrinh ct : listCT) {
			modelCT.addRow(new Object[] { ct.getMaCongTrinh(), ct.getTenCongTrinh(), ct.getDiaDiem() });
		}
	}

	/**
	 * Hàm di chuyển đến trang đầu của bảng
	 */
	private void denTrangDau() {
		listCT = chiTietDao.phanTrang(1, 8);
		xoaAllModelCT();
		docDuLieuCongTrinhVaoBang();
		txtPage.setText("1");
	}

	/**
	 * Hàm di chuyển đến trang cuối của bảng
	 */
	private void denTrangCuoi() {

		int slDb = chiTietDao.demSluongDuLieuTrongDB();
		int trangLonNhat;
		if (slDb % 8 == 0) {
			trangLonNhat = slDb / 8;
		} else {
			trangLonNhat = slDb / 8 + 1;
		}
		soDau = 7 * (trangLonNhat - 1) + 1;
		soCuoi = soDau + 7;
		xoaAllModelCT();
		listCT = chiTietDao.phanTrang(soDau, soCuoi);
		xoaAllModelCT();
		docDuLieuCongTrinhVaoBang();
		txtPage.setText(String.valueOf(trangLonNhat));

	}

	public void xoaAllModelCT() {
		DefaultTableModel m = (DefaultTableModel) tbCongTrinh.getModel();
		m.getDataVector().removeAllElements();
	}

	/**
	 * Hàm di chuyển đến trang thứ i-1 của bảng
	 */
	private void denTrangTruoc() {

		int trang = Integer.parseInt(txtPage.getText());
		if (trang > 1) {
			txtPage.setText(String.valueOf(trang - 1));
			soDau = 8 * (trang - 2) + 1;
			soCuoi = soDau + 7;
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
		int trang = Integer.parseInt(txtPage.getText());
		if (slDb % 8 == 0) {
			trangLonNhat = slDb / 8;
		} else {
			trangLonNhat = slDb / 8 + 1;
		}
		if (trang < trangLonNhat) {
			txtPage.setText(String.valueOf(trang + 1));
			soDau = 8 * (trang) + 1;
			soCuoi = soDau + 7;
			xoaAllModelCT();
			listCT = chiTietDao.phanTrang(soDau, soCuoi);
			docDuLieuCongTrinhVaoBang();
			tbCongTrinh.setRowSelectionInterval(0, 0);
		}
	}

	private void docDuLieu() {
		for (CongViec cv : listCongViec) {
			modelCV.addRow(new Object[] { cv.getMaCongViec(), cv.getTenCongViec(), cv.getTrinhDo().getTenTrinhDo() });

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (tbCongViec.getSelectedRow() != -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng làm mới trước khi thêm");
				return;
			}
			
			if (validata()) {
				String ma = taoMa();
				String ten = txtTenCV.getText().trim();
				String td = cboTrinhDo.getSelectedItem().toString();
				CongViec tt = new CongViec(ma, ten, new TrinhDo(td));
				int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				for (CongViec cv : listCongViec) {
					if(ten.equalsIgnoreCase(cv.getTenCongViec().trim())) {
						JOptionPane.showMessageDialog(null, "Tên công việc khá giống nhau vui lòng chọn tên khác để không bị nhắm lẫn");
						return;
					}
				}
				if (kt == JOptionPane.YES_OPTION) {
					boolean them = QLCV_DAO.themCV(tt);
					if (them) {
						listCongViec.add(tt);
						JOptionPane.showMessageDialog(null, "Thêm thành công");
						modelCV.addRow(new Object[] { tt.getMaCongViec(), tt.getTenCongViec(), tt.getTrinhDo() });
					} else
						JOptionPane.showMessageDialog(null, "Thêm không thành công");
				}
			}
		} else if (o.equals(btnCapNhat)) {
			int row = tbCongViec.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Chưa chọn công việc");
				return;
			}
			if (validata()) {
				
				
					String ma = listCongViec.get(row).getMaCongViec();
					String ten = txtTenCV.getText();
					String tt = cboTrinhDo.getSelectedItem().toString();
					CongViec sua = new CongViec(ma, ten, new TrinhDo(tt));
					int kt = JOptionPane.showConfirmDialog(null, "bạn có muốn cập nhật", "Thông báo",
							JOptionPane.YES_NO_OPTION);
					if (kt == JOptionPane.YES_OPTION) {
						boolean sua_1 = QLCV_DAO.capnhatCV(sua);
						if (sua_1) {
							modelCV.setValueAt(ma, row, 0);
							modelCV.setValueAt(ten, row, 1);
							modelCV.setValueAt(tt, row, 2);

							JOptionPane.showMessageDialog(null, "Sửa thành công");
						} else
							JOptionPane.showMessageDialog(null, "Sửa không thành công");
					}
				}
			

		} else {
			lamMoi();
		}
	}

	private void lamMoi() {
		// TODO Auto-generated method stub
		txtTenCV.setText("");
		cboTrinhDo.setSelectedIndex(0);
		tbCongViec.clearSelection();
	}

	private boolean validata() {
		// TODO Auto-generated method stub
		if (!txtTenCV.getText()
				.matches("[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ\r\n"
						+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu\r\n"
						+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ,0-9]+")) {
			JOptionPane.showMessageDialog(null, "Tên không hợp lệ!");
			txtTenCV.selectAll();
			txtTenCV.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tbCongViec.getSelectedRow();
		if (row >= 0) {
			String ten = modelCV.getValueAt(row, 1).toString();
			String tt = modelCV.getValueAt(row, 2).toString().trim();
			txtTenCV.setText(ten);
			cboTrinhDo.setSelectedItem(tt);
		}
		if (tbCongTrinh.getSelectedRow() >= 0 && e.getClickCount() == 2) {
			new QuanLiCongViecCongTrinh_Jfm(listCT.get(tbCongTrinh.getSelectedRow())).setVisible(true);
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

	public String taoMa() {
		String maCuoi = QLCV_DAO.getCVCuoi().getMaCongViec().substring(2);
		int so = Integer.parseInt(maCuoi) + 1000 + 1;
		String soMoi = ("" + so).substring(1);
		return "CV" + soMoi;
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
		// TODO Auto-generated method stub
		String tenCT = txtTimTenCT.getText();
		if (tenCT.equals("")) {
			listCT = chiTietDao.phanTrang(soDau, soCuoi);
		} else {
			listCT = chiTietDao.timCTDangLamTheoTen(tenCT);
		}
		xoaAllModelCT();
		docDuLieuCongTrinhVaoBang();
	}

	@Override

	public void itemStateChanged(ItemEvent e) {

		if (cboTimKiemMaCT.getSelectedIndex() == 0)
			listCT = chiTietDao.phanTrang(soDau, soCuoi);
		else {
			listCT = new ArrayList<CongTrinh>();
			listCT.add(new QuanLyCongTrinh_DAO().getCTTheoMa(cboTimKiemMaCT.getSelectedItem().toString()));
		}
		xoaAllModelCT();
		docDuLieuCongTrinhVaoBang();
	}
}
