/*
 * Copyright (C) 2022-2022 Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huaweicloud.sermant.router.spring.declarer;

import com.huaweicloud.sermant.core.plugin.agent.declarer.InterceptDeclarer;
import com.huaweicloud.sermant.core.plugin.agent.matcher.ClassMatcher;
import com.huaweicloud.sermant.core.plugin.agent.matcher.MethodMatcher;
import com.huaweicloud.sermant.router.spring.interceptor.OkHttpClientInterceptor;

/**
 * 针对okhttp请求方式，从注册中心获取实例列表拦截
 *
 * @author yangrh
 * @since 2022-10-25
 */
public class OkHttpClientDeclarer extends BaseRegistryPluginAdaptationDeclarer {
    /**
     * 增强类的全限定名 okhttp请求
     */
    private static final String[] ENHANCE_CLASSES = {
        "com.squareup.okhttp.Call"
    };

    /**
     * 拦截类的全限定名
     */
    private static final String INTERCEPT_CLASS = OkHttpClientInterceptor.class.getCanonicalName();

    @Override
    public ClassMatcher getClassMatcher() {
        return ClassMatcher.nameContains(ENHANCE_CLASSES);
    }

    @Override
    public InterceptDeclarer[] getInterceptDeclarers(ClassLoader classLoader) {
        return new InterceptDeclarer[] {
                InterceptDeclarer.build(MethodMatcher.nameContains("execute", "getResponseWithInterceptorChain"),
                        INTERCEPT_CLASS)
        };
    }
}
