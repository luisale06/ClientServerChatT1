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
