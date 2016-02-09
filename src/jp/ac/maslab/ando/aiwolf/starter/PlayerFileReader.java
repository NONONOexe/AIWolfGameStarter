package jp.ac.maslab.ando.aiwolf.starter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.aiwolf.common.data.Role;

import jp.ac.maslab.ando.aiwolf.util.Pair;

/**
 * プレイヤーファイルを読み込みます。
 * @author keisuke
 */
public class PlayerFileReader {
	private String pathname;
	private List<Pair<String, Role>> playerRoleList;

	private final String COMMENT = "#";
	private final String REGEX = ":";
	private final int FIELD_NUM = 2;
	private final int CLASSNAME = 0;
	private final int ROLE = 1;

	/**
	 * 指定されたパスのプレイヤーファイルを読み込むリーダを作成します。
	 * @param pathname プレイヤーファイルのパス名
	 */
	public PlayerFileReader(String pathname) {
		this.pathname = pathname;
		this.playerRoleList = createPlayerRoleList();
	}

	private List<Pair<String, Role>> createPlayerRoleList() {
		List<Pair<String, Role>> playerRoleList = new ArrayList<>();
		File file = new File(pathname);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				Pair<String, Role> playerRolePair = toPlayerRolePair(line);
				if (playerRolePair != null) {
					playerRoleList.add(playerRolePair);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return playerRoleList;
	}

	private Pair<String, Role> toPlayerRolePair(String line) {
		Pair<String, Role> playerRolePair;
		if (line.startsWith(COMMENT)) {
			playerRolePair = null;
		} else {
			String classname = line.split(REGEX)[CLASSNAME];
			Role role;
			if (line.split(REGEX).length < FIELD_NUM) {
				role = null;
			} else {
				role = Role.valueOf(line.split(REGEX)[ROLE].toUpperCase());
			}
			playerRolePair = new Pair<String, Role>(classname, role);
		}
		return playerRolePair;
	}

	/**
	 * プレイヤークラスに役職を関連付けたリストを返します。<br>
	 * 役職は{@code Pair<String,Role>}クラスによって関連付けられます。指定されていない場合はnullが関連付けられます。
	 * @return プレイヤークラスに役職を関連付けたリスト
	 */
	public List<Pair<String, Role>> getPlayerRoleList() {
		return playerRoleList;
	}
}
