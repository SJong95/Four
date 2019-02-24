package service;

import java.sql.Connection;
import dao.*;
import static db.JdbcUtil.*;

public class FourCommentDeleteService {

	public boolean isReboardPass(int reboardNum, String reboardPass) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		boolean isReboardPass=boardDAO.isReboardPass(reboardNum,reboardPass);
		//boardDAO에 isReboardPass메소드에 reboardNum,reboardPass를 매개값으로 넘겨주고
		//리턴값을 isReboardPass라는변수에 대입
		close(con);
		return isReboardPass; //변수isReboardPass값을 리턴
	}
	
	public boolean reboardDelete(int reboardNum) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int deleteCount = boardDAO.reboardDelete(reboardNum);
		//boardDAO에 reboardDelete메소드에 reboardNum을 매개값으로 넘겨주고
		//deleteCount에 그 리턴값을 대입
		boolean deleteResult=false;
		
		//deleteCount가 0보다크면deleteResult를 true로 바꾸고 commit
		//아니면 rollback
		if(deleteCount>0) {
			commit(con);
			deleteResult=true;
		}else {
			rollback(con);
		}
		return deleteResult; //위의 실행 결과 deleteResult를 리턴
	}
	
}