package ad;
//所有文件编码需要转换为utf-8
//不标注词性，list[j]+"\n",输出在out\\fen中，目的是一个词一行输出
//对文件分词，标注词性 需要把list[j]+" "，目的是让一个文本一行输出  在out\\fencicing

import java.io.BufferedReader;
//分词
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import code.NlpirTest.CLibrary;

public class NLP {
    private static ArrayList<String> FileList = new ArrayList<String>();

    public static ArrayList<String> readDirs(String filepath) throws FileNotFoundException, IOException {
        try {
            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("输入的[]");
                System.out.println("filepath:" + file.getAbsolutePath());
            } else {
                String[] flist = file.list();
                for (int i = 0; i < flist.length; i++) {
                    File newfile = new File(filepath + "\\" + flist[i]);
                    if (!newfile.isDirectory()) {
                        FileList.add(newfile.getAbsolutePath());
                    } else if (newfile.isDirectory()) //如果文件是一个目录
                    {
                        readDirs(filepath + "\\" + flist[i]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return FileList;//返回的是目录下的文件清单
    }

    //读取文件
    public static String readFile(String file) throws FileNotFoundException, IOException {
        FileReader fr1 = new FileReader(file);
        BufferedReader br = new BufferedReader(fr1);
        String s = br.readLine();
        StringBuffer buffer = new StringBuffer();
        while (s != null) {
            buffer.append(s);
            s = br.readLine();

        }

        return buffer.toString();//返回的是文件的String类型
    }

    //NLP分词返回String
    public static String cut(String file) throws FileNotFoundException, IOException {
        String content = NLP.readFile(file);
        ArrayList<String> list = new ArrayList<String>();
        //Data文件夹的目录
//        String argu = "E:\\Workspaces\\JnaTest_V1";
        String argu = "G:\\Tencent Files\\593844323\\FileRecv\\JnaTest_V1\\JnaTest_V1";
        String system_charset = "UTF-8";
        int charset_type = 1;
            int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
            String nativeBytes = null;
            try {
                nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(content, 0);
                //System.out.println("分词结果为： " + nativeBytes);
                CLibrary.Instance.NLPIR_Exit();

            } catch (Exception ex) {
                ex.printStackTrace();
        }

        System.out.println("分词Done!!");
        return nativeBytes;

    }

    public static void main(String[] args) throws Exception {
        String files = "G:\\Tencent Files\\593844323\\FileRecv\\JnaTest_V1\\JnaTest_V1\\in\\data";
        ArrayList<String> filelist = NLP.readDirs(files);
        System.out.println(filelist);
        for (int i = 0; i < filelist.size(); i++) {
            String fName = filelist.get(i);
            File tempFile = new File(fName.trim());
            String fileName = tempFile.getName();
            File outfile = new File("G:\\Tencent Files\\593844323\\FileRecv\\JnaTest_V1\\JnaTest_V1\\out\\fenliuqi\\" + fileName);
            String cutwords = NLP.cut(filelist.get(i)); //get cut words for one file
            FileWriter fw = new FileWriter(outfile);
            String[] cut = cutwords.split(" ");
            for (String s : cut)
                fw.write(s + "\n");
            System.out.println(outfile);
            System.out.println("写入成功！");
            fw.close();

        }
	/*处理的是STOP词库的词性
	String infile="in\\adstop.txt";
	String cutstop=cut(infile);
	String outfile="out\\adstop";
	FileWriter fw = new FileWriter(outfile);
	String[] str=cutstop.split(" ");
	for(int i=0;i<str.length;i++){
		fw.write(str[i]+"\n");	
	}	
	System.out.println("写入");
	fw.close();*/

    }


}

