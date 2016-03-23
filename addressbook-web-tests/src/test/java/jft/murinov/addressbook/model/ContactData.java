package jft.murinov.addressbook.model;

public class ContactData {
    private int id = 0;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nickname;
    private String firstAddress;
    private String homePhoneString;
    private String mobilePhoneString;
    private String workPhoneString;
    private String firstEmail;
    private String secondEmail;
    private String thirdEmail;
    private String group;
    private String allPhones;
    private String allEmails;

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withFirstAddress(String firstAddress) {
        this.firstAddress = firstAddress;
        return this;
    }

    public ContactData withHomePhone(String homePhoneString) {
        this.homePhoneString = homePhoneString;
        return this;
    }

    public ContactData withMobilePhoneString(String mobilePhoneString) {
        this.mobilePhoneString = mobilePhoneString;
        return this;
    }

    public ContactData withWorkPhoneString(String workPhoneString) {
        this.workPhoneString = workPhoneString;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withFirstEmail(String firstEmail) {
        this.firstEmail = firstEmail;
        return this;
    }

    public ContactData withSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
        return this;
    }

    public ContactData withThirdEmail(String thirdEmail) {
        this.thirdEmail = thirdEmail;
        return this;
    }

    public ContactData withAllEmail(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }



    public String getNickname() {
        return nickname;
    }

    public String getFirstAddress() {
        return firstAddress;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public String getThirdEmail() {
        return thirdEmail;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getHomePhone() {
        return homePhoneString;
    }

    public String getMobilePhone() {
        return mobilePhoneString;
    }

    public String getWorkPhone() {
        return workPhoneString;
    }

    public String getFirstEmail() {
        return firstEmail;
    }

    public String getGroup() {
        return group;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getHomePhoneString() {
        return homePhoneString;
    }

    public String getMobilePhoneString() {
        return mobilePhoneString;
    }

    public String getWorkPhoneString() {
        return workPhoneString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
