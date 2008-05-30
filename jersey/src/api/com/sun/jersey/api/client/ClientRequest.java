/*
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://jersey.dev.java.net/CDDL+GPL.html
 * or jersey/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at jersey/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.jersey.api.client;

import com.sun.jersey.impl.client.ClientRequestImpl;
import java.net.URI;
import javax.ws.rs.core.MultivaluedMap;

/**
 * A client (outbound) HTTP request.
 * <p>
 * Instances may be created by using the static method {@link #create} and
 * methods on {@link ClientRequest.Builder}.
 * 
 * @author Paul.Sandoz@Sun.Com
 */
public abstract class ClientRequest {
    
    /**
     * Get the URI of the request. The URI shall contain sufficient
     * components to correctly dispatch a request
     * 
     * @return the URI of the request.
     */
    public abstract URI getURI();
    
    /**
     * Get the HTTP method.
     * 
     * @return the HTTP method.
     */
    public abstract String getMethod();
    
    /**
     * Get the entity of the request.
     * 
     * @return the entity of the request.
     */
    public abstract Object getEntity();
    
    /**
     * Get the HTTP headers of the request.
     * 
     * @return the HTTP headers of the request.
     */
    public abstract MultivaluedMap<String, Object> getMetadata();

    /**
     * Clone the request.
     * 
     * @return the cloned request.
     */
    @Override
    public abstract ClientRequest clone();

    /**
     * Create a builder for building a new {@link ClientRequest}instance.
     * 
     * @return the builder.
     */
    public static final ClientRequest.Builder create() {
        return new Builder();
    }
            
    /**
     * The builder for building a {@link ClientRequest} instance.
     */
    public static final class Builder extends PartialRequestBuilder<Builder> {
        /**
         * Build the {@link ClientRequest}instance.
         * 
         * @param uri the URI of the request.
         * @param method the HTTP method.
         * @return the client request.
         */
        public ClientRequest build(URI uri, String method) {
            ClientRequest ro = new ClientRequestImpl(uri, method, entity, metadata);
            entity = null;
            metadata = null;
            return ro;
        }
    }
}