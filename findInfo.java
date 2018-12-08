import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * ����: ȸ���� ID, ��й�ȣ ã��
 * ���� �ۼ���: 2018/12/01
 * ���� ������: 2018/12/03
 */
public class findInfo extends JFrame implements ActionListener {
	JButton OK = new JButton("Ȯ��");
	JButton cancel = new JButton("���");
	Label nameLabel = new Label("�̸�");
	Label emailLabel = new Label("�̸����ּ�");
	TextField name = new TextField(12);
	TextField email = new TextField(12);
	
	public findInfo() {
		super("ID/PW ã��");
		setLayout(new GridLayout(3, 1));
		
		OK.addActionListener(this);
		cancel.addActionListener(this);
		
		Panel namePanel = new Panel();
		namePanel.add(nameLabel);
		namePanel.add(name);
		
		Panel emailPanel = new Panel();
		emailPanel.add(emailLabel);
		emailPanel.add(email);

		Panel btnPanel = new Panel();
		btnPanel.add(OK);
		btnPanel.add(cancel);
		
		add(namePanel);
		add(emailPanel);
		add(btnPanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 150);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		// Ȯ�� ��ư�� Ŭ���� ���
		if(obj == OK) {
			try {
				getInfoFromDB getInfo = new getInfoFromDB();
				String[][] memberData = getInfo.getInfoFromDB("members");
				
				for(int i = 0; i < memberData.length; i++) {
					if(memberData[i][2].equals(email.getText()) && memberData[i][3].equals(name.getText())) {
						JOptionPane.showMessageDialog(
								null, "ID: " + memberData[i][0] + ", PW: " + memberData[i][1],
								"����ý���", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				JOptionPane.showMessageDialog(
						null, "�̸� Ȥ�� �̸����ּҸ� Ȯ���ϼ���.",
						"����ý���", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(
						null, "�̸� Ȥ�� �̸����ּҸ� Ȯ���ϼ���.",
						"����ý���", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		// ��� ��ư�� Ŭ���� ���
		else if(obj == cancel) {
			home a = new home();
			hide();
		}
	}
}
