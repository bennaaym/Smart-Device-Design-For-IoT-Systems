package cc.ooad.project;

import cc.ooad.project.helper.Pair;

public class MainProcessingPlatform implements  IMainProcessingPlatform
{
    private  static MainProcessingPlatform instance;

    public static MainProcessingPlatform getInstance()
    {
        if(instance == null)
        {
            synchronized (Login.class)
            {
                if(instance == null)
                {
                    instance = new MainProcessingPlatform();
                }
            }
        }
        return instance;
    }


    private final ITemperatureSensor temperatureSensor;
    private final  IActuator actuator;

    private MainProcessingPlatform(){
        this.temperatureSensor = TemperatureSensor.getInstance();
        this.actuator = Actuator.getInstance();
    }

    @Override
    public Pair<Double, String> sendRequestToTemperatureSensor() {
        return this.temperatureSensor.readTemperature();
    }

    @Override
    public Pair<Boolean, String> sendRequestToActuator(Operation operation) {

        return switch (operation) {
            case turnOnCooler -> this.actuator.turnOnCooler();
            case turnOffCooler -> this.actuator.turnOffCooler();
            default -> null;
        };

    }
}
