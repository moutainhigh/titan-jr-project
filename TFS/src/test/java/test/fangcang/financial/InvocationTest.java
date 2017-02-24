package test.fangcang.financial;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationTest implements InvocationHandler {
	
	private Object realObject;
	
	public InvocationTest(Object realObject){
		this.realObject=realObject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("proxy do something");
		return method.invoke(realObject, args);
	}

}
