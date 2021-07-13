package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import dao.ChiTietCVDAO;
import dao.QLCongViec_DAO;
import dao.QuanLyCongTrinh_DAO;
import dao.QuanLyLaoDong_DAO;
import entity.ChiTietCV;
import entity.CongTrinh;
import entity.CongViec;
import entity.LaoDong;

import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.SystemColor;

public class ChiTietCongTrinh extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel txtMa;
	private JLabel txtLoai;
	private JButton btnThoat;
	private JLabel txtNgayTC;
	private JLabel txtNgayDKHT;
	private JTable table;
	private DefaultTableModel model;
	private QLCongViec_DAO congViecDAO = new QLCongViec_DAO();
	private QuanLyLaoDong_DAO laoDongDAO = new QuanLyLaoDong_DAO();
	private JComboBox<String> cboCongViec;
	private JLabel lblTong;
	private ArrayList<ChiTietCV> listChiTiet = new ArrayList<ChiTietCV>();
	private ChiTietCVDAO chiTietDAO = new ChiTietCVDAO();
	private String maCT;
	private ArrayList<CongViec> listCV = new QLCongViec_DAO().getCongViec();

	/**
	 * Create the frame.
	 */
	public ChiTietCongTrinh(String ma) {
		CongTrinh ct = new QuanLyCongTrinh_DAO().getCTTheoMa(ma);
		setBounds(100, 100, 969, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		listChiTiet = chiTietDAO.getChiTietTheoMaCT(ma);
		this.maCT = ma;

		JLabel lblNewLabel = new JLabel("CHI TIẾT CÔNG TRÌNH");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setBounds(20, 0, 427, 56);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã công trình:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(12, 64, 169, 27);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Tên công trình:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(12, 116, 169, 27);
		contentPane.add(lblNewLabel_3);

		txtMa = new JLabel();
		txtMa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMa.setText(ct.getMaCongTrinh());
		txtMa.setBounds(193, 64, 178, 27);
		contentPane.add(txtMa);

		JLabel lblNewLabel_2 = new JLabel("Loại công trình:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(12, 166, 169, 27);
		contentPane.add(lblNewLabel_2);

		txtLoai = new JLabel();
		txtLoai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtLoai.setText(ct.getLoaiCongTrinh());
		txtLoai.setBounds(193, 169, 178, 27);
		contentPane.add(txtLoai);

		JLabel lblNewLabel_4 = new JLabel("Ngày thi công:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_4.setBounds(12, 217, 169, 27);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Ngày dự kiến hoàn thành:");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5.setBounds(12, 280, 261, 27);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Địa điểm:");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_6.setBounds(12, 365, 96, 27);
		contentPane.add(lblNewLabel_6);

		JTextArea txtDiaDiem = new JTextArea();
		txtDiaDiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDiaDiem.setLineWrap(true);
		txtDiaDiem.setBackground(UIManager.getColor("Button.background"));
		txtDiaDiem.setWrapStyleWord(true);
		txtDiaDiem.setEditable(false);
		txtDiaDiem.setBounds(193, 353, 208, 71);
		contentPane.add(txtDiaDiem);

		btnThoat = new JButton("Thoát");
		btnThoat.setBackground(Color.GRAY);
		btnThoat.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnThoat.setBounds(20, 427, 133, 40);
		contentPane.add(btnThoat);
		btnThoat.setIcon(
				new ImageIcon(ChiTietCongTrinh.class.getResource("/images/baseline_exit_to_app_white_36dp.png")));

		txtNgayTC = new JLabel();
		txtNgayTC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNgayTC.setBounds(193, 220, 178, 27);
		contentPane.add(txtNgayTC);

		txtNgayDKHT = new JLabel();
		txtNgayDKHT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNgayDKHT.setBounds(228, 280, 178, 27);
		contentPane.add(txtNgayDKHT);

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		txtNgayTC.setText(df.format(ct.getNgayKhoiCong()));
		txtNgayDKHT.setText(df.format(ct.getNgayDKHoanThanh()));
		txtDiaDiem.setText(ct.getDiaDiem().getPhuongXa() + ", " + ct.getDiaDiem().getQuanHuyen() + ", "
				+ ct.getDiaDiem().getTinhTP());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(446, 81, 463, 372);
		contentPane.add(scrollPane);

		model = new DefaultTableModel(new String[] { "Họ tên", "Công Việc" }, 0);
		table = new JTable(model);
		scrollPane.setViewportView(table);

		table.setRowHeight(table.getRowHeight() + 20);

		cboCongViec = new JComboBox<String>();
		cboCongViec.setBounds(446, 43, 178, 27);
		contentPane.add(cboCongViec);
		cboCongViec.addItem("Tất cả");
		for (CongViec congViec : listCV) {
			cboCongViec.addItem(congViec.getTenCongViec());
		}

		JLabel lblTng = new JLabel("Tổng:");
		lblTng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTng.setBounds(769, 42, 60, 28);
		contentPane.add(lblTng);

		lblTong = new JLabel("0");
		lblTong.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTong.setBounds(839, 42, 74, 27);
		contentPane.add(lblTong);
		lblTong.setText("" + listChiTiet.size());
		
		JTextArea txtTenCT = new JTextArea();
		txtTenCT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenCT.setWrapStyleWord(true);
		txtTenCT.setText("<dynamic>, <dynamic>, <dynamic>");
		txtTenCT.setLineWrap(true);
		txtTenCT.setEditable(false);
		txtTenCT.setBackground(SystemColor.menu);
		txtTenCT.setBounds(193, 119, 215, 56);
		txtTenCT.setText(ct.getTenCongTrinh());
		
		contentPane.add(txtTenCT);

		table.getTableHeader().setForeground(new Color(31, 39, 191));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		table.getTableHeader().setBackground(new Color(116, 235, 52));
		
		
		btnThoat.addActionListener(this);
		cboCongViec.addActionListener(this);
		docDuLieuVaoBang();

	}

	public void docDuLieuVaoBang() {
		new Thread(() -> {
			for (ChiTietCV chiTietCV : listChiTiet) {
				LaoDong ld = laoDongDAO.getLaoDong(chiTietCV.getLaoDong().getMaLaoDong());
				CongViec cv = congViecDAO.getCongViec(chiTietCV.getCongViec().getMaCongViec());
				model.addRow(new Object[] { ld.getTenLaoDong(), cv.getTenCongViec() });
			}
		}).start();
	}

	public void xoaDuLieu() {
		DefaultTableModel m = (DefaultTableModel) table.getModel();
		m.getDataVector().removeAllElements();
		model.fireTableDataChanged();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThoat))
			this.setVisible(false);
		else {
			if(cboCongViec.getSelectedIndex()==0)
				listChiTiet = chiTietDAO.getChiTietTheoMaCT(this.maCT);
			else {
				String maCV = listCV.get(cboCongViec.getSelectedIndex()-1).getMaCongViec();
				listChiTiet = chiTietDAO.getChiTietTheoMaCV(maCT, maCV);
			}
			xoaDuLieu();
			docDuLieuVaoBang();
			lblTong.setText(""+listChiTiet.size());
		}
	}
}
