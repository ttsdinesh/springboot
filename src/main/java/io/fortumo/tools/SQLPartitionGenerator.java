package io.fortumo.tools;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Dinesh Thangaraj
 *
 * Created on 08-Jan-2018
 */
public class SQLPartitionGenerator {
	public static void main(String[] args) {
		// Provide proper path location
		Path file = Paths.get("partitions.sql");
		List<String> lines = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		sb.append("ALTER TABLE payments PARTITION BY RANGE (TO_DAYS(created_at))(");
		IntStream.range(0, 63).forEach(i -> {
			sb.append("PARTITION p");
			sb.append(i);
			sb.append(" VALUES LESS THAN ");
			sb.append("(TO_DAYS ('");
			sb.append(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
			sb.append("')),");
			cal.add(Calendar.MONTH, 1);
		});
		sb.append("PARTITION future VALUES LESS THAN MAXVALUE);");
		lines.add(sb.toString());
		try {
			Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DONE");
	}
}
