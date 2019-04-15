package com.Lia.Dao;

public interface DaoInt {
	
	//增
	public void insert(String table,int id,int cla,String name,int s1,int s2,int s3,int s4,int s5,int s6);
	//删
	public void delete(String table,int id);
	//改
	public void update(String table,int id,String subj,int score);
	//查询全部
	public void getList(String table,int cla);
	//查询特定元素
	public void getOne(String table,int id);
	
}
