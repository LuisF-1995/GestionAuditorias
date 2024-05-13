package com.lurodev.ApiGestionInspecciones.globalService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ExternalApiRequest {

    public ExternalApiRequest() {
    }

    public List<NameValuePair> convertToFormData(Map<String, String> parameters){
        List<NameValuePair> paramsFormData = new ArrayList<>();
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            paramsFormData.add(new BasicNameValuePair(parameter.getKey(), parameter.getValue()));
        }
        return paramsFormData;
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
