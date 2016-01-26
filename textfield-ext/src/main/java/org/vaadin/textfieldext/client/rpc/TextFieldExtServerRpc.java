package org.vaadin.textfieldext.client.rpc;

import com.vaadin.shared.annotations.Delayed;
import com.vaadin.shared.communication.ServerRpc;

public interface TextFieldExtServerRpc extends ServerRpc {

	@Delayed(lastOnly = true)
	public void sendText(String text);
}
