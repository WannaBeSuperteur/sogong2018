import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class memberInfoCheck {
	
	// 중복된 ID인지 검사
	public boolean checkDuplicated(String id) {
		try {
			getInfoFromDB getInfo = new getInfoFromDB();
			String[][] memberData = getInfo.getInfoFromDB("members");
			
			// 일치하는 ID가 있는지 검사하기
			for(int i = 0; i < memberData.length; i++) {
				if(memberData[i][0].equals(id)) {
					JOptionPane.showMessageDialog(null, "중복된 아이디입니다.",
							"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
					return true;
				}
			}
			JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.",
					"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
			return false;
		} catch (Exception e1) { return false; }
	}
	
	// 회원정보를 DB에 추가
	public boolean addMemberInfo (memberInfo mI, boolean modify) {
		// 이름을 입력하지 않은 경우
		if(mI.name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "이름을 입력하세요.",
					"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
		}
		
		// 아이디를 입력하지 않은 경우
		else if(mI.id.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "아이디를 확인하세요.",
					"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
		}
		
		// 비밀번호를 입력하지 않은 경우
		else if(mI.pwd.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요.",
					"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
		}
		
		// 비밀번호와 그 확인 값이 다른 경우
		else if(!mI.pwd.getText().equals(mI.ppwd.getText())) {
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.",
					"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
		}
		
		// 이메일을 입력하지 않은 경우
		else if(mI.email.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "이메일 주소를 확인하세요.",
					"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
		}
		
		// ID 중복 확인을 하지 않은 경우
		else if(!mI.idChecked && !modify) {
			JOptionPane.showMessageDialog(null, "ID 중복 확인을 먼저 해 주세요.",
					"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
		}
		
		// ID가 중복된 경우
		else if(mI.duplicatedID && !modify) {
			JOptionPane.showMessageDialog(null, "중복된 아이디입니다.",
					"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
		}
		
		// 모든 정보를 입력한 경우
		else {
			String id_ = mI.id.getText();
			String pwd_ = mI.pwd.getText();
			String email_ = mI.email.getText();
			String name_ = mI.name.getText();
			
			// 입력 정보에 공백이 포함된 경우
			if(id_.contains(" ") || pwd_.contains(" ") ||
					email_.contains(" ") || name_.contains(" ")) {
				JOptionPane.showMessageDialog(
						null, "아이디, 비밀번호, 이메일, 이름에는 공백이 없어야 합니다.",
						"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
			} else { // 모든 정보를 공백 없이 입력한 경우
				try {
					String toAdd = id_ + " " + pwd_ + " " + email_ + " " + name_ + "\n";
					
					if(modify) { // 회원정보를 수정하는 경우
						FileReader readDB = new FileReader("D:\\members.txt");
						BufferedReader readDB_ = new BufferedReader(readDB);
						String toWrite = "";
						String str = null;
						
						// 찾는 ID가 있는 인덱스(idIndex) 구하기
						getInfoFromDB getInfo = new getInfoFromDB();
						String[][] memberData = getInfo.getInfoFromDB("members");
						int idIndex = getInfo.lookupData(memberData, 0, id_);
						
						int count = 0;
						while((str = readDB_.readLine()) != null) {
							if(count == idIndex) { // 찾는 ID가 있는 인덱스이면
								toWrite += toAdd;
							} else { // 그렇지 않으면
								toWrite += (str + "\n");
							}
							count++;
						}
						readDB.close();
						
						// 파일에 쓰기
						FileWriter writeDB = new FileWriter("D:\\members.txt", false);
						writeDB.write(toWrite);
						writeDB.close();
						
						JOptionPane.showMessageDialog(
								null, "회원정보 수정이 완료되었습니다.",
								"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
						
						// 로그인 이후의 창 열기
						afterLogin a = new afterLogin(id_);
						mI.setVisible(false);
						return true;
					} else { // 회원가입을 하는 경우
						FileWriter writeDB = new FileWriter("D:\\members.txt", true);
						writeDB.write(toAdd);
						writeDB.close();

						JOptionPane.showMessageDialog(
								null, "회원가입이 완료되었습니다.",
								"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
						
						// 메인으로 돌아가기
						home a = new home();
						mI.setVisible(false);
						return true;
					}					
				}
				catch (Exception e1) { }
			}				
		}
		return false;
	}
}
