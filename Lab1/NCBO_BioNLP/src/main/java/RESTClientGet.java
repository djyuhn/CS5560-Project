import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class RESTClientGet {
    public static void main(String[] args)
	{
		if(args.length<2)
		{
			System.out.println("\n$ java RESTClientGet [Bioconcept] [Inputfile] [Format]");
			System.out.println("\nBioconcept: We support five kinds of bioconcepts, i.e., Gene, Disease, Chemical, Species, Mutation. When 'BioConcept' is used, all five are included.\n\tInputfile: a file with a pmid list\n\tFormat: PubTator (tab-delimited text file), BioC (xml), and JSON\n\n");
		}
		else
		{
			String Bioconcept=args[0];
			String Inputfile=args[1];
			String Format="PubTator";
			if(args.length > 2)
			{
				Format=args[2];
			}
			
			try {
			    //Output filepath for abstract medical related words
                String filePath = "data/abstract_medical_words.txt";

                //pmids
				BufferedReader fr= new BufferedReader(new FileReader(Inputfile));

				//Creating a file
				Writer fileWriter = new FileWriter(filePath);
                Writer bufferedWriter = new BufferedWriter(fileWriter);

				String pmid = "";
				while((pmid = fr.readLine()) != null)
				{
					URL url_Submit;
					url_Submit = new URL("https://www.ncbi.nlm.nih.gov/CBBresearch/Lu/Demo/RESTful/tmTool.cgi/" + Bioconcept + "/" + pmid + "/"+Format+"/");
					HttpURLConnection conn_Submit = (HttpURLConnection) url_Submit.openConnection();
					conn_Submit.setDoOutput(true);
					BufferedReader br_Submit = new BufferedReader(new InputStreamReader(conn_Submit.getInputStream()));
					String line="";
					int line_ct = 0;
					while((line = br_Submit.readLine()) != null)
					{
						System.out.println(line);
                        String[] word_info = line.split("\t");
                        System.out.println(Arrays.toString(word_info));
                        System.out.println(word_info.length);
                        // Array must not be empty or contain 1 element of ""
                        if (word_info.length != 0 && !word_info[0].equals("")) {
                            if (line_ct < 2) {
                                bufferedWriter.write(line);
                            }
                            else {
                                // Extract PMID and output the word and the genre associated with the word
                                bufferedWriter.write(word_info[0] + "\t" + word_info[3] + "\t" + word_info[4] + "\n");
                            }
                        }
                        line_ct++;
					}
					conn_Submit.disconnect();
				}
				fr.close();
				bufferedWriter.close();
			}
			catch (MalformedURLException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
    }
}