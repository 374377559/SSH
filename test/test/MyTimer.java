package test;

import java.util.Timer;

public class MyTimer {

	public static void main(String[] args) {
		  Timer timer=new Timer();
		  //延迟2秒执行，每3秒执行一次
		  timer.schedule(new MyTimeTesk(), 2000,3000);
	}

}
