IntStream.range(0, 5).map(i -> i * i).sum();

IntStream.iterate(2, i -> i * 2).limit(10).forEach(System.out::println);