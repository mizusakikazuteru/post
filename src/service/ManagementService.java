package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;

import beans.Branch;
import dao.ManagementDao;

public class ManagementService {

	public Branch branch(int Id) {
		Connection connection = null;
		try {
			connection = getConnection();

			ManagementDao managementDao = new ManagementDao();
			Branch branch = managementDao.getBranch(connection, Id);

			commit(connection);

			return branch;// HomeServletへ情報を渡す。
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}}





