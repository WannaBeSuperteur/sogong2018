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
		qI = new quizInfo("���� �����");
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
		
		// �α׾ƿ� ��ư�� Ŭ���� ���
		if(obj == qI.logoutBtn) {
			member mem = new member("", "");
			mem.logout();
			qI.setVisible(false);
		}
		
		// Ȯ�� ��ư�� Ŭ���� ���
		else if(obj == qI.OK) {
			boolean apply = qIA.apply(qI, false, userId);
			if(apply) {
				qI.setVisible(false);
			}
		}
		
		// ��� ��ư�� Ŭ���� ���
		else if(obj == qI.cancel) {
			afterLogin a = new afterLogin(userId);
			qI.setVisible(false);
		}
		
		// ������ ������ ���
		else if(obj == qI.type) {
			qIA.changeType(qI);
		}
	}
}
