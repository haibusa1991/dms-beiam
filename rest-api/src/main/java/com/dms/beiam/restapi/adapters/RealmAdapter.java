package com.dms.beiam.restapi.adapters;

import com.dms.beiam.restapi.operations.v1.system.realm.register.RealmRegisterInput;
import com.dms.beiam.restapi.operations.v1.system.realm.register.RealmRegisterResult;

public interface RealmAdapter {

    RealmRegisterResult realmRegister(RealmRegisterInput input);
}
