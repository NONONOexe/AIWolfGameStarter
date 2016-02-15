package jp.ac.maslab.ando.aiwolf.starter;

import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Role;

/**
 * エージェントの対戦結果を保持するクラスです。
 * @author keisuke
 */
public class AgentResult {
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
	 * 新しくエージェントの対戦結果を作ります。
	 * @param agent エージェント
	 * @param role 役職
	 * @param winOrLoss 勝敗
	 */
	public AgentResult(Agent agent, Role role, WinOrLoss winOrLoss) {
		this.agent = agent;
		this.role = role;
		this.winOrLoss = winOrLoss;
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
	 * この対戦結果を表す文字列を返します。
	 * @return この対戦結果を表す文字列
	 */
	@Override
	public String toString() {
		return agent + " : " + role + " : " + winOrLoss;
	}
}
