package ChanuE.MovieTheater.repository.movie;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class MovieSearch {

    private String movieName;
    private String areaName;
    private String specificAreaName;
    private LocalDate localDate;

}
