package com.leon1236.reforestry.core.multiblock;

import net.minecraft.world.level.Level;

public class TestMultiblockLogic extends MultiblockLogic<TestRectangularController> {
	public TestMultiblockLogic() {
		super(TestRectangularController.class);
	}

	@Override
	public TestRectangularController getController() {
		if (this.controller == null) {
			throw new IllegalStateException("Test multiblock logic has no controller");
		}
		return this.controller;
	}

	@Override
	public TestRectangularController createNewController(Level level) {
		return new TestRectangularController(level);
	}
}
