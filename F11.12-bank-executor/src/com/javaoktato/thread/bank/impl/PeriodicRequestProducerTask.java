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

import java.util.Date;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.TimerTask;

import com.javaoktato.thread.bank.domain.BankAccount;
import com.javaoktato.thread.bank.domain.TransactionRequest;

public class PeriodicRequestProducerTask extends TimerTask {
	private Queue<TransactionRequest> queue;
	private Map<Long, BankAccount> accounts;
	private Random rnd = new Random(new Date().getTime());

	public PeriodicRequestProducerTask(Queue<TransactionRequest> queue,
			Map<Long, BankAccount> accounts) {
		this.queue = queue;
		this.accounts = accounts;
	}

	@Override
	public void run() {
		// 1 - 10000  között véletlenszám
		int sum = rnd.nextInt(10000) + 1;

		// költség vagy jóváírás
		int sign = rnd.nextInt(2);
		if (sign == 1)
			sum = -sum;

		// a létezők közül sorsolunk egy számlát
		int acc = rnd.nextInt(accounts.size());
		Long accNo = accounts.keySet().toArray(new Long[0])[acc];

		// elkészül a feldolgozási kérés
		TransactionRequest req = new TransactionRequest(accNo, sum);

		// betesszük a sorba, és felébresztjük a feldolgozót
		synchronized (queue) {
			queue.offer(req);
			System.out.println("Tranzakció érkezett " + accNo + " számlához "
					+ sum + " összeggel.");
			queue.notify();
		}
	}
}
