package test.fangcang;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lockers {  
	public static Lock lock = null;
    
    /** 
     *订单锁。 
     */  
    public static Lock getLock(){  
    	if(lock == null){
    		lock =  new ReentrantLock();
    	}
    	return lock;
        
    }  
    
    
   Map<String,String> collectMap = new HashMap<String,String>();
    
    private void InserData(String orderNo,String sign) throws InterruptedException{
    	
//    	   Lockers.getLock().lock();
//    	   System.out.println("进行相关操作："+sign);
//    	   Thread.sleep(1000);
//    	   System.out.println(orderNo);
//    	   Lockers.getLock().unlock();
    	   
    	   synchronized (orderNo) {
    		   System.out.println("进行相关操作："+orderNo+"-------"+sign);
        	   System.out.println(orderNo+"：结束");
		}
      
    }
    
    
    
    
    public static void main(String[] args) throws Exception{  
    	final Lockers l = new Lockers();
    	    Runnable task1 = new Runnable(){  
    	        public void run(){  
    	             for(int i=0;i<10;i++){
    	            	 try {
							l.InserData("12354","1");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    	             }
    	        }  
    	    };  
    	    Runnable task2 = new Runnable(){  
    	        public void run(){  
    	        	  for(int i=0;i<10;i++){
     	            	 try {
							l.InserData("12354","2");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
     	             }
    	        }  
    	    }; 
    	    Runnable task3 = new Runnable(){  
    	        public void run(){  
    	        	  for(int i=0;i<10;i++){
     	            	 try {
							l.InserData("12354","3");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
     	             }
    	        }  
    	    }; 
    	    Runnable task4 = new Runnable(){  
    	        public void run(){  
    	        	  for(int i=0;i<10;i++){
     	            	 try {
							l.InserData("54321","4");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
     	             }
    	        }  
    	    }; 
    	    Runnable task5= new Runnable(){  
    	        public void run(){  
    	        	  for(int i=0;i<10;i++){
     	            	 try {
							l.InserData("54321","5");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
     	             }
    	        }  
    	    }; 
    	    Runnable task6 = new Runnable(){  
    	        public void run(){  
    	        	  for(int i=0;i<10;i++){
     	            	 try {
							l.InserData("54321","6");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
     	             }
    	        }  
    	    }; 
    	    Thread thread1 = new Thread(task1);
    	    Thread thread2 = new Thread(task2);
    	    Thread thread3 = new Thread(task3);
    	    Thread thread4 = new Thread(task4);
    	    Thread thread5 = new Thread(task5);
    	    Thread thread6 = new Thread(task6);
    	    thread1.start();
    	    thread2.start();
    	    thread3.start();
    	    thread4.start();
    	    thread5.start();
    	    thread6.start();
    } 
}