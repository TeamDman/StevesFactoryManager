package vswe.stevesfactory.interfaces;


import gigabit101.AdvancedSystemManager2.interfaces.GuiBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;


public interface IAdvancedTooltip {

    @SideOnly(Side.CLIENT)
    int getMinWidth(gigabit101.AdvancedSystemManager2.interfaces.GuiBase gui);
    @SideOnly(Side.CLIENT)
    int getExtraHeight(GuiBase gui);
    @SideOnly(Side.CLIENT)
    void drawContent(GuiBase gui, int x, int y, int mX, int mY);
    @SideOnly(Side.CLIENT)
    List<String> getPrefix(GuiBase gui);
    @SideOnly(Side.CLIENT)
    List<String> getSuffix(GuiBase gui);
}
