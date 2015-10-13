/* �\�[�g�ꗗ�E�B���h�E */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.Line2D;

public class sort_window extends join_window{
	/* �����o�ϐ� */
	// �萔
	static final int[] position_x = {392, 392, 392};
	static final int[] position_y = {154, 154, 154};
	static final int[] block_size_x = {190, 382, 382};
	static final int[] block_size_y = {279, 279, 315};
	static final int blocks_x = 7;
	static final int blocks_y = 5;
	static final int zooming = 3;
	public static final String[] show_dir_str  = {"�s��D��", "���D��"};
	public static final String[] show_type_str = {"�k���\��", "�ʏ�\��", "�g���\��"};
	static final String title = "�\�[�g�ꗗ";
	static final BasicStroke wideStroke = new BasicStroke(4.0f);
	// �ϐ�
	static int show_dir  = 0;	//�\������(�s�D��E��D��)
	static int show_type = 0;	//�\�����(�R���p�N�g�E�ʏ�E�G�N�X�g��)
	/* �R���X�g���N�^ */
	sort_window(){
		super();
	}
	/* �A�N�Z�b�T */
	int get_position_x(){return position_x[show_type];}
	int get_position_y(){return position_y[show_type];}
	int get_block_size_x(){return block_size_x[show_type];}
	int get_block_size_y(){return block_size_y[show_type];}
	int get_blocks_x(){return blocks_x;}
	int get_blocks_y(){return blocks_y;}
	void set_dir(int dir){show_dir = dir;}
	void set_type(int type){show_type = type;}
	int get_block_size_x_(){return block_size_x[show_type] / zooming;}
	int get_block_size_y_(){return block_size_y[show_type] / zooming;}
	int get_sx(int x){return get_block_size_x() * x;}
	int get_sy(int y){return get_block_size_y() * y;}
	int get_sx_(int x){return get_block_size_x_() * x;}
	int get_sy_(int y){return get_block_size_y_() * y;}
	String getWindowTitle(){return title + " - " + show_dir_str[show_dir] + " - " + show_type_str[show_type];}
	int getIndex(int i){
		switch(show_dir){
			case 0:	//�s��D��
				return i;
			case 1:	//���D��
				int x = i / blocks_y, y = i % blocks_y;
				return y * blocks_x + x;
		}
		return 0;
	}
	/* �摜���� */
	void addSpecialFrame(BufferedImage image, int px1, int py1, int px2, int py2){
		Graphics2D graphics2d = (Graphics2D)image.getGraphics();
		graphics2d.setStroke(wideStroke);
		graphics2d.setPaint(Color.BLUE);
		switch(show_dir){
			case 0:	//�s��D��
				for(int y = 1; y < blocks_y; y++){
					for(int x = 0; x < blocks_x; x++){
						if((y != py1) && (y != py2 + 1)){
							graphics2d.draw(new Line2D.Double(get_sx(x), get_sy(y), get_sx(x + 1), get_sy(y)));
						}
					}
				}
				break;
			case 1:	//���D��
				for(int x = 1; x < blocks_x; x++){
					for(int y = 0; y < blocks_y; y++){
						if((x != px1) && (x != px2 + 1)){
							graphics2d.draw(new Line2D.Double(get_sx(x), get_sy(y), get_sx(x), get_sy(y + 1)));
						}
					}
				}
				break;
		}
		graphics2d.dispose();
	}
	/* �摜���� */
	boolean checkImage(BufferedImage image){
		if(checkColor(image, 420, 118, 66,  60,  59) == false) return false;
		if(checkColor(image, 374,  80, 30, 157, 160) == false) return false;
		return true;
	}
	int checkImageX(BufferedImage image){
		if(checkColor(image, 420, 118, 66,  60,  59) == false) return -1;
		if(checkColor(image, 374,  80, 30, 157, 160) == false) return -1;
		for(int i = 0; i < blocks_size; i++){
			int p = getIndex(i);
			if(ss_buffer_flg.get(p) == false){
				return p;
			}
		}
		return -1;
	}
}
