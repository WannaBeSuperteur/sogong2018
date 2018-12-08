import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * 목적: 회원의 ID, 비밀번호 찾기
 * 최초 작성일: 2018/12/01
 * 최종 수정일: 2018/12/03
 */
public class findInfo extends JFrame implements ActionListener {
	JButton OK = new JButton("확인");
	JButton cancel = new JButton("취소");
	Label nameLabel = new Label("이름");
	Label emailLabel = new Label("이메일주소");
	TextField name = new TextField(12);
	TextField email = new TextField(12);
	
	public findInfo() {
		super("ID/PW 찾기");
		setLayout(new GridLayout(3, 1));
		
		OK.addActionListener(this);
		cancel.addActionListener(this);
		
		Panel namePanel = new Panel();
		namePanel.add(nameLabel);
		namePanel.add(name);
		
		Panel emailPanel = new Panel();
		emailPanel.add(emailLabel);
		emailPanel.add(email);

		Panel btnPanel = new Panel();
		btnPanel.add(OK);
		btnPanel.add(cancel);
		
		add(namePanel);
		add(emailPanel);
		add(btnPanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 150);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		// 확인 버튼을 클릭한 경우
		if(obj == OK) {
			try {
				getInfoFromDB getInfo = new getInfoFromDB();
				String[][] memberData = getInfo.getInfoFromDB("members");
				
				for(int i = 0; i < memberData.length; i++) {
					if(memberData[i][2].equals(email.getText()) && memberData[i][3].equals(name.getText())) {
						JOptionPane.showMessageDialog(
								null, "ID: " + memberData[i][0] + ", PW: " + memberData[i][1],
								"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				JOptionPane.showMessageDialog(
						null, "이름 혹은 이메일주소를 확인하세요.",
						"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(
						null, "이름 혹은 이메일주소를 확인하세요.",
						"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		// 취소 버튼을 클릭한 경우
		else if(obj == cancel) {
			home a = new home();
			hide();
		}
	}
}
