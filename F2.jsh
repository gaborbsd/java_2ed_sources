System.out.println("Hello, vilag!");



int limit = 5;
short i;
double pi = 3.1415;
double a = 45.0 / 16.0;
Button button = new JButton("OK");



i = 15;



char fkjel = '!';
System.out.println("Szép napot kívánok" + fkjel + '\u0021');



byte b = 12;
System.out.println("b = " + b);
		
short s = 345;
System.out.println("s = " + s);
		
int i = 5_666_777;
System.out.println("i = " + i);
System.out.println("Egy decimálisan megadott integer literál: " + 1_234_567);
System.out.println("Egy hexadecimálisan megadott integer literál: " + 0xFF_AA);
System.out.println("Egy oktálisan megadott integer literál: " + 0664);
System.out.println("Egy binárisan megadott integer literál: " + 0b1111_0000);
		
long l = 999_999_999_000_000l;
System.out.println("l = " + l);
System.out.println("Egy long literál: " + 123_456_789_000L);
System.out.println("Egy hexadecimálisan megadott long literál: " + 0xFF_FF_FF_FFL);

float f = 3.14f;
System.out.println("A pi értéke: " + f);
System.out.println("Az Euler-szám: " + 2.72F);
		
double d = 6.022e23;
System.out.println("Az Avogadro-szám: " + d);
System.out.println("A Boltzmann-állandó J/K mértékegységgel: " + 1.380_650_424E-23);
System.out.println("Megadhatunk -0-t is:" + -0.0);
System.out.println("Vagy plusz végtelent: " + Double.POSITIVE_INFINITY);
System.out.println("Vagy akár NaN-t: " + Double.NaN);



boolean igaz = true;
boolean hamis = false;
		
System.out.println("Ez igaz lesz: " + igaz);
System.out.println("Ez pedig hamis: " + hamis);



String elso = "alma";
String masodik = new String("korte");
String harmadik = null;

System.out.println("elso: " + elso);
System.out.println("masodik: " + masodik);
System.out.println("harmadik: " + harmadik);



var someNumericValue = 25;
var aStringObject = "abc";
var aButton = new JButton("My Button");



Integer i1 = new Integer(5);
Integer i2 = new Integer("6");
Character c = new Character('c');
		
int i4 = i1.intValue(); // 5
double d1 = i1.doubleValue(); // 5

Integer i5 = 1; // boxing
int i6 = i5;  // unboxing
Integer i7 = 1;
Integer i8 = new Integer(1);
System.out.println(i5 == i7); // true
System.out.println(i5 == i8); // false

long l1 = Long.parseLong("10", 2); // dec. 2
Long l2 = Long.valueOf("ff", 16); // dec. 255
Long l3 = Long.decode("0xff");  // dec. 255
System.out.println("hex: " + Long.toHexString(l3)); // ff
System.out.println("oct: " + Long.toOctalString(l3)); // 377
System.out.println("bin: " + Long.toBinaryString(l3)); // 11111111



String[] strVector;

strVector = new String[3]; // kezdetben minden eleme null
strVector[0] = "Valami";
System.out.println("0: " + strVector[0]); // "Valami"
System.out.println("1:" + strVector[1]); // null
System.out.println("length: " + strVector.length); // 3

int[][] intMatrix = new int[2][];
intMatrix[0] = new int[2];
intMatrix[1] = new int[2];
intMatrix[0][0] = 5;

int[] uj = new int[] { 1, 2 };
System.out.println(uj.length); // 2



public void sendMessage(String text, String subject, String... addresses) {
    for (String email : addresses) {
        // ide jön az emailküldő kód
    }
}

String[] rcpt = new String[] { "user1@example.com", "user2@example.com" };
sendMessage("Helló!", "Teszt 1", rcpt);
		
sendMessage("Helló!", "Teszt 2", "user1@exmaple.com", "user2@example.com");



public class Main {

    enum PartOfDay {
        MORNING, NOON, AFTERNOON, EVENING;
    }

    public static void main(String[] args) {
        // jelenlegi dátum és idő lekérése
        Date d = Calendar.getInstance().getTime();
        int hours = d.getHours(); // óra
        PartOfDay n;

        // napszak meghatározása
        if (hours < 12)
            n = PartOfDay.MORNING;
        else if (hours < 13)
            n = PartOfDay.NOON;
        else if (hours < 19)
            n = PartOfDay.AFTERNOON;
        else
            n = PartOfDay.EVENING;

        System.out.println("Napszak: " + n);
    }
}

Main.main(null);



5 / 3
5.0 / 3



int a = 5;
int b = 3;
float c = 3.0f;
		
System.out.println("a : " + a);
System.out.println("b : " + b);
System.out.println("c : " + c);
System.out.println("a + b : " + (a + b));
System.out.println("a - b : " + (a - b));
System.out.println("a * b : " + (a * b));
System.out.println("a / b : " + (a / b));
System.out.println("(double)a / b : " + ((double)a / b));
System.out.println("a / c : " + (a / c));
System.out.println("a % b : " + (a % b));
System.out.println("a++ : " + (a++));
System.out.println("++b : " + (++b));



System.out.println("3 > 5 : " + (3 > 5));
System.out.println("3 < 5 : " + (3 < 5));
System.out.println("3 >= 5 : " + (3 >= 5));
System.out.println("3 <= 5 : " + (3 <= 5));
System.out.println("3 == 5 : " + (3 == 5));
System.out.println("3 != 5 : " + (3 != 5));
System.out.println("3 == 3.0f : " + (3 == 3.0f));
System.out.println("3 == 3.0 : " + (3 == 3.0));

System.out.println("+0.0 != -0.0 : " + (+0.0 != -0.0));
System.out.println("+0.0 == -0.0 : " + (+0.0 == -0.0));
System.out.println("Double.POSITIVE_INFINITY == Double.NEGATIVE_INFINITY : " + (Double.POSITIVE_INFINITY == Double.NEGATIVE_INFINITY));
System.out.println("Double.NaN == Double.NaN : " + (Double.NaN == Double.NaN));
System.out.println("Double.isNaN(Double.NaN) : " + (Double.isNaN(Double.NaN)));



String a = "Hello";
String b = "Hello";
System.out.println("a == b : " + (a == b));
System.out.println("a == new String(\"Hello\") : "
		+ (a == new String("Hello")));
System.out.println("a.equals(new String(\"Hello\") :" + (a.equals(new String("Hello"))));



File elso = new File("fajlnev");
File masodik = new File("fajlnev");
System.out.println("elso == masodik : " + (elso == masodik));



int i1 = 0b11110000;
int i2 = 0b11001100;
		
int es = i1 &amp; i2;
int vagy = i1 | i2;
int kvagy = i1 ^ i2;
int neg = ~i1;


		
System.out.println("i1:    " + Integer.toBinaryString(i1));
System.out.println("i2:    " + Integer.toBinaryString(i2));
		
// 11000000
System.out.println("es:    " + Integer.toBinaryString(es));
// 11111100
System.out.println("vagy:  " + Integer.toBinaryString(vagy));
// 111100
System.out.println("kvagy: " + Integer.toBinaryString(kvagy));
// 11111111111111111111111100001111
System.out.println("neg:   " + Integer.toBinaryString(neg))



int i3 = -55;
int i4 = i3 << 1;
int i5 = i3 >> 1;
int i6 = i3 >>> 1;
		
// 11111111111111111111111111001001
System.out.println("i3 :  " + Integer.toBinaryString(i3));
// 11111111111111111111111110010010
System.out.println("<< :  " + Integer.toBinaryString(i4));
// 11111111111111111111111111100100
System.out.println(">> :  " + Integer.toBinaryString(i5));
// 1111111111111111111111111100100
System.out.println(">>> :  " + Integer.toBinaryString(i6));



boolean igaz = true;
boolean hamis = false;
		
System.out.println("igaz &amp; hamis: " + (igaz &amp; hamis));
System.out.println("igaz | hamis: " + (igaz | hamis));
System.out.println("igaz ^ hamis: " + (igaz ^ hamis));
System.out.println("igaz &amp; igaz: " + (igaz &amp; igaz));
System.out.println("hamis ^ hamis: " + (hamis ^ hamis));



int i = 4;
boolean b = true || (i++ &gt;= 5);
System.out.println("b: " + b); // true
System.out.println("i: " + i); // 4

b = true | (i++ &gt;= 5);
System.out.println("b: " + b); // true
System.out.println("i: " + i); // 5

System.out.println(!true); // false
System.out.println(!false); // true



// aktuális dátum és idő
Date d = Calendar.getInstance().getTime();
System.out.println("Jó " + (d.getHours() > 11 ? "napot"
    : "reggelt") + " kívánok!");
	
File f = new File("teszt.txt");

// ha nem létezik fájl, akkor létrehozunk egy üreset
if (!f.exists())
    f.createNewFile();
// beállítjuk, hogy írható legyen
f.setWritable(true);



// statikus tagváltozó
System.out.println("elérésiút-elválasztó karakter: " + File.pathSeparator);
// példányon keresztül is elérhető, de figyelmeztetést kapunk
System.out.println("Példányon keresztül is elérhető: " + f.pathSeparator);



public class Main {
    String str = "Példányváltozó";
    
    public void teszt() {
        String str = "Lokális változó";
        // lokális változó
        System.out.println("this nélkül: " + str);
        // példányváltozó
        System.out.println("thisszel: " + this.str);
    }
}

new Main().teszt();



// mindhárom true
System.out.println("str instanceof Object: " + (str instanceof Object));
System.out.println("str instanceof CharSequence: " + (str instanceof CharSequence));
System.out.println("str instanceof String: " + (str instanceof String));
// le sem fordul; File nem leszármazott osztálya a Stringnek
// System.out.println("str instanceof File: " + (str instanceof File));
// szintén true
System.out.println("null instanceof File: " + (null instanceof File));



short a = 4;
short b = 5;
short c = (short) (a + b);



// automatikus felfele konvertálás
CharSequence r1 = new String("bla");

String r2;
// nem fordulna le, mert lefele kell konvertálni
// r2 = r1;

// így jó, de ha nem ismernénk r1 pontos típusát, akkor
// ellenőrizni is kellene:
if (r1 instanceof String)
    r2 = (String) r1;
		
// nem fordulna le, mert már fordításkor is bizonyos,
// hogy r1-ben nem lehetett File-példány
// File f = (String) r1;



String s1 = "JAVA 11 SE";
String s2 = "Java 11 SE";
		
// nullától kezdődik
System.out.println("s1.charAt(5): " + s1.charAt(5));
// kisbetű/nagybetű különbség
System.out.println("s1.contains(\"Java\"): " + s1.contains("Java"));
// false
System.out.println("s1.equals(s2): " + s1.equals(s2));
// true
System.out.println("s1.equalsIgnoreCase(s2): " + s1.equalsIgnoreCase(s2));
// JAVA 6 SE
System.out.println("s1.replace(\'7\', \'6\'): " + s1.replace('7', '6'));
// java 7 se
System.out.println("s1.toLowerCase(): " + s1.toLowerCase());



public static boolean isPrime(long p) {
    boolean isPrime = true;
    long divisor = 2;
		
    while (divisor &lt; p) {
        if (p % divisor == 0) {
            isPrime = false;
            break;
        }
        p++;
    }
    return isPrime;
}

isPrime(7);
isPrime(10);



public static boolean isPrime(long p) {
    boolean isPrime = true;

    for (long divisor = 2; divisor &lt; p; divisor++) {
        if (p % divisor == 0) {
            isPrime = false;
            break;
        }
        p++;
    }
    return isPrime;
}

isPrime(7);
isPrime(10);