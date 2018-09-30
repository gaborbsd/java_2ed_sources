/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.thread.bank.domain;

public class TransactionRequest {
	private long accountNo;
	private double sum;

	public TransactionRequest(long accountNo, double sum) {
		super();
		this.accountNo = accountNo;
		this.sum = sum;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public double getSum() {
		return sum;
	}
}
