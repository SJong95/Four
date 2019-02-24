package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.Board;

public class FourCategoryService {

	public ArrayList<Board> getCategory(String category) {
		ArrayList<Board> article = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		article = boardDAO.selectCategory(category);
		close(con);
		return article;
		
	}

}
