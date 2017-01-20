package test.fangcang.financial;

import java.lang.reflect.Proxy;

public class ProxyMain {

	public static void main(String[] args){
		
		InvocationTest invocation = new InvocationTest(new Realobject());
		RealObjectInterface iface = (RealObjectInterface)Proxy.newProxyInstance(Realobject.class.getClassLoader(), Realobject.class.getInterfaces(), invocation);
		
//		System.out.println(iface);
		iface.g();
		iface.t();
	
	}
	
}
