package contloller;
//ログアウト
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {
		//セッションスコープ取得
		HttpSession session = req.getSession();

		session.invalidate(); // セッションの無効化(破棄する）

		res.sendRedirect("login.jsp");
	}

}
