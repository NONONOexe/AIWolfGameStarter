package jp.ac.maslab.ando.aiwolf.starter;

import java.util.HashMap;
import java.util.Map;

import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.data.Team;
import org.aiwolf.server.AIWolfGame;

import jp.ac.maslab.ando.aiwolf.starter.server.DirectConnectServer;

/**
 * 対戦結果を保持するクラスです。
 * @author keisuke
 */
public class GameResult {
	/**
	 * 勝利チームを示します。
	 */
	private Team winner;
	/**
	 * プレイヤー名にそのプレイヤーの対戦結果を関連付けたマップです。
	 */
	private Map<String, AgentResult> resultMap;

	/**
	 * 指定されたゲームサーバと人狼ゲームから新しく対戦結果を構築します。
	 * @param gameServer ゲームサーバ
	 * @param game 人狼ゲーム
	 */
	public GameResult(DirectConnectServer gameServer, AIWolfGame game) {
		this.winner = game.getWinner();
		this.resultMap = new HashMap<>();
		for (Agent agent : game.getGameData().getAgentList()) {
			String playerName = CompetitionAgentName.getPlayerName(gameServer.getPlayer(agent));
			Role role = game.getGameData().getRole(agent);
			WinOrLoss winOrLoss = (game.getWinner().equals(role.getTeam())) ? WinOrLoss.WIN : WinOrLoss.LOSE;
			AgentResult playerResult = new AgentResult(agent, role, winOrLoss);
			resultMap.put(playerName, playerResult);
		}
	}

	/**
	 * 勝利チームを返します。
	 * @return 勝利チーム
	 */
	public Team getWinner() {
		return winner;
	}

	/**
	 * プレイヤー名にそのプレイヤーの対戦結果を関連付けたマップを返します。
	 * @return プレイヤー名にそのプレイヤーの対戦結果を関連付けたマップ
	 */
	public Map<String, AgentResult> getResultMap() {
		return resultMap;
	}
}
