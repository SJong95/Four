package action;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.*;
import vo.ActionForward;

public class FourBadAction implements Action{ // Made by 세종
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String board_num = request.getParameter("board_num_bad"); // 싫어요 클릭한 영상 번호
		FourBadService fourBadService = new FourBadService();
		/*- 처리 후 버튼을 클릭한 상태로 되돌아가기 위함 -*/
		String category = request.getParameter("category");
		String align = request.getParameter("align");
		String search = request.getParameter("search");
		/*-----------------------------------*/
		boolean result = fourBadService.boardBad(board_num); // 싫어요 서비스 메소드 호출
		if(result) { // 처리 성공 후 정렬, 카테고리, 검색 값을 저장 후 경로 지정
			request.setAttribute("align", align);
			request.setAttribute("category", category);
			request.setAttribute("search", search);
			forward = new ActionForward();
			forward.setPath("four.tv");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}
}
