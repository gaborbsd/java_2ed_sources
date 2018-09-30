/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.generics;

import java.util.Arrays;

public class RentalStore<T> {
	private T[] store;
	// ez tárolja, melyik elem szabad
	private boolean[] free;

	// Meg kell adni egy tömböt a konstruktorban, mivel
	// generikus módon nem tudunk példányosítani
	public RentalStore(T[] store) {
		this.store = store;
		free = new boolean[store.length];
		Arrays.fill(free, true);
	}

	public T reserve() {
		for (int i = 0; i < free.length; i++)
			if (free[i]) {
				free[i] = false;
				return store[i];
			}
		return null;
	}

	public void release(T e) {
		for (int i = 0; i < store.length; i++)
			if (store[i].equals(e)) {
				free[i] = true;
				return;
			}
	}

	public static void main(String args[]) {
		String[] strings = new String[] { "alma", "k�rte" };
		RentalStore<String> store = new RentalStore<>(strings);

		// ilyenkor nincs típusellenőrzés
		RentalStore store2 = store;

		// lefoglaljuk sorban a sztringeket, a harmadik már null lesz
		String a = store.reserve();
		String b = store.reserve();
		String c = store.reserve();
		System.out.println(a + " " + b + " " + c);

		// elengedjük a másodikat, újbóli foglaláskor ezt kapjuk vissza
		store.release(b);
		System.out.println(store.reserve());
	}
}
