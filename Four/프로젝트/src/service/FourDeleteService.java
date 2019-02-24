package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;

public class FourDeleteService {

	public boolean isVideoWriter(int board_num, String parameter) {
		boolean isVideoWriter = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		isVideoWriter = boardDAO.isVideoBoardWriter(board_num, parameter);
		close(con);
		return isVideoWriter;
	}

	public boolean removeVideo(int board_num) {
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int deleteCount = boardDAO.deleteVideo(board_num);
		
		if(deleteCount > 0){
			commit(con);
			isRemoveSuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isRemoveSuccess;
	}

}
