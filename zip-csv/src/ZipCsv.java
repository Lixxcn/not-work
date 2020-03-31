import domain.AttaData;
import domain.InfoData;
import domain.ReadAttaData;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/4/2 10:25
 */
public class ZipCsv {
    public static String vendorFileEncoding = "GBK";
    public static List<AttaData> attaDataList = new ArrayList<>();
    public static List<InfoData> infoDataList = new ArrayList<>();
    public static List<ReadAttaData> readAttaDataList = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        String zipDir = "F:\\test\\sjt";
        File file = new File(zipDir);
        String[] fileNames = file.list();
        for (int i = 0; i < fileNames.length; i++) {
            if(fileNames[i].endsWith("zip")){
                System.out.println("找到压缩文件：" + fileNames[i]);
                zipUncompress(zipDir + File.separator + fileNames[i], zipDir);
                System.out.println("解压完成");
            }
        }
        fileNames = file.list();
        for (int i = 0; i < fileNames.length; i++) {
            //attaData_20200329.csv
            if(fileNames[i].contains("attaData") && fileNames[i].endsWith("csv")){
                System.out.println("解析attaData文件："+ fileNames[i]);
                paraAttaCsv(zipDir + File.separator + fileNames[i]);
                System.out.println("attaData文件解析完毕，共" + attaDataList.size() + "条");
//                for(AttaData attaData : attaDataList){
//                    System.out.println(attaData);
//                }
            }
            if(fileNames[i].contains("infoData") && fileNames[i].endsWith("csv")){
                System.out.println("解析infoData文件："+ fileNames[i]);
                paraInfoCsv(zipDir + File.separator + fileNames[i]);
                System.out.println("infoData文件解析完毕，共" + infoDataList.size() + "条");
//                for(InfoData infoData : infoDataList){
//                    System.out.println(infoData);
//                }
            }
            if(fileNames[i].contains("readAttaData") && fileNames[i].endsWith("csv")){
                System.out.println("解析readAttaData文件："+ fileNames[i]);
                paraReadCsv(zipDir + File.separator + fileNames[i]);
                System.out.println("readAttaData文件解析完毕，共" + readAttaDataList.size() + "条");
//                for(ReadAttaData readAttaData : readAttaDataList){
//                    System.out.println(readAttaData);
//                }
            }
        }
    }
    static void ParaProcess(String[] fileNames){

    }
    static void paraReadCsv(String fileName){
        String[] headNames = null; // 每列列头的名字
        String[] datas; // 每行数据
        BufferedReader reader = null;
        try {
            // reader = new BufferedReader(new FileReader(new File(fileName)));
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), vendorFileEncoding));
            int i = 0;// 判断是否是第一行数据
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (i == 0) {
                    headNames = line.split("\\|", -1);// 列头赋值
                    if (headNames.length == 1) {
                        headNames = headNames[0].split("\\^\\^", -1);
                    }
                } else {
                    datas = line.split("\\|", -1);// 行数据赋值
                    if (datas.length == 1) {
                        datas = datas[0].split("\\^\\^", -1);
//                        datas = StringTool.splitCSV(datas[0]);
                    }
                    if (headNames.length == datas.length) {
                        //ATTACHMENT_ID^^ATTACHMENT_REAL_NAME^^ATTACHMENT_SAVE_NAME^^ATTACHMENT_PATH^^FILE_SIZE
                        ReadAttaData readAttaData = new ReadAttaData();
                        for (int j = 0; j < headNames.length; j++) {
                            if (("RECEIPT_ID").equalsIgnoreCase(headNames[j])) {
                                readAttaData.setRECEIPT_ID(datas[j]);
                            } else if("ATTACHMENT_ID".equalsIgnoreCase(headNames[j])){
                                readAttaData.setATTACHMENT_ID(datas[j]);
                            }
                        }
                        readAttaDataList.add(readAttaData);
                    }
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    static void paraInfoCsv(String fileName){
        String[] headNames = null; // 每列列头的名字
        String[] datas; // 每行数据
        BufferedReader reader = null;
        try {
            // reader = new BufferedReader(new FileReader(new File(fileName)));
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), vendorFileEncoding));
            int i = 0;// 判断是否是第一行数据
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (i == 0) {
                    headNames = line.split("\\|", -1);// 列头赋值
                    if (headNames.length == 1) {
                        headNames = headNames[0].split("\\^\\^", -1);
                    }
                } else {
                    datas = line.split("\\|", -1);// 行数据赋值
                    if (datas.length == 1) {
                        datas = datas[0].split("\\^\\^", -1);
//                        datas = StringTool.splitCSV(datas[0]);
                    }
                    if (headNames.length == datas.length) {
                        //ATTACHMENT_ID^^ATTACHMENT_REAL_NAME^^ATTACHMENT_SAVE_NAME^^ATTACHMENT_PATH^^FILE_SIZE
                        InfoData infoData = new InfoData();
                        for (int j = 0; j < headNames.length; j++) {
                            if (("CITY_NAME").equalsIgnoreCase(headNames[j])) {
                                infoData.setCITY_NAME(datas[j]);
                            } else if("COUNTY_NAME".equalsIgnoreCase(headNames[j])){
                                infoData.setCOUNTY_NAME(datas[j]);
                            } else if("SITE_NAME".equalsIgnoreCase(headNames[j])){
                                infoData.setSITE_NAME(datas[j]);
                            } else if("SITE_NO".equalsIgnoreCase(headNames[j])){
                                infoData.setSITE_NO(datas[j]);
                            } else if("METER_NUM".equalsIgnoreCase(headNames[j])){
                                infoData.setMETER_NUM(datas[j]);
                            } else if("MONTH_ID".equalsIgnoreCase(headNames[j])){
                                infoData.setMONTH_ID(datas[j]);
                            } else if("READING_START".equalsIgnoreCase(headNames[j])){
                                infoData.setREADING_START(datas[j]);
                            } else if("READING_END".equalsIgnoreCase(headNames[j])){
                                infoData.setREADING_END(datas[j]);
                            } else if("MONTHLY_POWER".equalsIgnoreCase(headNames[j])){
                                infoData.setMONTHLY_POWER(datas[j]);
                            } else if("READING_START_TIME".equalsIgnoreCase(headNames[j])){
                                infoData.setREADING_START_TIME(datas[j]);
                            } else if("READING_END_TIME".equalsIgnoreCase(headNames[j])){
                                infoData.setREADING_END_TIME(datas[j]);
                            } else if("AVG_POWER".equalsIgnoreCase(headNames[j])){
                                infoData.setAVG_POWER(datas[j]);
                            } else if("CONTRACT_NO".equalsIgnoreCase(headNames[j])){
                                infoData.setCONTRACT_NO(datas[j]);
                            } else if("ELEC_PRICE".equalsIgnoreCase(headNames[j])){
                                infoData.setELEC_PRICE(datas[j]);
                            } else if("ELEC_AMT".equalsIgnoreCase(headNames[j])){
                                infoData.setELEC_AMT(datas[j]);
                            } else if("AVG_AMT".equalsIgnoreCase(headNames[j])){
                                infoData.setAVG_AMT(datas[j]);
                            } else if("ATTACHMENT_ID".equalsIgnoreCase(headNames[j])){
                                infoData.setATTACHMENT_ID(datas[j]);
                            } else if("ATTACHMENT_ID_RECEIPT".equalsIgnoreCase(headNames[j])){
                                infoData.setATTACHMENT_ID_RECEIPT(datas[j]);
                            } else if("ELEC_LOSE".equalsIgnoreCase(headNames[j])){
                                infoData.setELEC_LOSE(datas[j]);
                            } else if("ELEC_TOTAL".equalsIgnoreCase(headNames[j])){
                                infoData.setELEC_TOTAL(datas[j]);
                            } else if("RECEIPT_ID".equalsIgnoreCase(headNames[j])){
                                infoData.setRECEIPT_ID(datas[j]);
                            } else if("SETTLE_POWER".equalsIgnoreCase(headNames[j])){
                                infoData.setSETTLE_POWER(datas[j]);
                            } else if("PROVINCE".equalsIgnoreCase(headNames[j])){
                                infoData.setPROVINCE(datas[j]);
                            } else if("OWNER_CUST_CODE".equalsIgnoreCase(headNames[j])){
                                infoData.setOWNER_CUST_CODE(datas[j]);
                            }
                        }
                        infoDataList.add(infoData);
                    }
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    static void paraAttaCsv(String fileName){
        String[] headNames = null; // 每列列头的名字
        String[] datas; // 每行数据
        BufferedReader reader = null;
        try {
            // reader = new BufferedReader(new FileReader(new File(fileName)));
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), vendorFileEncoding));
            int i = 0;// 判断是否是第一行数据
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (i == 0) {
                    headNames = line.split("\\|", -1);// 列头赋值
                    if (headNames.length == 1) {
                        headNames = headNames[0].split("\\^\\^", -1);
                    }
                } else {
                    datas = line.split("\\|", -1);// 行数据赋值
                    if (datas.length == 1) {
                         datas = datas[0].split("\\^\\^", -1);
//                        datas = StringTool.splitCSV(datas[0]);
                    }
                    if (headNames.length == datas.length) {
                        //ATTACHMENT_ID^^ATTACHMENT_REAL_NAME^^ATTACHMENT_SAVE_NAME^^ATTACHMENT_PATH^^FILE_SIZE
                        AttaData attaData = new AttaData();
                        for (int j = 0; j < headNames.length; j++) {
                            if (("ATTACHMENT_ID").equalsIgnoreCase(headNames[j])) {
                                attaData.setATTACHMENT_ID(datas[j]);
                            } else if("ATTACHMENT_REAL_NAME".equalsIgnoreCase(headNames[j])){
                                attaData.setATTACHMENT_REAL_NAME(datas[j]);
                            } else if("ATTACHMENT_SAVE_NAME".equalsIgnoreCase(headNames[j])) {
                                attaData.setATTACHMENT_SAVE_NAME(datas[j]);
                            } else if("ATTACHMENT_PATH".equalsIgnoreCase(headNames[j])){
                                attaData.setATTACHMENT_PATH(datas[j]);
                            } else if("FILE_SIZE".equalsIgnoreCase(headNames[j])){
                                attaData.setFILE_SIZE(datas[j]);
                            }
                        }
                        attaDataList.add(attaData);
                    }
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void zipUncompress(String inputFile,String destDirPath) throws Exception {
        File srcFile = new File(inputFile);//获取当前压缩文件
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new Exception(srcFile.getPath() + "所指文件不存在");
        }
        ZipFile zipFile = new ZipFile(srcFile);//创建压缩文件对象
        //开始解压
        Enumeration<?> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            // 如果是文件夹，就创建个文件夹
            if (entry.isDirectory()) {
                String dirPath = destDirPath + "/" + entry.getName();
                srcFile.mkdirs();
            } else {
                // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                File targetFile = new File(destDirPath + "/" + entry.getName());
                // 保证这个文件的父文件夹必须要存在
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }
                targetFile.createNewFile();
                // 将压缩文件内容写入到这个文件中
                InputStream is = zipFile.getInputStream(entry);
                FileOutputStream fos = new FileOutputStream(targetFile);
                int len;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                fos.close();
                is.close();
            }
        }
    }
}
