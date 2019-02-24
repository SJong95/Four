package service;

import java.sql.Connection;

import dao.*;

import static db.JdbcUtil.*;
import vo.ReBoard;

public class FourCommentService {

	public boolean ReboardWrite(ReBoard reboard) {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		
		boolean writeResult = false;
		//boardDAO에 commentWrite라는 메소드에 매개변수값으로 reboard값을 넘긴다(Reboard.jsp의 값들)
		int result = boardDAO.commentWrite(reboard);
		
		//commentWrite 리턴값이0보다 크면 writeResult를 true로 바꾸고 commit 아니면 rollback
		if(result>0) {
			writeResult=true;
			commit(con);
		}else {
			rollback(con);
		}
		return writeResult;
	}

}
