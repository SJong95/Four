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
		String link = linkCheck(board.getBOARD_LINK()); // 등록시 링크란에 쓴 값 수정
		board.setBOARD_LINK_TITLE(setTitle(link)); // 타이틀 정보 저장
		board.setBOARD_LINK_IMAGE(setImage(link)); // 이미지 정보 저장
		board.setBOARD_LINK_NAME(setLinkName(link)); // 원본 게시자 정보 저장
		board.setBOARD_LINK_VIDEO(setVideo(link)); // 영상 재생 링크 저장
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
	private String linkCheck(String link) { // 유튜브에서 가져오는 링크의 형식 체크 후 하나로 바꾸기
		String linkId="";
		if(link.length()!=43) { // 길이가 43이 아닐 경우 (주소줄에서 복사한게 아닌 경우)
			if(link.contains("youtu.be")) { // 공유 밑 url 복사로 가져온 경우
				linkId = link.substring(17, 28);
			}else {// 재생목록의 영상을 가져온 경우
				linkId = link.substring(32,43);
			}
			link = "https://www.youtube.com/watch?v="+linkId; // 모두 같은 형식으로 수정
		}
		return link;
	}
	private String setVideo(String link) { // 영상 재생을 위한 링크 저장 메소드
		String linkId = link.substring(32);
		return "https://www.youtube.com/embed/"+linkId+"?autoplay=1&version=3&hd=1&modestbranding=1&rel=0&showinfo=0&fs=1";
	}
	private String setTitle(String link) { // 영상의 타이틀 정보를 가져오기 위한 메소드
		String title = "";
		try {
			Document doc = Jsoup.connect(link).get();// Jsoup 클래스를 이용해 접근
			title = doc.title();	// 해당 페이지의 타이틀태그의 정보를 가져옴
		}catch(Exception e) {
			e.printStackTrace();
		}
		return title;
	}
	private String setLinkName(String link) { // 영상의 원본 게시자 정보를 가져오기 위한 메소드
		String name="";
		try {
			Document doc = Jsoup.connect(link).get(); // Jsoup 클래스를 이용해 접근
			name = doc.select(".yt-user-info a").text();	// 원하는 정보의 태그로 접근해 텍스트를 가져옴
		}catch(Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	private String setImage(String link) { // 영상의 이미지 정보를 가져오기 위한 메소드
		String linkId = link.substring(32); // 유튜브 고유의 ID 값을 잘라서 가져온다.
		return "https://img.youtube.com/vi/"+linkId+"/0.jpg";
	}
}
