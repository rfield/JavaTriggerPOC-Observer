package com.cvc.vis.javatrigger.poc.service;

import com.cvc.vis.javatrigger.poc.domain.Movie;

public interface MovieService {

    public Movie getMovie(long id);

    public long addMovie(Movie movie);

    public long getMovieCount();
}
