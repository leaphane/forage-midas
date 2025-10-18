package com.jpmc.midascore.component;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jpmc.midascore.foundation.Transaction;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserApiClient {

    public long postRequest(Transaction transaction){

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Transaction> request = new HttpEntity<>(transaction, headers);
        ResponseEntity<String> responseEntity = template.postForEntity("http://localhost:8080/incentive", request, String.class);

        JSONObject incentive = new JSONObject(responseEntity.getBody());

        return incentive.getLong("amount");
    }


}
