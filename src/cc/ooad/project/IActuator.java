package cc.ooad.project;

import cc.ooad.project.helper.Pair;

public interface IActuator
{
    Pair<Boolean,String> turnOnCooler();
    Pair<Boolean,String> turnOffCooler();
}
