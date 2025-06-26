package com.dms.beiam.restapi.controllers;

import com.dms.beiam.restapi.base.BaseControllerTest;
import com.dms.beiam.restapi.operations.v1.system.realm.register.RealmRegisterInput;
import com.dms.beiam.restapi.operations.v1.system.realm.register.RealmRegisterResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static com.dms.beiam.restapi.configuration.RestApiRoutes.REGISTER_REALM;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RealmControllerRealmRegisterTest extends BaseControllerTest {
    private final String ROUTE = REGISTER_REALM;
    private final String REALM_NAME = "realmName";
    private final String REALM_ADMIN_EMAIL = "admin@realm.com";

    @Test
    @SneakyThrows
    void returnsErrorWhenRealmNameIsBlank() {
        RealmRegisterInput input = RealmRegisterInput
                .builder()
                .realmName(" ")
                .realmAdminEmail(REALM_ADMIN_EMAIL)
                .build();

        String content = objectMapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writeValueAsString(input);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ROUTE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value("about:blank"))
                .andExpect(jsonPath("$.title").value("Bad Request"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.detail").value("realmName must not be blank"))
                .andExpect(jsonPath("$.instance").value(ROUTE))
                .andExpect(jsonPath("$.beiamErrorCode").value("BRV001"));
    }

    @Test
    @SneakyThrows
    void returnsErrorWhenRealmNameIsNull() {
        RealmRegisterInput input = RealmRegisterInput
                .builder()
                .realmAdminEmail(REALM_ADMIN_EMAIL)
                .build();

        String content = objectMapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writeValueAsString(input);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ROUTE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value("about:blank"))
                .andExpect(jsonPath("$.title").value("Bad Request"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.detail").value("realmName must not be blank"))
                .andExpect(jsonPath("$.instance").value(ROUTE))
                .andExpect(jsonPath("$.beiamErrorCode").value("BRV001"));
    }

    @Test
    @SneakyThrows
    void returnsErrorWhenRealmNameIsEmpty() {
        RealmRegisterInput input = RealmRegisterInput
                .builder()
                .realmName("")
                .realmAdminEmail(REALM_ADMIN_EMAIL)
                .build();

        String content = objectMapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writeValueAsString(input);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ROUTE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value("about:blank"))
                .andExpect(jsonPath("$.title").value("Bad Request"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.detail").value("realmName must not be blank"))
                .andExpect(jsonPath("$.instance").value(ROUTE))
                .andExpect(jsonPath("$.beiamErrorCode").value("BRV001"));
    }

    @Test
    @SneakyThrows
    void returnsErrorWhenRealmAdminEmailIsNull() {
        RealmRegisterInput input = RealmRegisterInput
                .builder()
                .realmName(REALM_NAME)
                .build();

        String content = objectMapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writeValueAsString(input);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ROUTE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value("about:blank"))
                .andExpect(jsonPath("$.title").value("Bad Request"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.detail").value("realmAdminEmail must not be null"))
                .andExpect(jsonPath("$.instance").value(ROUTE))
                .andExpect(jsonPath("$.beiamErrorCode").value("BRV001"));
    }

    @Test
    @SneakyThrows
    void returnsErrorWhenRealmAdminEmailIsInvalid() {
        RealmRegisterInput input = RealmRegisterInput
                .builder()
                .realmName(REALM_NAME)
                .realmAdminEmail("invalid-email")
                .build();

        String content = objectMapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writeValueAsString(input);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ROUTE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value("about:blank"))
                .andExpect(jsonPath("$.title").value("Bad Request"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.detail").value("realmAdminEmail must be a well-formed email address"))
                .andExpect(jsonPath("$.instance").value(ROUTE))
                .andExpect(jsonPath("$.beiamErrorCode").value("BRV001"));
    }

    @Test
    @SneakyThrows
    void returnsErrorWhenAdminEmailIsBlank() {
        RealmRegisterInput input = RealmRegisterInput
                .builder()
                .realmName(REALM_NAME)
                .realmAdminEmail("  ")
                .build();

        String content = objectMapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writeValueAsString(input);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ROUTE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value("about:blank"))
                .andExpect(jsonPath("$.title").value("Bad Request"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.detail").value("realmAdminEmail must be a well-formed email address"))
                .andExpect(jsonPath("$.instance").value(ROUTE))
                .andExpect(jsonPath("$.beiamErrorCode").value("BRV001"));
    }

    @Test
    @SneakyThrows
    void returnsErrorWhenAdminEmailIsEmpty() {
        RealmRegisterInput input = RealmRegisterInput
                .builder()
                .realmName(REALM_NAME)
                .realmAdminEmail("  ")
                .build();

        String content = objectMapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writeValueAsString(input);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ROUTE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value("about:blank"))
                .andExpect(jsonPath("$.title").value("Bad Request"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.detail").value("realmAdminEmail must be a well-formed email address"))
                .andExpect(jsonPath("$.instance").value(ROUTE))
                .andExpect(jsonPath("$.beiamErrorCode").value("BRV001"));
    }

    @Test
    @SneakyThrows
    void returnsCorrect() {
        RealmRegisterInput input = RealmRegisterInput
                .builder()
                .realmName(REALM_NAME)
                .realmAdminEmail(REALM_ADMIN_EMAIL)
                .build();

        String content = objectMapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writeValueAsString(input);

        UUID realmId = UUID.fromString("0000000-0000-0000-0000-000000000000");
        RealmRegisterResult adapterResult = RealmRegisterResult
                .builder()
                .realmId(realmId)
                .build();

        when(realmAdapter.realmRegister(any())).thenReturn(adapterResult);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ROUTE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.realmId").value(realmId.toString()));
    }
}