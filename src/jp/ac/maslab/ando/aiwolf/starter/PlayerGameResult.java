package jp.ac.maslab.ando.aiwolf.starter;

import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.data.Status;

/**
 * プレイヤーのゲーム結果を保持するクラスです。
 * @author keisuke
 */
public class PlayerGameResult {
	/**
	 * エージェントを示します。
	 */
	private Agent agent;
	/**
	 * 役職を示します。
	 */
	private Role role;
	/**
	 * 勝敗を示します。
	 */
	private WinOrLoss winOrLoss;
	/**
	 * 生死を示します。
	 */
	private Status status;

	/**
	 * 新しくプレイヤーの対戦結果を作ります。
	 * @param agent エージェント
	 * @param role 役職
	 * @param winOrLoss 勝敗
	 * @param status 生死
	 */
	public PlayerGameResult(Agent agent, Role role, WinOrLoss winOrLoss, Status status) {
		this.agent = agent;
		this.role = role;
		this.winOrLoss = winOrLoss;
		this.status = status;
	}

	/**
	 * エージェントを返します。
	 * @return エージェント
	 */
	public Agent getAgent() {
		return agent;
	}

	/**
	 * 役職を返します。
	 * @return 役職
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * 勝敗を返します。
	 * @return 勝敗
	 */
	public WinOrLoss getWinOrLoss() {
		return winOrLoss;
	}

	/**
	 * 生死を返します。
	 * @return 生死
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * この対戦結果を表す文字列を返します。
	 * @return この対戦結果を表す文字列
	 */
	@Override
	public String toString() {
		return "agent=" + agent + ", role=" + role + ", winOrLoss=" + winOrLoss + ", status=" + status;
	}
}
