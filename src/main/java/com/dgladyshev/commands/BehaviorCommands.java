package com.dgladyshev.commands;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BehaviorCommands implements CommandMarker {

	@Autowired
	CheatBotService cheatBotService;

	@CliAvailabilityIndicator({"run"})
	public boolean isCommandAvailable() {
		//TODO implement
		return true;
	}

	@CliCommand(value = "start", help = "starts cheat bot")
	public String start(
			@CliOption(key = {"strategy"}, mandatory = false, help = "help strategy")
			final String strategy
	) {
		new Thread(() -> cheatBotService.startBot()).start();
		String message = "Cheat bot started";
		return !Strings.isNullOrEmpty(strategy) ? message + " with strategy " + strategy : message;
	}

	@CliCommand(value = "stop", help = "stops cheat bot")
	public String stop() {
		new Thread(() -> cheatBotService.stopBot()).start();
		return "Cheat bot stopped";
	}

	@CliCommand(value = "status", help = "get cheat bot status")
	public String status() {
		return cheatBotService.getStatus();
	}
}