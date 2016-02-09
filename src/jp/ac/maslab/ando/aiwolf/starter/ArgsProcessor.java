package jp.ac.maslab.ando.aiwolf.starter;

import java.util.ArrayList;
import java.util.List;

/**
 * AIWolfGameStarterのコマンドライン引数を処理します。
 * @author keisuke
 */
public class ArgsProcessor {
	private String[] args;
	private List<ArgBlock> argBlockList;
	private String configPath;

	private final String DEFAULT_CONFIG = "./default.cfg";

	/**
	 * 新規プロセッサを構築します。
	 * @param args コマンドライン引数
	 */
	public ArgsProcessor(String[] args) {
		this.args = args;
		this.argBlockList = createArgBlockList();
		this.configPath = createConfigPath();
	}

	private List<ArgBlock> createArgBlockList() {
		List<ArgBlock> argBlockList = new ArrayList<>();
		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-")) {
				Option option = Option.parseOption(args[i]);
				String arg = null;
				i++;
				if (i < args.length && !args[i].startsWith("-")) {
					arg = args[i];
				} else {
					i--;
					arg = "true";
				}
				argBlockList.add(new ArgBlock(option, arg));
			}
		}
		return argBlockList;
	}

	private String createConfigPath() {
		String configPath = DEFAULT_CONFIG;
		for (ArgBlock argBlock : argBlockList) {
			if (argBlock.getOption().equals(Option.CONFIG_FILE)) {
				configPath = argBlock.getArg();
				break;
			}
		}
		return configPath;
	}

	/**
	 * {@code List<ArgBlock>}型にしたコマンドライン引数を返します。
	 * @return {@code List<ArgBlock>}型にしたコマンドライン引数
	 */
	public List<ArgBlock> getArgBlockList() {
		return argBlockList;
	}

	/**
	 * コンフィグファイルのパス名文字列を返します。
	 * @return コンフィグファイルのパス名文字列
	 */
	public String getConfigPath() {
		return configPath;
	}

	/**
	 * 指定されたオプションがコマンドライン引数に含まれているかどうかを返します。
	 * @param option オプション
	 * @return オプションがコマンドライン引数に含まれているかどうか
	 */
	public boolean contains(Option option) {
		boolean contains = false;
		for (ArgBlock argBlock : argBlockList) {
			if (argBlock.getOption().equals(option)) {
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
			final String massage = "AIWolfStarter ver.0.1.1 spaghetti\n"
					+ "Roleover Date:2016/02/07\n"
					+ "Keisuke Ando";
			System.out.println(massage);
			System.exit(0);
		}
	}
}