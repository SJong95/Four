package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.*;
import service.*;


public class FourListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		//String page = request.getParameter("page");
		FourListService fourListService = new FourListService();
		ArrayList<Board> boardList = fourListService.boardList();
		ArrayList<Integer> countList = fourListService.commentCount(boardList);
		request.setAttribute("boardList", boardList);
		request.setAttribute("countList", countList);
		forward= new ActionForward();
		forward.setPath("/tv/main.jsp");
		return forward;
	}

}
