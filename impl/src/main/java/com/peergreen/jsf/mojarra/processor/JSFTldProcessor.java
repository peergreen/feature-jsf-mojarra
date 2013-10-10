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
package com.peergreen.jsf.mojarra.processor;

import org.apache.felix.ipojo.annotations.Requires;

import com.peergreen.deployment.ProcessorContext;
import com.peergreen.deployment.ProcessorException;
import com.peergreen.deployment.processor.Phase;
import com.peergreen.deployment.processor.Processor;
import com.peergreen.jsf.mojarra.loader.TldLoader;
import com.peergreen.webcontainer.WebApplication;

/**
 * Processor that will register JSF TLDs on the web applications
 * @author Florent Benoit
 */
@Processor
@Phase("WEBAPP_TLD")
public class JSFTldProcessor {

    @Requires(proxy=false)
    private TldLoader tldLoader;

    public void handle(WebApplication webApplication, ProcessorContext processorContext) throws ProcessorException {
        // Adds all TLD URLs found
        webApplication.getExtraTlds().addAll(tldLoader.getTLDs());
    }

}
