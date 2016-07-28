/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.tooling.internal.connection;

import org.gradle.internal.composite.IncludedBuild;
import org.gradle.tooling.ConfigurableLauncher;
import org.gradle.tooling.internal.consumer.AbstractLongRunningOperation;
import org.gradle.tooling.internal.consumer.parameters.ConsumerOperationParameters;

public class ToolingClientCompositeUtil {
    private final ConsumerOperationParameters operationParameters;

    ToolingClientCompositeUtil(ConsumerOperationParameters operationParameters) {
        this.operationParameters = operationParameters;
    }

    <V extends ConfigurableLauncher> void configureRequest(ConfigurableLauncher<V> request) {
        ((AbstractLongRunningOperation) request).copyFrom(operationParameters);
    }

    ParticipantConnector createParticipantConnector(IncludedBuild build) {
        return new ParticipantConnector(build, operationParameters.getGradleUserHomeDir(), operationParameters.getDaemonBaseDir(), operationParameters.getDaemonMaxIdleTimeValue(), operationParameters.getDaemonMaxIdleTimeUnits());
    }
}
