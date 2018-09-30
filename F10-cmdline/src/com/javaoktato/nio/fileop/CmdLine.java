/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.nio.fileop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CmdLine {

	public static void main(String[] args) {
		String currentDir = System.getProperty("user.dir");
		Path cwd = Paths.get(currentDir);

		try (InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);) {
			while (true) {
				String line = br.readLine();
				String[] params = line.split(" ");
				switch (params[0]) {
				case "cp":
					Files.copy(Paths.get(params[1]), Paths.get(params[2]));
					break;
				case "mv":
					Files.move(Paths.get(params[1]), Paths.get(params[2]));
					break;
				case "rm":
					Files.delete(Paths.get(params[1]));
					break;
				case "cd":
					Path newDir = cwd.resolve(params[1]);
					if (newDir != null)
						cwd = newDir.normalize();
					else
						System.out.println("Nem létező könyvtár.");
					break;
				case "ls":
					try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(cwd)) {
						for (Path path : directoryStream)
							System.out.println(path);
					}
					break;
				case "pwd":
					System.out.println(cwd.toAbsolutePath());
					break;
				case "exit":
					return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
