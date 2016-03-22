package contloller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Branch;
import beans.User;
import service.ManagementService;

@WebServlet(urlPatterns = { "/index.jsp" })

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		User user = (User) req.getSession().getAttribute("loginUser");

		boolean isShowMessageForm;

		if (user != null) {
			isShowMessageForm = true;
		} else {
			isShowMessageForm = false;
		}

		req.setAttribute("isShowMessageForm", isShowMessageForm);

		req.getRequestDispatcher("/home.jsp").forward(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {


		//支店コード１/支店名本社ならtrue
		HttpSession session = req.getSession();

		Int ID = req.getParameter("ID");

		ManagementService manegementService = new ManagementService();

		Branch id = manegementService.branch(ID);

	if (ID != null) {

		session.setAttribute("branch", ID);
		res.sendRedirect("manegement.jsp");
	} else {

		List<String> messages = new ArrayList<String>();
		messages.add("管理者権限がありません。");
		session.setAttribute("errorMessages", messages);
		res.sendRedirect("home.jsp");
	}
}

}
