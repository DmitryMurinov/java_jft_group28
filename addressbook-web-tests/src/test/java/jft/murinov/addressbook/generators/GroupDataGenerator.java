package jft.murinov.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
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

    @Parameter(names = "-path", description = "file name and path relayted to module folder")
    public String filename;

    @Parameter(names = "-f", description = "file format, csv or xml")
    public String format;

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
        if(format.equals("csv")){
            filename += "csv";
        saveAsCSV(groups, new File(filename));
        }else
        if(format.equals("xml")){
            filename += "xml";
            saveAsXML(groups, new File(filename));
            return;
        }else {
            System.out.println("Unrecognized format, choose csv or xml please " + format);
        }
    }

    private void saveAsXML(List<GroupData> groups, File file) throws IOException {

        System.out.println(file.getAbsolutePath());
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        String xml = xStream.toXML(groups);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsCSV(List<GroupData> groups, File file) throws IOException {
        System.out.println(file.getAbsolutePath());

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
