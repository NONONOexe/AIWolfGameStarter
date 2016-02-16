package jp.ac.maslab.ando.aiwolf.starter;

import java.util.HashMap;
import java.util.Map;

import org.aiwolf.common.data.Role;
import org.aiwolf.common.data.Status;

/**
 * プレイヤーの全ゲームの結果を保持するクラスです。
 * @author keisuke
 */
public class PlayerTotalGameResult {
	/**
	 * 役職ごとの勝利回数を保持します。
	 */
	private Map<Role, Integer> winningTimesMap;
	/**
	 * 役職ごとの敗北回数を保持します。
	 */
	private Map<Role, Integer> losingTimesMap;
	/**
	 * ゲーム終了時の生死の回数を保持します。
	 */
	private Map<Status, Integer> statusTimesMap;

	/**
	 * 新しくプレイヤーの全ゲームの結果を作成します。
	 */
	public PlayerTotalGameResult() {
		initializeWinningTimesMap();
		initializeLosingTimesMap();
		initializeStatusTimesMap();
	}

	/**
	 * 勝利の回数のマップを初期化します。
	 * @return
	 */
	private void initializeWinningTimesMap() {
		this.winningTimesMap = new HashMap<>();
		for (Role role : Role.values()) {
			winningTimesMap.put(role, 0);
		}
	}

	/**
	 * 敗北の回数のマップを初期化します。
	 * @return
	 */
	private void initializeLosingTimesMap() {
		this.losingTimesMap = new HashMap<>();
		for (Role role : Role.values()) {
			losingTimesMap.put(role, 0);
		}
	}

	/**
	 * 生死の回数のマップを初期化します。
	 */
	private void initializeStatusTimesMap() {
		this.statusTimesMap = new HashMap<>();
		for (Status status : Status.values()) {
			this.statusTimesMap.put(status, 0);
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
		addStatus(status);
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
	 * @param status 状態
	 */
	private void addStatus(Status status) {
		statusTimesMap.put(status, statusTimesMap.get(status) + 1);
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
	 * 指定された役職での勝率を返します。
	 * @param role 役職
	 * @return 指定された役職での勝率
	 */
	public double getWinningPercentage(Role role) {
		return (double) winningTimesMap.get(role) / (winningTimesMap.get(role) + losingTimesMap.get(role));
	}
}
