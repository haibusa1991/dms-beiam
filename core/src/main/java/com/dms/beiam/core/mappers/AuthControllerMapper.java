package com.dms.beiam.core.mappers;

import com.dms.beiam.core.base.adapter.AdapterMapper;
import com.dms.beiam.core.commands.v1.register.BeiamRegisterIdentityInput;
import com.dms.beiam.core.commands.v1.register.BeiamRegisterIdentityResult;
import com.dms.beiam.restapi.operations.v1.register.RegisterIdentityInput;
import com.dms.beiam.restapi.operations.v1.register.RegisterIdentityResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthControllerMapper extends AdapterMapper<RegisterIdentityInput,
        BeiamRegisterIdentityInput,
        BeiamRegisterIdentityResult,
        RegisterIdentityResult> {
}
