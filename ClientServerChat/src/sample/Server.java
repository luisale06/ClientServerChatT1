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
