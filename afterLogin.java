import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// 로그인 후 화면
public class afterLogin extends JFrame implements ActionListener {
	JButton makeQuiz = new JButton("퀴즈 만들기");
	JButton solveQuiz = new JButton("퀴즈 풀기");
	JButton modifyInfo = new JButton("정보수정");
	JButton leaveMember = new JButton("회원탈퇴");
	JButton logout = new JButton("로그아웃");
	String id = "";
	member mem;
	
	public afterLogin(String id) {
		super("로그인 후");
		mem = new member(id, "");
		setLayout(new GridLayout(3, 1, 10, 10));

		Panel panel1 = new Panel();
		panel1.add(modifyInfo);
		panel1.add(leaveMember);
		panel1.add(logout);
		add(panel1);
		add(makeQuiz);
		add(solveQuiz);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 150);
		setVisible(true);
		
		this.id = id;
		
		makeQuiz.addActionListener(this);
		solveQuiz.addActionListener(this);
		modifyInfo.addActionListener(this);
		leaveMember.addActionListener(this);
		logout.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		// 퀴즈 만들기 버튼을 클릭한 경우
		if(obj == makeQuiz) {
			makeQuiz a = new makeQuiz(id);
			setVisible(false);
		}
		
		// 퀴즈 풀기 버튼을 클릭한 경우
		else if(obj == solveQuiz) {
			quizList a = new quizList(id);
			setVisible(false);
		}
		
		// 회원정보 수정 버튼을 클릭한 경우
		else if(obj == modifyInfo) {
			mem.modifyMember();
			setVisible(false);
		}
		
		// 회원탈퇴 버튼을 클릭한 경우
		else if(obj == leaveMember) {
			mem.leaveMember();
			setVisible(false);
		}
		
		// 로그아웃 버튼을 클릭한 경우
		else if(obj == logout) {
			mem.logout();
			setVisible(false);
		}
	}
}
