package service;

import static db.JdbcUtil.*;
import java.sql.Connection;
import org.jsoup.*;
import org.jsoup.nodes.*;
import dao.BoardDAO;
import vo.Board;

public class FourWriteService {

	public boolean boardWrite(Board board) {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		String link = board.getBOARD_LINK();
		board.setBOARD_LINK_TITLE(setTitle(link));
		board.setBOARD_LINK_IMAGE(setImage(link));
		board.setBOARD_LINK_NAME(setLinkName(link));
		board.setBOARD_LINK_VIDEO(setVideo(link));
		boolean writeResult = false;
		int result = boardDAO.boardWrite(board);
		if(result!=0) {
			commit(con);
			writeResult=true;
		}else {
			rollback(con);
		}
		close(con);
		return writeResult;
	}
	private String setVideo(String link) {
		String linkId = link.substring(32);
		return "https://www.youtube.com/embed/"+linkId+"?autoplay=1&version=3&hd=1&modestbranding=1&rel=0&showinfo=0&fs=1";
	}
	private String setTitle(String link) {
		String title = "";
		try {
			Document doc = Jsoup.connect(link).get();
			title = doc.title();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return title;
	}
	private String setLinkName(String link) {
		String name="";
		try {
			Document doc = Jsoup.connect(link).get();
			name = doc.select(".yt-user-info a").text();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	private String setImage(String link) {
		String linkId = link.substring(32);
		return "https://img.youtube.com/vi/"+linkId+"/0.jpg";
	}
}
