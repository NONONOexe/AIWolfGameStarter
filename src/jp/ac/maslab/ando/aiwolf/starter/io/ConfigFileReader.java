package jp.ac.maslab.ando.aiwolf.starter.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import jp.ac.maslab.ando.aiwolf.starter.ConfigItem;

/**
 * 設定ファイルを読み込むためのクラスです。
 * @author keisuke
 */
public class ConfigFileReader {
	/**
	 * 設定ファイルのパス名文字列を示します。
	 */
	private String configFilePath;
	/**
	 * 設定ファイルの項目とその値を関連付けたマップです。
	 */
	private Map<ConfigItem, String> entryMap;
	/**
	 * コメントの始まりを表すシンボルです。
	 */
	public static final String COMMENT = "#";
	/**
	 * 項目とその値を区切るシンボルです。
	 */
	public static final String REGEX = "=";
	/**
	 * 項目のフィールド番号です。
	 */
	public static final int CONFIG_ITEM = 0;
	/**
	 * 値のフィールド番号です。
	 */
	public static final int VALUE = 1;

	/**
	 * 指定されたパスのコンフィグファイルを読み込むリーダを作成します。
	 * @param configFilePath コンフィグファイルのパス名
	 */
	public ConfigFileReader(String configFilePath) {
		this.configFilePath = configFilePath;
		this.entryMap = createEntryMap();
	}

	/**
	 * 設定ファイルの項目にその値を関連付けたマップを作成します。
	 * @return 設定ファイルの項目にその値を関連付けたマップ
	 */
	private Map<ConfigItem, String> createEntryMap() {
		Map<ConfigItem, String> entryMap = new EnumMap<>(ConfigItem.class);
		File configFile = new File(configFilePath);
		try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				if (!line.startsWith(COMMENT) && line.matches("..*=..*")) {
					ConfigItem item = ConfigItem.valueOf(line.split(REGEX)[CONFIG_ITEM].toUpperCase());
					String value = line.split(REGEX)[VALUE];
					entryMap.put(item, value);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("default.cfgが見つかりません。このプログラムと同じ階層にdefault.cfgを置いてください。");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entryMap;
	}

	/**
	 * 設定ファイルの項目にその値を関連付けたマップを返します。
	 * @return 設定ファイルの項目にその値を関連付けたマップ
	 */
	public Map<ConfigItem, String> getEntryMap() {
		return entryMap;
	}
}
