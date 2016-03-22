package dao;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Branch;
import exception.SQLRuntimeException;

public class ManagementDao {

	public ManagementDao() {

	}


	public Branch getBranch(Connection connection, int Id) {
		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM branches WHERE id=1";


			ps = connection.prepareStatement(sql);
			ps.setInt(1, Id);

			ResultSet rs = ps.executeQuery();

			List<Branch> branchList = toBranchList(rs);
			// beansクラスのBranch.javaから<Branch>に格納

			if (branchList.isEmpty() == true) {
				// 支店情報が空の時NULL
				return null;
			} else {
				return branchList.get(0);
				// ﾘﾀｰﾝでSERVICEクラスへ戻る
				// get(0)で情報を取り出す。
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);
		}
	}


	private static List<Branch> toBranchList(ResultSet rs) throws SQLException {

		List<Branch> ret = new ArrayList<Branch>();

		try {
			while (rs.next()) {
				Branch management = new Branch();
				management.setId(rs.getInt("id"));
				ret.add(management);

			}
			return ret;
		} finally {
			close(rs);
		}
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 4bd8a2a3afb31ce75d17c8b68710b441c7c40e07
