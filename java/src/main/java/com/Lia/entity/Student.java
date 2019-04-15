package com.Lia.entity;

import com.Lia.Util.SubjUtil;

public class Student {
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCla() {
		return cla;
	}

	public void setCla(int cla) {
		this.cla = cla;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	String name;
	int id;
	int cla;
	String semester;
	Object[] subjects = SubjUtil.getSubj(semester);
}
