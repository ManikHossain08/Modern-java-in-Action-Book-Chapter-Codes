package Problem_Solution_In_LambdaExpression;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ComputesLineNumberFromFile {

	private static final String FILE = "/Users/info.txt";

	public static void main(String[] args) throws IOException {

		// SOLUTION #01
		try {
			String numOfLines = processFile((BufferedReader b) -> b.lines().count() + "");
			System.out.println(numOfLines);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// SOLUTION #02
		BufferedReader inputFile = new BufferedReader(new FileReader(FILE));
		String numOfLine = processFile_Two(inputFile, (BufferedReader b) -> b.lines().count() + "");
		System.out.println(numOfLine);
		

	}
	// Usage for SOLUTION #01
	public static String processFile(iFileReaderInterface p) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
			return p.readLinesFromFile(br);
		}
	}
	// Usage for SOLUTION #02
	public static String processFile_Two(BufferedReader inputFile, iFileReaderInterface code) throws IOException {
		return code.readLinesFromFile(inputFile);
	}
}

@FunctionalInterface
interface iFileReaderInterface {
	String readLinesFromFile(BufferedReader b) throws IOException;
}
