package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;

public class FourDeleteService {

	public boolean isVideoWriter(int board_num, String parameter) { // 동영상 목록 불러오기
		boolean isVideoWriter = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		isVideoWriter = boardDAO.isVideoBoardWriter(board_num, parameter);
		close(con);
		return isVideoWriter;
	}

	public boolean removeVideo(int board_num) { // 동영상이 제거 되는지 아닌지 확인하는 메소드
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int deleteCount = boardDAO.deleteVideo(board_num);
		
		if(deleteCount > 0){ //제거가 되면 1을 출력하므로 여기 적용
			commit(con);
			isRemoveSuccess=true;
		}
		else{ // 아니면 롤백
			rollback(con);
		}
		
		close(con);
		return isRemoveSuccess;
	}

}
