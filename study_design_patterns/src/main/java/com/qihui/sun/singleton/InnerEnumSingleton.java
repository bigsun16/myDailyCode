package com.qihui.sun.singleton;

/*
*我们完全不用考虑序列化和反射的问题。枚举序列化是由jvm保证的，每一个枚举类型和定义的枚举变量在JVM中都是唯一的，
*在枚举类型的序列化和反序列化上，Java做了特殊的规定：在序列化时Java仅仅是将枚举对象的name属性输出到结果中，
*反序列化的时候则是通过java.lang.Enum的valueOf方法来根据名字查找枚举对象。
*创建枚举实例只有编译器能够做到，反射也无济于事。因为newInstance()会去判断Modifier.ENUM，如果是枚举类直接抛异常
*/
public class InnerEnumSingleton {
	private InnerEnumSingleton() {
	}

	private enum Singleton {
		INSTANCE;

		private final InnerEnumSingleton innerEnumSingleton;

		Singleton() {
			innerEnumSingleton = new InnerEnumSingleton();
		}

		public InnerEnumSingleton getInstance() {
			return innerEnumSingleton;
		}
	}

	public static InnerEnumSingleton getInstance() {
		return Singleton.INSTANCE.getInstance();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(()->{
				System.out.println(InnerEnumSingleton.getInstance());
			}).start();;
		}
	}
}
