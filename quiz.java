import javax.swing.JOptionPane;

public class quiz {
	private String title;
	
	public quiz(String title) {
		this.title = title;
	}
	
	public void makeQuiz() {
		makeQuiz a = new makeQuiz("");
	}
	
	public void solveQuiz(String userId) {
		solveQuiz a = new solveQuiz(this.title, userId);
	}
	
	public void modifyQuiz(quizList listWindow, String userId) {
		int result = JOptionPane.showConfirmDialog(
				null, "���� �����Ͻðڽ��ϱ�?",
				"����ý���", JOptionPane.WARNING_MESSAGE);
		if(result == JOptionPane.YES_OPTION) {
			modifyQuiz a = new modifyQuiz(this.title, userId);
			listWindow.setVisible(false);
		}
	}
	
	public boolean deleteQuiz() {
		int result = JOptionPane.showConfirmDialog(
				null, "���� �����Ͻðڽ��ϱ�?",
				"����ý���", JOptionPane.WARNING_MESSAGE);
		if(result == JOptionPane.YES_OPTION) {
			deleteQuiz a = new deleteQuiz(this.title);
			return true;
		}
		return false;
	}
}
