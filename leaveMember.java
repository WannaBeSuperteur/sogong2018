import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * ����: ȸ��Ż��
 * ���� �ۼ���: 2018/12/01
 * ���� ������: 2018/12/03
 */
public class leaveMember extends JFrame {
	getInfoFromDB getInfo = new getInfoFromDB();
	
	public leaveMember(String id) {
		int result = JOptionPane.showConfirmDialog(
				null, "���� Ż���Ͻðڽ��ϱ�?",
				"����ý���", JOptionPane.WARNING_MESSAGE);
		// ȸ�� Ż�� â���� '��'�� Ŭ���ϸ�
		if(result == JOptionPane.YES_OPTION) {
			try {
				String[][] memberData = getInfo.getInfoFromDB("members");
				int idIndex = getInfo.lookupData(memberData, 0, id);
				
				// ���Ͽ��� ������ �о����
				FileReader readDB = new FileReader("D:\\members.txt");
				BufferedReader readDB_ = new BufferedReader(readDB);
				String toWrite = "";
				String str = null;
				
				int count = 0;
				while((str = readDB_.readLine()) != null) {
					if(count != idIndex) { // ã�� ID�� �ִ� �ε����̸�
						toWrite += (str + "\n");
					}
					count++;
				}
				readDB_.close();
				
				// ���Ͽ� ����
				FileWriter writeDB = new FileWriter("D:\\members.txt", false);
				writeDB.write(toWrite);
				writeDB.close();
				
				JOptionPane.showMessageDialog(
						null, "Ż�� �Ǿ����ϴ�.",
						"����ý���", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) { }
		}
	}
}
