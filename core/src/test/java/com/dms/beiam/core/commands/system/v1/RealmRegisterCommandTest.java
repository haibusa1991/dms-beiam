package com.dms.beiam.core.commands.system.v1;

import com.dms.beiam.core.commands.v1.system.realm.register.BeiamRealmRegisterInput;
import com.dms.beiam.core.commands.v1.system.realm.register.BeiamRealmRegisterResult;
import com.dms.beiam.core.commands.v1.system.realm.register.RealmRegisterCommand;
import com.dms.beiam.persistence.entities.Realm;
import com.dms.beiam.persistence.repositories.RealmRepository;
import com.dms.beiam.restapi.errorhandler.exceptions.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RealmRegisterCommandTest {
    @Mock
    RealmRepository realmRepository;

    @InjectMocks
    private RealmRegisterCommand command;

    private final String REALM_NAME = "testRealm";
    private final String REALM_ADMIN_EMAIL = "admin@realm.com";

    private final BeiamRealmRegisterInput INPUT = BeiamRealmRegisterInput
            .builder()
            .realmName(REALM_NAME)
            .realmAdminEmail(REALM_ADMIN_EMAIL)
            .build();

    @Test
    void throwExceptionWhenRealmAlreadyExists() {
        when(realmRepository.existsByRealmName(REALM_NAME)).thenReturn(true);

        BusinessException exception = assertThrows(BusinessException.class, () -> command.execute(INPUT));

        assertEquals("Realm with the given name already exists.", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
        assertEquals("BRR001", exception.getErrorCode());
    }

    @Test
    void registersRealmSuccessfully() {
        when(realmRepository.existsByRealmName(REALM_NAME)).thenReturn(false);

        UUID realmId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        when(realmRepository.save(any())).thenAnswer(answer ->{
            Realm realm = answer.getArgument(0, Realm.class);

            Field id = realm.getClass().getDeclaredField("id");
            id.setAccessible(true);
            id.set(realm,realmId);

            return realm;
        });

        BeiamRealmRegisterResult result = command.execute(INPUT);

        assertEquals(realmId, result.getRealmId());
    }
}