package MuhammadIlhamAkbar.jfood_android;

public class Seller {
    private int id; // seller id
    private String name; //seller name
    private String email; //seller email
    private String phoneNumber; //seller phoneNumber
    private Location location; //seller location

    /**
     * This is constructor for object of class Seller
     * <p>
     * Update or changes id, name, email, phoneNumber, location
     * @param id - first parameter value of constructor, which becomes id seller
     * @param name - second parameter value of constructor, which becomes seller name
     * @param email - third parameter value of constructor, this parameter to set seller email
     * @param phoneNumber -fourth parameter value of constructor, this parameter to set seller phone number
     * @param location - fifth parameter value of constructor, this parameter to set seller location
     */
    public Seller(int id, String name, String email,String phoneNumber, Location location)
    {
        this.id = id;
        this.name = name;
        this.phoneNumber=phoneNumber;
        this.email = email;
        this.location = location;
    }

    /**
     * This is getId method that used to return id value
     * @return id, return id of seller
     */
    public int getId()
    {
        // put your code here
        return id;
    }

    /**
     * This is getName method that used to return name value
     * @return name, return seller's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * This is getEmail method that used to return email value
     * @return email, return email of seller
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * This is getPhoneNumber method that used to return phoneNumber value
     * @return phoneNumber, return value of seller phone number
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * This is getLocation method that used to return location value
     * @return location, return location of seller
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * This is setId method that used to set id value, that id is instance variable
     * @param Id, set value of seller id
     */
    public void setId(int Id)
    {
        this.id = Id;
    }

    /**
     * This is setName method that used to set name value, that name is instance variable
     * @param name, set seller's name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * This is setDate method that used to set email value, that email is instance variable
     * @param email, set name of email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * This is setPhoneNumber method that used to set phoneNumber value, that phoneNumber is instance variable
     * @param phoneNumber, set seller's phone number
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This is setLocation method that used to set location value, that location is instance variable
     * @param location, set seller location
     */
    public void setLocation (Location location)
    {
        this.location = location;
    }
}
