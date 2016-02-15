package jp.ac.maslab.ando.aiwolf.starter;

import java.util.List;

import jp.ac.maslab.ando.aiwolf.starter.io.ConfigFileReader;
import jp.ac.maslab.ando.aiwolf.starter.io.PlayerFileReader;

/**
 * AIWolfGameStarterの設定です。
 * @author keisuke
 */
public class Config {
	private ArgumentsProcessor argsProcessor;
	private ConfigFileReader configFileReader;
	private PlayerFileReader playerFileReader;

	/**
	 * 指定されたArgsProcessorから新規設定を作ります。
	 * @param argsProcessor コマンドライン引数を処理するオブジェクト
	 */
	public Config(ArgumentsProcessor argsProcessor) {
		this.argsProcessor = argsProcessor;
		this.configFileReader = new ConfigFileReader(argsProcessor.getConfigFilePathname());
		this.playerFileReader = new PlayerFileReader(configFileReader.getEntryMap().get(ConfigItem.PLAYER_FILE_PATH));
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
		return Integer.valueOf(configFileReader.getEntryMap().get(ConfigItem.NUMBER_OF_PLAYERS));
	}

	/**
	 * プレイヤーのクラス名と希望役職を含む{@code PlayerEntry}のリストを返します。
	 * @return {@code PlayerEntry}のリスト
	 */
	public List<PlayerEntry> getPlayerEntryList() {
		return playerFileReader.getPlayerEntryList();
	}

	/**
	 * ゲームの実行回数を返します
	 * @return ゲームの実行回数
	 */
	public int getNumberOfGames() {
		return Integer.valueOf(configFileReader.getEntryMap().get(ConfigItem.NUMBER_OF_GAMES));
	}

	/**
	 * ログの保存先ディレクトリのパス名文字列を返します。
	 * @return ログのパス名
	 */
	public String getLogDirectory() {
		return configFileReader.getEntryMap().get(ConfigItem.LOG_DIRECTORY_PATH);
	}

	/**
	 * デフォルトのプレイヤーのクラス名を返します
	 * @return デフォルトのプレイヤーのクラス名
	 */
	public String getDefaultPlayer() {
		return configFileReader.getEntryMap().get(ConfigItem.DEFAULT_PLAYER_CLASS_NAME);
	}
}
