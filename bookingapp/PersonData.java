

import java.time.LocalDateTime;

public class PersonData  {
    private String name;
    private String email;
    private String phone;
    private LocalDateTime date;
    private int peopleCount;
    private String specialRequests;
    private Dish dish;

    public PersonData(String name, String email, String phone, LocalDateTime date, int peopleCount,
            String specialRequests,
            Dish dish) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.peopleCount = peopleCount;
        this.specialRequests = specialRequests;
        this.dish = dish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PersonData:\nName: " + name + ",\nEmail: " + email + ",\nPhone: " + phone + ",\nDate: " + date
                + ",\nPeopleCount: " + peopleCount + (specialRequests != null || specialRequests.isEmpty() ? (",\nSpecialRequests: " + specialRequests) : " ") + ",\nDish: " + dish.getName();
    }
}
