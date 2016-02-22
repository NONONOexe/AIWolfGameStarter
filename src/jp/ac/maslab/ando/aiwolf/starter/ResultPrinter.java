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
		for (String playerName : playerTotalGameResultMap.keySet()) {
			PlayerTotalGameResult result = playerTotalGameResultMap.get(playerName);
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
			System.out.println("=============================================");
			System.out.println(playerName);
			System.out.println("順位: " + order + "位");
			System.out.println("---------------------------------------------");
			System.out.println("役職: 勝率(勝利回数/敗北回数)");
			System.out.println("---------------------------------------------");
			System.out.println("狩人: " + bodyguard);
			System.out.println("霊能: " + medium);
			System.out.println("占い: " + seer);
			System.out.println("村人: " + villager);
			System.out.println("---------------------------------------------");
			System.out.println("村人陣営: " + villagerTeam);
			System.out.println("---------------------------------------------");
			System.out.println("狂人: " + possessed);
			System.out.println("人狼: " + werewolf);
			System.out.println("---------------------------------------------");
			System.out.println("人狼陣営: " + werewolfTeam);
			System.out.println("---------------------------------------------");
			System.out.println("全体: " + total);
		}
	}

	/**
	 * 指定された役職のときの"勝率(勝利回数/敗北回数)"の文字列を返します。勝率は小数点第2位で四捨五入したものになります。
	 * @param result プレイヤーのゲーム結果
	 * @param role 役職
	 * @return "勝率(勝利回数/敗北回数)"の文字列
	 */
	private String createWinningPercentageString(PlayerTotalGameResult result, Role role) {
		try {
			double winPercentage = new BigDecimal(result.getWinningPercentage(role))
					.setScale(2, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
			return String.format("%s(%s/%s)", winPercentage,
					result.getWinningTimes(role), result.getLosingTimes(role));
		} catch (NumberFormatException e) {
			return String.format("%s(%s/%s)", " - ", result.getWinningTimes(role), result.getLosingTimes(role));
		}
	}

	/**
	 * 指定された陣営の"勝率(勝利回数/敗北回数)"の文字列を返します。勝率は小数点第2位で四捨五入したものになります。
	 * @param result プレイヤーのゲーム結果
	 * @param team 陣営
	 * @return "勝率(勝利回数/敗北回数)"の文字列
	 */
	private String createWinningPercentageString(PlayerTotalGameResult result, Team team) {
		try {
			double winPercentage = new BigDecimal(result.getWinningPercentage(team))
					.setScale(2, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
			return String.format("%s(%s/%s)", winPercentage,
					result.getWinningTimes(team), result.getLosingTimes(team));
		} catch (NumberFormatException e) {
			return String.format("%s(%s/%s)", " - ", result.getWinningTimes(team), result.getLosingTimes(team));
		}
	}

	/**
	 * 全役職合計の"勝率(勝利回数/敗北回数)"の文字列を返します。勝率は小数点第2位で四捨五入したものになります。
	 * @param result プレイヤーのゲーム結果
	 * @return "勝率(勝利回数/敗北回数)"の文字列
	 */
	private String createWinningPercentageString(PlayerTotalGameResult result) {
		try {
			double winPercentage = new BigDecimal(result.getWinningPercentage()).setScale(2, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
			return String.format("%s(%s/%s)", winPercentage,
					result.getWinningTimes(), result.getLosingTimes());
		} catch (NumberFormatException e) {
			return String.format("%s(%s/%s)", " - ", result.getWinningTimes(), result.getLosingTimes());
		}
	}
}
