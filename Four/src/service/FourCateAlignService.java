package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.*;
import vo.*;

public class FourCateAlignService {
	public ArrayList<Integer> commentCount(ArrayList<Board> boardList) { // 댓글 수 가져오기
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		ArrayList<Integer> countList = new ArrayList<>();
		for (int i = 0; i < boardList.size(); i++) { // 영상 리스트 갯수 별로 메소드를 호출해 댓글 갯수를 ArrayList에 저장
			int count = boardDAO.commentCount(boardList.get(i).getBOARD_NUM());
			countList.add(count);
		}
		close(con);
		return countList;
	}

	public int cateAlignCount(String category) { // 글 갯수 가져오기
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		int result = boardDAO.categoryCount(category);
		close(con);
		return result;
	}

	public ArrayList<Board> getCateAlign(String category, String align, int page, int limit) { // 리스트 가져오기
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		ArrayList<Board> boardList = null;
		if (align.equals("like")) { // 좋아요 정렬시
			boardList = boardDAO.cateLikeAlign(category, page, limit);
		} else if (align.equals("reply")) { // 댓글 순 정렬 시
			boardList = boardDAO.cateReplyAlign(category, page, limit);
		} else if (align.equals("regi")) { // 등록 순 정렬 시
			boardList = boardDAO.selectCategory(category, page, limit);
		}
		for (int i = 0; i < boardList.size(); i++) { // 영상 리스트 가져온 결과 확인
			System.out.println(boardList.get(i).getBOARD_LINK_TITLE());
		}
		close(con);
		return boardList;
	}
}
