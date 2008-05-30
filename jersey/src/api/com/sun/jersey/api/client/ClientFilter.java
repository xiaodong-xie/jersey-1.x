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

/**
 * A client filter capable of modifying the outbound HTTP request or 
 * the inbound HTTP response. 
 * <p>
 * An application-based filter extends this class and implements the 
 * {@link ClientHandler#handle} method. The general implementation pattern
 * is as follows:
 * <blockquote><pre>
 *     class AppClientFilter extends ClientFilter {
 *
 *         public ClientResponse handle(ClientRequest cr) {
 *             // Modify the request
 *             ClientRequest mcr = modifyRequest(cr);
 * 
 *             // Call the next client handler in the filter chain
 *             ClientResponse resp = getNext(mcr);
 * 
 *             // Modify the response
 *             return modifyResponse(resp);
 *         }
 *
 *         private byte[] loadClassData(String name) {
 *             // load the class data from the connection
 *             &nbsp;.&nbsp;.&nbsp;.
 *         }
 *     }
 * </pre></blockquote>
 * 
 * @author Paul.Sandoz@Sun.Com
 */
public abstract class ClientFilter implements ClientHandler {
    private ClientHandler next;
    
    /* package */ final void setNext(ClientHandler next) {
        this.next = next;
    }
   
    /**
     * Get the next client handler to invoke in the chain
     * of filters.
     * 
     * @return the next client handler.
     */
    public final ClientHandler getNext() {
        return next;
    }
    
    public abstract ClientResponse handle(ClientRequest cr) 
            throws ClientHandlerException;
}
