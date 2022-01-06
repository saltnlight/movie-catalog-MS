package com.flora.moviecatalogservice.Controller;

import com.flora.moviecatalogservice.Model.CatalogItem;
import com.flora.moviecatalogservice.Model.Movie;
import com.flora.moviecatalogservice.Model.Rating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@Slf4j
public class MovieCatalogWithWebClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    //    create multiple instances of the catalogItem
    @GetMapping("/{userId}")
    public List<CatalogItem> getMovieCatalog(@PathVariable("userId") String userId){

        // get all rated movieId - hardcoded response from rating API - for now
        List<Rating> ratings = Arrays.asList(
                new Rating("123", 4),
                new Rating("2350",2),
                new Rating("431",3)
        );

        // foreach movieId - call info service to get details
        return ratings.stream().map(
            rating -> {
                Movie movie =  webClientBuilder.build()
                                .get()
                                .uri("http://localhost:8082/movies/"+rating.getMovieId())
                                .retrieve()
                                .bodyToMono(Movie.class)
                                .block();

                log.info(movie.getName());

                // put them all together
                return new CatalogItem(movie.getName(),
                        "Asian animation of demon snake spirit and human boy",
                        rating.getRating());
            }
        ).collect(Collectors.toList());
    }

}
