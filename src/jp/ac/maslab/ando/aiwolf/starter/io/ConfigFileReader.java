package jp.ac.maslab.ando.aiwolf.starter.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import jp.ac.maslab.ando.aiwolf.starter.SettingItem;

/*
 * 2016/02/10 変数名の変更、JavaDocの追加、ファイルのエントリを正規表現で認識（改行を無視できるようになった）
 */
/**
 * コンフィグファイルを読み込みます。
 * @author keisuke
 */
public class ConfigFileReader {
	private String pathname;
	private Map<SettingItem, String> settingMap;
	/**
	 * コメントの始まりを表すシンボルです。
	 */
	public static final String COMMENT = "#";
	/**
	 * 設定項目とその値を区切るシンボルです。
	 */
	public static final String REGEX = "=";
	/**
	 * 設定項目のフィールド番号です。
	 */
	public static final int SETTING_ITEM = 0;
	/**
	 * 項目に対する値のフィールド番号です。
	 */
	public static final int VALUE = 1;

	/**
	 * 指定されたパスのコンフィグファイルを読み込むリーダを作成します。
	 * @param pathname コンフィグファイルのパス名
	 */
	public ConfigFileReader(String pathname) {
		this.pathname = pathname;
		this.settingMap = createSettingMap();
	}

	/**
	 * 設定項目にその値を関連付けたマップを作成します。
	 * @return 設定項目にその値を関連付けたマップ
	 */
	private Map<SettingItem, String> createSettingMap() {
		Map<SettingItem, String> settingMap = new EnumMap<>(SettingItem.class);
		File configFile = new File(pathname);
		try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				if (!line.startsWith(COMMENT) && line.matches("..*=..*")) {
					SettingItem item = SettingItem.valueOf(line.split(REGEX)[SETTING_ITEM].toUpperCase());
					String value = line.split(REGEX)[VALUE];
					settingMap.put(item, value);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return settingMap;
	}

	/**
	 * 設定項目にその値を関連付けたマップを返します。
	 * @return 設定項目にその値を関連付けたマップ
	 */
	public Map<SettingItem, String> getSettingMap() {
		return settingMap;
	}
}
