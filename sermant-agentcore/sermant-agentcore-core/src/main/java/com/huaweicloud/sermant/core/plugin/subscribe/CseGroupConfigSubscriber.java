/*
 * Copyright (C) 2022-2022 Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.huaweicloud.sermant.core.plugin.subscribe;

import com.huaweicloud.sermant.core.config.ConfigManager;
import com.huaweicloud.sermant.core.plugin.config.ServiceMeta;
import com.huaweicloud.sermant.core.service.dynamicconfig.DynamicConfigService;
import com.huaweicloud.sermant.core.service.dynamicconfig.common.DynamicConfigListener;
import com.huaweicloud.sermant.core.service.dynamicconfig.utils.LabelGroupUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * CSE订阅
 *
 * @author zhouss
 * @since 2022-04-14
 */
public class CseGroupConfigSubscriber extends AbstractGroupConfigSubscriber {
    private static final int REQUEST_MAP_SIZE = 4;

    private final Map<String, DynamicConfigListener> listenerCache = new HashMap<>(REQUEST_MAP_SIZE);

    private final String serviceName;

    private final DynamicConfigListener listener;

    private final ServiceMeta config;

    /**
     * CSE订阅
     *
     * @param serviceName 服务名
     * @param listener    监听器
     */
    public CseGroupConfigSubscriber(String serviceName, DynamicConfigListener listener) {
        this(serviceName, listener, null);
    }

    /**
     * 自定义配置中心实现的构造方法
     *
     * @param serviceName          服务名
     * @param listener             监听器
     * @param dynamicConfigService 配置中心实现
     */
    public CseGroupConfigSubscriber(String serviceName, DynamicConfigListener listener,
        DynamicConfigService dynamicConfigService) {
        super(dynamicConfigService);
        this.serviceName = serviceName;
        this.listener = listener;
        this.config = ConfigManager.getConfig(ServiceMeta.class);
    }

    @Override
    protected Map<String, DynamicConfigListener> buildGroupSubscribers() {
        buildAppRequest();
        buildServiceRequest();
        buildCustomRequest();
        return listenerCache;
    }

    private void buildServiceRequest() {
        final HashMap<String, String> map = new HashMap<>(REQUEST_MAP_SIZE);
        map.put("app", config.getApplication());
        map.put("service", serviceName);
        map.put("environment", config.getEnvironment());
        listenerCache.put(LabelGroupUtils.createLabelGroup(map), listener);
    }

    private void buildAppRequest() {
        final HashMap<String, String> map = new HashMap<>(REQUEST_MAP_SIZE);
        map.put("app", config.getApplication());
        map.put("environment", config.getEnvironment());
        listenerCache.put(LabelGroupUtils.createLabelGroup(map), listener);
    }

    private void buildCustomRequest() {
        if (StringUtils.isNoneBlank(config.getCustomLabel(), config.getCustomLabelValue())) {
            final HashMap<String, String> map = new HashMap<>(REQUEST_MAP_SIZE);
            map.put(config.getCustomLabel(), config.getCustomLabelValue());
            listenerCache.put(LabelGroupUtils.createLabelGroup(map), listener);
        }
    }

    @Override
    protected boolean isReady() {
        return StringUtils.isNoneBlank(serviceName, config.getApplication(), config.getEnvironment());
    }
}