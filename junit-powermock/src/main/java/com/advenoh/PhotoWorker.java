package com.advenoh;

import kr.pe.advenoh.model.Photos;
import kr.pe.advenoh.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PhotoWorker extends BaseWorker {
	public void run(String username) {
		log.info("run...username: }", username);

		User user = this.getUserInfo(username);
		List<Photos> photos = user.getPhotos();
		// process photos, etc
	}
}