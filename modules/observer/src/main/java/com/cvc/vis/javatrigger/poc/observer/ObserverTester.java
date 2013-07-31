package com.cvc.vis.javatrigger.poc.observer;

import com.cvc.vis.javatrigger.poc.domain.Movie;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: rfield
 * Date: 6/24/13
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObserverTester {

    public void testHttpGet() {

        // TODO can we Autowire this using @Autowired?
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("client-applicationContext.xml");
        RestTemplate restTemplate = (RestTemplate) applicationContext.getBean("restTemplate");


        //  Test HTTP GET
        Movie movie = restTemplate.getForObject(
                "http://localhost:8888/javatrigger/poc/movie/2",
                Movie.class);
        System.out.println("Price: " + movie.getPrice());
        System.out.println("Title: " + movie.getTitle());


        // Test HTTP POST
//        String requestString = "{\"price\":11.25,\"title\":\"The End of Days\"}";
        Movie requestBody = new Movie(-1, 11.25, "The End of Days");

        HttpHeaders headers = new HttpHeaders();
        ArrayList<MediaType> acceptList = new ArrayList<MediaType>();
        acceptList.add(MediaType.APPLICATION_JSON);
        headers.setAccept(acceptList);
        headers.setContentType(MediaType.APPLICATION_JSON);

//        HttpEntity request = new HttpEntity(requestString, headers);
        HttpEntity request = new HttpEntity(requestBody, headers);

        Movie returnedMovie = restTemplate.postForObject(
                "http://localhost:8888/javatrigger/poc/movie/add",
                request,
                Movie.class);

        System.out.println("Returned Movie: " + returnedMovie.toString());
    }


    public static void main(String arg[]) {
        ObserverTester client = new ObserverTester();
        client.testHttpGet();
    }
}
