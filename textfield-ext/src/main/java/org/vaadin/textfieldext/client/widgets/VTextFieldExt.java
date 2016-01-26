package org.vaadin.textfieldext.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vaadin.client.StyleConstants;
import com.vaadin.client.ui.Field;
import com.vaadin.client.ui.VTextField;

public class VTextFieldExt extends Composite implements Field {

	public static final String CLASS_NAME = "v-textfieldext";
	public static final String VALIDATION_ERROR_LABEL_CLASS_NAME = "v-textfield-validation-label";

	private VerticalPanel panel;
	private Label validationErrorLabel;
	private TextBox textBox;

	public VTextFieldExt() {
		super();

		panel = new VerticalPanel();

		textBox = new TextBox();		
		textBox.setStyleName(VTextField.CLASSNAME);
		textBox.addStyleName(StyleConstants.UI_WIDGET);
		panel.add(textBox);

		validationErrorLabel = new Label();
		validationErrorLabel.setStyleName(VALIDATION_ERROR_LABEL_CLASS_NAME);
		panel.add(validationErrorLabel);

		initWidget(panel);

		setStyleName(CLASS_NAME);
	}

	public TextBox getTextBox() {
		return textBox;
	}

	public void setText(String text) {
		textBox.setText(text);
	}

	public String getText() {
		return textBox.getText();
	}

	public Label getValidationErrorLabel() {
		return validationErrorLabel;
	}
}
