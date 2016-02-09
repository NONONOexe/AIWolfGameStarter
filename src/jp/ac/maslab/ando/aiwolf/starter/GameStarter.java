package jp.ac.maslab.ando.aiwolf.starter;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.aiwolf.common.data.Player;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.net.GameSetting;
import org.aiwolf.server.AIWolfGame;

import jp.ac.maslab.ando.aiwolf.util.Pair;

/**
 * 人狼ゲームのスターターです。
 * @author keisuke
 *
 */
public class GameStarter {
	private String defaultClassName;
	private int numberOfPlayers;
	private List<Pair<String, Role>> playerRoleList;
	private String logDirectory;

	/**
	 * 新規スターターを構築します。
	 * @param config コンフィグ
	 */
	public GameStarter(Config config) {
		this.defaultClassName = config.getDefaultPlayer();
		this.numberOfPlayers = config.getNumberOfPlayers();
		this.playerRoleList = config.getPlayerRoleList();
		this.logDirectory = config.getLogDirectory();
	}

	/**
	 * 人狼ゲームを実行します。
	 * @throws InstantiationException プレイヤークラスのインスタンス化に失敗したときにスローされます。
	 * @throws IllegalAccessException プレイヤークラスにアクセスできないときにスローされます。
	 * @throws ClassNotFoundException プレイヤークラスが存在しないときにスローされます。
	 * @throws IOException ログファイルの書き込みに失敗したときにスローされます。
	 */
	public AIWolfGame start() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		Map<Player, Role> playerMap = toRoleMap(playerRoleList, numberOfPlayers);
		DirectConnectServer gameServer = new DirectConnectServer(playerMap);
		GameSetting gameSetting = GameSetting.getDefaultGame(numberOfPlayers);
		AIWolfGame game = new AIWolfGame(gameSetting, gameServer);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timeString =timestamp.toString().replaceAll("[\\s-/:.]", "");
		File logFile = new File(String.format("%s/aiwolfGame%s.log", logDirectory, timeString));
		game.setLogFile(logFile);
		game.setRand(new Random(gameSetting.getRandomSeed()));
		game.start();
		return game;
	}

	private Map<Player, Role> toRoleMap(List<Pair<String, Role>> playerList, int numberOfPlayers)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Map<Player, Role> roleMap = new HashMap<>();
		for (Pair<String, Role> pair : playerList) {
			Player player = (Player) Class.forName(pair.getKey()).newInstance();
			roleMap.put(player, pair.getValue());
		}
		while (roleMap.size() < numberOfPlayers) {
			roleMap.put((Player) Class.forName(defaultClassName).newInstance(), null);
		}
		return roleMap;
	}
}
