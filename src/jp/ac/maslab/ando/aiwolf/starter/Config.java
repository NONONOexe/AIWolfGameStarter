package jp.ac.maslab.ando.aiwolf.starter;

import java.util.List;

import org.aiwolf.common.data.Role;

import jp.ac.maslab.ando.aiwolf.starter.io.ConfigFileReader;
import jp.ac.maslab.ando.aiwolf.starter.io.PlayerFileReader;
import jp.ac.maslab.ando.aiwolf.util.Pair;

/**
 * AIWolfGameStarterの設定です。
 * @author keisuke
 */
public class Config {
	private ArgsProcessor argsProcessor;
	private ConfigFileReader configFileReader;
	private PlayerFileReader playerFileReader;
	private int numberOfPlayers;
	private List<Pair<String, Role>> playerRoleList;
	private int numberOfGames;
	private String logDirectory;
	private String defaultPlayer;

	/**
	 * 指定されたArgsProcessorから新規設定を作ります。
	 * @param argsProcessor コマンドライン引数を処理するオブジェクト
	 */
	public Config(ArgsProcessor argsProcessor) {
		this.argsProcessor = argsProcessor;
		this.configFileReader = new ConfigFileReader(argsProcessor.getConfigPath());
		this.playerFileReader = new PlayerFileReader(configFileReader.getSettingMap().get(SettingItem.PLAYER_FILE));
		this.numberOfPlayers = Integer.parseInt(configFileReader.getSettingMap().get(SettingItem.NUMBER_OF_PLAYERS));
		this.playerRoleList = playerFileReader.getPlayerClassNameRoleList();
		this.numberOfGames = Integer.parseInt(configFileReader.getSettingMap().get(SettingItem.NUMBER_OF_GAMES));
		this.logDirectory = configFileReader.getSettingMap().get(SettingItem.LOG_DIRECTORY);
		this.defaultPlayer = configFileReader.getSettingMap().get(SettingItem.DEFAULT_PLAYER);
	}

	/**
	 * ヘルプ(Usage)やバージョン情報のようなメッセージを表示してプログラムを終了するようなオプションが指定されていないかを確認します。<br>
	 * 指定されていた場合にはメッセージを表示して、プログラムを終了します。
	 */
	public void checkExitArg() {
		argsProcessor.checkHelp();
		argsProcessor.checkVersion();
	}

	/**
	 * プレイヤーの数を返します。
	 * @return プレイヤーの数
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	/**
	 * プレイヤークラスに役職を関連付けたリストを返します。<br>
	 * 役職は{@code Pair<String,Role>}クラスによって関連付けられます。指定されていない場合はnullが関連付けられます。
	 * @return プレイヤークラスに役職を関連付けたリスト
	 */
	public List<Pair<String, Role>> getPlayerRoleList() {
		return playerRoleList;
	}

	/**
	 * ゲームの実行回数を返します
	 * @return ゲームの実行回数
	 */
	public int getNumberOfGames() {
		return numberOfGames;
	}

	/**
	 * ログの保存先ディレクトリのパス名文字列を返します。
	 * @return ログのパス名
	 */
	public String getLogDirectory() {
		return logDirectory;
	}

	/**
	 * デフォルトプレイヤークラス名を返します
	 * @return デフォルトプレイヤークラス名
	 */
	public String getDefaultPlayer() {
		return defaultPlayer;
	}
}
