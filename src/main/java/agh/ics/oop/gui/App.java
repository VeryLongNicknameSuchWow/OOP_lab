package agh.ics.oop.gui;

import agh.ics.oop.engine.IEngine;
import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.map.GrassField;
import agh.ics.oop.map.MapBoundary;
import agh.ics.oop.map.Vector2d;
import agh.ics.oop.map.element.AbstractWorldMapElement;
import agh.ics.oop.map.element.MoveDirection;
import agh.ics.oop.util.OptionsParser;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {

    private AbstractWorldMap worldMap;
    private int minX, maxX, minY, maxY;

    @Override
    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(String[]::new);
        MoveDirection[] directions = OptionsParser.parse(args);

        worldMap = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(directions, worldMap, positions);
        engine.run();

        MapBoundary mapBoundary = worldMap.getMapBoundary();
        minX = mapBoundary.getMinByX().x;
        maxX = mapBoundary.getMaxByX().x;
        minY = mapBoundary.getMinByY().y;
        maxY = mapBoundary.getMaxByY().y;

        System.out.println(worldMap);
        super.init();
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(true);

        addConstraints(gridPane);
        displayRowColumnLabels(gridPane);
        displayMap(gridPane);

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println();
    }

    private void addConstraints(GridPane gridPane) {
        for (int col = 0; col <= (maxX - minX) + 1; col++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(20));
        }
        for (int row = 0; row <= (maxY - minY) + 1; row++) {
            gridPane.getRowConstraints().add(new RowConstraints(20));
        }
    }

    private void displayRowColumnLabels(GridPane gridPane) {
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

    private void displayMap(GridPane gridPane) {
        for (int x = minX; x <= maxX; x++) {
            int gridX = x - minX + 1;
            for (int y = minY; y <= maxY; y++) {
                int gridY = maxY - y + 1;
                Object mapObject = worldMap.objectAt(new Vector2d(x, y));
                if (mapObject instanceof AbstractWorldMapElement mapElement) {
                    Label label = new Label(mapElement.toString());
                    GridPane.setHalignment(label, HPos.CENTER);
                    gridPane.add(label, gridX, gridY);
                }
            }
        }
    }
}
