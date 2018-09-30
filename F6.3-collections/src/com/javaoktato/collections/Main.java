/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.collections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
	public static void distinct(BufferedReader br) throws IOException {
		Set<String> set = new TreeSet<>();
		String s = br.readLine();
		while (s != null) {
			s = s.toLowerCase();
			StringTokenizer tok = new StringTokenizer(s, " .,:\n-?!+\"\'()");
			while (tok.hasMoreTokens())
				set.add(tok.nextToken());
			s = br.readLine();
		}
		System.out.println("A bemeneten kapott szavak:");
		for (String t : set)
			System.out.println(t);
	}

	public static void reverseWithList(BufferedReader br) throws IOException {
		List<String> list = new ArrayList<>();
		String s = br.readLine();
		while (s != null) {
			s = s.toLowerCase();
			StringTokenizer tok = new StringTokenizer(s, " .,:\n-?!+\"\'()");
			while (tok.hasMoreTokens())
				list.add(tok.nextToken());
			s = br.readLine();
		}
		System.out.println("A kapott szavak fordított sorrendben:");
		// listaiterátorral járjuk be visszafele
		ListIterator<String> it = list.listIterator(list.size());
		while (it.hasPrevious())
			System.out.println(it.previous());
	}

	public static void reverseWithDequeue(BufferedReader br) throws IOException {
		Deque<String> q = new ArrayDeque<>();
		String s = br.readLine();
		while (s != null) {
			s = s.toLowerCase();
			StringTokenizer tok = new StringTokenizer(s, " .,:\n-?!+\"\'()");
			while (tok.hasMoreTokens())
				q.offerFirst(tok.nextToken());
			s = br.readLine();
		}
		System.out.println("A kapott szavak fordított sorrendben:");
		while (!q.isEmpty())
			System.out.println(q.pollFirst());
	}

	public static void countWords(BufferedReader br) throws IOException {
		Map<String, Integer> map = new TreeMap<>();
		String s = br.readLine();
		while (s != null) {
			s = s.toLowerCase();
			StringTokenizer tok = new StringTokenizer(s, " .,:\n-?!+\"\'()");
			while (tok.hasMoreTokens()) {
				String tmp = tok.nextToken();
				Integer count = map.containsKey(tmp) ? map.get(tmp) : 0;
				map.put(tmp, ++count);
			}
			s = br.readLine();
		}
		System.out.println("A kapott szavak előfordulási száma:");
		for (Map.Entry<String, Integer> e : map.entrySet())
			System.out.println(e.getKey() + ": " + e.getValue());
	}

	public static void main(String[] args) throws IOException {
		try (InputStreamReader isr = new InputStreamReader(System.in); BufferedReader br = new BufferedReader(isr);) {
			if (args.length < 1) {
				System.out.println("Hiányzó paraméter.");
				System.exit(-1);
			}
			switch (args[0]) {
			case "--distinct":
				distinct(br);
				break;
			case "--reverse-list":
				reverseWithList(br);
				break;
			case "--reverse-queue":
				reverseWithDequeue(br);
				break;
			case "--word-count":
				countWords(br);
				break;
			default:
				System.out.println("Érvénytelen paraméter.");
			}
		}
	}
}
