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
import java.util.Locale;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/**
 * An encapsulation of a Web resource capable of building requests
 * to send to the Web resource and processing responses returned from the Web
 * resource.
 * <p>
 * The Web resource implements the {@link UniformInterface} to invoke the HTTP 
 * method on the Web resource. A client request may be built before invocation
 * on the uniform interface.
 * 
 * @author Paul.Sandoz@Sun.Com
 */
public final class WebResource extends Filterable implements 
        RequestBuilder<WebResource.Builder>,
        UniformInterface {    
    private final URI u;

    /* package */ WebResource(ClientHandler c, URI u) {
        super(c);
        this.u = u;
    }
    
    private WebResource(WebResource that, UriBuilder ub) {
        super(that);
        this.u = ub.build();
    }
    
    /**
     * Get the URI to the resource.
     * 
     * @return the URI.
     */
    public URI getURI() {
        return u;
    }
    
    /**
     * Get the URI builder to the resource.
     * 
     * @return the URI builder.
     */
    public UriBuilder getBuilder() {
        return UriBuilder.fromUri(u);
    }
        
    // UniformInterface
    
    public ClientResponse head() {
        return getHeadHandler().handle(new ClientRequestImpl(getURI(), "HEAD"));
    }
        
    public <T> T options(Class<T> c) {
        return handle(c, new ClientRequestImpl(getURI(), "OPTIONS"));
    }
        
    public <T> T options(GenericType<T> gt) {
        return handle(gt, new ClientRequestImpl(getURI(), "OPTIONS"));            
    }
        
    public <T> T get(Class<T> c) {
        return handle(c, new ClientRequestImpl(getURI(), "GET"));
    }
            
    public <T> T get(GenericType<T> gt) {
        return handle(gt, new ClientRequestImpl(getURI(), "GET"));            
    }
        
    public void put() {
        voidHandle(new ClientRequestImpl(getURI(), "PUT", null));
    }
    
    public void put(Object requestEntity) {
        voidHandle(new ClientRequestImpl(getURI(), "PUT", requestEntity));
    }
    
    public <T> T put(Class<T> c) {
        return handle(c, new ClientRequestImpl(getURI(), "PUT"));
    }

    public <T> T put(GenericType<T> gt) {
        return handle(gt, new ClientRequestImpl(getURI(), "PUT"));
    }

    public <T> T put(Class<T> c, Object requestEntity) {
        return handle(c, new ClientRequestImpl(getURI(), "PUT", requestEntity));
    }
            
    public <T> T put(GenericType<T> gt, Object requestEntity) {
        return handle(gt, new ClientRequestImpl(getURI(), "PUT", requestEntity));
    }

    public void post() {
        voidHandle(new ClientRequestImpl(getURI(), "POST"));
    }
    
    public void post(Object requestEntity) {
        voidHandle(new ClientRequestImpl(getURI(), "POST", requestEntity));
    }
    
    public <T> T post(Class<T> c) {
        return handle(c, new ClientRequestImpl(getURI(), "POST"));
    }

    public <T> T post(GenericType<T> gt) {
        return handle(gt, new ClientRequestImpl(getURI(), "POST"));
    }
    
    public <T> T post(Class<T> c, Object requestEntity) {
        return handle(c, new ClientRequestImpl(getURI(), "POST", requestEntity));
    }
            
    public <T> T post(GenericType<T> gt, Object requestEntity) {
        return handle(gt, new ClientRequestImpl(getURI(), "POST", requestEntity));
    }
    
    public void delete() {
        voidHandle(new ClientRequestImpl(getURI(), "DELETE"));
    }
    
    public void delete(Object requestEntity) {
        voidHandle(new ClientRequestImpl(getURI(), "DELETE", requestEntity));
    }
    
    public <T> T delete(Class<T> c) {
        return handle(c, new ClientRequestImpl(getURI(), "DELETE"));    
    }

    public <T> T delete(GenericType<T> gt) {
        return handle(gt, new ClientRequestImpl(getURI(), "DELETE"));
    }
    
    public <T> T delete(Class<T> c, Object requestEntity) {
        return handle(c, new ClientRequestImpl(getURI(), "DELETE", requestEntity));
    }
      
    public <T> T delete(GenericType<T> gt, Object requestEntity) {
        return handle(gt, new ClientRequestImpl(getURI(), "DELETE", requestEntity));
    }
    
    public void method(String method) {
        voidHandle(new ClientRequestImpl(getURI(), method));        
    }
    
    public void method(String method, Object requestEntity) {
        voidHandle(new ClientRequestImpl(getURI(), method, requestEntity));        
    }
    
    public <T> T method(String method, Class<T> c) {
        return handle(c, new ClientRequestImpl(getURI(), method));            
    }
    
    public <T> T method(String method, GenericType<T> gt) {
        return handle(gt, new ClientRequestImpl(getURI(), method));            
    }
    
    public <T> T method(String method, Class<T> c, Object requestEntity) {
        return handle(c, new ClientRequestImpl(getURI(), method, requestEntity));        
    }
    
    public <T> T method(String method, GenericType<T> gt, Object requestEntity) {
        return handle(gt, new ClientRequestImpl(getURI(), method, requestEntity));        
    }
    
    // RequestBuilder<WebResource.Builder>
    
    public Builder entity(Object entity) {
        return new Builder(getURI()).entity(entity);
    }

    public Builder entity(Object entity, MediaType type) {
        return new Builder(getURI()).entity(entity, type);
    }

    public Builder entity(Object entity, String type) {
        return new Builder(getURI()).entity(entity, type);
    }

    public Builder type(MediaType type) {
        return new Builder(getURI()).type(type);
    }
        
    public Builder type(String type) {
        return new Builder(getURI()).type(type);
    }
    
    public Builder accept(MediaType... types) {
        return new Builder(getURI()).accept(types);
    }

    public Builder accept(String... types) {
        return new Builder(getURI()).accept(types);
    }    
    
    public Builder acceptLanguage(Locale... locales) {
        return new Builder(getURI()).acceptLanguage(locales);
    }

    public Builder acceptLanguage(String... locales) {
        return new Builder(getURI()).acceptLanguage(locales);
    }    
    
    public Builder cookie(Cookie cookie) {
        return new Builder(getURI()).cookie(cookie);
    }
    
    public Builder header(String name, Object value) {
        return new Builder(getURI()).header(name, value);
    }

    // URI specific building
    
    /**
     * Create a new WebResource from this web resource with an additional path
     * added to the URI of this web resource.
     * <p>
     * Any filters on this web resource are inherited. Removal of filters
     * may cause undefined behaviour.
     *
     * @param path the additional path.
     * 
     * @return the new web resource.
     */
    public WebResource path(String path) {
        return new WebResource(this, getBuilder().path(path));
    }

    /**
     * Create a new WebResource from this web resource.
     * <p>
     * If the URI contains a path component and the path starts with a '/' then
     * the path of this web resource URI is replaced. Otherwise the path is
     * appended.
     * <p>
     * If the URI contains query parameters then those query parameters will
     * replace the query parameters (if any) of this web resource.
     * <p>
     * Any filters on this web resource are inherited. Removal of filters
     * may cause undefined behaviour.
     * 
     * @param uri the URI.
     * @return the new web resource.
     */
    public WebResource uri(URI uri) {
        UriBuilder b = getBuilder();
        String path = uri.getRawPath();
        if (path != null && path.length() > 0) {
            if (path.startsWith("/")) {
                b.replacePath(path);
            } else {
                b.path(path);
            }
        }
        String query = uri.getRawQuery();
        if (query != null && query.length() > 0) {
            b.replaceQuery(query);        
        }
        return new WebResource(this, b);
    }
    
    // Builder that builds client request and handles it
    
    /**
     * The builder for building a {@link ClientRequest} instance and 
     * handling the request using the {@link UniformInterface}. The methods
     * of the {@link UniformInterface} are the build methods of the builder.
     */
    public final class Builder extends PartialRequestBuilder<Builder> 
            implements UniformInterface {  
        
        private final URI u;
        
        private Builder(URI u) {
            this.u = u;
        }
        
        private ClientRequest build(String method) {
            ClientRequest ro = new ClientRequestImpl(u, method, entity, metadata);
            entity = null;
            metadata = null;
            return ro;
        }
        
        private ClientRequest build(String method, Object e) {
            ClientRequest ro = new ClientRequestImpl(u, method, e, metadata);
            entity = null;
            metadata = null;
            return ro;
        }
        
        // UniformInterface
        
        public ClientResponse head() {
            return getHeadHandler().handle(build("HEAD"));
        }
        
        public <T> T options(Class<T> c) {
            return handle(c, build("OPTIONS"));
        }
                
        public <T> T options(GenericType<T> gt) {
            return handle(gt, build("OPTIONS"));            
        }
        
        public <T> T get(Class<T> c) {
            return handle(c, build("GET"));
        }
                
        public <T> T get(GenericType<T> gt) {
            return handle(gt, build("GET"));            
        }
        
        public void put() {
            voidHandle(build("PUT"));
        }

        public void put(Object requestEntity) {
            voidHandle(build("PUT", requestEntity));
        }
        
        public <T> T put(Class<T> c) {
            return handle(c, build("PUT"));
        }
        
        public <T> T put(GenericType<T> gt) {
            return handle(gt, build("PUT"));
        }

        public <T> T put(Class<T> c, Object requestEntity) {
            return handle(c, build("PUT", requestEntity));
        }
        
        public <T> T put(GenericType<T> gt, Object requestEntity) {
            return handle(gt, build("PUT", requestEntity));
        }

        public void post() {
            voidHandle(build("POST"));
        }

        public void post(Object requestEntity) {
            voidHandle(build("POST", requestEntity));
        }

        public <T> T post(Class<T> c) {
            return handle(c, build("POST"));
        }
                
        public <T> T post(GenericType<T> gt) {
            return handle(gt, build("POST"));
        }

        public <T> T post(Class<T> c, Object requestEntity) {
            return handle(c, build("POST", requestEntity));
        }
        
        public <T> T post(GenericType<T> gt, Object requestEntity) {
            return handle(gt, build("POST", requestEntity));
        }
    
        public void delete() {
            voidHandle(build("DELETE"));
        }

        public void delete(Object requestEntity) {
            voidHandle(build("DELETE", requestEntity));
        }
        
        public <T> T delete(Class<T> c) {
            return handle(c, build("DELETE"));
        }
        
        public <T> T delete(GenericType<T> gt) {
            return handle(gt, build("DELETE"));
        }

        public <T> T delete(Class<T> c, Object requestEntity) {
            return handle(c, build("DELETE", requestEntity));
        }
        
        public <T> T delete(GenericType<T> gt, Object requestEntity) {
            return handle(gt, build("DELETE", requestEntity));
        }
    
        public void method(String method) {
            voidHandle(build(method));
        }

        public void method(String method, Object requestEntity) {
            voidHandle(build(method, requestEntity));
        }

        public <T> T method(String method, Class<T> c) {
            return handle(c, build(method));
        }

        public <T> T method(String method, GenericType<T> gt) {
            return handle(gt, build(method));
        }

        public <T> T method(String method, Class<T> c, Object requestEntity) {
            return handle(c, build(method, requestEntity));
        }        
        
        public <T> T method(String method, GenericType<T> gt, Object requestEntity) {
            return handle(gt, build(method, requestEntity));
        }        
    }
    
    
    private <T> T handle(Class<T> c, ClientRequest ro) {
        ClientResponse r = getHeadHandler().handle(ro);
        
        if (c == ClientResponse.class) return c.cast(r);
        
        if (r.getStatus() < 300) return r.getEntity(c);
        
        throw new UniformInterfaceException("Status: " + r.getStatus(), r);
    }
    
    private <T> T handle(GenericType<T> gt, ClientRequest ro) {
        ClientResponse r = getHeadHandler().handle(ro);
        
        if (gt.getRawClass() == ClientResponse.class) gt.getRawClass().cast(r);
        
        if (r.getStatus() < 300) return r.getEntity(gt);
        
        throw new UniformInterfaceException("Status: " + r.getStatus(), r);
    }
    
    private void voidHandle(ClientRequest ro) {
        ClientResponse r = getHeadHandler().handle(ro);
        
        if (r.getStatus() >= 300) 
            throw new UniformInterfaceException("Status: " + r.getStatus(), r);
    }
}