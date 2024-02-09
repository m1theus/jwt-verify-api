package dev.mmartins.jwtverifyapi.integration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mmartins.jwtverifyapi.rest.TokenRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VerifyTokenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void ShouldReturnOkAndTrueIfTokenIsValid() throws Exception {
        final String token = JWT
                .create()
                .withClaim("Role", "Admin")
                .withClaim("Seed", "2")
                .withClaim("Name", "José Silva")
                .sign(Algorithm.none());

        performPost(token)
                .andExpect(status().isOk())
                .andExpect(jsonPath("valid").value(true));
    }

    @Test
    void ShouldReturnOkAndFalseIfTokenHasInvalidRole() throws Exception {
        final String token = JWT
                .create()
                .withClaim("Role", "Manager")
                .withClaim("Seed", "2")
                .withClaim("Name", "José Silva")
                .sign(Algorithm.none());

        performPost(token)
                .andExpect(status().isOk())
                .andExpect(jsonPath("valid").value(false));
    }

    @Test
    void ShouldReturnOkAndFalseIfTokenHasInvalidSeed() throws Exception {
        final String token = JWT
                .create()
                .withClaim("Role", "Manager")
                .withClaim("Seed", "1")
                .withClaim("Name", "José Silva")
                .sign(Algorithm.none());

        performPost(token)
                .andExpect(status().isOk())
                .andExpect(jsonPath("valid").value(false));
    }

    @Test
    void ShouldReturnOkAndFalseIfTokenHasInvalidName() throws Exception {
        final String token = JWT
                .create()
                .withClaim("Role", "Manager")
                .withClaim("Seed", "1")
                .withClaim("Name", "M4ria S1lv4")
                .sign(Algorithm.none());

        performPost(token)
                .andExpect(status().isOk())
                .andExpect(jsonPath("valid").value(false));
    }

    @Test
    void ShouldReturnOkAndFalseIfTokenIsInvalid() throws Exception {
        final String token = JWT
                .create()
                .withClaim("", "u�\\u001d~��Ȕ��\\u001aȎ��Y\\u001bZ[��\\b�ٮB#�#s�C\\u0012\\\"�$�\\u0016�R#�%F������\\u0004\\u0017&\\u0017V��")
                .sign(Algorithm.none());

        performPost(token)
                .andExpect(status().isOk())
                .andExpect(jsonPath("valid").value(false));
    }

    private ResultActions performPost(final String token) throws Exception {
        return mockMvc.perform(post("/token/verify")
                .content(new ObjectMapper().writeValueAsString(new TokenRequest(token)))
                .contentType(MediaType.APPLICATION_JSON));
    }

}
