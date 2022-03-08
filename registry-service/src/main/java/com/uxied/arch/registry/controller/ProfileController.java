package com.uxied.arch.registry.controller;

import com.uxied.arch.common.registry.CreateProfile;
import com.uxied.arch.registry.domain.Profile;
import com.uxied.arch.registry.mapper.ProfileMappers;
import com.uxied.arch.registry.service.CreditCardService;
import com.uxied.arch.registry.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileMappers profileMappers;
    private final CreditCardService creditCardService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return profileService.get(id)
                .map(profile -> ResponseEntity.ok(profileMappers.map(profile)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Profile> profiles = profileService.getPage(PageRequest.of(page, size));
        return ResponseEntity.ok(profileMappers.map(profiles));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateProfile profile) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(profileService.save(profileMappers.map(profile)))
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/cards")
    public ResponseEntity<?> getCreditCards(@PathVariable String id) {
        return ResponseEntity.ok(creditCardService.findByOwnerId(id).stream().map(profileMappers::map).collect(Collectors.toList()));
    }
}
