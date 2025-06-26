package com.dms.beiam.core.base.adapter;

public interface AdapterMapper<API_INPUT, COMMAND_INPUT, COMMAND_RESULT, API_RESULT> {

    COMMAND_INPUT toCommandInput(API_INPUT input);
    API_RESULT toApiResult(COMMAND_RESULT result);
}
