package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.Board;

public class FourCategoryService {

	public ArrayList<Board> getCategory(String category,int page,int limit) {
		ArrayList<Board> article = null; // Board의 article 생성
		Connection con = getConnection(); 
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		article = boardDAO.selectCategory(category,page,limit); // article에 boardDAO.selectCategory 메소드 실행값 담기
		close(con);
		return article; 
		
	}
	public int categoryCount(String category) {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		int result = boardDAO.categoryCount(category); //result 변수에 boardDAO.categoryCount 메소드 실행값 담기
		close(con);
		return result;
	}

}
