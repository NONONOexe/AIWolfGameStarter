package jp.ac.maslab.ando.aiwolf.starter;

import java.util.ArrayList;
import java.util.List;

/**
 * スターターのコマンドライン引数を処理します。<br>
 * このクラスはコマンドライン引数をブロックに変換し、ブロック単位で処理を行います。
 * ここでのブロックというのはオプションとオプション引数を保持するクラス{@code Block}を指しています。
 * @author keisuke
 */
public class ArgumentsProcessor {
	/**
	 * コマンドライン引数から変換したブロックのリストを示します。
	 */
	private List<Block> blockList;
	/**
	 * 構成ファイルのパスを示します。
	 */
	private String configFilePath;
	/**
	 * デフォルトの構成ファイルのパスを示します。
	 */
	public static final String DEFAULT_CONFIG_PATH = "./default.cfg";

	/**
	 * 新しくコマンドライン引数を処理するためのプロセッサを構築します。
	 * @param arguments コマンドライン引数
	 */
	public ArgumentsProcessor(String[] arguments) {
		this.blockList = convertBlockList(arguments);
		this.configFilePath = createConfigFilePath();
	}

	/**
	 * コマンドライン引数をブロックのリストに変換します。
	 * @param arguments コマンドライン引数
	 * @return 変換したブロックのリスト
	 */
	private List<Block> convertBlockList(String[] arguments) {
		List<Block> blockList = new ArrayList<>();
		Option option = null;
		String optionArgument = null;
		for (String argument : arguments) {
			if (argument.startsWith("-")) {
				if (option != null) {
					blockList.add(new Block(option, optionArgument));
					optionArgument = null;
				}
				option = Option.parseOption(argument);
			} else {
				optionArgument = argument;
			}
		}
		blockList.add(new Block(option, optionArgument));
		return blockList;
	}

	/**
	 * 構成ファイルのパスを返します。<br>
	 * パスはブロックのリストを走査して返します。見つからなかった場合には、{@code DEFAULT_CONFIG_PATH}を返します。
	 * @return 構成ファイルのパス
	 */
	private String createConfigFilePath() {
		String configFilePath = DEFAULT_CONFIG_PATH;
		for (Block block : blockList) {
			if (block.getOption().equals(Option.CONFIG_FILE_PATH)) {
				configFilePath = block.getOptionArgument();
				break;
			}
		}
		return configFilePath;
	}

	/**
	 * コマンドライン引数から変換したブロックのリストを返します。
	 * @return ブロックのリスト
	 */
	public List<Block> getBlockList() {
		return blockList;
	}

	/**
	 * 設定ファイルのパス名文字列を返します。
	 * @return 設定ファイルのパス名文字列
	 */
	public String getConfigFilePathname() {
		return configFilePath;
	}

	/**
	 * 指定されたオプションがコマンドライン引数に含まれているかどうかを返します。
	 * @param option 含まれているかどうかを調べるオプション
	 * @return {@code option}がコマンドライン引数に含まれているかどうか
	 */
	public boolean contains(Option option) {
		boolean contains = false;
		for (Block block : blockList) {
			if (block.getOption().equals(option)) {
				contains = true;
				break;
			}
		}
		return contains;
	}

	/**
	 * ヘルプ(Usage)がコマンドライン引数に指定されていた場合にヘルプを表示してプログラムを終了します。
	 */
	public void checkHelp() {
		if (contains(Option.HELP)) {
			final String massage = "Usage:java AIWolfStarter\n"
					+ "-C config_file:use config file\n"
					+ "--version:print this starter's version\n"
					+ "--help:print this massage";
			System.out.println(massage);
			System.exit(0);
		}
	}

	/**
	 * バージョン情報がコマンドライン引数に指定されていた場合にバージョン情報を表示してプログラムを終了します。
	 */
	public void checkVersion() {
		if (contains(Option.VERSION)) {
			final String massage = "AIWolfStarter ver.0.1.2 spaghetti\n"
					+ "Roleover Date:2016/02/14\n"
					+ "Keisuke Ando";
			System.out.println(massage);
			System.exit(0);
		}
	}
}