package com.sunsoft.study.pattern.template.callback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.sunsoft.study.pattern.template.User;

/**
 * @File: UserService.java
 * @Date: 2015��8��28��
 * @Author: wwei
 * @Copyright: ��Ȩ���� (C) 2015 ��ΰ����.
 *
 */
public class UserService {
	/**
	 * ��ѯ�û���Ϣ
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public Object queryUser(final String sql) throws SQLException {
		JdbcTemplate jt = new JdbcTemplate();
		return jt.query(new StatementCallback(){// �����ڲ���ʵ��
			public Object doInStatement(PreparedStatement stmt, String sql) throws SQLException {
				ResultSet rs = stmt.executeQuery(sql);
				List<User> userList = new ArrayList<User>();
				User employee = null;
				while (rs.next()) {
					employee = new User();
					employee.setUserName(rs.getString("user_name"));
					employee.setPassword(rs.getString("password"));
					userList.add(employee);
				}
				return userList;
			}
		}, sql, false);
	}  
	
	/**
	 * �����û���Ϣ
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public boolean insertUser(final String sql) throws SQLException {
		JdbcTemplate jt = new JdbcTemplate();
		return jt.update(new StatementCallback(){// �����ڲ���ʵ��
			public Object doInStatement(PreparedStatement stmt, String sql) throws SQLException {
				return stmt.execute(sql);
			}
		}, sql, false);
	}  
	
	/**
	 * ���������û���Ϣ
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public int[] insertBatchUser(final String sql, final List<User> list) throws SQLException {
		JdbcTemplate jt = new JdbcTemplate();
		return jt.updateBatch(new StatementCallback(){// �����ڲ���ʵ��
			public int[] doInStatement(PreparedStatement stmt, String sql) throws SQLException {
				if (!CollectionUtils.isEmpty(list)) {
					int i = 0;
					for (User user : list) {
						stmt.setString(1, user.getUserName());
						stmt.setString(2, user.getPassword());
						if (i%500 == 0) {
							stmt.executeBatch();
							stmt.getConnection().commit();
							System.out.println("current location:--------------" + i);
						} 
						stmt.addBatch();
						i ++;
					}
				}
				return stmt.executeBatch();
			}
		}, sql, true);
	} 
}
