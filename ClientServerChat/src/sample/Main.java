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
            try {
                snsocket = new Socket("192.168.100.108", 4999);

                /**
                 * Creates instances of the class Prod_details and sets the
                 * value, weight and tax from the entries
                 */
                Prod_details details = new Prod_details();
                details.setValue(value_entry.getText());
                details.setWeight(weight_entry.getText());
                details.setTax(tax_entry.getText());

                /**
                 * The message sent by the client is an object
                 * "exitflow" is the stream that receives it
                 * and then the socket is closed
                 */
                ObjectOutputStream exitflow = new ObjectOutputStream(snsocket.getOutputStream());
                exitflow.writeObject(details);

                snsocket.close();

            } catch (IOException e) {
                /**
                 * Exception catch, send a message with the error
                 */
                System.out.println(e.getMessage()); //Prints the exception
            }
        });

        /**
         * Vbox from Java FX generation and get Children method
         */
        box = new VBox();
        box.getChildren().addAll(clientarea, value_entry, weight_entry, tax_entry, lblval, lblwght, lbltax, btn);

        /**
         * Scene from Java FX generation
         */
        scn = new Scene(box);

        /**
         * Thread initialization and generation
         */
        prepc = new Thread(this);
        prepc.start();

        /**
         * Scenario location and dimensions specifications.
         * Show method for the application to be seen
         */
        chat.setTitle("Product Chat");
        chat.setHeight(400);
        chat.setWidth(500);
        chat.setScene(scn);
        chat.show();
    }

    /**
     * Run method, started by the prep Thread.
     * Generates the server socket that is always looking for messages from the client
     */
    @Override
    public void run(){

        try{
            resocket = new ServerSocket(5000);

            /**
             * While loop that keeps the socket looking for messages
             */
            while(true){
                sckt = resocket.accept();

                /**
                 * The message sent by the client is a string
                 * "entryflow" is the stream that receives it and "res"
                 * read the object that is received
                 */
                DataInputStream entryflow = new DataInputStream(sckt.getInputStream());
                String res = entryflow.readUTF();

                /**
                 * Appends the text with the details in the server
                 * text area and closes the socket
                 */
                clientarea.appendText("\n The total price of the product is: " + res);

                sckt.close();

            }


        }catch(Exception e){
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


/**
 * Class that save the details from the product
 * introduced by the client
 */
class Prod_details implements Serializable {
    /**
     * Variables that are going to be saved
     */
    private String value, weight, tax;

    /**
     * get and set value method
     */
    public String getValue(){
        return value;
    }
    public void setValue(String val){
        this.value = val;
    }

    /**
     * get and set weight method
     */
    public String getWeight(){
        return weight;
    }
    public void setWeight(String wei){
        this.weight = wei;
    }

    /**
     * get and set tax method
     */
    public String getTax(){
        return tax;
    }
    public void setTax(String tx){
        this.tax = tx;
    }
}
