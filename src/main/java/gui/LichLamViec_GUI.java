package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ChiTietCVDAO;
import dao.QLCongViec_DAO;
import dao.ThongTinLaoDong_DAO;
import entity.ChiTietCV;
import entity.CongTrinh;
import entity.DiaDiem;
import entity.LaoDong;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class LichLamViec_GUI extends JFrame implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txtDiaChi;
	private JLabel lblNgayHT;
	private JLabel lblNgayTH;
	private JComboBox<String> cboCongTrinh;
	private JComboBox<String> cboCongViec;
	private ArrayList<CongTrinh> listCT = new ArrayList<CongTrinh>();
	private ArrayList<ChiTietCV> listChiTiet = new ArrayList<ChiTietCV>();
	private ChiTietCVDAO chiTietDAO = new ChiTietCVDAO();
	private LaoDong laoDong;
	private boolean kt = true;

	/**
	 * Create the frame.
	 */
	public LichLamViec_GUI(LaoDong ld) {
		this.laoDong = ld;
		listCT = new ThongTinLaoDong_DAO().getCongTrinh(ld.getMaLaoDong());
		setBounds(100, 100, 503, 572);
		setTitle("Lịch làm việc");
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLchLmVic = new JLabel("LỊCH LÀM VIỆC");
		lblLchLmVic.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblLchLmVic.setForeground(Color.blue);
		lblLchLmVic.setBounds(152, 11, 225, 49);
		contentPane.add(lblLchLmVic);

		JLabel lblCngTrnh = new JLabel("Công trình:");
		lblCngTrnh.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCngTrnh.setBounds(10, 126, 102, 26);
		contentPane.add(lblCngTrnh);

		cboCongTrinh = new JComboBox<String>();
		cboCongTrinh.setBounds(122, 126, 339, 26);
		contentPane.add(cboCongTrinh);

		JLabel lblCngVic = new JLabel("Công việc:");
		lblCngVic.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCngVic.setBounds(10, 189, 102, 26);
		contentPane.add(lblCngVic);

		cboCongViec = new JComboBox<String>();
		cboCongViec.setBounds(122, 189, 339, 26);
		contentPane.add(cboCongViec);

		JLabel lblNgyThcHin = new JLabel("Ngày thực hiện:");
		lblNgyThcHin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNgyThcHin.setBounds(10, 268, 136, 26);
		contentPane.add(lblNgyThcHin);

		JLabel lblNgyHonThnh = new JLabel("Ngày hoàn thành:");
		lblNgyHonThnh.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNgyHonThnh.setBounds(10, 325, 153, 26);
		contentPane.add(lblNgyHonThnh);

		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblaCh.setBounds(10, 396, 84, 26);
		contentPane.add(lblaCh);

		lblNgayTH = new JLabel("");
		lblNgayTH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgayTH.setBounds(152, 268, 298, 26);
		contentPane.add(lblNgayTH);

		lblNgayHT = new JLabel("");
		lblNgayHT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgayHT.setBounds(163, 325, 298, 26);
		contentPane.add(lblNgayHT);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(118, 339, 9, 13);
		contentPane.add(textArea);

		txtDiaChi = new JTextArea();
		txtDiaChi.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtDiaChi.setWrapStyleWord(true);
		txtDiaChi.setLineWrap(true);
		txtDiaChi.setBounds(122, 396, 333, 49);
		contentPane.add(txtDiaChi);
		
		JLabel lblKhong = new JLabel("");
		lblKhong.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKhong.setForeground(Color.RED);
		lblKhong.setBounds(97, 68, 312, 26);
		contentPane.add(lblKhong);

		for (CongTrinh congTrinh : listCT) {
			cboCongTrinh.addItem(congTrinh.getTenCongTrinh());
		}

		if (listCT.size() > 0) {
			DiaDiem dd = listCT.get(0).getDiaDiem();
			txtDiaChi.setText(dd.getPhuongXa() + " " + dd.getQuanHuyen() + " " + dd.getTinhTP());
 
			listChiTiet = chiTietDAO.timDanhSachCongViecDangLam(ld.getMaLaoDong(), listCT.get(0).getMaCongTrinh());
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			for (ChiTietCV chiTiet : listChiTiet) {
				cboCongViec.addItem(
						new QLCongViec_DAO().getCongViec(chiTiet.getCongViec().getMaCongViec()).getTenCongViec());
			}
			lblNgayTH.setText(df.format(listChiTiet.get(0).getNgayThucHien()));
			lblNgayHT.setText(df.format(listChiTiet.get(0).getNgayHoanThanh()));
		}
		else {
			lblKhong.setText("Bạn chưa được phân công");
		}
		cboCongTrinh.addItemListener(this);
		cboCongViec.addItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(cboCongTrinh)) {
			kt = false;
			cboCongViec.removeAllItems();
			String maCT = listCT.get(cboCongTrinh.getSelectedIndex()).getMaCongTrinh();
			String maLD = laoDong.getMaLaoDong();
			listChiTiet = chiTietDAO.timDanhSachCongViecDangLam(maLD, maCT);
			for (ChiTietCV chiTiet : listChiTiet) {
				cboCongViec.addItem(
						new QLCongViec_DAO().getCongViec(chiTiet.getCongViec().getMaCongViec()).getTenCongViec());

			}
			kt = true;
			DiaDiem dd = listCT.get(cboCongTrinh.getSelectedIndex()).getDiaDiem();
			txtDiaChi.setText(dd.getPhuongXa() + " " + dd.getQuanHuyen() + " " + dd.getTinhTP());

			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			lblNgayTH.setText(df.format(listChiTiet.get(0).getNgayThucHien()));
			lblNgayHT.setText(df.format(listChiTiet.get(0).getNgayHoanThanh()));
		} else {
			if (kt) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				lblNgayTH.setText(df.format(listChiTiet.get(cboCongViec.getSelectedIndex()).getNgayThucHien()));
				lblNgayHT.setText(df.format(listChiTiet.get(cboCongViec.getSelectedIndex()).getNgayHoanThanh()));
			}
		}

	}
}
