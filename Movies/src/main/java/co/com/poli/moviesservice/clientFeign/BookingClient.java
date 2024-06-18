package co.com.poli.moviesservice.clientFeign;

import co.com.poli.moviesservice.helpers.Response;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "bookings")
public interface BookingClient {
    @GetMapping("api/v1/poli/bookings/validation-existence/{movieId}")
    Response validExistence(@PathVariable Long movieId);
}