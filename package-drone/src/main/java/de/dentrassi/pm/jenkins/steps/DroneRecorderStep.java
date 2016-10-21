/**
 * Copyright 2011-2015 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.dentrassi.pm.jenkins.steps;

import hudson.Extension;
import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractStepImpl;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import hudson.Util;

import javax.annotation.Nonnull;

/**
 * Archiving for gatling reports.
 */
public class DroneRecorderStep extends AbstractStepImpl {

	public String serverUrl;
	
	public String channel;
	
	public String deployKey;
	
	public String artifacts;


	@DataBoundConstructor
    public DroneRecorderStep() {}

    @DataBoundSetter 
    public void setserverUrl(String serverUrl) {
        this.serverUrl = Util.fixEmptyAndTrim(serverUrl);
    }

    @DataBoundSetter 
    public void setchannel(String channel) {
        this.channel = Util.fixEmptyAndTrim(channel);
    }

    @DataBoundSetter 
    public void setdeployKey(String deployKey) {
        this.deployKey = Util.fixEmptyAndTrim(deployKey);
    }

    @DataBoundSetter 
    public void setartifacts(String artifacts) {
        this.artifacts = artifacts;
    }

	@Extension
    public static class DescriptorImpl extends AbstractStepDescriptorImpl {
        public DescriptorImpl() { super(DroneRecorderStepExecution.class); }

        @Override
        public String getFunctionName() {
            return "packageDrone";
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return "Archive a pakage to Package Drone site";
        }
    }
}