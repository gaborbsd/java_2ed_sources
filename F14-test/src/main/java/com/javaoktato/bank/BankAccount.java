/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.bank;

public class BankAccount {
	private long accountNo;
	private double balance;
	private long mobileNo;
	private SMSSender sender;

	public BankAccount(long accountNo, double balance, long mobileNo) {
		this.accountNo = accountNo;
		this.balance = balance;
		this.mobileNo = mobileNo;
		this.sender = new SMSSenderImpl();
	}

	// Ez csomagszintű, a tesztelhetőség kedvéért hozzuk létre
	BankAccount(long accountNo, double balance, long mobileNo, SMSSender sender) {
		this.accountNo = accountNo;
		this.balance = balance;
		this.mobileNo = mobileNo;
		this.sender = sender;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public boolean withdraw(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException(
					"Nem lehet negatív összeget levonni.");
		} else if (balance > amount) {
			balance -= amount;
			return true;
		} else {
			boolean ret = sender.send(mobileNo,
					"A művelet végrehajtásához nincs elég pénz a számláján.");
			if (ret == false)
				throw new RuntimeException("Nem lehetett SMS-t küldeni.");
			return false;
		}
	}
}
