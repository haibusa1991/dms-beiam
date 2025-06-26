package com.dms.beiam.application.integration;

import com.dms.beiam.application.integration.base.BaseIntegrationTest;
import com.dms.beiam.persistence.entities.Realm;
import com.dms.beiam.restapi.operations.v1.system.realm.register.RealmRegisterInput;
import com.jayway.jsonpath.JsonPath;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.dms.beiam.restapi.configuration.RestApiRoutes.REGISTER_REALM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegisterRealmTest extends BaseIntegrationTest {
    private final String ROUTE = REGISTER_REALM;

    @Test
    @SneakyThrows
    void returns200WhenRegisterRealm() {
        String realmName = "test-realm";
        String adminEmail = "admin@realm.com";

        RealmRegisterInput input = RealmRegisterInput
                .builder()
                .realmName(realmName)
                .realmAdminEmail(adminEmail)
                .build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post(ROUTE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals(1, realmRepository.count());

        Realm realm = realmRepository.findAll().getFirst();
        assertEquals(realmName, realm.getRealmName());
        assertEquals(adminEmail, realm.getRealmAdminEmail());

        String response = result.getResponse().getContentAsString();
        String responseRealmId = JsonPath.read(response, "$.realmId");
        assertEquals(realm.getId().toString(), responseRealmId);
    }

    @Test
    @SneakyThrows
    void returns400WhenRealmAlreadyExists() {
        String existingRealmName = "existing-realm";

        Realm realm = Realm
                .builder()
                .realmName(existingRealmName)
                .realmAdminEmail("admin@existing-realm.com")
                .build();

        realmRepository.save(realm);

        RealmRegisterInput input = RealmRegisterInput
                .builder()
                .realmName(existingRealmName)
                .realmAdminEmail("admin@realm.com")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ROUTE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.type").value("about:blank"))
                .andExpect(jsonPath("$.title").value("BRR001"))
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.detail").value("Realm with the given name already exists."))
                .andExpect(jsonPath("$.instance").value(ROUTE))
                .andExpect(jsonPath("$.properties").doesNotExist());
    }
}
