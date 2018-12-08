import java.io.BufferedReader;
import java.io.FileReader;

/*
 * 목적: 회원 DB, 퀴즈 DB에서 정보를 찾는 것을 쉽게 하기 위한 클래스
 * getInfoFromDB 메서드: DB용 파일에 있는 데이터를 표 형태의 2차원 배열로 바꾼다.
 * lookupData: 데이터 배열의 특정 번째 열의 값이 특정 값인 것을 찾아서 몇 번째 행인지 구한다.
 * 최초 작성일: 2018/12/01
 * 최종 수정일: 2018/12/03
 */
public class getInfoFromDB {
	// 파일에 있는 데이터를 배열로 변환
	public String[][] getInfoFromDB(String DBName) {
		try {
			FileReader readDB = new FileReader("D:\\" + DBName + ".txt");
			BufferedReader readDB_ = new BufferedReader(readDB);
			String data = "";
			String str = null;
			
			// 파일에서 ID 데이터 읽어오기
			while((str = readDB_.readLine()) != null) {
				data += (str + "\n");
			}
			readDB_.close();
			
			// 원하는 배열 만들기
			String[] eachLine = data.split("\n");
			String[][] result = new String[eachLine.length][];
			for(int i = 0; i < eachLine.length; i++) {
				if(DBName.equals("members")) { // members(회원정보) 데이터의 경우
					result[i] = eachLine[i].split(" ");
				} else { // 퀴즈 데이터의 경우
					result[i] = eachLine[i].split("\t");
				}
			}
			return result;
		} catch (Exception e1) { return null; }
	}
	
	// data 배열의 column번째 열의 값 중 값이 text인 것을 찾아서 몇 번째 행인지 구하기
	public int lookupData(String[][] data, int column, String text) {
		for(int i = 0; i < data.length; i++) {
			if(data[i][column].equals(text)) {
				return i;
			}
		}
		return -1;
	}
}
