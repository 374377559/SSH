package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzTask {
	public void doSimpleTimer() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("doing start....."+sdf.format(new Date()));
	}
}
