package jp.ac.maslab.ando.aiwolf.starter;

/**
 * プレイヤーファイルのエントリです。プレイヤーのクラス名と希望役職を保持します。
 * @author keisuke
 *
 */
public class PlayerEntry {
	/**
	 * プレイヤーのクラス名を示します。
	 */
	private String playerClassName;
	/**
	 * 希望する役職を示します。
	 */
	private String suggestedRole;

	/**
	 * 新規エントリを作成します。
	 * @param playerClassName プレイヤーのクラス名
	 * @param suggestedRole 希望する役職
	 */
	public PlayerEntry(String playerClassName, String suggestedRole) {
		this.playerClassName = playerClassName;
		this.suggestedRole = suggestedRole;
	}

	/**
	 * プレイヤーのクラス名を返します。
	 * @return プレイヤーのクラス名
	 */
	public String getPlayerClassName() {
		return playerClassName;
	}

	/**
	 * 希望する役職を返します
	 * @return 希望する役職
	 */
	public String getSuggestedRole() {
		return suggestedRole;
	}

	/**
	 * このエントリの内容を文字列で返します。</br>
	 * 次の文字列を返します。<br>
	 * <blockquote>
	 * {@code playerClassName + " : " + suggestedRole}
	 * </blockquote>
	 * @return このエントリの内容に適した文字列
	 */
	@Override
	public String toString() {
		return playerClassName + " : " + suggestedRole;
	}
}
