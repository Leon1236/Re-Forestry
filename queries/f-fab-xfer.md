# F-FAB-XFER — Fabricator JEI transfer

CE 1.21.1 `FabricatorRecipeTransferHandler` writes the ghost grid locally then sends `PacketRecipeTransferRequest` (BlockPos + 9 stacks). Re-Forestry does **not** copy that packet.

Fabricator recipes already have unique results and `ContainerFabricator` implements `IContainerRecipeBook` (`selectRecipe` via `clickMenuButton`). Transfer matches carpenter:

1. Compare `recipe.getResultStack()` to `container.getGuiRecipes()` with `ItemStack.isSameItemSameComponents`.
2. Client `handleInventoryButtonClick(containerId, IContainerRecipeBook.recipeButtonId(i))`.

Worktable needed a payload because vanilla crafting shares outputs; fabricator/carpenter do not.
