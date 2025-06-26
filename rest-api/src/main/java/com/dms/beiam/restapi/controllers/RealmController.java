package com.dms.beiam.restapi.controllers;

import com.dms.beiam.restapi.ResultHandler;
import com.dms.beiam.restapi.adapters.RealmAdapter;
import com.dms.beiam.restapi.operations.v1.system.realm.register.RealmRegisterInput;
import com.dms.beiam.restapi.operations.v1.system.realm.register.RealmRegisterResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.dms.beiam.restapi.configuration.RestApiRoutes.REGISTER_REALM;

@RestController
@RequiredArgsConstructor
public class RealmController {
    private final ResultHandler resultHandler;
    private final RealmAdapter realmAdapter;

    @PostMapping(REGISTER_REALM)
    public ResponseEntity<RealmRegisterResult> realmRegister(@Valid @RequestBody RealmRegisterInput input) {
        return resultHandler.handle(realmAdapter.realmRegister(input), HttpStatus.CREATED);
    }
}
