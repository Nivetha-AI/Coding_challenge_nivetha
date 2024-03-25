package com.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.dao.HospitalServiceImpl;
import com.exception.PatientNumberNotFoundException;

public abstract class IHospitalService {
	HospitalServiceImpl hospitalServiceimpl = new HospitalServiceImpl();

	public Appointment getAppointmentById(int appointmentId) throws SQLException {

		return hospitalServiceimpl.getAppointmentById(appointmentId);
	}

	public List<Appointment> getAppointmentsForPatient(int patientId)
			throws SQLException, PatientNumberNotFoundException {

		List<Appointment> list = hospitalServiceimpl.getAppointmentsForPatient(patientId);
		return list;
	}

	public List<Appointment> getAppointmentsForDoctor(int doctorId) throws SQLException {
		List<Appointment> list = hospitalServiceimpl.getAppointmentsForDoctor(doctorId);
		return list;
	}

	public void scheduleAppointment(Appointment ap) throws SQLException {
		hospitalServiceimpl.scheduleAppointment(ap);
	}

	public void updateAppointment(int aid, int patientId2, int doctorId2, LocalDate appointmentDate1,
			String description1) throws SQLException {
		hospitalServiceimpl.updateAppointment(aid, patientId2, doctorId2, appointmentDate1, description1);

	}

	public void cancelAppointment(int appointmentId1) throws SQLException {
		hospitalServiceimpl.cancelAppointment(appointmentId1);

	}

}
