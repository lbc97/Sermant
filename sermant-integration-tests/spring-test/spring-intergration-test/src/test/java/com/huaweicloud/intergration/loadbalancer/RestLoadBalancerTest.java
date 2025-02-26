/*
 * Copyright (C) 2022-2022 Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.huaweicloud.intergration.loadbalancer;

import org.junit.Rule;
import org.junit.rules.TestRule;

import java.util.HashMap;
import java.util.Map;

/**
 * rest测试
 *
 * @author zhouss
 * @since 2022-08-17
 */
public class RestLoadBalancerTest extends LoadbalancerTest {
    @Rule(order = 200)
    public final TestRule TEST_RULE = new LoadBalancerRule();

    @Override
    protected String getServiceName() {
        return "rest-provider";
    }

    @Override
    protected String getUrl() {
        return "http://localhost:8005/lb";
    }

    @Override
    protected Map<String, String> getLabels() {
        final Map<String, String> labels = new HashMap<>();
        labels.put("app", "rest");
        labels.put("environment", "development");
        return labels;
    }
}
