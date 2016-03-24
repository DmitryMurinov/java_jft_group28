package jft.murinov.addressbook.generators;

import jft.murinov.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by d.murinov on 24.03.2016.
 */
public class GroupDataGenerator {

    public static void main(String[] args) throws IOException {
        int totalGroups = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<GroupData> groups = generateGroups(totalGroups);
        save(groups, file);
    }

    private static void save(List<GroupData> groups, File file) throws IOException {

        Writer writer = new FileWriter(file);

        for (GroupData group : groups){
            writer.write(String.format("%s;%s;%s\n", group.getGroupName(), group.getGroupHeader(), group.getGroupFooter()));
        }
        writer.close();
    }

    private static List<GroupData> generateGroups(int totalGroups) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for(int i = 0; i < totalGroups; i++){
            groups.add(new GroupData().withName(String.format("test group %s", i))
                    .withHeader(String.format("test header %s", i))
                    .withFooter(String.format("test footer %s", i)));
        }
        return groups;
    }
}
