package contloller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Branch;
import beans.Post;
import beans.User;
import service.ManagementService;
import service.UserService;

@WebServlet(urlPatterns = { "/home" })

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		User user = (User) req.getSession().getAttribute("loginUser");
		//支店コード１/支店名本社ならtrue
		HttpSession session = req.getSession();

		int branchId = req.getInt("branchId");

		ManagementService manegementService = new ManagementService();

		Branch id = manegementService.branch(branchId);

	if (branchId != null) {

		session.setAttribute("branch", branchId);
		res.sendRedirect("manegement.jsp");
	} else {

		List<String> messages = new ArrayList<String>();
		messages.add("管理者権限がありません。");
		session.setAttribute("errorMessages", messages);
		res.sendRedirect("home.jsp");
	}
}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = req.getSession();
		if (isValid(req, messages) == true) {

			User user = (User) session.getAttribute("loginUser");

			Post post = new Post();
			post.setSubject(req.getParameter("subject"));
			post.setText(req.getParameter("text"));
			post.setCategory(req.getParameter("category"));
			post.setUserId(user.getId());


			new UserService().register(user);
			// フォワード
			RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
			dispatcher.forward(req, res);

		} else {
			session.setAttribute("errorMessages", messages);
			res.sendRedirect("home.jsp");
		}
	}

	private boolean isValid(HttpServletRequest req, List<String> messages) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}


	}


