/* �L�^�͑�؂Ȃ�.jar Ver.1.1 */

/* TODO:
 * �ȏ�����������烊���[�X����
 */

public class nano{
	public static main_window main_frame;
	public static unit_window unit_frame;
	public static sort_window sort_frame;
	public static option_window option_frame;
	public static void main(String args[]){
		// �����ꗗ�p
		unit_frame = new unit_window();
		// �\�[�g�ꗗ�p
		sort_frame = new sort_window();
		// ���C�����
		main_frame = new main_window();
		// �I�v�V�������
		option_frame = new option_window();
	}
}
