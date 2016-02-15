package jp.ac.maslab.ando.aiwolf.starter;

public class ArgumentsProcessorTest {
	public static void main(String[] args) {
		String[] arguments = {"-C", "test.cfg"};
		ArgumentsProcessor ap = new ArgumentsProcessor(arguments);
		for (Block block : ap.getBlockList()) {
			System.out.println(block);
		}
	}
}
