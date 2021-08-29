package sample;

/**
 *imports implemented in the application
 * @author Luis Alejandro Barreda
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server class
 */
public class Server extends Application implements Runnable {

    /**
     * Variables for the application and sockets
     */
    Scene scn;
    VBox box;
    TextArea txtarea;
    Thread preps;
    Socket sckt, snsocket;
    ServerSocket resocket;
    Prod_details details;

    /**
     * class that starts the application
     */
    @Override
    public void start(Stage server){

        /**
         * Text Area from Java FX generation and location specifications
         */
        txtarea = new TextArea();
        txtarea.setTranslateX(0);
        txtarea.setTranslateY(0);

        /**
         * Vbox from Java FX generation and get Children method
         */
        box = new VBox();
        box.getChildren().add(txtarea);

        /**
         * Scene from Java FX generation
         */
        scn = new Scene(box);

        /**
         * Thread initialization and generation
         */
        preps = new Thread(this);
        preps.start();

        /**
         * Scenario location and dimensions specifications.
         * Show method for the application to be seen
         */
        server.setTitle("Server");
        server.setHeight(200);
        server.setWidth(500);
        server.setScene(scn);
        server.show();
    }

    /**
     * Run method, started by the prep Thread.
     * Generates the server socket that is always looking for messages from the client
     */
    @Override
    public void run() {
        try {
            resocket = new ServerSocket(4999);

            /**
             * While loop that keeps the socket looking for messages
             */
            while (true) {
                sckt = resocket.accept();

                /**
                 * The message sent by the client is received as an object
                 * "entryflow" is the stream that receives it and "details"
                 * read the object that is received
                 */
                ObjectInputStream entryflow = new ObjectInputStream(sckt.getInputStream());
                details = (Prod_details) entryflow.readObject();

                /**
                 * Extracts the details by the .getDetail() method from the class in a String
                 */
                String value = details.getValue();
                String weight = details.getWeight();
                String tax = details.getTax();

                /**
                 * Converts the Strings to double for the calculation of the total price
                 */
                double val = Integer.parseInt(value);
                double wght = Integer.parseInt(weight);
                double tx = Integer.parseInt(tax);

                /**
                 * Calculation of the total price
                 * Converts the result back to a String
                 */
                double ope = (val * (tx / 100)) + (wght * 0.15);
                String res = String.valueOf(ope);

                /**
                 * Appends the text with the details in the server text area
                 */
                txtarea.appendText("\n" + "The value is: " + value + ". The weight is: " + weight + ". The tax is: " + tax + ".");

                /**
                 * Generates another socket to send the information back to the client
                 * exitflow sends a string back and then the stream and sockets are closed
                 */
                snsocket = new Socket("192.168.100.108", 5000);
                DataOutputStream exitflow = new DataOutputStream(snsocket.getOutputStream());
                exitflow.writeUTF(String.valueOf(ope));
                exitflow.close();

                snsocket.close();
                sckt.close();
            }

        } catch (IOException | ClassNotFoundException e) {
            /**
             * Exception catch, send a message with the error
             */
            System.out.println(e.getMessage());
        }
    }

    /**
     * Launch the program
     */
    public static void main(String[] args) {
        launch(args);
    }

}
