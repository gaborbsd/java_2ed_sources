package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CachingProxyFactory {

	private static class ObjectArrayWrapper {
		private Object[] arr;

		public ObjectArrayWrapper(Object[] arr) {
			this.arr = arr;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(arr);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ObjectArrayWrapper other = (ObjectArrayWrapper) obj;
			if (!Arrays.equals(arr, other.arr))
				return false;
			return true;
		}
	}

	private static class CachingInvocationHandler implements InvocationHandler {
		private Map<String, Map<ObjectArrayWrapper, Object>> cache = new HashMap<>();
		private Object target;

		public CachingInvocationHandler(Object target) {
			this.target = target;
		}

		@Override
		public Object invoke(Object proxy, Method proxyMethod, Object[] proxyArgs) {
			try {
				Method targetMethod = target.getClass().getMethod(proxyMethod.getName(),
						proxyMethod.getParameterTypes());
				boolean cached = false;
				if (targetMethod.isAnnotationPresent(Cached.class))
					cached = targetMethod.getAnnotation(Cached.class).value();

				if (!cached)
					return proxyMethod.invoke(target, proxyArgs);

			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				e1.printStackTrace();
				return null;
			}

			Map<ObjectArrayWrapper, Object> perMethodCache = cache.get(proxyMethod.getName());
			if (perMethodCache == null) {
				perMethodCache = new HashMap<>();
				cache.put(proxyMethod.getName(), perMethodCache);
			}

			ObjectArrayWrapper args = new ObjectArrayWrapper(proxyArgs);
			Object result = perMethodCache.get(args);
			if (result == null) {
				try {
					result = proxyMethod.invoke(target, proxyArgs);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				perMethodCache.put(args, result);
			}
			return result;
		}
	}

	private CachingProxyFactory() {
	}

	@SuppressWarnings("unchecked")
	public static <T> T createProxy(T delegate, Class<?> proxyInterface) {
		return (T) Proxy.newProxyInstance(delegate.getClass().getClassLoader(), new Class<?>[] { proxyInterface },
				new CachingInvocationHandler(delegate));
	}
}
