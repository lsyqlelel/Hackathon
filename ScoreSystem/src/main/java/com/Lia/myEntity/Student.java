package com.Lia.myEntity;

import java.util.ArrayList;

public class Student {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Semester> getList() {
		return ScoresList;
	}

	public void setList(ArrayList<Semester> list) {
		this.ScoresList = list;
	}
	public void add(Semester se) {
		ScoresList.add(se);
	}
	
	
	private String name;
	private String id;
	
	//四个学期成绩
	public ArrayList<Semester> ScoresList = new ArrayList<Semester>();
//	public List<Semester> list = new ArrayList<Semester>();
	
	@Override
	public String toString() {
		String string = "姓名："+name+" 学号："+id+" "+ScoresList.toString();
		return string;
		
	}
}
