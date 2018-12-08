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
				null, "정말 수정하시겠습니까?",
				"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
		if(result == JOptionPane.YES_OPTION) {
			modifyQuiz a = new modifyQuiz(this.title, userId);
			listWindow.setVisible(false);
		}
	}
	
	public boolean deleteQuiz() {
		int result = JOptionPane.showConfirmDialog(
				null, "정말 삭제하시겠습니까?",
				"퀴즈시스템", JOptionPane.WARNING_MESSAGE);
		if(result == JOptionPane.YES_OPTION) {
			deleteQuiz a = new deleteQuiz(this.title);
			return true;
		}
		return false;
	}
}
