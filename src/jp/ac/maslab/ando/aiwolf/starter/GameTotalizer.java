package jp.ac.maslab.ando.aiwolf.starter;

import java.util.HashMap;
import java.util.Map;

import org.aiwolf.common.data.Agent;
import org.aiwolf.server.AIWolfGame;

import jp.ac.maslab.ando.aiwolf.starter.server.DirectConnectServer;

/**
 * 勝敗結果などを集計するクラスです。
 * @author keisuke
 */
public class GameTotalizer {
	private AIWolfGame game;
	private DirectConnectServer gameServer;

	private int villagerWinCount;
	private int werewolfWinCount;
	private Map<String, Integer> agentWinCountMap;

	public GameTotalizer() {
		this.game = null;
		this.gameServer = null;

		this.villagerWinCount = 0;
		this.werewolfWinCount = 0;
		this.agentWinCountMap = new HashMap<>();
	}

	/**
	 * 人狼ゲームを設定します。
	 * @param game 人狼ゲーム
	 */
	public void setGame(AIWolfGame game) {
		this.game = game;
	}

	/**
	 * ゲームサーバを設定します。
	 * @param gameServer ゲームサーバ
	 */
	public void setGameServer(DirectConnectServer gameServer) {
		this.gameServer = gameServer;
	}

	/**
	 * 現在保持しているデータを集計します。
	 */
	public void tally() {
		switch (game.getWinner()) {
		case VILLAGER:
			villagerWinCount++;
			break;
		case WEREWOLF:
			werewolfWinCount++;
			break;
		}
		for (Agent agent : game.getGameData().getAgentList()) {
			String agentName = gameServer.requestName(agent);
			if (game.getGameData().getFinalGameInfo(agent).getRole().getTeam().equals(game.getWinner())) {
				if (agentWinCountMap.get(agentName) != null) {
					agentWinCountMap.put(agentName, agentWinCountMap.get(agentName) + 1);
				} else {
					agentWinCountMap.put(agentName, 1);
				}
			}
		}
	}

	public void printResult() {
		System.out.printf("Number of Teams Win\n");
		System.out.printf("Villager : %d\n", villagerWinCount);
		System.out.printf("Werewolf : %d\n", werewolfWinCount);
		System.out.printf("Number of Agents Win\n");
		for (String agentName : agentWinCountMap.keySet()) {
			System.out.printf("%s : %d\n", agentName, agentWinCountMap.get(agentName));
		}
	}
}
