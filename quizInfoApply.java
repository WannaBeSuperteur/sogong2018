import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class quizInfoApply {
	// 퀴즈 생성 또는 수정
	public boolean apply(quizInfo qI, boolean modify, String userId) {
		String title_ = qI.title.getText();
		String content_ = qI.content.getText();
		String answer_ = qI.answer.getText();
		int type_ = qI.type.getSelectedIndex(); // 객관식은 0, 단답형은 1
		
		String cho1 = qI.choice1.getText();
		String cho2 = qI.choice2.getText();
		String cho3 = qI.choice3.getText();
		String cho4 = qI.choice4.getText();
		String cho5 = qI.choice5.getText();
		
		// 정보를 입력하지 않은 경우
		if(title_.equals("") || content_.equals("") || answer_.equals("")) {
			JOptionPane.showMessageDialog(null, "입력이 올바르지 않습니다",
					"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// 객관식 퀴즈에서 선택지를 입력하지 않은 경우
		if(type_ == 0 && (cho1.equals("") || cho2.equals("") ||
				cho3.equals("") || cho4.equals("") || cho5.equals(""))) {
			JOptionPane.showMessageDialog(null, "입력이 올바르지 않습니다",
					"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		// 퀴즈에 대한 모든 정보를 입력한 경우
		else {
			try {
				/* 구분: 제목, 내용, 정답, 유형, 선택지 1,2,3,4,5,
				 *      사용자 ID, 푼 사람이 있는지 여부를 탭으로 구분
				 */
				String toAdd = "";
				if(qI.type.getSelectedIndex() == 0) { // 객관식인 경우
					toAdd = title_ + "\t" + content_ + "\t" + answer_ + "\t" + type_;
					toAdd += "\t" + cho1 + "\t" + cho2 + "\t" + cho3;
					toAdd += "\t" + cho4 + "\t" + cho5 + "\t" + userId + "\t0\n";
				} else { // 단답형인 경우
					toAdd = title_ + "\t" + content_ + "\t" + answer_ + "\t" + type_;
					toAdd += "\t \t \t \t \t \t" + userId + "\t0\n";
				}
				
				if(modify) { // 퀴즈를 수정하는 경우
					FileReader readDB = new FileReader("D:\\quiz.txt");
					BufferedReader readDB_ = new BufferedReader(readDB);
					String toWrite = "";
					String str = null;
					
					// 찾는 퀴즈 제목이 있는 인덱스(titleIndex) 구하기
					getInfoFromDB getInfo = new getInfoFromDB();
					String[][] quizData = getInfo.getInfoFromDB("quiz");
					int titleIndex = getInfo.lookupData(quizData, 0, title_);
					
					int count = 0;
					while((str = readDB_.readLine()) != null) {
						if(count == titleIndex) { // 찾는 ID가 있는 인덱스이면
							toWrite += toAdd;
						} else { // 그렇지 않으면
							toWrite += (str + "\n");
						}
						count++;
					}
					readDB.close();
					
					// 파일에 쓰기
					FileWriter writeDB = new FileWriter("D:\\quiz.txt", false);
					writeDB.write(toWrite);
					writeDB.close();
					
					JOptionPane.showMessageDialog(
							null, "수정 완료!",
							"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
					afterLogin a = new afterLogin(userId);
				} else { // 새로운 퀴즈를 만드는 경우
					FileWriter writeDB = new FileWriter("D:\\quiz.txt", true);
					writeDB.write(toAdd);
					writeDB.close();

					JOptionPane.showMessageDialog(
							null, "생성 완료!",
							"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
					afterLogin a = new afterLogin(userId);
				}
				return true;
			}
			catch (Exception e1) {
				System.out.print(e1.getMessage());
				return false;
			}			
		}
	}
	
	// 유형 변경
	public void changeType(quizInfo qI) {
		int type = qI.type.getSelectedIndex();
		if(type == 0) { // 객관식
			qI.choiceLabel.setEnabled(true);
			qI.choice1.setEnabled(true);
			qI.choice2.setEnabled(true);
			qI.choice3.setEnabled(true);
			qI.choice4.setEnabled(true);
			qI.choice5.setEnabled(true);
		} else { // 단답형
			qI.choiceLabel.setEnabled(false);
			qI.choice1.setEnabled(false);
			qI.choice2.setEnabled(false);
			qI.choice3.setEnabled(false);
			qI.choice4.setEnabled(false);
			qI.choice5.setEnabled(false);
		}
	}
}
