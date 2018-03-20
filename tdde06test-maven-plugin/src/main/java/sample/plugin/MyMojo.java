package sample.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

@Mojo( name = "sayhi")
public class MyMojo extends AbstractMojo
{
    @Parameter(property = "filename")
    private String filename;

    String everything = "inget hittades i filen";



    public void execute() throws MojoExecutionException
    {
        try {
            everything = new MyMojo().readFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            getLog().info( everything );

        }
    }


    public String readFile(String filename) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();

        }catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        br.close();
        return everything;

    }

}