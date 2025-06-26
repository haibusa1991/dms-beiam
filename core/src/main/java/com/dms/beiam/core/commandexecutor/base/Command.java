package com.dms.beiam.core.commandexecutor.base;


public interface Command<R extends CommandResult, I extends CommandInput> {
    R execute(I input);
}
