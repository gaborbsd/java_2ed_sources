package proxy;

import java.time.Duration;
import java.time.Instant;

public class Main {

	public static void main(String[] args) {
		NumericOperations delegate = new NumericOperationsImpl();
		NumericOperations op = CachingProxyFactory.createProxy(delegate, NumericOperations.class);

		Instant startInstant, endInstant;

		startInstant = Instant.now();
		System.out.println(op.getDivisors(5000));
		endInstant = Instant.now();
		System.out.println("Execution took " + Duration.between(startInstant, endInstant).getSeconds() + " seconds");

		startInstant = Instant.now();
		System.out.println(op.getDivisors(5000));
		endInstant = Instant.now();
		System.out.println("Execution took " + Duration.between(startInstant, endInstant).getSeconds() + " seconds");

		startInstant = Instant.now();
		op.factorial(100000);
		endInstant = Instant.now();
		System.out.println("Execution took " + Duration.between(startInstant, endInstant).getSeconds() + " seconds");

		startInstant = Instant.now();
		op.factorial(100000);
		endInstant = Instant.now();
		System.out.println("Execution took " + Duration.between(startInstant, endInstant).getSeconds() + " seconds");
	}
}
