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
		//boardDAO에 reboardRecomment메소드에 reboard를 매개변수로 넘겨주고 리턴값을 recommentCount에 대입
		int recommentCount = boardDAO.reboardRecomment(reboard);
		boolean replyResult=false;
		//recommentCount가 0보다 크면 replyResult를 true로 바꾸고 commit 아니면 rollback
		if(recommentCount>0) {
			replyResult=true;
			commit(con);
		}else {
			rollback(con);
		}
		return replyResult; //변수 replyResult값을 넘겨준다
	}
}
