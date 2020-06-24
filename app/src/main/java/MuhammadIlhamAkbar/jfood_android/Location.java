package MuhammadIlhamAkbar.jfood_android;
/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This Location Class used to precessing location data
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class Location {
    private String province;
    private String description;
    private String city;

    /**
     * This is constructor for object of class Invoice
     * <p>
     * Update or changes city, province, description
     * @param city - first parameter value of constructor, which becomes city name
     * @param province - second parameter value of constructor, which becomes province name
     * @param description - third parameter value of constructor, description of location in String
     */
    public Location(String city,String province, String description)
    {
        this.city=city;
        this.province=province;
        this.description=description;
    }

    /**
     * This is getCity method that is used to return city value
     * @return city, return city location
     */
    public String getCity()
    {
        return city;
    }

    /**
     * This is getProvince method that is used to return id value
     * @return province, return province location
     */
    public String getProvince()
    {
        return province;
    }

    /**
     * This is getDescription method that is used to return description value
     * @return description, return location description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * This is setCity method that is used to set city value, that city is instance variable
     * @param city, set city location in stirng
     */
    public void setCity(String city)
    {
        this.city=city;
    }

    /**
     * This is setDescriptioe method that is used to set description value, that province is instance variable
     * @param description, set province location in string
     */
    public void setDescription(String description)
    {
        this.description=description;
    }

    /**
     * This is setProvince method that is used to set province value, that province is instance variable
     * @param province, set province location in string
     */
    public void setProvince(String province)
    {
        this.province=province;
    }
}
