package com.gbuilder.mylog;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.data.util.TextFileProperty;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	FilesystemContainer logs = new FilesystemContainer(new File("C:\\Users\\Zularbine\\workspace_neon\\glassfishlog\\logs"));
	//FilesystemContainer logs = new FilesystemContainer(new File("../logs"));
	//ComboBox logList = new ComboBox("Logs", logs);
	ListSelect logList = new ListSelect("Logs", logs);
	Label logView = new Label("", ContentMode.PREFORMATTED);
	
	@Override
    protected void init(VaadinRequest vaadinRequest) {
		
		HorizontalSplitPanel split = new HorizontalSplitPanel();
		setContent(split);
		split.addComponent(logList);
		split.addComponent(logView);
		split.setSplitPosition(25, Sizeable.UNITS_PERCENTAGE);
		logList.addValueChangeListener(new ValueChangeListener() {

			public void valueChange(ValueChangeEvent event) {
				logView.setPropertyDataSource(new TextFileProperty((File) event.getProperty().getValue()));
				
			}
		});
		logList.setImmediate(true);
		
		
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
