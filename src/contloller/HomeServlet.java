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
import service.PostService;
import service.UserService;

@WebServlet(urlPatterns = { "/home" })

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		User user = (User) req.getSession().getAttribute("loginUser");
		// 支店コード１/支店名本社ならtrue
		HttpSession session = req.getSession();

		int branchId = (int) session.getAttribute("branchId");

		ManagementService manegementService = new ManagementService();

		Branch id = manegementService.branch(branchId);

		if (branchId == 1) {

			session.setAttribute("branch", branchId);
			res.sendRedirect("management.jsp");
		} else {

			List<String> messages = new ArrayList<String>();
			messages.add("管理者権限がありません。");
			session.setAttribute("errorMessages", messages);
			res.sendRedirect("home.jsp");
		}
		boolean isShowMessageForm;
		if (user != null) {
			isShowMessageForm = true;
		} else {
			isShowMessageForm = false;
		}

		List<Post> messages = new PostService().getpost();

		req.setAttribute("messages", messages);
		req.setAttribute("isShowMessageForm", isShowMessageForm);

		req.getRequestDispatcher("home.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = req.getSession();

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

	}

}
