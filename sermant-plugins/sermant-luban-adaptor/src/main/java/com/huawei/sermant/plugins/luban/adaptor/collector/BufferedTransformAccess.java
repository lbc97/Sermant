/*
 * Copyright (C) 2021-2022 Huawei Technologies Co., Ltd. All rights reserved.
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

package com.huawei.sermant.plugins.luban.adaptor.collector;

import com.huawei.sermant.core.plugin.agent.annotations.BeanPropertyFlag;

import com.lubanops.apm.bootstrap.TransformAccess;

/**
 * 包装的{@link TransformAccess}接口，为其增加{@link BeanPropertyFlag}注解，声明Java Bean名称和类型
 *
 * @author HapThorin
 * @version 1.0.0
 * @since 2022-01-26
 */
@BeanPropertyFlag(value = "lopsAttribute", type = Object.class)
public interface BufferedTransformAccess extends TransformAccess {
}
