/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package ca.teamdman.sfm.common.net.packet.manager.put;

import ca.teamdman.sfm.SFM;
import ca.teamdman.sfm.SFMUtil;
import ca.teamdman.sfm.common.flow.data.core.Position;
import ca.teamdman.sfm.common.flow.data.impl.FlowTileInputData;
import ca.teamdman.sfm.common.net.packet.manager.C2SManagerPacket;
import ca.teamdman.sfm.common.tile.ManagerTileEntity;
import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;

public class ManagerFlowInputDataPacketC2S extends C2SManagerPacket {

	private final Position ELEMENT_POSITION;

	public ManagerFlowInputDataPacketC2S(int windowId, BlockPos pos, Position elementPosition) {
		super(windowId, pos);
		this.ELEMENT_POSITION = elementPosition;
	}

	public static class Handler extends C2SManagerPacket.C2SHandler<ManagerFlowInputDataPacketC2S> {

		@Override
		public void finishEncode(
			ManagerFlowInputDataPacketC2S msg,
			PacketBuffer buf
		) {
			buf.writeLong(msg.ELEMENT_POSITION.toLong());
		}

		@Override
		public ManagerFlowInputDataPacketC2S finishDecode(
			int windowId, BlockPos tilePos,
			PacketBuffer buf
		) {
			return new ManagerFlowInputDataPacketC2S(
				windowId,
				tilePos,
				Position.fromLong(buf.readLong())
			);
		}

		@Override
		public void handleDetailed(ManagerFlowInputDataPacketC2S msg, ManagerTileEntity manager) {
			FlowTileInputData data = new FlowTileInputData(
				UUID.randomUUID(),
				msg.ELEMENT_POSITION,
				new ArrayList<>()
			);

			SFM.LOGGER.debug(
				SFMUtil.getMarker(getClass()),
				"C2S received, creating input at position {} with id {}",
				msg.ELEMENT_POSITION,
				data.getId()
			);

			manager.addData(data);
			manager.markAndNotify();
			manager.sendPacketToListeners(new ManagerFlowInputDataPacketS2C(
				msg.WINDOW_ID,
				data.getId(),
				msg.ELEMENT_POSITION
			));
		}
	}
}