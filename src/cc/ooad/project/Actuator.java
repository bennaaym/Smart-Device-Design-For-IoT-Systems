package cc.ooad.project;

import cc.ooad.project.helper.Pair;

import java.util.Random;

public class Actuator implements IActuator
{
    //Singleton
    private static Actuator instance;

    public static Actuator getInstance()
    {
        if(instance == null)
        {
            synchronized (Login.class)
            {
                if(instance == null)
                {
                    instance = new Actuator();
                }
            }
        }
        return instance;
    }
    //

    private  ActuatorState state = ActuatorState.off;

    private Actuator(){}

    @Override
    public Pair<Boolean, String> turnOnCooler() {
        boolean  success = true;
        String error = null;

        // randomize
        final int bound = 1000;
        final double successPercentage = 0.99;
        final Random rand = new Random();
        final int randRes = rand.nextInt(bound);

        if(randRes < bound * successPercentage) //actuator works 99% of time without  issues
        {
            if(state == ActuatorState.on)
            {
                error = "The cooler is already on";
            }
        }
        else
        {
            success = false;
            error = "Actuator not able to turn on the cooler";
        }

        this.state = ActuatorState.on;
        return (new Pair<>(success, error));
    }

    @Override
    public Pair<Boolean, String> turnOffCooler() {

        boolean success = true;
        String error = null;

        // randomize
        final int bound = 1000;
        final double successPercentage = 0.99;
        final Random rand = new Random();
        final int randRes = rand.nextInt(bound);

        if(randRes < bound * successPercentage) //actuator works 99% of time without  issues
        {
            if (state == ActuatorState.off)
                error = "The cooler is already off";

        }
        else
        {
            success=false;
            error = "Actuator not able to turn off the cooler";
        }


        this.state = ActuatorState.off;
        return (new Pair<>(success, error));
    }
}
