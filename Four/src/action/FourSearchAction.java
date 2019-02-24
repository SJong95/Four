package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.*;
import vo.*;

public class FourSearchAction implements Action{ // Made by 세종

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		String search = request.getParameter("search");
		String category = request.getParameter("category");
		FourSearchService fourSearchService = new FourSearchService();
		/* 페이지 처리 부분 */
		int page=1; // 페이지 번호를 알기위한 변수
		int limit=6; // 한 페이지에서 보여줄 글 갯수 지정
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listCount = fourSearchService.searchCount(search); // 검색 글 갯수
		ArrayList<Board> boardList = fourSearchService.getSearch(search,page,limit); // 검색 영상 가져오기
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
		ArrayList<Integer> countList = fourSearchService.commentCount(boardList); // 영상 댓글 수 가져오기
		request.setAttribute("search", search);// 페이지 이동시 필요
		request.setAttribute("category", category);// 페이지 이동시 필요
		request.setAttribute("boardList", boardList);
		request.setAttribute("countList", countList);
		forward.setPath("/tv/main.jsp");
		return forward;
	}

}
