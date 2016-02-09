package jp.ac.maslab.ando.aiwolf.starter;

/**
 * コマンドライン引数に渡されたオプションとその引数を保持するクラスです。
 * @author keisuke
 */
public class ArgBlock {
	private Option option;
	private String arg;

	/**
	 * 新規ブロックを構築します。
	 * @param option オプション
	 * @param arg オプション引数
	 */
	public ArgBlock(Option option, String arg) {
		this.option = option;
		this.arg = arg;
	}

	/**
	 * オプションを返します。
	 * @return オプション
	 */
	public Option getOption() {
		return option;
	}

	/**
	 * オプション引数を返します。
	 * @return オプション引数
	 */
	public String getArg() {
		return arg;
	}

	/**
	 * オプションの名前とオプション引数をコロンで区切った文字列を返します。
	 * @return オプション名とオプション引数をコロンで区切った文字列
	 */
	@Override
	public String toString() {
		return option.toString() + ":" + arg;
	}

	/**
	 * コマンドラインでの入力をそのまま返します。
	 * @return コマンドラインでの入力文字列
	 */
	public String toCommand() {
		return option.getCommand() + " " + arg;
	}
}
