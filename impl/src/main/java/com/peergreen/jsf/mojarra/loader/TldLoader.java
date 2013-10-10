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
package com.peergreen.jsf.mojarra.loader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Validate;
import org.osgi.framework.BundleContext;

/**
 * Used to load the JSTL Taglibs TLD.
 * @author Florent Benoit
 */
@Component
@Provides
@Instantiate
public class TldLoader {

    /**
     * Collection of URLs.
     */
    private final Collection<URL> urls;

    /**
     * Bundle context.
     */
    private final BundleContext bundleContext;


    /**
     * Constructor.
     */
    public TldLoader(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
        this.urls = new ArrayList<>();
    }

    /**
     * In validate method, gets all URL of TLD files.
     */
    @Validate
    public void start() {
        Enumeration<URL> enumeration = bundleContext.getBundle().findEntries("META-INF", "*.tld", false);
        while (enumeration.hasMoreElements()) {
         urls.add(enumeration.nextElement());
        }
    }

    /**
     * Clear all URLs.
     */
    @Invalidate
    public void stop() {
        urls.clear();
    }

    /**
     * @return the TLD found
     */
    public Collection<URL> getTLDs() {
        return urls;
    }


}
