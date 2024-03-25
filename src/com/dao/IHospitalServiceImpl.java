package com.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.exception.PatientNumberNotFoundException;
import com.model.Appointment;

public interface IHospitalServiceImpl {
	public Appointment getAppointmentById(int appointmentId) throws SQLException;

	public List<Appointment> getAppointmentsForPatient(int patientId)
			throws SQLException, PatientNumberNotFoundException;

	public List<Appointment> getAppointmentsForDoctor(int doctorId) throws SQLException;

	public void scheduleAppointment(Appointment ap) throws SQLException;

	public void updateAppointment(int aid, int patientId2, int doctorId2, LocalDate appointmentDate1,
			String description) throws SQLException;

	public void cancelAppointment(int appointmentId1) throws SQLException;

}
