package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.*;
import vo.*;

public class FourAlignService {
	public ArrayList<Board> boardAlign(String align,int page,int limit) { // 영상 정렬된 리스트 가져오기
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
			// DB연결 부분
		ArrayList<Board> boardList = null;
		if(align.equals("like")) {	// 좋아요 순
			boardList = boardDAO.likeAlign(page, limit);
		}else if(align.equals("reply")) {	// 댓글 순
			boardList = boardDAO.replyAlign(page,limit);	
		}else {	// 등록 순 (기존에 잇던 영상 리스트 메소드 호출)
			boardList = boardDAO.boardList(page, limit);
		}
		for(int i =0;i<boardList.size();i++) { // 영상을 제대로 가져온 뒤 확인을 위함
			System.out.println(boardList.get(i).getBOARD_LINK_TITLE());
		}
		close(con);
		return boardList;
	}
	public ArrayList<Integer> commentCount(ArrayList<Board> boardList) { // 영상별 댓글 갯수 가져오기
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		ArrayList<Integer> countList = new ArrayList<>();
		for(int i =0;i<boardList.size();i++) { // 영상 리스트 갯수 별로 메소드를 호출해 댓글 갯수를 ArrayList에 저장
			int count = boardDAO.commentCount(boardList.get(i).getBOARD_NUM());
			countList.add(count);
		}
		close(con);
		return countList;
	}
	public int listCount() { // 글 전체 갯수 가져오기
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		int result = boardDAO.listCount();
		close(con);
		return result;
	}

}
