import java.awt.event.*;
import javax.swing.*;

public class modifyMember extends JFrame implements ActionListener {
	memberInfo mI = null;
	
	public modifyMember(String id) {
		mI = new memberInfo("��������");
		mI.id.setText(id);
		mI.id.setEnabled(false);
		mI.idCheck.addActionListener(this);
		mI.OK.addActionListener(this);
		mI.cancel.addActionListener(this);
		
		getInfoFromDB getInfo = new getInfoFromDB();
		String[][] memberData = getInfo.getInfoFromDB("members");
		
		// ��ġ�ϴ� ID�� ã�Ƽ� �� ID�� �ش��ϴ� ��й�ȣ, �̸�, �̸����� ǥ���ϱ�
		int userIndex = getInfo.lookupData(memberData, 0, id);
		System.out.println(id + " " + userIndex);
		mI.pwd.setText(memberData[userIndex][1]);
		mI.email.setText(memberData[userIndex][2]);
		mI.name.setText(memberData[userIndex][3]);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		memberInfoCheck mIC = new memberInfoCheck();
		
		// ID �ߺ�Ȯ�� ��ư�� Ŭ���� ���
		if(obj == mI.idCheck) {
			mI.idChecked = true;
			mI.duplicatedID = mIC.checkDuplicated(mI.id.getText());
			mI.setVisible(false);
		}
		
		// Ȯ�� ��ư�� Ŭ���� ���
		else if(obj == mI.OK) {
			boolean modified = mIC.addMemberInfo(mI, true);
			if(modified) mI.setVisible(false);
		}
		
		// ��� ��ư�� Ŭ���� ���
		else if(obj == mI.cancel) {
			afterLogin a = new afterLogin(mI.id.getText());
			mI.setVisible(false);
		}
	}
}
