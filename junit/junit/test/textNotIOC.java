package junit.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import com.atguigu.survey.base.m.BaseServcieImpl;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.utils.DataProcessUtils;

public class textNotIOC {

	@Test
	public void text4(){
		BaseServcieImpl<User> base = new BaseServcieImpl<User>();
		String capitalName = base.getCapitalName("Employ");
		System.out.println(capitalName);
	}
	
	@Test
	public void text3(){
		//测试生成表名方法
		String tableName = DataProcessUtils.generateTableName(-25);
		System.out.println(tableName);
		tableName = DataProcessUtils.generateTableName(-20);
		System.out.println(tableName);
		tableName = DataProcessUtils.generateTableName(-3);
		System.out.println(tableName);
		tableName = DataProcessUtils.generateTableName(0);
		System.out.println(tableName);
		tableName = DataProcessUtils.generateTableName(3);
		System.out.println(tableName);
		tableName = DataProcessUtils.generateTableName(9);
		System.out.println(tableName);
		tableName = DataProcessUtils.generateTableName(20);
		System.out.println(tableName);
		tableName = DataProcessUtils.generateTableName(23);
		System.out.println(tableName);
		tableName = DataProcessUtils.generateTableName(25);
		System.out.println(tableName);
		
		
		
	}
	
	@Test
	public void text2(){
		System.out.println( 2L << 58);
		System.out.println( 2L << 59);
		System.out.println( 2L << 60);
	}

	@Test
	public void text1(){
		
		Set<String> set = new HashSet();

		set.add("aaa");
		set.add("bbb");
		set.add("ccc");
		set.add("ddd");
		set.add("eee");
		set.add("fff");
		set.add("ggg");
		set.add("hhh");
		set.add("iii");
		set.add("jjj");
		set.add("kkk");
		
		set.add("lll");
		set.add("mmm");
		set.add("nnn");
		set.add("ooo");
		set.add("ppp");
		set.add("qqq");
		set.add("rrr");
		set.add("sss");
		set.add("ttt");
		set.add("uuu");
		set.add("vvv");
		

		set.add("www");
		set.add("xxx");
		set.add("yyy");
		set.add("zzz");
		
		for(int i = 0 ; i < 10000; i++){
			int j = 0; 
			Iterator<String> iterator = set.iterator();
			//for (String str : set) {
			while(iterator.hasNext()){
				String str = iterator.next();
				if(j == 0){
					System.out.print("qqq".equals(str) ? "" : "是"+str+"非 qqq\n\t");
				}
				if(j == 1){
					System.out.print("ddd".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 2){
					System.out.print("zzz".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 3){
					System.out.println("www".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 4){
					System.out.println("nnn".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 5){
					System.out.println("iii".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 6){
					System.out.println("bbb".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 7){
					System.out.println("xxx".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 8){
					System.out.println("fff".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 9){
					System.out.println("sss".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 10){
					System.out.println("ccc".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 11){
					System.out.println("lll".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 12){
					System.out.println("kkk".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 13){
					System.out.println("uuu".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 14){
					System.out.println("hhh".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 15){
					System.out.println("ooo".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				
				if(j == 16){
					System.out.println("aaa".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 17){
					System.out.println("vvv".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 18){
					System.out.println("ppp".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 19){
					System.out.println("eee".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 20){
					System.out.println("ttt".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 21){
					System.out.println("jjj".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 22){
					System.out.println("mmm".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 23){
					System.out.println("ggg".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 24){
					System.out.println("yyy".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				if(j == 25){
					System.out.println("rrr".equals(str) ? "" : "是"+str+"非 qqq\n\t");
					
				}
				
				
				j++;
			}
		}
		
	}
	
}
