package com.damian.chatapp.controller;

import animatefx.animation.LightSpeedIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public JFXTextArea clientTextArea;
    public JFXTextField clientField;
    public JFXButton clientSend;
    public Label l1;
    public Socket socket;
    public DataOutputStream dos;
    public DataInputStream dis;
    public String clientMsg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new LightSpeedIn(clientTextArea).play();
        new LightSpeedIn(clientField).play();
        new LightSpeedIn(clientSend).play();
        new LightSpeedIn(l1).play();
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 3000);
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                clientMsg = "";
                while (!clientMsg.equals("exit")) {
                    clientMsg = clientField.getText();
                    clientTextArea.appendText("\nServer : " + dis.readUTF());

                }
                clientTextArea.appendText("\nServer has left the chat : 🛎️.\n");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();

    }


    public void clientOnAction(ActionEvent actionEvent) {
        clientTextArea.appendText("ME : "+clientField.getText() + "\n");
        try {

            dos.writeUTF(clientField.getText());
            dos.flush();
            clientField.clear();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Error while sending the message from client : " + e.getLocalizedMessage()).show();
        }


    }


}
