package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.model.Appointment;

import com.mysql.jdbc.PreparedStatement;

import com.util.DBUtilProperty;

public class HospitalServiceImpl implements IHospitalServiceImpl{

	@Override
	public Appointment getAppointmentById(int appointmentId) throws SQLException {
		
		Connection conn = DBUtilProperty.getDBconn();
		
		String sql = "select * from appointment where appointment_id=?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
      
		pstmt.setInt(1,appointmentId);
		
		ResultSet rst =pstmt.executeQuery();
		
		if(rst.next()) {
			    
			    int patientId=rst.getInt("patient_Id");
			    int doctorId= rst.getInt("doctor_Id");
				String appointmentDate = rst.getString("apoointment_date");
				String description = rst .getString("description");
				// save it in object 
				Appointment ap = new Appointment(patientId,doctorId,LocalDate.parse(appointmentDate),description);
				return ap;
				}
		DBUtilProperty.dbclose();
		throw new NullPointerException("Invalid ID given");
	}

	public List<Appointment> getAppointmentsForPatient(int patientId) throws SQLException {
         Connection conn = DBUtilProperty.getDBconn();
         List<Appointment> list = new ArrayList<>();
		String sql = "select * from appointment where patient_id=?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
      
		pstmt.setInt(1,patientId);
		
		ResultSet rst =pstmt.executeQuery();
		
		while(rst.next()) {
			    int appointmentId=rst.getInt("appointment_id");
			    int patientId1=rst.getInt("patient_Id");
			    int doctorId= rst.getInt("doctor_Id");
				String appointmentDate = rst.getString("apoointment_date");
				String description = rst .getString("description");
				// save it in object 
				Appointment ap = new Appointment(appointmentId,patientId1,doctorId,LocalDate.parse(appointmentDate),description);
				list.add(ap);
	    }
	
	
		DBUtilProperty.dbclose();
	return list;
	}

	@Override
	public List<Appointment> getAppointmentsForDoctor(int doctorId) throws SQLException {

		 Connection conn = DBUtilProperty.getDBconn();
	         List<Appointment> list = new ArrayList<>();
			String sql = "select * from appointment where doctor_id=?";
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
	      
			pstmt.setInt(1,doctorId);
			
			ResultSet rst =pstmt.executeQuery();
			
			while(rst.next()) {
				    int appointmentId=rst.getInt("appointment_id");
				    int patientId1=rst.getInt("patient_Id");
				    int doctorId1= rst.getInt("doctor_Id");
					String appointmentDate = rst.getString("apoointment_date");
					String description = rst .getString("description");
					// save it in object 
					Appointment ap = new Appointment(appointmentId,patientId1,doctorId1,LocalDate.parse(appointmentDate),description);
					list.add(ap);
	}
			DBUtilProperty.dbclose();
			return list;
	}

	@Override
	public void scheduleAppointment(Appointment ap) throws SQLException {
		 Connection conn = DBUtilProperty.getDBconn();
		String sql = "insert into appointment(patient_id , doctor_id , apoointment_date , description) values (?,?,?,?)";
		// prepare the statement
   	  PreparedStatement pstmt =  (PreparedStatement) conn.prepareStatement(sql);
	  // attach the values to ?
	pstmt.setInt(1,ap.getPatientId());
	pstmt.setInt(2,ap.getDoctorId());
	pstmt.setString(3,ap.getAppointmentDate().toString());
	pstmt.setString(4,ap.getDescription());
	
    // execute the query
	pstmt.executeUpdate();//1:success ,0:failure
	
	
	DBUtilProperty.dbclose();
		
	}

	@Override
	public void updateAppointment(int aid, int patientId2, int doctorId2, LocalDate appointmentDate1, String description1)
			throws SQLException {
		Connection conn = DBUtilProperty.getDBconn();
			
			String sql ="update appointment set patient_id=? ,doctor_id=? , apoointment_date=? ,description=? where appointment_id =?";
			// prepare the statement
			
				PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
				// attach the values to ?
				pstmt.setInt(1, patientId2);
				pstmt.setInt(2,doctorId2);
				pstmt.setDate(3, java.sql.Date.valueOf(appointmentDate1));
				pstmt.setString(4, description1);
				pstmt.setInt(5,aid);
				
			
				
				
				DBUtilProperty.dbclose();
				
		
	}

	@Override
	public void cancelAppointment(int appointmentId1) throws SQLException {
		Connection conn = DBUtilProperty.getDBconn();
		String sql ="delete from appointment where appointment_id =?";
		// prepare the statement
		
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
			// attach the values to ?
			pstmt.setInt(1,appointmentId1);
			DBUtilProperty.dbclose();
			
	}
		
	
}