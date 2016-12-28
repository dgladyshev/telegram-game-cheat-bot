package com.dgladyshev.commands;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

@Component
public class BehaviorCommands implements CommandMarker {

	@CliAvailabilityIndicator({"print"})
	public boolean isCommandAvailable() {
		return true;
	}

	@CliCommand(value = "print", help = "Prints message")
	public String print(
			@CliOption(key = {"message"}, mandatory = true, help = "help message")
			final String message
	) {
		return "Message: " + message;
	}
}