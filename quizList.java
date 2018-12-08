import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class quizList extends JFrame implements ActionListener {
	Label nicknameLabel = new Label("");
	JButton logoutBtn = new JButton("로그아웃");
	String userId = "";
	
	public quizList(String userId) {
		super("퀴즈 목록");
		this.userId = userId;
		getInfoFromDB getInfo = new getInfoFromDB();
		String[][] quizData = getInfo.getInfoFromDB("quiz");
		setLayout(new GridLayout(quizData.length+2, 1, 10, 10));
		
		// 사용자의 이름 표시하기
		Panel memberInfo = new Panel();
		String[][] memberData = getInfo.getInfoFromDB("members");
		int nameIndex = getInfo.lookupData(memberData, 0, userId);
		nicknameLabel.setText(memberData[nameIndex][3]);
		memberInfo.add(nicknameLabel);
		logoutBtn.addActionListener(this);
		memberInfo.add(logoutBtn);
		add(memberInfo);
		
		// 머리글 표시하기
		Panel firstRow = new Panel();
		Label name_ = new Label("작성자");
		Label title_ = new Label("제목");
		Label type_ = new Label("유형");
		Label modify_ = new Label("수정");
		Label delete_ = new Label("삭제");
		firstRow.setLayout(new GridLayout(1, 5));
		firstRow.add(name_);
		firstRow.add(title_);
		firstRow.add(type_);
		firstRow.add(modify_);
		firstRow.add(delete_);
		add(firstRow);
		
		// 메인 박스(퀴즈 목록) 설정하기
		Panel[] quizPanel = new Panel[quizData.length];
		for(int i = 0; i < quizData.length; i++) {
			try {
				String creatorId = quizData[i][9]; // 사용자의 ID
				int userIndex = getInfo.lookupData(memberData, 0, creatorId);
				
				// 리스트의 구성 요소 정의하기
				Panel panel = new Panel();
				Label name = new Label(memberData[userIndex][3]); // 사용자의 이름
				JButton title = new JButton(quizData[i][0]); // 퀴즈 제목
				Label type = null;
				if(quizData[i][3].equals("0")) { // 객관식인 경우
					type = new Label("객관식");
				} else { // 단답형인 경우
					type = new Label("단답형");
				}
				JButton editBtn = new JButton("수정");
				JButton delBtn = new JButton("삭제");
				
				quiz q = new quiz(quizData[i][0]);
				
				// 리스너 추가하기
				// 1. 퀴즈 풀기
				title.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 퀴즈를 만든 사람은 풀 수 없음
						if(creatorId.equals(userId)) {
							JOptionPane.showMessageDialog(null, 
									"퀴즈 작성자는 풀 수 없습니다.",
									"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
						} else {
							q.solveQuiz(userId);
							setVisible(false);
						}
					}
				});
				
				// 2. 작성자가 아니거나 푼 사람이 있으면 퀴즈 수정, 삭제 버튼 클릭 시 오류 메시지 띄우기
				// 작성자가 아니면 오류 메시지 띄우기
				if(!userId.equals(creatorId)) {
					btnLock(editBtn, delBtn,
							"작성자만 수정할 수 있습니다.", "작성자만 삭제할 수 있습니다.");
				}
				
				// 작성자이지만 퀴즈를 푼 사람이 있으면 오류 메시지 띄우기
				else if(quizData[i][10].equals("1")) {
					btnLock(editBtn, delBtn, "푼 사람이 있는 퀴즈는 수정할 수 없습니다.",
							"푼 사람이 있는 퀴즈는 삭제할 수 없습니다.");
				}
				
				// 푼 사람이 없으면 퀴즈 수정, 삭제 가능하게 하기
				else {
					final int ii = i;
					
					// 퀴즈 수정
					editBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							q.modifyQuiz(quizList.this, userId);
						}
					});
					// 퀴즈 삭제
					delBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							boolean del = q.deleteQuiz();
							
							// 퀴즈 목록에서 퀴즈 삭제하기
							if(del) {
								remove(quizPanel[ii]);
							}
						}
					});
				}
				
				// 요소 추가하기
				panel.setLayout(new GridLayout(1, 5, 10, 10));
				name.setBackground(Color.WHITE);
				panel.add(name);
				panel.add(title);
				type.setBackground(Color.WHITE);
				panel.add(type);
				panel.add(editBtn);
				panel.add(delBtn);
				add(panel);
				quizPanel[i] = panel;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, 
						"데이터베이스에 퀴즈가 없습니다.",
						"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		setSize(600, (quizData.length+2)*50);
		setVisible(true);
	}
	
	// 수정, 삭제 버튼을 누르면 오류 메시지 띄우기
	public void btnLock(JButton editBtn, JButton delBtn,
			String editMsg, String delMsg) {
		// 퀴즈 수정
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
						editMsg, "퀴즈시스템", JOptionPane.WARNING_MESSAGE);
			}
		});
		// 퀴즈 삭제
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
						delMsg, "퀴즈시스템", JOptionPane.WARNING_MESSAGE);
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		// 로그아웃 버튼을 클릭한 경우
		if(obj == logoutBtn) {
			member mem = new member(this.userId, "");
			mem.logout();
			setVisible(false);
		}
	}
}
