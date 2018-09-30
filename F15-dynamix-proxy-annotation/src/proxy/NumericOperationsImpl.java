package proxy;

import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.LongStream;

public class NumericOperationsImpl implements NumericOperations {

	@Override
	public Set<Long> getDivisors(long dividend) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) { }

		return LongStream
				.rangeClosed(1, dividend)
				.filter(l -> dividend % l == 0)
				.collect(TreeSet::new, TreeSet::add,
				TreeSet::addAll);
	}

	@Override
	@Cached
	public BigInteger factorial(long num) {
		BigInteger result = BigInteger.valueOf(1);
		while (num > 1) {
			result = result.multiply(BigInteger.valueOf(num));
			num--;
		}
		return result;
	}
}