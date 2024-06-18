package com.example.user.service;

import com.example.user.clientFeign.BookingClient;
import com.example.user.model.Booking;
import com.example.user.persistence.entity.User;
import com.example.user.persistence.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookingClient bookingClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(User user) {
        ModelMapper modelMapper = new ModelMapper();
        Booking booking = modelMapper.map(bookingClient.findBookingByUserId(user.getId()).getData(), Booking.class);
        if(booking.getId() != null) {
            return true;
        } else {
            userRepository.delete(user);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
