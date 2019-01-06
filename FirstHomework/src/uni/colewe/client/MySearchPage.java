package uni.colewe.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;

public class MySearchPage extends FlowPanel {
	
	//private static Logger logger = Logger.getLogger(MySearchPage.class.getName());
	
	private MyDictionary dict;
	
	private HTML resultBlock;
	
	private TextBox searchBox;
	private Button btn_search;
	private CheckBox btn_highLight;
	private CheckBox btn_startsWith;
	private RadioButton btn_ruToEng;
	private RadioButton btn_EngToRu;
	
	private boolean searchActive = false;
	
	public MySearchPage(MyDictionary dict){
		super();
		this.dict = dict;
		createContent();
	}

	private void createContent() {
		createSearchOptions();
		createSearchEntry();
		createResultBlock();
		
		
		
	}
	

	private void createSearchOptions(){
		FlowPanel searchOptions = new FlowPanel();
		
		Label lbl1 = new Label("Query language: ");
		
		btn_ruToEng = new RadioButton("queryLanguage", "Russian");
		btn_EngToRu = new RadioButton("queryLanguage", "English");
		
		btn_ruToEng.setValue(true);
		
		btn_startsWith = new CheckBox("Also match words starting with query");
		btn_startsWith.setValue(false);
		
		btn_highLight = new CheckBox("Highlight matches");
		btn_highLight.setValue(false);
		btn_highLight.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if(searchActive){
					performLookup();	
				}
			}
		});
		
		searchOptions.add(lbl1);
		searchOptions.add(btn_ruToEng);
		searchOptions.add(btn_EngToRu);
		searchOptions.add(btn_startsWith);
		searchOptions.add(btn_highLight);
		
		this.add(searchOptions);
	}

	private void createSearchEntry() {
		FlowPanel searchEntry = new FlowPanel();
		
		searchBox = new TextBox();
		searchBox.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
					performLookup();
				}
				
			}			
		});
		
		btn_search = new Button("Search");
		btn_search.addMouseUpHandler(new MouseUpHandler(){

			@Override
			public void onMouseUp(MouseUpEvent event) {
				performLookup();
				
			}
			
		});
		
		searchEntry.add(searchBox);
		searchEntry.add(btn_search);
		
		this.add(searchEntry);
		
	}
	
	private void createResultBlock() {
		resultBlock = new HTML("<p>Enter a query in the above box and press the search button or hit enter to execute it</p>");
		this.add(resultBlock);
		
	}
	
	private void performLookup() {
		searchActive = true;
		//logger.log(Level.INFO, "Performing lookup");
		resultBlock.setHTML(dict.lookup(searchBox.getText().trim(), btn_EngToRu.getValue(), btn_startsWith.getValue(), btn_highLight.getValue()));		
	}

}
