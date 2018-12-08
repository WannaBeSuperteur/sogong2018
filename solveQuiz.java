import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class solveQuiz extends JFrame implements ActionListener {
	JButton OK = new JButton("Ȯ��");
	JButton cancel = new JButton("���");
	
	Label titleLabel = new Label("����");
	Label contentLabel = new Label("����");
	Label choiceLabel = new Label("������");
	Label answerLabel = new Label("��");
	
	TextField title_ = new TextField(15);
	TextField content_ = new TextField(15);
	TextField answer_ = new TextField(15);
	
	JButton choice1 = new JButton("");
	JButton choice2 = new JButton("");
	JButton choice3 = new JButton("");
	JButton choice4 = new JButton("");
	JButton choice5 = new JButton("");
	
	int choice = -1;
	int type_ = 0;
	String rightAnswer = "";
	String userId = "";
	
	public solveQuiz(String title, String userId) {
		super("���� Ǯ��");
		checkQuiz(title); // �ش� ��� ����, ������ �� ���� �ϱ� 
		
		getInfoFromDB getInfo = new getInfoFromDB();
		String[][] quizData = getInfo.getInfoFromDB("quiz");
		int quizIndex = getInfo.lookupData(quizData, 0, title);
		this.userId = userId;
		title_.setText(quizData[quizIndex][0]);
		content_.setText(quizData[quizIndex][1]);
		title_.setEnabled(false);
		content_.setEnabled(false);
		choice1.setText(quizData[quizIndex][4]);
		choice2.setText(quizData[quizIndex][5]);
		choice3.setText(quizData[quizIndex][6]);
		choice4.setText(quizData[quizIndex][7]);
		choice5.setText(quizData[quizIndex][8]);
		choice1.setBackground(Color.LIGHT_GRAY);
		choice2.setBackground(Color.LIGHT_GRAY);
		choice3.setBackground(Color.LIGHT_GRAY);
		choice4.setBackground(Color.LIGHT_GRAY);
		choice5.setBackground(Color.LIGHT_GRAY);
		
		// ���̾ƿ��� �߰��ϱ�
		setLayout(new GridLayout(5, 1, 10, 10));
		
		Panel titlePanel = new Panel();
		titlePanel.add(titleLabel);
		titlePanel.add(title_);
		
		Panel contentPanel = new Panel();
		contentPanel.add(contentLabel);
		contentPanel.add(content_);
		
		Panel choicePanel = new Panel();
		choicePanel.add(choiceLabel);
		choicePanel.add(choice1);
		choicePanel.add(choice2);
		choicePanel.add(choice3);
		choicePanel.add(choice4);
		choicePanel.add(choice5);
		
		Panel answerPanel = new Panel();
		answerPanel.add(answerLabel);
		answerPanel.add(answer_);
		
		Panel btnPanel = new Panel();
		btnPanel.add(OK);
		btnPanel.add(cancel);
		
		add(titlePanel);
		add(contentPanel);
		add(choicePanel);
		add(answerPanel);
		add(btnPanel);
		
		// �׼� ������ �����
		OK.addActionListener(this);
		cancel.addActionListener(this);
		choice1.addActionListener(this);
		choice2.addActionListener(this);
		choice3.addActionListener(this);
		choice4.addActionListener(this);
		choice5.addActionListener(this);
		
		// ����� ������ ��ġ�ϴ� ��� ã�Ƽ� ������ ǥ��
		for(int i = 0; i < quizData.length; i++) {
			if(quizData[i][0].equals(title)) {
				String type = quizData[i][3];
				rightAnswer = quizData[i][2];
				
				// �������̸� ��� ��Ȱ��ȭ�ϱ�
				if(quizData[i][3].equals("0")) {
					answer_.setEnabled(false);
				}

				// �ܴ����̸� ������ ��Ȱ��ȭ�ϱ�
				else if(quizData[i][3].equals("1")) {
					type_ = 1;
					choice1.setEnabled(false);
					choice2.setEnabled(false);
					choice3.setEnabled(false);
					choice4.setEnabled(false);
					choice5.setEnabled(false);
				}
			}
		}
		
		setSize(600, 240);
		setVisible(true);
	}
	
	// �ش� ��� ����/������ �� ���� �ϱ�
	public void checkQuiz(String title) {
		getInfoFromDB getInfo = new getInfoFromDB();
		String[][] quizData = getInfo.getInfoFromDB("quiz");
		int quizIndex = getInfo.lookupData(quizData, 0, title);
		
		// quizIndex �ε����� �ش��ϴ� �ٿ��� ������ ���е� ����� �� ���� �������� ������ 1��
		String toAdd = "";
		for(int i = 0; i < quizData[quizIndex].length-1; i++) {
			toAdd += (quizData[quizIndex][i] + "\t");
		}
		toAdd += "1\n";
		
		// ���� �б�
		try {
			FileReader readDB = new FileReader("D:\\quiz.txt");
			BufferedReader readDB_ = new BufferedReader(readDB);
			String toWrite = "";
			String str = null;
	
			int count = 0;
			while((str = readDB_.readLine()) != null) {
				if(count == quizIndex) { // ã�� ID�� �ִ� �ε����̸�
					toWrite += toAdd;
				} else { // �׷��� ������
					toWrite += (str + "\n");
				}
				count++;
			}
			readDB.close();
			
			// ���Ͽ� ����
			FileWriter writeDB = new FileWriter("D:\\quiz.txt", false);
			writeDB.write(toWrite);
			writeDB.close();
		} catch (Exception e) { }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		quizInfoApply qIA = new quizInfoApply();

		// Ȯ�� ��ư�� Ŭ���� ���
		if(obj == OK) {
			// ���� �������� �ʾ����� ���� �޽��� ǥ���ϱ�
			if((type_ == 0 && choice == -1) ||
				(type_ == 1 && answer_.getText().equals(""))) {
				JOptionPane.showMessageDialog(
						null, "���� ����/�Է��� �ּ���.",
						"����ý���", JOptionPane.WARNING_MESSAGE);
			}
			else { // ���� ���������� �������� �����ϱ�
				if(type_ == 0) { // ������ ������ ���
					if(String.valueOf(choice).equals(rightAnswer)) {
						JOptionPane.showMessageDialog(
								null, "�����Դϴ�.",
								"����ý���", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(
								null, "�����Դϴ�. ������ " + rightAnswer + "���Դϴ�.",
								"����ý���", JOptionPane.WARNING_MESSAGE);
					}
				} else { // �ܴ��� ������ ���
					if(answer_.getText().equals(rightAnswer)) {
						JOptionPane.showMessageDialog(
								null, "�����Դϴ�.",
								"����ý���", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(
								null, "�����Դϴ�. ������ " + rightAnswer + "�Դϴ�.",
								"����ý���", JOptionPane.WARNING_MESSAGE);
					}
				}
				
				// ���� ������� �Ѿ��
				quizList a = new quizList(userId);
				setVisible(false);
			}
		}
		
		// ��� ��ư�� Ŭ���� ���
		else if(obj == cancel) {
			quizList a = new quizList(userId);
			setVisible(false);
		}
		
		// �� �������� ������ ���
		else {
			choice1.setBackground(Color.LIGHT_GRAY);
			choice2.setBackground(Color.LIGHT_GRAY);
			choice3.setBackground(Color.LIGHT_GRAY);
			choice4.setBackground(Color.LIGHT_GRAY);
			choice5.setBackground(Color.LIGHT_GRAY);
			if(obj == choice1) {
				choice = 1;
				choice1.setBackground(Color.YELLOW);
			} else if(obj == choice2) {
				choice = 2;
				choice2.setBackground(Color.YELLOW);
			} else if(obj == choice3) {
				choice = 3;
				choice3.setBackground(Color.YELLOW);
			} else if(obj == choice4) {
				choice = 4;
				choice4.setBackground(Color.YELLOW);
			} else if(obj == choice5) {
				choice = 5;
				choice5.setBackground(Color.YELLOW);
			}
		}
	}
}
