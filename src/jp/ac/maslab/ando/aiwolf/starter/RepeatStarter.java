package jp.ac.maslab.ando.aiwolf.starter;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.aiwolf.common.data.Player;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.net.GameSetting;
import org.aiwolf.server.AIWolfGame;

import jp.ac.maslab.ando.aiwolf.starter.server.DirectConnectServer;

/**
 * 繰り返し実行を行うスターターです。
 * @author keisuke
 */
public class RepeatStarter {
	/**
	 * スターターの設定を示します。
	 */
	private StarterSetting starterSetting;

	/**
	 * 指定された設定で新しくスターターを構築します。
	 * @param setting スターターの設定
	 */
	public RepeatStarter(StarterSetting setting) {
		this.starterSetting = setting;
	}

	/**
	 * ゲームを実行します。
	 * @throws ClassNotFoundException プレイヤークラスが存在しないときにスローされます。
	 * @throws IllegalAccessException プレイヤークラスにアクセスできないときにスローされます。
	 * @throws InstantiationException プレイヤークラスのインスタンス化に失敗したときにスローされます。
	 * @throws IOException ログファイルの書き込みに失敗したときにスローされます。
	 */
	public void start() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		List<GameResult> gameResultList = new ArrayList<>();
		for (int i = 0; i < starterSetting.getNumberOfgames(); i++) {
			DirectConnectServer gameServer = new DirectConnectServer(createPlayerRoleMap());
			GameSetting gameSetting = GameSetting.getDefaultGame(starterSetting.getNumberOfplayers());
			AIWolfGame game = new AIWolfGame(gameSetting, gameServer);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String timeString = timestamp.toString().replaceAll("[\\s-/:.]", "");
			File logFile = new File(
					String.format("%s/aiwolfGame%s.log", starterSetting.getLogDirectoryPath(), timeString));
			game.setLogFile(logFile);
			game.setRand(new Random(gameSetting.getRandomSeed()));
			game.start();
			gameResultList.add(new GameResult(gameServer, game));
		}
		TotalGameAggregate totalGameAggregate = new TotalGameAggregate(gameResultList);
		totalGameAggregate.printResult();
	}

	/**
	 * プレイヤーに役職を関連付けたマップを作成し、返します。<br>
	 * プレイヤーの数に満たないときは残りのプレイヤーをデフォルトプレイヤーで埋めます。
	 * @return プレイヤーに役職を関連付けたマップ
	 * @throws ClassNotFoundException プレイヤークラスが存在しないときにスローされます。
	 * @throws IllegalAccessException プレイヤークラスにアクセスできないときにスローされます。
	 * @throws InstantiationException プレイヤークラスのインスタンス化に失敗したときにスローされます。
	 */
	private Map<Player, Role> createPlayerRoleMap()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Map<Player, Role> playerRoleMap = new HashMap<>();
		for (PlayerEntry playerEntry : starterSetting.getPlayerEntryList()) {
			Player player = (Player) Class.forName(playerEntry.getPlayerClassName()).newInstance();
			if (playerEntry.getSuggestedRole() != null) {
				playerRoleMap.put(player, Role.valueOf(playerEntry.getSuggestedRole()));
			} else {
				playerRoleMap.put(player, null);
			}
		}
		while (playerRoleMap.size() < starterSetting.getNumberOfplayers()) {
			playerRoleMap.put((Player) Class.forName(starterSetting.getDefaultPlayerClassName()).newInstance(), null);
		}
		return playerRoleMap;
	}
}
