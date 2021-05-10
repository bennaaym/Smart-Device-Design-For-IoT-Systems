package cc.ooad.project;

import java.util.Scanner;

public class Login implements ILogin
{
    private static Login instance;

    public static Login getInstance(IDBMS db)
    {
        if(instance == null)
        {
            synchronized (Login.class)
            {
                if(instance == null)
                {
                    instance = new Login(db);
                }
            }
        }
        return instance;
    }

    private final  IDBMS db;

    private Login(IDBMS db)
    {
        this.db = db;
    }

    @Override
    public String readUsername() {
        System.out.print("username : ");
        return ((new Scanner(System.in)).nextLine()).trim();
    }

    @Override
    public String readPassword() {
        System.out.print("password : ");
        return ((new Scanner(System.in)).nextLine()).trim();
    }

    @Override
    public boolean login(String username,String password)
    {
       return db.login(username,password);
    }
}
