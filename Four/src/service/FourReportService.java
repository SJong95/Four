package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;

public class FourReportService {
	
	public boolean boardReport(String board_num) {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		int restulLike = boardDAO.boardReport(board_num); 
		boolean result = false;
		if(restulLike != 0) {
			commit(con);
			result = true;
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

}
