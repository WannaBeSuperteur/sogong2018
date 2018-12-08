import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import javax.swing.*;

public class home extends JFrame implements ActionListener {
	JButton loginBtn = new JButton("로그인");
	JButton findInfoBtn = new JButton("ID/PW 찾기");
	JButton joinBtn = new JButton("회원가입");
	Label idLabel = new Label("ID");
	Label pwdLabel = new Label("PW");
	TextField id = new TextField(10);
	TextField pwd = new TextField(10);
	
	// 파일 만들기
	public void fileCreate(String DBName) {
		try {
			FileReader readDB = new FileReader("D:\\" + DBName + ".txt");
			BufferedReader readDB_ = new BufferedReader(readDB);
		} catch (Exception e) {
			File f = new File("D:\\" + DBName + ".txt");
			try {
				f.createNewFile();
			} catch (Exception e1) { }
		}
	}
	
	public home() {
		super("HOME");
		setLayout(new GridLayout(3, 1));
		
		fileCreate("members");
		fileCreate("quiz");
		
		loginBtn.addActionListener(this);
		findInfoBtn.addActionListener(this);
		joinBtn.addActionListener(this);
		
		Panel idPanel = new Panel();
		idPanel.add(idLabel);
		idPanel.add(id);
		
		Panel pwPanel = new Panel();
		pwPanel.add(pwdLabel);
		pwPanel.add(pwd);
		
		Panel btnPanel = new Panel();
		btnPanel.add(loginBtn);
		btnPanel.add(findInfoBtn);
		btnPanel.add(joinBtn);
		
		add(idPanel);
		add(pwPanel);
		add(btnPanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 150);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		// 로그인 버튼을 클릭한 경우
		if(obj == loginBtn) {
			member member = new member(id.getText(), pwd.getText());
			boolean accepted = member.login();
			
			// 로그인 성공 시 로그인 후 화면으로 넘어가기
			if(accepted) {
				afterLogin a = new afterLogin(id.getText());
				setVisible(false);
			}
		}
		
		// 정보 찾기 버튼을 클릭한 경우
		else if(obj == findInfoBtn) {
			member member = new member("", "");
			member.findinfo();
			setVisible(false);
		}
		
		// 회원가입 버튼을 클릭한 경우
		else if(obj == joinBtn) {
			member member = new member("", "");
			member.joinMember();
			setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		home hf = new home();
	}
}
