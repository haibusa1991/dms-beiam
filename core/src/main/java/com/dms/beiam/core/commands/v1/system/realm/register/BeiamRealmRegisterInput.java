package com.dms.beiam.core.commands.v1.system.realm.register;

import com.dms.beiam.core.commandexecutor.base.CommandInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class BeiamRealmRegisterInput implements CommandInput {

    private String realmName;
    private String realmAdminEmail;
}
