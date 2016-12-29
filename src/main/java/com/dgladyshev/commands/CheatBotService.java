package com.dgladyshev.commands;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

@Slf4j
@Service
public class CheatBotService {

	@Getter
	private AtomicBoolean keepAlive = new AtomicBoolean();

	public void startBot() {
		keepAlive.set(true);
		while (keepAlive.get()) {
			//TODO write implementation for bot behavior
			log.info("wow");
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopBot() {
		keepAlive.set(false);
	}

	public String getStatus() {
		return "bot is alive = " + keepAlive.get();
	}
}
