import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * 목적: 회원탈퇴
 * 최초 작성일: 2018/12/01
 * 최종 수정일: 2018/12/03
 */
public class leaveMember extends JFrame {
	getInfoFromDB getInfo = new getInfoFromDB();
	
	public leaveMember(String id) {
		int result = JOptionPane.showConfirmDialog(
				null, "정말 탈퇴하시겠습니까?",
				"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
		// 회원 탈퇴 창에서 '예'를 클릭하면
		if(result == JOptionPane.YES_OPTION) {
			try {
				String[][] memberData = getInfo.getInfoFromDB("members");
				int idIndex = getInfo.lookupData(memberData, 0, id);
				
				// 파일에서 데이터 읽어오기
				FileReader readDB = new FileReader("D:\\members.txt");
				BufferedReader readDB_ = new BufferedReader(readDB);
				String toWrite = "";
				String str = null;
				
				int count = 0;
				while((str = readDB_.readLine()) != null) {
					if(count != idIndex) { // 찾는 ID가 있는 인덱스이면
						toWrite += (str + "\n");
					}
					count++;
				}
				readDB_.close();
				
				// 파일에 쓰기
				FileWriter writeDB = new FileWriter("D:\\members.txt", false);
				writeDB.write(toWrite);
				writeDB.close();
				
				JOptionPane.showMessageDialog(
						null, "탈퇴 되었습니다.",
						"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) { }
		}
	}
}
