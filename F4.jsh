StringBuilder sb = new StringBuilder("Hello,");
sb.replace(4, 5, "ó");
sb.append(" világ!");
sb.deleteCharAt(12);
// eredmény: Helló, világ
System.out.println(sb);



String email1 = "foo@bar.hu";
String email2 = "rossz@email@cim.com";
final String re = "([a-zA-Z0-9_]*)@([a-zA-Z0-9_]*)\\.[a-zA-Z]*";

System.out.println(email1.matches(re));
System.out.println(email2.matches(re));
System.out.println(Arrays.toString(email1.split("@")))



String str = "Merkúr,Vénusz,Mars,Föld";
StringTokenizer tokenizer = new StringTokenizer(str, ",");
while (tokenizer.hasMoreTokens())
    System.out.println(tokenizer.nextToken());



Date date = new Date();
System.out.println(date);