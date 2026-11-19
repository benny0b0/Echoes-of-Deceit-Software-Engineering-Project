package com.iss.echoesofdeceit.controller;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javafx.scene.Node;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = PuzzleElement.class, name = "PuzzleElement"),
        @JsonSubTypes.Type(value = PuzzleComposite.class, name = "PuzzleComposite")
})

public interface Component {
    Node render();
}
