/* �I�v�V�����E�B���h�E */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class option_window extends JFrame implements ActionListener{
	/* �����o�ϐ� */
	// �萔
	static final int object_x = 80;
	static final int object_y = 24;
	static final int object_space = 10;
	static final int[] fps_int = {0, 1, 2, 3, 5, 10};
	static final String[] fps_str = {"����", "1fps", "2fps", "3fps", "5fps", "10fps"};
	// �ϐ�
	public static int fps;
	public static JCheckBox checkbox1, checkbox2, checkbox3;
	static JComboBox combo_box;
	/* �R���X�g���N�^ */
	option_window(){
		// �E�B���h�E�̐ݒ�
		setTitle("�I�v�V����");
		getContentPane().setPreferredSize(new Dimension(position_x(3), position_y(2)));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setAlwaysOnTop(true);
		// �I�u�W�F�N�g�̐ݒ�
		JPanel panel = new JPanel();
		panel.setLayout(null);
		checkbox1 = new JCheckBox("�r���\��");
			checkbox1.setBounds(position_x(0), position_y(0), size_x(1), size_y(1));
			checkbox1.setMargin(new Insets(0, 0, 0, 0));
			checkbox1.addActionListener(this);
			checkbox1.setActionCommand("�r���\��");
			checkbox1.setSelected(true);
			panel.add(checkbox1);
		checkbox2 = new JCheckBox("�����擾");
			checkbox2.setBounds(position_x(1), position_y(0), size_x(1), size_y(1));
			checkbox2.setMargin(new Insets(0, 0, 0, 0));
			checkbox2.addActionListener(this);
			checkbox2.setActionCommand("�����擾");
			panel.add(checkbox2);
		checkbox3 = new JCheckBox("���O�B��");
			checkbox3.setBounds(position_x(2), position_y(0), size_x(1), size_y(1));
			checkbox3.setMargin(new Insets(0, 0, 0, 0));
			checkbox3.addActionListener(this);
			checkbox3.setActionCommand("���O�B��");
			checkbox3.setSelected(true);
			panel.add(checkbox3);
		combo_box = new JComboBox(fps_str);
			combo_box.setBounds(position_x(0), position_y(1), size_x(1), size_y(1));
			combo_box.addActionListener(this);
			combo_box.setActionCommand("fps�ύX");
			panel.add(combo_box);
		getContentPane().add(panel, BorderLayout.CENTER);
		pack();
	}
	/* �C�x���g�����p */
	public void actionPerformed(ActionEvent event){
		String command_str = event.getActionCommand();
		if(command_str == null) return;
		if(command_str.equals("�r���\��")){
			nano.sort_frame.panel.repaint();
			nano.unit_frame.panel.repaint();
		}
		if(command_str.equals("�����擾")){
			if(checkbox2.isSelected()){
				if(main_window.combo_box1.getSelectedIndex() != 0){
					main_window.timer.restart();
					main_window.timer.setDelay(500);
				}
			}else{
				if(main_window.combo_box1.getSelectedIndex() != 0){
					main_window.timer.setDelay(1000 * 60 * 60 * 24);
				}
			}
		}
		if(command_str.equals("fps�ύX")){
			fps = fps_int[combo_box.getSelectedIndex()];
			if(fps != 0){
				main_window.putLog("�y�A�ˋ@�\�z");
				main_window.putLog("fps�F" + fps + "fps");
				if(main_window.combo_box1.getSelectedIndex() == 0){
					main_window.timer.restart();
					main_window.timer.setDelay(1000 / fps);
				}
			}else{
				main_window.putLog("�y�A�ˋ@�\�z");
				main_window.putLog("fps�FOFF");
				if(main_window.combo_box1.getSelectedIndex() == 0){
					main_window.timer.setDelay(1000 * 60 * 60 * 24);
				}
			}
		}
	}
	/* �I�u�W�F�N�g�p�萔���v�Z���� */
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
}
