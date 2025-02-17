package com.example.appuser.service;

import com.example.appuser.model.AppUser;
import com.example.appuser.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> getUserById(Long id) {
        return appUserRepository.findById(id);
    }

    public AppUser createUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public Optional<AppUser> updateUser(Long id, AppUser updatedUser) {
        return appUserRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return appUserRepository.save(user);
        });
    }

    public Optional<AppUser> partiallyUpdateUser(Long id, AppUser updatedUser) {
        return appUserRepository.findById(id).map(user -> {
            if (updatedUser.getName() != null) {
                user.setName(updatedUser.getName());
            }
            if (updatedUser.getEmail() != null) {
                user.setEmail(updatedUser.getEmail());
            }
            return appUserRepository.save(user);
        });
    }

    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }
}

