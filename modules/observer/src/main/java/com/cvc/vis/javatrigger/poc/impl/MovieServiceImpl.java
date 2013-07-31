package com.cvc.vis.javatrigger.poc.impl;

import com.cvc.vis.javatrigger.poc.domain.Movie;
import com.cvc.vis.javatrigger.poc.service.MovieService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

    protected static Logger logger = Logger.getLogger(MovieServiceImpl.class.getName());

    public MovieServiceImpl() {
        init();
    }

    // In-memory list
    private List<Movie> movies = new ArrayList<Movie>();

    private void init() {
        addMovie(9.95, "I am Legend");
        addMovie(5.95, "Monsters, Inc.");
    }

    public Movie getMovie(long id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public long addMovie(Movie movie) {
        int idTodSet = movies.size() + 1;
        movie.setId(idTodSet);
        movies.add(movie);
        return idTodSet;
    }

    public long addMovie(double price, String title) {
        Movie movie = new Movie(movies.size() + 1, price, title);
        return addMovie(movie);
    }

    public long getMovieCount() {
        return movies.size();
    }
}


