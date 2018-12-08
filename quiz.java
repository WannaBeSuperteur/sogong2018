import javax.swing.JOptionPane;

/*
 * 목적: 퀴즈 객체에 대한 속성을 기술
 * 최초 작성일: 2018/12/01
 * 최종 수정일: 2018/12/08
 */
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
