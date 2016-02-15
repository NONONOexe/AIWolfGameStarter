package jp.ac.maslab.ando.aiwolf.starter.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Player;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.net.GameSetting;
import org.aiwolf.server.GameData;
import org.aiwolf.server.net.GameServer;

public class DirectConnectServer implements GameServer {
	private Map<Agent, Player> agentPlayerMap;
	private Map<Player, Agent> playerAgentMap;
	private Map<Agent, Role> agentRequestRoleMap;
	private GameData gameData;
	private GameSetting gameSetting;

	public DirectConnectServer(Map<Player, Role> playerRoleMap) {
		agentPlayerMap = new LinkedHashMap<>();
		playerAgentMap = new LinkedHashMap<>();
		agentRequestRoleMap = new HashMap<>();

		int idx = 1;
		for (Player player : playerRoleMap.keySet()) {
			Agent agent = Agent.getAgent(idx);
			agentPlayerMap.put(agent, player);
			playerAgentMap.put(player, agent);
			agentRequestRoleMap.put(agent, playerRoleMap.get(player));
			idx++;
		}
	}

	@Override
	public List<Agent> getConnectedAgentList() {
		return new ArrayList<>(agentPlayerMap.keySet());
	}

	@Override
	public void setGameData(GameData gameData) {
		this.gameData = gameData;
	}

	@Override
	public void setGameSetting(GameSetting gameSetting) {
		this.gameSetting = gameSetting;
	}

	@Override
	public void init(Agent agent) {
		agentPlayerMap.get(agent).initialize(gameData.getGameInfo(agent), gameSetting.clone());
	}

	@Override
	public String requestName(Agent agent) {
		String name = agentPlayerMap.get(agent).getName();
		if (name == null) {
			name = agentPlayerMap.get(agent).getClass().getSimpleName();
		}
		return name;
	}

	@Override
	public Role requestRequestRole(Agent agent) {
		return agentRequestRoleMap.get(agent);
	}

	@Override
	public void dayStart(Agent agent) {
		agentPlayerMap.get(agent).update(gameData.getGameInfo(agent));
		agentPlayerMap.get(agent).dayStart();
	}

	@Override
	public void dayFinish(Agent agent) {
		agentPlayerMap.get(agent).update(gameData.getGameInfo(agent));
	}

	@Override
	public String requestTalk(Agent agent) {
		agentPlayerMap.get(agent).update(gameData.getGameInfo(agent));
		String talk = agentPlayerMap.get(agent).talk();
		return talk;
	}

	@Override
	public String requestWhisper(Agent agent) {
		agentPlayerMap.get(agent).update(gameData.getGameInfo(agent));
		String whisper = agentPlayerMap.get(agent).whisper();
		return whisper;
	}

	@Override
	public Agent requestVote(Agent agent) {
		agentPlayerMap.get(agent).update(gameData.getGameInfo(agent));
		Agent target = agentPlayerMap.get(agent).vote();
		return target;
	}

	@Override
	public Agent requestDivineTarget(Agent agent) {
		agentPlayerMap.get(agent).update(gameData.getGameInfo(agent));
		Agent target = agentPlayerMap.get(agent).divine();
		return target;
	}

	@Override
	public Agent requestGuardTarget(Agent agent) {
		agentPlayerMap.get(agent).update(gameData.getGameInfo(agent));
		Agent target = agentPlayerMap.get(agent).guard();
		return target;
	}

	@Override
	public Agent requestAttackTarget(Agent agent) {
		agentPlayerMap.get(agent).update(gameData.getGameInfo(agent));
		Agent target = agentPlayerMap.get(agent).attack();
		return target;
	}

	@Override
	public void finish(Agent agent) {
		agentPlayerMap.get(agent).update(gameData.getGameInfo(agent));
		agentPlayerMap.get(agent).finish();
	}

	@Override
	public void close() {
	}

	/**
	 * 指定されたプレイヤーに対応するエージェントを返します。
	 * @param player プレイヤー
	 * @return 指定されたプレイヤーに対応するエージェント
	 */
	public Agent getAgent(Player player) {
		return playerAgentMap.get(player);
	}

	/**
	 * 指定されたエージェントに対応するプレイヤーを返します。
	 * @param agent エージェント
	 * @return 指定されたエージェントに対応するプレイヤー
	 */
	public Player getPlayer(Agent agent) {
		return agentPlayerMap.get(agent);
	}
}
