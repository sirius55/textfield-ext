Custom Component for Vaadin with GWT
====================================

Abstract
--------

This is an extended TextField Component, which displays an error message from a validator below the TextField within this component.


Usage 
-----
```java
TextFieldExt tf = new TextFieldExt();

tf.addValidator(new StringLengthValidator("validation error !", 2, 3, false));

tf.setValidationVisible(false);

layout.addComponent(tf);

try {

  tf.validate();
  
} catch (Exception e) {

	tf.setValidationVisible(true);
	
}
```


