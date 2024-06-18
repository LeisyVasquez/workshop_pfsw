package co.com.poli.moviesservice.clientFeign;

import co.com.poli.moviesservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "showtimes")
public interface ShowtimeClient {
    @GetMapping("api/v1/poli/showtimes/validation-existence/{movieId}")
    Response validExistence(@PathVariable Long movieId);
}
