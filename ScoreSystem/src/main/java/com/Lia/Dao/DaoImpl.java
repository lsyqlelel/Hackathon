package com.Lia.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.Lia.Util.SubjUtil;
import com.Lia.myEntity.SubjectForPerson;




public class DaoImpl extends BaseDao {
	private static final Logger logger = LogManager.getLogger(DaoImpl.class);
	//增加
	public void insert(String Semester,String id,int cla,String name,int s1,int s2,int s3,int s4,int s5,int s6) {
		String sql = "insert into ? values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {Semester,id,cla,name,Semester,s1,s2,s3,s4,s5,s6};
		int i = this.executeUpdate(sql, params);
		if(i>0) {
			System.out.println("插入成功");
		}
		this.closeResource();
	}

	//删
	public void delete(String Semester,String id) {
		String sql = "delete from ? where id = ?";
		Object[] params = {Semester,id};
		int i = this.executeUpdate(sql, params);
		if(i>0) {
			System.out.println("删除成功");
		}
		this.closeResource();
	}

	//改
	public void update(String Semester,String id,String subj,int score) {
		String sql = "update ? set ? = ? where id = ?";
		Object[] params = {Semester,subj,score,id};
		int i = this.executeUpdate(sql, params);
		if(i>0) {
			System.out.println("修改成功");
		}
		this.closeResource();
	}

	//查询基本信息
	public void getList(String Semester,int cla) {
		Map<String,Integer> subjMap = new HashMap<String, Integer>();
		
		String sql = "select * from ? where class = ?";//班长查询班级班级成绩
		Object[] params = {Semester,cla};
		ResultSet rs = this.executeSql(sql, params);
		String[] subjects = SubjUtil.getSubj(Semester);
		try {
			while(rs.next()) {
				for(int i=0;i<6;i++) {
					subjMap.put(subjects[i], rs.getInt(subjects[i]));
				}
			}
		} catch (SQLException e) {
			logger.error("DaoImpl:"+e);
		}
		this.closeResource();
	}

	//查询一个学生某个学期的基本成绩
	//把查询的数据封装成一个person对象
	//返回一个map<科目，成绩>
	//查询一个学期的成绩
	public Map<String, Integer> getOne(String Semester,String id) {
		Map<String,Integer> subjMap = new HashMap<String, Integer>();
		String sql = "select * from "+Semester+" where 学号 = ?";
		String[] params = {id};
		ResultSet rs = this.executeSql(sql, params);
		String[] subjects = SubjUtil.getSubj(Semester);
		try {
			while(rs.next()) {
				for(int i=0;i<6;i++) {
					subjMap.put(subjects[i], rs.getInt(subjects[i]));
				}
			}
		} catch (SQLException e) {
			logger.error("DaoImpl:"+e);
		}
		this.closeResource();
		return subjMap;
	}
	
	
	
	//计算班级人数
	public int NumofStu(String Semester,int cla) {
		int num = 0;
		String sql = "select count(*) as num from "+Semester+" where 班级 = ?";
		Object[] params = {cla};
		ResultSet rs = this.executeSql(sql, params);
		try {
			while(rs.next()) {
				num = rs.getInt("num");
			}
		} catch (SQLException e) {
			logger.error("DaoImpl:"+e);
		}
		this.closeResource();
		return num;
	}
	
	//个人总分
	public int SumScores(String Semester ,String id) {
		int sum = 0;
		Object[] subjects = SubjUtil.getSubj(Semester);
		String sql = "select 学号,姓名,("+subjects[0]+"+"+subjects[1]+"+"+subjects[2]+"+"+subjects[3]+"+"+subjects[4]+"+"+subjects[5]+") as sum from "+Semester+" where 学号 = ?";
//		System.out.println("sql语句"+sql);
		Object[] params = {id};
		ResultSet rs = this.executeSql(sql, params);
		try {
			while(rs.next()) {
				sum = rs.getInt("sum");
			}
		} catch (SQLException e) {
			logger.error("DaoImpl:"+e);
		}
		this.closeResource();
		return sum;
	}	
	
	//个人平均分
	public double getOneAvg(String Semester ,String id) {
		DaoImpl di = new DaoImpl();
		int SumofScore = di.SumScores(Semester, id);
		this.closeResource();
		return SumofScore*1.0/6;
	}
	
	//个人总成绩排名(学生查询，数据库返回一条数据)
	public int getNO(String Semester ,String id) {
		int NO = 0;
		Object[] subjects = SubjUtil.getSubj(Semester);
		String sql = "select * from (SELECT obj.学号,obj.姓名,@rownum := @rownum + 1 AS no FROM ( SELECT 学号,姓名 FROM "+Semester+"  ORDER BY (select ("+subjects[0]+"+"+subjects[1]+"+"+subjects[2]+"+"+subjects[3]+"+"+subjects[4]+"+"+subjects[5]+") as a) DESC ) AS obj,(SELECT @rownum := 0) r )as q where 学号 = ?";
		Object[] params = {id};
		ResultSet rs = this.executeSql(sql, params);
		try {
			while(rs.next()) {
//				String name = rs.getString("姓名");
				NO = rs.getInt("no");
//				System.out.println("学号："+id+"  姓名："+name+"  排名="+NO);
			}
		} catch (SQLException e) {
			logger.error("DaoImpl:"+e);
		}
		this.closeResource();
		return NO;
	}
	
	//班级排名百分比
	public double getRate(String Semester ,String id) {
		DaoImpl di = new DaoImpl();
		int NO = di.getNO(Semester, id);
		int Num_Stu = di.NumofStu(Semester, 1);
		this.closeResource();
		return NO*1.0/Num_Stu;
	}
	
	//个人单科成绩排名(班长查询，数据库返回多条数据）
	public ArrayList<SubjectForPerson>  getSubjNO(String Semester,String subj) {
		String sql = "SELECT obj.学号,obj.姓名,obj."+subj+",@rownum := @rownum + 1 AS no FROM   (SELECT 学号, 姓名, "+subj+" FROM "+Semester+" ORDER BY "+subj+"  DESC) AS obj,  (SELECT @rownum := 0) r";
//		Object[] params = {subj,subj,Semester,subj};
		Object[] params = {};
		
		ResultSet rs = this.executeSql(sql, params);
		ArrayList<SubjectForPerson> list = new ArrayList<SubjectForPerson>();
		try {
			while(rs.next()) {
				//学号，姓名，分数，排名
				SubjectForPerson sp = new SubjectForPerson();
				sp.setSubject(subj);
				sp.setName(rs.getString("姓名"));
				sp.setId( rs.getString("学号") );
				sp.setScore( rs.getInt(subj) );
				sp.setRank( rs.getInt("no") );
				list.add(sp);
			}
		} catch (SQLException e) {
			logger.error("DaoImpl:"+e);
		}
		this.closeResource();
		return list;
	}
	
	//各科目优秀率及格率(返回某个学科的优秀率，只有一条数据)
	public double getRate(String Semester,String subj,int least) {
		int num1=1,num2=1;
		String sql = "select count(*) as num from "+Semester+" where "+subj+" > ?";
		Object[] params = {least};
		ResultSet rs = this.executeSql(sql, params);
		try {
			while(rs.next()) {
				num1 = rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "select count(*) as num from  "+Semester;
		Object[] params1 = {};
		rs = this.executeSql(sql, params1);
		try {
			while(rs.next()) {
				num2 = rs.getInt("num");
			}
		} catch (SQLException e) {
			logger.error("DaoImpl:"+e);
		}
		this.closeResource();
//		System.out.println(subj+"优秀率为:"+num1*1.0/num2);
		return num1*1.0/num2;
	}
	
	//计算学科平均分(返回一条数据)
	public double SubjAvg(String Semester,String subj) {
		int SumofScore = 0;
		int SumofStu = 0;
		String sql  = "select sum("+subj+") as sum from "+Semester;
//		Object[] params = {subj,Semester};
		Object[] params = {};
		ResultSet rs = this.executeSql(sql, params);
		try {
			while(rs.next()) {
				SumofScore = rs.getInt("sum");
			}
		} catch (SQLException e) {
			logger.error("DaoImpl:"+e);
		}
		sql = "select count(*) as num from  "+Semester;
//		Object[] params1 = {Semester};
		Object[] params1 = {};
		
		rs = this.executeSql(sql, params1);
		try {
			while(rs.next()) {
				SumofStu = rs.getInt("num");
			}
		} catch (SQLException e) {
			logger.error("DaoImpl:"+e);
		}
		this.closeResource();
		return SumofScore*1.0/SumofStu;
	}
	
	//计算学科最高
	public int Max(String Semester,String subj) {
		int max = 0;
		String sql  = "select max("+subj+") as max from "+Semester;
		Object[] params = {};
		ResultSet rs = this.executeSql(sql, params);
		try {
			while(rs.next()) {
				max = rs.getInt("max");
			}
		} catch (SQLException e) {
			logger.error("DaoImpl:"+e);
		}
		this.closeResource();
		return max;
	}
	
	//计算学科最低分
		public int Min(String Semester,String subj) {
			int min = 0;
			String sql  = "select min("+subj+") as min from "+Semester;
			Object[] params = {};
			ResultSet rs = this.executeSql(sql, params);
			try {
				while(rs.next()) {
					min = rs.getInt("min");
				}
			} catch (SQLException e) {
				logger.error("DaoImpl:"+e);
			}
			this.closeResource();
			return min;
		}
		
	//获取某个班级全部学生学号
		public ArrayList<String> getStdId(int cla){
			ArrayList<String> IDList = new ArrayList<String>();
			String sql = "select 学号 from 大一上 where 班级 = ?";
			Object[] params = {cla};			
			ResultSet rs = this.executeSql(sql, params);
			try {
				while(rs.next()) {
					 IDList.add( rs.getString("学号") ); 
				}
			} catch (SQLException e) {
				logger.error("DaoImpl:"+e);
			}
			this.closeResource();
			return IDList;
		}
		
		public String getName(String id) {
			String name = null;
			String sql = "select 姓名 from 大一上 where 学号 = ?";
			Object[] params = {id};
			ResultSet rs = this.executeSql(sql, params);
			try {
				while(rs.next()) {
					name =  rs.getString("姓名"); 
				}
			} catch (SQLException e) {
				logger.error("DaoImpl:"+e);
			}
			this.closeResource();
			return name;
		}
		
}
