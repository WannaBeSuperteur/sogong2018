import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class memberInfoCheck {
	
	// �ߺ��� ID���� �˻�
	public boolean checkDuplicated(String id) {
		try {
			getInfoFromDB getInfo = new getInfoFromDB();
			String[][] memberData = getInfo.getInfoFromDB("members");
			
			// ��ġ�ϴ� ID�� �ִ��� �˻��ϱ�
			for(int i = 0; i < memberData.length; i++) {
				if(memberData[i][0].equals(id)) {
					JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�.",
							"����ý���", JOptionPane.WARNING_MESSAGE);
					return true;
				}
			}
			JOptionPane.showMessageDialog(null, "��� ������ ���̵��Դϴ�.",
					"����ý���", JOptionPane.WARNING_MESSAGE);
			return false;
		} catch (Exception e1) { return false; }
	}
	
	// ȸ�������� DB�� �߰�
	public boolean addMemberInfo (memberInfo mI, boolean modify) {
		// �̸��� �Է����� ���� ���
		if(mI.name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "�̸��� �Է��ϼ���.",
					"����ý���", JOptionPane.WARNING_MESSAGE);
		}
		
		// ���̵� �Է����� ���� ���
		else if(mI.id.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "���̵� Ȯ���ϼ���.",
					"����ý���", JOptionPane.WARNING_MESSAGE);
		}
		
		// ��й�ȣ�� �Է����� ���� ���
		else if(mI.pwd.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� Ȯ���ϼ���.",
					"����ý���", JOptionPane.WARNING_MESSAGE);
		}
		
		// ��й�ȣ�� �� Ȯ�� ���� �ٸ� ���
		else if(!mI.pwd.getText().equals(mI.ppwd.getText())) {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.",
					"����ý���", JOptionPane.WARNING_MESSAGE);
		}
		
		// �̸����� �Է����� ���� ���
		else if(mI.email.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "�̸��� �ּҸ� Ȯ���ϼ���.",
					"����ý���", JOptionPane.WARNING_MESSAGE);
		}
		
		// ID �ߺ� Ȯ���� ���� ���� ���
		else if(!mI.idChecked && !modify) {
			JOptionPane.showMessageDialog(null, "ID �ߺ� Ȯ���� ���� �� �ּ���.",
					"����ý���", JOptionPane.WARNING_MESSAGE);
		}
		
		// ID�� �ߺ��� ���
		else if(mI.duplicatedID && !modify) {
			JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�.",
					"����ý���", JOptionPane.WARNING_MESSAGE);
		}
		
		// ��� ������ �Է��� ���
		else {
			String id_ = mI.id.getText();
			String pwd_ = mI.pwd.getText();
			String email_ = mI.email.getText();
			String name_ = mI.name.getText();
			
			// �Է� ������ ������ ���Ե� ���
			if(id_.contains(" ") || pwd_.contains(" ") ||
					email_.contains(" ") || name_.contains(" ")) {
				JOptionPane.showMessageDialog(
						null, "���̵�, ��й�ȣ, �̸���, �̸����� ������ ����� �մϴ�.",
						"����ý���", JOptionPane.WARNING_MESSAGE);
			} else { // ��� ������ ���� ���� �Է��� ���
				try {
					String toAdd = id_ + " " + pwd_ + " " + email_ + " " + name_ + "\n";
					
					if(modify) { // ȸ�������� �����ϴ� ���
						FileReader readDB = new FileReader("D:\\members.txt");
						BufferedReader readDB_ = new BufferedReader(readDB);
						String toWrite = "";
						String str = null;
						
						// ã�� ID�� �ִ� �ε���(idIndex) ���ϱ�
						getInfoFromDB getInfo = new getInfoFromDB();
						String[][] memberData = getInfo.getInfoFromDB("members");
						int idIndex = getInfo.lookupData(memberData, 0, id_);
						
						int count = 0;
						while((str = readDB_.readLine()) != null) {
							if(count == idIndex) { // ã�� ID�� �ִ� �ε����̸�
								toWrite += toAdd;
							} else { // �׷��� ������
								toWrite += (str + "\n");
							}
							count++;
						}
						readDB.close();
						
						// ���Ͽ� ����
						FileWriter writeDB = new FileWriter("D:\\members.txt", false);
						writeDB.write(toWrite);
						writeDB.close();
						
						JOptionPane.showMessageDialog(
								null, "ȸ������ ������ �Ϸ�Ǿ����ϴ�.",
								"����ý���", JOptionPane.WARNING_MESSAGE);
						
						// �α��� ������ â ����
						afterLogin a = new afterLogin(id_);
						mI.setVisible(false);
						return true;
					} else { // ȸ�������� �ϴ� ���
						FileWriter writeDB = new FileWriter("D:\\members.txt", true);
						writeDB.write(toAdd);
						writeDB.close();

						JOptionPane.showMessageDialog(
								null, "ȸ�������� �Ϸ�Ǿ����ϴ�.",
								"����ý���", JOptionPane.WARNING_MESSAGE);
						
						// �������� ���ư���
						home a = new home();
						mI.setVisible(false);
						return true;
					}					
				}
				catch (Exception e1) { }
			}				
		}
		return false;
	}
}
