
package com.app;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestClass {


	//[2,3,4,5,1,3,2,4]
	//[4,32,12,45,6,1]
	//644032121
	//first compare first digit if 


	public static void main2(String[] args) {

		Integer[] arr =  new Integer[]{4,32,12,45,6,1};

		Stream.of(arr).sorted((i1,i2)->{

			String str1 = String.valueOf(i1);
			String str2 = String.valueOf(i2);

			if(str1.length()==str2.length()) {
				return i2.compareTo(i1);
			}else if(str1.length()>str2.length()) {
				String[] arr1= str1.split("");
				String[] arr2= str2.split("");


			}

			return i1.compareTo(i2);
		}).collect(Collectors.toList());



	}



	public static void main3(String[] args) {

		Integer[] arr =  new Integer[] {2,3,4,5,1,3,2,4};
		Map<Integer, Long> map  = Stream.of(arr).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		Set keySet = map.keySet();
		/*		map.


		while(keySetItr.hasNext()) {
			Integer key =  (Integer) keySetItr.next();
			long count = map.get(key);

			if(count==1) {
				System.out.println(key);
				break;
			}
		}
		 */
	}


	Object lock = new Object();
	int i= 0;


	public static void main(String[] args) {
		TestClass tc=new TestClass();
		try {
			Thread t1 =  new Thread(()->{
				tc.printOdd(20);
			});

			Thread t2 =  new Thread(()->{
				tc.printEven(20);
			});


			t1.start();
			t2.start();

			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	void  printOdd(int num) {
		try {
			for(; i <=num;i++) {
				System.out.println("odd:"+i);
				synchronized(lock) {
					if(i%2!=0) {
						System.out.println("o:"+i);
						lock.notify();
						lock.wait();

					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	void  printEven(int num) {
		try {
			for(; i <=num;i++) {
				System.out.println("evn"+i);
				synchronized(lock) {
					if(i%2==0) {
						System.out.println("e:"+i);
						lock.notify();
						
						lock.wait();
					}

				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

