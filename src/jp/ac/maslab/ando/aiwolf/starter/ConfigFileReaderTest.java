package jp.ac.maslab.ando.aiwolf.starter;

public class ConfigFileReaderTest {
	private static final String PATHNAME = "./default.cfg";

	public static void main(String[] args) {
		ConfigFileReader cl = new ConfigFileReader(PATHNAME);
		for (SettingItem item : cl.getSettingMap().keySet()) {
			System.out.printf("%s=%s\n", item.toString().toLowerCase(), cl.getSettingMap().get(item));
		}
	}
}
