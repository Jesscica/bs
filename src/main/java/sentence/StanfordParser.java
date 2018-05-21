package sentence;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;   
import edu.stanford.nlp.process.DocumentPreprocessor;  
import edu.stanford.nlp.ling.CoreLabel;    
import edu.stanford.nlp.ling.HasWord;    
import edu.stanford.nlp.trees.*;  
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
public class StanfordParser {
	public static void main(String[] args) throws IOException {  
	    LexicalizedParser lp =   
	       LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/chinesePCFG.ser.gz");  
	    if (args.length > 0) {  
	      demoDP(lp, args[0]);  
	    } else {  
	      demoAPI(lp);  
	    }  
	  }  
	  
	  public static void demoDP(LexicalizedParser lp, String filename) {  
	    TreebankLanguagePack tlp = new PennTreebankLanguagePack();  
	    GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();  
	    for (List<HasWord> sentence : new DocumentPreprocessor(filename)) {  
	      Tree parse = lp.apply(sentence);  
	      parse.pennPrint();  
	      System.out.println();  
	        
	      GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);  
	      Collection tdl = gs.typedDependenciesCCprocessed(true);  
	      System.out.println(tdl);  
	      System.out.println();  
	    }  
	  }  
	  
	  public static void demoAPI(LexicalizedParser lp) throws IOException {  
	    // This option shows parsing a list of correctly tokenized words  
		  String file="out\\sentence\\111.txt";
		 BufferedReader b1 = new BufferedReader(new FileReader(file));
		 String line;
		while((line=b1.readLine())!=null){
			int len=line.length();
		if(len>1) {	
	    String[] sent = line.split(" ");  
	    List<CoreLabel> rawWords = new ArrayList<CoreLabel>();  
	    for (String word : sent) {  
	      CoreLabel l = new CoreLabel();  
	      l.setWord(word);  
	      rawWords.add(l);  
	    }  
	    Tree parse = lp.apply(rawWords);  
	    parse.pennPrint();  
	    System.out.println();  
	    TreebankLanguagePack tlp = lp.getOp().langpack();  
	    GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();  
	    GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);  
	    List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();  
	    System.out.println(tdl);  
	    System.out.println();  
	    
	    TreePrint tp = new TreePrint("penn,typedDependenciesCollapsed",tlp);  
	   // tp.printTree(parse);  
	  }  
	  }	
	  }
	  private StanfordParser() {} // static methods only  
	  
	  
}

