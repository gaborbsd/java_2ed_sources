Set<String> words = new TreeSet<>(new Comparator<String> {
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
});



Set<String> words = new TreeSet<>((s1, s2) -> s1.length() - s2.length());




List<String> names = List.of("Péter", "Tamás", "Bence");
names.forEach(n -> { System.out.println(n); });




List<String> names = List.of("Péter", "Tamás", "Bence");
names.forEach(System.out::println);