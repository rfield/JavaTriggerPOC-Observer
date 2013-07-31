package com.cvc.vis.javatrigger.poc.controller;

import com.cvc.vis.javatrigger.poc.domain.Movie;
import com.cvc.vis.javatrigger.poc.service.MovieService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@Resource(name = "restController")
@RequestMapping("/movie")
public class RestController {

    @Resource(name = "movieService")
    private MovieService movieService;

    protected static Logger logger = Logger.getLogger(RestController.class.getName());


    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            headers = "Accept=application/xml,application/json")
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Movie getMovie(@PathVariable("id") Long id) {
        logger.debug("Provider has received request to get person with id: " + id);
        return movieService.getMovie(id);
    }

    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            headers = "Accept=application/xml,application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Movie addMovie(@RequestBody Movie movie) {
        logger.info("Got HTTP POST Hessage: " + movie.toString());
        System.out.println("Got HTTP POST Hessage: " + movie.toString());
        return new Movie(movieService.addMovie(movie), movie.getPrice(), movie.getTitle());
    }
}
