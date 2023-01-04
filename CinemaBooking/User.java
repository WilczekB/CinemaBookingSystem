class User{

    private String name;
    private String surname;
    private String email;

    //Konstruktor
    public User(String n, String s, String e)
    {
        name = n;
        surname = s;
        email = e;
    }

    //Settery i gettery
    public String getName()
    {
        return name;
    }

    public void setName(String n)
    {
        name = n;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String s)
    {
        surname = s;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String e)
    {
        email = e;
    }


}