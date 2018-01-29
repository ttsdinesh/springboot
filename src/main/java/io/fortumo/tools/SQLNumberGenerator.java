package io.fortumo.tools;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dinesh Thangaraj
 *
 * Created on 06-Jan-2018
 */
public class SQLNumberGenerator {

	public static void main(String[] args) {
		// Provide proper file location
		Path file = Paths.get("numbers.sql");
		for (int i = 1; i < 76 * 1000 * 100; i++) {
			List<String> lines = new ArrayList<String>();
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO NUMBERS VALUES");
			for (; i % 1000 != 0; i++) {
				sb.append("(");
				sb.append(i);
				sb.append(")");
				sb.append(",");
			}
			sb.setLength(sb.length() - 1);
			sb.append(";");
			lines.add(sb.toString());
			try {
				Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("DONE");
	}
}
