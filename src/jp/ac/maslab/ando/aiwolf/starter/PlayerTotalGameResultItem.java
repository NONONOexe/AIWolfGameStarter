package jp.ac.maslab.ando.aiwolf.starter;

/**
 * プレイヤーの全ゲームの結果に対する項目の列挙です。
 * @author keisuke
 */
public enum PlayerTotalGameResultItem {
	/**
	 * プレイヤーの名前を示します。
	 */
	PLAYER_NAME,
	/**
	 * 勝利回数を示します。
	 */
	WINNING_TIMES,
	/**
	 * 敗北回数を示します。
	 */
	LOSING_TIMES,
	/**
	 * 勝率を示します。
	 */
	WIN_PROBABILITY,
	/**
	 * 村陣営への所属回数を示します。
	 */
	AFFILIATION_TIMES_TO_VILLAGER_TEAM,
	/**
	 * 人狼陣営への所属回数を示します。
	 */
	AFFILIATION_TIMES_TO_WEREWOLF_TEAM,
	/**
	 * 村陣営での勝利回数を示します。
	 */
	WINNING_TIMES_IN_VILLAGER_TEAM,
	/**
	 * 村陣営での勝率を示します。
	 */
	WINNING_PERCENTAGE_IN_VILLAGER_TEAM,
	/**
	 * 村陣営での敗北回数を示します。
	 */
	LOSING_TIMES_IN_VILLAGER_TEAM,
	/**
	 * 人狼陣営の勝利回数を示します。
	 */
	WINNING_TIMES_IN_WEREWOLF_TEAM,
	/**
	 * 人狼陣営の敗北回数を示します。
	 */
	LOSING_TIMES_IN_WEREWOLF_TEAM,
	/**
	 * 人狼陣営での勝率を示します。
	 */
	WINNING_PERCENTAGE_IN_WEREWOLF_TEAM,
	/**
	 * 順位を示します。
	 */
	ORDER,
	/**
	 * 生存回数を示します。
	 */
	EXISTENCE_TIMES,
	/**
	 * 死亡回数を示します。
	 */
	DEATH_TIMES,
	/**
	 * 生存率を示します。
	 */
	EXISTENCE_RATE,
	WinningPercentageInWerewolf
}
