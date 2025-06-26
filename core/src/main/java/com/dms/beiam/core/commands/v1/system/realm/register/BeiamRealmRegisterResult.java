package com.dms.beiam.core.commands.v1.system.realm.register;

import com.dms.beiam.core.commandexecutor.base.CommandResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class BeiamRealmRegisterResult implements CommandResult {

    private UUID realmId;
}
