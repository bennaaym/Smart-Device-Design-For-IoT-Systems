package cc.ooad.project;

import cc.ooad.project.helper.Tools;


public class SmartDevice implements  ISmartDevice
{
    private final INetworkInterface networkInterface;


    SmartDevice()
    {
        this.networkInterface = NetworkInterface.getInstance();
    }


    @Override
    public void run() {
        /* running the device */
        final int delay = 1000;
        this.networkInterface.displayMessage("starting...");
        Tools.delay(delay);
        Tools.flashScreen();

        /* login Screen */
        boolean isLogged;
        do
        {
            Tools.flashScreen();
            isLogged = this.networkInterface.loginScreen();
            Tools.delay(delay);

        }while(!isLogged);


        /* menu Screen */
        while (true) {
            Tools.flashScreen();
            Operation option = this.networkInterface.menu();
            switch (option) {
                case displayTemperature -> this.networkInterface.displayTemperature();
                case turnOnCooler -> this.networkInterface.turnOnCooler(Operation.turnOnCooler);
                case turnOffCooler -> this.networkInterface.turnOffCooler(Operation.turnOffCooler);
            }
            Tools.PressKey("Press any key to return to the menu");
        }
    }
}
