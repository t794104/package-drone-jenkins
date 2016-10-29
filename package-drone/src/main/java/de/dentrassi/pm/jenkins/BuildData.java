/*******************************************************************************
 * Copyright (c) 2015 IBH SYSTEMS GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package de.dentrassi.pm.jenkins;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

import hudson.model.Action;
import hudson.model.ProminentProjectAction;

@ExportedBean ( defaultVisibility = 999 )
public class BuildData implements Action, Serializable, ProminentProjectAction
{
    private static final long serialVersionUID = 1L;

    private final String serverUrl;

    private final String channel;

    private final Map<String, String> artifacts;

    public BuildData ( final String serverUrl, final String channel, final Map<String, String> artifacts )
    {
        this.serverUrl = serverUrl;
        this.channel = channel;
        this.artifacts = artifacts;
    }

    @Override
    public String getIconFileName ()
    {
        return "/plugin/package-drone/images/pdrone-32x32.png";
    }

    @Override
    public String getDisplayName ()
    {
        return "Package Drone";
    }

    @Override
    public String getUrlName ()
    {
        return UrlMaker.make ( this.serverUrl, this.channel );
    }

    @Exported
    public String getChannel ()
    {
        return this.channel;
    }

    @Exported
    public String getServerUrl ()
    {
        return this.serverUrl;
    }

    @Exported
    public Map<String, String> getArtifacts ()
    {
        return this.artifacts;
    }

    public Object readResolve ()
    {
        return this;
    }

    @Override
    protected Object clone () throws CloneNotSupportedException
    {
        return new BuildData ( this.serverUrl, this.channel, new HashMap<String, String> ( this.artifacts ) );
    }

}
