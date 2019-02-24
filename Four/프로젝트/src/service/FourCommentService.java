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
		int result = boardDAO.commentWrite(reboard);
		if(result>0) {
			writeResult=true;
			commit(con);
		}else {
			rollback(con);
		}
		return writeResult;
	}

}
