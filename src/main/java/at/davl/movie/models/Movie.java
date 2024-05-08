package at.davl.movie.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(nullable = false, length = 200, name = "title")
    @NotBlank(message = "Please provide movie's title!")
    private String title;

    @Column(nullable = false, length = 200, name = "director")
    @NotBlank(message = "Please provide movie's director")
    private String director;

    @Column(nullable = false, length = 200, name = "studio")
    @NotBlank(message = "Please provide movie's studio!")
    private String studio;

    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast;

    @Column(nullable = false, name = "releaseYear")
    @NotBlank(message = "Please provide movie's release year!")
    private Integer releaseYear;

    @Column(nullable = false, name = "poster")
    @NotBlank(message = "Please provide movie's poster!")
    private String poster;


}
