package gui;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import dao.ChiTietCVDAO;
import dao.PhanCongLaoDong_DAO;
import dao.QLCongViec_DAO;
import dao.QuanLyLaoDong_DAO;
import entity.ChiTietCV;
import entity.CongTrinh;
import entity.CongViec;
import entity.LaoDong;

import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class QLLichLamViec extends JPanel implements ActionListener, MouseListener, ItemListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tbCongTrinh;
	private JTextField txtTimTenCT;
	private JTextField txtTimMaCT;
	private JTable tbLaoDong;
	private JTextField txtTimTenLD;
	private JComboBox<String> cboCongViec;
	private JLabel btnPage;
	private ChiTietCVDAO chiTietDAO = new ChiTietCVDAO();
	private ArrayList<CongTrinh> listCT = chiTietDAO.phanTrang(1, 8);
	private DefaultTableModel modelCongTrinh;
	private DefaultTableModel modelLaoDong;
	private JDateChooser ngayThucHien;
	private JDateChooser ngayHoanThanh;
	private ArrayList<CongViec> listCV = new QLCongViec_DAO().getCongViec();
	private JButton btnSua;
	private ArrayList<LaoDong> listLD = new ArrayList<LaoDong>();
	private JCheckBox cbkCongViec;
	private ArrayList<ChiTietCV> chitiet;
	private JButton btnlamMoi;
	private JLabel lblSoLuongLD;

	/**
	 * Create the panel.
	 */
	public QLLichLamViec() {
		setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Công trình", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 0, 809, 550);
		add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 789, 400);
		panel_1.add(scrollPane);

		modelCongTrinh = new DefaultTableModel(new String[] { "Mã CT", "Tên công trình", "Ngày khởi công", "Địa chỉ" },
				0) {
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
		tbCongTrinh = new JTable(modelCongTrinh);
		scrollPane.setViewportView(tbCongTrinh);

		JButton btnTurnTop = new JButton("");
		btnTurnTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				denTrangDau();
			}
		});
		btnTurnTop.setIcon(
				new ImageIcon(QLLichLamViec.class.getResource("/images/baseline_skip_previous_white_24dp.png")));
		btnTurnTop.setForeground(Color.DARK_GRAY);
		btnTurnTop.setFocusPainted(false);
		btnTurnTop.setBackground(Color.DARK_GRAY);
		btnTurnTop.setBounds(20, 508, 37, 25);
		panel_1.add(btnTurnTop);

		JButton btnTurnLeft = new JButton("");
		btnTurnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				denTrangTruoc();
			}
		});
		btnTurnLeft
				.setIcon(new ImageIcon(QLLichLamViec.class.getResource("/images/baseline_fast_rewind_white_24dp.png")));
		btnTurnLeft.setFocusPainted(false);
		btnTurnLeft.setBackground(Color.DARK_GRAY);
		btnTurnLeft.setBounds(67, 508, 37, 25);
		panel_1.add(btnTurnLeft);

		btnPage = new JLabel("1");
		btnPage.setBackground(Color.LIGHT_GRAY);
		btnPage.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPage.setHorizontalAlignment(SwingConstants.CENTER);
		btnPage.setBounds(114, 508, 46, 25);
		btnPage.setOpaque(true);
		panel_1.add(btnPage);

		JButton btnTurnRight = new JButton("");
		btnTurnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				denTrangSau();
			}
		});
		btnTurnRight.setIcon(
				new ImageIcon(QLLichLamViec.class.getResource("/images/baseline_fast_forward_white_24dp.png")));
		btnTurnRight.setFocusPainted(false);
		btnTurnRight.setBackground(Color.DARK_GRAY);
		btnTurnRight.setBounds(170, 508, 37, 25);
		panel_1.add(btnTurnRight);

		JButton btnTurnEnd = new JButton("");
		btnTurnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				denTrangCuoi();
			}
		});
		btnTurnEnd.setIcon(new ImageIcon(QLLichLamViec.class.getResource("/images/baseline_skip_next_white_24dp.png")));
		btnTurnEnd.setFocusPainted(false);
		btnTurnEnd.setBackground(Color.DARK_GRAY);
		btnTurnEnd.setBounds(217, 508, 37, 25);
		panel_1.add(btnTurnEnd);

		JLabel lblTmKim = new JLabel("Tìm kiếm:");
		lblTmKim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTmKim.setBounds(24, 20, 85, 29);
		panel_1.add(lblTmKim);

		JLabel lblMCngTrnh = new JLabel("Mã công trình");
		lblMCngTrnh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMCngTrnh.setBounds(24, 60, 108, 14);
		panel_1.add(lblMCngTrnh);

		JLabel lblTnCngTrnh = new JLabel("Tên công trình");
		lblTnCngTrnh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTnCngTrnh.setBounds(302, 60, 108, 14);
		panel_1.add(lblTnCngTrnh);

		txtTimTenCT = new JTextField();
		txtTimTenCT.setColumns(10);
		txtTimTenCT.setBounds(403, 54, 378, 29);
		panel_1.add(txtTimTenCT);

		txtTimMaCT = new JTextField();
		txtTimMaCT.setColumns(10);
		txtTimMaCT.setBounds(125, 54, 163, 29);
		panel_1.add(txtTimMaCT);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Công việc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(812, 0, 504, 550);
		add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 262, 473, 277);
		panel_2.add(scrollPane_1);

		modelLaoDong = new DefaultTableModel(new String[] { "Tên LD", "Ngày thực hiện", "Ngày hoàn thành" }, 0) {
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
		tbLaoDong = new JTable(modelLaoDong);
		scrollPane_1.setViewportView(tbLaoDong);

		cboCongViec = new JComboBox<String>();
		cboCongViec.setBounds(183, 120, 153, 25);
		panel_2.add(cboCongViec);
		cboCongViec.addItem("Công việc");
		for (CongViec cv : listCV) {
			cboCongViec.addItem(cv.getTenCongViec());
		}

		JLabel lblNgyThcHin = new JLabel("Ngày thực hiện:");
		lblNgyThcHin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgyThcHin.setBounds(21, 31, 108, 14);
		panel_2.add(lblNgyThcHin);

		JLabel lblNgyHonThnh = new JLabel("Ngày hoàn thành:");
		lblNgyHonThnh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgyHonThnh.setBounds(21, 76, 124, 14);
		panel_2.add(lblNgyHonThnh);

		ngayThucHien = new JDateChooser();
		ngayThucHien.setBounds(183, 25, 157, 25);
		panel_2.add(ngayThucHien);

		ngayThucHien.setDateFormatString("dd/MM/yyyy");

		ngayHoanThanh = new JDateChooser();
		ngayHoanThanh.setBounds(183, 70, 157, 25);
		panel_2.add(ngayHoanThanh);

		ngayHoanThanh.setDateFormatString("dd/MM/yyyy");

		JLabel lblTmKim_1 = new JLabel("Tìm kiếm:");
		lblTmKim_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTmKim_1.setBounds(21, 161, 85, 29);
		panel_2.add(lblTmKim_1);

		JLabel lblTnLaong = new JLabel("Tên lao động:");
		lblTnLaong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTnLaong.setBounds(21, 201, 108, 14);
		panel_2.add(lblTnLaong);

		txtTimTenLD = new JTextField();
		txtTimTenLD.setColumns(10);
		txtTimTenLD.setBounds(122, 195, 372, 25);
		panel_2.add(txtTimTenLD);

		JSeparator separator = new JSeparator();
		separator.setBounds(58, 161, 421, 2);
		panel_2.add(separator);

		btnSua = new JButton("Sửa");
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSua.setFocusPainted(false);
		btnSua.setBackground(Color.DARK_GRAY);
		btnSua.setBounds(373, 16, 108, 43);
		panel_2.add(btnSua);

		panel_1.setBackground(Color.WHITE);
		panel_2.setBackground(Color.WHITE);

		tbCongTrinh.getTableHeader().setForeground(new Color(31, 39, 191));
		tbCongTrinh.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		tbCongTrinh.getTableHeader().setBackground(new Color(116, 235, 52));

		tbCongTrinh.setRowHeight(tbCongTrinh.getRowHeight() + 20);

		tbLaoDong.getTableHeader().setForeground(new Color(31, 39, 191));
		tbLaoDong.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		tbLaoDong.getTableHeader().setBackground(new Color(116, 235, 52));

		tbLaoDong.setRowHeight(tbLaoDong.getRowHeight() + 20);

		JLabel lblSaTheoCng = new JLabel("Sửa theo công việc:");
		lblSaTheoCng.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSaTheoCng.setBounds(21, 124, 124, 14);
		panel_2.add(lblSaTheoCng);

		cbkCongViec = new JCheckBox("");
		cbkCongViec.setBounds(151, 120, 21, 23);
		panel_2.add(cbkCongViec);

		tbCongTrinh.getColumnModel().getColumn(0).setPreferredWidth(30);
		tbCongTrinh.getColumnModel().getColumn(1).setPreferredWidth(100);
		tbCongTrinh.getColumnModel().getColumn(2).setPreferredWidth(30);
		tbCongTrinh.getColumnModel().getColumn(3).setPreferredWidth(270);
		docDuLieuVaoTBCT();

		cboCongViec.addItemListener(this);
		tbCongTrinh.addMouseListener(this);
		tbLaoDong.addMouseListener(this);

		btnSua.addActionListener(this);

		cbkCongViec.addActionListener(this);
		cboCongViec.setEnabled(false);

		btnlamMoi = new JButton("Làm mới");
		btnlamMoi.setForeground(Color.WHITE);
		btnlamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnlamMoi.setFocusPainted(false);
		btnlamMoi.setBackground(Color.DARK_GRAY);
		btnlamMoi.setBounds(371, 73, 108, 43);
		panel_2.add(btnlamMoi);

		JLabel lblSLng = new JLabel("Số lượng:");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSLng.setBounds(21, 230, 68, 25);
		panel_2.add(lblSLng);

		lblSoLuongLD = new JLabel("0");
		lblSoLuongLD.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSoLuongLD.setBounds(104, 230, 68, 25);
		panel_2.add(lblSoLuongLD);

		txtTimMaCT.addKeyListener(this);
		txtTimTenCT.addKeyListener(this);

		txtTimTenLD.addKeyListener(this);

		btnlamMoi.addActionListener(this);
	}

	public void docDuLieuVaoTBCT() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CongTrinh ct : listCT) {
			modelCongTrinh.addRow(new Object[] { ct.getMaCongTrinh(), ct.getTenCongTrinh(),
					df.format(ct.getNgayKhoiCong()), ct.getDiaDiem() });
		}
	}

	public void docDuLieuVaoTBLD() {
		new Thread(() -> {
			try {
				listLD = new ArrayList<LaoDong>();
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				for (ChiTietCV ctCV : chitiet) {
					LaoDong nd = new QuanLyLaoDong_DAO().getLaoDong(ctCV.getLaoDong().getMaLaoDong());
					listLD.add(nd);
					modelLaoDong.addRow(new Object[] { nd.getTenLaoDong(), df.format(ctCV.getNgayThucHien()),
							df.format(ctCV.getNgayHoanThanh()) });
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}).start();
	}

	/**
	 * Hàm di chuyển đến trang đầu của bảng
	 */
	private void denTrangDau() {
		listCT = chiTietDAO.phanTrang(1, 6);
		xoaAllModel();
		docDuLieuVaoTBCT();
		tbCongTrinh.clearSelection();
		btnPage.setText("1");
	}

	/**
	 * Hàm di chuyển đến trang cuối của bảng
	 */
	private void denTrangCuoi() {
		int slDb = chiTietDAO.demSluongDuLieuTrongDB();
		int trangLonNhat;
		if (slDb % 6 == 0) {
			trangLonNhat = slDb / 6;
		} else {
			trangLonNhat = slDb / 6 + 1;
		}
		int soDau = 6 * (trangLonNhat - 1) + 1;
		int soCuoi = soDau + 5;
		xoaAllModel();
		listCT = chiTietDAO.phanTrang(soDau, soCuoi);
		xoaAllModel();
		docDuLieuVaoTBCT();
		tbCongTrinh.clearSelection();
		btnPage.setText(String.valueOf(trangLonNhat));

	}

	/**
	 * Hàm di chuyển đến trang thứ i-1 của bảng
	 */
	private void denTrangTruoc() {

		int trang = Integer.parseInt(btnPage.getText());
		if (trang > 1) {
			btnPage.setText(String.valueOf(trang - 1));
			int soDau = 6 * (trang - 2) + 1;
			int soCuoi = soDau + 5;
			xoaAllModel();
			listCT = chiTietDAO.phanTrang(soDau, soCuoi);
			xoaAllModel();
			docDuLieuVaoTBCT();
			tbCongTrinh.clearSelection();
		}
	}

	/**
	 * Hàm di chuyển đến trang thứ i+1 của bảng
	 */
	private void denTrangSau() {

		int slDb = chiTietDAO.demSluongDuLieuTrongDB();
		int trangLonNhat;
		int trang = Integer.parseInt(btnPage.getText());
		if (slDb % 6 == 0) {
			trangLonNhat = slDb / 6;
		} else {
			trangLonNhat = slDb / 6 + 1;
		}
		if (trang < trangLonNhat) {
			btnPage.setText(String.valueOf(trang + 1));
			int soDau = 6 * (trang) + 1;
			int soCuoi = soDau + 5;
			xoaAllModel();
			listCT = chiTietDAO.phanTrang(soDau, soCuoi);
			docDuLieuVaoTBCT();
			tbCongTrinh.clearSelection();
		}
	}

	public void xoaAllModel() {
		listLD = new ArrayList<LaoDong>();
		DefaultTableModel m = (DefaultTableModel) tbCongTrinh.getModel();
		m.getDataVector().removeAllElements();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(tbCongTrinh)) {

			int row = tbCongTrinh.getSelectedRow();

			String ma = modelCongTrinh.getValueAt(row, 0).toString();
			cboCongViec.setSelectedIndex(0);
			chitiet = chiTietDAO.getChiTietTheoMaCT(ma);
			xoaAllmodelLD();
			modelLaoDong.fireTableDataChanged();
			docDuLieuVaoTBLD();
			lblSoLuongLD.setText("" + chitiet.size());

		}
		if (tbLaoDong.getSelectedRow() >= 0 && !cbkCongViec.isSelected()) {

			int row = tbLaoDong.getSelectedRow();
			String ngayTH[] = modelLaoDong.getValueAt(row, 1).toString().split("/");
			String ngayHT[] = modelLaoDong.getValueAt(row, 2).toString().split("/");
			ngayThucHien.setDate(new Date(Integer.parseInt(ngayTH[2]) - 1900, Integer.parseInt(ngayTH[1]) - 1,
					Integer.parseInt(ngayTH[0])));
			ngayHoanThanh.setDate(new Date(Integer.parseInt(ngayHT[2]) - 1900, Integer.parseInt(ngayHT[1]) - 1,
					Integer.parseInt(ngayHT[0])));

		} else if (tbLaoDong.getSelectedRow() == -1) {
			ngayHoanThanh.setDate(null);
			ngayThucHien.setDate(null);
		}

	}

	public void xoaAllmodelLD() {
		DefaultTableModel m = (DefaultTableModel) tbLaoDong.getModel();
		m.getDataVector().removeAllElements();
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
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

		int row = tbCongTrinh.getSelectedRow();
		if (row == -1)
			return;
		String ma = modelCongTrinh.getValueAt(row, 0).toString();

		if (cboCongViec.getSelectedIndex() == 0)
			chitiet = chiTietDAO.getChiTietTheoMaCT(ma);
		else {
			String maCV = listCV.get(cboCongViec.getSelectedIndex() - 1).getMaCongViec();
			chitiet = chiTietDAO.getChiTiet(maCV, ma);
		}
		xoaAllmodelLD();
		modelLaoDong.fireTableDataChanged();
		docDuLieuVaoTBLD();
		lblSoLuongLD.setText("" + chitiet.size());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		int rowCT = tbCongTrinh.getSelectedRow();

		if (o.equals(btnSua)) {
			if (rowCT == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công trình");
				return;
			}
			if (kiemTra()) {
				String maCT = listCT.get(tbCongTrinh.getSelectedRow()).getMaCongTrinh();
				Date th = chuyenNgay(ngayThucHien.getDate());
				Date ht = chuyenNgay(ngayHoanThanh.getDate());
				if (cbkCongViec.isSelected()) {
					if (cboCongViec.getSelectedIndex() != 0) {
//						chọn cbo công việc
						String maCV = listCV.get(cboCongViec.getSelectedIndex() - 1).getMaCongViec();
						ArrayList<ChiTietCV> list = chiTietDAO.getChiTiet(maCV, maCT);
						ArrayList<ChiTietCV> listTrung = new ArrayList<ChiTietCV>();

						for (ChiTietCV chiTietCV : list) {
							if (ktTrungLich(chiTietCV, th, ht).getCongTrinh() != null)
								listTrung.add(chiTietCV);
						}

						if (listTrung.size() == 0) {
							int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa", "Thông báo",
									JOptionPane.YES_NO_OPTION);
							if (kt == JOptionPane.YES_OPTION) {
								if (chiTietDAO.capNhatLL(th, ht, maCT, maCV, "")) {
									JOptionPane.showMessageDialog(null, "Cập nhật thành công");
									xoaAllmodelLD();
									modelLaoDong.fireTableDataChanged();
									chitiet = chiTietDAO.getChiTietTheoMaCT(maCT);
									docDuLieuVaoTBLD();
									tbLaoDong.clearSelection();
									ngayHoanThanh.setDate(null);
									ngayThucHien.setDate(null);
									cbkCongViec.setSelected(false);
									cboCongViec.setEnabled(false);
								} else
									JOptionPane.showMessageDialog(null, "Cập nhật không thành công");
							}

						} else {
							new TrungLich_GUI(listTrung).setVisible(true);
						}

					} else {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn công việc");
						return;
					}

				} else {
//					neu khong chon
					int rowLD = tbLaoDong.getSelectedRow();
					if (rowLD == -1) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn lao động");
						return;
					}
					String maLD = chitiet.get(rowLD).getLaoDong().getMaLaoDong();
					String maCV = chitiet.get(rowLD).getCongViec().getMaCongViec();
					boolean kt = ktTrungLich(chitiet.get(rowLD), ht, th).getCongTrinh() != null;
					if (!kt) {
						int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa", "Thông báo",
								JOptionPane.YES_NO_OPTION);
						if (xacNhan == JOptionPane.YES_OPTION) {
							if (chiTietDAO.capNhatLL(th, ht, maCT, maCV, maLD)) {
								JOptionPane.showMessageDialog(null, "Cập nhật thành công");
								xoaAllmodelLD();
								modelLaoDong.fireTableDataChanged();
								chitiet = chiTietDAO.getChiTietTheoMaCT(maCT);
								docDuLieuVaoTBLD();
								tbLaoDong.clearSelection();
								ngayHoanThanh.setDate(null);
								ngayThucHien.setDate(null);
								cbkCongViec.setSelected(false);
								cboCongViec.setEnabled(false);
							} else {
								JOptionPane.showMessageDialog(null, "Cập nhật không thành công");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Lao động đã bị trùng lịch");
					}
				}
			}

		} else if (o.equals(cbkCongViec)) {
			cboCongViec.setEnabled(cbkCongViec.isSelected());
			tbLaoDong.setRowSelectionAllowed(!cbkCongViec.isSelected());
			ngayHoanThanh.setDate(null);
			ngayThucHien.setDate(null);
		} else if (o.equals(btnlamMoi)) {
			xoaAllmodelLD();
			modelLaoDong.fireTableDataChanged();
			ngayHoanThanh.setDate(null);
			ngayThucHien.setDate(null);
			cbkCongViec.setSelected(false);
			cboCongViec.setEnabled(false);
		}

	}

	@SuppressWarnings("deprecation")
	public Date chuyenNgay(java.util.Date ngay) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String s[] = df.format(ngay).split("/");
		return new Date(Integer.parseInt(s[2]) - 1900, Integer.parseInt(s[1]) - 1, Integer.parseInt(s[0]));
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
		Object o = e.getSource();
		if (o.equals(txtTimMaCT) || o.equals(txtTimTenCT)) {
			xoaAllmodelLD();
			modelLaoDong.fireTableDataChanged();
			tbCongTrinh.clearSelection();
			ngayHoanThanh.setDate(null);
			ngayThucHien.setDate(null);
			listCT = chiTietDAO.timCongTrinhDangLam(txtTimMaCT.getText(), txtTimTenCT.getText());
			xoaAllModel();
			docDuLieuVaoTBCT();
		} else if (o.equals(txtTimTenLD)) {
			if (tbCongTrinh.getSelectedRow() == -1)
				return;

			chitiet = chiTietDAO.timLDDangLamTheoTen(listCT.get(tbCongTrinh.getSelectedRow()).getMaCongTrinh(),
					txtTimTenLD.getText());
			xoaAllmodelLD();
			modelLaoDong.fireTableDataChanged();
			docDuLieuVaoTBLD();
			lblSoLuongLD.setText("" + chitiet.size());
		}
	}

	/**
	 * 
	 * @param ct
	 * @param ngayTH
	 * @param ngayHT
	 * @return chi tiet bi trung
	 */
	public ChiTietCV ktTrungLich(ChiTietCV ct, Date ngayTH, Date ngayHT) {
		ChiTietCV c = new ChiTietCV();
		ArrayList<ChiTietCV> list = new PhanCongLaoDong_DAO().getChiTietCVTheoMaLD(ct.getLaoDong().getMaLaoDong());
		list.remove(ct);
		if (list.size() < 2)
			return c;

		int check = -1;
		for (int i = 0; i < list.size() - 2; i++) {
			if (!(ngayTH.before(list.get(i).getNgayHoanThanh()) && ngayHT.after(list.get(i + 1).getNgayThucHien())))
				check = i;
			else
				check = -1;
		}
		if (check != -1) {
			return list.get(check);
		}
		ChiTietCV f = list.get(0);
		if (!(ngayHT.before(f.getNgayThucHien()) && ngayTH.before(f.getNgayThucHien())))
			return f;
		ChiTietCV l = list.get(list.size() - 1);
		if (!(ngayTH.after(l.getNgayHoanThanh()) && ngayHT.after(l.getNgayHoanThanh())))
			return l;

		return c;
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
		Date th = chuyenNgay(ngayThucHien.getDate());
		Date ht = chuyenNgay(ngayHoanThanh.getDate());
		if (th.after(ht)) {
			JOptionPane.showMessageDialog(null, "Ngày thực hiện phải trước ngày hoàn thành");
			return false;
		}

		int row = tbCongTrinh.getSelectedRow();
		CongTrinh ct = listCT.get(row);

		if (th.before(ct.getNgayKhoiCong())) {
			JOptionPane.showMessageDialog(null, "Ngày thực hiện phải sau ngày khởi công");
			return false;
		}

		return true;
	}
}