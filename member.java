import java.io.*;

import javax.swing.JOptionPane;

/*
 * ����: ȸ�� ��ü�� ���� �Ӽ��� ���
 * ���� �ۼ���: 2018/12/01
 * ���� ������: 2018/12/03
 */
public class member {
	private String name;
	private String id;
	private String pwd;
	private String ppwd;
	private String email;
	
	public member(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	
	public void joinMember() {
		joinMember a = new joinMember();
	}
	
	public void modifyMember() {
		modifyMember a = new modifyMember(this.id);
	}
	
	public void leaveMember() {
		leaveMember a = new leaveMember(this.id);
	}
	
	public boolean login() {
		try {
			getInfoFromDB getInfo = new getInfoFromDB();
			String[][] memberData = getInfo.getInfoFromDB("members");
			int idIndex = getInfo.lookupData(memberData, 0, id);
			String pw = memberData[idIndex][1];
			
			if(pw.equals(this.pwd)) { // ��й�ȣ�� ��ġ�ϸ�
				return true;
			} else { // ��й�ȣ�� ��ġ���� ������
				JOptionPane.showMessageDialog(
						null, "���̵� Ȥ�� ��й�ȣ�� Ȯ���ϼ���.",
						"����ý���", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					null, "���̵� Ȥ�� ��й�ȣ�� Ȯ���ϼ���.",
					"����ý���", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	
	public void logout() {
		JOptionPane.showMessageDialog(
				null, "�α׾ƿ� �Ǿ����ϴ�.",
				"����ý���", JOptionPane.WARNING_MESSAGE);
		home a = new home();
	}
	
	public void findinfo() {
		findInfo a = new findInfo();
	}
}
