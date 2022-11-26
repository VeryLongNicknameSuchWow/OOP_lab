package agh.ics.oop.gui;

import agh.ics.oop.map.element.AbstractWorldMapElement;
import agh.ics.oop.map.element.Animal;
import agh.ics.oop.map.element.Grass;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class GuiElementBox extends VBox {

    private final AbstractWorldMapElement mapElement;

    private final ImageView imageView;
    private final Label label;

    public GuiElementBox(AbstractWorldMapElement mapElement) {
        super();
        imageView = new ImageView();
        label = new Label();
        this.mapElement = mapElement;

        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        getChildren().add(imageView);
        getChildren().add(label);
        setAlignment(Pos.CENTER);
        update();
    }

    public void update() {
        if (mapElement instanceof Animal) {
            label.setText("Z " + mapElement.getPosition().toString());
        } else if (mapElement instanceof Grass) {
            label.setText("Trawa");
        } else {
            label.setText(mapElement.toString());
        }

        label.setAlignment(Pos.CENTER);

        Path imagePath = mapElement.getTexture();
        Image image;
        try (InputStream imageInputStream = Files.newInputStream(imagePath)) {
            image = new Image(imageInputStream);
            imageView.setImage(image);
        } catch (IOException e) {
            System.out.println(imagePath + " does not contain a valid image");
            throw new RuntimeException(e);
        }
    }

    public AbstractWorldMapElement getMapElement() {
        return mapElement;
    }
}
