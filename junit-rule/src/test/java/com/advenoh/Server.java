package com.advenoh;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
	public void connect() {
		log.info("서버 연결 시작...");
	}

	public void disconnect() {
		log.info("서버 연결 끊기...");
	}
}
