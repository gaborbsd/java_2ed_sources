Instant first = Instant.now();
System.out.println(first);
Instant second = Instant.now();
System.out.println(first.isBefore(second))



LocalDate date = LocalDate.of(1848, 03, 15);
System.out.println(date.getDayOfWeek());
System.out.println(date.isLeapYear());



LocalTime time = LocalTime.now();
System.out.println(time);
System.out.println(time.getSecond());



ZonedDateTime dateTime = ZonedDateTime.now();
System.out.println(dateTime);
System.out.println(dateTime.getZone());



LocalDate date = LocalDate.now();
System.out.println(date.minusDays(45));
LocalTime time = LocalTime.now();
System.out.println(time.plusMinutes(17));



LocalDate date = LocalDate.now();
System.out.println(date.minus(45, ChronoUnit.DAYS));
LocalTime time = LocalTime.now();
System.out.println(time.plus(17, ChronoUnit.MINUTES));



LocalDate date = LocalDate.now();
System.out.println(date.with(ChronoField.DAY_OF_WEEK, 1));
LocalTime time = LocalTime.now();
System.out.println(time.get(ChronoField.SECOND_OF_DAY));



LocalTime time = LocalTime.now();
Duration duration = Duration.ofHours(2).plusMinutes(30);
System.out.println(time.plus(duration));



LocalDate birthDate = LocalDate.of(1903, 12, 28);
LocalDate currentDate = LocalDate.now();
Period period = Period.between(birthDate, currentDate);
System.out.println(period);



LocalDateTime dateTime = LocalDateTime.now();
DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT);
System.out.println(formatter.format(dateTime));



LocalDate date = LocalDate.now();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd", new Locale("es"));
System.out.println(formatter.format(date));



LocalDateTime dateTime = LocalDateTime.now();
DateTimeFormatterBuilder dfb = new DateTimeFormatterBuilder();
dfb = dfb.appendValue(ChronoField.HOUR_OF_DAY, 2)
        .appendLiteral(":")
        .appendValue(ChronoField.MINUTE_OF_HOUR, 2);
DateTimeFormatter formatter = dfb.toFormatter();
System.out.println(formatter.format(dateTime));