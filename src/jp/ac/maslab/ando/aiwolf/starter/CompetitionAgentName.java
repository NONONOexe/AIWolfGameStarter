package jp.ac.maslab.ando.aiwolf.starter;

import java.util.HashMap;
import java.util.Map;

import org.aiwolf.common.data.Player;

/**
 * 大会に参加したエージェントのチーム名を保持しているクラスです。
 * @author keisuke
 */
public class CompetitionAgentName {
	private CompetitionAgentName() {
	}

	private static class CompetionAgentNameHolder {
		private static final CompetitionAgentName instance = new CompetitionAgentName();
	}

	/**
	 * {@code CompetitionAgentName}のインスタンスを返します。
	 * @return {@code CompetitionAgentName}のインスタンス
	 */
	public static synchronized CompetitionAgentName getInstance() {
		return CompetionAgentNameHolder.instance;
	}

	private Map<String, String> teamNameMap;

	private void initializeClassPathTeamNameMap() {
		teamNameMap = new HashMap<>();
		teamNameMap.put("jp.halfmoon.inaba.aiwolf.strategyplayer.StrategyPlayer", "饂飩");
		teamNameMap.put("com.gmail.jinro.noppo.players.RoleAssignPlayer", "働きの悪い村");
		teamNameMap.put("org.aiwolf.Satsuki.LearningPlayer.AIWolfMain", "Satsuki");
		teamNameMap.put("jp.ac.shibaura_it.ma15082.WasabiRoleAssignPlayer", "wasabi");
		teamNameMap.put("takata.player.TakataRoleAssignPlayer", "GofukuLab");
		teamNameMap.put("ipa.myAgent.IPARoleAssignPlayer", "IPA");
		teamNameMap.put("org.aiwolf.iace10442.ChipRoleAssignPlayer", "iace10442");
		teamNameMap.put("com.github.haretaro.pingwo.role.PingwoRoleAssignPlayer", "平兀");
		teamNameMap.put("com.yy.player.YYRoleAssignPlayer", "Y・Y");
		teamNameMap.put("kainoueAgent.MyRoleAssignPlayer", "swingby");
		teamNameMap.put("jp.ac.aitech.k13009kk.aiwolf.client.player.AndoRoleAssignPlayer", "itolab");
		teamNameMap.put("com.gmail.the.seventh.layers.RoleAssignPlayer", "Team Fenrir");
		teamNameMap.put("jp.ac.cu.hiroshima.info.cm.nakamura.player.NoriRoleAssignPlayer", "中村人");
		teamNameMap.put("com.canvassoft.Agent.CanvasRoleAssignPlayer", "CanvasSoft");
		teamNameMap.put("com.gmail.octobersky.MyRoleAssignPlayer", "昼休みはいつも人狼でつぶれる");
		teamNameMap.put("jp.ac.cu.hiroshima.inaba.agent.InabaAgent", "chime");
		teamNameMap.put("net.kanolab.tminowa.MyRoleAssignPlayer", "KanoLab");
		teamNameMap.put("com.si.maekawa.MaekawaRoleAssignPlayer", "まえかわ");
		teamNameMap.put("mskdel.myAgent.mskdelRoleAssignPlayer", "mskdel");
		teamNameMap.put("aaaaa.aaaaaaa.aaaaa.Router", "——————–");
		teamNameMap.put("takelab.KumaAgent.KumaRoleAssignPlayer", "Takelab");
	}

	/**
	 * 指定されたクラスパスが第１回人狼知能大会＠CEDEC2015のエージェントのものであれば、そのチーム名を返します。
	 * そうでない場合はnullを返します。
	 * @param classPath クラスパス
	 * @return 指定されたクラスパスが登録されたチーム名。登録されていない場合はnull
	 */
	public String getTeamName(String classPath) {
		if (teamNameMap == null) {
			initializeClassPathTeamNameMap();
		}
		return teamNameMap.get(classPath);
	}

	/**
	 * 指定されたプレイヤーの名前を返します。第１回人狼知能大会＠CEDEC2015のエージェントを指定している場合は、そのチーム名を返します。
	 * プレイヤーに名前が設定されていない場合はプレイヤーのクラス名を返します。
	 * @param player プレイヤー
	 * @return プレイヤーの名前
	 */
	public static String getPlayerName(Player player) {
		CompetitionAgentName competitionAgentName = CompetitionAgentName.getInstance();
		String competitionName = competitionAgentName.getTeamName(player.getClass().getName());
		if (competitionName != null) {
			return competitionName;
		}
		String playerName = player.getName();
		if (playerName != null) {
			return playerName;
		}
		return player.getClass().getSimpleName();
	}
}
