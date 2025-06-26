package com.dms.beiam.core.adapters;

import com.dms.beiam.core.commandexecutor.base.CommandExecutor;
import com.dms.beiam.core.commands.v1.system.realm.register.RealmRegister;
import com.dms.beiam.core.mappers.RealmRegisterMapper;
import com.dms.beiam.restapi.adapters.RealmAdapter;
import com.dms.beiam.restapi.operations.v1.system.realm.register.RealmRegisterInput;
import com.dms.beiam.restapi.operations.v1.system.realm.register.RealmRegisterResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeiamRealmAdapter implements RealmAdapter {
    private final CommandExecutor commandExecutor;

    //mappers
    private final RealmRegisterMapper realmRegisterMapper;

    //commands
    private final RealmRegister realmRegister;


    @Override
    public RealmRegisterResult realmRegister(RealmRegisterInput input) {
        return commandExecutor.execute(input,realmRegisterMapper, realmRegister);
    }
}
