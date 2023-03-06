package semi.team.baro.history.conroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.team.baro.history.model.vo.HistoryPageData;
import semi.team.baro.mercenary.model.service.MercenaryService;

/**
 * Servlet implementation class HistoryMercenaryServlet
 */
@WebServlet(name = "HistoryMercenary", urlPatterns = { "/historyMercenary.do" })
public class HistoryMercenaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryMercenaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int reqPage = Integer.parseInt(request.getParameter("memberNo"));
		//3. 비즈니스로직
		MercenaryService service = new MercenaryService();
		HistoryPageData hpd = service.mercenaryOneSelect(memberNo, reqPage);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/history.jsp");
		request.setAttribute("list", hpd.getMcList());
		request.setAttribute("pageNavi", hpd.getPageNavi());
		view.forward(request, response);
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
