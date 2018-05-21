package ad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//简单关键字过滤 结果
public class NLP_filter {
    //获得去除后缀名的文件名
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
//分好词的广告文本和违规词库是以一行一行来存储的
        //待识别的广告
        String filename = "G:\\Tencent Files\\593844323\\FileRecv\\JnaTest_V1\\JnaTest_V1\\out\\fen";
        ArrayList<String> filelist = NLP.readDirs(filename);
        for (int i = 0; i < filelist.size(); i++) {
            String name = getFileNameNoEx(filelist.get(i));
            BufferedReader b1 = new BufferedReader(new FileReader(filelist.get(i)));
            //词库
            BufferedReader b2 = new BufferedReader(new FileReader("G:\\Tencent Files\\593844323\\FileRecv\\JnaTest_V1\\JnaTest_V1\\in\\stop (2).txt"));
            String fName = filelist.get(i);
            File tempFile = new File(fName.trim());
            String fileName = tempFile.getName();
            //写出的违规结果
            BufferedWriter bw = new BufferedWriter(new FileWriter("G:\\Tencent Files\\593844323\\FileRecv\\JnaTest_V1\\JnaTest_V1\\out\\stop1\\" + fileName));
            String s1 = null;
            String s2 = null;
            Set<String> set = new HashSet<String>();
            Set<String> set1 = new HashSet<String>();
            //set存放的是违法词库中的词 不重复
            while ((s1 = b1.readLine()) != null) {
                set.add(s1);
            }
            //set存放的是一个广告文本中分好的词 不重复
            while ((s2 = b2.readLine()) != null) {
                set1.add(s2);
            }
            set.retainAll(set1);
            boolean in = set.isEmpty();
            if (!in) {
                System.out.println(fileName + "------------");
                System.out.println("违规词汇是:" + set);
                int size = set.size();
                System.out.println("违规个数是:" + size);
                bw.write(name + " ");
                for (String s : set) {
                    bw.write(s + " ");//System.out.println("写入成功");
                    //System.out.println(s);
                }
                String s = "";
                s = size + "";
                bw.write(s);
            } else {
                System.out.println(fileName + "------------");
                System.out.println("没有违规词汇");
                bw.write(name + " " + 0);
            }
            ;
            bw.close();
            b2.close();
            b1.close();
        }
    }
}
