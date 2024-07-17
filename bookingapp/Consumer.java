public class Consumer  {
    private String username;
    private String password;
    private PersonData personData1;
    private PersonData personData2;
    
    public Consumer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonData getPersonData1() {
        return personData1;
    }

    public void setPersonData1(PersonData personData) {
        this.personData1 = personData;
    }   
    
    public PersonData getPersonData2() {
        return personData2;
    }

    public void setPersonData2(PersonData personData) {
        this.personData2 = personData;
    }
 
}
