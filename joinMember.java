import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/*
 * ����: ȸ������
 * ���� �ۼ���: 2018/12/01
 * ���� ������: 2018/12/03
 */
public class joinMember extends JFrame implements ActionListener {
	memberInfo mI = null;
	
	public joinMember() {
		mI = new memberInfo("ȸ������");
		mI.idCheck.addActionListener(this);
		mI.OK.addActionListener(this);
		mI.cancel.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		memberInfoCheck mIC = new memberInfoCheck();
		
		// ID �ߺ�Ȯ�� ��ư�� Ŭ���� ���
		if(obj == mI.idCheck) {
			mI.idChecked = true;
			mI.duplicatedID = mIC.checkDuplicated(mI.id.getText());
		}
		
		// Ȯ�� ��ư�� Ŭ���� ���
		else if(obj == mI.OK) {
			boolean newMember = mIC.addMemberInfo(mI, false);
			if(newMember) mI.setVisible(false);
		}
		
		// ��� ��ư�� Ŭ���� ���
		else if(obj == mI.cancel) {
			home a = new home();
			mI.setVisible(false);
		}
	}
}
