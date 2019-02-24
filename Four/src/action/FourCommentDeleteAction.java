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
		//view.jsp에서부터 request값을 불러와 변수에 넣는다
		int board_num = Integer.parseInt(request.getParameter("BOARD_NUM"));
		int reboardNum = Integer.parseInt(request.getParameter("REBOARD_NUM"));
		String reboardPass = request.getParameter("REBOARD_PASS");
		
		//fourCommentDeleteService라는 객체를 생성 isReboardPass라는 메소드에 
		//reboardNum,reboardPass를 매개변수로 넘겨주고 리턴값을 isPassRight에 대입
		FourCommentDeleteService fourCommentDeleteService = new FourCommentDeleteService();
		ActionForward forward = null;
		boolean isPassRight = fourCommentDeleteService.isReboardPass(reboardNum,reboardPass);
	
		//!isPassRight가 false면 삭제권한없음 띄움
		if(!isPassRight) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한 없음!')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			//아니면 fourCommentDeleteService의 reboardDelete메소드에 reboardNum을 매개변수로 넘겨줌
			//리턴값을 deleteResult에 대입
			
			boolean deleteResult = fourCommentDeleteService.reboardDelete(reboardNum);
			
			if(!deleteResult) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
				//!deleteResult가 false면 위 문장 실행
			}else {
				HttpSession session = request.getSession();
				forward=new ActionForward();
				forward.setRedirect(true);
				forward.setPath("fourDetailAction.tv");
				session.setAttribute("BOARD_NUM", board_num);
				//!deleteResult가 false가 아니면 위 문장 실행
			}
		}
		return forward; //위의 실행 결과를 리턴
	}

}
