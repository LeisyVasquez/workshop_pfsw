package com.example.user.clientFeign;

import com.example.user.helper.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "bookings")
public interface BookingClient {
    @GetMapping("api/v1/poli/bookings/user/{userId}")
    Response findBookingByUserId(@PathVariable Long userId);
}
