package service;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import dao.BoardDAO;
import vo.*;

public class FourDetailService {

	public Board getArticle(int board_num) {
		Board article = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		article = boardDAO.selectArticle(board_num);
		close(con);
		return article;
		
	}
	public ArrayList<ReBoard> reboardCommentList(int board_num) {
		Connection con = getConnection();
		BoardDAO reboardDAO = BoardDAO.getInstance();
		reboardDAO.setConnection(con);
		
		ArrayList<ReBoard> commentList = reboardDAO.reboardList(board_num);
		close(con);
		return commentList;
	}

}
