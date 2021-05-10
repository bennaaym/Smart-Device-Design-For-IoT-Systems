package cc.ooad.project;

public interface ILogin
{
    String readUsername();
    String readPassword();
    boolean login(String username,String password);
}
