package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.Board;

public class FourSearchService {

	public int searchCount(String search) { // 검색 영상 글 갯수
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		int result = boardDAO.searchCount(search);
		close(con);
		return result;
	}

	public ArrayList<Board> getSearch(String search, int page, int limit) { // 검색 영상 글 리스트 가져오기
		ArrayList<Board> boardList = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		boardList = boardDAO.getSearch(search,page,limit);
		close(con);
		return boardList;
		
	}
	public ArrayList<Integer> commentCount(ArrayList<Board> boardList) { // 영상 댓글 갯수 가져오기
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		ArrayList<Integer> countList = new ArrayList<>();
		for(int i =0;i<boardList.size();i++) { // 해당 영상의 번호를 넘기면서 가져옴
			int count = boardDAO.commentCount(boardList.get(i).getBOARD_NUM());
			countList.add(count);
		}
		close(con);
		return countList;
	}

}
