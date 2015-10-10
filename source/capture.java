/* �摜�擾�p */

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class capture{
	/* �����o�ϐ� */
	// �萔
	public static final int flash_x = 800;
	public static final int flash_y = 480;
	static final int judge_x = flash_x + 2;
	static final int judge_y = flash_y + 2;
	static final int white = 0xffffff;
	// �ϐ�
	static ArrayList<Integer> gd_index, gc_index;
	static int display_index = -1, flash_px, flash_py;
	/* �͂���̍��W���擾���� */
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
			int n = 0;
			for(BufferedImage image : images){
				int width  = image.getWidth();
				int height = image.getHeight();
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
						if((image.getRGB(x    , y    ) & white) != white) continue;
						if((image.getRGB(x + 1, y    ) & white) != white) continue;
						if((image.getRGB(x    , y + 1) & white) != white) continue;
						if((image.getRGB(x + 1, y + 1) & white) == white) continue;
						// �u�E��v
						if((image.getRGB(x + flash_x    , y    ) & white) != white) continue;
						if((image.getRGB(x + flash_x + 1, y    ) & white) != white) continue;
						if((image.getRGB(x + flash_x    , y + 1) & white) == white) continue;
						if((image.getRGB(x + flash_x + 1, y + 1) & white) != white) continue;
						// �u�����v
						if((image.getRGB(x    , y + flash_y    ) & white) != white) continue;
						if((image.getRGB(x + 1, y + flash_y    ) & white) == white) continue;
						if((image.getRGB(x    , y + flash_y + 1) & white) != white) continue;
						if((image.getRGB(x + 1, y + flash_y + 1) & white) != white) continue;
						// �u�E���v
						if((image.getRGB(x + flash_x    , y + flash_y    ) & white) == white) continue;
						if((image.getRGB(x + flash_x + 1, y + flash_y    ) & white) != white) continue;
						if((image.getRGB(x + flash_x    , y + flash_y + 1) & white) != white) continue;
						if((image.getRGB(x + flash_x + 1, y + flash_y + 1) & white) != white) continue;
						// ���o�ł����̂ŁA���̃f�B�X�v���C�̔ԍ�����э��W���擾����
						display_index = n;
						flash_px = x + 1;
						flash_py = y + 1;
						break;
					}
					if(display_index >= 0) break;
				}
				n++;
			}
			if(display_index >= 0){
				main_window.putLog("�f�B�X�v���C�ԍ�-������W�F" + display_index + "-" + flash_px + "," + flash_py);
			}else{
				main_window.putLog("�͂���̉�ʂ��擾�ł��܂���ł����B");
			}
		}
		catch(Exception error){
			error.printStackTrace();
		}
	}
	/* �͂���̉�ʂ��擾���� */
	static public BufferedImage getImage(){
		try{
			if(display_index < 0) return null;
			GraphicsDevice[] all_gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
			GraphicsConfiguration[] all_gc = all_gd[gd_index.get(display_index)].getConfigurations();
			Robot robot = new Robot(all_gd[gd_index.get(display_index)]);
			return robot.createScreenCapture(all_gc[gc_index.get(display_index)].getBounds()).getSubimage(flash_px, flash_py, flash_x, flash_y);
		}
		catch(Exception error){
			error.printStackTrace();
			return null;
		}
	}
}
