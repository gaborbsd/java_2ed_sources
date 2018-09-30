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

public class SMSSenderImpl implements SMSSender {

	@Override
	public boolean send(long number, String msg) {

		// hosszú és bonyolult SMS-küldési folyamat

		try {
			Thread.sleep(5000);;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// sikeres végrehajtás
		return true;
	}
}
