import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class solveQuiz extends JFrame implements ActionListener {
	JButton OK = new JButton("확인");
	JButton cancel = new JButton("취소");
	
	Label titleLabel = new Label("제목");
	Label contentLabel = new Label("내용");
	Label choiceLabel = new Label("선택지");
	Label answerLabel = new Label("답");
	
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
		super("퀴즈 풀기");
		checkQuiz(title); // 해당 퀴즈를 수정, 삭제할 수 없게 하기 
		
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
		
		// 레이아웃에 추가하기
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
		
		// 액션 리스너 만들기
		OK.addActionListener(this);
		cancel.addActionListener(this);
		choice1.addActionListener(this);
		choice2.addActionListener(this);
		choice3.addActionListener(this);
		choice4.addActionListener(this);
		choice5.addActionListener(this);
		
		// 제목과 내용이 일치하는 퀴즈를 찾아서 정보를 표시
		for(int i = 0; i < quizData.length; i++) {
			if(quizData[i][0].equals(title)) {
				String type = quizData[i][3];
				rightAnswer = quizData[i][2];
				
				// 객관식이면 답란 비활성화하기
				if(quizData[i][3].equals("0")) {
					answer_.setEnabled(false);
				}

				// 단답형이면 선택지 비활성화하기
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
	
	// 해당 퀴즈를 수정/삭제할 수 없게 하기
	public void checkQuiz(String title) {
		getInfoFromDB getInfo = new getInfoFromDB();
		String[][] quizData = getInfo.getInfoFromDB("quiz");
		int quizIndex = getInfo.lookupData(quizData, 0, title);
		
		// quizIndex 인덱스에 해당하는 줄에서 탭으로 구분된 내용들 중 가장 오른쪽의 내용을 1로
		String toAdd = "";
		for(int i = 0; i < quizData[quizIndex].length-1; i++) {
			toAdd += (quizData[quizIndex][i] + "\t");
		}
		toAdd += "1\n";
		
		// 파일 읽기
		try {
			FileReader readDB = new FileReader("D:\\quiz.txt");
			BufferedReader readDB_ = new BufferedReader(readDB);
			String toWrite = "";
			String str = null;
	
			int count = 0;
			while((str = readDB_.readLine()) != null) {
				if(count == quizIndex) { // 찾는 ID가 있는 인덱스이면
					toWrite += toAdd;
				} else { // 그렇지 않으면
					toWrite += (str + "\n");
				}
				count++;
			}
			readDB.close();
			
			// 파일에 쓰기
			FileWriter writeDB = new FileWriter("D:\\quiz.txt", false);
			writeDB.write(toWrite);
			writeDB.close();
		} catch (Exception e) { }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		quizInfoApply qIA = new quizInfoApply();

		// 확인 버튼을 클릭한 경우
		if(obj == OK) {
			// 답을 선택하지 않았으면 오류 메시지 표시하기
			if((type_ == 0 && choice == -1) ||
				(type_ == 1 && answer_.getText().equals(""))) {
				JOptionPane.showMessageDialog(
						null, "답을 선택/입력해 주세요.",
						"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
			}
			else { // 답을 선택했으면 정답인지 판정하기
				if(type_ == 0) { // 객관식 문제의 경우
					if(String.valueOf(choice).equals(rightAnswer)) {
						JOptionPane.showMessageDialog(
								null, "정답입니다.",
								"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(
								null, "오답입니다. 정답은 " + rightAnswer + "번입니다.",
								"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
					}
				} else { // 단답형 문제의 경우
					if(answer_.getText().equals(rightAnswer)) {
						JOptionPane.showMessageDialog(
								null, "정답입니다.",
								"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(
								null, "오답입니다. 정답은 " + rightAnswer + "입니다.",
								"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
					}
				}
				
				// 퀴즈 목록으로 넘어가기
				quizList a = new quizList(userId);
				setVisible(false);
			}
		}
		
		// 취소 버튼을 클릭한 경우
		else if(obj == cancel) {
			quizList a = new quizList(userId);
			setVisible(false);
		}
		
		// 각 선택지를 선택한 경우
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
