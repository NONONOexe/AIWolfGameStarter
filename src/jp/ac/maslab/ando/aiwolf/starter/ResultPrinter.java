package jp.ac.maslab.ando.aiwolf.starter;

import java.math.BigDecimal;
import java.util.Map;

import org.aiwolf.common.data.Role;
import org.aiwolf.common.data.Team;

public class ResultPrinter {
	/**
	 * プレイヤーごとの全ゲームの結果を保持するマップです。
	 */
	private Map<String, PlayerTotalGameResult> playerTotalGameResultMap;

	/**
	 * 指定されたゲームの結果を出力するためのオブジェクトを作成します。
	 * @param playerTotalGameResultMapプレイヤー名にプレイヤーの全ゲームの結果を関連付けたマップ
	 */
	public ResultPrinter(Map<String, PlayerTotalGameResult> playerTotalGameResultMap) {
		this.playerTotalGameResultMap = playerTotalGameResultMap;
	}

	/**
	 * コンソールでゲームの結果を表示します。
	 */
	public void showConsole() {
		System.out.printf("%-20s %-6s %-135s\n", "Player Name", "Order",
				"Winning Percentage(Winning Times/LosingTimes)");
		System.out.printf("%27s %-95s %-31s %-15s\n",
				"", "ROLE", "TEAM", "TOTAL");
		System.out.printf("%27s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
				"", "bodyguard", "medium", "possessed", "seer", "villager", "werewolf", "VILLAGER", "WEREWOLF");
		for (String playerName : playerTotalGameResultMap.keySet()) {
			PlayerTotalGameResult result = playerTotalGameResultMap.get(playerName);
			CompetitionAgentName name = CompetitionAgentName.getInstance();
			if (name.getAlias(playerName) != null) {
				playerName = name.getAlias(playerName);
			}
			int order = result.getOrder();
			String bodyguard = createWinningPercentageString(result, Role.BODYGUARD);
			String medium = createWinningPercentageString(result, Role.MEDIUM);
			String possessed = createWinningPercentageString(result, Role.POSSESSED);
			String seer = createWinningPercentageString(result, Role.SEER);
			String villager = createWinningPercentageString(result, Role.VILLAGER);
			String werewolf = createWinningPercentageString(result, Role.WEREWOLF);
			String villagerTeam = createWinningPercentageString(result, Team.VILLAGER);
			String werewolfTeam = createWinningPercentageString(result, Team.WEREWOLF);
			String total = createWinningPercentageString(result);
			System.out.printf("%-20s %-6s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
					playerName, order,
					bodyguard, medium, possessed, seer, villager, werewolf,
					villagerTeam, werewolfTeam, total);
		}
	}

	/**
	 * 指定された役職のときの"勝率(勝利回数/敗北回数)"の文字列を返します。勝率は小数点第2位で四捨五入したものになります。
	 * @param result プレイヤーのゲーム結果
	 * @param role 役職
	 * @return "勝率(勝利回数/敗北回数)"の文字列
	 */
	private String createWinningPercentageString(PlayerTotalGameResult result, Role role) {
		double winPercentage = new BigDecimal(result.getWinningPercentage(role)).setScale(2, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		return String.format("%s(%s/%s)", winPercentage,
				result.getWinningTimes(role), result.getLosingTimes(role));
	}

	/**
	 * 指定された陣営の"勝率(勝利回数/敗北回数)"の文字列を返します。勝率は小数点第2位で四捨五入したものになります。
	 * @param result プレイヤーのゲーム結果
	 * @param team 陣営
	 * @return "勝率(勝利回数/敗北回数)"の文字列
	 */
	private String createWinningPercentageString(PlayerTotalGameResult result, Team team) {
		double winPercentage = new BigDecimal(result.getWinningPercentage(team)).setScale(2, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		return String.format("%s(%s/%s)", winPercentage,
				result.getWinningTimes(team), result.getLosingTimes(team));
	}

	/**
	 * 全役職合計の"勝率(勝利回数/敗北回数)"の文字列を返します。勝率は小数点第2位で四捨五入したものになります。
	 * @param result プレイヤーのゲーム結果
	 * @return "勝率(勝利回数/敗北回数)"の文字列
	 */
	private String createWinningPercentageString(PlayerTotalGameResult result) {
		double winPercentage = new BigDecimal(result.getWinningPercentage()).setScale(2, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		return String.format("%s(%s/%s)", winPercentage,
				result.getWinningTimes(), result.getLosingTimes());
	}
}
