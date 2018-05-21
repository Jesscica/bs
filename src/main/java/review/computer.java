package review;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ad.NLP;
import ad.NLP_filter;
import ad.combine;

public class computer {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String infiles = "out\\stop";
        ArrayList<String> outfilelist = NLP.readDirs(infiles);
        int size = outfilelist.size();
        String[] array = (String[]) outfilelist.toArray(new String[size]);
        String out = "out\\cumputerstop.txt";
        combine.mergeFiles(out, array);
        String files = "in\\rengong\\rengong.txt";
        String filename = "out\\fen";
        BufferedWriter bw = new BufferedWriter(new FileWriter(files));
        ArrayList<String> filelist = NLP.readDirs(filename);
        for (int i = 0; i < filelist.size(); i++) {
            String name = NLP_filter.getFileNameNoEx(filelist.get(i));
            bw.write(name + "\n");
        }
        bw.close();
    }
}
