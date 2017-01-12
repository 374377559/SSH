package test;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimerTask;

public class MyTimeTesk extends TimerTask{

	@Override
	public void run() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("doing start....."+sdf.format(new Date()));
	}

}
