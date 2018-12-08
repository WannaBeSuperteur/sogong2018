import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class quizInfoApply {
	// ���� ���� �Ǵ� ����
	public boolean apply(quizInfo qI, boolean modify, String userId) {
		String title_ = qI.title.getText();
		String content_ = qI.content.getText();
		String answer_ = qI.answer.getText();
		int type_ = qI.type.getSelectedIndex(); // �������� 0, �ܴ����� 1
		
		String cho1 = qI.choice1.getText();
		String cho2 = qI.choice2.getText();
		String cho3 = qI.choice3.getText();
		String cho4 = qI.choice4.getText();
		String cho5 = qI.choice5.getText();
		
		// ������ �Է����� ���� ���
		if(title_.equals("") || content_.equals("") || answer_.equals("")) {
			JOptionPane.showMessageDialog(null, "�Է��� �ùٸ��� �ʽ��ϴ�",
					"����ý���", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// ������ ����� �������� �Է����� ���� ���
		if(type_ == 0 && (cho1.equals("") || cho2.equals("") ||
				cho3.equals("") || cho4.equals("") || cho5.equals(""))) {
			JOptionPane.showMessageDialog(null, "�Է��� �ùٸ��� �ʽ��ϴ�",
					"����ý���", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		// ��� ���� ��� ������ �Է��� ���
		else {
			try {
				/* ����: ����, ����, ����, ����, ������ 1,2,3,4,5,
				 *      ����� ID, Ǭ ����� �ִ��� ���θ� ������ ����
				 */
				String toAdd = "";
				if(qI.type.getSelectedIndex() == 0) { // �������� ���
					toAdd = title_ + "\t" + content_ + "\t" + answer_ + "\t" + type_;
					toAdd += "\t" + cho1 + "\t" + cho2 + "\t" + cho3;
					toAdd += "\t" + cho4 + "\t" + cho5 + "\t" + userId + "\t0\n";
				} else { // �ܴ����� ���
					toAdd = title_ + "\t" + content_ + "\t" + answer_ + "\t" + type_;
					toAdd += "\t \t \t \t \t \t" + userId + "\t0\n";
				}
				
				if(modify) { // ��� �����ϴ� ���
					FileReader readDB = new FileReader("D:\\quiz.txt");
					BufferedReader readDB_ = new BufferedReader(readDB);
					String toWrite = "";
					String str = null;
					
					// ã�� ���� ������ �ִ� �ε���(titleIndex) ���ϱ�
					getInfoFromDB getInfo = new getInfoFromDB();
					String[][] quizData = getInfo.getInfoFromDB("quiz");
					int titleIndex = getInfo.lookupData(quizData, 0, title_);
					
					int count = 0;
					while((str = readDB_.readLine()) != null) {
						if(count == titleIndex) { // ã�� ID�� �ִ� �ε����̸�
							toWrite += toAdd;
						} else { // �׷��� ������
							toWrite += (str + "\n");
						}
						count++;
					}
					readDB.close();
					
					// ���Ͽ� ����
					FileWriter writeDB = new FileWriter("D:\\quiz.txt", false);
					writeDB.write(toWrite);
					writeDB.close();
					
					JOptionPane.showMessageDialog(
							null, "���� �Ϸ�!",
							"����ý���", JOptionPane.WARNING_MESSAGE);
					afterLogin a = new afterLogin(userId);
				} else { // ���ο� ��� ����� ���
					FileWriter writeDB = new FileWriter("D:\\quiz.txt", true);
					writeDB.write(toAdd);
					writeDB.close();

					JOptionPane.showMessageDialog(
							null, "���� �Ϸ�!",
							"����ý���", JOptionPane.WARNING_MESSAGE);
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
	
	// ���� ����
	public void changeType(quizInfo qI) {
		int type = qI.type.getSelectedIndex();
		if(type == 0) { // ������
			qI.choiceLabel.setEnabled(true);
			qI.choice1.setEnabled(true);
			qI.choice2.setEnabled(true);
			qI.choice3.setEnabled(true);
			qI.choice4.setEnabled(true);
			qI.choice5.setEnabled(true);
		} else { // �ܴ���
			qI.choiceLabel.setEnabled(false);
			qI.choice1.setEnabled(false);
			qI.choice2.setEnabled(false);
			qI.choice3.setEnabled(false);
			qI.choice4.setEnabled(false);
			qI.choice5.setEnabled(false);
		}
	}
}
