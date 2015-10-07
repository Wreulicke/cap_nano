/*
javac nano.java
java nano
jar cvfm nano.jar MANIFEST.MF *.class
jar tf nano.jar
*/

/* �Ƃ肠���������[�X����
 * �I�𒆂͑I��g���o���ĕ�����₷������
 * �u�}���g�v�𓱓��B�}���g�̈ʒu�ɒǉ�����d�l�ɂ��邱�ƂŎg���₷������
 * ���������\�[�g��ʂƉ�����ʂ��قڈꏏ�Ȃ̂ŁA���ۃN���X���`���Ă�������p��������
 */

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class nano{
	public static main_window main_frame;
	public static unit_window unit_frame;
	public static sort_window sort_frame;
	public static void main(String args[]){
		// ���C�����
		main_frame = new main_window();
		main_frame.setVisible(true);
		// �����ꗗ�p
		unit_frame = new unit_window();
		// �\�[�g�ꗗ�p
		sort_frame = new sort_window();
	}
}

/* ���C���E�B���h�E�̏��� */
class main_window extends JFrame implements ActionListener{
	/* �����o�ϐ��Q */
	static final Event e_event = new Event();
	static JComboBox combo_box = null;
	public static JTextArea text_area;
	// �I�u�W�F�N�g�ݒ�p
	static final int object_x = 64;
	static final int object_y = 24;
	static final int object_space = 10;
	static final String soft_name = "�L�^�͑�؂Ȃ�";
	static final String[] save_type = {"�ʏ�", "����", "�\�[�g"};
	/* �I�u�W�F�N�g�p */
	// ���C����ʂ̐ݒ�
	main_window(){
		setResizable(false);
		// setPreferredSize�ŃE�B���h�E�����̃T�C�Y�𒲐����Ă���
		setTitle(soft_name);
		getContentPane().setPreferredSize(new Dimension(position_x(4), position_y(4)));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// �p�l���̐ݒ�
		JPanel panel = new JPanel();
		panel.setLayout(null);
		// �I�u�W�F�N�g�̐ݒ�
		JButton button1 = new JButton("���W�擾");
			button1.setBounds(position_x(0), position_y(0), size_x(1), size_y(1));
			button1.setMargin(new Insets(0, 0, 0, 0));
			button1.addActionListener(this);
			button1.setActionCommand("���W�擾");
		combo_box = new JComboBox(save_type);
			combo_box.setBounds(position_x(1), position_y(0), size_x(1), size_y(1));
			combo_box.addActionListener(this);
			combo_box.setActionCommand("�I��ύX");
		JButton button2 = new JButton("�摜�ǉ�");
			button2.setBounds(position_x(2), position_y(0), size_x(1), size_y(1));
			button2.setMargin(new Insets(0, 0, 0, 0));
			button2.addActionListener(this);
			button2.setActionCommand("�摜�ǉ�");
		JButton button3 = new JButton("�摜�ۑ�");
			button3.setBounds(position_x(3), position_y(0), size_x(1), size_y(1));
			button3.setMargin(new Insets(0, 0, 0, 0));
			button3.addActionListener(this);
			button3.setActionCommand("�摜�ۑ�");
		text_area = new JTextArea();
			text_area.setEditable(false);
		JScrollPane scrollpane = new JScrollPane(text_area);
			scrollpane.setBounds(position_x(0), position_y(1), size_x(4), size_y(3));
		// �p�l���ɃI�u�W�F�N�g��ǉ�
		panel.add(button1);
		panel.add(combo_box);
		panel.add(button2);
		panel.add(button3);
		panel.add(scrollpane);
		// �R���e�i�Ƀp�l����ǉ�
		Container content_pane = getContentPane();
		content_pane.add(panel, BorderLayout.CENTER);
		pack();
	}
	// �I�u�W�F�N�g�p�萔���v�Z����
	private static int position_x(int x){
		return object_space * (x + 1) + object_x * x;
	}
	private static int position_y(int y){
		return object_space * (y + 1) + object_y * y;
	}
	private static int size_x(int x){
		return object_space * (x - 1) + object_x * x;
	}
	private static int size_y(int y){
		return object_space * (y - 1) + object_y * y;
	}
	// �e�L�X�g�G���A�Ƀe�L�X�g��ǉ�����
	public static void putLog(String message){
		text_area.append(message + "\n");
	}
	/* �C�x���g�����p */
	public void actionPerformed(ActionEvent event){
		String command_str = event.getActionCommand();
		if(command_str.equals("���W�擾")){
			e_event.getKancollePosition();
		}
		if(command_str.equals("�I��ύX")){
			switch((String)combo_box.getSelectedItem()){
				case "�ʏ�":
					nano.unit_frame.setVisible(false);
					nano.sort_frame.setVisible(false);
					break;
				case "����":
					nano.unit_frame.setVisible(true);
					nano.sort_frame.setVisible(false);
					break;
				case "�\�[�g":
					nano.unit_frame.setVisible(false);
					nano.sort_frame.setVisible(true);
					break;
			}
		}
		if(command_str.equals("�摜�ǉ�")){
			switch((String)combo_box.getSelectedItem()){
				case "����":
					unit_window.addImage(e_event.getImage());
					break;
				case "�\�[�g":
					sort_window.addImage(e_event.getImage());
					break;
			}
		}
		if(command_str.equals("�摜�ۑ�")){
			switch((String)combo_box.getSelectedItem()){
				case "�ʏ�":
					e_event.savePicture();
					break;
				case "����":
					unit_window.savePicture();
					break;
				case "�\�[�g":
					sort_window.savePicture();
					break;
			}
		}
	}
}

/* �����ꗗ�̏��� */
class unit_window extends JFrame implements MouseListener{
	/* �����o�ϐ��Q */
	static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS");
	static final int unit_pos_x  = 330;
	static final int unit_pos_y  = 100;
	static final int unit_width  = 455;
	static final int unit_height = 365;
	static final int unit_zooming = 4;
	static final int unit_panels_x = 6;
	static final int unit_panels_y = 6;
	static final int unit_window_width  = unit_width  * unit_panels_x;
	static final int unit_window_height = unit_height * unit_panels_y;
	//
	public static BufferedImage unit_list_image = new BufferedImage(unit_window_width, unit_window_height, BufferedImage.TYPE_INT_BGR);
	static ArrayList<Boolean> unit_list_flg = new ArrayList<Boolean>();
	static UnitWindow panel = null;
	static int press_position;
	boolean press_flg = false;
	// �I�u�W�F�N�g�ݒ�p
	unit_window(){
		setResizable(false);
		setTitle("�����ꗗ");
		getContentPane().setPreferredSize(new Dimension(position_x(unit_panels_x), position_y(unit_panels_y)));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new UnitWindow();
		getContentPane().add(panel, BorderLayout.CENTER);
		pack();
		for(int i = 0; i < unit_panels_x * unit_panels_y; i++){
			unit_list_flg.add(false);
		}
		addMouseListener(this);
		Graphics graphics = unit_list_image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, unit_window_width, unit_window_height);
	}
	private static int position_x(int x){
		return unit_width * x / unit_zooming;
	}
	private static int position_y(int y){
		return unit_height * y / unit_zooming;
	}
	private static int calcPosition(int x, int y){
		int position = x * unit_panels_y + y;
		if(position < 0) position = 0;
		if(position >= unit_panels_x * unit_panels_y) position = unit_panels_x * unit_panels_y - 1;
		return position;
	}
	/* �C�x���g���p */
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	// �}�E�X���������ۂ̏���
	public void mousePressed(MouseEvent event){
		if(press_flg) return;
		Point point = event.getPoint();
		press_position = calcPosition(point.x * unit_zooming / unit_width, point.y * unit_zooming / unit_height);
		press_flg = true;
	}
	// �}�E�X�𗣂����ۂ̏���
	public void mouseReleased(MouseEvent event){
		if(press_flg == false) return;
		Point point = event.getPoint();
		int release_position = calcPosition(point.x * unit_zooming / unit_width, point.y * unit_zooming / unit_height);
		if(press_position != release_position){
			main_window.putLog("�y�摜�����z");
			main_window.putLog("��ʁF�������");
			main_window.putLog("(" + (press_position / unit_panels_y) + "," + (press_position % unit_panels_y) + ")��(" + (release_position / unit_panels_y) + "," + (release_position % unit_panels_y) + ")");
			int x1 =   press_position / unit_panels_y, y1 =   press_position % unit_panels_y;
			int x2 = release_position / unit_panels_y, y2 = release_position % unit_panels_y;
			BufferedImage temp_image1 = unit_list_image.getSubimage(x1 * unit_width, y1 * unit_height, unit_width, unit_height);
			BufferedImage temp_image2 = unit_list_image.getSubimage(x2 * unit_width, y2 * unit_height, unit_width, unit_height);
			BufferedImage unit_list_image_ = new BufferedImage(unit_window_width, unit_window_height, BufferedImage.TYPE_INT_BGR);
			Graphics graphics = unit_list_image_.getGraphics();
			graphics.drawImage(unit_list_image, 0, 0, this);
			graphics.drawImage(temp_image1, x2 * unit_width, y2 * unit_height, this);
			graphics.drawImage(temp_image2, x1 * unit_width, y1 * unit_height, this);
			unit_list_image = unit_list_image_;
			boolean temp = unit_list_flg.get(press_position);
			unit_list_flg.set(press_position, unit_list_flg.get(release_position));
			unit_list_flg.set(release_position, temp);
		}
		press_flg = false;
		panel.repaint();
	}
	public void mouseClicked(MouseEvent event){
		if (event.getClickCount() < 2) return;
		// �_�u���N���b�N�����ۂ́A���̏ꏊ�̋L�^�摜����������
		Point point = event.getPoint();
		int delete_position = calcPosition(point.x * unit_zooming / unit_width, point.y * unit_zooming / unit_height);
//		if(unit_list_flg.get(delete_position) == false) return;
		unit_list_flg.set(delete_position, false);
		int x = delete_position / unit_panels_y, y = delete_position % unit_panels_y;
		Graphics graphics = unit_list_image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(x * unit_width, y * unit_height, unit_width, unit_height);
		main_window.putLog("�y�摜�폜�z");
		main_window.putLog("��ʁF�������");
		main_window.putLog("�ǉ��ʒu�F(" + x+  "," + y + ")");
		panel.repaint();
	}
	static private boolean checkColor(BufferedImage image, int x, int y, int r, int g, int b){
		Color color = new Color(image.getRGB(x, y));
		int diff_r = color.getRed() - r, diff_g = color.getGreen() - g, diff_b = color.getBlue() - b;
		int diff = diff_r * diff_r + diff_g * diff_g + diff_b * diff_b;
		if(diff < 500) return true;
		return false;
	}
	static public void addImage(BufferedImage image){
		// ���O�ɁA������ʂȂ̂�����������
		if(checkColor(image, 300, 172, 241, 191, 119) == false) return;
		// �ǉ�����
		for(int j = 0; j < 2; j++){
			for(int i = 0; i < 3; i++){
				for(int n = 0; n < 3; n++){
					for(int m = 0; m < 2; m++){
						int x = i * 2 + m, y = j * 3 + n;
						//System.out.println(unit_list_flg.get(x * unit_panels_y + y));
						if(unit_list_flg.get(x * unit_panels_y + y)) continue;
						// �󂫏ꏊ��T������ŁA���̈ʒu�ɉ摜��ǉ�����
						main_window.putLog("�y�摜�ǉ��z");
						main_window.putLog("��ʁF�������");
						main_window.putLog("�ǉ��ʒu�F(" + x+  "," + y + ")");
						Graphics graphics = unit_list_image.getGraphics();
						graphics.drawImage(image.getSubimage(unit_pos_x, unit_pos_y, unit_width, unit_height), x * unit_width, y * unit_height, null);
						unit_list_flg.set(x * unit_panels_y + y, true);
						panel.repaint();
						return;
					}
				}
			}
		}
	}
	static public void savePicture(){
		// �ۑ��摜�̗̈�����肷��
		int px1 = 0, py1 = 0, px2 = 0, py2 = 0;
		boolean flg = false;
		for(int x = 0; x < unit_panels_x; x++){
			for(int y = 0; y < unit_panels_y; y++){
				if(unit_list_flg.get(x * unit_panels_y + y)){
					if(flg == false){
						px1 = px2 = x;
						py1 = py2 = y;
						flg = true;
					}else{
						px1 = Math.min(px1, x);
						py1 = Math.min(py1, y);
						px2 = Math.max(px2, x);
						py2 = Math.max(py2, y);
					}
				}
			}
		}
		if(flg == false) return;
		main_window.putLog("�y�摜�ۑ�(����)�z");
		String save_name = sdf.format(Calendar.getInstance().getTime()) + ".png";
		main_window.putLog(save_name);
		try{
			ImageIO.write(unit_list_image.getSubimage(px1 * unit_width, py1 * unit_height, (px2 - px1 + 1) * unit_width, (py2 - py1 + 1) * unit_height), "png", new File(save_name));
		}
		catch(Exception error){
			error.printStackTrace();
		}
	}
	class UnitWindow extends JPanel{
		public void paintComponent(Graphics graphics){
			graphics.drawImage(unit_list_image.getScaledInstance(position_x(unit_panels_x), position_y(unit_panels_y), Image.SCALE_AREA_AVERAGING), 0, 0, this);
			Graphics2D graphics2d = (Graphics2D)graphics;
			for(int x = 1; x <= unit_panels_x - 1; x++){
				graphics2d.draw(new Line2D.Double(position_x(x), position_y(0), position_x(x), position_y(unit_panels_y)));
			}
			for(int y = 1; y <= unit_panels_y - 1; y++){
				graphics2d.draw(new Line2D.Double(position_x(0), position_y(y), position_x(unit_panels_x), position_y(y)));
			}
		}
	}
}

/* �\�[�g�ꗗ�̏��� */
class sort_window extends JFrame implements MouseListener{
	/* �����o�ϐ��Q */
	static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS");
	static final int sort_pos_x  = 398;
	static final int sort_pos_y  = 154;
	static final int sort_width  = 194;
	static final int sort_height = 279;
	static final int sort_zooming =  3;
	static final int sort_panels_x = 7;
	static final int sort_panels_y = 5;
	static final int sort_window_width  = sort_width  * sort_panels_x;
	static final int sort_window_height = sort_height * sort_panels_y;
	//
	public static BufferedImage sort_list_image = new BufferedImage(sort_window_width, sort_window_height, BufferedImage.TYPE_INT_BGR);
	static ArrayList<Boolean> sort_list_flg = new ArrayList<Boolean>();
	static sortWindow panel = null;
	static int press_position;
	boolean press_flg = false;
	// �I�u�W�F�N�g�ݒ�p
	sort_window(){
		setResizable(false);
		setTitle("�\�[�g�ꗗ");
		getContentPane().setPreferredSize(new Dimension(position_x(sort_panels_x), position_y(sort_panels_y)));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new sortWindow();
		getContentPane().add(panel, BorderLayout.CENTER);
		pack();
		for(int i = 0; i < sort_panels_x * sort_panels_y; i++){
			sort_list_flg.add(false);
		}
		addMouseListener(this);
		Graphics graphics = sort_list_image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, sort_window_width, sort_window_height);
	}
	private static int position_x(int x){
		return sort_width * x / sort_zooming;
	}
	private static int position_y(int y){
		return sort_height * y / sort_zooming;
	}
	private static int calcPosition(int x, int y){
		int position = x * sort_panels_y + y;
		if(position < 0) position = 0;
		if(position >= sort_panels_x * sort_panels_y) position = sort_panels_x * sort_panels_y - 1;
		return position;
	}
	/* �C�x���g���p */
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	// �}�E�X���������ۂ̏���
	public void mousePressed(MouseEvent event){
		if(press_flg) return;
		Point point = event.getPoint();
		press_position = calcPosition(point.x * sort_zooming / sort_width, point.y * sort_zooming / sort_height);
		press_flg = true;
	}
	// �}�E�X�𗣂����ۂ̏���
	public void mouseReleased(MouseEvent event){
		if(press_flg == false) return;
		Point point = event.getPoint();
		int release_position = calcPosition(point.x * sort_zooming / sort_width, point.y * sort_zooming / sort_height);
		if(press_position != release_position){
			main_window.putLog("�y�摜�����z");
			main_window.putLog("��ʁF�\�[�g���");
			main_window.putLog("(" + (press_position / sort_panels_y) + "," + (press_position % sort_panels_y) + ")��(" + (release_position / sort_panels_y) + "," + (release_position % sort_panels_y) + ")");
			int x1 =   press_position / sort_panels_y, y1 =   press_position % sort_panels_y;
			int x2 = release_position / sort_panels_y, y2 = release_position % sort_panels_y;
			BufferedImage temp_image1 = sort_list_image.getSubimage(x1 * sort_width, y1 * sort_height, sort_width, sort_height);
			BufferedImage temp_image2 = sort_list_image.getSubimage(x2 * sort_width, y2 * sort_height, sort_width, sort_height);
			BufferedImage sort_list_image_ = new BufferedImage(sort_window_width, sort_window_height, BufferedImage.TYPE_INT_BGR);
			Graphics graphics = sort_list_image_.getGraphics();
			graphics.drawImage(sort_list_image, 0, 0, this);
			graphics.drawImage(temp_image1, x2 * sort_width, y2 * sort_height, this);
			graphics.drawImage(temp_image2, x1 * sort_width, y1 * sort_height, this);
			sort_list_image = sort_list_image_;
			boolean temp = sort_list_flg.get(press_position);
			sort_list_flg.set(press_position, sort_list_flg.get(release_position));
			sort_list_flg.set(release_position, temp);
		}
		press_flg = false;
		panel.repaint();
	}
	public void mouseClicked(MouseEvent event){
		if (event.getClickCount() < 2) return;
		// �_�u���N���b�N�����ۂ́A���̏ꏊ�̋L�^�摜����������
		Point point = event.getPoint();
		int delete_position = calcPosition(point.x * sort_zooming / sort_width, point.y * sort_zooming / sort_height);
		if(sort_list_flg.get(delete_position) == false) return;
		sort_list_flg.set(delete_position, false);
		int x = delete_position / sort_panels_y, y = delete_position % sort_panels_y;
		Graphics graphics = sort_list_image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(x * sort_width, y * sort_height, sort_width, sort_height);
		main_window.putLog("�y�摜�폜�z");
		main_window.putLog("��ʁF�\�[�g���");
		main_window.putLog("�ǉ��ʒu�F(" + x+  "," + y + ")");
		panel.repaint();
	}
	static private boolean checkColor(BufferedImage image, int x, int y, int r, int g, int b){
		Color color = new Color(image.getRGB(x, y));
		int diff_r = color.getRed() - r, diff_g = color.getGreen() - g, diff_b = color.getBlue() - b;
		int diff = diff_r * diff_r + diff_g * diff_g + diff_b * diff_b;
		if(diff < 500) return true;
		return false;
	}
	static public void addImage(BufferedImage image){
		// ���O�ɁA�\�[�g��ʂȂ̂�����������
		if(checkColor(image, 420, 118, 66,  60,  59) == false) return;
		if(checkColor(image, 374,  80, 30, 157, 160) == false) return;
		// �ǉ�����
		for(int x = 0; x < sort_panels_x; x++){
			for(int y = 0; y < sort_panels_y; y++){
				if(sort_list_flg.get(x * sort_panels_y + y)) continue;
				// �󂫏ꏊ��T������ŁA���̈ʒu�ɉ摜��ǉ�����
				main_window.putLog("�y�摜�ǉ��z");
				main_window.putLog("��ʁF�\�[�g���");
				main_window.putLog("�ǉ��ʒu�F(" + x+  "," + y + ")");
				Graphics graphics = sort_list_image.getGraphics();
				graphics.drawImage(image.getSubimage(sort_pos_x, sort_pos_y, sort_width, sort_height), x * sort_width, y * sort_height, null);
				sort_list_flg.set(x * sort_panels_y + y, true);
				panel.repaint();
				return;
			}
		}
	}
	static public void savePicture(){
		// �ۑ��摜�̗̈�����肷��
		int px1 = 0, py1 = 0, px2 = 0, py2 = 0;
		boolean flg = false;
		for(int x = 0; x < sort_panels_x; x++){
			for(int y = 0; y < sort_panels_y; y++){
				if(sort_list_flg.get(x * sort_panels_y + y)){
					if(flg == false){
						px1 = px2 = x;
						py1 = py2 = y;
						flg = true;
					}else{
						px1 = Math.min(px1, x);
						py1 = Math.min(py1, y);
						px2 = Math.max(px2, x);
						py2 = Math.max(py2, y);
					}
				}
			}
		}
		if(flg == false) return;
		main_window.putLog("�y�摜�ۑ�(�\�[�g)�z");
		String save_name = sdf.format(Calendar.getInstance().getTime()) + ".png";
		main_window.putLog(save_name);
		try{
			ImageIO.write(sort_list_image.getSubimage(px1 * sort_width, py1 * sort_height, (px2 - px1 + 1) * sort_width, (py2 - py1 + 1) * sort_height), "png", new File(save_name));
		}
		catch(Exception error){
			error.printStackTrace();
		}
	}
	class sortWindow extends JPanel{
		public void paintComponent(Graphics graphics){
			graphics.drawImage(sort_list_image.getScaledInstance(position_x(sort_panels_x), position_y(sort_panels_y), Image.SCALE_AREA_AVERAGING), 0, 0, this);
			Graphics2D graphics2d = (Graphics2D)graphics;
			for(int x = 1; x <= sort_panels_x - 1; x++){
				graphics2d.draw(new Line2D.Double(position_x(x), position_y(0), position_x(x), position_y(sort_panels_y)));
			}
			for(int y = 1; y <= sort_panels_y - 1; y++){
				graphics2d.draw(new Line2D.Double(position_x(0), position_y(y), position_x(sort_panels_x), position_y(y)));
			}
		}
	}
}

/* �C�x���g���̏��� */
class Event{
	/* �����o�ϐ��Q */
	static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS");
	// �摜�F���p
	static final int flash_width = 800;
	static final int flash_height = 480;
	static final int judge_x = flash_width + 2;
	static final int judge_y = flash_height + 2;
	static ArrayList<Integer> gd_index;
	static ArrayList<Integer> gc_index;
	static int display_index = -1, flash_pos_x, flash_pos_y;
	// �͂���̍��W���擾����
	static public void getKancollePosition(){
		display_index = -1;
		main_window.putLog("�y���W�擾�z");
		// ���W���擾���鏈��
		// �܂��A�S�Ẵf�B�X�v���C�ɂ�����X�N�V�����擾����
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		gd_index = new ArrayList<Integer>();
		gc_index = new ArrayList<Integer>();
		try{
			// �S�ẴO���t�B�b�N�X�f�o�C�X�Ɋւ�������擾����
			GraphicsDevice[] all_gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
			int i = 0, j;
			for(GraphicsDevice gd : all_gd){
				// �e�O���t�B�b�N�f�o�C�X�ɂ�����O���t�B�b�N�X�������擾����
				GraphicsConfiguration[] all_gc = gd.getConfigurations();
				// �e�O���t�B�b�N�X�����ɏ]���A���̍��W���擾���ăX�N�V�����B��
				Robot robot = new Robot(gd);
				j = 0;
				for(GraphicsConfiguration gc : all_gc){
					images.add(robot.createScreenCapture(gc.getBounds()));
					gd_index.add(i);
					gc_index.add(j);
					j++;
				}
				i++;
			}
			// �e�X�N�V���ɂ����āA�͂���̉�ʂƂȂ肤��800x480�̉摜��T������
			for(int n = images.size() - 1; n >= 0; n--){
				int width  = images.get(n).getWidth();
				int height = images.get(n).getHeight();
				// �͂���̉�ʂ����݂����Ȃ��قǂ̃X�N�V���T�C�Y�Ȃ�T�����Ȃ�
				if((width < judge_x) || (height < judge_y)) continue;
				// �͂���̉�ʂ���������
				int search_x = width  - judge_x;
				int search_y = height - judge_y;
				for(int x = 0; x <= search_x; x++){
					for(int y = 0; y <= search_y; y++){
						// �u����v
						//[0xFFFFFF]0xFFFFFF
						// 0xFFFFFF 0x??????
						// ���܂��Q�Ƃ���̂�[]�̈ʒu�B��L�p�^�[���������Ă��瑼��3�p��������
						if((images.get(n).getRGB(x    , y    ) & 0xffffff) != 0xffffff) continue;
						if((images.get(n).getRGB(x + 1, y    ) & 0xffffff) != 0xffffff) continue;
						if((images.get(n).getRGB(x    , y + 1) & 0xffffff) != 0xffffff) continue;
						if((images.get(n).getRGB(x + 1, y + 1) & 0xffffff) == 0xffffff) continue;
						// �u�E��v
						if((images.get(n).getRGB(x + flash_width    , y    ) & 0xffffff) != 0xffffff) continue;
						if((images.get(n).getRGB(x + flash_width + 1, y    ) & 0xffffff) != 0xffffff) continue;
						if((images.get(n).getRGB(x + flash_width    , y + 1) & 0xffffff) == 0xffffff) continue;
						if((images.get(n).getRGB(x + flash_width + 1, y + 1) & 0xffffff) != 0xffffff) continue;
						// �u�����v
						if((images.get(n).getRGB(x    , y + flash_height    ) & 0xffffff) != 0xffffff) continue;
						if((images.get(n).getRGB(x + 1, y + flash_height    ) & 0xffffff) == 0xffffff) continue;
						if((images.get(n).getRGB(x    , y + flash_height + 1) & 0xffffff) != 0xffffff) continue;
						if((images.get(n).getRGB(x + 1, y + flash_height + 1) & 0xffffff) != 0xffffff) continue;
						// �u�E���v
						if((images.get(n).getRGB(x + flash_width    , y + flash_height    ) & 0xffffff) == 0xffffff) continue;
						if((images.get(n).getRGB(x + flash_width + 1, y + flash_height    ) & 0xffffff) != 0xffffff) continue;
						if((images.get(n).getRGB(x + flash_width    , y + flash_height + 1) & 0xffffff) != 0xffffff) continue;
						if((images.get(n).getRGB(x + flash_width + 1, y + flash_height + 1) & 0xffffff) != 0xffffff) continue;
						// ���o�ł����̂ŁA���̃f�B�X�v���C�̔ԍ�����э��W���擾����
						display_index = n;
						flash_pos_x = x + 1;
						flash_pos_y = y + 1;
						break;
					}
					if(display_index >= 0) break;
				}
			}
			if(display_index >= 0){
				main_window.putLog("�f�B�X�v���C�ԍ�-������W�F" + display_index + "-" + flash_pos_x + "," + flash_pos_y);
			}else{
				main_window.putLog("�͂���̉�ʂ��擾�ł��܂���ł����B");
			}
		}
		catch(Exception error){
			error.printStackTrace();
		}
	}
	// �͂���̉摜��ۑ�����
	static public void savePicture(){
		try{
			if(display_index < 0) return;
			// �摜��ۑ����鏈��
			main_window.putLog("�y�摜�ۑ��z");
			BufferedImage latest_flash_image = getImage();
			Date latest_flash_time = getDate();
			String save_name = sdf.format(latest_flash_time) + ".png";
			main_window.putLog(save_name);
			ImageIO.write(latest_flash_image, "png", new File(save_name));
		}
		catch(Exception error){
			error.printStackTrace();
		}
	}
	// �͂���̉�ʂ��擾����
	static public BufferedImage getImage(){
		try{
			if(display_index < 0) return null;
			GraphicsDevice[] all_gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
			GraphicsConfiguration[] all_gc = all_gd[gd_index.get(display_index)].getConfigurations();
			Robot robot = new Robot(all_gd[gd_index.get(display_index)]);
			return robot.createScreenCapture(all_gc[gc_index.get(display_index)].getBounds()).getSubimage(flash_pos_x, flash_pos_y, flash_width, flash_height);
		}
		catch(Exception error){
			error.printStackTrace();
			return null;
		}
	}
	// ���ݎ������擾����
	static public Date getDate(){
		return Calendar.getInstance().getTime();
	}
}
