package com.lurodev.adminsgestioninspecciones.globalServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class ExternalApiRequest {

    public ExternalApiRequest() {
    }


    public String convertObjectToJson(Object object){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        }
        catch (JsonProcessingException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public Object getDataFromApi(String endPoint) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(endPoint, Object.class);
            return response.getBody();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object postDataToApi(String endPoint, String requestBody) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<Object> response = restTemplate.postForEntity(endPoint, requestEntity, Object.class);
            return response.getBody();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateDataInApi(String endpoint, String requestBody) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            restTemplate.put(endpoint, requestEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDataFromApi(String endpoint) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(endpoint);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
