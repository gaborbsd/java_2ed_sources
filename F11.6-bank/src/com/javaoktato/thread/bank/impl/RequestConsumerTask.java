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

import com.javaoktato.thread.bank.domain.BankAccount;
import com.javaoktato.thread.bank.domain.TransactionRequest;

public class RequestConsumerTask implements Runnable {
	private Queue<TransactionRequest> queue;
	private Map<Long, BankAccount> accounts;

	public RequestConsumerTask(Queue<TransactionRequest> queue, Map<Long, BankAccount> accounts) {
		this.queue = queue;
		this.accounts = accounts;
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
					} catch (InterruptedException e) {}
				req = queue.poll();
			}

			// kikeressük a megfelelő számlát
			BankAccount acc = accounts.get(req.getAccountNo());

			// elvégezzük a feldolgozást egy menetben
			synchronized (acc) {
				double balance = acc.getBalance();
				balance += req.getSum();
				acc.setBalance(balance);
				System.out.println("Tranzakció feldolgozva " + acc.getAccountNo() + " számlához " + req.getSum()
						+ " összeggel, új egyenleg " + balance + '.');
			}
		}
	}
}
