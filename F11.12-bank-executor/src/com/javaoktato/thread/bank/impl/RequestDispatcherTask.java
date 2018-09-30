/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.thread.bank.impl;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.javaoktato.thread.bank.domain.BankAccount;
import com.javaoktato.thread.bank.domain.TransactionRequest;

public class RequestDispatcherTask implements Runnable {
	private static final int THREAD_NO = 5;

	private Queue<TransactionRequest> queue;
	private Map<Long, BankAccount> accounts;
	private ExecutorService executorService;

	public RequestDispatcherTask(Queue<TransactionRequest> queue, Map<Long, BankAccount> accounts) {
		this.queue = queue;
		this.accounts = accounts;
		executorService = Executors.newFixedThreadPool(THREAD_NO);
	}

	@Override
	public void run() {
		TransactionRequest req;
		for (;;) {

			// várakozunk amíg nincs kérés, utána kivesszük a sorból
			synchronized (queue) {
				if (queue.isEmpty())
					try {
						queue.wait();
					} catch (InterruptedException e) { }
				req = queue.poll();
			}

			executorService.submit(new RequestProcessorTask(req, accounts));
		}
	}
}