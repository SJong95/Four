package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.*;
import vo.ActionForward;

public class FourDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int board_num=Integer.parseInt(request.getParameter("BOARD_NUM"));  //jsp에 있는 BOARD_NUM 파라미터 담기
//		String nowPage = request.getParameter("page");
		FourDeleteService boardDeleteService = new FourDeleteService();
		boolean DeleteVideo =boardDeleteService.isVideoWriter(board_num, request.getParameter("PASS_DEL")); 

		if(!DeleteVideo){ //DeleteVideo 값이 false면 삭제할 권한 없음
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}

		else{ 
			
			boolean isDeleteSuccess = boardDeleteService.removeVideo(board_num);

			if(!isDeleteSuccess){ // isDeleteSuccess 값이 false면 삭제 안됨.
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			else{// isDeleteSucess 값이 true면 삭제 실행
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("four.tv"); // four.tv로 경로 설정
			}
			
		}


		return forward;
	}

}
