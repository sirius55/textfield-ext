package org.vaadin.textfieldext.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.textfieldext.components.TextFieldExt;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Title("TextField Ext Add-on Demo")
@SuppressWarnings("serial")
@Theme("textfieldext-demo")
public class DemoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.textfieldext.demo.DemoTextFieldExtWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		VerticalLayout layout = new VerticalLayout();

		// Name Layout
		final PropertysetItem itemName = new PropertysetItem();
		final FieldGroup fieldGroupName = new FieldGroup(itemName);
		itemName.addItemProperty("firstname", new ObjectProperty<String>(""));
		itemName.addItemProperty("lastname", new ObjectProperty<String>(""));

		final VerticalLayout layoutName = new VerticalLayout();
		layoutName.setSizeFull();

		TextFieldExt firstName = new TextFieldExt();
		firstName.setCaption("First Name: ");
		firstName.addValidator(new StringLengthValidator("First name must be 2-5 chars.", 2, 5, false));
		firstName.setValidationVisible(false);
		fieldGroupName.bind(firstName, "firstname");

		TextFieldExt lastName = new TextFieldExt();
		lastName.setCaption("Last Name: ");
		lastName.addValidator(new StringLengthValidator("Last name must be 3-6 chars.", 2, 5, false));
		lastName.setValidationVisible(false);
		fieldGroupName.bind(lastName, "lastname");

		Button login = new Button("Login");

		Panel panelName = new Panel();
		panelName.setWidth("480px");
		
		layoutName.addComponent(firstName);
		layoutName.setComponentAlignment(firstName, Alignment.MIDDLE_CENTER);
		layoutName.addComponent(lastName);
		layoutName.setComponentAlignment(lastName, Alignment.MIDDLE_CENTER);
		layoutName.addComponent(login);
		layoutName.setComponentAlignment(login, Alignment.MIDDLE_CENTER);
		
		panelName.setContent(layoutName);
		layout.addComponent(panelName);
		layout.setComponentAlignment(panelName, Alignment.MIDDLE_CENTER);
		
		login.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				try {
					fieldGroupName.commit();
				} catch (CommitException e) {
					firstName.setValidationVisible(true);
					lastName.setValidationVisible(true);
				}
			}
		});

		Label label= new Label("");
		label.addStyleName("h4");
		layout.addComponent(label);

		// Email Layout
		final PropertysetItem itemEmail = new PropertysetItem();
		final FieldGroup fieldGroupEmail = new FieldGroup(itemEmail);
		itemEmail.addItemProperty("email", new ObjectProperty<String>(""));

		final VerticalLayout layoutEmail = new VerticalLayout();
		layoutEmail.setSizeFull();

		Panel panelEmail = new Panel();
		panelEmail.setWidth("480px");
		
		TextFieldExt email = new TextFieldExt();
		email.setCaption("Email: ");
		email.addValidator(new EmailValidator("Email is not valid."));
		email.setValidationVisible(false);
		fieldGroupEmail.bind(email, "email");

		Button send = new Button("Send");

		layoutEmail.addComponent(email);
		layoutEmail.setComponentAlignment(email, Alignment.MIDDLE_CENTER);
		
		layoutEmail.addComponent(send);
		layoutEmail.setComponentAlignment(send, Alignment.MIDDLE_CENTER);
		
		panelEmail.setContent(layoutEmail);
		layout.addComponent(panelEmail);
		layout.setComponentAlignment(panelEmail, Alignment.MIDDLE_CENTER);
		
		send.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				try {
					fieldGroupEmail.commit();
				} catch (CommitException e) {
					email.setValidationVisible(true);
				}
			}
		});
				
		setContent(layout);
	}
}
