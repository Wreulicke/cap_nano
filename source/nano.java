/* �L�^�͑�؂Ȃ�.jar Ver.1.2 */

/* TODO:
 * ����
 * ����
 * �������߂�dispose���Ă���
 * ���@�����y�у\�[�g�ꗗ�̃f�t�H���g�ł̕\���ʒu��ύX
 * ���@�摜�S�폜�@�\(�摜�ۑ����Ɋm�F����`)
 * ���������摜�擾�@�\(�擾���x��1fps���x�ɂ��Ă���)
 * �������O�B���@�\(���g����щ��K����̖��O)
 * �����A�ˋ@�\(�ꉞ���x�����ł���悤�ɂ��Ă���)
 * �����N���b�v�@�\�\�Ґ���ʂŕҐ����������؂����ĕۑ�
 * �����N���b�v�@�\�\��`�Ŏ�������ю��v���������؂����ĕۑ�
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
