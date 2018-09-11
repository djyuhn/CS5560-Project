import edu.stanford.nlp.coref.CorefCoreAnnotations;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * Created by Mayanka on 13-Jun-16.
 */
public class Main {
    public static void main(String args[]) {
        try {
            // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
            Properties props = new Properties();
            props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
            StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

            //Output filepath for abstract's NLP words
            String abstracts_nlp = "data/abstracts_nlp.txt";

            //Output filepath for each abstract's nouns, verbs, and counts
            String abstract_nvc = "data/abstracts_nvc.txt";

            //Abstracts' NLP file and buffered writers
            Writer abstracts_nlp_fw = new FileWriter(abstracts_nlp);
            Writer abstracts_nlp_bw = new BufferedWriter(abstracts_nlp_fw);

            //Abstracts' Nouns, Verbs, and Counts (NVC) file and buffered writers
            Writer abstracts_pos_nvc = new FileWriter(abstract_nvc);
            Writer abstracts_nvc_bw = new BufferedWriter(abstracts_pos_nvc);

            File abstracts=new File("data/abstracts.txt");
            BufferedReader abstracts_br = new BufferedReader(new FileReader(abstracts));

            String abstract_line;
            int abstract_count = 1;
            while ((abstract_line = abstracts_br.readLine())!=null) {

                // read some text in the text variable
                String text = abstract_line;

                // create an empty Annotation just with the given text
                Annotation document = new Annotation(text);

                // run all Annotators on this text
                pipeline.annotate(document);

                // these are all the sentences in this document
                // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
                List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

                // Count number of nouns (NN) and verbs (VB) from POS for the abstract and store the verbs and nouns
                int nouns = 0;
                int verbs = 0;

                abstracts_nlp_bw.write("Abstract " + abstract_count + "\n\n");

                for (CoreMap sentence : sentences) {
                    // traversing the words in the current sentence
                    // a CoreLabel is a CoreMap with additional token-specific methods
                    for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {

                        System.out.println(token);
                        abstracts_nlp_bw.write(token + "\n");

                        // this is the text of the token
                        String word = token.get(CoreAnnotations.TextAnnotation.class);
                        System.out.println("Text Annotation");
                        System.out.println(token + ":" + word);
                        abstracts_nlp_bw.write("Text Annotation:\t" + word + "\n");

                        // this is the Lemmatized tag of the token
                        String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
                        System.out.println("Lemma Annotation");
                        System.out.println(token + ":" + lemma);
                        abstracts_nlp_bw.write("Lemma Annotation:\t" + lemma + "\n");

                        // this is the POS tag of the token
                        String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                        System.out.println("POS");
                        System.out.println(token + ":" + pos);
                        abstracts_nlp_bw.write("POS:\t" + pos + "\n");
                        if (pos.contains("NN")) {
                            nouns++;
                        }
                        else if (pos.contains("VB")) {
                            verbs++;
                        }


                        // this is the NER label of the token
                        String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                        System.out.println("NER");
                        System.out.println(token + ":" + ne);
                        abstracts_nlp_bw.write("NER:\t" + lemma + "\n\n");

                    }

                    // this is the parse tree of the current sentence
                    Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
                    System.out.println(tree);
                    // this is the Stanford dependency graph of the current sentence


                    Map<Integer, CorefChain> graph =
                            document.get(CorefCoreAnnotations.CorefChainAnnotation.class);
                    System.out.println(graph.values().toString());
                }

                abstracts_nvc_bw.write("Abstract " + abstract_count + "\nNouns:\t" + nouns + "\nVerbs:\t" + verbs + "\n");
                abstract_count++;
            }
            abstracts_nlp_bw.close();
            abstracts_nvc_bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
