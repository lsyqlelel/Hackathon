package com.Lia.Text;

import com.Lia.Dao.MyDao;
import com.Lia.myEntity.MyClass;
import com.Lia.myEntity.Student;
import com.alibaba.fastjson.JSONObject;

public class forJDBCText {

	public static void main(String[] args) {
		//学生端查询测试
		MyDao md = new MyDao();
		Student dateOfStud = md.getDateOfStud("031799102");
//		System.out.println(dateOfStud.toString());
		
		
//		//学生端json数据测试。
		JSONObject aStudent = new JSONObject();
		aStudent.put("name",dateOfStud.getName());
		aStudent.put("id", dateOfStud.getId());
		aStudent.put("ScoresList", dateOfStud.getList());
		
		System.out.println(aStudent);
		
		System.out.println();
		System.out.println("=====================================");
//		System.out.println();
		
		//班长端查询测试
		MyClass mc = md.getSummOfCla(1);
//		System.out.println(mc);
		
		
		JSONObject aClass = new JSONObject();
		aClass.put("BasicDate", mc.getSubjList());//每个科目基本数据
		aClass.put("StuScores", mc.getStuList());//学生成绩
		aClass.put("SubjRank", mc.getSubjMap());//学科排名
		
		System.out.println(aClass);
		
		
	}

}







