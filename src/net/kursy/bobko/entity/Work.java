package net.kursy.bobko.entity;


public class Work extends Entity {

    private User user;
    private TypeOfWorks typeOfWorks;
    private ScopeOfWorks scopeOfWorks;
    private String date;
    private String address;
    private String status;

    public Work() {
    }

    public Work(User user, TypeOfWorks typeOfWorks, ScopeOfWorks scopeOfWorks,
                String date, String address, String status) {
        this.user = user;
        this.typeOfWorks = typeOfWorks;
        this.scopeOfWorks = scopeOfWorks;
        this.date = date;
        this.address = address;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TypeOfWorks getTypeOfWorks() {
        return typeOfWorks;
    }

    public void setTypeOfWorks(TypeOfWorks typeOfWorks) {
        this.typeOfWorks = typeOfWorks;
    }

    public ScopeOfWorks getScopeOfWorks() {
        return scopeOfWorks;
    }

    public void setScopeOfWorks(ScopeOfWorks scopeOfWorks) {
        this.scopeOfWorks = scopeOfWorks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Customer: " + user.getFirstName() + " " + user.getSecondName() + ". "
                + " Type of works: " + typeOfWorks.getType() + ". Scope of works: " + scopeOfWorks.getScope()
                + ". Date: " + date + "Status :" + status + "\n";
    }
}
