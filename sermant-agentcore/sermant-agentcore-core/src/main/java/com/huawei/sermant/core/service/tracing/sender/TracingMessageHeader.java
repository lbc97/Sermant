/*
 * Copyright (C) 2022-2022 Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.huawei.sermant.core.service.tracing.sender;

import lombok.Builder;

/**
 * 链路追踪向后端发送数据的头部（应用信息、节点信息）
 *
 * @author luanwenfei
 * @since 2022-03-07
 */
@Builder
public class TracingMessageHeader {
    private String instanceId;

    private String appId;
}
