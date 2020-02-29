# About

A RAT terminal creator/handler

# How it works

Bane consists of 3 main components:

- Bane the initial terminal interface
- RAT the shell interface which enables you to create, customize RAT
- Shell the rat interface which enables you to control remotely victims

# Requirements

- Java version 8
- Linux/Mac OS it is also fully working for Windows but the terminal interface is not perfectly compatible yet.

# Setup

If you are on a Linux-like system you can use install.sh to add bane as the command otherwise you can use Bane anyway by typing

`java -jar bane.jar`

# Usage

Initially you will be welcomed by the Bane interface there you can choose to use the RAT Interface or the Shell Interface.

`help` to show the commands of the current interface

#### Bane Interface

`rat` to go to the RAT Interface<br>
`shell` to go to the Shell Interface

#### Shell Interface


`start (port)` Creates the server at the specified port (optional)<br>
`show` Shows the devices connected to the bane service<br>
`sysinfo` Get system info about the device<br>
`select <name>` Selects the device<br>
`track` Tracks the current position of the device<br>
`sopen` Starts a remote shell<br>
`sclose` Closes the remote shell<br>
`news` Returns the notifications<br>
`keylog` Keylogs the device (In development)<br>
`photo` Take photo (In development)<br>
`record -d <time>` Record audio (In development)<br>
`video -d <time>` Record video (In development)<br>
`upload <path>` Uploads a file (Testing...)<br>
`download <path>` Downloads a file (Testing...)<br>
`video -d <time>` Record video (In development)<br>
`name <new name>` Changes the name of the current device<br>
`sendsms number \"text\"` Sends a message to a number<br>
`dump [sms/contacts/calls]` Get all sms/contacts/calls

#### RAT Interface

`set <address> <port>` Sets the address and port of the server<br>
`build [apk/exe]` Builds the bane application<br>
`inject [apk/exe] path` Injects bane inside an application

# Backdoor Supported Platforms

- [Android](https://github.com/datteroandrea/banedroid) (currently under development but still open for Alpha tests) 
- Windows (not quite yet)
- Linux/Max OS (not quite yet)

# TODO

- [ ] RAT Interface
    - [ ] set <address> <port>
    - [ ] build [apk/exe]
    - [ ] inject [apk/...] path
- [ ] Shell Interface
    - [ ] upload
    - [ ] download
