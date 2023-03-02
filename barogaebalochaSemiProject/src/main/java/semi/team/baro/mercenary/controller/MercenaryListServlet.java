package semi.team.baro.mercenary.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.team.baro.mercenary.model.service.MercenaryService;
import semi.team.baro.mercenary.model.vo.Mercenary;

/**
 * Servlet implementation class MercenaryListServlet
 */
@WebServlet(name = "MercenaryList", urlPatterns = { "/mercenaryList.do" })
public class MercenaryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MercenaryListServlet() {
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
		//3. 비즈니스로직
		MercenaryService service = new MercenaryService();
		ArrayList<Mercenary> list = service.mercenarySelectAll();
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/mercenary/mercenaryList.jsp");
		request.setAttribute("list", list);
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
