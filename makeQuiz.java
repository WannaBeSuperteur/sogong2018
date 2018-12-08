import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import javax.swing.*;

public class makeQuiz extends JFrame implements ActionListener {
	quizInfo qI = null;
	String userId = "";
	
	public makeQuiz(String userId) {
		qI = new quizInfo("퀴즈 만들기");
		qI.logoutBtn.addActionListener(this);
		qI.OK.addActionListener(this);
		qI.cancel.addActionListener(this);
		qI.type.addActionListener(this);
		this.userId = userId;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		quizInfoApply qIA = new quizInfoApply();
		
		// 로그아웃 버튼을 클릭한 경우
		if(obj == qI.logoutBtn) {
			member mem = new member("", "");
			mem.logout();
			qI.setVisible(false);
		}
		
		// 확인 버튼을 클릭한 경우
		else if(obj == qI.OK) {
			boolean apply = qIA.apply(qI, false, userId);
			if(apply) {
				qI.setVisible(false);
			}
		}
		
		// 취소 버튼을 클릭한 경우
		else if(obj == qI.cancel) {
			afterLogin a = new afterLogin(userId);
			qI.setVisible(false);
		}
		
		// 유형을 선택한 경우
		else if(obj == qI.type) {
			qIA.changeType(qI);
		}
	}
}
