package Backend.Algorithm;

import GUI.Field;
import GUI.GUI;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class GenerateMaze extends Thread{
    public ArrayList<Field> map;
    ArrayList<Field> tmp;
    boolean bol = true;

    public void generate() {
        Random random = new Random();

        map = new ArrayList<>();
        map.add(GUI.start);

        GUI.start.mapped = true;

        while (true) {
            Field tmpField = map.get(map.size() - 1);

            tmp = tmpField.getUnmappedAndUnmarkedNeighbours();

            if (tmp.size() == 0) {
                jammed();
            }

            if (!bol) {
                break;
            }

            int rand = random.nextInt(tmp.size());

            Field field = tmp.get(rand);
            field.mapped = true;

            map.add(field);

            draw();
        }

        GUI.start.mapped = false;
        GUI.start = null;
        this.interrupt();
    }

    public void jammed() {
        for (int i = map.size() - 1; i > -1 ; i--) {
            Field mapped = map.get(i);

            if (mapped.start) {
                bol = false;
                break;
            }

            for (Field field : mapped.getUnmappedNeighbours()) {
                if (!field.mapped && field.getUnmappedAndUnmarkedNeighbours().size() != 0) {
                    field.blocked = false;
                    field.mapped = true;
                    map.add(field);
                    tmp = field.getUnmappedAndUnmarkedNeighbours();
                    return;
                }
            }
        }
    }

    public void draw() {
        for (Field field : GUI.fields) {
            if (map.contains(field)) {
                field.setFill(Color.RED);
            }
        }

        blocking();
    }

    public void blocking() {
        for (Field field01 : map) {
            if (!field01.equals(map.get(map.size() - 1))) {
                for (Field field02 : field01.getUnmappedAndUnmarkedNeighbours()) {
                    field02.blocked = true;
                    field02.setFill(Color.BLACK);
                }
            }
        }

        try {
            //Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        generate();
    }
}
