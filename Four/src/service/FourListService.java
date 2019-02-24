package service;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.Board;
import static db.JdbcUtil.*;

public class FourListService {

	public ArrayList<Board> boardList(int page,int limit) { // 영상 리스트 가져오기
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		ArrayList<Board> boardList = boardDAO.boardList(page,limit);
		for(int i =0;i<boardList.size();i++) { // 정상적으로 가져왓는지 확인하기 위함
			System.out.println(boardList.get(i).getBOARD_LINK_TITLE());
		}
		close(con);
		return boardList;
	}
	public ArrayList<Integer> commentCount(ArrayList<Board> boardList) { // 영상 댓글 가져오기
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		ArrayList<Integer> countList = new ArrayList<>();
		for(int i =0;i<boardList.size();i++) { // 해당 글 번호를 매개변수로 넘겨주면서 가져옴
			int count = boardDAO.commentCount(boardList.get(i).getBOARD_NUM());
			countList.add(count);
		}
		close(con);
		return countList;
	}
	public int listCount() { // 전체 글 갯수
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		int result = boardDAO.listCount();
		close(con);
		return result;
	}

}
