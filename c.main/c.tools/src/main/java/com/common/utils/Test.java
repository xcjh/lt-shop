package com.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {
	
	public static void main(String[] args) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
	}
	
	public static void add(){
		System.out.println("追同一个篮球12期(一个月)，红球随机，不赔本够买");
		int price = 2;//单价
		int amount = 0;//总金额
		int total = 0;//总注数
		int upcount = 1;//上期购买次数
		for (int i=1;i<13;i++) {
			if(upcount<i)upcount=i;
			int subtotal = upcount * 2;
			int zjje = upcount * 5;//本期中奖金额
			amount += subtotal;	
			while(amount>zjje){
				upcount ++;
				amount +=2;
				zjje +=5;
			}
			
			
			total += upcount;
			
			
			System.out.println(String.format("第%s次购买%s注消费%s元, 共购买%s注消费金额%s，中奖金额%s",i,upcount,subtotal,total,amount,upcount*5));
		}
	}
	
	
	public static void addOne(){
		System.out.println("追同一个篮球12期(一个月)，红球随机，每次增加一注");
		int price = 2;//单价
		int amount = 0;//总金额
		int total = 0;//总注数
		for (int i=1;i<13;i++) {
			int subtotal = 0;
			for (int j=1;j<=i;j++){
				subtotal = j*price;
			}
			amount += subtotal;
			total += i;
			System.out.println(String.format("第%s次购买%s注消费%s元, 共购买%s注消费金额%s，中奖金额%s",i,i,subtotal,total,amount,i*5));
		}
		System.out.println(String.format("12期都不中奖亏损%s,第12期中奖亏损%s", amount,amount-60));
	}

}
