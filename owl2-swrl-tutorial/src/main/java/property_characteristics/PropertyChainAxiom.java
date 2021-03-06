package property_characteristics;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DJ Yuhn on 11/15/2018
 */
public class PropertyChainAxiom {
    public static void main(String[] args) throws IOException {
        List<String> tripletsArrayList = new ArrayList<>();
        try {
            File file=new File("data/triplets/allTriplets.txt");
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
            String triplet;

            while ((triplet=bufferedReader.readLine()) != null) {
                tripletsArrayList.add(triplet);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ArrayList<String> propertyChainArray = new ArrayList<>();
        // Property Chain Axiom
        for(int i = 0; i < tripletsArrayList.size(); i++) {
            String[] splitTriplet = tripletsArrayList.get(i).split("\t");
            for (int j = i + 1; j < tripletsArrayList.size(); j++) {
                String[] checkSplitTriplet = tripletsArrayList.get(j).split("\t");
                if(splitTriplet.length == 3 && checkSplitTriplet.length == 3) {
                    if (splitTriplet[2].equals(checkSplitTriplet[0])) {
                        propertyChainArray.add(tripletsArrayList.get(i) + " || "
                                + tripletsArrayList.get(j) + " => "
                                + splitTriplet[0] + "\t"
                                + "--NEW PREDICATE--" + "\t"
                                + checkSplitTriplet[2] + "\n"
                        );
                    }
                }
            }
        }

        BufferedWriter propertyChainWriter = new BufferedWriter(new FileWriter("data/propertyChain.txt"));
        for(String symmetric: propertyChainArray) {
            propertyChainWriter.append(symmetric);
        }
        propertyChainWriter.close();

    }
}
