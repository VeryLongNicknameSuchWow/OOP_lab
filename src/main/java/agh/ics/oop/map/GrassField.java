package agh.ics.oop.map;

import agh.ics.oop.map.element.AbstractWorldMapElement;
import agh.ics.oop.map.element.Grass;

public class GrassField extends AbstractWorldMap {

    private final int grassUpperBound;

    public GrassField(int grassFieldCount) {
        super();
        this.grassUpperBound = (int) Math.min(Integer.MAX_VALUE, Math.round(Math.sqrt(grassFieldCount * 10)));
        for (int i = 0; i < grassFieldCount; i++) {
            placeRandomGrass();
        }
    }

    private void placeRandomGrass() {
        boolean placed;
        do {
            placed = placeElement(new Grass(this, 0, grassUpperBound));
        } while (!placed);
    }

    @Override
    public boolean placeElement(AbstractWorldMapElement element) {
        Vector2d position = element.getPosition();
        if (isGrass(position)) {
            placeRandomGrass();
        }
        return super.placeElement(element);
    }

    public boolean isGrass(Vector2d position) {
        return objectAt(position) instanceof Grass;
    }
}
