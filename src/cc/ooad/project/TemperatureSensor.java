package cc.ooad.project;

import cc.ooad.project.helper.Pair;

import java.util.Random;

public class TemperatureSensor implements ITemperatureSensor {

    //Singleton
    private static TemperatureSensor instance;
    public static TemperatureSensor getInstance()
    {
        if(instance == null)
        {
            synchronized (Login.class)
            {
                if(instance == null)
                {
                    instance = new TemperatureSensor();
                }
            }
        }
        return instance;
    }
    //


    private TemperatureSensor(){}

    @Override
    public Pair<Double,String> readTemperature() {
        Double temperature = null;
        String error = null;

        //randomize
        final int bound = 10000;
        final double successPercentage = 0.99;
        Random rand = new Random();
        final int randRes = rand.nextInt(bound);

        if(randRes < bound * successPercentage) // 99% of time sensor works without any issues
        {
            temperature = Math.floor(rand.nextDouble() * 35) + 10;
            if(temperature>=40)
                error = "It's really hot in here, turn on the cooler to cool it down";
        }
        else
            error = "Temperature sensor not able to read the temperature";

        return (new Pair<>(temperature, error));
    }
}
