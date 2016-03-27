package jft.murinov.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("Group")
public class GroupData {

    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Expose
    private String groupName;
    @Expose
    private String groupHeader;
    @Expose
    private String groupFooter;

    public GroupData withId(int id) {
        this.id = id;
        return this;
    }

    public GroupData withFooter(String groupFooter) {
        this.groupFooter = groupFooter;
        return this;
    }

    public GroupData withName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public GroupData withHeader(String groupHeader) {
        this.groupHeader = groupHeader;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupHeader() {
        return groupHeader;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "groupName='" + groupName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getGroupFooter() {
        return groupFooter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (id != groupData.id) return false;
        return groupName != null ? groupName.equals(groupData.groupName) : groupData.groupName == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }
}
