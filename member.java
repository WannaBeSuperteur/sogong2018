import java.io.*;

import javax.swing.JOptionPane;

/*
 * 목적: 회원 객체에 대한 속성을 기술
 * 최초 작성일: 2018/12/01
 * 최종 수정일: 2018/12/03
 */
public class member {
	private String name;
	private String id;
	private String pwd;
	private String ppwd;
	private String email;
	
	public member(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	
	public void joinMember() {
		joinMember a = new joinMember();
	}
	
	public void modifyMember() {
		modifyMember a = new modifyMember(this.id);
	}
	
	public void leaveMember() {
		leaveMember a = new leaveMember(this.id);
	}
	
	public boolean login() {
		try {
			getInfoFromDB getInfo = new getInfoFromDB();
			String[][] memberData = getInfo.getInfoFromDB("members");
			int idIndex = getInfo.lookupData(memberData, 0, id);
			String pw = memberData[idIndex][1];
			
			if(pw.equals(this.pwd)) { // 비밀번호가 일치하면
				return true;
			} else { // 비밀번호가 일치하지 않으면
				JOptionPane.showMessageDialog(
						null, "아이디 혹은 비밀번호를 확인하세요.",
						"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					null, "아이디 혹은 비밀번호를 확인하세요.",
					"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	
	public void logout() {
		JOptionPane.showMessageDialog(
				null, "로그아웃 되었습니다.",
				"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
		home a = new home();
	}
	
	public void findinfo() {
		findInfo a = new findInfo();
	}
}
