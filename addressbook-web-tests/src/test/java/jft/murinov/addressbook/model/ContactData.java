package jft.murinov.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickname;
    private final String firstAddress;
    private final String homePhoneString;
    private final String mobilePhoneString;
    private final String firstEmail;

    public ContactData(String firstName, String middleName, String lastName, String nickname, String firstAddress, String homePhoneString, String mobilePhoneString, String firstEmail) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.firstAddress = firstAddress;
        this.homePhoneString = homePhoneString;
        this.mobilePhoneString = mobilePhoneString;
        this.firstEmail = firstEmail;
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

    public String getNickname() {
        return nickname;
    }

    public String getFirstAddress() {
        return firstAddress;
    }

    public String getHomePhoneString() {
        return homePhoneString;
    }

    public String getMobilePhoneString() {
        return mobilePhoneString;
    }

    public String getFirstEmail() {
        return firstEmail;
    }
}
