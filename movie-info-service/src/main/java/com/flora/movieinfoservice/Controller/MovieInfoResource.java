package com.flora.movieinfoservice.Controller;

import com.flora.movieinfoservice.Model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieInfoResource {

    List<Movie> movies = Arrays.asList(new Movie[]{
            new Movie("123", "White Snake"),
            new Movie("431", "Encanto"),
            new Movie("235", "Boiling Ring")}
    );

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){

        for (Movie m: movies) {
            if (m.getMovieId().equals(movieId)) return m;
        }

        return new Movie();
    }
}
