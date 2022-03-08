package com.uxied.arch.registry.controller;

import com.uxied.arch.common.registry.CreateProfile;
import com.uxied.arch.common.registry.ViewProfile;
import com.uxied.arch.registry.domain.Profile;
import com.uxied.arch.registry.mapper.ProfileMappers;
import com.uxied.arch.registry.service.CreditCardService;
import com.uxied.arch.registry.service.ProfileService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@WebMvcTest(controllers = ProfileController.class)
public class ProfileControllerTest {

    @MockBean
    private ProfileService profileService;

    @MockBean
    private ProfileMappers profileMappers;

    @MockBean
    private CreditCardService creditCardService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<ViewProfile> jsonViewProfile;

    @Autowired
    private JacksonTester<CreateProfile> jsonCreateProfile;

    @Test
    @DisplayName("Should create a new profile when making POST request to endpoint - /profiles")
    void shouldSuccessfullyCreateProfile() throws Exception {
        // GIVEN
        String profileId = "53530dbf-addd-4ab4-adfc-8333552752a6";
        Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse("1955-07-23");
        CreateProfile profile = new CreateProfile(profileId, "Maryanne", "Roskelly", "+86 (197) 716-2950",
                "Female", birthdate);

        given(profileService.save(new Profile())).willReturn(profileId);

        // WHEN
        MockHttpServletResponse response = mockMvc.perform(
                post("/profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCreateProfile.write(profile).getJson())
        ).andReturn().getResponse();

        // THEN
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getHeader("Location")).isNotBlank();
    }

    @Test
    @DisplayName("Should return a profile when making GET request to endpoint - /profiles/{id}")
    void shouldReturnProfileWhenMakingGetWithExistingId() throws Exception {
        // GIVEN
        String profileId = "53530dbf-addd-4ab4-adfc-8333552752a6";
        Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse("1955-07-23");
        Profile profile = new Profile(profileId, "Maryanne", "Roskelly", "+86 (197) 716-2950",
                "Female", birthdate, List.of());
        ViewProfile viewProfile = new ViewProfile(profileId, "Maryanne", "Roskelly", "+86 (197) 716-2950",
                "Female", birthdate);

        given(profileService.get(profileId)).willReturn(Optional.of(profile));
        given(profileMappers.map(profile)).willReturn(viewProfile);
        // WHEN
        MockHttpServletResponse response = mockMvc.perform(
                get("/profiles/" + profileId).accept(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // THEN
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonViewProfile.write(profileMappers.map(profile)).getJson());
    }

    @Test
    @DisplayName("Should return NOT FOUND when making GET request to endpoint - /profiles/{id} with non existing id")
    void shouldReturnNotFoundWhenMakingGetWithNonExistingId() throws Exception {
        // GIVEN
        String profileId = "53530dbf-addd-4ab4-adfc-8333552752a63";
        given(profileService.get(profileId)).willReturn(Optional.empty());

        // WHEN
        MockHttpServletResponse response = mockMvc.perform(
                get("/profiles/" + profileId).accept(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // THEN
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

    @Test
    @DisplayName("Should return a page when making GET request to endpoint - /profiles")
    void shouldReturnPAgeWhenMakingGetRequest() throws Exception {
        // GIVEN
        String profileId = "53530dbf-addd-4ab4-adfc-8333552752a63";
        given(profileService.get(profileId)).willReturn(Optional.empty());

        // WHEN
        MockHttpServletResponse response = mockMvc.perform(
                get("/profiles/" + profileId).accept(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // THEN
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }
}
