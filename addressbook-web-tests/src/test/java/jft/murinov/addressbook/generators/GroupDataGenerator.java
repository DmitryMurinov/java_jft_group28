package jft.murinov.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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

    @Parameter(names = "-c", description = "Groups count")
    public int totalGroups;

    @Parameter(names = "-f", description = "file name and path relayted to module folder")
    public String filename;

    public static void main(String[] args) throws IOException {

        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }catch (ParameterException pe){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(totalGroups);
        save(groups, new File(filename));
    }

    private void save(List<GroupData> groups, File file) throws IOException {

        Writer writer = new FileWriter(file);

        for (GroupData group : groups){
            writer.write(String.format("%s;%s;%s\n", group.getGroupName(), group.getGroupHeader(), group.getGroupFooter()));
        }
        writer.close();
    }

    private List<GroupData> generateGroups(int totalGroups) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for(int i = 0; i < totalGroups; i++){
            groups.add(new GroupData().withName(String.format("test group %s", i))
                    .withHeader(String.format("test header %s", i))
                    .withFooter(String.format("test footer %s", i)));
        }
        return groups;
    }
}
