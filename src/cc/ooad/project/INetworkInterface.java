package cc.ooad.project;

public interface INetworkInterface
{
    void displayTemperature();
    void turnOnCooler(Operation operation);
    void turnOffCooler(Operation operation);
    Operation menu();
    void displayMessage(String message);
    boolean loginScreen();
}
