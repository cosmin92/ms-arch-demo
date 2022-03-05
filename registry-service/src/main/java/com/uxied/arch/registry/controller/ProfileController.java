package com.uxied.arch.registry.controller;

import com.uxied.arch.common.registry.CreateProfile;
import com.uxied.arch.registry.mapper.ProfileMappers;
import com.uxied.arch.registry.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileMappers profileMappers;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return profileService.get(id)
                .map(profile -> ResponseEntity.ok(profileMappers.map(profile)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateProfile profile) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(profileService.save(profileMappers.map(profile)))
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
