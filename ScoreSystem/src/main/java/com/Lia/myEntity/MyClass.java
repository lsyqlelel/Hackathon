package com.Lia.myEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MyClass {

	
	@Override
	public String toString() {
		return "班级概览:\n"+subjList+"\n各学科排名:\n"+subjMap+"\n学生信息\n"+stuList;
	}

	public ArrayList<Student> getStuList() {
		return stuList;
	}

	public void setStuList(ArrayList<Student> stuList) {
		this.stuList = stuList;
	}

	public Map<String, ArrayList<SubjectForPerson>> getSubjMap() {
		return subjMap;
	}

	public void setSubjMap(Map<String, ArrayList<SubjectForPerson>> subjMap) {
		this.subjMap = subjMap;
	}

	public ArrayList<SubjectForClass> getSubjList() {
		return subjList;
	}

	public void setSubjList(ArrayList<SubjectForClass> subjList) {
		this.subjList = subjList;
	}
	
	public void addtoSubjList(SubjectForClass sc) {
		subjList.add(sc);
	}
		
	//一个列表存放每个科目的基本数据（平均分，最高分等）。
	private ArrayList<SubjectForClass> subjList = new ArrayList<SubjectForClass>();
	//一个列表存放各个学科的排名列表清单
	private Map< String,ArrayList<SubjectForPerson> > subjMap = new HashMap<String, ArrayList<SubjectForPerson>>();
	//一个列表存放全部学生成绩信息
	private ArrayList<Student> stuList = new ArrayList<Student>();
}
