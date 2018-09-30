/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.stream;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.toList;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.javaoktato.stream.Person.Gender;
import com.javaoktato.stream.Person.MaritalStatus;

public class App {
	private static final int LIST_SIZE = 500;
	private static Random rnd = new Random(Instant.now().toEpochMilli());

	private static Person getRandomPerson() {
		Person person = new Person();

		LocalDateTime start = LocalDateTime.of(1900, 1, 1, 0, 0);
		LocalDateTime end = LocalDateTime.of(2000, 1, 1, 0, 0);
		Duration diff = Duration.between(start, end);
		int diffIndays = (int)diff.toDays();
		int days = rnd.nextInt(diffIndays);
		days %= diffIndays;
		LocalDate birthDate = start.plusDays(days).toLocalDate();
		person.setBirthDate(birthDate);

		person.setHeight(120 + rnd.nextInt(90));
		person.setWeight(40 + rnd.nextInt(80) + rnd.nextDouble());
		MaritalStatus maritalStatus = MaritalStatus.values()[rnd.nextInt(MaritalStatus.values().length)];
		person.setMaritalStatus(maritalStatus);
		Gender gender = Gender.values()[rnd.nextInt(Gender.values().length)];
		person.setGender(gender);
		person.setChildren(rnd.nextInt(4));
		person.setMonthlySalary(80_000 + rnd.nextInt(600) * 1000);

		return person;
	}

	private static List<Person> getPersonList() {
		List<Person> list = new ArrayList<>();
		for (int i = 0; i < LIST_SIZE; i++)
			list.add(getRandomPerson());
		return list;
	}

	public static void main(String args[]) {
		// Person list inicializáló véletlen adatokkal
		List<Person> list = getPersonList();

		// 5 legidősebb személy születési dátuma
		System.out.println(list
				.stream()
				.sorted((p1, p2) -> p1.getBirthDate().compareTo(p2.getBirthDate()))
				.limit(5)
				.map(p -> p.getBirthDate())
				.collect(toList()));

		// Az 500.000-nél többet keresők száma
		System.out.println(list
				.stream()
				.filter(p -> p.getMonthlySalary() > 500_000)
				.count());

		// Az 50 fölötti férfiak átlagfizetése
		System.out.println(list
				.stream()
				.filter(p -> p.getBirthDate().until(LocalDate.now()).getYears() > 50)
				.collect(averagingDouble(p -> p.getMonthlySalary())).doubleValue());

		// Van-e kétgyermekes özvegy nő?
		System.out.println(list
				.stream()
				.anyMatch(p -> p.getChildren() == 2 
					&& p.getMaritalStatus() == MaritalStatus.WIDOW && p.getGender() == Gender.FEMALE));

		// Mindenkinek van-e gyermeke?
		System.out.println(list
				.stream()
				.allMatch(p -> p.getChildren() > 0));

		Instant before, after;

		// átlagfizetés szekvenciálisan
		before = Instant.now();
		System.out.println(list
				.stream()
				.mapToDouble(p -> p.getMonthlySalary())
				.average()
				.getAsDouble());
		after = Instant.now();
		System.out.println("Elapsed time: " + Duration.between(before, after));

		// átlagfizetés párhuzamosan
		before = Instant.now();
		System.out.println(list
				.parallelStream()
				.mapToDouble(p -> p.getMonthlySalary())
				.average()
				.getAsDouble());
		after = Instant.now();
		System.out.println("Elapsed time: " + Duration.between(before, after));
	}
}