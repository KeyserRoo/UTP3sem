package zad1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil implements FileVisitor<Path> {

	private String fileToSaveTo;

	private Futil(String fileToSaveTo) {
		this.fileToSaveTo = fileToSaveTo;
	}

	public static void processDir(String dirName, String resultFileName) {
		try {
			Futil futil = new Futil(resultFileName);

			Files.deleteIfExists(new File(resultFileName).toPath());

			Files.walkFileTree(Paths.get(dirName), futil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		InputStreamReader sr = new InputStreamReader(Files.newInputStream(file), "cp1250");
		BufferedReader br = new BufferedReader(sr);
		
		String line;
		StringBuilder text = new StringBuilder();
		while ((line = br.readLine()) != null) {
			text.append(line).append("\n");
		}

		Path toWrite = (new File(fileToSaveTo)).toPath();
		OutputStream os = Files.newOutputStream(toWrite, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		OutputStreamWriter osw = new OutputStreamWriter(os,StandardCharsets.UTF_8);
		BufferedWriter bw = new BufferedWriter(osw);

		bw.write(text.toString());
		bw.close();

		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
		return FileVisitResult.CONTINUE;
	}
}
