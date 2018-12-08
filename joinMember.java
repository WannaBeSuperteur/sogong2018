import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/*
 * 목적: 회원가입
 * 최초 작성일: 2018/12/01
 * 최종 수정일: 2018/12/03
 */
public class joinMember extends JFrame implements ActionListener {
	memberInfo mI = null;
	
	public joinMember() {
		mI = new memberInfo("회원가입");
		mI.idCheck.addActionListener(this);
		mI.OK.addActionListener(this);
		mI.cancel.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		memberInfoCheck mIC = new memberInfoCheck();
		
		// ID 중복확인 버튼을 클릭한 경우
		if(obj == mI.idCheck) {
			mI.idChecked = true;
			mI.duplicatedID = mIC.checkDuplicated(mI.id.getText());
		}
		
		// 확인 버튼을 클릭한 경우
		else if(obj == mI.OK) {
			boolean newMember = mIC.addMemberInfo(mI, false);
			if(newMember) mI.setVisible(false);
		}
		
		// 취소 버튼을 클릭한 경우
		else if(obj == mI.cancel) {
			home a = new home();
			mI.setVisible(false);
		}
	}
}
