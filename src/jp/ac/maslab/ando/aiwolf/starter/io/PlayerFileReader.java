package jp.ac.maslab.ando.aiwolf.starter.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.ac.maslab.ando.aiwolf.starter.PlayerEntry;

/**
 * プレイヤーファイルを読み込みます。
 * @author keisuke
 */
public class PlayerFileReader {
	/**
	 * プレイヤーファイルのパス名文字列を示します。
	 */
	private String pathname;
	/**
	 * プレイヤーファイルから読みこんだ{@code PlayerEntry}のリストを示します。
	 */
	private List<PlayerEntry> playerEntryList;
	/**
	 * コメントの始まりを表すシンボルです。
	 */
	public static final String COMMENT = "#";
	/**
	 * プレイヤーのクラス名と役職を区切るシンボルです。
	 */
	public static final String REGEX = ":";
	/**
	 * プレイヤークラス名のフィールド番号です。
	 */
	public static final int PLAYER_CLASS_NAME = 0;
	/**
	 * 役職のフィールド番号です。
	 */
	public static final int SUGGESTED_ROLE = 1;

	/**
	 * 指定されたパスのプレイヤーファイルを読み込むリーダを作成します。
	 * @param pathname プレイヤーファイルのパス名
	 */
	public PlayerFileReader(String pathname) {
		this.pathname = pathname;
		this.playerEntryList = createPlayerEntryList();
	}

	/**
	 * プレイヤーファイルから{@code PlayerEntry}のリストを作成します。
	 * @return {@code PlayerEntry}のリスト
	 */
	private List<PlayerEntry> createPlayerEntryList() {
		List<PlayerEntry> playerEntryList = new ArrayList<>();
		File playerFile = new File(pathname);
		try (BufferedReader br = new BufferedReader(new FileReader(playerFile))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				if (!line.startsWith(COMMENT) && line.matches("..*:.*")) {
					PlayerEntry playerEntry = convertPlayerEntry(line);
					playerEntryList.add(playerEntry);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return playerEntryList;
	}

	/**
	 * 指定された文字列を{@code PlayerEntry}に変換します。
	 * @param string 文字列
	 * @return 変換した{@code PlayerEntry}
	 */
	private PlayerEntry convertPlayerEntry(String string) {
		String playerClassName = string.split(REGEX)[PLAYER_CLASS_NAME];
		String suggestedRole = null;
		if (SUGGESTED_ROLE < string.split(REGEX).length) {
			suggestedRole = string.split(REGEX)[SUGGESTED_ROLE];
		}
		return new PlayerEntry(playerClassName, suggestedRole);
	}

	/**
	 * プレイヤーファイルから読みこんだ{@code PlayerEntry}のリストを返します。
	 * @return プレイヤーファイルから読みこんだ{@code PlayerEntry}のリスト
	 */
	public List<PlayerEntry> getPlayerEntryList() {
		return playerEntryList;
	}
}
