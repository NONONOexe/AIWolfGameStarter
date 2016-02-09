package jp.ac.maslab.ando.aiwolf.starter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Player;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.net.GameSetting;
import org.aiwolf.server.GameData;
import org.aiwolf.server.net.GameServer;

import jp.ac.maslab.ando.aiwolf.util.Pair;

public class DirectConnectServer implements GameServer {
	//private Map<Agent, Player> agentPlayerMap;
	//private Map<Player, Agent> playerAgentMap;
	//private Map<Agent, Role> requestRoleMap;
	private Map<Agent, Pair<Player, Role>> agentPlayerMap;
	private GameData gameData;
	private GameSetting gameSetting;

	public DirectConnectServer(Map<Player, Role> playerMap) {
		agentPlayerMap = new LinkedHashMap<>();
		int idx = 1;
		for (Player player : playerMap.keySet()) {
			Agent agent = Agent.getAgent(idx);
			idx++;
			agentPlayerMap.put(agent, new Pair<Player, Role>(player, playerMap.get(player)));
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
		Player player = agentPlayerMap.get(agent).getKey();
		player.initialize(gameData.getGameInfo(agent), gameSetting.clone());
	}

	@Override
	public String requestName(Agent agent) {
		Player player = agentPlayerMap.get(agent).getKey();
		String name = player.getName();
		if (name == null) {
			name = player.getClass().getSimpleName();
		}
		return name;
	}

	@Override
	public Role requestRequestRole(Agent agent) {
		return agentPlayerMap.get(agent).getValue();
	}

	@Override
	public void dayStart(Agent agent) {
		Player player = agentPlayerMap.get(agent).getKey();
		player.update(gameData.getGameInfo(agent));
		player.dayStart();
	}

	@Override
	public void dayFinish(Agent agent) {
		Player player = agentPlayerMap.get(agent).getKey();
		player.update(gameData.getGameInfo(agent));
	}

	@Override
	public String requestTalk(Agent agent) {
		Player player = agentPlayerMap.get(agent).getKey();
		player.update(gameData.getGameInfo(agent));
		String talk = player.talk();
		return talk;
	}

	@Override
	public String requestWhisper(Agent agent) {
		Player player = agentPlayerMap.get(agent).getKey();
		player.update(gameData.getGameInfo(agent));
		String whisper = player.whisper();
		return whisper;
	}

	@Override
	public Agent requestVote(Agent agent) {
		Player player = agentPlayerMap.get(agent).getKey();
		player.update(gameData.getGameInfo(agent));
		Agent target = player.vote();
		return target;
	}

	@Override
	public Agent requestDivineTarget(Agent agent) {
		Player player = agentPlayerMap.get(agent).getKey();
		player.update(gameData.getGameInfo(agent));
		Agent target = player.divine();
		return target;
	}

	@Override
	public Agent requestGuardTarget(Agent agent) {
		Player player = agentPlayerMap.get(agent).getKey();
		player.update(gameData.getGameInfo(agent));
		Agent target = player.guard();
		return target;
	}

	@Override
	public Agent requestAttackTarget(Agent agent) {
		Player player = agentPlayerMap.get(agent).getKey();
		player.update(gameData.getGameInfo(agent));
		Agent target = player.attack();
		return target;
	}

	@Override
	public void finish(Agent agent) {
		Player player = agentPlayerMap.get(agent).getKey();
		player.update(gameData.getFinalGameInfo(agent));
		player.finish();
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
		Agent agent = null;
		for (Entry<Agent, Pair<Player, Role>> entry : agentPlayerMap.entrySet()) {
			if (player.equals(entry.getValue().getKey())) {
				agent = entry.getKey();
				break;
			}
		}
		return agent;
	}
}
