import java.awt.event.*;
import javax.swing.*;

public class modifyMember extends JFrame implements ActionListener {
	memberInfo mI = null;
	
	public modifyMember(String id) {
		mI = new memberInfo("정보수정");
		mI.id.setText(id);
		mI.id.setEnabled(false);
		mI.idCheck.addActionListener(this);
		mI.OK.addActionListener(this);
		mI.cancel.addActionListener(this);
		
		getInfoFromDB getInfo = new getInfoFromDB();
		String[][] memberData = getInfo.getInfoFromDB("members");
		
		// 일치하는 ID를 찾아서 그 ID에 해당하는 비밀번호, 이름, 이메일을 표시하기
		int userIndex = getInfo.lookupData(memberData, 0, id);
		System.out.println(id + " " + userIndex);
		mI.pwd.setText(memberData[userIndex][1]);
		mI.email.setText(memberData[userIndex][2]);
		mI.name.setText(memberData[userIndex][3]);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		memberInfoCheck mIC = new memberInfoCheck();
		
		// ID 중복확인 버튼을 클릭한 경우
		if(obj == mI.idCheck) {
			mI.idChecked = true;
			mI.duplicatedID = mIC.checkDuplicated(mI.id.getText());
			mI.setVisible(false);
		}
		
		// 확인 버튼을 클릭한 경우
		else if(obj == mI.OK) {
			boolean modified = mIC.addMemberInfo(mI, true);
			if(modified) mI.setVisible(false);
		}
		
		// 취소 버튼을 클릭한 경우
		else if(obj == mI.cancel) {
			afterLogin a = new afterLogin(mI.id.getText());
			mI.setVisible(false);
		}
	}
}
