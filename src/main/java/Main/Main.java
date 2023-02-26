package Main;

import GUI.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Width of the map: ");
            int x = Integer.parseInt(reader.readLine());
            System.out.println();

            System.out.print("Height of the map: ");
            int y = Integer.parseInt(reader.readLine());

            GUI.width = x;
            GUI.height = y;

            launch(GUI.class, args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
