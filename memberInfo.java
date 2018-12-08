import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class memberInfo extends JFrame {
	JButton idCheck = new JButton("아이디 중복확인");
	JButton OK = new JButton("확인");
	JButton cancel = new JButton("취소");
	
	Label nameLabel = new Label("이름");
	Label idLabel = new Label("아이디");
	Label pwdLabel = new Label("비밀번호");
	Label ppwdLabel = new Label("비밀번호 확인");
	Label emailLabel = new Label("이메일주소");
	
	TextField name = new TextField(20);
	TextField id = new TextField(10);
	TextField pwd = new TextField(10);
	TextField ppwd = new TextField(10);
	TextField email = new TextField(20);
	
	boolean idChecked = false; // ID 중복확인 여부
	boolean duplicatedID = false; // ID 중복 여부
	
	public memberInfo(String windowTitle) {
		super(windowTitle);
		setLayout(new GridLayout(6, 1, 10, 10));
		
		Panel namePanel = new Panel();
		namePanel.add(nameLabel);
		namePanel.add(name);
		
		Panel idPanel = new Panel();
		idPanel.add(idLabel);
		idPanel.add(id);
		idPanel.add(idCheck);
		
		// 정보를 수정하는 경우 ID 중복검사 버튼 비활성화
		if(windowTitle.equals("정보수정")) {
			idCheck.setEnabled(false);
		}
		
		Panel pwdPanel = new Panel();
		pwdPanel.add(pwdLabel);
		pwdPanel.add(pwd);
		
		Panel ppwdPanel = new Panel();
		ppwdPanel.add(ppwdLabel);
		ppwdPanel.add(ppwd);
		
		Panel emailPanel = new Panel();
		emailPanel.add(emailLabel);
		emailPanel.add(email);
		
		Panel btnPanel = new Panel();
		btnPanel.add(OK);
		btnPanel.add(cancel);
		
		add(namePanel);
		add(idPanel);
		add(pwdPanel);
		add(ppwdPanel);
		add(emailPanel);
		add(btnPanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(450, 280);
		setVisible(true);
	}
}
