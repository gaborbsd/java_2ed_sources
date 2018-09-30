/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.proxy;

import java.time.Duration;
import java.time.Instant;

public class Main {

	public static void main(String[] args) {
		NumericOperations delegate = new NumericOperationsImpl();
		NumericOperations op = CachingProxyFactory.createProxy(delegate, NumericOperations.class);

		Instant startInstant, endInstant;

		startInstant = Instant.now();
		System.out.println(op.getDivisors(5000));
		endInstant = Instant.now();
		System.out.println("Execution took " + Duration.between(startInstant, endInstant).getSeconds() + " seconds");

		startInstant = Instant.now();
		System.out.println(op.getDivisors(5000));
		endInstant = Instant.now();
		System.out.println("Execution took " + Duration.between(startInstant, endInstant).getSeconds() + " seconds");
	}
}
