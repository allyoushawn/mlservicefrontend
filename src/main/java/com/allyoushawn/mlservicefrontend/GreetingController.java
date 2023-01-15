package com.allyoushawn.mlservicefrontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import com.allyoushawn.mlservice.MLServiceResponse;

@Controller
public class GreetingController {
    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);
    private static final String POST_URL = "http://localhost:8081/sentimentAnalysis";
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) throws IOException {
        model.addAttribute("greeting", greeting);

        HttpPost httpPost = new HttpPost(POST_URL);
        httpPost.addHeader("Content-Type", "application/json");
        String text = greeting.getContent();

        StringBuilder json = new StringBuilder();
        json.append("{\"content\":\"");
        json.append(text);
        json.append("\", \"requestId\":\"");
        json.append(greeting.getId());
        json.append("\"}");
        System.out.println(json.toString());

        httpPost.setEntity(new StringEntity(json.toString()));

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        System.out.println("POST Response Status:: "
                + httpResponse.getStatusLine().getStatusCode());

        String responseString = EntityUtils.toString(httpResponse.getEntity());
        MLServiceResponse response = mapper.readValue(responseString, MLServiceResponse.class);
        greeting.setContent("The response from ML Service is: " + response.getContent());


        return "result";
    }

}