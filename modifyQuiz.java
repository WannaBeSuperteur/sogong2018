import java.awt.event.*;
import javax.swing.*;

/*
 * ����: ���� ���� â ǥ�� �� �̺�Ʈ ó��
 * ���� �ۼ���: 2018/12/01
 * ���� ������: 2018/12/08
 */
public class modifyQuiz extends JFrame implements ActionListener {
	quizInfo qI = null;
	String userId = "";
	quizInfoApply qIA = new quizInfoApply();
	
	public modifyQuiz(String title, String userId) {
		// ������ ��ġ�ϴ� ��� DB���� �ҷ�����
		getInfoFromDB getInfo = new getInfoFromDB();
		String[][] quizData = getInfo.getInfoFromDB("quiz");
		int quizIndex = getInfo.lookupData(quizData, 0, title);
		
		qI = new quizInfo("���� ����");
		qI.title.setText(quizData[quizIndex][0]);
		qI.title.setEnabled(false);
		qI.content.setText(quizData[quizIndex][1]);
		qI.answer.setText(quizData[quizIndex][2]);
		qI.type.setSelectedIndex(Integer.parseInt(quizData[quizIndex][3]));
		qI.choice1.setText(quizData[quizIndex][4]);
		qI.choice2.setText(quizData[quizIndex][5]);
		qI.choice3.setText(quizData[quizIndex][6]);
		qI.choice4.setText(quizData[quizIndex][7]);
		qI.choice5.setText(quizData[quizIndex][8]);
		
		qI.logoutBtn.addActionListener(this);
		qI.OK.addActionListener(this);
		qI.cancel.addActionListener(this);
		qI.type.addActionListener(this);
		this.userId = userId;
		
		qIA.changeType(qI);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		// �α׾ƿ� ��ư�� Ŭ���� ���
		if(obj == qI.logoutBtn) {
			member mem = new member("", "");
			mem.logout();
			qI.setVisible(false);
		}
		
		// Ȯ�� ��ư�� Ŭ���� ���
		else if(obj == qI.OK) {
			boolean apply = qIA.apply(qI, true, userId);
			if(apply) {
				qI.setVisible(false);
			}
		}
		
		// ��� ��ư�� Ŭ���� ���
		else if(obj == qI.cancel) {
			quizList a = new quizList(userId);
			qI.setVisible(false);
		}
		
		// ������ ������ ���
		else if(obj == qI.type) {
			qIA.changeType(qI);
		}
	}
}
