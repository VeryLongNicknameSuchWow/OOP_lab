package agh.ics.oop.gui;

import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.map.*;
import agh.ics.oop.map.element.AbstractWorldMapElement;
import agh.ics.oop.map.element.MoveDirection;
import agh.ics.oop.util.OptionsParser;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application implements IPositionChangeObserver {

    Thread engineThread;
    private GridPane gridPane;
    private AbstractWorldMap worldMap;
    private int minX, maxX, minY, maxY;
    private SimulationEngine engine;

    @Override
    public void init() throws Exception {
        String[] args = getParameters().getUnnamed().toArray(String[]::new);
        MoveDirection[] directions = OptionsParser.parse(args);

        worldMap = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        engine = new SimulationEngine(directions, worldMap, positions);
        engine.setMoveDelay(300);
        engine.addObserver(this);
        engineThread = new Thread(engine);

        super.init();
    }

    @Override
    public void start(Stage primaryStage) {
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        update();

        Scene scene = new Scene(gridPane, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        engineThread.start();
    }

    public void update() {
        MapBoundary mapBoundary = worldMap.getMapBoundary();
        minX = mapBoundary.getMinByX().x;
        maxX = mapBoundary.getMaxByX().x;
        minY = mapBoundary.getMinByY().y;
        maxY = mapBoundary.getMaxByY().y;

        gridPane.getChildren().clear();

        addConstraints();
        displayRowColumnLabels();
        displayMap();
    }

    private void addConstraints() {
        for (int col = 0; col <= (maxX - minX) + 1; col++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(50));
        }
        for (int row = 0; row <= (maxY - minY) + 1; row++) {
            gridPane.getRowConstraints().add(new RowConstraints(50));
        }
    }

    private void displayRowColumnLabels() {
        Label desc = new Label("y/x");
        gridPane.add(desc, 0, 0);
        GridPane.setHalignment(desc, HPos.CENTER);

        for (int x = minX; x <= maxX; x++) {
            int gridX = x - minX + 1;
            Label label = new Label(String.valueOf(x));
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.add(label, gridX, 0);
        }

        for (int y = minY; y <= maxY; y++) {
            int gridY = maxY - y + 1;
            Label label = new Label(String.valueOf(y));
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.add(label, 0, gridY);
        }
    }

    private void displayMap() {
        for (int x = minX; x <= maxX; x++) {
            int gridX = x - minX + 1;
            for (int y = minY; y <= maxY; y++) {
                int gridY = maxY - y + 1;
                Object mapObject = worldMap.objectAt(new Vector2d(x, y));
                if (mapObject instanceof AbstractWorldMapElement mapElement) {
                    GuiElementBox elementBox = new GuiElementBox(mapElement);
                    GridPane.setHalignment(elementBox, HPos.CENTER);
                    gridPane.add(elementBox, gridX, gridY);
                }
            }
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        update();
    }
}
