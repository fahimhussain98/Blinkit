package com.hussain.javaconfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hussain.dao.StudentDao;
import com.hussain.model.Student;

public class App {
	
	public static void main(String[] args) throws SQLException {

		ApplicationContext context = new AnnotationConfigApplicationContext(config.class);//jav based congiguration ho rhe hai

		JdbcTemplate jd = context.getBean("jdbc", JdbcTemplate.class); // yan se jdbc bean call hogi

		System.out.println(jd.getDataSource().getConnection());
		
//		  String sql = "insert into student(id,name,address) values(?,?,?)";
//		  int i = jd.update(sql, 4, "Pavy", "USA");
//		  System.out.println("sucessfully inserted : " + i);
		 

		/* Perform Dao Standard */

		StudentDao dao = context.getBean("stDao", StudentDao.class);

		/* insert */

//		
//		 Student st = new Student(); st.setId(4); st.setName("jageswar");
//		  st.setAddres("London");
//		  
//		  int i = dao.insert(st); System.out.println("insert sucessfully " + i);
		 
		  
		/* update details */

		
//		 Student st = new Student(); st.setId(4); st.setName("jageswar up");
//		  st.setAddres("jageswar up"); int i = dao.updateDetails(st);
//		  System.out.println("update sucessfully " + i);
		 

		/* delete data */

		/*
		 * int i = dao.delete(4); System.out.println("delete sucessfully " + i);
		 */

		/*
		 * Student st = dao.getStudentById(3); System.out.println(st);
		 */

//		List<Student> list = dao.getAllStudent();
//
//		for (Student sts : list) {
//			System.out.println(sts);
//		}
		  
//		  
//		  String sql = "delete from student where id=? ";
//		  int   i = jd.update(sql,2);
//		  System.out.println("delete successfully " + i );
		
		
//		String sql = "update student set name =?, address=?  where id=? ";
//		int  i   =  jd.update(sql,"hussain ","jordon",5);
//		System.out.println("update successfully "+ i );
		
		//**********************************************************************
		//****this studentDao present in the different folder***************************
		
		
		/* Perform Dao Standard */

		//StudentDao dao = context.getBean("stDao", StudentDao.class);// yan se stDao bean call hogi


		/* insert */

		
//		  Student st = new Student(); 
//		  st.setId(4);
//		  st.setName("jageswar");
//		  st.setAddres("London");
//		  
//		  int i = dao.insert(st); 
//		  System.out.println("insert sucessfully " + i);
		 
		/* update details */

		
//		  Student st = new Student(); 
//		  st.setId(4);
//		  st.setName("jageswar up");
//		  st.setAddres("china");
//		  int i = dao.updateDetails(st);
//		  System.out.println("update sucessfully " + i);
		 

		/* delete data */

		
		//  int i = dao.delete(4); System.out.println("delete sucessfully " + i);
		 

		/*
		 * Student st = dao.getStudentById(3); System.out.println(st);
		 */

		List<Student> list = dao.getAllStudent();

		for (Student sts : list) {
			System.out.println(sts);
		}
		
		
		


	}

}
