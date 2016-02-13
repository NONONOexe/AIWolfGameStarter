package jp.ac.maslab.ando.aiwolf.starter;

import java.io.IOException;

import org.aiwolf.server.AIWolfGame;

/**
 * 繰り返し実行可能なゲームスターターです。
 * @author keisuke
 */
public class GameRepeater {
	private GameStarter gameStarter;
	private int numberOfGames = 1;
	private GameTotalizer gameTotalizer;

	/**
	 * 新規リピーターを構築します。
	 * @param gameStarter 人狼ゲームを実行するゲームスターター
	 * @param numberOfGames 繰り返すゲームの回数
	 */
	public GameRepeater(GameStarter gameStarter, int numberOfGames) {
		this.gameStarter = gameStarter;
		this.numberOfGames = numberOfGames;
	}

	/**
	 * 人狼ゲームの繰り返し実行を開始します。
	 * @param numberOfGames 繰り返すゲームの回数
	 * @throws InstantiationException プレイヤークラスのインスタンス化に失敗したときにスローされます。
	 * @throws IllegalAccessException プレイヤークラスにアクセスできないときにスローされます。
	 * @throws ClassNotFoundException プレイヤークラスが存在しないときにスローされます。
	 * @throws IOException ログファイルの書き込みに失敗したときにスローされます。
	 */
	public void repeat()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		for (int i = 0; i < numberOfGames; i++) {
			AIWolfGame game = gameStarter.start();
			if (gameTotalizer != null) {
				gameTotalizer.setGame(game);
				gameTotalizer.setGameServer(gameStarter.getGameServer());
				gameTotalizer.tally();
			}
		}
	}

	/**
	 * {@code GameTotalizer}を設定します。
	 * @param gameTotalizer 勝敗などを集計するためのオブジェクト
	 */
	public void setGameTotalizer(GameTotalizer gameTotalizer) {
		this.gameTotalizer = gameTotalizer;
	}
}