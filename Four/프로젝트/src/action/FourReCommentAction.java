package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.*;
import vo.ActionForward;
import vo.ReBoard;

public class FourReCommentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ReBoard reboard = new ReBoard();
		int board_num = Integer.parseInt(request.getParameter("BOARD_NUM"));
		int num=Integer.parseInt(request.getParameter("REBOARD_NUM"));
		String id=request.getParameter("comname");
		String pass=request.getParameter("compass");
		String content=request.getParameter("comcontent");
		
		reboard.setBOARD_NUM(board_num);
		reboard.setREBOARD_NAME(id);
		reboard.setREBOARD_PASS(pass);
		reboard.setREBOARD_CONTENT(content);
		reboard.setREBOARD_RE_REF(num);
		
		FourReCommentService fourRecommentService = new FourReCommentService();
		boolean recommentResult = fourRecommentService.reboardRecomment(reboard);
		if(recommentResult) {
			HttpSession session = request.getSession();
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("fourDetailAction.tv");
			session.setAttribute("BOARD_NUM", board_num);
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('답글 실패')");
			out.println("history.back");
			out.println("</script>");
		}
		return forward;
		
	}

}
