package jp.ac.maslab.ando.aiwolf.starter;

import java.util.List;

import jp.ac.maslab.ando.aiwolf.starter.io.ConfigFileReader;
import jp.ac.maslab.ando.aiwolf.starter.io.PlayerFileReader;

/**
 * スターターの設定のクラスです。
 * @author keisuke
 */
public class StarterSetting {
	/**
	 * プレイヤーの数を示します。
	 */
	private int numberOfplayers;
	/**
	 * プレイヤーのクラス名と希望役職のリストを示します。
	 */
	private List<PlayerEntry> playerEntryList;
	/**
	 * 実行するゲームの回数を示します。
	 */
	private int numberOfgames;
	/**
	 * ログを保存するディレクトリのパスを示します。
	 */
	private String logDirectoryPath;
	/**
	 * デフォルトのプレイヤーのクラス名を示します。
	 */
	private String defaultPlayerClassName;

	/**
	 * 指定されたパスの設定ファイルを使用し、新しく設定を構築します。
	 * @param pathname 設定ファイルのパス
	 */
	public StarterSetting(String pathname) {
		ConfigFileReader configFileReader = new ConfigFileReader(pathname);
		PlayerFileReader playerFileReader = new PlayerFileReader(
				configFileReader.getEntryMap().get(ConfigItem.PLAYER_FILE_PATH));
		this.numberOfplayers = Integer.parseInt(configFileReader.getEntryMap().get(ConfigItem.NUMBER_OF_PLAYERS));
		this.playerEntryList = playerFileReader.getPlayerEntryList();
		this.numberOfgames = Integer.parseInt(configFileReader.getEntryMap().get(ConfigItem.NUMBER_OF_GAMES));
		this.logDirectoryPath = configFileReader.getEntryMap().get(ConfigItem.LOG_DIRECTORY_PATH);
		this.defaultPlayerClassName = configFileReader.getEntryMap().get(ConfigItem.DEFAULT_PLAYER_CLASS_NAME);
	}

	/**
	 * プレイヤーの数を返します。
	 * @return プレイヤーの数
	 */
	public int getNumberOfplayers() {
		return numberOfplayers;
	}

	/**
	 * プレイヤーのクラス名と希望役職のリストを返します。
	 * @return プレイヤーのクラス名と希望役職のリスト
	 */
	public List<PlayerEntry> getPlayerEntryList() {
		return playerEntryList;
	}

	/**
	 * 実行するゲームの回数を返します。
	 * @return 実行するゲームの回数
	 */
	public int getNumberOfgames() {
		return numberOfgames;
	}

	/**
	 * ログを保存するディレクトリのパスを返します。
	 * @return ログを保存するディレクトリのパス
	 */
	public String getLogDirectoryPath() {
		return logDirectoryPath;
	}

	/**
	 * デフォルトのプレイヤーのクラス名を返します。
	 * @return デフォルトのプレイヤーのクラス名
	 */
	public String getDefaultPlayerClassName() {
		return defaultPlayerClassName;
	}
}
