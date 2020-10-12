package QuizTasks_CleanCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ComputesLineNumberFromFile {

	// private static final String FILE =
	// ComputesLineNumberFromFile.class.getResource("./data.txt").getFile();
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
		String numOfLine = processFileTwo(inputFile, (BufferedReader b) -> b.lines().count() + "");
		System.out.println(numOfLine);
		

	}
	// usage
	public static String processFile(iFileReaderInterface p) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
			return p.readLinesFromFile(br);
		}
	}

	public static String processFileTwo(BufferedReader inputFile, iFileReaderInterface code) throws IOException {
		return code.readLinesFromFile(inputFile);
	}
}

@FunctionalInterface
interface iFileReaderInterface {
	String readLinesFromFile(BufferedReader b) throws IOException;
}
