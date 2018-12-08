import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/*
 * 목적: 퀴즈를 생성하거나 수정하는 창을 표시
 * 최초 작성일: 2018/12/01
 * 최종 수정일: 2018/12/03
 */
public class quizInfo extends JFrame {
	JButton logoutBtn = new JButton("로그아웃");
	JButton OK = new JButton("확인");
	JButton cancel = new JButton("취소");
	
	Label titleLabel = new Label("제목");
	Label contentLabel = new Label("내용");
	Label typeLabel = new Label("유형");
	Label answerLabel = new Label("정답");
	Label choiceLabel = new Label("선택지");
	
	TextField title = new TextField(15);
	TextField content = new TextField(15);
	String[] items = new String[] {"객관식", "단답형"};
	JComboBox<String> type = new JComboBox<>(items);
	TextField answer = new TextField(15);
	
	TextField choice1 = new TextField(3);
	TextField choice2 = new TextField(3);
	TextField choice3 = new TextField(3);
	TextField choice4 = new TextField(3);
	TextField choice5 = new TextField(3);
	
	public quizInfo(String windowTitle) {
		super(windowTitle);
		setLayout(new GridLayout(7, 1));
		
		Panel titlePanel = new Panel();
		titlePanel.add(titleLabel);
		titlePanel.add(title);
		
		Panel contentPanel = new Panel();
		contentPanel.add(contentLabel);
		contentPanel.add(content);
		
		Panel typePanel = new Panel();
		typePanel.add(typeLabel);
		typePanel.add(type);
		
		Panel answerPanel = new Panel();
		answerPanel.add(answerLabel);
		answerPanel.add(answer);
		
		Panel choicePanel = new Panel();
		choicePanel.add(choiceLabel);
		choicePanel.add(choice1);
		choicePanel.add(choice2);
		choicePanel.add(choice3);
		choicePanel.add(choice4);
		choicePanel.add(choice5);
		
		Panel btnPanel = new Panel();
		btnPanel.add(OK);
		btnPanel.add(cancel);
		
		add(logoutBtn);
		add(titlePanel);
		add(contentPanel);
		add(typePanel);
		add(answerPanel);
		add(choicePanel);
		add(btnPanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(450, 300);
		setVisible(true);
	}
}
