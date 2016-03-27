package jft.murinov.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("Contact")
public class ContactData {

    @XStreamOmitField
    private int id = 0;
    @Expose
    private String firstName;
    @Expose
    private String middleName;
    @Expose
    private String lastName;
    @Expose
    private String nickname;
    @Expose
    private String title;
    @Expose
    private String company;
    @Expose
    private String firstAddress;
    @Expose
    private String homePhoneString;
    @Expose
    private String mobilePhoneString;
    @Expose
    private String workPhoneString;
    @Expose
    private String fax;
    @Expose
    private String firstEmail;
    @Expose
    private String secondEmail;
    @Expose
    private String thirdEmail;
    @Expose
    private String homepage;
    @Expose
    private String group;
    @Expose
    private String allPhones;
    @Expose
    private String allEmails;
    @Expose
    private String address2;
    @Expose
    private String phone2;
    @Expose
    private String notes;

    @XStreamOmitField
    private File photo;

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

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

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
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

    public ContactData withMobilePhone(String mobilePhoneString) {
        this.mobilePhoneString = mobilePhoneString;
        return this;
    }

    public ContactData withWorkPhone(String workPhoneString) {
        this.workPhoneString = workPhoneString;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withFax(String fax){
        this.fax = fax;
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

    public ContactData withHomepage(String homepage) {
        this.homepage = homepage;
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

    public ContactData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public ContactData withPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getFax() {
        return fax;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getNotes() {
        return notes;
    }

    public ContactData withNotes(String notes) {
        this.notes = notes;

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

    public File getPhoto() {
        return photo;
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
