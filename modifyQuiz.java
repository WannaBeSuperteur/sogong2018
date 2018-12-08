import java.awt.event.*;
import javax.swing.*;

/*
 * 목적: 퀴즈 수정 창 표시 및 이벤트 처리
 * 최초 작성일: 2018/12/01
 * 최종 수정일: 2018/12/08
 */
public class modifyQuiz extends JFrame implements ActionListener {
	quizInfo qI = null;
	String userId = "";
	quizInfoApply qIA = new quizInfoApply();
	
	public modifyQuiz(String title, String userId) {
		// 제목이 일치하는 퀴즈를 DB에서 불러오기
		getInfoFromDB getInfo = new getInfoFromDB();
		String[][] quizData = getInfo.getInfoFromDB("quiz");
		int quizIndex = getInfo.lookupData(quizData, 0, title);
		
		qI = new quizInfo("퀴즈 수정");
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

		// 로그아웃 버튼을 클릭한 경우
		if(obj == qI.logoutBtn) {
			member mem = new member("", "");
			mem.logout();
			qI.setVisible(false);
		}
		
		// 확인 버튼을 클릭한 경우
		else if(obj == qI.OK) {
			boolean apply = qIA.apply(qI, true, userId);
			if(apply) {
				qI.setVisible(false);
			}
		}
		
		// 취소 버튼을 클릭한 경우
		else if(obj == qI.cancel) {
			quizList a = new quizList(userId);
			qI.setVisible(false);
		}
		
		// 유형을 선택한 경우
		else if(obj == qI.type) {
			qIA.changeType(qI);
		}
	}
}
