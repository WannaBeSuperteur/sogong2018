import java.io.*;
import javax.swing.JOptionPane;

public class deleteQuiz {
	public deleteQuiz(String title) {
		try {
			FileReader readDB = new FileReader("D:\\quiz.txt");
			BufferedReader readDB_ = new BufferedReader(readDB);
			String toWrite = "";
			String str = null;
			
			// ã�� ���� ������ �ִ� �ε���(titleIndex) ���ϱ�
			getInfoFromDB getInfo = new getInfoFromDB();
			String[][] quizData = getInfo.getInfoFromDB("quiz");
			int titleIndex = getInfo.lookupData(quizData, 0, title);
			
			int count = 0;
			while((str = readDB_.readLine()) != null) {
				if(count != titleIndex) { // ã�� ���� ������ �ִ� �ε����� �����ϰ� ����
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
		}
		catch (Exception e1) { System.out.print(e1.getMessage());}
	}
}
