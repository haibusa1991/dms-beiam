package com.dms.beiam.core.commandexecutor.base;


import com.dms.beiam.core.base.adapter.AdapterMapper;
import com.dms.beiam.restapi.base.RestApiInput;
import com.dms.beiam.restapi.base.RestApiResult;

public interface CommandExecutor {
    <API_INPUT extends RestApiInput,
            COMMAND_INPUT extends CommandInput,
            COMMAND_RESULT extends CommandResult,
            API_RESULT extends RestApiResult> API_RESULT execute(API_INPUT input,
                                                                 AdapterMapper<API_INPUT, COMMAND_INPUT, COMMAND_RESULT, API_RESULT> mapper,
                                                                 Command<COMMAND_RESULT, COMMAND_INPUT> command);
}
