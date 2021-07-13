package gui;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JTree;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import java.awt.BorderLayout;
import java.awt.Color;

public class TroGiup extends JFrame implements TreeSelectionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTree tree;
	private JEditorPane edit;
	private DefaultMutableTreeNode nodeQLLaoDong;
	private DefaultMutableTreeNode nodeThemLD;
	private DefaultMutableTreeNode nodeXoaLD;
	private DefaultMutableTreeNode nodeSuaLD;
	private DefaultMutableTreeNode nodeQLCongTrinh;
	private DefaultMutableTreeNode nodeThemCT;
	private DefaultMutableTreeNode nodeSuaCT;
	private DefaultMutableTreeNode nodePhanCongLD;
	private DefaultMutableTreeNode nodeChuyenLD;
	private DefaultMutableTreeNode nodeQLLichLam;
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode nodeQLCongViec;
	private DefaultMutableTreeNode nodeThongKe;
	private DefaultMutableTreeNode nodeThemCV;
	private DefaultMutableTreeNode nodeCapNhatCV;
	private DefaultMutableTreeNode nodeCapNhatTrangThaiCV;
	private DefaultMutableTreeNode nodeQLPhanCong;

	public TroGiup(int page) {
		setTitle("Trợ giúp");
		setResizable(false);
		setBounds(100, 100, 1211, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 251, 618);
		contentPane.add(panel);
		panel.setLayout(null);

		root = new DefaultMutableTreeNode("Trợ giúp");

		nodeQLLaoDong = new DefaultMutableTreeNode("Quản lý lao động");
		nodeThemLD = new DefaultMutableTreeNode("Thêm lao động");
		nodeXoaLD = new DefaultMutableTreeNode("Nghỉ việc");
		nodeSuaLD = new DefaultMutableTreeNode("Sửa lao động");
		nodeQLCongTrinh = new DefaultMutableTreeNode("Quản lý công trình");
		nodeThemCT = new DefaultMutableTreeNode("Thêm công trình");
		nodeSuaCT = new DefaultMutableTreeNode("Sửa công trình");
		nodePhanCongLD = new DefaultMutableTreeNode("Phân công");
		nodeChuyenLD = new DefaultMutableTreeNode("Chuyển lao động");
		nodeQLLichLam = new DefaultMutableTreeNode("Quản lý lịch làm việc");
		nodeThongKe = new DefaultMutableTreeNode("Thống kê");
		nodeQLCongViec = new DefaultMutableTreeNode("Quản lý công việc");
		nodeThemCV = new DefaultMutableTreeNode("Thêm công việc");
		nodeCapNhatCV = new DefaultMutableTreeNode("Cập nhật thông tin công việc");
		nodeCapNhatTrangThaiCV = new DefaultMutableTreeNode("Cập nhật trạng thái công việc");
		nodeQLPhanCong = new DefaultMutableTreeNode("Quản lý phân công");
		
		
		nodeQLLaoDong.add(nodeThemLD);
		nodeQLLaoDong.add(nodeXoaLD);
		nodeQLLaoDong.add(nodeSuaLD);
		root.add(nodeQLLaoDong);

		nodeQLCongTrinh.add(nodeThemCT);
		nodeQLCongTrinh.add(nodeSuaCT);
		root.add(nodeQLCongTrinh);
		
		nodeQLPhanCong.add(nodePhanCongLD);
		nodeQLPhanCong.add(nodeChuyenLD);
		root.add(nodeQLPhanCong);
		
		nodeQLCongViec.add(nodeThemCV);
		nodeQLCongViec.add(nodeCapNhatCV);
		nodeQLCongViec.add(nodeCapNhatTrangThaiCV);
		root.add(nodeQLCongViec);
		
		root.add(nodeQLLichLam);
		root.add(nodeThongKe);
		
		tree = new JTree(root);

		tree.setBounds(0, 45, 248, 500);
		panel.add(tree);

		JLabel lblNewLabel = new JLabel("Trợ giúp");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(0, 0, 248, 43);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(249, 0, 946, 618);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		edit = new JEditorPane();
		edit.setEditable(false);

		JScrollPane sc = new JScrollPane(edit);

		panel_1.add(sc);

		edit.setContentType("text/html");
		edit.setBackground(Color.white);
		tree.addTreeSelectionListener(this);
		if(page==0)
			tree.setSelectionPath(new TreePath(nodeThemLD.getPath()));
		else if(page==1)
			tree.setSelectionPath(new TreePath(nodeThemCT.getPath()));
		else if(page==2)
			tree.setSelectionPath(new TreePath(nodePhanCongLD.getPath()));
		else if(page==3)
			tree.setSelectionPath(new TreePath(nodeChuyenLD.getPath()));
		else if(page==4)
			tree.setSelectionPath(new TreePath(nodeThemCV.getPath()));
		else if(page==5)
			tree.setSelectionPath(new TreePath(nodeThongKe.getPath()));
		else
			tree.setSelectionPath(new TreePath(nodeQLCongViec.getPath()));
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		String sa = e.getNewLeadSelectionPath().getLastPathComponent().toString();
		if (sa.equals("Thêm lao động")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 20px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 20px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Nhập thông tin cần thiết</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/themld1.jpg")+"\">\r\n" + 
					"			<h2>Bước 2: Ấn nút thêm</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/themld2.jpg")+"\">\r\n" + 
					"			<h2>Bước 3: Bạn xác nhận lại</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/themld3.jpg")+"\">\r\n" + 
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");

		} else if (sa.equals("Nghỉ việc")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 20px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 10px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Chọn lao động</h2>\r\n" + 
					"			<img  src=\""+TroGiup.class.getResource("/images/xoa1.jpg")+"\">\r\n" + 
					"			<h2>Bước 2: Ấn nút nghỉ việc</h2>\r\n" + 
					"			<img  src=\""+TroGiup.class.getResource("/images/xoa2.jpg")+"\">\r\n" + 
					"			<h2>Bước 3: Bạn xác nhận lại</h2>\r\n" + 
					"			<img  src=\""+TroGiup.class.getResource("/images/xoa3.jpg")+"\">\r\n" + 
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		} else if (sa.equals("Sửa lao động")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 20px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 10px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Chọn lao động cần sửa</h2>\r\n" + 
					"			<img  src=\""+TroGiup.class.getResource("/images/suald1.jpg")+"\">\r\n" + 
					"			<h2>Bước 2: Sửa thông tin cần thiết mà bạn muốn sửa</h2>\r\n" + 
					"			<img  src=\""+TroGiup.class.getResource("/images/suald2.jpg")+"\">\r\n" + 
					"			<h2>Bước 3: Nhấn nút cấp nhật</h2>\r\n" + 
					"			<img  src=\""+TroGiup.class.getResource("/images/suald3.jpg")+"\">\r\n" + 
					"			<h2>Bước 4: Bạn xác nhận lại</h2>\r\n" + 
					"			<img width=850 src=\""+TroGiup.class.getResource("/images/suald4.jpg")+"\">\r\n" + 
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		} else if (sa.equals("Thêm công trình")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 20px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 10px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Nhập thông tin cần thiết</h2>\r\n" + 
					"			<img  src=\""+TroGiup.class.getResource("/images/themct1.jpg")+"\">\r\n" + 
					"			<h2>Bước 2: Nhấn nút thêm</h2>\r\n" + 
					"			<img  src=\""+TroGiup.class.getResource("/images/themct2.jpg")+"\">\r\n" + 
					"			<h2>Bước 3: Bạn xác nhận lại</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/themct3.jpg")+"\">\r\n" + 
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");

		} else if (sa.equals("Sửa công trình")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 20px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 10px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Bạn chọn công trình cần sửa</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/suact1.jpg")+"\">\r\n" + 
					"			<h2>Bước 2: Nhập thông tin mới cho công trình</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/suact2.jpg")+"\">\r\n" + 
					"			<h2>Bước 3: Nhấn nút sửa</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/suact3.jpg")+"\">\r\n" + 
					"			<h2>Bước 4: Bạn xác nhận lại</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/suact4.jpg")+"\">\r\n" + 
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");

		} else if (sa.equals("Phân công")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 20px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 10px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Chọn công trình cần phân công</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/phancong1.jpg")+"\">\r\n" + 
					"			<h2>Bước 2: Chọn công việc, ngày thực hiện , ngày hoàn thành</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/phancong2.jpg")+"\">\r\n" + 
					"			<h2>Bước 3: Chọn lao động mà bạn muốn phân công</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/phancong3.jpg")+"\">\r\n" + 
					"			<h2>Bước 4: Nhấn vào mũi tên để thêm lao động vào danh sách</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/phancong4.jpg")+"\">\r\n" + 
					"			<h2>Bước 5: Nhấn nút phân công</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/phancong5.jpg")+"\">\r\n" + 
					"			<h2>Bước 6: Xác nhận lại</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/phancong6.jpg")+"\">\r\n" +
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");

		} else if (sa.equals("Chuyển lao động")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 20px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 10px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Chọn công trình có lao động cần chuyển</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/chuyenld1.jpg")+"\">\r\n" + 
					"			<h2>Bước 2: Chọn công trình cần nhận thêm lao động, công việc, ngày thực hiện, ngày hoàn thành</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/chuyenld2.jpg")+"\">\r\n" + 
					"			<h2>Bước 3: Chọn lao động mà bạn muốn chuyển</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/chuyenld3.jpg")+"\">\r\n" + 
					"			<h2>Bước 4: Nhấn vào mũi tên để thêm lao động vào danh sách</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/chuyenld4.jpg")+"\">\r\n" + 
					"			<h2>Bước 5: Nhấn nút chuyển</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/chuyenld5.jpg")+"\">\r\n" + 
					"			<h2>Bước 6: Xác nhận lại</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/chuyenld6.jpg")+"\">\r\n" + 
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");

		} else if (sa.equals("Quản lý lịch làm việc")) {
			edit.setText("<html>\r\n" + 
					"<head>\r\n" + 
					"	<title></title>\r\n" + 
					"	<style type=\"text/css\">\r\n" + 
					"		div{\r\n" + 
					"			margin-left: 20px;\r\n" + 
					"		}\r\n" + 
					"		h2{\r\n" + 
					"			padding: 10px;\r\n" + 
					"		}\r\n" + 
					"	</style>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<div>\r\n" + 
					"		<h2>Bước 1: chọn công trình cần sửa thời gian làm việc</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/lichlamviec1.jpg")+"\">\r\n" + 
					"		<h2>Bước 2:</h2>\r\n" + 
					"		<ul>\r\n" + 
					"			<li>\r\n" + 
					"				<h3>Bạn có thể chọn sửa theo công việc</h3>\r\n" + 
					"				<img src=\""+TroGiup.class.getResource("/images/lichlamviec2.jpg")+"\">\r\n" + 
					"			</li>\r\n" + 
					"			<li>\r\n" + 
					"				<h3>Hoặc bạn có thể chọn sửa cho từng lao động</h3>\r\n" + 
					"				<img src=\""+TroGiup.class.getResource("/images/lichlamviec3.jpg")+"\">\r\n" + 
					"			</li>\r\n" + 
					"		</ul>\r\n" + 
					"		<h2>Bước 3: Bạn chọn ngày thực hiện và ngày hoàn thành</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/lichlamviec4.jpg")+"\">\r\n" + 
					"		<h2>Bước 4: Nhấn nút sửa</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/lichlamviec5.jpg")+"\">\r\n" + 
					"		<h2>Bước 5: Chọn yes nếu đồng ý sửa</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/lichlamviec6.jpg")+"\">\r\n" + 
					"	</div>\r\n" + 
					"</body>\r\n" + 
					"</html>");
		} else if (sa.equals("Thống kê")) {
			edit.setText("<html>\r\n" + 
					"<head>\r\n" + 
					"	<title></title>\r\n" + 
					"	<style type=\"text/css\">\r\n" + 
					"		div{\r\n" + 
					"			margin-left: 20px;\r\n" + 
					"		}\r\n" + 
					"		h2{\r\n" + 
					"			padding: 10px;\r\n" + 
					"		}\r\n" + 
					"	</style>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<div>\r\n" + 
					"		<h2>Bước 1: Chọn các tiêu chí cần thống kê</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/thongke1.jpg")+"\">\r\n" + 
					"		<h2>Bước 2: Nhấn nút xuất file</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/thongke2.jpg")+"\">\r\n" + 
					"		<h2>Bước 3: chọn đường dẫn file và nhập tên file</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/thongke3.jpg")+"\">\r\n" + 
					"		<h2>Bước 4: Nhấn nút save để lưu</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/thongke4.jpg")+"\">\r\n" + 
					"		<h2>Bước 5: Chọn yes để xác nhận lại</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/thongke5.jpg")+"\">\r\n" + 
					"	</div>\r\n" + 
					"</body>\r\n" + 
					"</html>");

		} else if (sa.equals("Thêm công việc")) {
			edit.setText("<html>\r\n" + 
					"<head>\r\n" + 
					"	<title></title>\r\n" + 
					"	<style type=\"text/css\">\r\n" + 
					"		div{\r\n" + 
					"			margin-left: 20px;\r\n" + 
					"		}\r\n" + 
					"		h2{\r\n" + 
					"			padding: 10px;\r\n" + 
					"		}\r\n" + 
					"	</style>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<div>\r\n" + 
					"		<h2>Bước 1: Nhập các thông tin cần thiết</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/themcv1.jpg")+"\">\r\n" + 
					"		<h2>Bước 2: Nhấn nút thêm</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/themcv2.jpg")+"\">\r\n" + 
					"		<h2>Bước 3: Chọn yes để xác nhận lại</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/themcv3.jpg")+"\">\r\n" + 
					"	</div>\r\n" + 
					"</body>\r\n" + 
					"</html>");

		}else if(sa.equals("Cập nhật thông tin công việc")) {
			edit.setText("<html>\r\n" + 
					"<head>\r\n" + 
					"	<title></title>\r\n" + 
					"	<style type=\"text/css\">\r\n" + 
					"		div{\r\n" + 
					"			margin-left: 20px;\r\n" + 
					"		}\r\n" + 
					"		h2{\r\n" + 
					"			padding: 10px;\r\n" + 
					"		}\r\n" + 
					"	</style>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<div>\r\n" + 
					"		<h2>Bước 1: Chọn công việc cần sửa</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/capnhatcv1.jpg")+"\">\r\n" + 
					"		<h2>Bước 2: nhập thông tin mới</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/capnhatcv2.jpg")+"\">\r\n" + 
					"		<h2>Bước 3: Nhấn nút cập nhật</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/capnhatcv3.jpg")+"\">\r\n" + 
					"		<h2>Bước 4: Chọn yes để xác nhận lại</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/capnhatcv4.jpg")+"\">\r\n" + 
					"	</div>\r\n" + 
					"</body>\r\n" + 
					"</html>");
		}else if(sa.equals("Cập nhật trạng thái công việc")) {
			edit.setText("<html>\r\n" + 
					"<head>\r\n" + 
					"	<title></title>\r\n" + 
					"	<style type=\"text/css\">\r\n" + 
					"		div{\r\n" + 
					"			margin-left: 20px;\r\n" + 
					"		}\r\n" + 
					"		h2{\r\n" + 
					"			padding: 10px;\r\n" + 
					"		}\r\n" + 
					"	</style>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<div>\r\n" + 
					"		<h2>Bước 1: Nháy đúp vào công trình cần sửa</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/capnhatttcv1.jpg")+"\">\r\n" + 
					"		<h2>Bước 2: Chọn vào công việc cần sữa</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/capnhatttcv2.jpg")+"\">\r\n" + 
					"		<h2>Bước 3: Chọn trạng thái công việc</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/capnhatttcv3.jpg")+"\">\r\n" + 
					"		<h2>Bước 4: Nhấn nút cập nhật</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/capnhatttcv4.jpg")+"\">\r\n" + 
					"		<h2>Bước 5: Chọn yes để xác nhận lại</h2>\r\n" + 
					"		<img src=\""+TroGiup.class.getResource("/images/capnhatttcv5.jpg")+"\">\r\n" + 
					"	</div>\r\n" + 
					"</body>\r\n" + 
					"</html>");
		}
	}
}
