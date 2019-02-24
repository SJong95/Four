package action;

import vo.ActionForward;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FourCategoryService;
import service.FourListService;
import vo.ActionForward;
import vo.Board;
import vo.PageInfo;

public class FourCategoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("카테고리 액션");
		ActionForward forward = new ActionForward();
		String category = request.getParameter("category");
		FourCategoryService fourCategoryService = new FourCategoryService();
		FourListService fourListService = new FourListService();
		/* 페이지 처리 부분 */
		int page=1; // 페이지 번호를 알기위한 변수
		int limit=6; // 한 페이지에서 보여줄 글 갯수 지정
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listCount = fourCategoryService.categoryCount(category);
		ArrayList<Board> boardList = fourCategoryService.getCategory(category,page,limit);
		/* 페이지 계산 부분 */
		int maxPage = (int)((double)listCount/limit+1);
		int startPage = (((int)((double)page/6+0.9))-1)*6+1;
		int endPage = startPage+6-1;
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListPage(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		request.setAttribute("pageInfo", pageInfo);
		/* ----------- */
		//ArrayList<Board> boardList = fourCategoryService.getCategory(category);
		ArrayList<Integer> countList = fourListService.commentCount(boardList);
		request.setAttribute("category", category);
		request.setAttribute("boardList", boardList);
		request.setAttribute("countList", countList);
		forward.setPath("/tv/main.jsp");
		return forward;
	}
}
