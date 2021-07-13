package gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JWindow;
import javax.swing.JEditorPane;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.util.Random;

public class About_PCLD_Jfm extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JProgressBar pgb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		About_PCLD_Jfm about = new About_PCLD_Jfm();
		about.setVisible(true);
		init(); 
		about.setVisible(false);
		new DangNhap().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public About_PCLD_Jfm() {
		setBounds(100, 100, 691, 366);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnThongTin = new JPanel();
		pnThongTin.setBounds(290, 10, 357, 282);
		contentPane.add(pnThongTin);
		pnThongTin.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 26, 270, 205);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(About_PCLD_Jfm.class.getResource("/images/phancong.jpg")));

		JEditorPane edThongTin = new JEditorPane();
		edThongTin.setEditable(false);
		edThongTin.setBounds(0, 0, 354, 282);
		pnThongTin.add(edThongTin);
		edThongTin.setContentType("text/html");
		edThongTin.setText("<html lang=\"en\">\r\n" + "<head>\r\n" + "    <meta charset=\"UTF-8\">\r\n" + "</head>\r\n"
				+ "<body>\r\n" + "    <h1>Chương trình quản lí lao động</h1>" + "    <p>Version: 2020-12 (1.0.0)</p>"
				+ "    <p>Build id: 20201220-00001</p>" + "    <h2>Đội ngũ phát triển: </h2>" + "    <ul>\r\n"
				+ "        <li><a> Đặng Văn Nghiêm</li>\r\n" + "        <li> Trương Đình Phước</li>\r\n"
				+ "        <li> Trần Mỹ Linh</li>\r\n" + "        <li> Nguyễn Thị Thanh Thúy</li>\r\n" + "    </ul>\r\n"
				+ "    </br>\r\n" + "</body>\r\n" + "</html>");

		pgb = new JProgressBar();
		pgb.setBackground(Color.WHITE);
		pgb.setStringPainted(true);
		pgb.setBounds(54, 320, 619, 14);
		contentPane.add(pgb);
	}

	public static void init() {
		int j = new Random().nextInt(5)+1;
		try {
			for (int i = 0; i <= 100; i+=j) {
				Thread.sleep(20);
				pgb.setValue(i);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
