package bane.shell.dispatcher;

import bane.shell.Shell;

public abstract class Dispatcher {

    public final Shell shell;
    protected String dispatchMessage;

    public Dispatcher(Shell shell){
        this.shell = shell;
        this.dispatchMessage = shell.username+"> ";
    }

    public abstract void help();

    public abstract void execute(String data);

    public void setDispatcher(Dispatcher dispatcher) {
        this.shell.setDispatcher(dispatcher);
    }

    public void exit() {
        this.shell.setListen(false);
    }

    public String getDispatchMessage(){
        return this.dispatchMessage;
    }

}
