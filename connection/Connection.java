package bane.connection;

import bane.connection.device.Device;
import com.bane.model.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// TODO: add Callback to send method
public class Connection extends Thread {

    public final String address;
    public final int port;
    public final ArrayList<Device> devices;
    private Device currentDevice;

    public Connection(String address, int port) {
        this.address = address;
        this.port = port;
        this.devices = new ArrayList<>();
    }

    // listen for connections
    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(this.port);
            while(true){
                Socket socket = server.accept();
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                String name = (String) ((BaneObject) input.readObject()).values[0].value;
                devices.add(new Device(name,socket,input,output));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BaneObject send(BaneObject bon) throws Exception {
        this.currentDevice.output.writeObject(bon);
        this.currentDevice.output.flush();
        return (BaneObject) this.currentDevice.input.readObject();
    }

    public void setCurrentDevice(String name){
        for (int i = 0; i < devices.size(); i++) {
            if(devices.get(i).name.equals(name)){
                this.currentDevice = this.devices.get(i);
            }
        }
    }

    public Device getCurrentDevice(){
        return this.currentDevice;
    }

    public void close() throws Exception {
        for (int i = 0; i < devices.size(); i++) {
            devices.get(i).input.close();
            devices.get(i).output.close();
            devices.get(i).socket.close();
        }
    }

}