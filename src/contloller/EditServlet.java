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

import org.apache.commons.lang.StringUtils;

import beans.Branch;
import beans.Department;
import beans.User;
import exception.NoRowsUpdatedRuntimeException;
import service.BranchService;
import service.DepartmentService;
import service.UserService;


@WebServlet(urlPatterns = { "/edit" })
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

//		HttpSession session = req.getSession();
//		User loginUser = (User) session.getAttribute("loginUser");
//
//		if (session.getAttribute("editUser") == null) {
//			User editUser = new UserService().getUser(loginUser.getId());
//			session.setAttribute("editUser", editUser);
//		}

		// 支店情報取得→jspへ表示
		BranchService branchService = new BranchService();
		List<Branch> branches = branchService.getBranches();

		req.setAttribute("branches", branches);

		// 部署・役職情報取得→jspへ表示
		DepartmentService departmentService = new DepartmentService();
		List<Department> departments = departmentService.getDepartments();

		req.setAttribute("departments", departments);
		req.getRequestDispatcher("edit.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = req.getSession();

		User editUser = getEditUser(req);
		session.setAttribute("editUser", editUser);
		if (isValid(req, messages) == true) {
			// IsEmptyメソッドの処理(null,空チェック）

			try {
				new UserService().update(editUser);
				// DBの更新を行う（.update)
			} catch (NoRowsUpdatedRuntimeException e) {
				session.removeAttribute("editUser");
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", messages);
				res.sendRedirect("edit.jsp");
			}

			session.setAttribute("loginUser", editUser);
			// loginUser→変更前の情報 editUserは変更後の情報
			session.removeAttribute("editUser");

			res.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			res.sendRedirect("edit");
		}
	}

	// ユーザー編集の為の情報
	private User getEditUser(HttpServletRequest req) throws IOException, ServletException {

		HttpSession session = req.getSession();
		User editUser = (User) session.getAttribute("editUser");

		editUser.setLoginId(req.getParameter("loginId"));
		editUser.setPassword(req.getParameter("password"));
		editUser.setName(req.getParameter("name"));
		editUser.setBranchId(req.getParameter("branchId"));
		editUser.setDepartmentId(req.getParameter("departmentId"));
		return editUser;

	}

	//
	private boolean isValid(HttpServletRequest req, List<String> messages) {

		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");
		String name = req.getParameter("name");

		// 必須項目の空とnullの入力チェック
		if (StringUtils.isEmpty(loginId)) {
			messages.add("ログインIDを入力してください");
		}
		if (StringUtils.isEmpty(password)) {
			messages.add("パスワードを入力してください");
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("氏名を入力してください");
		}
		if (messages.size() == 0) {
			// 上記情報があったらOK、なかったら上記のメッセージ。
			return true;
		} else {
			return false;
		}
	}

}
