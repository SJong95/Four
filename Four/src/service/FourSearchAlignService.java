package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.Board;

public class FourSearchAlignService {

	public int searchCount(String search) { // 검색 글 갯수
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		int result = boardDAO.searchCount(search);
		close(con);
		return result;
	}

	public ArrayList<Board> getSearch(String search, String align, int page, int limit) { // 영상 리스트 가져오기
		ArrayList<Board> boardList = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		if(align.equals("like")) { // 좋아요 메소드 호출
			boardList = boardDAO.searchLikeAlign(search, page, limit);
		}else if(align.equals("reply")) { // 댓글 순 메소드 호출
			boardList = boardDAO.searchReplyAlign(search, page, limit);
		}else { // 등록순 메소드 호출
			boardList = boardDAO.getSearch(search,page,limit);	
		}
		close(con);
		return boardList;
		
	}
	public ArrayList<Integer> commentCount(ArrayList<Board> boardList) { // 영상 댓글 가져오기
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
