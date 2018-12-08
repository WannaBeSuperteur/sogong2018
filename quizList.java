import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class quizList extends JFrame implements ActionListener {
	Label nicknameLabel = new Label("");
	JButton logoutBtn = new JButton("�α׾ƿ�");
	String userId = "";
	
	public quizList(String userId) {
		super("���� ���");
		this.userId = userId;
		getInfoFromDB getInfo = new getInfoFromDB();
		String[][] quizData = getInfo.getInfoFromDB("quiz");
		setLayout(new GridLayout(quizData.length+2, 1, 10, 10));
		
		// ������� �̸� ǥ���ϱ�
		Panel memberInfo = new Panel();
		String[][] memberData = getInfo.getInfoFromDB("members");
		int nameIndex = getInfo.lookupData(memberData, 0, userId);
		nicknameLabel.setText(memberData[nameIndex][3]);
		memberInfo.add(nicknameLabel);
		logoutBtn.addActionListener(this);
		memberInfo.add(logoutBtn);
		add(memberInfo);
		
		// �Ӹ��� ǥ���ϱ�
		Panel firstRow = new Panel();
		Label name_ = new Label("�ۼ���");
		Label title_ = new Label("����");
		Label type_ = new Label("����");
		Label modify_ = new Label("����");
		Label delete_ = new Label("����");
		firstRow.setLayout(new GridLayout(1, 5));
		firstRow.add(name_);
		firstRow.add(title_);
		firstRow.add(type_);
		firstRow.add(modify_);
		firstRow.add(delete_);
		add(firstRow);
		
		// ���� �ڽ�(���� ���) �����ϱ�
		Panel[] quizPanel = new Panel[quizData.length];
		for(int i = 0; i < quizData.length; i++) {
			try {
				String creatorId = quizData[i][9]; // ������� ID
				int userIndex = getInfo.lookupData(memberData, 0, creatorId);
				
				// ����Ʈ�� ���� ��� �����ϱ�
				Panel panel = new Panel();
				Label name = new Label(memberData[userIndex][3]); // ������� �̸�
				JButton title = new JButton(quizData[i][0]); // ���� ����
				Label type = null;
				if(quizData[i][3].equals("0")) { // �������� ���
					type = new Label("������");
				} else { // �ܴ����� ���
					type = new Label("�ܴ���");
				}
				JButton editBtn = new JButton("����");
				JButton delBtn = new JButton("����");
				
				quiz q = new quiz(quizData[i][0]);
				
				// ������ �߰��ϱ�
				// 1. ���� Ǯ��
				title.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// ��� ���� ����� Ǯ �� ����
						if(creatorId.equals(userId)) {
							JOptionPane.showMessageDialog(null, 
									"���� �ۼ��ڴ� Ǯ �� �����ϴ�.",
									"����ý���", JOptionPane.WARNING_MESSAGE);
						} else {
							q.solveQuiz(userId);
							setVisible(false);
						}
					}
				});
				
				// 2. �ۼ��ڰ� �ƴϰų� Ǭ ����� ������ ���� ����, ���� ��ư Ŭ�� �� ���� �޽��� ����
				// �ۼ��ڰ� �ƴϸ� ���� �޽��� ����
				if(!userId.equals(creatorId)) {
					btnLock(editBtn, delBtn,
							"�ۼ��ڸ� ������ �� �ֽ��ϴ�.", "�ۼ��ڸ� ������ �� �ֽ��ϴ�.");
				}
				
				// �ۼ��������� ��� Ǭ ����� ������ ���� �޽��� ����
				else if(quizData[i][10].equals("1")) {
					btnLock(editBtn, delBtn, "Ǭ ����� �ִ� ����� ������ �� �����ϴ�.",
							"Ǭ ����� �ִ� ����� ������ �� �����ϴ�.");
				}
				
				// Ǭ ����� ������ ���� ����, ���� �����ϰ� �ϱ�
				else {
					final int ii = i;
					
					// ���� ����
					editBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							q.modifyQuiz(quizList.this, userId);
						}
					});
					// ���� ����
					delBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							boolean del = q.deleteQuiz();
							
							// ���� ��Ͽ��� ���� �����ϱ�
							if(del) {
								remove(quizPanel[ii]);
							}
						}
					});
				}
				
				// ��� �߰��ϱ�
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
						"�����ͺ��̽��� ��� �����ϴ�.",
						"����ý���", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		setSize(600, (quizData.length+2)*50);
		setVisible(true);
	}
	
	// ����, ���� ��ư�� ������ ���� �޽��� ����
	public void btnLock(JButton editBtn, JButton delBtn,
			String editMsg, String delMsg) {
		// ���� ����
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
						editMsg, "����ý���", JOptionPane.WARNING_MESSAGE);
			}
		});
		// ���� ����
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
						delMsg, "����ý���", JOptionPane.WARNING_MESSAGE);
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		// �α׾ƿ� ��ư�� Ŭ���� ���
		if(obj == logoutBtn) {
			member mem = new member(this.userId, "");
			mem.logout();
			setVisible(false);
		}
	}
}
