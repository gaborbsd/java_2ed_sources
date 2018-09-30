/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.thread.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

	public static void main(String[] args) {
		AsyncRandomGenerator rndGen = new AsyncRandomGenerator();
		CompletableFuture<Integer> num = rndGen.getRandomNumber();

		// Funkcionális taszkkezelés
		num.thenAccept(System.out::println);
		num.thenApply(i -> i * 2).thenAccept(System.out::println);

		// állapot pollozása
		while (!num.isDone()) {
			System.out.println("Waiting for result...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}

		// Eredm�ny lek�r�se
		try {
			System.out.println(num.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		// Eredm�ny azonnali lek�r�se default �rt�kkel
		System.out.println(num.getNow(0));
	}
}
