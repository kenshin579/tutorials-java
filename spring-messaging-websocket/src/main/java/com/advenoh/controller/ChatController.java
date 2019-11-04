package com.advenoh.controller;

import kr.pe.advenoh.model.Message;
import kr.pe.advenoh.model.OutputMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
public class ChatController {

	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public OutputMessage send(final Message message) throws Exception {

		final String time = new SimpleDateFormat("HH:mm").format(new Date());
		return new OutputMessage(message.getFrom(), message.getText(), time);
	}

}
