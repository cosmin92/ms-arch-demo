package com.uxied.arch.registry.controller;


import com.uxied.arch.common.registry.ViewProfile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureJsonTesters
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileControllerIntegrationTest {

    private static final String INSERT_STATEMENT = "INSERT INTO profiles (id, first_name, last_name, phone, gender, birthday) " +
            "VALUES ('51e2a956-437b-4d11-8d94-e2e800f76554', 'Devi', 'Greenhall', '+33 (796) 145-6108', 'Male', '1926-02-09');";
    private static final String DELETE_STATEMENT = "DELETE FROM profiles WHERE id='51e2a956-437b-4d11-8d94-e2e800f76554'";

    @LocalServerPort
    private int port;

    @Autowired
    private JacksonTester<ViewProfile> jsonViewProfile;


    private static TestRestTemplate restTemplate;
    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        restTemplate = new TestRestTemplate();
        headers = new HttpHeaders();
    }

//    @Test
//    public void testCreateStudent() throws Exception {
//        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                createURLWithPort("/profiles"), HttpMethod.POST, entity, String.class);
//
//        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
//
//        assertTrue(actual.contains("/students"));
//    }

    @Test
    @Sql(statements = INSERT_STATEMENT, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = DELETE_STATEMENT, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldRetrieveProfileById() throws Exception {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<ViewProfile> response = restTemplate.exchange(url("/51e2a956-437b-4d11-8d94-e2e800f76554"),
                HttpMethod.GET, entity, ViewProfile.class);

        Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse("1926-02-09");
        ViewProfile profile = new ViewProfile("51e2a956-437b-4d11-8d94-e2e800f76554", "Devi",
                "Greenhall", "Male", "+33 (796) 145-6108", birthdate);

        assertThat(jsonViewProfile.write(response.getBody()).getJson()).isEqualTo(jsonViewProfile.write(profile).getJson());

    }

    private String url(String path) {
        return "http://localhost:" + port + "/profiles" + path;
    }
}
