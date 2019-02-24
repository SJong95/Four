package action;

import vo.ActionForward;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FourCategoryService;
import service.FourListService;
import vo.ActionForward;
import vo.Board;

public class FourCategoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		String Category = request.getParameter("category");
		FourCategoryService fourCategoryService = new FourCategoryService();
		FourListService fourListService = new FourListService();
		ArrayList<Board> boardList = fourCategoryService.getCategory(Category);
		ArrayList<Integer> countList = fourListService.commentCount(boardList);
		request.setAttribute("boardList", boardList);
		request.setAttribute("countList", countList);
		forward.setPath("/tv/main.jsp");
		return forward;
	}
}
