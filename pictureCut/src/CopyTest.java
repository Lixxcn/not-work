import java.awt.image.BufferedImage;
import java.io.*;

public class CopyTest extends Demo{
    public static void main(String[] args)  {
        File file = new File("F:\\java-test\\in\\01_001.jpg");
        File file2 = new File("F:\\java-test\\out2\\01_001.jpg");
        String str1 = "F:\\java-test\\in\\01_002.jpg";
        String str2 = "F:\\java-test\\in\\01_002-02.jpg";

        BufferedImage bi=file2img(str1);
        BufferedImage bii=img_tailor(bi,0,0,1560,2400);
        img2file(bii,"jpg", str2);
        System.out.println("test");


//        copyFile("F:\\java-test\\in\\01_001.jpg","F:\\java-test\\out2");
//        listDicTory2(file,file2);

//        FileInputStream in=new FileInputStream(file);
//        InputStreamReader inReader=new InputStreamReader(in);
//        FileOutputStream out=new FileOutputStream(file2,true);//true:不覆盖原来内容,false:覆盖原来内容
//        OutputStreamWriter outWriter=new OutputStreamWriter(out, "utf-8");
//        int c;
//        while((c=inReader.read())!=-1) {
//
//            outWriter.write((char)c);
//            outWriter.flush();
//        }
//        System.out.println("复制完成");
//        inReader.close();
//        outWriter.close();

    }

    public static void listDicTory2(File file, File file2) throws IllegalAccessException, IOException {
        FileReader fr=new FileReader(file);
        FileWriter fw=new FileWriter(file2,true);//true:不覆盖原来内容,false:覆盖原来内容
        int c;
        while((c=fr.read())!=-1) {
            fw.write((char)c);
            fw.flush();
        }
        fr.close();
        fw.close();
    }

    /*
     * 实现文件的拷贝
     * @param srcPathStr
     *          源文件的地址信息
     * @param desPathStr
     *          目标文件的地址信息
     */
    private static void copyFile(String srcPathStr, String desPathStr) {
        //1.获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\")+1); //目标文件地址
        System.out.println(newFileName);
        desPathStr = desPathStr + File.separator + newFileName; //源文件地址
        System.out.println(desPathStr);

        try{
            //2.创建输入输出流对象
            FileInputStream fis = new FileInputStream(srcPathStr);
            FileOutputStream fos = new FileOutputStream(desPathStr);

            //创建搬运工具
            byte datas[] = new byte[1024*8];
            //创建长度
            int len = 0;
            //循环读取数据
            while((len = fis.read(datas))!=-1){
                fos.write(datas,0,len);
            }
            //3.释放资源
            fis.close();
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
