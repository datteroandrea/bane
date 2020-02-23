package bane.shell.dispatcher;

import bane.shell.Shell;

public class RatDispatcher extends Dispatcher {

    public RatDispatcher(Shell shell) {
        super(shell);
        super.dispatchMessage = shell.username+"\\rat> ";
    }

    @Override
    public void execute(String data) {
        if(data.equals("exit")){
            super.exit();
        } else if (data.equals("help")){
            this.help();
        } else if(data.equals("shell")){
            // setting the dispatcher as the shell dispatcher
            this.setDispatcher(new ShellDispatcher(this.shell));
        }
    }

    @Override
    public void help() {
        System.out.println(
                "set <address> <port>\tSets the address and port of the server to connect to\n"+
                "build [apk/exe]\tBuilds the bane application\n"+
                "inject [apk/exe] path\tInjects bane inside an application");
    }

}
