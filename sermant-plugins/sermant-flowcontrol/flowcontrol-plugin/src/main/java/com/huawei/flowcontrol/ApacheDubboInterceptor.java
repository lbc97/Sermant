/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Based on org/apache/skywalking/apm/plugin/asf/dubbo/DubboInterceptor.java
 * from the Apache Skywalking project.
 */

package com.huawei.flowcontrol;

import com.huawei.flowcontrol.service.ApacheDubboService;
import com.huawei.sermant.core.agent.common.BeforeResult;
import com.huawei.sermant.core.agent.interceptor.InstanceMethodInterceptor;
import com.huawei.sermant.core.service.ServiceManager;

import java.lang.reflect.Method;

/**
 * apache dubbo拦截后的增强类,埋点定义sentinel资源
 *
 * @author liyi
 * @since 2020-08-26
 */
public class ApacheDubboInterceptor implements InstanceMethodInterceptor {
    private ApacheDubboService apacheDubboService;

    @Override
    public void before(Object obj, Method method, Object[] allArguments, BeforeResult result) throws Exception {
        apacheDubboService = ServiceManager.getService(ApacheDubboService.class);
        apacheDubboService.before(obj, method, allArguments, result);
//        Invoker<?> invoker = null;
//        if (allArguments[0] instanceof Invoker) {
//            invoker = (Invoker<?>) allArguments[0];
//        }
//        Invocation invocation = null;
//        if (allArguments[1] instanceof Invocation) {
//            invocation = (Invocation) allArguments[1];
//        }
//        if (invocation == null || invoker == null) {
//            return;
//        }
//        try {
//            EntryFacade.INSTANCE.tryEntry(invocation);
//        } catch (BlockException ex) {
//            // 流控异常返回给上游
//            result.setResult(AsyncRpcResult.newDefaultAsyncResult(ex.toRuntimeException(), invocation));
//            handleBlockException(ex, getResourceName(invoker.getInterface().getName(), invocation.getMethodName()),
//                    "ApacheDubboInterceptor consumer", EntryFacade.DubboType.APACHE);
//        }
    }

    @Override
    public Object after(Object obj, Method method, Object[] allArguments, Object ret) {
        apacheDubboService.after(obj, method, allArguments, ret);
//        Result result = (Result) ret;
//        // 记录dubbo的exception
//        if (result != null && result.hasException()) {
//            EntryFacade.INSTANCE.tryTraceEntry(result.getException(), RpcContext.getContext().isProviderSide(),
//                    EntryFacade.DubboType.APACHE);
//        }
//        EntryFacade.INSTANCE.exit(EntryFacade.DubboType.APACHE);
        return ret;
    }

    @Override
    public void onThrow(Object obj, Method method, Object[] arguments, Throwable t) {
        apacheDubboService.onThrow(obj, method, arguments, t);
//        if (t != null) {
//            EntryFacade.INSTANCE
//                    .tryTraceEntry(t, RpcContext.getContext().isProviderSide(), EntryFacade.DubboType.APACHE);
//        }
//        EntryFacade.INSTANCE.exit(EntryFacade.DubboType.APACHE);
    }
}
