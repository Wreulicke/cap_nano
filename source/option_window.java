/* �I�v�V�����E�B���h�E */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class option_window extends JFrame implements ActionListener{
	/* �����o�ϐ� */
	// �萔
	static final int object_x = 80;
	static final int object_y = 24;
	static final int object_space = 10;
	// �ϐ�
	public static JCheckBox checkbox1;
	/* �R���X�g���N�^ */
	option_window(){
		// �E�B���h�E�̐ݒ�
		setTitle("�I�v�V����");
		getContentPane().setPreferredSize(new Dimension(position_x(3), position_y(1)));
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
			panel.add(checkbox1);
		getContentPane().add(panel, BorderLayout.CENTER);
		pack();
	}
	/* �C�x���g�����p */
	public void actionPerformed(ActionEvent event){
		String command_str = event.getActionCommand();
		if(command_str.equals("�r���\��")){
			nano.sort_frame.panel.repaint();
			nano.unit_frame.panel.repaint();
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
