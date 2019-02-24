package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.*;
import vo.*;

public class FourCateAlignAction implements Action{ // Made by 세종

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("카테 정렬 액션");
		ActionForward forward = new ActionForward();
		String category = request.getParameter("category");
		String align = request.getParameter("align");
		FourCateAlignService fourCateAlignService = new FourCateAlignService();
		/* 페이지 처리 부분 */
		int page=1; // 페이지 번호를 알기위한 변수
		int limit=6; // 한 페이지에서 보여줄 글 갯수 지정
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listCount = fourCateAlignService.cateAlignCount(category); // 조건에 맞는 글 갯수 가져오기
		ArrayList<Board> boardList = fourCateAlignService.getCateAlign(category,align,page,limit);
			// 카테고리+정렬 영상 리스트 가져오기 
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
		ArrayList<Integer> countList = fourCateAlignService.commentCount(boardList); // 댓글 수 가져오기
		request.setAttribute("align", align);// 페이지 이동시 필요
		request.setAttribute("category", category);// 페이지 이동시 필요
		request.setAttribute("boardList", boardList);
		request.setAttribute("countList", countList);
		forward.setPath("/tv/main.jsp");
		return forward;
	}

}
