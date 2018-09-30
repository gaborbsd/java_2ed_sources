/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class SearchAndReplace {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Használat: SearchAndReplace "
					+ "regex új_szöveg fájl");
			System.exit(-1);
		}

		BufferedReader br = null;
		try {
			br = (args[2].equals("-")) ? new BufferedReader(
					new InputStreamReader(System.in)) : new BufferedReader(
					new InputStreamReader(new FileInputStream(args[2])));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String s;
		try (StringWriter writer = new StringWriter();) {
			while ((s = br.readLine()) != null) {
				writer.append(s.replaceAll(args[0], args[1]));
				writer.append('\n');
			}
			br.close();
			System.out.println(writer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}