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

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class AsyncRandomGenerator {
	
	public CompletableFuture<Integer> getRandomNumber() {
		CompletableFuture<Integer> result = new CompletableFuture<>();
		new Thread(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) { }
			int num = new Random().nextInt();
			result.complete(num);
		}).start();
		return result;
	}
}
