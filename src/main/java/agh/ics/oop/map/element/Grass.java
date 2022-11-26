package agh.ics.oop.map.element;

import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.Vector2d;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Grass extends AbstractWorldMapElement {

    private final static Random grassRandom = new Random();

    public Grass(IWorldMap map, int lowerBound, int upperBound) {
        this(map, new Vector2d(grassRandom.nextInt(lowerBound, upperBound), grassRandom.nextInt(lowerBound, upperBound)));
    }

    public Grass(IWorldMap map, Vector2d position) {
        super(map, false, position);
    }

    @Override
    public Path getTexture() {
        return Paths.get("src/main/resources/grass.png");
    }

    @Override
    public String toString() {
        return "*";
    }
}
