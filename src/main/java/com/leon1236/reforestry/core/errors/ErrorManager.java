package com.leon1236.reforestry.core.errors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.core.IErrorManager;

public final class ErrorManager implements IErrorManager {
    private final Map<Identifier, IError> byId = new HashMap<>();
    private final Map<IError, Short> numericIds = new HashMap<>();
    private final List<IError> errors = new ArrayList<>();

    @Override
    @Nullable
    public IError getError(short id) {
        if (id < 0 || id >= errors.size()) {
            return null;
        }
        return errors.get(id);
    }

    @Override
    @Nullable
    public IError getError(Identifier errorId) {
        return byId.get(errorId);
    }

    @Override
    public List<IError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    @Override
    public IErrorLogic createErrorLogic() {
        return new ErrorLogic();
    }

    @Override
    public short getNumericId(IError error) {
        return numericIds.getOrDefault(error, (short) -1);
    }

    public void register(IError error) {
        if (byId.putIfAbsent(error.getId(), error) != null) {
            return;
        }
        short id = (short) errors.size();
        errors.add(error);
        numericIds.put(error, id);
    }
}
