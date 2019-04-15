package com.Lia.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.Lia.Util.SubjUtil;


public class DaoImpl extends BaseDao implements DaoInt{
	//增加
	public void insert(String Semester,int id,int cla,String name,int s1,int s2,int s3,int s4,int s5,int s6) {
		String sql = "insert into ? values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {Semester,id,cla,name,Semester,s1,s2,s3,s4,s5,s6};
		int i = this.executeUpdate(sql, params);
		if(i>0) {
			System.out.println("插入成功");
		}
		this.closeResource();
	}

	//删
	public void delete(String Semester,int id) {
		String sql = "delete from ? where id = ?";
		Object[] params = {Semester,id};
		int i = this.executeUpdate(sql, params);
		if(i>0) {
			System.out.println("删除成功");
		}
		this.closeResource();
	}

	//改
	public void update(String Semester,int id,String subj,int score) {
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
		String sql = "select * from ? where class = ?";//班长查询班级班级成绩
		Object[] params = {Semester,cla};
		ResultSet rs = this.executeSql(sql, params);
		Object[] subjects = SubjUtil.getSubj(Semester);
		try {
			while(rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("id");
				int[] ans = {}; 
				for(int i=0;i<6;i++) {
					ans[i] = rs.getInt((String) subjects[i]);
				}
				System.out.println("学号："+id+"  姓名："+name+":");
				for(int i=0;i<6;i++) {
					System.out.println(" "+subjects[i]+": "+ans[i]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeResource();
	}

	//查询一个人的基本成绩
	public void getOne(String Semester,int id) {
		String sql = "select * from ? where id = ?";
		Object[] params = {id};
		ResultSet rs = this.executeSql(sql, params);
		Object[] subjects = SubjUtil.getSubj(Semester);
		try {
			while(rs.next()) {
				String name = rs.getString("name");
				int[] ans = {}; 
				for(int i=0;i<6;i++) {
					ans[i] = rs.getInt((String) subjects[i]);
				}
				System.out.println("学号："+id+"  姓名："+name+":");
				for(int i=0;i<6;i++) {
					System.out.println(" "+subjects[i]+": "+ans[i]);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeResource();		
	}
	
	//计算班级人数
	public int NumofStu(String Semester,int cla) {
		int num = 0;
		String sql = "select count(*) as num from ? where class = ?";
		Object[] params = {Semester,cla};
		ResultSet rs = this.executeSql(sql, params);
		try {
			while(rs.next()) {
				num = rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	//个人总分
	public int SumScores(String Semester ,int id) {
		int sum = 0;
		String sql = "select id,name,(?+?+?+?+?+?) as sum from ? where id = ?";
		Object[] subjects = SubjUtil.getSubj(Semester);
		Object[] params = {subjects[0],subjects[1],subjects[2],subjects[3],subjects[4],subjects[5],Semester,id};
		ResultSet rs = this.executeSql(sql, params);
		try {
			while(rs.next()) {
				String name = rs.getString("name");
				sum = rs.getInt("sum");
				System.out.println("学号："+id+"  姓名："+name+"  总分="+sum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeResource();
		return sum;
	}	
	
	//个人平均分
	public double getOneAvg(String Semester ,int id) {
		DaoImpl di = new DaoImpl();
		int SumofScore = di.SumScores(Semester, id);
		this.closeResource();
		return SumofScore*1.0/6;
	}
	
	//个人总成绩排名(学生查询，数据库返回一条数据)
	public int getNO(String Semester ,int id) {
		int NO = 0;
		String sql = "select * from (SELECT obj.学号,obj.姓名,@rownum := @rownum + 1 AS no FROM ( SELECT 学号,姓名 FROM `大一上`  ORDER BY (select (计算机学科导论+认知实习+高等数学B（上）+高级语言程序设计+高级语言程序设计实践+线性代数) as a) DESC ) AS obj,(SELECT @rownum := 0) r )as q where 学号 = 031799128";
		Object[] subjects = SubjUtil.getSubj(Semester);
		Object[] params = {subjects[0],subjects[1],subjects[2],subjects[3],subjects[4],subjects[5],Semester,id};
		ResultSet rs = this.executeSql(sql, params);
		try {
			while(rs.next()) {
				String name = rs.getString("name");
				NO = rs.getInt("no");
				System.out.println("学号："+id+"  姓名："+name+"  排名="+NO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeResource();
		return NO;
	}
	
	//班级排名百分比
	public double getRate(String Semester ,int id) {
		DaoImpl di = new DaoImpl();
		int NO = di.getNO(Semester, id);
		int Num_Stu = di.NumofStu(Semester, 1);
		return NO*1.0/Num_Stu;
	}
	
	//个人单科成绩排名(班长查询，数据库返回多条数据）
	public void getSubNO(String Semester,String subj,int id) {
		String sql = "SELECT obj.学号,obj.姓名,obj.?,@rownum := @rownum + 1 AS no FROM   (SELECT 学号, 姓名, ? FROM `?` ORDER BY ?  DESC) AS obj,  (SELECT @rownum := 0) r";
		Object[] params = {subj,subj,Semester,subj};
		ResultSet rs = this.executeSql(sql, params);
		try {
			while(rs.next()) {
				String name = rs.getString("name");
				int NO = rs.getInt("no");
				System.out.println("学号："+id+"  姓名："+name+"  排名="+NO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeResource();
	}
	
	//各科目优秀率及格率(返回某个学科的优秀率，只有一条数据)
	public double getRate(String Semester,String subj,int least) {
		int num1=1,num2=1;
		String sql = "selct count(*) as num from ? where ? > ?";
		Object[] params = {Semester,subj,least};
		ResultSet rs = this.executeSql(sql, params);
		try {
			while(rs.next()) {
				num1 = rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "select count(*) as num from ? ";
		Object[] params1 = {Semester};
		rs = this.executeSql(sql, params1);
		try {
			while(rs.next()) {
				num2 = rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num1*1.0/num2;
	}
	
	//计算学科平均分(返回一条数据)
	public double SubjAvg(String Semester,String subj) {
		int SumofScore = 0;
		int SumofStu = 0;
		String sql  = "select sum(?) as sum from ?";
		Object[] params = {subj,Semester};
		ResultSet rs = this.executeSql(sql, params);
		try {
			while(rs.next()) {
				SumofScore = rs.getInt("sum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "select count(*) as num from ? ";
		Object[] params1 = {Semester};
		rs = this.executeSql(sql, params1);
		try {
			while(rs.next()) {
				SumofStu = rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SumofScore*1.0/SumofStu;
	}
	
	//计算学科最高
	public int Max(String Semester,String subj) {
		int max = 0;
		String sql  = "select max(?) as max from ?";
		Object[] params = {subj,Semester};
		ResultSet rs = this.executeSql(sql, params);
		try {
			while(rs.next()) {
				max = rs.getInt("max");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max;
	}
	
	//计算学科最低分
		public int Min(String Semester,String subj) {
			int min = 0;
			String sql  = "select min(?) as min from ?";
			Object[] params = {subj,Semester};
			ResultSet rs = this.executeSql(sql, params);
			try {
				while(rs.next()) {
					min = rs.getInt("min");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return min;
		}
}
