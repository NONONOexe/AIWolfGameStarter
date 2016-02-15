package jp.ac.maslab.ando.aiwolf.starter;

/**
 * コマンドライン引数に渡されたオプションとその引数を保持するクラスです。
 * @author keisuke
 */
public class Block {
	private Option option;
	private String optionArgument;

	/**
	 * 新しくオプションとその引数を持つブロックを構築します。
	 * @param option オプション
	 * @param optionArgument オプション引数
	 */
	public Block(Option option, String optionArgument) {
		this.option = option;
		this.optionArgument = optionArgument;
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
	public String getOptionArgument() {
		return optionArgument;
	}

	/**
	 * このブロックを表す文字列を返します。
	 * <blockquote>
	 * {@code option.toString() + " : " + optionArgument}
	 * </blockquote>
	 * @return このブロックを表す文字列
	 */
	@Override
	public String toString() {
		return option.toString() + " : " + optionArgument;
	}
}
