/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.htmlgen;

import static j2html.TagCreator.*;


public class App {
	public static void main(String[] args) {
		String text =
				html(
						head(
								title("My First Wemgape")
						),
						body(
								h1("Hello, World!"),
								p("I hope you like this small example.")
						)
				).renderFormatted();
		System.out.println(text);
	}
}
