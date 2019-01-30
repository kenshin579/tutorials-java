package kr.jdm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthService {
	private AuthDao dao;

	public boolean isLogin(String id) {
		boolean isLogin = dao.isLogin(id);
		if (isLogin) {
			log.info("isLogin: id: {}", id);
		}
		return isLogin;
	}
}
