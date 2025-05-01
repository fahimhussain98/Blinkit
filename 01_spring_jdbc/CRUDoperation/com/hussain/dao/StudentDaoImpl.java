package com.hussain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//import javax.swing.tree.RowMapper;
//import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hussain.model.Student;

//@Configuration()
@Component("stDao")
public class StudentDaoImpl implements StudentDao{
	@Autowired
	private JdbcTemplate jdbcTemplate; //ye myconfig file me define hai
	

	
//	  public JdbcTemplate getJdbcTemplate() { return jdbcTemplate; } //comment because i configure teh @ based
//	  
//	  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		  this.jdbcTemplate =jdbcTemplate; 
//		  }
	
	
	
	 //********************************************

	public int insert(Student student) {

		String sql = "insert into student(id,name,address) values(?,?,?)";
		int i = jdbcTemplate.update(sql, student.getId(), student.getName(), student.getAddres());
		return i;
	}

	public int updateDetails(Student student) {
		String sql = "update student set name=? ,address=? where id=?";
		int i = jdbcTemplate.update(sql, student.getName(), student.getAddres(), student.getId());

		return i;
	}

	public int delete(int id) {
		String sql = "delete from student where id=?";
		int i = jdbcTemplate.update(sql, id);
		return i;
	}

	public Student getStudentById(int id) {
	    String sql = "SELECT * FROM student WHERE id=?";
	    
	    RowMapper<Student> rowMapper = new RowMapper<Student>() {
	        @Override
	        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Student st = new Student();
	            st.setId(rs.getInt(1));
	            st.setName(rs.getString(2));
	            st.setAddres(rs.getString(3));
	            return st;
	        }
	    };
	    
	    try {
	        // Use queryForObject correctly
	        return jdbcTemplate.queryForObject(sql, rowMapper, id);
	    } catch (EmptyResultDataAccessException e) {
	        // Handle case when no student is found with the given id
	        return null;
	    }
	}

	public List<Student> getAllStudent() {
	    String sql = "SELECT * FROM student";
	    
	    RowMapper<Student> rowMapper = new RowMapper<Student>() {
	        @Override
	        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Student st = new Student();
	            st.setId(rs.getInt(1));
	            st.setName(rs.getString(2));
	            st.setAddres(rs.getString(3));
	            return st;
	        }
	    };
	    
	    return jdbcTemplate.query(sql, rowMapper);
	}

}
