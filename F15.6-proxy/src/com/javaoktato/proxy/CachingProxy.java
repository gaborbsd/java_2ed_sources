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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CachingProxy extends NumericOperationsImpl {
	private NumericOperationsImpl delegate;
	private Map<Long, Set<Long>> cache = new HashMap<>();

	public CachingProxy(NumericOperationsImpl delegate) {
		this.delegate = delegate;
	}

	@Override
	public Set<Long> getDivisors(long dividend) {
		Set<Long> result = cache.get(dividend);
		if (result == null) {
			result = delegate.getDivisors(dividend);
			cache.put(dividend, result);
		}
		return result;
	}
}
