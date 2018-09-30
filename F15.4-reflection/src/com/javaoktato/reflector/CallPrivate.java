/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.reflector;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CallPrivate {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Class<String> clazz = String.class;
		
		Method isLatin1Method = clazz.getDeclaredMethod("isLatin1");
		isLatin1Method.setAccessible(true);
		
		Field valueField = clazz.getDeclaredField("value");
		valueField.setAccessible(true);
		
		String latin1String = "Hello world!";
		String nonLatin1String = "éáűúőóü";
		
		boolean firstResult = (Boolean) isLatin1Method.invoke(latin1String);
		byte[] firstArray = (byte[]) valueField.get(latin1String);
		boolean secondResult = (Boolean) isLatin1Method.invoke(nonLatin1String);
		byte[] secondArray = (byte[]) valueField.get(nonLatin1String);
		System.out.println(firstResult);
		System.out.println(firstArray.length);
		System.out.println(secondResult);
		System.out.println(secondArray.length);
	}

}
