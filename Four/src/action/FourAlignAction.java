package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.*;
import service.*;


public class FourAlignAction implements Action{ // Made by 세종

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		FourAlignService fourAlignService = new FourAlignService();
		String align = request.getParameter("align");
		/* 페이지 처리 부분 */
		int page=1; // 페이지 번호를 알기위한 변수
		int limit=6; // 한 페이지에서 보여줄 글 갯수 지정
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listCount = fourAlignService.listCount(); // 전체 글 갯수 
		ArrayList<Board> boardList = fourAlignService.boardAlign(align,page,limit); 
			// 정렬 값과 페이징처리 값 전달해 정렬된 영상리스트 boardList에 저장
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
		//ArrayList<Board> boardList = fourListService.boardList();
		ArrayList<Integer> countList = fourAlignService.commentCount(boardList);
		request.setAttribute("align", align);	// 현재 정렬값을 기억하기 위함
		request.setAttribute("boardList", boardList);	// 영상 리스트 저장
		request.setAttribute("countList", countList);	// 해당 영상 별 댓글갯수를 보여주기 위함
		forward= new ActionForward();
		forward.setPath("/tv/main.jsp");
		return forward;
	}

}
