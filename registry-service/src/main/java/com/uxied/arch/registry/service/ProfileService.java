package com.uxied.arch.registry.service;

import com.uxied.arch.registry.domain.Profile;
import com.uxied.arch.registry.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Profile> getPage(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    /**
     * Insert a new profile into <i>profiles</i> table.
     *
     * @param profile Profile instance to be saved.
     * @return The newly created profile's id
     * @throws
     */
    public String save(Profile profile) {
        return profileRepository.save(profile).getId();
    }


}
