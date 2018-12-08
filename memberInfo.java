import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class memberInfo extends JFrame {
	JButton idCheck = new JButton("���̵� �ߺ�Ȯ��");
	JButton OK = new JButton("Ȯ��");
	JButton cancel = new JButton("���");
	
	Label nameLabel = new Label("�̸�");
	Label idLabel = new Label("���̵�");
	Label pwdLabel = new Label("��й�ȣ");
	Label ppwdLabel = new Label("��й�ȣ Ȯ��");
	Label emailLabel = new Label("�̸����ּ�");
	
	TextField name = new TextField(20);
	TextField id = new TextField(10);
	TextField pwd = new TextField(10);
	TextField ppwd = new TextField(10);
	TextField email = new TextField(20);
	
	boolean idChecked = false; // ID �ߺ�Ȯ�� ����
	boolean duplicatedID = false; // ID �ߺ� ����
	
	public memberInfo(String windowTitle) {
		super(windowTitle);
		setLayout(new GridLayout(6, 1, 10, 10));
		
		Panel namePanel = new Panel();
		namePanel.add(nameLabel);
		namePanel.add(name);
		
		Panel idPanel = new Panel();
		idPanel.add(idLabel);
		idPanel.add(id);
		idPanel.add(idCheck);
		
		// ������ �����ϴ� ��� ID �ߺ��˻� ��ư ��Ȱ��ȭ
		if(windowTitle.equals("��������")) {
			idCheck.setEnabled(false);
		}
		
		Panel pwdPanel = new Panel();
		pwdPanel.add(pwdLabel);
		pwdPanel.add(pwd);
		
		Panel ppwdPanel = new Panel();
		ppwdPanel.add(ppwdLabel);
		ppwdPanel.add(ppwd);
		
		Panel emailPanel = new Panel();
		emailPanel.add(emailLabel);
		emailPanel.add(email);
		
		Panel btnPanel = new Panel();
		btnPanel.add(OK);
		btnPanel.add(cancel);
		
		add(namePanel);
		add(idPanel);
		add(pwdPanel);
		add(ppwdPanel);
		add(emailPanel);
		add(btnPanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(450, 280);
		setVisible(true);
	}
}
