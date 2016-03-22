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

import beans.User;
import service.LoginService;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// doGetでlogin.jspへforwardする。
		req.getRequestDispatcher("login.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");

		LoginService loginService = new LoginService();

		User user = loginService.login(loginId, password);

		// ログイン情報を保持する為、セッションスタート
		HttpSession session = req.getSession();
		// ユーザーがnullかチェックする
		if (user != null) {

			// リダイレクト
			session.setAttribute("loginUser", user);
			res.sendRedirect("home.jsp");

		} else {
			// ユーザーがnullだったらリストに入れてエラー文を出す。
			List<String> messages = new ArrayList<String>();
			messages.add("ログインに失敗しました。");
			session.setAttribute("errorMessages", messages);
			// ここにerrorMessagesを記述する事でjspにも使用出来るようになる。
			res.sendRedirect("login");
		}
	}

}