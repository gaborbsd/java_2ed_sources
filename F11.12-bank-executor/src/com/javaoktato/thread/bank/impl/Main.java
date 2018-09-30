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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import com.javaoktato.thread.bank.domain.BankAccount;
import com.javaoktato.thread.bank.domain.TransactionRequest;

public class Main {

	public static void main(String[] args) {
		BankAccount acc1 = new BankAccount(1111_1111_1111_1111l, 150_000.0);
		BankAccount acc2 = new BankAccount(1111_1111_1111_2222l, 250_000.0);
		BankAccount acc3 = new BankAccount(2222_1111_1111_1111l, 1_000_000.0);

		Map<Long, BankAccount> accounts = new HashMap<>();
		accounts.put(acc1.getAccountNo(), acc1);
		accounts.put(acc2.getAccountNo(), acc2);
		accounts.put(acc3.getAccountNo(), acc3);

		Queue<TransactionRequest> queue = new LinkedList<>();

		TimerTask producer = new PeriodicRequestProducerTask(queue, accounts);
		TimerTask producer2 = new PeriodicRequestProducerTask(queue, accounts);
		Timer timer = new Timer();
		timer.schedule(producer, 2000, 8000);
		timer.schedule(producer2, 0, 6000);

		Runnable consumer = new RequestDispatcherTask(queue, accounts);
		Thread t = new Thread(consumer);
		t.start();
	}
}
