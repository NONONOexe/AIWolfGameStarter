package jp.ac.maslab.ando.aiwolf.starter.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.aiwolf.common.data.Role;

import jp.ac.maslab.ando.aiwolf.util.Pair;

/*
 * 2016/02/10 変数名の変更、JavaDocの追加、ファイルのエントリを正規表現で認識（改行を無視できるようになった）
 */
/**
 * プレイヤーファイルを読み込みます。
 * @author keisuke
 */
public class PlayerFileReader {
	private String pathname;
	private List<Pair<String, Role>> playerClassNameRoleList;
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
	public static final int PLAYERCLASSNAME = 0;
	/**
	 * 役職のフィールド番号です。
	 */
	public static final int ROLE = 1;
	/**
	 * フィールドの数です。
	 */
	public static final int FIELD_NUM = 2;

	/**
	 * 指定されたパスのプレイヤーファイルを読み込むリーダを作成します。
	 * @param pathname プレイヤーファイルのパス名
	 */
	public PlayerFileReader(String pathname) {
		this.pathname = pathname;
		this.playerClassNameRoleList = createPlayerClassNameRoleList();
	}

	/**
	 * プレイヤークラス名と役職のペアのリストを作成します。
	 * @return プレイヤークラス名と役職のペアのリスト
	 */
	private List<Pair<String, Role>> createPlayerClassNameRoleList() {
		List<Pair<String, Role>> playerClassNameRoleList = new ArrayList<>();
		File playerFile = new File(pathname);
		try (BufferedReader br = new BufferedReader(new FileReader(playerFile))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				if (!line.startsWith(COMMENT) && line.matches("..*:.*")) {
					Pair<String, Role> playerClassNameRolePair = toPlayerClassNameRolePair(line);
					playerClassNameRoleList.add(playerClassNameRolePair);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return playerClassNameRoleList;
	}

	/**
	 * 指定された文字列をプレイヤークラス名と役職のペアにして返します。
	 * @param str 読み込む文字列
	 * @return プレイヤークラス名と役職のペア
	 */
	private Pair<String, Role> toPlayerClassNameRolePair(String str) {
		Pair<String, Role> playerClassNameRolePair;
		String playerClassName = str.split(REGEX)[PLAYERCLASSNAME];
		Role role;
		if (str.split(REGEX).length < FIELD_NUM) {
			role = null;
		} else {
			role = Role.valueOf(str.split(REGEX)[ROLE].toUpperCase());
		}
		playerClassNameRolePair = new Pair<String, Role>(playerClassName, role);
		return playerClassNameRolePair;
	}

	/**
	 * プレイヤークラス名に役職を関連付けた{@code Pair<String,Role>}のリストを返します。<br>
	 * 役職が指定されていない場合はnullが関連付けられます。
	 * @return プレイヤークラス名に役職を関連付けた{@code Pair<String,Role>}のリスト
	 */
	public List<Pair<String, Role>> getPlayerClassNameRoleList() {
		return playerClassNameRoleList;
	}
}
