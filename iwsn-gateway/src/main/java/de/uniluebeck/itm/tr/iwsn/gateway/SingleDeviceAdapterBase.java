/**********************************************************************************************************************
 * Copyright (c) 2010, Institute of Telematics, University of Luebeck                                                 *
 * All rights reserved.                                                                                               *
 *                                                                                                                    *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the   *
 * following conditions are met:                                                                                      *
 *                                                                                                                    *
 * - Redistributions of source code must retain the above copyright notice, this list of conditions and the following *
 *   disclaimer.                                                                                                      *
 * - Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the        *
 *   following disclaimer in the documentation and/or other materials provided with the distribution.                 *
 * - Neither the name of the University of Luebeck nor the names of its contributors may be used to endorse or promote*
 *   products derived from this software without specific prior written permission.                                   *
 *                                                                                                                    *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, *
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE      *
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,         *
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE *
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF    *
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY   *
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.                                *
 **********************************************************************************************************************/

package de.uniluebeck.itm.tr.iwsn.gateway;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.ListenableFuture;
import de.uniluebeck.itm.nettyprotocols.ChannelHandlerConfigList;
import de.uniluebeck.itm.tr.devicedb.DeviceConfig;
import de.uniluebeck.itm.tr.iwsn.nodeapi.NodeApiCallResult;
import de.uniluebeck.itm.util.concurrent.*;
import de.uniluebeck.itm.wsn.drivers.core.MacAddress;
import eu.wisebed.api.v3.common.NodeUrn;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Iterables.contains;
import static com.google.common.collect.Iterables.size;
import static com.google.common.collect.Maps.newHashMap;

public abstract class SingleDeviceAdapterBase extends ListenableDeviceAdapter {

	protected final DeviceConfig deviceConfig;

	protected final String deviceType;

	protected final String devicePort;

	protected final Map<String, String> deviceConfiguration;

	protected SingleDeviceAdapterBase(final String deviceType,
									  final String devicePort,
									  @Nullable final Map<String, String> deviceConfiguration,
									  final DeviceConfig deviceConfig) {
		this.deviceType = checkNotNull(deviceType);
		this.devicePort = checkNotNull(devicePort);
		this.deviceConfiguration = deviceConfiguration;
		this.deviceConfig = checkNotNull(deviceConfig);
	}

	@Override
	public String getDevicePort() {
		return devicePort;
	}

	@Override
	@Nullable
	public Map<String, String> getDeviceConfiguration() {
		return deviceConfiguration;
	}

	@Override
	public String getDeviceType() {
		return deviceType;
	}

	@Override
	@Nullable
	public Map<NodeUrn, DeviceConfig> getDeviceConfigs() {
		final Map<NodeUrn, DeviceConfig> map = newHashMap();
		map.put(deviceConfig.getNodeUrn(), deviceConfig);
		return map;
	}

	@Override
	public ListenableFutureMap<NodeUrn, Boolean> areNodesAlive(final Iterable<NodeUrn> nodeUrns) {
		checkArgument(size(checkNotNull(nodeUrns)) == 1 && contains(nodeUrns, deviceConfig.getNodeUrn()));
		return new SettableFutureMap<NodeUrn, Boolean>(ImmutableMap.of(deviceConfig.getNodeUrn(), isNodeAlive()));
	}

	@Override
	public ListenableFutureMap<NodeUrn, Boolean> areNodesConnected(final Iterable<NodeUrn> nodeUrns) {
		checkArgument(size(checkNotNull(nodeUrns)) == 1 && contains(nodeUrns, deviceConfig.getNodeUrn()));
		return new SettableFutureMap<NodeUrn, Boolean>(ImmutableMap.of(deviceConfig.getNodeUrn(), isNodeConnected()));
	}

	@Override
	public ListenableFutureMap<NodeUrn, NodeApiCallResult> disableNodes(final Iterable<NodeUrn> nodeUrns) {
		checkArgument(size(checkNotNull(nodeUrns)) == 1 && contains(nodeUrns, deviceConfig.getNodeUrn()));
		return new SettableFutureMap<NodeUrn, NodeApiCallResult>(
				ImmutableMap.of(deviceConfig.getNodeUrn(), disableNode())
		);
	}

	@Override
	public ListenableFutureMap<NodeUrn, NodeApiCallResult> disablePhysicalLinks(
			final Map<NodeUrn, NodeUrn> sourceTargetMap) {
		checkArgument(sourceTargetMap.size() == 1 && sourceTargetMap.containsKey(deviceConfig.getNodeUrn()));
		return new SettableFutureMap<NodeUrn, NodeApiCallResult>(
				ImmutableMap.of(deviceConfig.getNodeUrn(),
						disablePhysicalLink(new MacAddress(sourceTargetMap.get(deviceConfig.getNodeUrn()).getSuffix()))
				)
		);
	}

	@Override
	public ListenableFutureMap<NodeUrn, NodeApiCallResult> disableVirtualLinks(
			final Map<NodeUrn, NodeUrn> sourceTargetMap) {
		checkArgument(sourceTargetMap.size() == 1 && sourceTargetMap.containsKey(deviceConfig.getNodeUrn()));
		return new SettableFutureMap<NodeUrn, NodeApiCallResult>(
				ImmutableMap.of(deviceConfig.getNodeUrn(),
						disableVirtualLink(new MacAddress(sourceTargetMap.get(deviceConfig.getNodeUrn()).getSuffix()))
				)
		);
	}

	@Override
	public ListenableFutureMap<NodeUrn, NodeApiCallResult> enableNodes(final Iterable<NodeUrn> nodeUrns) {
		checkArgument(size(checkNotNull(nodeUrns)) == 1 && contains(nodeUrns, deviceConfig.getNodeUrn()));
		return new SettableFutureMap<NodeUrn, NodeApiCallResult>(
				ImmutableMap.of(deviceConfig.getNodeUrn(), enableNode())
		);
	}

	@Override
	public ListenableFutureMap<NodeUrn, NodeApiCallResult> enablePhysicalLinks(
			final Map<NodeUrn, NodeUrn> sourceTargetMap) {
		checkArgument(sourceTargetMap.size() == 1 && sourceTargetMap.containsKey(deviceConfig.getNodeUrn()));
		return new SettableFutureMap<NodeUrn, NodeApiCallResult>(
				ImmutableMap.of(deviceConfig.getNodeUrn(),
						enablePhysicalLink(new MacAddress(sourceTargetMap.get(deviceConfig.getNodeUrn()).getSuffix()))
				)
		);
	}

	@Override
	public ListenableFutureMap<NodeUrn, NodeApiCallResult> enableVirtualLinks(
			final Map<NodeUrn, NodeUrn> sourceTargetMap) {
		checkArgument(sourceTargetMap.size() == 1 && sourceTargetMap.containsKey(deviceConfig.getNodeUrn()));
		return new SettableFutureMap<NodeUrn, NodeApiCallResult>(
				ImmutableMap.of(deviceConfig.getNodeUrn(),
						enableVirtualLink(new MacAddress(sourceTargetMap.get(deviceConfig.getNodeUrn()).getSuffix()))
				)
		);
	}

	@Override
	public ProgressListenableFutureMap<NodeUrn, Void> flashProgram(final Iterable<NodeUrn> nodeUrns,
																   byte[] binaryImage) {
		checkArgument(size(checkNotNull(nodeUrns)) == 1 && contains(nodeUrns, deviceConfig.getNodeUrn()));
		return new ProgressSettableFutureMap<NodeUrn, Void>(
				ImmutableMap.of(deviceConfig.getNodeUrn(), flashProgram(binaryImage))
		);
	}

	@Override
	public Set<NodeUrn> getNodeUrns() {
		return isRunning() ? ImmutableSet.of(deviceConfig.getNodeUrn()) : ImmutableSet.<NodeUrn>of();
	}

	@Override
	public ListenableFutureMap<NodeUrn, Void> resetNodes(final Iterable<NodeUrn> nodeUrns) {
		checkArgument(size(checkNotNull(nodeUrns)) == 1 && contains(nodeUrns, deviceConfig.getNodeUrn()));
		return new SettableFutureMap<NodeUrn, Void>(ImmutableMap.of(deviceConfig.getNodeUrn(), resetNode()));
	}

	@Override
	public ListenableFutureMap<NodeUrn, Void> sendMessage(final Iterable<NodeUrn> nodeUrns, final byte[] messageBytes) {
		checkArgument(size(checkNotNull(nodeUrns)) == 1 && contains(nodeUrns, deviceConfig.getNodeUrn()));
		return new SettableFutureMap<NodeUrn, Void>(
				ImmutableMap.of(deviceConfig.getNodeUrn(), sendMessage(messageBytes))
		);
	}

	@Override
	public ListenableFutureMap<NodeUrn, Void> setChannelPipelines(final Iterable<NodeUrn> nodeUrns,
																  final ChannelHandlerConfigList channelHandlerConfigs) {
		checkArgument(size(checkNotNull(nodeUrns)) == 1 && contains(nodeUrns, deviceConfig.getNodeUrn()));
		return new SettableFutureMap<NodeUrn, Void>(
				ImmutableMap.of(deviceConfig.getNodeUrn(), setChannelPipeline(channelHandlerConfigs))
		);
	}

	@Override
	public ListenableFutureMap<NodeUrn, ChannelHandlerConfigList> getChannelPipelines(
			final Iterable<NodeUrn> nodeUrns) {
		checkArgument(size(checkNotNull(nodeUrns)) == 1 && contains(nodeUrns, deviceConfig.getNodeUrn()));
		return new SettableFutureMap<NodeUrn, ChannelHandlerConfigList>(
				ImmutableMap.of(deviceConfig.getNodeUrn(), getChannelPipeline())
		);
	}

	protected abstract ListenableFuture<NodeApiCallResult> enableNode();

	protected abstract ListenableFuture<NodeApiCallResult> enablePhysicalLink(MacAddress targetMacAddress);

	protected abstract ListenableFuture<NodeApiCallResult> disableVirtualLink(MacAddress targetMacAddress);

	protected abstract ListenableFuture<NodeApiCallResult> disableNode();

	protected abstract ListenableFuture<NodeApiCallResult> disablePhysicalLink(MacAddress targetMacAddress);

	protected abstract ProgressListenableFuture<Void> flashProgram(byte[] binaryImage);

	protected abstract ListenableFuture<Boolean> isNodeAlive();

	protected abstract ListenableFuture<Boolean> isNodeConnected();

	protected abstract ListenableFuture<Void> resetNode();

	protected abstract ListenableFuture<Void> sendMessage(byte[] messageBytes);

	protected abstract ListenableFuture<NodeApiCallResult> enableVirtualLink(MacAddress targetMacAddress);

	protected abstract ListenableFuture<Void> setChannelPipeline(ChannelHandlerConfigList channelHandlerConfigs);

	protected abstract ListenableFuture<ChannelHandlerConfigList> getChannelPipeline();
}
