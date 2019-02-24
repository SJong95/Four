package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.*;
import service.*;


public class FourListAction implements Action{ // Made by 세종,준철(페이징처리)

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		FourListService fourListService = new FourListService();
		String align = request.getParameter("align");
		/* 페이지 처리 부분 */
		int page=1; // 페이지 번호를 알기위한 변수
		int limit=6; // 한 페이지에서 보여줄 글 갯수 지정
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = fourListService.listCount(); // 전체 글 갯수
		ArrayList<Board> boardList = fourListService.boardList(page,limit); // 리스트 서비스 메소드 호출
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
		ArrayList<Integer> countList = fourListService.commentCount(boardList); // 영상 별 댓글 가져오기
		request.setAttribute("boardList", boardList);
		request.setAttribute("countList", countList);
		forward= new ActionForward();
		forward.setPath("/tv/main.jsp");
		return forward;
	}

}
