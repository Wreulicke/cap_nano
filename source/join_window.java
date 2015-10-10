/* �ꗗ�E�B���h�E(���ۃN���X) */

/* getIndex()�ɂ����āA�f�t�H���g�̔ԍ��t���͎��̒ʂ�B
 * 00 01 02 03 04 05
 * 06 07 08 09 10 11
 * 12 13 14 15 16 17
 * 18 19 20 21 22 23
 * 24 25 26 27 28 29
 * 30 31 32 33 34 35
 */

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

abstract class join_window extends JFrame implements MouseListener, KeyListener, MouseMotionListener{
	/* �����o�ϐ� */
	// �萔
	static final float frame_width = 4.0f;
	static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS");
	// �ϐ�
	boolean press_flg = false;
	int window_x, window_y, blocks_size, press_position, enter_position, type_ = -1;
	BufferedImage show_image, blank_image;
	public join_panel panel;
	ArrayList<BufferedImage> ss_buffer;
	ArrayList<Boolean> ss_buffer_flg;
	/* �R���X�g���N�^ */
	join_window(){
		// �E�B���h�E�E�I�u�W�F�N�g�̐ݒ�
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setAlwaysOnTop(true);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		window_x = get_sx_(get_blocks_x());
		window_y = get_sy_(get_blocks_y());
		redraw();
		setTransferHandler(new DropFileHandler());
		// �o�b�t�@�̐ݒ�
		blocks_size = get_blocks_x() * get_blocks_y();
		ss_buffer = new ArrayList<BufferedImage>();
		ss_buffer_flg = new ArrayList<Boolean>();
		blank_image = new BufferedImage(capture.flash_x, capture.flash_y, BufferedImage.TYPE_INT_BGR);
		Graphics graphics = blank_image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, capture.flash_x, capture.flash_y);
		for(int i = 0; i < blocks_size; i++){
			ss_buffer.add(clone(blank_image));
			ss_buffer_flg.add(false);
		}
		show_image = new BufferedImage(window_x, window_y, BufferedImage.TYPE_INT_BGR);
		graphics = show_image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, window_x, window_y);
	}
	/* �A�N�Z�b�T */
	abstract int get_position_x();
	abstract int get_position_y();
	abstract int get_block_size_x();
	abstract int get_block_size_y();
	abstract int get_blocks_x();
	abstract int get_blocks_y();
	abstract void set_dir(int dir);
	abstract void set_type(int type);
	abstract int get_block_size_x_();
	abstract int get_block_size_y_();
	abstract int get_sx(int x);
	abstract int get_sy(int y);
	abstract int get_sx_(int x);
	abstract int get_sy_(int y);
	abstract String getWindowTitle();
	abstract int getIndex(int i);
	/* �}�E�X�C�x���g */
	public void mouseClicked(MouseEvent event){
		// �_�u���N���b�N�����ۂ́A���̏ꏊ�̋L�^�摜����������
		if (event.getClickCount() < 2) return;
		int delete_position = getPosition(event);
		if(ss_buffer_flg.get(delete_position) == false) return;
		main_window.putLog("�y�摜�폜�z");
			// show_image(��ʕ\��)��ł̍폜
			int x = delete_position % get_blocks_x(), y = delete_position / get_blocks_x();
			Graphics graphics = show_image.getGraphics();
			graphics.setColor(Color.white);
			graphics.fillRect(get_sx_(x), get_sy_(y), get_block_size_x_(), get_block_size_y_());
			// �o�b�t�@�̍폜
			ss_buffer.set(delete_position, clone(blank_image));
			// �t���O�̍폜
			ss_buffer_flg.set(delete_position, false);
		main_window.putLog("�ǉ��ʒu�F(" + x+  "," + y + ")");
		panel.repaint();
	}
	public void mousePressed(MouseEvent event){
		if(!press_flg){
			// �}�E�X���������ۂ́A�������ʒu���L�����Ă���
			press_position = getPosition(event);
			press_flg = true;
			panel.repaint();
		}else{
			// �}�E�X�Ńh���b�O���Ă���Ԃ́A�Ώۂ̈ʒu���L�����ď�������
			enter_position = getPosition(event);
			panel.repaint();
		}
	}
	public void mouseReleased(MouseEvent event){
		// �}�E�X�𗣂����ۂ́A���̈ʒu�̃}�X�Ƃ̌������s��
		if(press_flg == false) return;
		int release_position = getPosition(event);
		if(press_position != release_position){
			main_window.putLog("�y�摜�����z");
				// show_image(��ʕ\��)��ł̌���
				int x1 =   press_position % get_blocks_x(), y1 =   press_position / get_blocks_x();
				int x2 = release_position % get_blocks_x(), y2 = release_position / get_blocks_x();
				BufferedImage temp_1 = clone(show_image.getSubimage(get_sx_(x1), get_sy_(y1), get_block_size_x_(), get_block_size_y_()));
				BufferedImage temp_2 = clone(show_image.getSubimage(get_sx_(x2), get_sy_(y2), get_block_size_x_(), get_block_size_y_()));
				Graphics graphics = show_image.getGraphics();
				graphics.drawImage(temp_1, get_sx_(x2), get_sy_(y2), this);
				graphics.drawImage(temp_2, get_sx_(x1), get_sy_(y1), this);
				// �o�b�t�@�̌���
				BufferedImage buffer2 = clone(ss_buffer.get(press_position));
				ss_buffer.set(press_position, clone(ss_buffer.get(release_position)));
				ss_buffer.set(release_position, buffer2);
				// �t���O�̌���
				boolean flg = ss_buffer_flg.get(press_position);
				ss_buffer_flg.set(press_position, ss_buffer_flg.get(release_position));
				ss_buffer_flg.set(release_position, flg);
			main_window.putLog("(" + x2 + "," + y2 + ")��(" + x1 + "," + y1 + ")");
		}
		press_flg = false;
		panel.repaint();
	}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	public void mouseMoved(MouseEvent event){}
	public void mouseDragged(MouseEvent event){
		enter_position = getPosition(event);
		panel.repaint();
	}
	/* �L�[�C�x���g */
	public void keyPressed(KeyEvent event){
		// Alt+Z�ɔ�������
		int keycode = event.getKeyCode();
		if (keycode != KeyEvent.VK_Z) return;
		int modifer = event.getModifiersEx();
		if((modifer & InputEvent.ALT_DOWN_MASK) != 0){
			addImage(capture.getImage());
		}
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	/* �\�����X�V���� */
	void redraw(){
		// �E�B���h�E�ɂ�����ݒ�
		setTitle(getWindowTitle());
		getContentPane().setPreferredSize(new Dimension(window_x, window_y));
		// �I�u�W�F�N�g�ɂ�����ݒ�
		panel = new join_panel();
		getContentPane().add(panel, BorderLayout.CENTER);
		pack();
		panel.repaint();
	}
	/* �R���{�{�b�N�X�̏�Ԃ���A�E�B���h�E�̕\����ύX���� */
	public void changeMode(int dir, int type){
		set_dir(dir);
		set_type(type);
		if(type_ != type){
			window_x = get_sx_(get_blocks_x());
			window_y = get_sy_(get_blocks_y());
			show_image = new BufferedImage(window_x, window_y, BufferedImage.TYPE_INT_BGR);
			Graphics graphics = show_image.getGraphics();
			for(int x = 0; x < get_blocks_x(); x++){
				for(int y = 0; y < get_blocks_y(); y++){
					BufferedImage temp = ss_buffer.get(y * get_blocks_x() + x).getSubimage(get_position_x(), get_position_y(), get_block_size_x(), get_block_size_y());
					graphics.drawImage(temp.getScaledInstance(get_block_size_x_(), get_block_size_y_(), Image.SCALE_AREA_AVERAGING), get_sx_(x), get_sy_(y), this);
				}
			}
			type_ = type;
		}
		redraw();
//		System.out.println("" + dir + " " + type + " " + window_x + " " + window_y);
	}
	/* �摜��ǉ����� */
	public void addImage(BufferedImage image){
		if(image == null) return;
		if(checkImage(image) == false) return;
		for(int i = 0; i < blocks_size; i++){
			int p = getIndex(i);
			if(ss_buffer_flg.get(p) == false){
				main_window.putLog("�y�摜�ǉ��z");
					ss_buffer.set(p, image);
					ss_buffer_flg.set(p, true);
					int px = p % get_blocks_x(), py = p / get_blocks_x();
					BufferedImage temp = image.getSubimage(get_position_x(), get_position_y(), get_block_size_x(), get_block_size_y());
					Graphics graphics = show_image.getGraphics();
					graphics.drawImage(temp.getScaledInstance(get_block_size_x_(), get_block_size_y_(), Image.SCALE_AREA_AVERAGING), get_sx_(px), get_sy_(py), this);
					panel.repaint();
				main_window.putLog("�ʒu�F(" + px + "," + py + ")");
				return;
			}
		}
	}
	/* �摜��ۑ����� */
	abstract void addSpecialFrame(BufferedImage image, int px1, int py1, int px2, int py2);
	public void savePicture(){
		// �ő�̈�𔻒f����
		int px1 = get_blocks_x(), py1 = get_blocks_y(), px2 = -1, py2 = -1;
		for(int x = 0; x < get_blocks_x(); x++){
			for(int y = 0; y < get_blocks_y(); y++){
				if(ss_buffer_flg.get(y * get_blocks_x() + x)){
					px1 = Math.min(px1, x);
					py1 = Math.min(py1, y);
					px2 = Math.max(px2, x);
					py2 = Math.max(py2, y);
				}
			}
		}
		if(px2 - px1 < 0) return;
		// �ۑ��p�o�b�t�@�ɉ摜��z�u����
		main_window.putLog("�y�摜�ۑ��z");
		BufferedImage save_buffer = new BufferedImage(get_sx(get_blocks_x()), get_sy(get_blocks_y()), BufferedImage.TYPE_INT_BGR);
		Graphics graphics = save_buffer.getGraphics();
		for(int x = 0; x < get_blocks_x(); x++){
			for(int y = 0; y < get_blocks_y(); y++){
				BufferedImage temp = ss_buffer.get(y * get_blocks_x() + x).getSubimage(get_position_x(), get_position_y(), get_block_size_x(), get_block_size_y());
				graphics.drawImage(temp, get_sx(x), get_sy(y), this);
			}
		}
		// ����Șg����ǉ�����
		if(option_window.checkbox1.isSelected()){
			addSpecialFrame(save_buffer, px1, py1, px2, py2);
		}
		// �摜�̕ۑ�����
		String save_name = sdf.format(Calendar.getInstance().getTime()) + ".png";
		try{
			ImageIO.write(save_buffer.getSubimage(px1 * get_block_size_x(), py1 * get_block_size_y(), (px2 - px1 + 1) * get_block_size_x(), (py2 - py1 + 1) * get_block_size_y()), "png", new File(save_name));
//			ImageIO.write(save_buffer, "png", new File(save_name));
			main_window.putLog(save_name);
		}
		catch(Exception error){
			error.printStackTrace();
		}
	}
	/* �摜���� */
	abstract boolean checkImage(BufferedImage image);
	boolean checkColor(BufferedImage image, int x, int y, int r, int g, int b){
		Color color = new Color(image.getRGB(x, y));
		int diff_r = color.getRed() - r, diff_g = color.getGreen() - g, diff_b = color.getBlue() - b;
		int diff = diff_r * diff_r + diff_g * diff_g + diff_b * diff_b;
		if(diff < 500) return true;
		return false;
	}
	/* ���W�擾 */
	int getPosition(MouseEvent event){
		Point point = event.getPoint();
		int mx = point.x / get_block_size_x_(), my = point.y / get_block_size_y_();
		if(mx < 0) mx = 0;
		if(mx >= get_blocks_x()) mx = get_blocks_x() - 1;
		if(my < 0) my = 0;
		if(my >= get_blocks_y()) my = get_blocks_y() - 1;
		return my * get_blocks_x() + mx;
	}
	/* �摜���f�B�[�v�R�s�[���� */
	BufferedImage clone(BufferedImage image){
		BufferedImage clone = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		Graphics graphics = clone.createGraphics();
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();
		return clone;
	}
	/* �ʃN���X */
	class join_panel extends JPanel{
		@Override
		public void paintComponent(Graphics graphics){
			// �w�i�̊G��`�ʂ���
			graphics.drawImage(show_image, 0, 0, this);
			Graphics2D graphics2d = (Graphics2D)graphics;
			// �}�[�J�[��`�ʂ���
			if(press_flg){
				BasicStroke wideStroke = new BasicStroke(frame_width);
				graphics2d.setStroke(wideStroke);
				int x1 = press_position % get_blocks_x(), y1 = press_position / get_blocks_x();
				int x2 = enter_position % get_blocks_x(), y2 = enter_position / get_blocks_x();
				graphics2d.setPaint(Color.BLUE);
				graphics2d.draw(new Rectangle2D.Double(get_sx_(x1) + frame_width / 2, get_sy_(y1) + frame_width / 2, get_block_size_x_() - frame_width, get_block_size_y_() - frame_width));
				graphics2d.setPaint(Color.RED);
				graphics2d.draw(new Rectangle2D.Double(get_sx_(x2) + frame_width / 2, get_sy_(y2) + frame_width / 2, get_block_size_x_() - frame_width, get_block_size_y_() - frame_width));
				BasicStroke normalStroke = new BasicStroke(1.0f);
				graphics2d.setStroke(normalStroke);
				graphics2d.setPaint(Color.BLACK);
			}
			// �g����`�ʂ���
			for(int x = 1; x <= get_blocks_x() - 1; x++){
				graphics2d.draw(new Line2D.Double(get_sx_(x), 0, get_sx_(x), window_y));
			}
			for(int y = 1; y <= get_blocks_y() - 1; y++){
				graphics2d.draw(new Line2D.Double(0, get_sy_(y), window_x, get_sy_(y)));
			}
		}
	}
	class DropFileHandler extends TransferHandler{
		@Override
		public boolean canImport(TransferSupport support){
			// �h���b�v����Ă��Ȃ��ꍇ�͎󂯎��Ȃ�
			if(!support.isDrop()) return false;
			// �h���b�v���ꂽ���̂��t�@�C���ł͂Ȃ��ꍇ�͎󂯎��Ȃ�
			if(!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) return false;
			return true;
		}
		@Override
		public boolean importData(TransferSupport support){
			// �󂯎���Ă������̂��m�F����
			if(!canImport(support)) return false;
			// �h���b�v����
			Transferable transferable = support.getTransferable();
			try{
				// �t�@�C�����󂯎��
				List<File> files = (List<File>)transferable.getTransferData(DataFlavor.javaFileListFlavor);
				// ���Ԃɓǂݍ���Œǉ�����
				for(File file : files){
					BufferedImage load_image = ImageIO.read(file);
					addImage(load_image);
				}
			}
			catch(Exception error){
				error.printStackTrace();
			}
			return true;
		}
	}
}
