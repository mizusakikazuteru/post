package service;


import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Post;
import dao.PostMessageDao;
//新規投稿
public class PostService {


	public void register(Post posts) {

		Connection connection = null;
		try {
			connection = getConnection();

			PostMessageDao postmessageDao = new PostMessageDao();
			postmessageDao.insert(connection, posts);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	private static final int LIMIT_NUM = 1000;

	public List<Post> getPost() {

		Connection connection = null;
		try {
			connection = getConnection();

			PostMessageDao postmessageDao = new PostMessageDao();
			List<Post> ret = postmessageDao.getPost(connection, LIMIT_NUM);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}


