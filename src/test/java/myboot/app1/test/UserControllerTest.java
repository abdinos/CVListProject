package myboot.app1.test;

import lombok.extern.slf4j.Slf4j;
import myboot.app1.security.JwtProvider;
import myboot.app1.security.UserService;
import myboot.app1.web.XUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Slf4j
class UserControllerTest {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserService userService;

    private final String baseUrl = "http://localhost:8081/secu-users/";
    @Test
    public void getUserJWT() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.postForEntity(baseUrl + "login?username=aaa&password=aaa", null,  String.class);

        var JWT = response.getBody();
        log.info(JWT);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + JWT);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<XUserDTO> user = restTemplate.exchange(baseUrl + "aaa",
                HttpMethod.GET, entity, XUserDTO.class);

        assertEquals("aaa", user.getBody().getUsername());
    }

    @Test
    public void logoutUser() {
        // Login user and fetching the JWT
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> jwt = restTemplate
                .postForEntity(baseUrl + "login?username=aaa&password=aaa", null, String.class);


        // Login out user
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt.getBody());
        HttpEntity entity = new HttpEntity(headers);

        var res = restTemplate.exchange(baseUrl + "logout",
                HttpMethod.GET, entity, String.class);

        assert(res.getBody()).equals("user logged out");
    }
}