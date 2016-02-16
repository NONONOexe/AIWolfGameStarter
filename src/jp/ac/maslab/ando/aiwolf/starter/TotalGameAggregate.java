package jp.ac.maslab.ando.aiwolf.starter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aiwolf.common.data.Role;

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
	}

	// TODO
	public void printResult() {
		for (String playerName : playerTotalGameResultMap.keySet()) {
			PlayerTotalGameResult playerTotalGameResult = playerTotalGameResultMap.get(playerName);
			System.out.println(playerName);
			for (Role role : Role.values()) {
				System.out.println(role + ":" + playerTotalGameResult.getWinningPercentage(role));
			}
		}
	}
}
