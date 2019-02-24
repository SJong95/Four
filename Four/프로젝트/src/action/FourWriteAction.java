package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FourWriteService;
import vo.ActionForward;
import vo.Board;

public class FourWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		Board board = new Board();
		board.setBOARD_LINK(request.getParameter("link"));
		board.setBOARD_SUBJECT(request.getParameter("subject"));
		board.setBOARD_NAME(request.getParameter("name"));
		board.setBOARD_PASS(request.getParameter("pass"));
		board.setBOARD_CONTENT(request.getParameter("content"));
		board.setBOARD_CATEGORY(request.getParameter("category"));
		FourWriteService fourWriteService = new FourWriteService();
		boolean writeResult = fourWriteService.boardWrite(board);
		if(writeResult) {
			forward = new ActionForward();
			forward.setPath("four.tv");
			forward.setRedirect(true);
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
