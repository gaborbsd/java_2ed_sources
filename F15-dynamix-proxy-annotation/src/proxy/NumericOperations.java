package proxy;

import java.math.BigInteger;
import java.util.Set;

public interface NumericOperations {
	
	Set<Long> getDivisors(long dividend);
	
	BigInteger factorial(long num);
}
