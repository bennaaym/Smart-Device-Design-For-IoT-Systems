package cc.ooad.project;

import cc.ooad.project.helper.Pair;

public interface ITemperatureSensor
{
    Pair<Double,String> readTemperature();
}
