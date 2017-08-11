package org.vaadin.textfieldext.components;

import org.vaadin.textfieldext.client.connectors.TextFieldExtState;
import org.vaadin.textfieldext.client.rpc.TextFieldExtServerRpc;

import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.ui.AbstractField;

public class TextFieldExt extends AbstractField<String> {

	public TextFieldExt() {
		registerRpc(new TextFieldExtServerRpc() {
			@Override
			public void sendText(String text) {
				setValue(text);
			}
		});
	}
	@Override
	protected TextFieldExtState getState() {
		return (TextFieldExtState) super.getState();
	}

	@Override
	public void setValue(String newFieldValue)  {
		super.setValue(newFieldValue);

		getState().text = newFieldValue;
	}
	
	@Override
	public String getValue() {
		return getState().text;
	}
	@Override
	protected void doSetValue(String value) {
		setValue(value);
	}

	@Override
	public Class<? extends String> getType() {
		return String.class;
	}
}
