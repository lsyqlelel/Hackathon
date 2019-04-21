package com.Lia.myEntity;

import java.util.Map;

public class Semester {

	
	public int getNO() {
		return NO;
	}
	public void setNO(int NO) {
		this.NO = NO;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getAver() {
		return aver;
	}
	public void setAver(double aver) {
		this.aver = aver;
	}
	public int getTotle_score() {
		return totle_score;
	}
	public void setTotle_score(int totle_score) {
		this.totle_score = totle_score;
	}
	public Map<String, Integer> getSubjMap() {
		return SubjMap;
	}
	public void setSubjMap(Map<String, Integer> stud_subj) {
		this.SubjMap = stud_subj;
	}
	
	public void put(String key,Integer value) {
		SubjMap.put(key, value);
	}
	
	private int NO;
	private double rate;//班级排名百分比
	private double aver;//个人平均分
	private int totle_score;//总分
	private Map<String,Integer> SubjMap;//学科及该学科里面对应的相关数据（科目名称，该科分数）

	@Override
	public String toString() {
		return " [排名："+NO+" 班级排名百分比 "+rate+" 个人平均分"+aver+" 总分"+totle_score+" 学科名称和分数："+SubjMap+"]";
	}
}