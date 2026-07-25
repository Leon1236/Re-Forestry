package com.leon1236.reforestry.core.errors;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.core.IErrorLogic;

public final class ErrorLogic implements IErrorLogic {
    private final Set<IError> errors = new HashSet<>();

    @Override
    public boolean setCondition(boolean condition, IError error) {
        if (condition) {
            errors.add(error);
        } else {
            errors.remove(error);
        }
        return condition;
    }

    @Override
    public boolean contains(IError error) {
        return errors.contains(error);
    }

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    @Override
    public Set<IError> getErrors() {
        return Collections.unmodifiableSet(errors);
    }

    @Override
    public void clearErrors() {
        errors.clear();
    }
}
