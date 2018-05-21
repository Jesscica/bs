package com.lzh.controller;

import code.NlpirTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Controller
@EnableAutoConfiguration
public class Main {
    @RequestMapping("/ce")
    @ResponseBody
    String home(String content) throws IOException {


        String argu = "G:\\Tencent Files\\593844323\\FileRecv\\JnaTest_V1\\JnaTest_V1";
        BufferedReader b2 = new BufferedReader(new FileReader("G:\\Tencent Files\\593844323\\FileRecv\\JnaTest_V1\\JnaTest_V1\\in\\stop (2).txt"));

        String system_charset = "UTF-8";
        int charset_type = 1;
        int init_flag = NlpirTest.CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
        String nativeBytes = null;
        try {
            nativeBytes = NlpirTest.CLibrary.Instance.NLPIR_ParagraphProcess(content, 0);
            System.out.println("分词结果为： " + nativeBytes);
            NlpirTest.CLibrary.Instance.NLPIR_Exit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Set<String> cutSet = new HashSet<String>();
        Set<String> set1 = new HashSet<String>();
        String s2= null;
        String cut[] = nativeBytes.split(" ");
        for (String str : cut) {
            cutSet.add(str);
        }
        while ((s2 = b2.readLine()) != null) {
            set1.add(s2);
        }
        cutSet.retainAll(set1);
        //没有违规词
        if (CollectionUtils.isEmpty(cutSet)){
            return "当前广告没有违规词";
        }
        String result = "";
        System.out.println("违规词汇是:" + cutSet.toString());
        result+="违规词个数有:"+cutSet.size()+"个,违规词汇是:" + cutSet.toString();
        return result;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

}
