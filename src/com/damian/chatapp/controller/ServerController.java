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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {
    public JFXTextArea serverTextArea;
    public JFXTextField serverField;
    public JFXButton serverSend;

    public ServerSocket ss;
    public Socket socket;
    public DataInputStream dis;
    public DataOutputStream dos;
    public String clientMsg;


    public Label l1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new LightSpeedIn(serverField).play();
        new LightSpeedIn(serverTextArea).play();
        new LightSpeedIn(serverSend).play();
        new LightSpeedIn(l1).play();

        new Thread(() -> {
            try {
                ss = new ServerSocket(3000);
                socket = ss.accept();
                serverTextArea.appendText("Server started at port : 3000 🚀.\n");
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF("Client is now connected to the server from port 3000 : 🚀.\n");
                clientMsg = "";
                while (!clientMsg.equals("exit")) {
                    clientMsg = dis.readUTF();
                    serverTextArea.appendText("\nClient : " + clientMsg);

                }
                serverTextArea.appendText("\nClient has left the chat!\n");


            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }).start();


    }

    public void serverOnAction(ActionEvent actionEvent) {
        try {
            dos.writeUTF(serverField.getText());
            dos.flush();
            serverField.clear();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Error while sending the message from the server : " + e.getLocalizedMessage()).show();
        }


    }
}

