package jp.ac.maslab.ando.aiwolf.starter;

import org.aiwolf.common.data.Role;

import jp.ac.maslab.ando.aiwolf.util.Pair;

public class PlayerFileReaderTest {
	private static final String PATHNAME = "./player";

	public static void main(String[] args) {
		PlayerFileReader pfr = new PlayerFileReader(PATHNAME);
		for (Pair<String, Role> playerRole : pfr.getPlayerRoleList()) {
			System.out.printf("%-70s : %-10s\n", playerRole.getKey(), playerRole.getValue());
		}
	}
}
