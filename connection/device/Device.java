package bane.connection.device;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Device {

    public final String name;
    public final Socket socket;
    public final ObjectInputStream input;
    public final ObjectOutputStream output;

    public Device(String name, Socket socket) throws Exception {
        this.name = name;
        this.socket = socket;
        this.input = new ObjectInputStream(socket.getInputStream());
        this.output = new ObjectOutputStream(this.socket.getOutputStream());
    }

    public Device(String name, Socket socket, ObjectInputStream input, ObjectOutputStream output) throws Exception {
        this.name = name;
        this.socket = socket;
        this.input = input;
        this.output = output;
    }
}
