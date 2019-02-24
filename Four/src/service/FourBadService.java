package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;

public class FourBadService {

	public boolean boardBad(String board_num) {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		int restulLike = boardDAO.boardBad(board_num); // 추천 수 감소 메소드 호출
		boolean result = false;
		if(restulLike != 0) { // 결과 값에 따라 커밋 밑 롤백
			commit(con);
			result = true;
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

}
