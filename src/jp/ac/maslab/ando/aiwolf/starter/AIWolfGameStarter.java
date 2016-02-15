package jp.ac.maslab.ando.aiwolf.starter;

import java.io.IOException;

public class AIWolfGameStarter {
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		ArgumentsProcessor argsProcessor = new ArgumentsProcessor(args);
		argsProcessor.checkHelp();
		argsProcessor.checkVersion();

		StarterSetting setting = new StarterSetting(argsProcessor.getConfigFilePathname());
		RepeatStarter starter = new RepeatStarter(setting);
		starter.start();
	}
}
