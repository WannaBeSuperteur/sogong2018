import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// �α��� �� ȭ��
public class afterLogin extends JFrame implements ActionListener {
	JButton makeQuiz = new JButton("���� �����");
	JButton solveQuiz = new JButton("���� Ǯ��");
	JButton modifyInfo = new JButton("��������");
	JButton leaveMember = new JButton("ȸ��Ż��");
	JButton logout = new JButton("�α׾ƿ�");
	String id = "";
	member mem;
	
	public afterLogin(String id) {
		super("�α��� ��");
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
		
		// ���� ����� ��ư�� Ŭ���� ���
		if(obj == makeQuiz) {
			makeQuiz a = new makeQuiz(id);
			setVisible(false);
		}
		
		// ���� Ǯ�� ��ư�� Ŭ���� ���
		else if(obj == solveQuiz) {
			quizList a = new quizList(id);
			setVisible(false);
		}
		
		// ȸ������ ���� ��ư�� Ŭ���� ���
		else if(obj == modifyInfo) {
			mem.modifyMember();
			setVisible(false);
		}
		
		// ȸ��Ż�� ��ư�� Ŭ���� ���
		else if(obj == leaveMember) {
			mem.leaveMember();
			setVisible(false);
		}
		
		// �α׾ƿ� ��ư�� Ŭ���� ���
		else if(obj == logout) {
			mem.logout();
			setVisible(false);
		}
	}
}
