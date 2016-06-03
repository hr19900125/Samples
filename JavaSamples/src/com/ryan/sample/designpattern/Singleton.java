package com.ryan.sample.designpattern;

public class Singleton {

	/**
	 * 懒汉，线程不安全 这种lazy loading，致命的是在多线程不能正常工作
	 */
	public static class Singleton1 {
		private static Singleton1 instance;

		private Singleton1() {
		}

		public static Singleton1 getInstance() {
			if (instance == null) {
				instance = new Singleton1();
			}
			return instance;
		}
	}

	/**
	 * 懒汉，线程安全 由于getInstance有synchronized修饰，效率低下
	 */
	public static class Singleton2 {
		private static Singleton2 instance;

		private Singleton2() {
		}

		public static synchronized Singleton2 getInstance() {
			if (instance == null) {
				instance = new Singleton2();
			}
			return instance;
		}
	}

	/**
	 * 饿汉 这种方式基于classloder机制避免了多线程的同步问题，不过，instance在类装载时就实例化，虽然导致类装载的原因有很多种，
	 * 在单例模式中大多数都是调用getInstance方法，
	 * 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化instance显然没有达到lazy loading的效果。
	 */
	public static class Singleton3 {
		private static Singleton3 instance = new Singleton3();

		private Singleton3() {
		}

		public static Singleton3 getInstance() {
			return instance;
		}
	}

	/**
	 * 饿汉，变种 也是在类被加载的时候的初始化了，和第三中类似
	 */
	public static class Singleton4 {
		private static Singleton4 instance = null;

		static {
			instance = new Singleton4();
		}

		private Singleton4() {
		}

		public static Singleton4 getInstance() {
			return instance;
		}
	}

	/**
	 * 静态内部类
	 * 这种方式同样利用了classloder的机制来保证初始化instance时只有一个线程，它跟第三种和第四种方式不同的是（很细微的差别）：
	 * 第三种和第四种方式是只要Singleton类被装载了，那么instance就会被实例化（没有达到lazy
	 * loading效果），而这种方式是Singleton类被装载了，instance不一定被初始化。因为SingletonHolder类没有被主动使用
	 * ，只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，从而实例化instance。想象一下，
	 * 如果实例化instance很消耗资源，我想让他延迟加载，另外一方面，我不希望在Singleton类加载时就实例化，
	 * 因为我不能确保Singleton类还可能在其他的地方被主动使用从而被加载，那么这个时候实例化instance显然是不合适的。这个时候，
	 * 这种方式相比第三和第四种方式就显得很合理。
	 */
	public static class Singleton5 {
		private static class SingletonHolder {
			private static final Singleton5 INSTANCE = new Singleton5();
		}

		private Singleton5() {
		}

		public static final Singleton5 getInstance() {
			return SingletonHolder.INSTANCE;
		}

	}

	/**
	 * 枚举
	 */
	public enum Singleton6 {
		INSTANCE;
	}

	/**
	 * 双重校验锁
	 */
	public static class Singleton7 {
		private volatile static Singleton7 instance;

		private Singleton7() {
		}

		public static Singleton7 getInstance() {
			if (instance == null) {
				synchronized (Singleton7.class) {
					if (instance == null) {
						instance = new Singleton7();
					}
				}
			}
			return instance;
		}
	}

}
