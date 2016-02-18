package jp.ac.maslab.ando.aiwolf.starter;

import java.util.HashMap;
import java.util.Map;

import org.aiwolf.common.data.Role;
import org.aiwolf.common.data.Status;
import org.aiwolf.common.data.Team;

/**
 * プレイヤーの全ゲームの結果を保持するクラスです。
 * @author keisuke
 */
public class PlayerTotalGameResult {
	/**
	 * プレイヤー名を示します。
	 */
	private String playerName;
	/**
	 * 順位
	 */
	private int order;
	/**
	 * 役職ごとの勝利回数を保持します。
	 */
	private Map<Role, Integer> winningTimesMap;
	/**
	 * 役職ごとの敗北回数を保持します。
	 */
	private Map<Role, Integer> losingTimesMap;
	/**
	 * 役職ごとのゲーム終了時における生存回数を保持します。
	 */
	private Map<Role, Integer> aliveTimesMap;
	/**
	 * 役職ごとのゲーム終了時における死亡回数を保持します。
	 */
	private Map<Role, Integer> deadTimesMap;

	/**
	 * 新しくプレイヤーの全ゲームの結果を作成します。
	 */
	public PlayerTotalGameResult() {
		winningTimesMap = new HashMap<>();
		losingTimesMap = new HashMap<>();
		aliveTimesMap = new HashMap<>();
		deadTimesMap = new HashMap<>();
		for (Role role : Role.values()) {
			winningTimesMap.put(role, 0);
			losingTimesMap.put(role, 0);
			aliveTimesMap.put(role, 0);
			deadTimesMap.put(role, 0);
		}
	}

	/**
	 * 指定されたゲームの結果を追加します。
	 * @param playerGameResult ゲームの結果
	 */
	public void addTotalGameResult(PlayerGameResult playerGameResult) {
		Role role = playerGameResult.getRole();
		WinOrLoss winOrLoss = playerGameResult.getWinOrLoss();
		Status status = playerGameResult.getStatus();
		addWinOrLoss(role, winOrLoss);
		addStatus(role, status);
	}

	/**
	 * 勝敗の情報を追加します。
	 * @param role 役職
	 * @param winOrLoss 勝敗
	 */
	private void addWinOrLoss(Role role, WinOrLoss winOrLoss) {
		switch (winOrLoss) {
		case WIN:
			winningTimesMap.put(role, winningTimesMap.get(role) + 1);
			break;
		case LOSING:
			losingTimesMap.put(role, losingTimesMap.get(role) + 1);
			break;
		}
	}

	/**
	 * 状態の情報を追加します。
	 * @param role 役職
	 * @param status 状態
	 */
	private void addStatus(Role role, Status status) {
		switch (status) {
		case ALIVE:
			aliveTimesMap.put(role, aliveTimesMap.get(role) + 1);
			break;
		case DEAD:
			deadTimesMap.put(role, deadTimesMap.get(role) + 1);
			break;
		}
	}

	/**
	 * 指定された役職での勝利回数を返します。
	 * @param role 役職
	 * @return 指定された役職での勝利回数
	 */
	public int getWinningTimes(Role role) {
		return winningTimesMap.get(role);
	}

	/**
	 * 指定された陣営での勝利回数を返します。
	 * @param team 陣営
	 * @return 指定された陣営での勝利回数
	 */
	public int getWinningTimes(Team team) {
		int winningTimes = 0;
		for (Role role : winningTimesMap.keySet()) {
			if (role.getTeam().equals(team)) {
				winningTimes += winningTimesMap.get(role);
			}
		}
		return winningTimes;
	}

	/**
	 * 勝利回数を返します。
	 * @return 勝利回数
	 */
	public int getWinningTimes() {
		int winningTimes = 0;
		for (Role role : winningTimesMap.keySet()) {
			winningTimes += winningTimesMap.get(role);
		}
		return winningTimes;
	}

	/**
	 * 指定された役職での敗北回数を返します。
	 * @param role 役職
	 * @return 指定された役職での敗北回数
	 */
	public int getLosingTimes(Role role) {
		return losingTimesMap.get(role);
	}

	/**
	 * 指定された陣営での敗北回数を返します。
	 * @param team 陣営
	 * @return 指定された陣営での敗北回数
	 */
	public int getLosingTimes(Team team) {
		int losingTimes = 0;
		for (Role role : losingTimesMap.keySet()) {
			if (role.getTeam().equals(team)) {
				losingTimes += losingTimesMap.get(role);
			}
		}
		return losingTimes;
	}

	/**
	 * 敗北回数を返します。
	 * @return 敗北回数
	 */
	public int getLosingTimes() {
		int losingTimes = 0;
		for (Role role : losingTimesMap.keySet()) {
			losingTimes += losingTimesMap.get(role);
		}
		return losingTimes;
	}

	/**
	 * 指定された役職での生存回数を返します。
	 * @param role 役職
	 * @return 指定された役職での生存回数
	 */
	public int getAliveTimes(Role role) {
		return aliveTimesMap.get(role);
	}

	/**
	 * 指定された陣営での生存回数を返します。
	 * @param team 陣営
	 * @return 指定された陣営での生存回数
	 */
	public int getAliveTimes(Team team) {
		int aliveTimes = 0;
		for (Role role : aliveTimesMap.keySet()) {
			if (role.getTeam().equals(team)) {
				aliveTimes += aliveTimesMap.get(role);
			}
		}
		return aliveTimes;
	}

	/**
	 * 生存回数を返します。
	 * @return 生存回数
	 */
	public int getAliveTimes() {
		int aliveTimes = 0;
		for (Role role : aliveTimesMap.keySet()) {
			aliveTimes += aliveTimesMap.get(role);
		}
		return aliveTimes;
	}

	/**
	 * 指定された役職での死亡回数を返します。
	 * @param role 役職
	 * @return 指定された役職での死亡回数
	 */
	public int getDeadTimes(Role role) {
		return deadTimesMap.get(role);
	}

	/**
	 * 指定された陣営での死亡回数を返します。
	 * @param team 陣営
	 * @return 指定された陣営での死亡回数
	 */
	public int getDeadTimes(Team team) {
		int deadTimes = 0;
		for (Role role : aliveTimesMap.keySet()) {
			if (role.getTeam().equals(team)) {
				deadTimes += deadTimesMap.get(role);
			}
		}
		return deadTimes;
	}

	/**
	 * 死亡回数を返します。
	 * @return 死亡回数
	 */
	public int getDeadTimes() {
		int deadTimes = 0;
		for (Role role : deadTimesMap.keySet()) {
			deadTimes += deadTimesMap.get(role);
		}
		return deadTimes;
	}

	/**
	 * 指定された役職での勝率を返します。
	 * @param role 役職
	 * @return 指定された役職での勝率
	 */
	public double getWinningPercentage(Role role) {
		return (double) winningTimesMap.get(role) / (winningTimesMap.get(role) + losingTimesMap.get(role));
	}

	/**
	 * 指定された陣営での勝率を返します。
	 * @param team 陣営
	 * @return 指定された陣営での勝率
	 */
	public double getWinningPercentage(Team team) {
		return (double) getWinningTimes(team) / (getWinningTimes(team) + getLosingTimes(team));
	}

	/**
	 * 勝率を返します。
	 * @return 勝率
	 */
	public double getWinningPercentage() {
		return (double) getWinningTimes() / (getWinningTimes() + getLosingTimes());
	}

	/**
	 * 指定された役職での敗北率を返します。
	 * @param role 役職
	 * @return 指定された役職での勝率
	 */
	public double getLosingPercentage(Role role) {
		return (double) losingTimesMap.get(role) / (winningTimesMap.get(role) + losingTimesMap.get(role));
	}

	/**
	 * 指定された陣営での敗北率を返します。
	 * @param team 陣営
	 * @return 指定された陣営での敗北率
	 */
	public double getLosingPercentage(Team team) {
		return (double) getLosingTimes(team) / (getWinningTimes(team) + getLosingTimes(team));
	}

	/**
	 * 敗北率を返します。
	 * @return 敗北率
	 */
	public double getLosingPercentage() {
		return (double) getLosingTimes() / (getWinningTimes() + getLosingTimes());
	}

	/**
	 * 指定された役職の生存率を返します。
	 * @param role 役職
	 * @return 指定された役職での生存率
	 */
	public double getAlivePercentage(Role role) {
		return (double) aliveTimesMap.get(role) / (aliveTimesMap.get(role) + deadTimesMap.get(role));
	}

	/**
	 * 指定された陣営での生存率を返します。
	 * @param team 陣営
	 * @return 指定された陣営での生存率
	 */
	public double getAlivePercentage(Team team) {
		return (double) getAliveTimes(team) / (getAliveTimes(team) + getDeadTimes(team));
	}

	/**
	 * 生存率を返します。
	 * @return 生存率
	 */
	public double getAlivePercentage() {
		return (double) getAliveTimes() / (getAliveTimes() + getDeadTimes());
	}

	/**
	 * 指定された役職の死亡率を返します。
	 * @param role 役職
	 * @return 指定された役職での死亡率
	 */
	public double getDeadPercentage(Role role) {
		return (double) deadTimesMap.get(role) / (aliveTimesMap.get(role) + deadTimesMap.get(role));
	}

	/**
	 * 指定された陣営での死亡率を返します。
	 * @param team 陣営
	 * @return 指定された陣営での死亡率
	 */
	public double getDeadPercentage(Team team) {
		return (double) getDeadTimes(team) / (getAliveTimes(team) + getDeadTimes(team));
	}

	/**
	 * 死亡率を返します。
	 * @return 死亡率
	 */
	public double getDeadPercentage() {
		return (double) getDeadTimes() / (getAliveTimes() + getDeadTimes());
	}

	/**
	 * 順位を設定します。
	 * @param order 順位
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * 順位を返します。
	 * @return 順位
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * プレイヤー名を設定します。
	 * @param playerName プレイヤー名
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * プレイヤー名を返します。
	 * @return プレイヤー名
	 */
	public String getPlayerName() {
		return playerName;
	}
}
