package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.exception.PatientNumberNotFoundException;
import com.model.Appointment;

import com.service.HospitalService;


public class MainModule {
 public static void main(String[] args) {
	 HospitalService hospitalService  = new HospitalService ();  
     Scanner sc = new Scanner(System.in);
     while (true)
     {
     	System.out.println("************Hospital Info System ************");
     	System.out.println("Press 1. to Get Appointment By ID ");
     	System.out.println("Press 2. to get Appointments for patient ");	     	
     	System.out.println("press 3. to get appointments for doctor ");
     	System.out.println("press 4  to schedule appointment");
     	System.out.println("press 5. to update Appointment ");
     	System.out.println("Press 6. to cancel Appointment");
     	System.out.println("Press 0. to exit");
     	System.out.println("**********************************************");
     	
     	int input = sc.nextInt();
     	
     	if(input ==0) {
     		System.out.println("Existing ... Thank You");
     		break;
     	}
     	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     	switch(input){
		case 1:
			System.out.println("Appointment Deatils");
			System.out.println("Enter appointment Id : ");
			int appointmentId = sc.nextInt();
			
			try {
				Appointment appointment = hospitalService.getAppointmentById(appointmentId);
				System.out.println(appointment);
			} catch (SQLException e) {// if query goes wrong
				System.out.println(e.getMessage());
			}
			catch(NullPointerException e) {// if id is invalid
				System.out.println(e.getMessage());
			}
			break;
			
		case 2:
			System.out.println("Appointment Deatils");
			System.out.println("Enter Patient Id : ");
			int patientId = sc.nextInt();
			
			try {
				List<Appointment> list= hospitalService.getAppointmentsForPatient(patientId);
				for (Appointment a: list) {
			    	System.out.println(a);
				}
			} catch (SQLException | PatientNumberNotFoundException e) {// if query goes wrong
				System.out.println(e.getMessage());
			}
			
			break;
		case 3:
			System.out.println("Appointment Deatils");
			System.out.println("Enter doctor Id: ");
			int doctorId = sc.nextInt();
			
			try {
				List<Appointment> list= hospitalService.getAppointmentsForDoctor(doctorId);
				for (Appointment a: list) {
			    	System.out.println(a);
				}
			} catch (SQLException e) {// if query goes wrong
				System.out.println(e.getMessage());
			}
			catch(NullPointerException e) {// if id is invalid
				System.out.println(e.getMessage());
			}
			break;
		case 4:
			
			System.out.println("Enter patient ID ");
			int patientId1 = sc.nextInt();  
            System.out.println("Enter doctor ID");
            int doctorId1=sc.nextInt();
            System.out.println("Enter appointment Date ");
            String  appointmentDateStr= sc.next();
            LocalDate appointmentDate = LocalDate.parse( appointmentDateStr, formatter);
            System.out.println("Enter description");
            sc.nextLine();
            String description = sc.nextLine();
			// insert the record in db
			Appointment ap= new Appointment(patientId1,doctorId1,appointmentDate,description);
	
				try {
					hospitalService. scheduleAppointment(ap);
					System.out.println("schedule appointed ");

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

  			
  			break;
  			
  			
		case 5:
			System.out.println("Enter appointment ID to update");
			int aid = sc.nextInt();
			System.out.println("Enter patient ID to update ");
			int patientId2 = sc.nextInt();  
            System.out.println("Enter doctor ID to update");
            int doctorId2=sc.nextInt();
            System.out.println("Enter appointment Date to update ");
            String  appointmentDateStr1= sc.next();
            LocalDate appointmentDate1 = LocalDate.parse( appointmentDateStr1, formatter);
            System.out.println("Enter description");
            sc.nextLine();
            String description1 = sc.nextLine();
            
            try {
				hospitalService.updateAppointment(aid, patientId2, doctorId2,appointmentDate1,description1);
				System.out.println("Record updated");
			} catch (SQLException  e) {
				System.out.println(e.getMessage());
			}
			
			break;
		
		case 6:
			System.out.println("Enter appointment Id to Delete");
			int appointmentId1= sc.nextInt();
			try {
				hospitalService.cancelAppointment(appointmentId1);
                System.out.println("Appointment cancelled");
			} catch (SQLException e ) {
				System.out.println(e.getMessage());
			}
			break;
			
		default:
  			System.out.println("Invalid Input given..");
  			break;
  			
        
		
}

 }sc.close();
 

}
}