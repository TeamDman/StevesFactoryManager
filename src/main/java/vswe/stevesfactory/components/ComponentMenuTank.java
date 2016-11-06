package vswe.stevesfactory.components;

import gigabit101.AdvancedSystemManager2.Localization;
import gigabit101.AdvancedSystemManager2.blocks.ConnectionBlockType;
import gigabit101.AdvancedSystemManager2.components.ComponentMenuContainer;

import java.util.List;


public class ComponentMenuTank extends ComponentMenuContainer {
    public ComponentMenuTank(gigabit101.AdvancedSystemManager2.components.FlowComponent parent) {
        super(parent, ConnectionBlockType.TANK);
    }

    @Override
    public String getName() {
        return Localization.TANK_MENU.toString();
    }

    @Override
    public void addErrors(List<String> errors) {
        if (selectedInventories.isEmpty()) {
            errors.add(Localization.NO_TANK_ERROR.toString());
        }
    }
}
