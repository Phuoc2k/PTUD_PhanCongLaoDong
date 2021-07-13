package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.QLCongViec_DAO;
import entity.ChiTietCV;
import entity.CongTrinh;
import entity.CongViec;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;

public class QuanLiCongViecCongTrinh_Jfm extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel modelCV;
	private JTable tbCV;
	private JComboBox<String> cboTrangThai;
	private List<CongViec> listCV = new ArrayList<CongViec>();
	private QLCongViec_DAO qlCVDao = new QLCongViec_DAO();
	private List<ChiTietCV> listCTiet = new ArrayList<ChiTietCV>();
	private String ma;
	private JButton btnHoanThanh;

	/**
	 * Create the frame.
	 */
	public QuanLiCongViecCongTrinh_Jfm(CongTrinh congTrinh) {
		setBounds(100, 100, 703, 522);
		setLocationRelativeTo(null);
		setTitle("Quản Lí Công Việc Công Trình");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên công trình:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(22, 10, 131, 27);
		contentPane.add(lblNewLabel);

		JLabel lblTenCongTrinh = new JLabel("");
		lblTenCongTrinh.setBounds(145, 10, 534, 27);
		contentPane.add(lblTenCongTrinh);
		lblTenCongTrinh.setText(congTrinh.getTenCongTrinh().trim());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 95, 641, 322);
		contentPane.add(scrollPane);
		modelCV = new DefaultTableModel(new String[] { "Mã công việc", "Tên công việc", "Trạng thái" }, 0) {
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
		tbCV = new JTable(modelCV);
		scrollPane.setViewportView(tbCV);

		btnHoanThanh = new JButton("Cập nhật");
		btnHoanThanh.setFocusPainted(false);
		btnHoanThanh.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnHoanThanh.setBounds(22, 436, 98, 27);
		contentPane.add(btnHoanThanh);

		JLabel lblNewLabel_1 = new JLabel("Trạng Thái:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(22, 47, 131, 27);
		contentPane.add(lblNewLabel_1);

		cboTrangThai = new JComboBox<String>();
		cboTrangThai.setBounds(155, 47, 206, 27);
		contentPane.add(cboTrangThai);
		cboTrangThai.addItem("Chưa hoàn thành");
		cboTrangThai.addItem("Hoàn thành");

		ma = congTrinh.getMaCongTrinh();
		listCTiet = qlCVDao.getChiTietTheoMaCT(ma);

		String ma = "";
		for (ChiTietCV ct : listCTiet) {
			if (ma.equals(ct.getCongViec().getMaCongViec()) == false) {
				listCV.add(qlCVDao.getCongViec(ct.getCongViec().getMaCongViec()));
				ma = ct.getCongViec().getMaCongViec();
			}
		}
		docDuLieuVaoModelCV();

		tbCV.getTableHeader().setBackground(new Color(116, 235, 52));
		tbCV.getTableHeader().setForeground(new Color(31, 39, 191));
		tbCV.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		tbCV.setRowHeight(tbCV.getRowHeight() + 20);

		tbCV.addMouseListener(this);
		btnHoanThanh.addActionListener(this);
	}

	public void docDuLieuVaoModelCV() {
		for (CongViec congViec : listCV) {
			List<ChiTietCV> list = new ArrayList<ChiTietCV>();
			list = qlCVDao.getChiTiet(congViec.getMaCongViec(), ma);

			modelCV.addRow(new Object[] { congViec.getMaCongViec(), congViec.getTenCongViec(),
					list.get(0).isTrangThai() ? "Hoàn thành" : "Chưa hoàn thành" });
		}
	}

	public void xoaAllModelCV() {
		DefaultTableModel m = (DefaultTableModel) tbCV.getModel();
		m.getDataVector().removeAllElements();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tbCV.getSelectedRow();
		if (row >= 0) {
			cboTrangThai.setSelectedItem(tbCV.getValueAt(row, 2).toString());
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
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnHoanThanh)) {
			int row = tbCV.getSelectedRow();
			if (row >= 0) {
				int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn sủa", "Thông báo", JOptionPane.YES_NO_OPTION);
				if (kt == JOptionPane.YES_OPTION) {
					if (cboTrangThai.getSelectedItem().toString() == modelCV.getValueAt(row, 2).toString()) {
						JOptionPane.showMessageDialog(null, "Không thể cập nhật vì trùng trạng thái");
						return;
					} else if (cboTrangThai.getSelectedIndex() == 0) {
						qlCVDao.capNhatTrangThaiCB(ma, modelCV.getValueAt(row, 0).toString(), false);
						xoaAllModelCV();
						docDuLieuVaoModelCV();
						JOptionPane.showMessageDialog(null, "Cập nhật thành công");
					} else if (cboTrangThai.getSelectedIndex() == 1) {
						qlCVDao.capNhatTrangThaiCB(ma, modelCV.getValueAt(row, 0).toString(), true);
						xoaAllModelCV();
						docDuLieuVaoModelCV();
						JOptionPane.showMessageDialog(null, "Cập nhật thành công");

					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công việc cần sửa");
				return;
			}
		}

	}
}
