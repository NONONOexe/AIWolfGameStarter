package jp.ac.maslab.ando.aiwolf.starter;

/**
 * AIWolfGameSterter実行時のオプション名の列挙です。
 * @author keisuke
 *
 */
public enum Option {
	/**
	 * コンフィグファイルを設定するオプションです。
	 */
	CONFIG_FILE("-C"),
	/**
	 * バージョン名を表示するオプションです。
	 */
	VERSION("--version"),
	/**
	 * 使用方法(Usage)を表示するオプションです。
	 */
	HELP("--help");

	private String command;

	private Option(String command) {
		this.command = command;
	}

	/**
	 * このOptionのコマンド文字列を返します。
	 * @return オプションのコマンド文字列
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 指定されたコマンド文字列のOptionを返します。指定されたコマンドのオプションが存在しない場合はnullを返します。
	 * @param command コマンド文字列
	 * @return コマンド文字列のOption
	 */
	public static Option parseOption(String command) {
		Option option = null;
		for (Option value : Option.values()) {
			if (value.getCommand().equals(command)) {
				option = value;
				break;
			}
		}
		return option;
	}
}
