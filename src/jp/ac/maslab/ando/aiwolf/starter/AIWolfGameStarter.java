package jp.ac.maslab.ando.aiwolf.starter;

import java.io.IOException;

public class AIWolfGameStarter {
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		Config config = new Config(new ArgsProcessor(args));
		config.checkExitArg();

		GameStarter gameStarter = new GameStarter(config);
		GameRepeater gameRepeater = new GameRepeater(gameStarter, config.getNumberOfGames());
		GameTotalizer gameTotalizer = new GameTotalizer();
		gameRepeater.setGameTotalizer(gameTotalizer);
		gameRepeater.repeat();
		System.out.println();
		gameTotalizer.printResult();
	}
}
