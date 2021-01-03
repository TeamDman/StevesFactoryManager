/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package ca.teamdman.sfm.client.gui.flow.impl.util;

import ca.teamdman.sfm.client.gui.flow.core.BaseScreen;
import ca.teamdman.sfm.client.gui.flow.core.Colour3f;
import ca.teamdman.sfm.client.gui.flow.core.Colour3f.CONST;
import ca.teamdman.sfm.client.gui.flow.core.Size;
import ca.teamdman.sfm.common.flow.data.core.Position;
import com.mojang.blaze3d.matrix.MatrixStack;

public abstract class FlowMinusButton extends FlowButton {

	private final Colour3f COLOUR;

	public FlowMinusButton(Position pos, Size size, Colour3f colour) {
		super(pos, size);
		this.COLOUR = colour;
	}

	@Override
	public void draw(
		BaseScreen screen, MatrixStack matrixStack, int mx, int my, float deltaTime
	) {
		int x = getPosition().getX();
		int y = getPosition().getY();
		int w = getSize().getWidth();
		int h = getSize().getHeight();
		int thickness = 2;
		int margin = 2;
		if (isInBounds(mx, my)) {
			// highlight
			screen.drawRect(
				matrixStack,
				x,
				y,
				w,
				h,
				CONST.HIGHLIGHT
			);
		}
		// horizontal bar
		screen.drawRect(
			matrixStack,
			x + margin,
			y + h / 2 - thickness / 2,
			w - margin * 2,
			thickness,
			COLOUR
		);
		super.drawTooltip(screen, matrixStack, mx, my, deltaTime);
	}
}
