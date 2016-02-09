package jp.ac.maslab.ando.aiwolf.starter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

/**
 * コンフィグファイルを読み込みます。
 * @author keisuke
 *
 */
public class ConfigFileReader {
	private String pathname;
	private Map<SettingItem, String> settingMap;

	private final String COMMENT = "#";
	private final String REGEX = "=";
	private final int ITEM = 0;
	private final int VALUE = 1;

	/**
	 * 指定されたパスのコンフィグファイルを読み込むリーダを作成します。
	 * @param pathname コンフィグファイルのパス名
	 */
	public ConfigFileReader(String pathname) {
		this.pathname = pathname;
		this.settingMap = createSettingMap();
	}

	private Map<SettingItem, String> createSettingMap() {
		Map<SettingItem, String> settingMap = new EnumMap<>(SettingItem.class);
		File file = new File(pathname);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				if (!line.startsWith(COMMENT)) {
					SettingItem item = SettingItem.valueOf(line.split(REGEX)[ITEM].toUpperCase());
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
