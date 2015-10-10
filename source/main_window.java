/* ���C���E�B���h�E */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class main_window extends JFrame implements ActionListener{
	/* �����o�ϐ� */
	// �萔
	static final int object_x = 80;
	static final int object_y = 24;
	static final int object_space = 10;
	static final String soft_name = "�L�^�͑�؂Ȃ�";
	static final String[] save_type = {"�ʏ�", "����", "�\�[�g"};
	static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS");
	// �ϐ�
	static JComboBox combo_box, join_combo_dir, join_combo_type;
	DefaultComboBoxModel model_dir, model_type;
	public static JTextArea text_area;
	/* �R���X�g���N�^ */
	main_window(){
		// �E�B���h�E�̐ݒ�
		setTitle(soft_name);
		getContentPane().setPreferredSize(new Dimension(position_x(4), position_y(5)));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setAlwaysOnTop(true);
		// �I�u�W�F�N�g�̐ݒ�
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JButton button1 = new JButton("���W�擾");
			button1.setBounds(position_x(0), position_y(0), size_x(1), size_y(1));
			button1.setMargin(new Insets(0, 0, 0, 0));
			button1.addActionListener(this);
			button1.setActionCommand("���W�擾");
			panel.add(button1);
		combo_box = new JComboBox(save_type);
			combo_box.setBounds(position_x(1), position_y(0), size_x(1), size_y(1));
			combo_box.addActionListener(this);
			combo_box.setActionCommand("�I��ύX");
			panel.add(combo_box);
		JButton button2 = new JButton("�摜�ǉ�");
			button2.setBounds(position_x(2), position_y(0), size_x(1), size_y(1));
			button2.setMargin(new Insets(0, 0, 0, 0));
			button2.addActionListener(this);
			button2.setActionCommand("�摜�ǉ�");
			button2.setMnemonic(KeyEvent.VK_Z);
			panel.add(button2);
		JButton button3 = new JButton("�摜�ۑ�");
			button3.setBounds(position_x(3), position_y(0), size_x(1), size_y(1));
			button3.setMargin(new Insets(0, 0, 0, 0));
			button3.addActionListener(this);
			button3.setActionCommand("�摜�ۑ�");
			panel.add(button3);
		text_area = new JTextArea();
			text_area.setEditable(false);
			JScrollPane scrollpane = new JScrollPane(text_area);
			scrollpane.setBounds(position_x(0), position_y(2), size_x(4), size_y(3));
			panel.add(scrollpane);
		model_dir = new DefaultComboBoxModel();
			join_combo_dir = new JComboBox(model_dir);
			join_combo_dir.setBounds(position_x(0), position_y(1), size_x(1), size_y(1));
			join_combo_dir.addActionListener(this);
			join_combo_dir.setActionCommand("�����ύX");
			join_combo_dir.setEnabled(false);
			panel.add(join_combo_dir);
		model_type = new DefaultComboBoxModel();
			join_combo_type = new JComboBox(model_type);
			join_combo_type.setBounds(position_x(1), position_y(1), size_x(1), size_y(1));
			join_combo_type.addActionListener(this);
			join_combo_type.setActionCommand("��ޕύX");
			join_combo_type.setEnabled(false);
			panel.add(join_combo_type);
		JButton button4 = new JButton("�I�v�V����");
			button4.setBounds(position_x(2), position_y(1), size_x(1), size_y(1));
			button4.setMargin(new Insets(0, 0, 0, 0));
			button4.addActionListener(this);
			button4.setActionCommand("�I�v�V����");
			panel.add(button4);
		getContentPane().add(panel, BorderLayout.CENTER);
		pack();
	}
	/* �C�x���g�����p */
	public void actionPerformed(ActionEvent event){
		String command_str = event.getActionCommand();
		if(command_str.equals("���W�擾")){
			capture.getKancollePosition();
		}
		if(command_str.equals("�I��ύX")){
			switch((String)combo_box.getSelectedItem()){
				case "�ʏ�":
					nano.unit_frame.setVisible(false);
					nano.sort_frame.setVisible(false);
					model_dir  = new DefaultComboBoxModel();
					model_type = new DefaultComboBoxModel();
					join_combo_dir.setModel(model_dir);
					join_combo_type.setModel(model_type);
					join_combo_dir.setEnabled(false);
					join_combo_type.setEnabled(false);
					break;
				case "����":
					nano.unit_frame.setVisible(true);
					nano.sort_frame.setVisible(false);
					model_dir  = new DefaultComboBoxModel(nano.unit_frame.show_dir_str);
					model_type = new DefaultComboBoxModel(nano.unit_frame.show_type_str);
					model_dir.setSelectedItem(model_dir.getElementAt(nano.unit_frame.show_dir));
					model_type.setSelectedItem(model_type.getElementAt(nano.unit_frame.show_type));
					join_combo_dir.setModel(model_dir);
					join_combo_type.setModel(model_type);
					join_combo_dir.setEnabled(true);
					join_combo_type.setEnabled(true);
					break;
				case "�\�[�g":
					nano.unit_frame.setVisible(false);
					nano.sort_frame.setVisible(true);
					model_dir  = new DefaultComboBoxModel(nano.sort_frame.show_dir_str);
					model_type = new DefaultComboBoxModel(nano.sort_frame.show_type_str);
					model_dir.setSelectedItem(model_dir.getElementAt(nano.sort_frame.show_dir));
					model_type.setSelectedItem(model_type.getElementAt(nano.sort_frame.show_type));
					join_combo_dir.setModel(model_dir);
					join_combo_type.setModel(model_type);
					join_combo_dir.setEnabled(true);
					join_combo_type.setEnabled(true);
					break;
			}
		}
		if(command_str.equals("�����ύX")){
			switch((String)combo_box.getSelectedItem()){
				case "����":
					nano.unit_frame.changeMode(join_combo_dir.getSelectedIndex(), join_combo_type.getSelectedIndex());
					break;
				case "�\�[�g":
					nano.sort_frame.changeMode(join_combo_dir.getSelectedIndex(), join_combo_type.getSelectedIndex());
					break;
			}
		}
		if(command_str.equals("��ޕύX")){
			switch((String)combo_box.getSelectedItem()){
				case "����":
					nano.unit_frame.changeMode(join_combo_dir.getSelectedIndex(), join_combo_type.getSelectedIndex());
					break;
				case "�\�[�g":
					nano.sort_frame.changeMode(join_combo_dir.getSelectedIndex(), join_combo_type.getSelectedIndex());
					break;
			}
		}
		if(command_str.equals("�摜�ǉ�")){
			switch((String)combo_box.getSelectedItem()){
				case "����":
					nano.unit_frame.addImage(capture.getImage());
					break;
				case "�\�[�g":
					nano.sort_frame.addImage(capture.getImage());
					break;
			}
		}
		if(command_str.equals("�摜�ۑ�")){
			switch((String)combo_box.getSelectedItem()){
				case "�ʏ�":
					try{
						if(capture.display_index < 0) return;
						BufferedImage flash_image = capture.getImage();
						if(flash_image == null) return;
						putLog("�y�摜�ۑ��z");
						String save_name = sdf.format(Calendar.getInstance().getTime()) + ".png";
						ImageIO.write(flash_image, "png", new File(save_name));
						putLog(save_name);
					}
					catch(Exception error){
						error.printStackTrace();
					}
					break;
				case "����":
					nano.unit_frame.savePicture();
					break;
				case "�\�[�g":
					nano.sort_frame.savePicture();
					break;
			}
		}
		if(command_str.equals("�I�v�V����")){
			nano.option_frame.setVisible(true);
		}
	}
	/* �e�L�X�g�G���A�Ƀe�L�X�g��ǉ����� */
	public static void putLog(String message){
		text_area.append(message + "\n");
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
