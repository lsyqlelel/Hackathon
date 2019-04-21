package com.Lia.myEntity;

public class SubjectForClass {

	
	
	@Override
	public String toString() {
		return "[科目:"+subj+" 最高分 "+max+" 最低分 "+min+" 平均分 "+aver+" 优秀率  "+excellentRate+" 及格率   "+passRate+"]";
	}
	
	public String getSubj() {
		return subj;
	}
	public void setSubj(String subj) {
		this.subj = subj;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public double getAver() {
		return aver;
	}
	public void setAver(double aver) {
		this.aver = aver;
	}
	public double getExcellentRate() {
		return excellentRate;
	}
	public void setExcellentRate(double excellentRate) {
		this.excellentRate = excellentRate;
	}
	public double getPassRate() {
		return passRate;
	}
	public void setPassRate(double passRate) {
		this.passRate = passRate;
	}
	
	private String subj;
	private int max;
	private int min;
	private double aver;
	private double excellentRate;
	private double passRate;
	
}
