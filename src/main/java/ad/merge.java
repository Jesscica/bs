package ad;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class merge {
	public static void mergeFile(String outFile, String[] files) throws FileNotFoundException, IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFile)); 
		for(String f:files){
		String word=NLP.readFile(f);
		bw.write(word);
	}
		bw.close();
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
	    //合并文件到out\\fen.txt
			String infiles="out\\stop2";
			ArrayList<String> outfilelist=NLP.readDirs(infiles);
			int size=outfilelist.size();
		    String[] array = (String[])outfilelist.toArray(new String[size]);
			String out="out\\computer2.txt";
			mergeFile(out,array);
			System.out.println("合并成功");
		}
}
