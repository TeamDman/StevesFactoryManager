/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package ca.teamdman.sfm.client.gui.flow.impl.manager.template;

import ca.teamdman.sfm.client.gui.flow.core.FlowComponent;
import ca.teamdman.sfm.client.gui.flow.impl.manager.core.CloneController;
import ca.teamdman.sfm.client.gui.flow.impl.manager.core.ManagerFlowController;
import ca.teamdman.sfm.client.gui.flow.impl.manager.flowdataholder.BasicTileInputFlowButton;
import ca.teamdman.sfm.client.gui.flow.impl.util.ButtonLabel;
import ca.teamdman.sfm.client.gui.flow.impl.util.FlowIconButton;
import ca.teamdman.sfm.common.flow.core.Position;
import ca.teamdman.sfm.common.flow.data.BasicTileInputFlowData;
import ca.teamdman.sfm.common.flow.data.ItemStackTileEntityRuleFlowData;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.TranslationTextComponent;

public class BasicInputSpawnerFlowButton extends FlowIconButton {

	private final ManagerFlowController CONTROLLER;

	public BasicInputSpawnerFlowButton(
		ManagerFlowController controller
	) {
		super(ButtonLabel.ADD_INPUT, new Position());
		this.CONTROLLER = controller;
	}

	@Override
	public List<? extends ITextProperties> getTooltip() {
		List<ITextComponent> list = new ArrayList<>();
		list.add(new TranslationTextComponent("gui.sfm.flow.tooltip.basic_input_spawner"));
		return list;
	}

	@Override
	public boolean mousePressed(int mx, int my, int button) {
		// override mousePressed instead of onClicked because of custom hover logic
		boolean rtn = super.mousePressed(mx, my, button);
		if (clicking) {
			clicking = false;
			CONTROLLER.findFirstChild(CloneController.class).ifPresent(cloner -> {
				// create default rule data
				ItemStackTileEntityRuleFlowData ruleData = new ItemStackTileEntityRuleFlowData();

				// create button data
				BasicTileInputFlowData buttonData = new BasicTileInputFlowData(
					UUID.randomUUID(),
					new Position(),
					ruleData.getId()
				);

				// create rule button component
				FlowComponent comp = new BasicTileInputFlowButton(CONTROLLER, buttonData, ruleData);

				// set component as cloning
				cloner.setCloning(comp);
			});
			return true;
		}
		return rtn;
	}

	@Override
	public void onClicked(int mx, int my, int button) {
	}
}