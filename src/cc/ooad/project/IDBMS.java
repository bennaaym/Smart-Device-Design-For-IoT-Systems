package cc.ooad.project;
import java.sql.Connection;

public interface IDBMS
{
    Connection connect();
    boolean login(String username,String password);
}
