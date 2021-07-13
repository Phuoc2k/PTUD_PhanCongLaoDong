package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.QuanLyLaoDong_DAO;
import entity.ChiTietCV;
import entity.LaoDong;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class TrungLich_GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JLabel lblTng;
	private JLabel lblTong;


	public TrungLich_GUI(ArrayList<ChiTietCV> list) {
		setBounds(100, 100, 606, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDanhSchLao = new JLabel("Danh Sách Lao Động Trùng Lịch");
		lblDanhSchLao.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDanhSchLao.setBounds(167, 11, 265, 60);
		contentPane.add(lblDanhSchLao);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 75, 537, 386);
		contentPane.add(scrollPane);
		
		model = new DefaultTableModel(new String[] {"Tên lao động", "Ngày thực hiện", "Ngày hoàn thành"}, 0) {
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
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		table.getTableHeader().setForeground(new Color(31, 39, 191));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		table.getTableHeader().setBackground(new Color(116, 235, 52));
		
		table.setRowHeight(table.getRowHeight()+20);
		
		lblTng = new JLabel("Tổng:");
		lblTng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTng.setBounds(28, 472, 46, 21);
		contentPane.add(lblTng);
		
		lblTong = new JLabel(""+list.size());
		lblTong.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTong.setBounds(82, 472, 46, 21);
		contentPane.add(lblTong);
	
		SimpleDateFormat df  = new SimpleDateFormat("dd/MM/yyyy");
		for (ChiTietCV ctCV : list) {
			LaoDong nd = new QuanLyLaoDong_DAO().getLaoDong(ctCV.getLaoDong().getMaLaoDong());
			model.addRow(new Object[] { nd.getTenLaoDong(), df.format(ctCV.getNgayThucHien()),
					df.format(ctCV.getNgayHoanThanh()) });
		}
	}
}
