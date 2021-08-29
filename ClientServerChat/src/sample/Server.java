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
