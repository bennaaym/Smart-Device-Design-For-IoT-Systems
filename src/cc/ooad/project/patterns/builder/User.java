package cc.ooad.project.patterns.builder;


public class User implements IUser
{
    private final String username;
    private final String password;

    public User(UserBuilder builder)
    {
        this.username = builder.username;
        this.password = builder.password;
    }


    @Override
    public void update(String update) {
        System.out.println("(!) Notification : "+update);
    }


    public static class UserBuilder
    {
        private String username;
        private String password;

        public UserBuilder(String username,String password)
        {
            this.username = username;
            this.password = password;
        }



        public User build()
        {
            return new User(this);
        }
    }
}
