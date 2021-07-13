package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.ThongKe_Dao;
import entity.CongTrinh;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;

public class ThongKe_Jpanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton rdbQuy;
	private JRadioButton rdbThang;
	private ButtonGroup group;
	private JComboBox<String> cbonam;
	private JComboBox<String> cboTrangThai;
	private DefaultTableModel modelCT;
	private JButton btnXuatFile;
	private JComboBox<String> cboQuyThang;
	private JTable tbCongTrinh;
	private ThongKe_Dao thongKeDAO = new ThongKe_Dao();
	private ArrayList<CongTrinh> listCT = new ArrayList<CongTrinh>();
	private boolean kt = true;
	private JLabel lblTongSo;
	private JRadioButton rdbTatCa;

	/**
	 * Create the panel.
	 */
	public ThongKe_Jpanel() {
		setBackground(Color.WHITE);
		setLayout(null);

		listCT = new ArrayList<CongTrinh>();

		JLabel lblNewLabel = new JLabel("Năm:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(30, 30, 85, 22);
		add(lblNewLabel);

		cbonam = new JComboBox<String>();
		cbonam.setBounds(88, 33, 194, 22);
		add(cbonam);
		cbonam.addItem("");
		for (int i = LocalDateTime.now().getYear(); i >= LocalDateTime.now().getYear() - 5; i--) {
			cbonam.addItem("" + i);
		}

		JLabel lblNewLabel_1 = new JLabel("Tháng");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(555, 30, 74, 22);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Quý");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(475, 30, 43, 22);
		add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Trạng thái:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(889, 30, 107, 26);
		add(lblNewLabel_3);

		cboTrangThai = new JComboBox<String>();
		cboTrangThai.setBounds(1006, 30, 195, 22);
		add(cboTrangThai);
		cboTrangThai.addItem("Tất cả");
		cboTrangThai.addItem("Hoàn thành");
		cboTrangThai.addItem("Đang thi công");
		cboTrangThai.addItem("Chậm tiến độ");

		JSeparator separator = new JSeparator();
		separator.setBounds(276, 68, 1, 459);
		add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(39, 93, 1249, 8);
		add(separator_1);

		JPanel pnlThongke = new JPanel();
		pnlThongke.setBackground(Color.LIGHT_GRAY);
		pnlThongke.setBounds(10, 104, 1305, 393);
		add(pnlThongke);
		pnlThongke.setLayout(null);

		btnXuatFile = new JButton("Xuất File");
		btnXuatFile.setFocusPainted(false);
		btnXuatFile.setIcon(new ImageIcon(ThongKe_Jpanel.class.getResource("/images/thong_ke.png")));
		btnXuatFile.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXuatFile.setBounds(394, 507, 161, 43);
		add(btnXuatFile);

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFocusPainted(false);
		btnLamMoi.setIcon(new ImageIcon(ThongKe_Jpanel.class.getResource("/images/lam_moi.png")));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLamMoi.setBounds(732, 507, 138, 44);
		add(btnLamMoi);

		rdbQuy = new JRadioButton("");
		rdbQuy.setBackground(Color.WHITE);
		rdbQuy.setBounds(446, 30, 21, 21);
		add(rdbQuy);

		rdbThang = new JRadioButton("");
		rdbThang.setBackground(Color.WHITE);
		rdbThang.setBounds(526, 30, 21, 21);
		add(rdbThang);

		rdbTatCa = new JRadioButton("");
		rdbTatCa.setBackground(Color.WHITE);
		rdbTatCa.setSelected(true);
		rdbTatCa.setBounds(341, 30, 21, 21);
		add(rdbTatCa);

		group = new ButtonGroup();
		group.add(rdbQuy);
		group.add(rdbThang);
		group.add(rdbTatCa);

		modelCT = new DefaultTableModel(new String[] { "Mã công trình", "Tên công trình", "Loại công trình", "Địa điểm",
				"Ngày khởi công", "Ngày DK hoàn thành", "Trạng thái" }, 0) {
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1305, 354);
		pnlThongke.add(scrollPane);

		tbCongTrinh = new JTable(modelCT);
		scrollPane.setViewportView(tbCongTrinh);

		JLabel lblTngS = new JLabel("Tổng số:");
		lblTngS.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTngS.setHorizontalAlignment(SwingConstants.CENTER);
		lblTngS.setBounds(0, 365, 88, 23);
		pnlThongke.add(lblTngS);

		lblTongSo = new JLabel("0");
		lblTongSo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongSo.setBounds(98, 365, 57, 20);
		pnlThongke.add(lblTongSo);

		rdbQuy.addActionListener(this);
		rdbThang.addActionListener(this);
		rdbTatCa.addActionListener(this);
		btnXuatFile.addActionListener(this);

		cbonam.addActionListener(this);

		cboTrangThai.addActionListener(this);

		cboQuyThang = new JComboBox<String>();
		cboQuyThang.setBounds(635, 30, 205, 23);
		add(cboQuyThang);
		cboQuyThang.addActionListener(this);

		lblTongSo.setText(listCT.size() + "");

		tbCongTrinh.setRowHeight(tbCongTrinh.getRowHeight() + 20);

		JLabel lblNewLabel_2_1 = new JLabel("Tất cả");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(370, 30, 74, 22);
		add(lblNewLabel_2_1);
		tbCongTrinh.getTableHeader().setBackground(new Color(116, 235, 52));
		tbCongTrinh.getTableHeader().setForeground(new Color(31, 39, 191));
		tbCongTrinh.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));

		tbCongTrinh.getColumnModel().getColumn(0).setPreferredWidth(30);
		tbCongTrinh.getColumnModel().getColumn(1).setPreferredWidth(150);
		tbCongTrinh.getColumnModel().getColumn(2).setPreferredWidth(50);
		tbCongTrinh.getColumnModel().getColumn(3).setPreferredWidth(350);
		tbCongTrinh.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbCongTrinh.getColumnModel().getColumn(5).setPreferredWidth(50);
	}

	public void docDuLieuVaoModelCT() {
		for (CongTrinh ct : listCT) {
			modelCT.addRow(new Object[] { ct.getMaCongTrinh(), ct.getTenCongTrinh(), ct.getLoaiCongTrinh(),
					ct.getDiaDiem(), ct.getNgayKhoiCong(), ct.getNgayDKHoanThanh(), ct.getTrangThai() });
		}

	}

	public void xoaAllModel() {
		DefaultTableModel m = (DefaultTableModel) tbCongTrinh.getModel();
		m.getDataVector().removeAllElements();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(rdbQuy)) {
			kt = false;
			cboQuyThang.removeAllItems();
			for (int i = 1; i <= 4; i++)
				cboQuyThang.addItem("" + i);
			if (cbonam.getSelectedIndex() == 0)
				listCT = new ArrayList<CongTrinh>();
			else {
				int check;
				if (rdbTatCa.isSelected())
					check = 0;
				else if (rdbQuy.isSelected())
					check = 1;
				else
					check = 2;

				listCT = thongKeDAO.getAllCongTrinh(Integer.parseInt(cbonam.getSelectedItem().toString()),
						rdbTatCa.isSelected() ? 1 : Integer.parseInt(cboQuyThang.getSelectedItem().toString()), check,
						cboTrangThai.getSelectedIndex());

			}
			xoaAllModel();
			docDuLieuVaoModelCT();
			modelCT.fireTableDataChanged();
			kt = true;
		} else if (o.equals(rdbThang)) {
			kt = false;
			cboQuyThang.removeAllItems();
			for (int i = 1; i < 13; i++)
				cboQuyThang.addItem("" + i);
			if (cbonam.getSelectedIndex() == 0)
				listCT = new ArrayList<CongTrinh>();
			else {
				int check;
				if (rdbTatCa.isSelected())
					check = 0;
				else if (rdbQuy.isSelected())
					check = 1;
				else
					check = 2;
				listCT = thongKeDAO.getAllCongTrinh(Integer.parseInt(cbonam.getSelectedItem().toString()),
						rdbTatCa.isSelected() ? 1 : Integer.parseInt(cboQuyThang.getSelectedItem().toString()), check,
						cboTrangThai.getSelectedIndex());
			}

			xoaAllModel();
			docDuLieuVaoModelCT();
			modelCT.fireTableDataChanged();
			kt = true;
		} else if (o.equals(rdbTatCa)) {
			kt = false;
			cboQuyThang.removeAllItems();
			int check;
			if (rdbTatCa.isSelected())
				check = 0;
			else if (rdbQuy.isSelected())
				check = 1;
			else
				check = 2;
			if (cbonam.getSelectedIndex() == 0)
				listCT = new ArrayList<CongTrinh>();
			else
				listCT = thongKeDAO.getAllCongTrinh(Integer.parseInt(cbonam.getSelectedItem().toString()),
						rdbTatCa.isSelected() ? 1 : Integer.parseInt(cboQuyThang.getSelectedItem().toString()), check,
						cboTrangThai.getSelectedIndex());
			xoaAllModel();
			docDuLieuVaoModelCT();
			modelCT.fireTableDataChanged();
			lblTongSo.setText("" + listCT.size());
			kt = true;

		} else if (o.equals(btnXuatFile)) {
			
			if(listCT.size()==0) {
				JOptionPane.showMessageDialog(null, "Không có nội dung để xuất");
				return;
			}
			
			JFileChooser fChooser = new JFileChooser();

			fChooser.addChoosableFileFilter(new FileFilter() {

				@Override
				public String getDescription() {
					// TODO Auto-generated method stub
					return "Excel file (*.xls, *xlsx)";
				}

				@Override
				public boolean accept(File f) {
					// TODO Auto-generated method stub
					if (f.isDirectory()) {
						return true;
					} else {
						return f.getName().toLowerCase().endsWith(".xls")
								|| f.getName().toLowerCase().endsWith(".xlsx");
					}
				}
			});

			int i = fChooser.showSaveDialog(this);
			if (i == 0) {
				String path = fChooser.getSelectedFile().getAbsolutePath();
				
				if (!path.matches("(.)+(\\.xls|\\.xlsx)$")) {
					path += ".xlsx";
				}
				int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn xuất file", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if (xacNhan == JOptionPane.YES_OPTION) {
					boolean t = ghiFileExcel(path);
					if (t) {
						JOptionPane.showMessageDialog(null, "Thành công");
						xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn xem file", "Thông báo", JOptionPane.YES_NO_OPTION);
						if(xacNhan==JOptionPane.YES_OPTION)
							try {
								Desktop.getDesktop().open(new File(fChooser.getSelectedFile().getParent()));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
					else
						JOptionPane.showMessageDialog(null, "Không thành công");
				}
			}

		} else if (o.equals(cbonam) || o.equals(cboTrangThai) || o.equals(cboQuyThang)) {
			if (kt) {
				if (cbonam.getSelectedIndex() == 0) {
					listCT = new ArrayList<CongTrinh>();
				} else {
					int check;
					if (rdbTatCa.isSelected())
						check = 0;
					else if (rdbQuy.isSelected())
						check = 1;
					else
						check = 2;

					listCT = thongKeDAO.getAllCongTrinh(Integer.parseInt(cbonam.getSelectedItem().toString()),
							rdbTatCa.isSelected() ? 1 : Integer.parseInt(cboQuyThang.getSelectedItem().toString()),
							check, cboTrangThai.getSelectedIndex());
				}

				xoaAllModel();
				docDuLieuVaoModelCT();
				modelCT.fireTableDataChanged();
				lblTongSo.setText("" + listCT.size());

			}
		}
	}

	public boolean ghiFileExcel(String path) {
		Workbook workBook = new XSSFWorkbook();

		Sheet sh = workBook.createSheet("Sheet1");
		String header[] = { "Mã công trình", "Tên công trình", "Loại công trình", "Địa điểm", "Ngày khởi công",
				"Ngày dự kiến hoàn thành" };

		Row rowHeader = sh.createRow(0);
		for (int i = 0; i < header.length; i++) {
			Cell cell = rowHeader.createCell(i);
			cell.setCellValue(header[i]);
		}

		int numRow = 1;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CongTrinh ct : listCT) {
			Row row = sh.createRow(numRow++);
			row.createCell(0).setCellValue(ct.getMaCongTrinh());
			row.createCell(1).setCellValue(ct.getTenCongTrinh());
			row.createCell(2).setCellValue(ct.getLoaiCongTrinh());
			row.createCell(3).setCellValue(
					ct.getDiaDiem().getPhuongXa()+" " + ct.getDiaDiem().getQuanHuyen()+" " + ct.getDiaDiem().getTinhTP());
			row.createCell(4).setCellValue(df.format(ct.getNgayKhoiCong()));
			row.createCell(5).setCellValue(df.format(ct.getNgayDKHoanThanh()));
		}

		for (int i = 0; i < header.length; i++)
			sh.autoSizeColumn(i);

		try {
			FileOutputStream f = new FileOutputStream(path);
			workBook.write(f);
			f.close();
			workBook.close();
		} catch (Exception e) {
			return false;
		}
		return true;

	}
}
