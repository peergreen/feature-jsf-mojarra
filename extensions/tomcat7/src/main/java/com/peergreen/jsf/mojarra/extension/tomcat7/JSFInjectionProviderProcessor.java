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
package com.peergreen.jsf.mojarra.extension.tomcat7;

import com.peergreen.deployment.ProcessorContext;
import com.peergreen.deployment.ProcessorException;
import com.peergreen.deployment.processor.Phase;
import com.peergreen.deployment.processor.Processor;
import com.peergreen.jsf.mojarra.extension.tomcat7.provider.MojarraTomcat7InjectionProvider;
import com.peergreen.webcontainer.tomcat7.PeergreenContext;
import com.peergreen.webcontainer.tomcat7.TomcatWebApplication;
import com.sun.faces.config.WebConfiguration.WebContextInitParameter;

/**
 * This processor will defined the InjectionProvider to use with the JSF implementation.
 * @author Florent Benoit
 */
@Processor
@Phase("PRE_START")
public class JSFInjectionProviderProcessor  {

    public void handle(TomcatWebApplication tomcatWebApplication, ProcessorContext processorContext) throws ProcessorException {

        // Gets the context
        PeergreenContext peergreenContext = tomcatWebApplication.getContext();

        // adds a parameter
        peergreenContext.addParameter(WebContextInitParameter.InjectionProviderClass.getQualifiedName(), MojarraTomcat7InjectionProvider.class.getName());

    }

}
