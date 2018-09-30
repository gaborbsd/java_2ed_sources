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

import com.javaoktato.thread.bank.domain.BankAccount;
import com.javaoktato.thread.bank.domain.TransactionRequest;

public class RequestProcessorTask implements Runnable {
	private TransactionRequest req;
	private Map<Long, BankAccount> accounts;

	public RequestProcessorTask(TransactionRequest req, Map<Long, BankAccount> accounts) {
		this.req = req;
		this.accounts = accounts;
	}

	@Override
	public void run() {
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
