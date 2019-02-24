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
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String board_num_str=request.getParameter("BOARD_NUM");
		int board_num=0;
		if(board_num_str !=null) {
			board_num = Integer.parseInt(board_num_str);
		}else {
			HttpSession session = request.getSession();
			board_num = (int)session.getAttribute("BOARD_NUM");
		}
		//String page = request.getParameter("page");
		FourDetailService fourDetailService = new FourDetailService();
		Board board = fourDetailService.getArticle(board_num);
		ArrayList<ReBoard> commentList = fourDetailService.reboardCommentList(board_num);
		ActionForward forward = new ActionForward();
		//request.setAttribute("page", page);
	   	request.setAttribute("board", board);
	   	request.setAttribute("commentList", commentList);
   		forward.setPath("/tv/view.jsp");
   		return forward;
	}

}
