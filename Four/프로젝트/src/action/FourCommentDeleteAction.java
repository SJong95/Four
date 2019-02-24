package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.*;
import vo.ActionForward;

public class FourCommentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int board_num = Integer.parseInt(request.getParameter("BOARD_NUM"));
		int reboardNum = Integer.parseInt(request.getParameter("REBOARD_NUM"));
		String reboardPass = request.getParameter("REBOARD_PASS");
		
		FourCommentDeleteService fourCommentDeleteService = new FourCommentDeleteService();
		ActionForward forward = null;
		boolean isPassRight = fourCommentDeleteService.isReboardPass(reboardNum,reboardPass);
	
		
		if(!isPassRight) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한 없음!')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			boolean deleteResult =fourCommentDeleteService.reboardDelete(reboardNum);
			if(!deleteResult) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			}else {
				HttpSession session = request.getSession();
				forward=new ActionForward();
				forward.setRedirect(true);
				forward.setPath("fourDetailAction.tv");
				session.setAttribute("BOARD_NUM", board_num);
			}
		}
		return forward;
	}

}
