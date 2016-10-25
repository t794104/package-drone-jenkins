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

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
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

    public BuildData ( String serverUrl, String channel, Map<String, String> artifacts )
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
        try
        {
            return serverUrl + "/channel/" + URIUtil.encodePath ( channel ) + "/view";
        }
        catch ( URIException e )
        {
            throw new RuntimeException ( e );
        }
    }

    @Exported
    public String getChannel ()
    {
        return channel;
    }

    @Exported
    public String getServerUrl ()
    {
        return serverUrl;
    }
    
    @Exported
    public Map<String, String> getArtifacts ()
    {
        return artifacts;
    }

    public Object readResolve ()
    {
        return this;
    }

    @Override
    protected Object clone () throws CloneNotSupportedException
    {
        return new BuildData ( this.serverUrl, channel, new HashMap<String, String> ( artifacts ) );
    }

}