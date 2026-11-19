package com.iss.echoesofdeceit.controller;

import com.fasterxml.jackson.annotation.JsonTypeName;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;
@JsonTypeName("PuzzleComposite")  // Associa il nome al tipo


public class PuzzleComposite implements Component {
    private String type;
    private String styleClass;
    private String element;
    private List<Component> children;

    public PuzzleComposite(){}
    // Aggiungi un componente figlio
    public void addChild(Component component) {
        children.add(component);
    }
    @Override
    public Node render() {
        Pane container;

        switch (this.element) {
            case "VBox":
                container = new VBox();
                break;
            case "HBox":
                container = new HBox();
                break;
            case "StackPane":
                container = new StackPane();
                break;
            case "BorderPane":
                container = new BorderPane();

                for (Component child : this.children) {
                    if (child instanceof PuzzleComposite) {
                        PuzzleComposite childComposite = (PuzzleComposite) child;
                        Node childNode = child.render();
                        switch (childComposite.getElement()) {
                            case "top":
                                ((BorderPane) container).setTop(childNode);
                                break;
                            case "center":
                                ((BorderPane) container).setCenter(childNode);
                                break;
                            case "left":
                                ((BorderPane) container).setLeft(childNode);
                                break;
                            case "right":
                                ((BorderPane) container).setRight(childNode);
                                break;
                            case "bottom":
                                ((BorderPane) container).setBottom(childNode);
                                break;
                            default:
                                System.out.println("Elemento non valido per BorderPane: " + childComposite.getElement());
                                break;
                        }
                    } else {
                        System.out.println("Tipo di componente non gestito nel BorderPane");
                    }
                }
                return container; // Ritorna subito il BorderPane poiché è già stato configurato.
            default:
                container = new StackPane();
                break;
        }

        container.getStyleClass().add(this.styleClass);

        for (Component child : this.children) {
            container.getChildren().add(child.render());
        }
        return container;
    }


    public String getStyleclass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public List<Component> getChildren(){
        return this.children;
    }
    public void setChildren(List<Component> children) {
        this.children = children;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }
}
