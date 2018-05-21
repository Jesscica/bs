package MI;

import java.util.ArrayList;  
import java.util.List;  
import java.util.TreeSet;  
  
/** 
 * 计算句子中每个词的信息熵 
 *  
 * @author Administrator 
 * 
 */  
public class GetWordEntropy {  
  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
  
        String sentence = "由于 这 款 颜色 不好 控制 的   是 随机 发货 的   亲 谅解   水性 和 油性 的 两 个 最佳 清洁 配方 ， 能够 有效 的 卸 除 防水 及 不易 脱落 的 眼 部 及 面部 妆 容 。 轻柔 配方 ， 不 刺激 眼 部 细嫩 肌肤 、 缓解 紧张 的 面部 皮肤 ， 减少 肌肤 水 份 流失 ， 无 紧 绷 油腻 感 ， 清爽 ， 保 湿 ， 不 刺激 。 ";  
        String[] words = sentence.split(" ");  
        CalculateWordEntropy(words);  
  
    }  
  
    public static void CalculateWordEntropy(String[] words) {  
  
        int length = words.length;  
  
        ArrayList<String[]> wordList = new ArrayList<String[]>();  
        // 将分好的词每3个一组存到数组中  
        for (int i = 0; i < length; i++) {  
  
            String[] wordSeg = new String[3];  
            if (i == 0) {  
                wordSeg[0] = "null";  
                wordSeg[1] = words[i];  
                wordSeg[2] = words[i + 1];  
            } else if (i == length - 1) {  
                wordSeg[0] = words[i - 1];  
                wordSeg[1] = words[i];  
                wordSeg[2] = "null";  
            } else {  
                wordSeg[0] = words[i - 1];  
                wordSeg[1] = words[i];  
                wordSeg[2] = words[i + 1];  
            }  
  
            wordList.add(wordSeg);  
  
        }  
        // 去除重复的词  
        List<String> lists = new ArrayList<String>();  
        for (int l = 0; l < length; l++) {  
            lists.add(words[l]);  
        }  
        List<String> tempList = new ArrayList<String>();  
        for (String str : lists) {  
            if (!(tempList.contains(str))) {  
                tempList.add(str);  
            }  
        }  
        String[] wordClean = new String[tempList.size()];  
        for (int m = 0; m < tempList.size(); m++) {  
            wordClean[m] = tempList.get(m);  
        }  
        // 统计每个词的词频  
        int[] frequent = new int[wordClean.length];  
        for (int j = 0; j < wordClean.length; j++) {  
            int count = 0;  
            for (int k = 0; k < words.length; k++) {  
                if (wordClean[j].equals(words[k])) {  
                    count++;  
                }  
            }  
            frequent[j] = count;  
        }  
        // 将三元组中中间的那个词相同的存到一个list中，然后计算该词的信息熵  
        double[] allEntropy = new double[wordClean.length];  
        for (int n = 0; n < wordClean.length; n++) {  
            ArrayList<String[]> wordSegList = new ArrayList<String[]>();  
            int count = 1;  
            for (int p = 0; p < wordList.size(); p++) {  
                String[] wordSegStr = wordList.get(p);  
                if (wordSegStr[1].equals(wordClean[n])) {  
                    count++;  
                    wordSegList.add(wordSegStr);  
                }  
            }  
            String[] leftword = new String[wordSegList.size()];  
            String[] rightword = new String[wordSegList.size()];  
            // 计算左信息熵  
            for (int i = 0; i < wordSegList.size(); i++) {  
                String[] left = wordSegList.get(i);  
                leftword[i] = left[0];  
            }  
            // 去除左边重复的词  
            List<String> listsLeft = new ArrayList<String>();  
            for (int l = 0; l < leftword.length; l++) {  
                listsLeft.add(leftword[l]);  
            }  
            List<String> tempListLeft = new ArrayList<String>();  
            for (String str : listsLeft) {  
                if (!(tempListLeft.contains(str))) {  
                    tempListLeft.add(str);  
                }  
            }  
            String[] leftWordClean = new String[tempListLeft.size()];  
            for (int m = 0; m < tempListLeft.size(); m++) {  
                leftWordClean[m] = tempListLeft.get(m);  
            }  
            // 统计左边每个词的词频  
            int[] leftFrequent = new int[leftWordClean.length];  
            for (int j = 0; j < leftWordClean.length; j++) {  
                int leftcount = 0;  
                for (int k = 0; k < leftword.length; k++) {  
                    if (leftWordClean[j].equals(leftword[k])) {  
                        leftcount++;  
                    }  
                }  
                leftFrequent[j] = leftcount;  
            }  
            // 计算左熵值  
            double leftEntropy = 0;  
            for (int i = 0; i < leftFrequent.length; i++) {  
                double a = (double) leftFrequent[i] / count;  
                double b = Math.log((double) leftFrequent[i] / count);  
                leftEntropy += -a * b;  
                // leftEntropy +=  
                // (-(double)(leftFrequent[i]/count))*Math.log((double)(leftFrequent[i]/count));  
            }  
            // 计算右信息熵  
            for (int i = 0; i < wordSegList.size(); i++) {  
                String[] right = wordSegList.get(i);  
                rightword[i] = right[2];  
            }  
            // 去除右边重复的词  
            List<String> listsRight = new ArrayList<String>();  
            for (int l = 0; l < rightword.length; l++) {  
                listsRight.add(rightword[l]);  
            }  
            List<String> tempListRight = new ArrayList<String>();  
            for (String str : listsRight) {  
                if (!(tempListRight.contains(str))) {  
                    tempListRight.add(str);  
                }  
            }  
            String[] rightWordClean = new String[tempListRight.size()];  
            for (int m = 0; m < tempListRight.size(); m++) {  
                rightWordClean[m] = tempListRight.get(m);  
            }  
            // 统计右边每个词的词频  
            int[] rightFrequent = new int[rightWordClean.length];  
            for (int j = 0; j < rightWordClean.length; j++) {  
                int rightcount = 0;  
                for (int k = 0; k < rightword.length; k++) {  
                    if (rightWordClean[j].equals(rightword[k])) {  
                        rightcount++;  
                    }  
                }  
                rightFrequent[j] = rightcount;  
            }  
            // 计算右熵值  
            double rightEntropy = 0.0;  
            for (int i = 0; i < rightFrequent.length; i++) {  
                double a = (double) rightFrequent[i] / count;  
                double b = Math.log((double) rightFrequent[i] / count);  
                rightEntropy += -a * b;  
                // rightEntropy +=  
                // (-(double)(rightFrequent[i]/count))*Math.log((double)(rightFrequent[i]/count));  
            }  
            // 计算词的总信息熵  
            double wordEntropy = leftEntropy + rightEntropy;  
            allEntropy[n] = wordEntropy;  
  
        }  
  
        for (int i = 0; i < allEntropy.length; i++) {  
            System.out.println(wordClean[i] + ":" + allEntropy[i]);  
        }  
    }  
  
}  
