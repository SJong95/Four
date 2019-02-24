package service;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.Board;
import static db.JdbcUtil.*;

public class FourListService {

	public ArrayList<Board> boardList() {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		ArrayList<Board> boardList = boardDAO.boardList();
		for(int i =0;i<boardList.size();i++) {
			System.out.println(boardList.get(i).getBOARD_LINK_TITLE());
		}
		close(con);
		return boardList;
	}
	public ArrayList<Integer> commentCount(ArrayList<Board> boardList) {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		ArrayList<Integer> countList = new ArrayList<>();
		for(int i =0;i<boardList.size();i++) {
			int count = boardDAO.commentCount(boardList.get(i).getBOARD_NUM());
			countList.add(count);
		}
		close(con);
		return countList;
	}

}
