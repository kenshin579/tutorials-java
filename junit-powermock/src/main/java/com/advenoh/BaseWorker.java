package com.advenoh;

import com.advenoh.model.User;

public abstract class BaseWorker {
//	public void JDBCConnection connection;

//	protected JDBCConnection getDatabaseHandle() {
//		return connection;
//	}

	protected User getUserInfo(String username) {
//		Rows row = connection.executeQuery( ... );
		// complicated logic to parse rows and retrieve
		// User data
		User user = new User();
		user.setName("Frank");
		return user;
	}

	public abstract void run(String arg);
}
