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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.javaoktato.bank.BankAccount;
import com.javaoktato.bank.SMSSender;

// test double, amely csak kiírja, hogy hívva lett
class SMSSenderMock implements SMSSender {

	@Override
	public boolean send(long number, String msg) {
		System.out.println("send(" + number + ", " + msg + ")");
		return true;
	}
}

/*
 * Ez a második tesztosztály, amely már kézzel írt test double objektumot használ 
 */
public class BankAccountTest2 {
	private BankAccount account;
	private SMSSender sender;

	@Before
	public void setUp() throws Exception {
		sender = new SMSSenderMock();
		account = new BankAccount(1234567812345678l, 30000.0, 3630111111l,
				sender);
	}

	@Test
	public void testSuccessfulWithdraw() {
		boolean result = account.withdraw(1000.0);
		assertTrue(result);
		assertEquals("A levonás utáni összeg nem egyezik a várttal", 29000.0,
				account.getBalance(), 1.0);
	}

	@Test
	public void testFailedWithdraw() {
		boolean result = account.withdraw(100000.0);
		assertFalse(result);
		assertEquals("A sikertelen levonás utáni összeg nem egyezik a várttal",
				30000.0, account.getBalance(), 1.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidWithdraw() {
		account.withdraw(-1000.0);
	}
}
