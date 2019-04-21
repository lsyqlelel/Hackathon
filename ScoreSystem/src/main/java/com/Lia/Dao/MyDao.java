package com.Lia.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.Lia.Util.SubjUtil;
import com.Lia.Util.TermUtil;
import com.Lia.myEntity.MyClass;
import com.Lia.myEntity.Semester;
import com.Lia.myEntity.Student;
import com.Lia.myEntity.SubjectForClass;
import com.Lia.myEntity.SubjectForPerson;

public class MyDao {
	private static final Logger logger = LogManager.getLogger(MyDao.class);
	//请求学生，返回学生数据
	//返回 --> 各学科分数（6），平均分，班级排名百分比，班级排名
	public Student getDateOfStud(String id) {
		Student stu = new Student();
		for(int i=0;i<4;i++) {
			String semester = TermUtil.getTerm(i);
			DaoImpl di = new DaoImpl();
			Semester se = new Semester();//要放在for循环里面！！！
			stu.setName(di.getName(id) );
			stu.setId(id);
			se.setSubjMap( di.getOne(semester, id) );//基本成绩传给学期。
			se.setTotle_score( di.SumScores(semester, id) );//总分
			se.setAver( di.getOneAvg(semester, id) );
			se.setNO( di.getNO(semester, id)  );
			se.setRate( di.getRate(semester, id) );
			stu.add(se);
		}
		return stu;
	}
	
	//请求班级，返回班级数据
	//班级成绩基本情况：最高最低平均分，优秀率及格率；
	public MyClass getSummOfCla(int cla) {
		//各学科的优秀率，及格率，平均分，最高分，最低分
		MyClass mc = new MyClass(); 
		DaoImpl di = new DaoImpl();
		for(int j=0;j<4;j++) {
			String semester = TermUtil.getTerm(j);
			String[] subj = SubjUtil.getSubj(semester);
			for(int i=0;i<6;i++) {
				SubjectForClass sc = new SubjectForClass();   //subjectclass是班级的科目
				sc.setSubj( subj[i] );
				sc.setExcellentRate( di.getRate(semester, subj[i], 90) );
				sc.setPassRate( di.getRate(semester, subj[i], 60) );
				sc.setAver(  di.SubjAvg(semester, subj[i]) );
				sc.setMax( di.Max(semester,subj[i]));
				sc.setMin( di.Min(semester, subj[i]));
				mc.addtoSubjList(sc);
			}
		}
		//所有学生某一课成绩排行
		Map< String,ArrayList<SubjectForPerson> > subjMap = new HashMap<String, ArrayList<SubjectForPerson>>();
		for(int i=0;i<4;i++) {
			String semester = TermUtil.getTerm(i);
			String[] subj = SubjUtil.getSubj(semester);
			for(int j=0;j<6;j++) {
				 ArrayList<SubjectForPerson> aList = di.getSubjNO(semester, subj[j]);
				 subjMap.put(subj[j],aList);  //   学科<------------>一个列表
			}
		}
		mc.setSubjMap(subjMap);
		
		//所有学生总成绩排名汇总表格
		ArrayList<String> IDList = di.getStdId(cla);
		ArrayList<Student> stuList = new ArrayList<Student>();
		MyDao md = new MyDao();
		for(String id :IDList) {
			stuList.add( md.getDateOfStud(id) ); 
		}
		mc.setStuList(stuList);
		logger.debug("大小："+stuList.size());
		System.out.println("大小："+stuList.size() );
		return mc;
	}
}
