package uni.colewe.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FirstHomework implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
//		MyDictionary dict = new MyDictionary();
//		MySearchPage sp = new MySearchPage(dict);
		
		RootPanel.get().add(new MySearchPage(new MyDictionary()));
		
		
	}
}
