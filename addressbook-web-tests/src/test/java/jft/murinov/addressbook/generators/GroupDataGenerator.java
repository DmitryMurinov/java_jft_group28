package jft.murinov.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    @Parameter(names = "-path", description = "file name and path relayed to module folder")
    public String filename;

    @Parameter(names = "-f", description = "file format, csv, xml or json")
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
        }else
        if(format.equals("json")){
            filename += "json";
            saveAsJSON(groups, new File(filename));
            return;
        }else {
            System.out.println("Unrecognized format, choose csv or xml please json" + format);
        }
    }

    private void saveAsCSV(List<GroupData> groups, File file) throws IOException {

        try(
        Writer writer = new FileWriter(file);
        ) {
            for (GroupData group : groups) {
                writer.write(String.format("%s;%s;%s\n", group.getName(), group.getGroupHeader(), group.getGroupFooter()));
            }
        }
    }

    private void saveAsXML(List<GroupData> groups, File file) throws IOException {

        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        String xml = xStream.toXML(groups);
        try(
        Writer writer = new FileWriter(file);
        ) {
            writer.write(xml);
        }
    }

    private void saveAsJSON(List<GroupData> groups, File file) throws IOException {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        String json = gson.toJson(groups);
        try(
        Writer writer = new FileWriter(file);
        ) {
            writer.write(json);
        }
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
