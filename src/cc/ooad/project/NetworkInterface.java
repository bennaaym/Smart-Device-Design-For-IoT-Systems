package cc.ooad.project;

import cc.ooad.project.helper.Pair;
import cc.ooad.project.patterns.builder.IUser;
import cc.ooad.project.patterns.builder.User;
import cc.ooad.project.patterns.observer.ISubject;
import cc.ooad.project.patterns.observer.UnitState;

import java.util.Scanner;

public class NetworkInterface implements INetworkInterface
{
    //Singleton
    private static NetworkInterface instance;

    public static NetworkInterface getInstance()
    {
        if(instance == null)
        {
            synchronized (Login.class)
            {
                if(instance == null)
                {
                    instance = new NetworkInterface();
                }
            }
        }
        return instance;
    }
    //

    private final IMainProcessingPlatform mpp;
    private final ISubject publisher;

    private NetworkInterface()
    {
        this.mpp = MainProcessingPlatform.getInstance();
        this.publisher = new UnitState();
    }


    @Override
    public void displayTemperature() {

        Pair<Double,String> respond = this.mpp.sendRequestToTemperatureSensor();

        if(respond.second != null)
            this.publisher.notify(respond.second);

        if(respond.first != null)
            this.displayMessage("Temperature : "+respond.first+" °C");

    }

    @Override
    public void turnOnCooler(Operation operation) {
        Pair<Boolean,String> respond = this.mpp.sendRequestToActuator(Operation.turnOnCooler);

        if(respond.second != null)
        {
            if(respond.first)
                this.displayMessage(respond.second);
            else
                this.publisher.notify(respond.second);

            return;
        }

        this.displayMessage("The cooler has been successfully turned on");
    }

    @Override
    public void turnOffCooler(Operation operation) {
        Pair<Boolean,String> respond = this.mpp.sendRequestToActuator(Operation.turnOffCooler);

        if(respond.second != null)
        {
            if(respond.first)
                this.displayMessage(respond.second);
            else
                this.publisher.notify(respond.second);

            return;
        }

        this.displayMessage("The cooler has been successfully turned off");
    }

    @Override
    public Operation menu() {

        System.out.println("----------------------------------------------------------------------");
        System.out.println("                                MENU                                 ");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("    (1) Display Temperature");
        System.out.println("    (2) Turn On Cooler");
        System.out.println("    (3) Turn Off Cooler");
        System.out.println("    (4) Exit           ");

        int option;
        Scanner sc = new Scanner(System.in);

        do
        {
            option = sc.nextInt();
            if(option <1 || option >4)
                this.displayMessage("Please choose a valid option (1-4)");

        }while(option < 1 || option >4);

        switch (option)
        {
            case 1:
                return Operation.displayTemperature;
            case 2:
                return Operation.turnOnCooler;
            case 3:
                return Operation.turnOffCooler;
            case 4:
                System.exit(0);
        }

        return null;
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }



    @Override
    public boolean loginScreen() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("                                LOGIN                                 ");
        System.out.println("----------------------------------------------------------------------");

        ILogin logger = Login.getInstance(new PostgreSQL());
        String username = logger.readUsername();
        String password = logger.readPassword();
        boolean res = logger.login(username,password);
        if(res)
        {
            IUser user = new User.UserBuilder(username,password)
                             .build();
            this.publisher.attach(user);
        }

        return res;
    }
}
