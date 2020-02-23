package bane;

import bane.shell.Shell;

public class Main {

    public static void main(String[] args) {
        System.out.println("\033[1;31m"+"     ____\n" +
                "    / __ )____ _____  ___ \n" +
                "   / __  / __ `/ __ \\/ _ \\\n" +
                "  / /_/ / /_/ / / / /  __/\n" +
                " /_____/\\__,_/_/ /_/\\___/\n" +
                "\n" + "\033[0m" +
                "GitHub: https://github.com/datteroandrea");
	    Shell s = new Shell("stecco");
	    s.start();
    }
}
