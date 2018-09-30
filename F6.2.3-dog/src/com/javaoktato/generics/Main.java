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

import java.util.ArrayList;
import java.util.List;

class Animal {
}

class Dog extends Animal {
	public void bark() {
	}
}

class GermanShepherd extends Dog {
}

class Cat extends Animal {
	public void meow() {
	}
}

public class Main {
	public static void fillArrayWithDogs(Animal[] arr) {
		for (int i = 0; i < arr.length; i++)
			arr[i] = new Dog();
	}

//	public static void fillListWithDogs(List<Animal> list) {
//		for (int i = 0; i < 5; i++)
//			list.add(new Dog());
//	}

	public static void fillListWithDogs(List<? super Dog> list) {
		for (int i = 0; i < 5; i++)
			list.add(new Dog());
	}
	
	public static void barkAll(List<? extends Dog> list) {
		for (Dog d : list)
			d.bark();
	}

	public static void main(String[] args) {
		// List<Cat> catList = new ArrayList<>();
		// fillListWithDogs(catList);

		// Cat[] catArray = new Cat[5];
		// fillArrayWithDogs(catArray);

		List<GermanShepherd> gsList = new ArrayList<>();
		gsList.add(new GermanShepherd());
		barkAll(gsList);
		
		List<Animal> animalList = new ArrayList<>();
		fillListWithDogs(animalList);
	}
}
