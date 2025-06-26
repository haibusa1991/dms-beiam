package com.dms.beiam.core.mappers;

import com.dms.beiam.core.base.adapter.AdapterMapper;
import com.dms.beiam.core.commands.v1.system.realm.register.BeiamRealmRegisterInput;
import com.dms.beiam.core.commands.v1.system.realm.register.BeiamRealmRegisterResult;
import com.dms.beiam.restapi.operations.v1.system.realm.register.RealmRegisterInput;
import com.dms.beiam.restapi.operations.v1.system.realm.register.RealmRegisterResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RealmRegisterMapper extends AdapterMapper<RealmRegisterInput,
        BeiamRealmRegisterInput,
        BeiamRealmRegisterResult,
        RealmRegisterResult> {
}
