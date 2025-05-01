package com.hussain.springJDBC;

import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.hussain.dao.StudentDao;
import com.hussain.model.Student;


public class App {
    public static void main(String[] args) throws SQLException {
//        ApplicationContext context = new ClassPathXmlApplicationContext("MyConfig.xml");
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/hussain/springJDBC/MyConfig.xml");
        JdbcTemplate jd = context.getBean("jdbcTemplate", JdbcTemplate.class);
        System.out.println(jd);
         
//        String sql = "insert into student(id , name , address ) values (?,?,?)";
//        int i   =  jd.update(sql , 5,"hussain", "delhi");
//        System.out.println("Successfully inseted" + i );

        
        //******************this is call the folder => CRUDoperation***************
        

		StudentDao dao = context.getBean("stDao",StudentDao.class);
//		Student st = new Student();
//		st.setId(3);
//		st.setName("Rakesh");
//		st.setAddres("UK");
//		
//		int i   = dao.insert(st);
//		System.out.println("insert Successfully.."+i);
		
		
		/* update details*/ 
		
//		  Student st = new Student(); 
//		  st.setId(1); 
//		  st.setName("Rakesh up");
//		  st.setAddres("Uk up"); 
//		  int i = dao.updateDetails(st);
//		  System.out.println("update sucessfully " + i);
		
		/* delete data */
		
//		  String sql = "delete from student where id=?";
//		  int i = jd.update(sql, 3);
//		  System.out.println("delete succefully " + i);
		
		  
		  /* fetch data by id */
			
//			  String sql = "select * from student where id=?";
//			  RowMapper rowMapper = new RowMapper() {
//				  
//			  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//			  
//			  String name = rs.getString(3); //kis column ki value chahiye
//			  
//			  return name; }
//			  
//			  };
//			  
//			  String name = (String) jd.queryForObject(sql, rowMapper, 1); // kis id ke value chahiye
//			  
//			  System.out.println(name);
		
		//*******************************************************
//		  String sql = "select * from student";
//		  
//		  RowMapper rowMapper = new RowMapper() {
//		  
//		  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//		  
//		  String name = rs.getString(3);
//		  
//		  return name; 
//		  	}
//		  };
//		  
//		  List<String> list = jd.query(sql, rowMapper);
//		  
//		  for (String name : list) { 
//			  System.out.println(name); 
//			  }
		
		
		//*******************************************************
		/* Perform Dao Standard */

		//StudentDao dao = context.getBean("stDao", StudentDao.class);

		/* insert */

//		Student st = new Student();
//		st.setId(6);
//		st.setName("soumit");
//		st.setAddres("krishana nagar");
//
//		int i = dao.insert(st);
//		System.out.println("insert sucessfully " + i);
		 
			 
		/* update details */
//		
//		  Student st = new Student();
//		  st.setId(3);
//		  st.setName("panday ");
//		  st.setAddres("pakistan");
//		  int i = dao.updateDetails(st);
//		  System.out.println("update sucessfully " + i);
		
		
		/* delete data */

		
//		 int i=dao.delete(4);
//		 System.out.println("delete sucessfully " + i);
//		  Student st = dao.getStudentById(1); 
//		  System.out.println(st);
		//*************************
		
		/*  get the data  */
		  List<Student> list = dao.getAllStudent();

			for (Student sts : list) {
				System.out.println(sts);
			}
		 
		 
		 		
    }
}

