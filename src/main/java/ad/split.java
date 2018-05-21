package ad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//以标点符号的词性标注，分句子
public class split {
	public static void main(String[] args) {
		try {
			
			File inputfile = new File("out\\fen.txt");
			FileReader fr = new FileReader(inputfile);
			BufferedReader reader = new BufferedReader(fr);
			
			File outfile = new File("out\\split_sentence.txt");
			FileWriter fw = new FileWriter(outfile);

			String line;
			while ((line = reader.readLine()) != null) {
				String temp, emotion;
				if ((temp = splitSen(line)).length() > 0) {
					emotion = temp.substring(0, temp.length() - 1);					
					fw.write(emotion + "\n");					
				}
			}
			fw.close();
			reader.close();
			fr.close();
			genFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String splitSen(String line) {
		String out = new String("");
		String[] str;
		
		line = line.trim();
		str = line.split("/wd");
		for (int i = 0; i < str.length; i++) {
			str[i] = str[i]+"/wd";
			System.out.println(str[i]);
			str[i] = str[i].trim();
			String[] strList;
			strList = str[i].split(" ");
			for(int j = 0; j < strList.length; j++){
				if (strList[j].endsWith(new String("/wd"))||strList[j].endsWith(new String("/wj"))||strList[j].endsWith(new String("/wt"))||strList[j].endsWith(new String("/ww"))||strList[j].endsWith(new String("/wkz"))||strList[j].endsWith(new String("/wky"))) {
					out += "\n";					
				}else{
					out += strList[j] + " ";
				}	
			}
		}
		return out;
	}
	
	public static void genFile() throws IOException {
		
		File inputfile = new File("out\\split_sentence.txt");
		FileReader fr = new FileReader(inputfile);
		BufferedReader reader = new BufferedReader(fr);
		
		File outfile = new File("out\\sentence.txt");
		FileWriter fw = new FileWriter(outfile);

		
		String line;
		while ((line = reader.readLine()) != null) {
		
			if(!line.equals("")){
				System.out.println(line);
				fw.write(line+"\n");
			}
		}
		fw.close();
		reader.close();
		fr.close();
	}
}
