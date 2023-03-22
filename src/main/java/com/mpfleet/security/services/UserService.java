package com.mpfleet.security.services;

import com.mpfleet.security.models.User;
import com.mpfleet.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

        private final BCryptPasswordEncoder encoder;
        private final UserRepository userRepository;

        @Autowired
        public UserService(BCryptPasswordEncoder encoder, UserRepository userRepository) {
            this.encoder = encoder;
            this.userRepository = userRepository;
        }

        public List<User> findAll() {
            return userRepository.findAll();
        }

        public User findById(Long id) {
            return userRepository.findById(id).orElse(null);
        }

        public void delete(Long id) {
            userRepository.deleteById(id);
        }

        public void save(User user) {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }
