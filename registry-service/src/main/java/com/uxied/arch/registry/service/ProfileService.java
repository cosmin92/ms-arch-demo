package com.uxied.arch.registry.service;

import com.uxied.arch.registry.domain.Profile;
import com.uxied.arch.registry.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Optional<Profile> get(String id) {
        return profileRepository.findById(id);
    }

    /**
     *
     *
     * @param newProfile - Profile instance to be saved.
     * @return The newly created profile's id
     */
    public String save(Profile newProfile){
        return profileRepository.save(newProfile).getId();
    }

}
