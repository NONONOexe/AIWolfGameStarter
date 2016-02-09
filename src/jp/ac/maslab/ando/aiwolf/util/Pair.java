package jp.ac.maslab.ando.aiwolf.util;

/**
 * 2つの値をペアにして使用するクラスです。
 * @author keisuke
 *
 * @param <K> キー
 * @param <V> 値
 */
public class Pair<K, V> {
	private K key;
	private V value;

	/**
	 * 新しくペアを構築します。
	 * @param key キー
	 * @param value 値
	 */
	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * このペアのキーを返します。
	 * @return キー
	 */
	public K getKey() {
		return this.key;
	}

	/**
	 * このペアのキーを設定します。
	 * @param key キー
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * このペアの値を返します。
	 * @return 値
	 */
	public V getValue() {
		return this.value;
	}

	/**
	 * このペアの値を設定します。
	 * @param value 値
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * キーと値をカンマで区切った文字列を返します。
	 * <blockquote>{@code key + ", " + value}</blockquote>
	 * @return キーと値をカンマで区切った文字列
	 */
	@Override
	public String toString() {
		return key + ", " + value;
	}
}
