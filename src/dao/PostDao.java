package dao;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Post;
import exception.SQLRuntimeException;

public class PostDao {

	// 投稿情報を結果セットからbeansのpost.javaに入れる
	public List<Post> toPostList(ResultSet rs) throws SQLException {

		List<Post> ret = new ArrayList<Post>();

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String Subject = rs.getString("subject");
				String Text = rs.getString("text");
				String Category = rs.getString("category");
				int UserId = rs.getInt("userId");

				Post post = new Post();
				post.setId(id);
				post.setSubject(Subject);
				post.setText(Text);
				post.setCategory(Category);
				post.setUserId(UserId);

				ret.add(post);

			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, Post post) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO postings ( ");
			sql.append(" subject");
			sql.append(", text");
			sql.append(",category");
			sql.append(", user_id");
			sql.append(") VALUES (");
			sql.append(" ?"); // subject
			sql.append(", ?"); // text
			sql.append(", ?"); // category
			sql.append(", ?"); // user_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, post.getSubject());
			ps.setString(2, post.getText());
			ps.setString(3, post.getCategory());
			ps.setInt(4, post.getUserId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
