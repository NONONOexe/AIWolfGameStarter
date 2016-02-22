package jp.ac.maslab.ando.aiwolf.starter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.data.Status;
import org.aiwolf.common.data.Team;
import org.aiwolf.server.AIWolfGame;

import jp.ac.maslab.ando.aiwolf.starter.server.DirectConnectServer;

/**
 * ゲーム結果を保持するクラスです。
 * @author keisuke
 */
public class GameResult {
	/**
	 * 勝利チームを示します。
	 */
	private Team winningTeam;
	/**
	 * プレイヤー名にそのプレイヤーの対戦結果を関連付けたマップです。
	 */
	private Map<String, PlayerGameResult> playerResultMap;

	/**
	 * 指定されたゲームサーバと人狼ゲームから新しく対戦結果を構築します。
	 * @param gameServer ゲームサーバ
	 * @param game 人狼ゲーム
	 */
	public GameResult(DirectConnectServer gameServer, AIWolfGame game) {
		this.winningTeam = game.getWinner();
		this.playerResultMap = new HashMap<>();
		Map<String, Integer> nameIdxMap = new HashMap<>();
		for (Agent agent : game.getGameData().getAgentList()) {
			String playerName = CompetitionAgentName.getPlayerName(gameServer.getPlayer(agent));
			if (playerResultMap.containsKey(playerName)) {
				if (nameIdxMap.containsKey(playerName)) {
					String newName = playerName + "(" + nameIdxMap.get(playerName) + ")";
					nameIdxMap.put(playerName, nameIdxMap.get(playerName) + 1);
					playerName = newName;
				} else {
					String newName = playerName + "(" + 2 + ")";
					nameIdxMap.put(playerName, 2);
					playerName = newName;
				}
			}
			Role role = game.getGameData().getRole(agent);
			WinOrLoss winOrLoss = game.getWinner().equals(role.getTeam()) ? WinOrLoss.WIN : WinOrLoss.LOSING;
			Status status = game.getGameData().getStatus(agent);
			PlayerGameResult playerResult = new PlayerGameResult(agent, role, winOrLoss, status);
			playerResultMap.put(playerName, playerResult);
		}
	}

	/**
	 * 勝利チームを返します。
	 * @return 勝利チーム
	 */
	public Team getWinningTeam() {
		return winningTeam;
	}

	/**
	 * プレイヤー名のリストを返します。
	 * @return プレイヤー名のリスト
	 */
	public List<String> getPlayerNameList() {
		return new ArrayList<>(playerResultMap.keySet());
	}

	/**
	 * 指定されたプレイヤーの対戦結果を返します。
	 * @param playerName プレイヤーの名前
	 * @return 指定されたプレイヤーの対戦結果
	 */
	public PlayerGameResult getPlayerGameResult(String playerName) {
		return playerResultMap.get(playerName);
	}
}
