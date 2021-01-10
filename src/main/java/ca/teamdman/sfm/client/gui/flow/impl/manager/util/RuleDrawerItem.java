/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package ca.teamdman.sfm.client.gui.flow.impl.manager.util;

import ca.teamdman.sfm.client.gui.flow.impl.manager.core.ManagerFlowController;
import ca.teamdman.sfm.client.gui.flow.impl.manager.flowdataholder.itemstacktileentityrule.ItemStackTileEntityRuleFlowComponent;
import ca.teamdman.sfm.client.gui.flow.impl.util.FlowContainer;
import ca.teamdman.sfm.client.gui.flow.impl.util.FlowDrawer;
import ca.teamdman.sfm.client.gui.flow.impl.util.ItemStackFlowComponent;
import ca.teamdman.sfm.common.config.Config.Client;
import ca.teamdman.sfm.common.flow.core.FlowDataHolder;
import ca.teamdman.sfm.common.flow.core.Position;
import ca.teamdman.sfm.common.flow.data.ItemStackTileEntityRuleFlowData;

public class RuleDrawerItem extends FlowContainer implements
	FlowDataHolder<ItemStackTileEntityRuleFlowData> {

	private final ItemStackFlowComponent ICON;
	private final ManagerFlowController CONTROLLER;
	private final FlowDrawer DRAWER;
	private ItemStackTileEntityRuleFlowData data;

	public RuleDrawerItem(
		FlowDrawer drawer,
		ManagerFlowController controller,
		ItemStackTileEntityRuleFlowData rule
	) {
		super(rule.position, ItemStackFlowComponent.DEFAULT_SIZE.copy());
		this.DRAWER = drawer;
		this.CONTROLLER = controller;
		this.data = rule;
		this.ICON = new ItemStackFlowComponent(rule.icon, new Position(2, 2)) {
			@Override
			public void onSelectionChanged() {
				RuleDrawerItem.this.onClicked(isSelected());
			}
		};
		addChild(ICON);
		DRAWER.update();
	}

	public void onClicked(boolean activate) {
	}

	public ItemStackFlowComponent getIcon() {
		return ICON;
	}

	@Override
	public ItemStackTileEntityRuleFlowData getData() {
		return data;
	}

	@Override
	public void setData(ItemStackTileEntityRuleFlowData data) {
		this.data = data;
	}

	public void openRule() {
		if (!Client.allowMultipleRuleWindows) {
			CONTROLLER.getChildren().stream()
				.filter(c -> c instanceof ItemStackTileEntityRuleFlowComponent)
				.map(c -> ((ItemStackTileEntityRuleFlowComponent) c))
				.forEach(c -> {
					c.setVisible(false);
					c.setEnabled(false);
				});

			DRAWER.getChildren().stream()
				.filter(c -> c instanceof RuleDrawerItem)
				.forEach(c -> ((RuleDrawerItem) c).ICON.setSelected(false));
		}
		ICON.setSelected(true);
		CONTROLLER.findFirstChild(data.getId()).ifPresent(v -> v.setVisible(true));
	}
}
