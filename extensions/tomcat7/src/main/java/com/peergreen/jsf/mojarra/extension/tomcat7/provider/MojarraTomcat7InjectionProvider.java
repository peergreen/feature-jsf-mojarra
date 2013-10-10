/**
 * Copyright 2013 Peergreen S.A.S. All rights reserved.
 * Proprietary and confidential.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.peergreen.jsf.mojarra.extension.tomcat7.provider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.naming.NamingException;
import javax.servlet.ServletContext;

import org.apache.tomcat.InstanceManager;

import com.sun.faces.spi.InjectionProvider;
import com.sun.faces.spi.InjectionProviderException;

/**
 * Based on Tomcat6 injection provider in previous Mojarra releases
 * @author Florent Benoit
 */
public class MojarraTomcat7InjectionProvider implements InjectionProvider {

    /**
     * ServletContext parameter.
     */
    private final ServletContext servletContext;

    /**
     * Instance Manager.
     */
    private InstanceManager instanceManager = null;

    /**
     * newInstance method.
     */
    private final Method newInstanceMethod = null;

    /**
     * destroyInstance method.
     */
    private final Method destroyInstanceMethod = null;


    public MojarraTomcat7InjectionProvider(ServletContext servletContext) {
        this.servletContext = servletContext;
        this.instanceManager = (InstanceManager) servletContext.getAttribute(InstanceManager.class.getName());
    }


    /**
     * <p>The implementation of this method must perform the following
     * steps:
     *    <ul>
     *        <li>Inject the supported resources per the Servlet 2.5
     *           specification into the provided object</li>
     *    </ul>
     * </p>
     * <p>This method <em>must not</em> invoke any methods
     * annotated with <code>@PostConstruct</code>
     * @param managedBean the target managed bean
     * @throws InjectionProviderException if an error occurs during
     *  resource injection
     */
    @Override
    public void inject(Object managedBean) throws InjectionProviderException {
        try {
            instanceManager.newInstance(managedBean);
        } catch (IllegalAccessException | InvocationTargetException | NamingException e) {
            throw new InjectionProviderException("Unable to perform injection", e);
        }
    }

    /**
     * <p>The implemenation of this method must invoke any
     * method marked with the <code>@PreDestroy</code> annotation
     * (per the Common Annotations Specification).
     *
     * @param managedBean the target managed bean
     * @throws com.sun.faces.spi.InjectionProviderException
     *          if an error occurs when invoking
     *          the method annotated by the <code>@PreDestroy</code> annotation
     */
    @Override
    public void invokePreDestroy(Object managedBean) throws InjectionProviderException {
        try {
            instanceManager.destroyInstance(managedBean);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new InjectionProviderException("Unable to invoke preDestroy", e);
        }
    }

    /**
     * <p>The implemenation of this method must invoke any
     * method marked with the <code>@PostConstruct</code> annotation
     * (per the Common Annotations Specification).
     *
     * @param managedBean the target managed bean
     * @throws com.sun.faces.spi.InjectionProviderException
     *          if an error occurs when invoking
     *          the method annotated by the <code>@PostConstruct</code> annotation
     */
    @Override
    public void invokePostConstruct(Object managedBean) throws InjectionProviderException {
        // performed in inject() method
    }






}
