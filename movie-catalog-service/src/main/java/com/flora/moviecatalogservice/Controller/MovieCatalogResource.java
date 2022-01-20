package com.flora.moviecatalogservice.Controller;
// accepts a userID and returns a payload containing a list of movie details

import com.flora.moviecatalogservice.Model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@Slf4j
public class MovieCatalogResource {

   @Autowired
   private RestTemplate restTemplate;

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
                   Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
                   log.info(movie.getName());

       // put them all together
                   return new CatalogItem(movie.getName(),
                           "Asian animation of demon snake spirit and human boy",
                           rating.getRating());
               })
               .collect(Collectors.toList());
   }
}
