package com.simple.patientapp.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simple.patientapp.model.Appointments;
import com.simple.patientapp.model.Availability;
import com.simple.patientapp.repository.AppointmentRepository;
import com.simple.patientapp.repository.AvailabilityRepository;

@Service
public class AppointmentServices {
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private AvailabilityRepository availabilityRepository; 
    
    @Autowired
    private SmsService smsService;

    // Save the appointment
    public void saveAppointment(Appointments info) {
    	
        String appointmentId = generateAppointmentId(info.getFullname(), info.getAppointment_date());
        info.setAppointmentId(appointmentId);

    	
        appointmentRepository.save(info);
        sendSmsNotification(info);

    }
    
   

    // Count the number of booked appointments
    public int countAppointmentsByPuId(String puId) {
        return appointmentRepository.countByPuId(puId);
    }
    //get Appointments by PuId
    public List<Appointments> getAppointmentsByPuId(String puId) {
        return appointmentRepository.findByPuId(puId);
    }
   //get All Appointment 
    public List<Appointments> getAllAppointments() {
        return appointmentRepository.findAll();
    }
  //get data by Appointment ID  
    public Appointments findAppointmentById(int id) {
        return appointmentRepository.findById(id).orElse(null);
    }
    
    public void cancelAppointment(int id) {
        Appointments appointment = findAppointmentById(id);

        if (appointment != null) {
            appointment.setStatus(Appointments.STATUS_Cancle);  // Change the status to 'Cancelled'
            appointmentRepository.save(appointment);  // Save the updated appointment
        }
    }

 // Method to generate the Appointment ID
    private String generateAppointmentId(String fullname, String appointmentDate) {
        String namePart = fullname.length() >= 4 ? fullname.substring(0, 4).toUpperCase() : fullname.toUpperCase();
        String year = appointmentDate.substring(0, 4);
        return namePart + year;
    }
    
    private void sendSmsNotification(Appointments info) {
    	// SimpleDateFormat fulldatetimeformat = new SimpleDateFormat("E,dd MMM yy @ h:m a");
        String phoneNumber = info.getContact_number(); // Assuming you have this in the AppointmentInfo
        String message = "Hello " + info.getFullname() +", you have been admitted on " + info.getAppointment_date() + 
                         " @ " + info.getAppointment_time() +" @ TechnoCiper HOSPITAL,Pune"+
                         ". Thanks MedicoHelpline";
        smsService.sendSms(phoneNumber, message);
    }
    
    
    public List<Availability> getAllAvailability() {
        return availabilityRepository.findAll();
    }

}
