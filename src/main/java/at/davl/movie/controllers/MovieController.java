package at.davl.movie.controllers;


import at.davl.movie.dto.MovieDto;
import at.davl.movie.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add-movie")
    public ResponseEntity<MovieDto> addMovieHandler(@RequestPart MultipartFile file,
                                                    @RequestPart String movieDto) throws IOException {
        System.out.println("Movie Controller 32");
        MovieDto convertedDto = convertToMovieDto(movieDto);
        System.out.println("Movie Controller 34");
        return new ResponseEntity<>(movieService.addMovie(convertedDto, file), HttpStatus.CREATED);
    }

    private MovieDto convertToMovieDto(String movieDtoObj) throws JsonProcessingException {
        // mapper (we need it for a map value to the MovieDto class)
        ObjectMapper objectMapper = new ObjectMapper();
        // it mapped movie to MovieDto
        MovieDto movieDto = objectMapper.readValue(movieDtoObj, MovieDto.class);
        return movieDto;
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMovieHandler(@PathVariable Integer movieId){
        return ResponseEntity.ok(movieService.getMovie(movieId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getAllMoviesHandler() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PutMapping("/update/{movieId}")
    public ResponseEntity<MovieDto> updateMovieHandler(@PathVariable Integer movieId,
                                                       @RequestPart MultipartFile file,
                                                       @RequestPart String movieDtoObj) throws IOException {

        if (file.isEmpty()) file = null;
        MovieDto movieDto = convertToMovieDto(movieDtoObj);
        return ResponseEntity.ok(movieService.updateMovie(movieId, movieDto, file));
    }


    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<String> deleteMovieHandler(@PathVariable Integer movieId) throws IOException {
        return ResponseEntity.ok(movieService.deleteMovie(movieId));
    }
}

