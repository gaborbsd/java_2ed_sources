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
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflector {

	private static void reflectModifier(int m) {
		if (Modifier.isPublic(m))
			System.out.print("public ");
		if (Modifier.isProtected(m))
			System.out.print("protected ");
		if (Modifier.isPrivate(m))
			System.out.print("private ");
		if (Modifier.isStatic(m))
			System.out.print("static ");
		if (Modifier.isFinal(m))
			System.out.print("final ");
		if (Modifier.isAbstract(m))
			System.out.print("abstract ");
		if (Modifier.isSynchronized(m))
			System.out.print("synchronized ");
		if (Modifier.isTransient(m))
			System.out.print("transient ");
	}

	private static void reflectField(Field f) {
		reflectModifier(f.getModifiers());
		System.out.print(f.getType().getSimpleName());
		System.out.print(' ');
		System.out.print(f.getName());
		System.out.println(';');
	}

	private static void reflectMethod(Method m) {
		reflectModifier(m.getModifiers());
		System.out.print(m.getReturnType().getSimpleName());
		System.out.print(' ');
		System.out.print(m.getName());
		System.out.print('(');
		Class<?>[] paramTypes = m.getParameterTypes();
		for (int i = 0; i < paramTypes.length; i++) {
			System.out.print(paramTypes[i].getSimpleName());
			System.out.print(" arg" + i);
			if (i < paramTypes.length - 1)
				System.out.print(", ");
		}
		System.out.println(");");
	}

	private static void reflectClass(Class<?> c) {
		for (Field f : c.getDeclaredFields())
			reflectField(f);
		for (Method m : c.getDeclaredMethods())
			reflectMethod(m);
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Adja meg a csomagnévvel "
					+ "kvalifikált osztálynevet paraméterként!");
			System.exit(-1);
		}
		try {
			Class<?> c = Class.forName(args[0]);
			reflectClass(c);
		} catch (ClassNotFoundException e) {
			System.out.println("Az osztály nem található!");
			e.printStackTrace();
		}
	}
}
