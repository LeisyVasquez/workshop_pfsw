package co.com.poli.moviesservice.service;

import co.com.poli.moviesservice.clientFeign.BookingClient;
import co.com.poli.moviesservice.clientFeign.ShowtimeClient;
import co.com.poli.moviesservice.helpers.Response;
import co.com.poli.moviesservice.persistence.entity.Movies;
import co.com.poli.moviesservice.persistence.repository.MoviesRepository;
import co.com.poli.moviesservice.service.dto.MoviesInDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MoviesServiceImpl implements IMoviesServices {

    private final MoviesRepository moviesRepository;
    private final BookingClient bookingClient;
    private final ShowtimeClient showtimeClient;



    public Movies save(MoviesInDTO moviesInDTO) {
        Movies movie = new Movies();
        movie.setTitle(moviesInDTO.getTitle());
        movie.setDirector(moviesInDTO.getDirector());
        movie.setRating(moviesInDTO.getRating());
        return moviesRepository.save(movie);
    }

    public String delete(Long id) {
        Optional<Movies> movie = moviesRepository.findById(id);
        if (movie.isPresent()) {
            Response existBooking = bookingClient.validExistence(id);
            if (Objects.equals(existBooking.getData().toString(), "true")) {
                return "existe_booking";
            }
            Response existShowtime = showtimeClient.validExistence(id);
            if (Objects.equals(existShowtime.getData().toString(), "true")) {
                return "existe_showtime";
            }
            moviesRepository.delete(movie.get());
            return "eliminado";
        }
        return "inexistente";
    }

    public List<Movies> findAll() {
        return moviesRepository.findAll();
    }

    public Movies findById(Long id) {
        return moviesRepository.findById(id).orElse(null);
    }
}
