package service;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.BoardDAO;
import vo.ReBoard;

public class FourReCommentService {

	public boolean reboardRecomment(ReBoard reboard) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int recommentCount = boardDAO.reboardRecomment(reboard);
		boolean replyResult=false;
		if(recommentCount>0) {
			replyResult=true;
			commit(con);
		}else {
			rollback(con);
		}
		return replyResult;
	}
}
