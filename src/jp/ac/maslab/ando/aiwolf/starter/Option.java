package jp.ac.maslab.ando.aiwolf.starter;

/**
 * オプションの列挙です。
 * @author keisuke
 */
public enum Option {
	/**
	 * 構成ファイル(Config File)を指定するオプションです。
	 */
	CONFIG_FILE_PATH("-C"),
	/**
	 * バージョンを表示するオプションです。
	 */
	VERSION("--version"),
	/**
	 * 使用方法(Usage)を表示するオプションです。
	 */
	HELP("--help");

	/**
	 * コマンドラインに入力する文字列を示します。
	 */
	private String command;

	/**
	 * 新規オプションを作成します。
	 * @param command コマンドラインに入力する文字列
	 */
	private Option(String command) {
		this.command = command;
	}

	/**
	 * 指定された文字列のコマンドに対応する{@code Option}を返します。
	 * @param command コマンドラインに入力する文字列
	 * @return {@code command}に対応する{@code Option}
	 */
	public static Option parseOption(String command) {
		Option option = null;
		for (Option item : Option.values()) {
			if (item.command.equals(command)) {
				option = item;
				break;
			}
		}
		return option;
	}
}
