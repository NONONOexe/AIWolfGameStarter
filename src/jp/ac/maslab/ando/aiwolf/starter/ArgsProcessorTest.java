package jp.ac.maslab.ando.aiwolf.starter;

public class ArgsProcessorTest {
	public static void main(String[] args) {
		String[] test = {"-C", "test.cfg", "--version", "--help"};
		ArgsProcessor ap = new ArgsProcessor(test);
		for (ArgBlock argBlock : ap.getArgBlockList()) {
			System.out.println(argBlock);
		}
	}
}
