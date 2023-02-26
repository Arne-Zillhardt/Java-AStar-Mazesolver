package GUI;

import Backend.Algorithm.AStar;
import Backend.Algorithm.GenerateMaze;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class GUI extends Application {
    public static int width;
    public static int height;
    public static ArrayList<Field> fields;
    public static ArrayList<Field> unMarkedFields;
    public static Field start;
    public static Field end;
    static int spacePressed;
    static Random random;
    Stage stage;
    GridPane root;
    Scene scene;

    @Override
    public void start(Stage st) throws Exception {
        stage = new Stage();
        stage.setTitle("A* Pathfinding");
        stage.setResizable(false);

        random = new Random();

        setUp();

        stage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.R)) {
                setUp();
            }

            if (e.getCode().equals(KeyCode.SPACE) && spacePressed == 0) {
                spacePressed ++;
                Thread thread = new GenerateMaze();
                thread.start();
            } else if (e.getCode().equals(KeyCode.SPACE) && spacePressed == 1) {
                AStar.pathfinding();
            }
        });

        stage.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Field field = (Field) e.getTarget();

            if (e.getButton().equals(MouseButton.PRIMARY) && start == null && field != end) {
                field.setFill(Color.GREEN);
                start = field;
                unMarkedFields.add(start);
            } else if (e.getButton().equals(MouseButton.PRIMARY) && end == null && field != start) {
                field.setFill(Color.BLUE);
                end = field;
            } else {
                if (e.getButton().equals(MouseButton.PRIMARY) && field == start) {
                    field.setFill(Color.RED);
                    unMarkedFields.remove(start);
                    start = null;
                } else if (e.getButton().equals(MouseButton.PRIMARY) && field == end) {
                    field.setFill(Color.RED);
                    end = null;
                } else if (e.getButton().equals(MouseButton.PRIMARY)) {
                    end.setFill(Color.WHITE);
                    field.setFill(Color.BLUE);
                    end = field;
                }
            }
        });

        stage.show();
    }

    public void setUp() {
        root = new GridPane();
        root.setPadding(new Insets(2, 2, 2, 2));
        root.setHgap(2);
        root.setVgap(2);

        spacePressed = 0;

        int x = random.nextInt(width);
        int y = random.nextInt(height);

        fields = new ArrayList<>();
        unMarkedFields = new ArrayList<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Field field = new Field(i, j);

                if (field.x == x && field.y == y) {
                    field.setFill(Color.BLUE);
                    field.start = true;
                    start = field;
                }

                root.add(field, i, j);
                fields.add(field);
            }
        }

        scene = new Scene(root);
        scene.setFill(Color.GRAY);

        stage.setScene(scene);
    }
}