package com.geektrust;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import com.geektrust.commands.CommandRegistry;
import com.geektrust.config.Configuration;

public class App {

	public static void main(String[] args) {
		List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
		run(commandLineArgs);
	}

	public static void run(List<String> commandLineArgs) {

		Configuration configuration = Configuration.getInstance();
		CommandRegistry commandRegistry = configuration.getCommandRegistry();

		String inputFile = commandLineArgs.get(0);
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {

			while (true) {
				String line = bufferedReader.readLine();
				if (line == null)
					break;

				commandRegistry.invokeCommand(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
