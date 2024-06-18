package com.example.user.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class Booking {
    private Long id;
    private Long userId;
    private Long showtimeId;
    private String movies;
}
