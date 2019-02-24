package service;

import java.sql.Connection;
import dao.*;
import static db.JdbcUtil.*;

public class FourCommentDeleteService {

	public boolean isReboardPass(int reboardNum, String reboardPass) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		boolean isReboardPass=boardDAO.isReboardPass(reboardNum,reboardPass);
		close(con);
		return isReboardPass;
	}
	
	public boolean reboardDelete(int reboardNum) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int deleteCount = boardDAO.reboardDelete(reboardNum);
		boolean deleteResult=false;
		if(deleteCount>0) {
			commit(con);
			deleteResult=true;
		}else {
			rollback(con);
		}
		return deleteResult;
	}
	
}
