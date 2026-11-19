package com.iss.echoesofdeceit.controller;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.iss.echoesofdeceit.model.SoundsManager;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

@JsonTypeName("PuzzleElement")  // Associa il nome al tipo


public class PuzzleElement implements Component {
    private String type;
    private String element;
    private String text;
    private String styleClass;
    private String actionId;
    private String image;
    private boolean wrapText;
    private static int index = 1;
    private static String textAreaString = "";

    public PuzzleElement(){}


    @Override
    public Node render() {
        switch (this.element) {
            case "Button":
                Button button = new Button(this.text);
                button.getStyleClass().add(this.styleClass);
                button.setOnAction(event -> {
                    PuzzleController.list.add(this.actionId);
                    button.setDisable(true);

                  if(button.getStyleClass().toString().equals("button button-guard-puzzle") ) {
                     button.setText(String.valueOf(index));
                     index+=1;
                    }

                    if(button.getStyleClass().toString().equals("button button-inseguimento") ) {
                        button.setDisable(true);
                    }

                });
                return button;
            case "SpecialButton" :
                Button specialButton = new Button(this.text);
                specialButton.getStyleClass().add(this.styleClass);
                if(specialButton.getText().equals("- 1"))
                    PuzzleController.listDecrementsButtons.add(specialButton);
                else if(specialButton.getText().equals("+ 1"))
                    PuzzleController.listIncrementsButtons.add(specialButton);

                return specialButton;
            case "ImageView":
                ImageView imageView = new ImageView(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/com/iss/echoesofdeceit/images/" + this.image)))));
                imageView.getStyleClass().add(this.styleClass);
                return imageView;
            case "ImageButton":
                ImageView imageButton = new ImageView(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/com/iss/echoesofdeceit/images/" + this.image)))));
                imageButton.getStyleClass().add(this.styleClass);
                imageButton.setOnMouseClicked(e -> {
                    PuzzleController.list.add(this.actionId);
                    if (imageButton.getStyleClass().toString().matches(".*image-view\\s+(img-piano-(DO|RE|MI|FA|SOL|LA|SI|DO2)|img-nota-nera(1|2|3|4|5|6)|img-carillon).*")) {
                        if (imageButton.getStyleClass().toString().equals("image-view img-piano-DO")) SoundsManager.getInstance().playSound("i");
                        if (imageButton.getStyleClass().toString().equals("image-view img-piano-RE")) SoundsManager.getInstance().playSound("o");
                        if (imageButton.getStyleClass().toString().equals("image-view img-piano-MI")) SoundsManager.getInstance().playSound("p");
                        if (imageButton.getStyleClass().toString().equals("image-view img-piano-FA")) SoundsManager.getInstance().playSound("a");
                        if (imageButton.getStyleClass().toString().equals("image-view img-piano-SOL")) SoundsManager.getInstance().playSound("s");
                        if (imageButton.getStyleClass().toString().equals("image-view img-piano-LA")) SoundsManager.getInstance().playSound("d");
                        if (imageButton.getStyleClass().toString().equals("image-view img-piano-SI")) SoundsManager.getInstance().playSound("f");
                        if (imageButton.getStyleClass().toString().equals("image-view img-piano-DO2")) SoundsManager.getInstance().playSound("g");
                        if (imageButton.getStyleClass().toString().equals("image-view img-nota-nera1")) SoundsManager.getInstance().playSound("I1");
                        if (imageButton.getStyleClass().toString().equals("image-view img-nota-nera2")) SoundsManager.getInstance().playSound("O1");
                        if (imageButton.getStyleClass().toString().equals("image-view img-nota-nera3")) SoundsManager.getInstance().playSound("P1");
                        if (imageButton.getStyleClass().toString().equals("image-view img-nota-nera4")) SoundsManager.getInstance().playSound("S1");
                        if (imageButton.getStyleClass().toString().equals("image-view img-nota-nera5")) SoundsManager.getInstance().playSound("D1");
                        if (imageButton.getStyleClass().toString().equals("image-view img-nota-nera6")) SoundsManager.getInstance().playSound("G1");
                        if (imageButton.getStyleClass().toString().equals("image-view img-carillon")) SoundsManager.getInstance().playSound("carrillion");
                    }
                    else {
                        imageButton.setDisable(true);
                    }

                    if(imageButton.getStyleClass().toString().equals("image-view img-grid-button")){
                        imageButton.getParent().setStyle("-fx-border-color: red;");
                    }
                });
                return imageButton;

            case "TextField":
                TextField textField = new TextField();
                textField.getStyleClass().add(this.styleClass);
                textField.requestFocus();
                textField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                        if(textField.getStyleClass().toString().equals("text-input text-field input-textfield-padlock") ) {
                            if (newValue.length() > 4 || !(newValue.matches("^[0-9]*$")) )
                                textField.setText(oldValue);

                        }else if(textField.getStyleClass().toString().equals("text-input text-field input-textfield-mercanti")){
                            if (newValue.length() > 1 ) {
                                textField.setText(oldValue);
                            }
                        }
                        else if (newValue.length() > 2) {
                            textField.setText(oldValue);
                        }
                        textAreaString = textField.getText().toLowerCase();
                    }
                });
                return textField;

            case "Label":
                Label label = new Label(this.text);
                label.getStyleClass().add(this.styleClass);
                label.setWrapText(true);
                return label;
            case "TextArea":
                TextArea textArea = new TextArea(this.text);
                textArea.getStyleClass().add(this.styleClass);
                textArea.setWrapText(true);
                textArea.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                        if (newValue.length() > 2000) {
                            textArea.setText(oldValue);
                        }
                        textAreaString = newValue;

                    }
                });
                return textArea;
            case "CodedLabel":
                Label codedLabel = new Label(this.text);
                codedLabel.getStyleClass().add(this.styleClass);
                codedLabel.setWrapText(this.wrapText);
                PuzzleController.list.add(codedLabel);
                return codedLabel;
            default:
                return new Label("Elemento sconosciuto: " + this.element);
        }
    }


    public static void setTextAreaString(String textAreaString) {
        PuzzleElement.textAreaString = textAreaString;
    }

    public static String getTextAreaString() {
        return PuzzleElement.textAreaString;
    }

    public static void setIndex(int index) {
        PuzzleElement.index = index;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isWrapText() {
        return wrapText;
    }

    public void setWrapText(boolean wrapText) {
        this.wrapText = wrapText;
    }
}
