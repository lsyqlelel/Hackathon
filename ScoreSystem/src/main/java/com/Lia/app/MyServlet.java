package com.Lia.app;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.Lia.Dao.MyDao;
import com.Lia.myEntity.MyClass;
import com.Lia.myEntity.Student;
import com.alibaba.fastjson.JSONObject;

//@WebServlet(asyncSupported = true, urlPatterns = { "/some" })
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(MyServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out=resp.getWriter();
		out.println("<h1>this is a post request </h1>");
		out.println("<h2>postpostpost<h2>");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		logger.debug("用户名"+username);
		logger.debug("密码"+password);
		out.println("username :"+username);
		out.println("password :"+password);

		MyDao md = new MyDao();
		Student dateOfStud = md.getDateOfStud(username);
		JSONObject aStudent = new JSONObject();
		aStudent.put("name",dateOfStud.getName());
		System.out.println(dateOfStud.getName());
		aStudent.put("id", dateOfStud.getId());
		aStudent.put("ScoresList", dateOfStud.getList());
		out.println(aStudent);
		out.println();
		out.println("============================================================");
		out.flush();
		
		MyClass mc = md.getSummOfCla(1);
		JSONObject aClass = new JSONObject();
		aClass.put("BasicDate", mc.getSubjList());//每个科目基本数据
		aClass.put("StuScores", mc.getStuList());//学生成绩
		aClass.put("SubjRank", mc.getSubjMap());//学科排名
		out.println(aClass);
		out.flush();
		
		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		
		logger.debug("调用post方法");
		PrintWriter out=resp.getWriter();
		out.println("调用post方法");
		this.doGet(req, resp);
		
		String operation = req.getParameter("operation");//操作对象
		MyDao md = new MyDao();
		if("1".equals(operation)) {
			String id = req.getParameter("id");
			Student dateOfStud = md.getDateOfStud(id);
			JSONObject aStudent = new JSONObject();
			aStudent.put("name",dateOfStud.getName());
			aStudent.put("id", dateOfStud.getId());
			aStudent.put("ScoresList", dateOfStud.getList());
			out.println(aStudent);
			out.flush();
		}
		
		//班长端查询数据
		if("2".equals(operation)) {
//			String cla = req.getParameter("class");
			MyClass mc = md.getSummOfCla(1);
			JSONObject aClass = new JSONObject();
			aClass.put("BasicDate", mc.getSubjList());//每个科目基本数据
			aClass.put("StuScores", mc.getStuList());//学生成绩
			aClass.put("SubjRank", mc.getSubjMap());//学科排名
			out.println(aClass);
			out.flush();
		}
	}
}
