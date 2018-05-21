package review;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import ruc.irm.similarity.word.hownet2.concept.XiaConceptParser;

public class accurcy {
    public static double simmin = 0.95;

    public static void main(String[] args) throws Exception {
        String filepath1 = "E:\\Workspaces\\JnaTest_V1\\out\\computer.txt";
        String filepath2 = "E:\\Workspaces\\JnaTest_V1\\in\\rengong\\rengong.txt";
        float sum = 0;
        float sum1 = 0;
        float sum2 = 0;
        float P = 0;
        float R = 0;
        float F = 0;
        File file1 = new File(filepath1);
        File file2 = new File(filepath2);
        BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file1));
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file2));
        String lineTxt1 = null;
        String lineTxt2 = null;
        while ((lineTxt1 = bufferedReader1.readLine()) != null && (lineTxt2 = bufferedReader2.readLine()) != null) {
            String[] a = lineTxt1.split(" ");
            String[] b = lineTxt2.split(" ");
            //a[a.length-1]是最后一个元素
            if (a[0].equals(b[0])) {
                String aa = a[a.length - 1];
                String bb = b[b.length - 1];
                float aa1 = Float.parseFloat(aa);
                float bb1 = Float.parseFloat(bb);
                sum1 = sum1 + aa1;
                //System.out.println(sum1);//计算机识别出的总个数
                sum2 = sum2 + bb1;
                for (int i = 1; i < a.length - 1; i++) {
                    for (int j = 1; j < b.length - 1; j++) {
                        XiaConceptParser xParser = null;
                        //LiuConceptParser lParser = null;
                        xParser = XiaConceptParser.getInstance();
                        // lParser = LiuConceptParser.getInstance();

                        double sim = xParser.getSimilarity(a[i], b[j]);
                        if (a[i].contains(b[j])) {
                            //if(a[i].contains(b[j])||sim>simmin){
                            System.out.println(a[i]);
                            System.out.println(b[j]);
                            sum = sum + 1;
                        }
                    }
                }
            } else {
                System.out.println("文件路径不同");
            }
        }
        System.out.println("计算机与人工识别出相同的词为" + sum);
        System.out.println("计算机识别出的违规词个数" + sum1);
        System.out.println("人工识别出的违规词个数" + sum2);
        // P=sum/sum2; R=sum/sum1;F=2*P*R/(P+R);
        P = sum / sum1;
        R = sum / sum2;
        F = 2 * P * R / (P + R);
        System.out.println("准确率" + P);
        System.out.println("召回率" + R);
        System.out.println("F值是" + F);
        bufferedReader1.close();
        bufferedReader2.close();

    }
}
