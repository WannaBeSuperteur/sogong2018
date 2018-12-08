import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/*
 * ����: ��� �����ϰų� �����ϴ� â�� ǥ��
 * ���� �ۼ���: 2018/12/01
 * ���� ������: 2018/12/03
 */
public class quizInfo extends JFrame {
	JButton logoutBtn = new JButton("�α׾ƿ�");
	JButton OK = new JButton("Ȯ��");
	JButton cancel = new JButton("���");
	
	Label titleLabel = new Label("����");
	Label contentLabel = new Label("����");
	Label typeLabel = new Label("����");
	Label answerLabel = new Label("����");
	Label choiceLabel = new Label("������");
	
	TextField title = new TextField(15);
	TextField content = new TextField(15);
	String[] items = new String[] {"������", "�ܴ���"};
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
