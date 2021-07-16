package cc.ooad.project;

import cc.ooad.project.helper.Pair;

public interface IMainProcessingPlatform
{
    Pair<Double,String> sendRequestToTemperatureSensor();
    Pair<Boolean,String> sendRequestToActuator(Operation operation);
}
