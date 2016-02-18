package jp.ac.maslab.ando.aiwolf.starter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 全ゲームの結果を集計するクラスです。
 * @author keisuke
 */
public class TotalGameAggregate {
	/**
	 * プレイヤーごとの全ゲームの結果を保持します。
	 */
	private Map<String, PlayerTotalGameResult> playerTotalGameResultMap;

	/**
	 * ゲームの結果のリストから集計データを作成します。
	 * @param gameResultList ゲームの結果のリスト
	 */
	public TotalGameAggregate(List<GameResult> gameResultList) {
		playerTotalGameResultMap = new HashMap<>();
		tally(gameResultList);
	}

	/**
	 * 指定されたゲームの結果のリストを集計します。
	 * @param gameResultList
	 */
	private void tally(List<GameResult> gameResultList) {
		for (GameResult gameResult : gameResultList) {
			for (String playerName : gameResult.getPlayerNameList()) {
				PlayerGameResult playerGameResult = gameResult.getPlayerGameResult(playerName);
				if (!playerTotalGameResultMap.containsKey(playerName)) {
					playerTotalGameResultMap.put(playerName, new PlayerTotalGameResult());
				}
				PlayerTotalGameResult playerTotalGameResult = playerTotalGameResultMap.get(playerName);
				playerTotalGameResult.addTotalGameResult(playerGameResult);
			}
		}
		Map<String, Double> winningPercentageMap = new TreeMap<>();
		for (String playerName : playerTotalGameResultMap.keySet()) {
			playerTotalGameResultMap.get(playerName).setPlayerName(playerName);
			winningPercentageMap.put(playerName, playerTotalGameResultMap.get(playerName).getWinningPercentage());
		}
		for (String playerName : playerTotalGameResultMap.keySet()) {
			playerTotalGameResultMap.get(playerName).setOrder(getOrder(playerName, winningPercentageMap));
		}
	}

	/**
	 * 勝率のマップから指定されたプレイヤーが何位かを返します。
	 * @param ownName プレイヤー名
	 * @param winningPercentageMap 勝率マップ
	 * @return プレイヤーの順位
	 */
	private int getOrder(String ownName, Map<String, Double> winningPercentageMap) {
		int order = 0;
		double ownPercentage = winningPercentageMap.get(ownName);
		for (String playerName : winningPercentageMap.keySet()) {
			if (ownPercentage <= winningPercentageMap.get(playerName)) {
				order++;
			}
		}
		return order;
	}

	/**
	 * プレイヤー名に各プレイヤーの全ゲームの結果を関連付けたマップを返します。
	 * @return プレイヤー名に各プレイヤーの全ゲームの結果を関連付けたマップ
	 */
	public Map<String, PlayerTotalGameResult> getPlayerTotalGameResultMap() {
		return playerTotalGameResultMap;
	}
}
