package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.FourDetailService;
import vo.ActionForward;
import vo.*;

public class FourDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {// 상세목록 값 불러오기
		String board_num_str=request.getParameter("BOARD_NUM");
		int board_num=0;
		if(board_num_str !=null) {
			board_num = Integer.parseInt(board_num_str);
		}else {
			HttpSession session = request.getSession();
			board_num = (int)session.getAttribute("BOARD_NUM");
		}
		//String page = request.getParameter("page");
		FourDetailService fourDetailService = new FourDetailService(); // 값을 담을 객체 생성
		Board board = fourDetailService.getArticle(board_num);
		ArrayList<ReBoard> commentList = fourDetailService.reboardCommentList(board_num); //reboardCommentList 실행
		ActionForward forward = new ActionForward();
		//request.setAttribute("page", page);
	   	request.setAttribute("board", board);
	   	request.setAttribute("commentList", commentList);
   		forward.setPath("/tv/view.jsp"); // 실행후 경로 설정
   		return forward;
	}

}
