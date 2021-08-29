package sample;

/**
 *imports implemented in the application
 * @author Luis Alejandro Barreda
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.ObjectOutputStream;
import java.net.*;
import java.io.*;

/**
 * Client class
 */
public class Main extends Application implements Runnable{

    /**
     * Variables for the application and sockets
     */
    Scene scn;
    VBox box;
    TextArea clientarea;
    Label lblval, lblwght, lbltax;
    TextField value_entry, weight_entry, tax_entry;
    Button btn;
    Socket snsocket, sckt;
    ServerSocket resocket;
    Thread prepc;

    /**
     * class that starts the application
     */
    @Override
    public void start(Stage chat){

        /**
         * Text Area from Java FX generation and location specifications
         */
        clientarea = new TextArea();
        clientarea.setTranslateX(0);
        clientarea.setTranslateY(0);

        /**
         * Labels from Java FX generation and location specifications
         */
        lblval = new Label("Enter the value");
        lblval.setTranslateX(0);
        lblval.setTranslateY(-50);

        lblwght = new Label("Enter the weight");
        lblwght.setTranslateX(0);
        lblwght.setTranslateY(-25);

        lbltax = new Label("Enter the tax");
        lbltax.setTranslateX(0);
        lbltax.setTranslateY(5);

        /**
         * Entries from Java FX generation, location, and dimension specifications
         */
        value_entry = new TextField();
        value_entry.setTranslateX(100);
        value_entry.setTranslateY(20);
        value_entry.setMaxHeight(20);
        value_entry.setMaxWidth(70);

        weight_entry = new TextField();
        weight_entry.setTranslateX(100);
        weight_entry.setTranslateY(40);
        weight_entry.setMaxHeight(20);
        weight_entry.setMaxWidth(70);

        tax_entry = new TextField();
        tax_entry.setTranslateX(100);
        tax_entry.setTranslateY(60);
        tax_entry.setMaxHeight(20);
        tax_entry.setMaxWidth(70);

        /**
         * Button from Java FX generation and location specifications
         */
        btn = new Button("Send: ");
        btn.setTranslateX(220);
        btn.setTranslateY(-50);

        /**
         * Button´s action. Opens a socket to send,  as a class, the details written in the entries
         */
        btn.setOnAction(event -> {
