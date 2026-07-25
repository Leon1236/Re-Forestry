package com.leon1236.reforestry.api.core;

import java.util.Set;

import com.leon1236.reforestry.api.IForestryApi;

public interface IErrorLogic extends IErrorSource {
    boolean setCondition(boolean condition, IError error);

    boolean contains(IError error);

    boolean hasErrors();

    void clearErrors();

    default short[] toArray() {
        IErrorManager manager = IForestryApi.INSTANCE.getErrorManager();
        Set<IError> errors = getErrors();
        short[] statesArray = new short[errors.size()];
        int i = 0;
        for (IError error : errors) {
            statesArray[i] = manager.getNumericId(error);
            i++;
        }
        return statesArray;
    }

    default void fromArray(short[] errorArray) {
        clearErrors();
        IErrorManager manager = IForestryApi.INSTANCE.getErrorManager();
        for (short errorId : errorArray) {
            IError error = manager.getError(errorId);
            if (error != null) {
                setCondition(true, error);
            }
        }
    }
}
