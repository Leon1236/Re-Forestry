package com.leon1236.reforestry.api.core;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;

public interface IErrorManager {
    @Nullable
    IError getError(short id);

    @Nullable
    IError getError(Identifier errorId);

    List<IError> getErrors();

    IErrorLogic createErrorLogic();

    short getNumericId(IError error);
}
