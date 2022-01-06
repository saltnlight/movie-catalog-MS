package com.flora.ratingsdataservice.Controller;

import com.flora.ratingsdataservice.Model.Rating;
import com.flora.ratingsdataservice.Model.UserRating;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @RequestMapping(value = "/movies/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

//    @RequestMapping("/user/{userId}")
//    public UserRating getUserRatings(@PathVariable("userId") String userId) {
//        UserRating userRating = new UserRating();
//        userRating.initData(userId);
//        return userRating;
//
//    }

}
