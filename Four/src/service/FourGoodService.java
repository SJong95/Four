package service;

import dao.BoardDAO;
import static db.JdbcUtil.*;

import java.sql.Connection;
public class FourGoodService {

	public boolean boardGood(String board_num) {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		int restulLike = boardDAO.boardGood(board_num); // 추천 수 증가 메소드 호출
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
