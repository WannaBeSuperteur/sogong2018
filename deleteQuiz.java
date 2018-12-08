import java.io.*;
import javax.swing.JOptionPane;

public class deleteQuiz {
	public deleteQuiz(String title) {
		try {
			FileReader readDB = new FileReader("D:\\quiz.txt");
			BufferedReader readDB_ = new BufferedReader(readDB);
			String toWrite = "";
			String str = null;
			
			// 찾는 퀴즈 제목이 있는 인덱스(titleIndex) 구하기
			getInfoFromDB getInfo = new getInfoFromDB();
			String[][] quizData = getInfo.getInfoFromDB("quiz");
			int titleIndex = getInfo.lookupData(quizData, 0, title);
			
			int count = 0;
			while((str = readDB_.readLine()) != null) {
				if(count != titleIndex) { // 찾는 퀴즈 제목이 있는 인덱스를 제외하고 쓰기
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
					null, "삭제 완료!",
					"퀴즈시스템", JOptionPane.WARNING_MESSAGE);				
		}
		catch (Exception e1) { System.out.print(e1.getMessage());}
	}
}
