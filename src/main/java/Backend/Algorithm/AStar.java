package Backend.Algorithm;

import GUI.GUI;
import GUI.Field;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class AStar {
    public static ArrayList<Field> map = new ArrayList<>();

    public static void pathfinding() {
        ArrayList<Field> OPEN = new ArrayList<>();
        ArrayList<Field> CLOSED = new ArrayList<>();

        OPEN.add(GUI.start);

        while (true) {
            Field current = OPEN.get(0);
            if (OPEN.size() != 1) {
                for (int i = 1; i < OPEN.size(); i++) {
                    if (OPEN.get(i).getFCost() < current.getFCost()){
                        if (OPEN.get(i).getHCost() < current.getHCost()) {
                            current = OPEN.get(i);
                        }
                    }
                }
            }

            OPEN.remove(current);
            CLOSED.add(current);

            if (current.equals(GUI.end)) {
                retracePath();
                break;
            }

            for (Field neighbour : current.getNeighbours()) {
                if (!CLOSED.contains(neighbour)) {
                    int newCostToNeighbour = current.getGCost() + getDistance(current, neighbour);

                    if (newCostToNeighbour < neighbour.getGCost() || !OPEN.contains(neighbour)) {
                        neighbour.setGCost(newCostToNeighbour);
                        neighbour.setHCost(getDistance(neighbour, GUI.end));
                        neighbour.parent = current;

                        if (!OPEN.contains(neighbour)) {
                            OPEN.add(neighbour);
                        }
                    }
                }
            }
        }
    }

    public static void retracePath() {
        Field start = GUI.start;
        Field current = GUI.end;
        Field end = GUI.end;

        while (!current.equals(start)) {
            if (!current.equals(end)) {
                current.setFill(Color.CYAN);
            }

            current = current.parent;
        }
    }

    public static int getDistance(Field labelA, Field labelB) {
        int distX = Math.abs(labelA.x - labelB.x);
        int distY = Math.abs(labelA.y - labelB.y);

        if (distX > distY) {
            return 14 * distY + 10 * (distX - distY);
        } else {
            return 14 * distX + 10 * (distY - distX);
        }
    }
}
