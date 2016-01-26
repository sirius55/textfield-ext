package org.vaadin.textfieldext.client.connectors;

import org.vaadin.textfieldext.client.rpc.TextFieldExtServerRpc;
import org.vaadin.textfieldext.client.widgets.VTextFieldExt;
import org.vaadin.textfieldext.components.TextFieldExt;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(TextFieldExt.class)
public class TextFieldExtConnector extends AbstractComponentConnector implements BlurHandler {

	private final static String ERROR_MESSAGE = "errorMessage";
	private final static String TEXTFIELD_ERROR_CLASSNAME = "v-textfield-error";
	
	public TextFieldExtConnector() {
		getWidget().getTextBox().addBlurHandler(this);
	}

	@Override
	public VTextFieldExt getWidget() {
		return (VTextFieldExt) super.getWidget();
	}

	@Override
	protected Widget createWidget() {
		return GWT.create(VTextFieldExt.class);
	}

	@Override
	public TextFieldExtState getState() {
		return (TextFieldExtState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		if (stateChangeEvent.hasPropertyChanged(ERROR_MESSAGE) && getState().errorMessage != null) {
			getWidget().getValidationErrorLabel().getElement().setInnerHTML(getState().errorMessage);
			getWidget().getTextBox().addStyleName(TEXTFIELD_ERROR_CLASSNAME);
		} else if (stateChangeEvent.hasPropertyChanged(ERROR_MESSAGE) && getState().errorMessage == null) {
			getWidget().getTextBox().removeStyleName(TEXTFIELD_ERROR_CLASSNAME);
			getWidget().getValidationErrorLabel().getElement().removeAllChildren();
		}

		getWidget().setText(getState().text);
	}

	@Override
	public void onBlur(BlurEvent blurEvent) {
		sendText();
	}

	private void sendText() {
		getRpcProxy(TextFieldExtServerRpc.class).sendText(getWidget().getText());
	}
}
