package com.jo.zain.RestFullClient;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



public class App 
{
	public static final String 
	PATIENT_URL_SERVICES = "http://localhost:8080/restws/services/patientservice";
	
    public static void main( String[] args )
    {
    	// to get patient with wrapping back patient object
        Client client = ClientBuilder.newClient();
        WebTarget target = 
        		client.target(PATIENT_URL_SERVICES)
        		.path("/patients")
        		.path("/{id}")
        		.resolveTemplate("id", "123");
        Patient patient = target.request().get(Patient.class);
        System.out.println("Response Status : " + patient.getName());
        
        // PUT
        patient.setName("MahmoudOdeh");
        WebTarget putTarget = client.target(PATIENT_URL_SERVICES).path("/patients");
        Response patientUpdate = putTarget.request().put(Entity.entity
        		(patient, MediaType.APPLICATION_XML));
        System.out.println("Response Status : " + patientUpdate.getStatus());
        patientUpdate.close();
        
        
        //POST 
        Patient postPatient = new Patient();
        postPatient.setName("after posted");
        
        WebTarget postTarget = client.target(PATIENT_URL_SERVICES).path("/patients");
        Patient post = postTarget.request().post(Entity.entity(postPatient, MediaType.APPLICATION_XML), Patient.class);
        System.out.println("Response Status : " + post.getId());
        
        //DELETE
        WebTarget deleteTarget = 
        		client.target(PATIENT_URL_SERVICES)
        		.path("/patients")
        		.path("/{id}")
        		.resolveTemplate("id", "125");
        Response deleteResponse = target.request().get();
        System.out.println("Delete Response Status : " + deleteResponse.getStatus());
        deleteResponse.close();
        client.close();
        
    }
}
