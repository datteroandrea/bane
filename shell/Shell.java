package bane.shell;

import bane.shell.dispatcher.DefaultDispatcher;
import bane.shell.dispatcher.Dispatcher;
import bane.shell.dispatcher.ShellDispatcher;
import javafx.scene.paint.Color;

import java.util.Scanner;

public class Shell extends Thread {

    public final String username;
    private boolean listen = true;
    private Dispatcher dispatcher;

    public Shell(String username){
        this.username = username;
        this.dispatcher = new DefaultDispatcher(this);
    }

    @Override
    public void run() {
        System.out.print(this.dispatcher.getDispatchMessage());
        Scanner input = new Scanner(System.in);
        while(this.listen){
            // reads command
            String command = input.nextLine();
            // executes command
            this.dispatcher.execute(command);
            if(!command.equals("exit")){
                System.out.print(this.dispatcher.getDispatchMessage());
            } else if (dispatcher.getClass() == ShellDispatcher.class){
                ShellDispatcher s = ((ShellDispatcher) dispatcher);
                try {
                    s.getConnection().close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Goodbye!");
                System.exit(0);
            }
        }
    }

    public void setListen(boolean state) {
        this.listen = state;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void print_ok(String output){
        System.out.print("\u001B[32m");
        System.out.println("[Y] "+output+" [Y]");
        System.out.print("\033[0m");
    }

    public void print_nok(String output){
        System.out.print("\033[0;31m");
        System.out.println("[X] "+output+" [X]");
        System.out.print("\033[0m");
    }

    public void print_info(String output){
        System.out.print("\033[0;34m");
        System.out.println("[*] "+output+" [*]");
        System.out.print("\033[0m");
    }

    public void print_warning(String output){
        System.out.print("\033[0;33m");
        System.out.println("[!] "+output+" [!]");
        System.out.print("\033[0m");
    }

}
