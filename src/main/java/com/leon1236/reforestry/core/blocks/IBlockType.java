package com.leon1236.reforestry.core.blocks;

import com.leon1236.reforestry.api.core.IBlockSubtype;

public interface IBlockType extends IBlockSubtype {
    IMachineProperties<?> getMachineProperties();
}
