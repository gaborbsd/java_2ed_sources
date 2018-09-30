public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Helló, világ!");
    }
}



class Counter {
    // tagváltozó: a számláló értéke
    protected int value;

    // konstruktor: inicializálja a számlálót
    public Counter() {
        value = 0;
    }

    // metódus: növeli a számlálót
    public void increment() {
        value++;
    }

    // metódus: kiolvassa az értéket
    public int getValue() {
        return value;
    }
}



class Counter2 extends Counter {

    @Override
    public void increment() {
        value += 2;
		// vagy: super.increment();
		// és utána: value += 1;
    }
}



public class Singleton {
    private static Singleton instance = null;
	
    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }

    public void hello() {
        System.out.println("Hello");
    }

    public static void main(String[] args) {
        Singleton i = Singleton.getInstance();
        i.hello();
    }
}



public class Main {
    // Első
    private int a = 2;
	
    { System.out.println("Második, a értéke ekkor már " + a); }
	
    public Main() {
        System.out.println("Negyedik.");
    }
	
    { System.out.println("Harmadik."); }

    public static void main(String[] args) {
        Main m = new Main();
    }
}

Main.main(null);


public class InstanceCounting {
    private static int intancesCreated = 0;
	
    public InstanceCounting() {
        intancesCreated++;
    }

    public static int getIntancesCreated() {
        return intancesCreated;
    }
}

public class Maze {
    class Field {
        int x;
        int y;

        Field(int x, int y) {
            this.x = x;
            this.y = y;
        }
		
        public Maze getMaze() {
            // Így hivatkozhatunk a külső osztály kapcsolódó példányára
            return Maze.this;
        }
    }

    private Field[][] fields;

    public Maze(int dimX, int dimY) {
        fields = new Field[dimX][];
        for (int x = 0; x < dimX; x++) {
            fields[x] = new Field[dimY];
            for (int y = 0; y < dimY; y++) {
                Field f = new Field(x, y);
                fields[x][y] = f;
            }
        }
    }

    public static void main(String[] args) {
        Maze m = new Maze(5,5);
		
        // Nem fordul le; a belső osztály a tartalmazó
        // osztály példánya nélkül nem használható
        // Maze.Field f = new Maze.Field(5, 0);
		
        // Ha létezik tartalmazó példány, akkor már működik
        Maze.Field f2 = m.new Field(5, 0);
		
		// Az alábbi is helyes, de elvesztjük a referenciát
		// a külső osztályra
		Maze.Field f3 = new Maze(5, 5).new Field(5, 0);
    }
}



public class MethodLocal {

    public void method() {
        int a = 5;
        final int b = 6;
		int c = 7;
		a = 8;
		
        class MethodLocalClass {
            MethodLocalClass() {
                // Ez nem működik
                // System.out.println(a);
				
                // De ezek igen
                System.out.println(b);
				System.out.println(c);
            }
        }
		
        MethodLocalClass localC = new MethodLocalClass();
    }
}



public class TicTac {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            boolean even = false;

            @Override
            public void run() {
                System.out.println(!even ? "tic" : "tac");
                even = !even;
            }
        }, 0, 1000);
    }
}



public class Maze2 {
    static class Field {
        int x;
        int y;

        Field(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private Maze2.Field[][] fields;

    public Maze2(int dimX, int dimY) {
        fields = new Maze2.Field[dimX][];
        for (int x = 0; x < dimX; x++) {
            fields[x] = new Maze2.Field[dimY];
            for (int y = 0; y < dimY; y++) {
                Maze2.Field f = new Maze2.Field(x, y);
                fields[x][y] = f;
            }
        }
    }
	
    public static void main(String[] args) {
        // A belső osztály tartalmazó példány nélkül is használható
        Maze2.Field f = new Maze2.Field(5, 0);
    }
}



class Owner implements Cloneable {
    String name;

    public Owner(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class Cat implements Cloneable {
    String name;
    Owner owner;

    public Cat(String name, Owner owner) {
        this.name = name;
        this.owner = owner;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Cat ret = (Cat) super.clone();
        ret.owner = (Owner) this.owner.clone();
        return ret;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Owner o1 = new Owner("Gábor");
        Cat c1 = new Cat("Gustavo", o1);
        Cat c2 = (Cat) c1.clone();

        System.out.println(c1 == c2);
        System.out.println(c1.name.equals(c2.name));
        System.out.println(c1.owner.name.equals(c2.owner.name));
        System.out.println(c1.owner == c2.owner);
    }
}

Cat.main(null);



public double divide(double dividend, double divisor) {
    try (FileWriter writer = new FileWriter("result");) {
        double result = divide(dividend, divisor);
        writer.append(Double.toString(result));
    } catch (InvalidDivisorException e) {
        System.out.println("Hiba az osztás során: " +
            e.getMessage());
    } catch (IOException e) {
        e.printStackTrace();
    }
}

divide(4, 2);
divide(2, 0);



public enum LengthUnit {
    METER(1.0, "m"), YARD(0.9144, "yd"), FOOT(0.3048, "ft"), INCH(0.0254, "in");

    private String abbrev;
    private double meters;

    private LengthUnit(double meters, String abbrev) {
        this.meters = meters;
        this.abbrev = abbrev;
    }

    public static LengthUnit parseAbbrev(String abbrev) {
        for (LengthUnit u : LengthUnit.values()) {
            if (u.abbrev.equals(abbrev))
                return u;
        }
        return null;
    }

    public double toMeters(double x) {
        return x * meters;
    }

    @Override
    public String toString() {
        return abbrev;
    }
}



public class LengthUnitClass {
    public static LengthUnitClass METER;
    public static LengthUnitClass YARD;
    public static LengthUnitClass FOOT;
    public static LengthUnitClass INCH;

    public static LengthUnitClass[] values() {
        return new LengthUnitClass[] { METER, YARD, FOOT, INCH };
    }

    static {
        METER = new LengthUnitClass(1.0, "m");
        YARD = new LengthUnitClass(0.9144, "yd");
        FOOT = new LengthUnitClass(0.3048, "ft");
        INCH = new LengthUnitClass(0.0254, "in");
    }

    private String abbrev;
    private double meters;

    private LengthUnitClass(double meters, String abbrev) {
        this.abbrev = abbrev;
        this.meters = meters;
    }

    public static LengthUnitClass parseAbbrev(String abbrev) {
        for (LengthUnitClass u : LengthUnitClass.values()) {
            if (u.abbrev.equals(abbrev))
                return u;
            }
            return null;
    }

    public double toMeters(double x) {
        return x * meters;
    }

    @Override
    public String toString() {
        return abbrev;
    }
}