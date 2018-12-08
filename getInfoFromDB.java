import java.io.BufferedReader;
import java.io.FileReader;

public class getInfoFromDB {
	// ���Ͽ� �ִ� �����͸� �迭�� ��ȯ
	public String[][] getInfoFromDB(String DBName) {
		try {
			FileReader readDB = new FileReader("D:\\" + DBName + ".txt");
			BufferedReader readDB_ = new BufferedReader(readDB);
			String data = "";
			String str = null;
			
			// ���Ͽ��� ID ������ �о����
			while((str = readDB_.readLine()) != null) {
				data += (str + "\n");
			}
			readDB_.close();
			
			// ���ϴ� �迭 �����
			String[] eachLine = data.split("\n");
			String[][] result = new String[eachLine.length][];
			for(int i = 0; i < eachLine.length; i++) {
				if(DBName.equals("members")) { // members(ȸ������) �������� ���
					result[i] = eachLine[i].split(" ");
				} else { // ���� �������� ���
					result[i] = eachLine[i].split("\t");
				}
			}
			return result;
		} catch (Exception e1) { return null; }
	}
	
	// data �迭�� column��° ���� �� �� ���� text�� ���� ã�Ƽ� �� ��° ������ ���ϱ�
	public int lookupData(String[][] data, int column, String text) {
		for(int i = 0; i < data.length; i++) {
			if(data[i][column].equals(text)) {
				return i;
			}
		}
		return -1;
	}
}
