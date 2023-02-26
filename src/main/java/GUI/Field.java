package GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Field extends Rectangle {
    public int x;
    public int y;
    public boolean start;
    public boolean mapped;
    public boolean blocked;
    public boolean colored;
    public Field parent;

    private int gCost;
    private int hCost;

    public Field (int x, int y) {
        this.x = x;
        this.y = y;
        this.start = false;
        this.mapped = false;
        this.blocked = false;
        this.colored = false;

        this.setHeight(25);
        this.setWidth(25);

        this.setFill(Color.WHITE);
    }

    public static Field getField(int x, int y) {
        Field field = new Field(x, y);

        for (Field field1 : GUI.fields) {
            if (field.y == field1.y && field.x == field1.x) {
                field = field1;
                break;
            }
        }

        return field;
    }

    public ArrayList<Field> getNeighbours() {
        ArrayList<Field> neighbours = new ArrayList<>();

        for (Field field : GUI.fields) {
            if (!field.blocked) {
                if ((field.x == this.x && field.y == this.y + 1) || (field.x == this.x && field.y == this.y - 1) || (field.x == this.x + 1 && field.y == this.y) || (field.x == this.x - 1 && field.y == this.y)) {
                    neighbours.add(field);
                }
            }
        }

        return neighbours;
    }

    public ArrayList<Field> getUnmappedNeighbours() {
        ArrayList<Field> neighbours = new ArrayList<>();

        for (Field field : GUI.fields) {
            if (!field.mapped) {
                if ((field.x == this.x && field.y == this.y + 1) || (field.x == this.x && field.y == this.y - 1) || (field.x == this.x + 1 && field.y == this.y) || (field.x == this.x - 1 && field.y == this.y)) {
                    neighbours.add(field);
                }
            }
        }

        return neighbours;
    }

    public ArrayList<Field> getUnmappedAndUnmarkedNeighbours() {
        ArrayList<Field> neighbours = new ArrayList<>();

        for (Field field : GUI.fields) {
            if (!field.blocked && !field.mapped) {
                if ((field.x == this.x && field.y == this.y + 1) || (field.x == this.x && field.y == this.y - 1) || (field.x == this.x + 1 && field.y == this.y) || (field.x == this.x - 1 && field.y == this.y)) {
                    neighbours.add(field);
                }
            }
        }

        return neighbours;
    }

    public int getFCost() {
        return gCost + hCost;
    }

    public int getGCost() {
        return gCost;
    }

    public int getHCost() {
        return hCost;
    }

    public void setGCost(int cost) {
        gCost = cost;
    }

    public void setHCost(int cost) {
        hCost = cost;
    }
}
