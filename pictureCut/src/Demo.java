import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipFile;

public class Demo {
    public static void main(String[] args) {
        ZipFile s;

        String str = "F:\\dawn\\onepiece\\color\\ONE.PIECE.COLOR.HS.01-50[Chinese]海賊王彩漫中文版[高分辨率]\\OP_Color_Volume01_V3.1_HS";
        //文件与BufferedImage间的转换
//        BufferedImage bi=file2img("F:\\dawn\\onepiece\\color\\ONE.PIECE.COLOR.HS.01-50[Chinese]海賊王彩漫中文版[高分辨率]\\处理后\\java\\test1.jpg");  //读取图片
//        BufferedImage bii=img_tailor(bi,0,0,1560,2400);
//        BufferedImage bii=img_tailor(bi,0,0,1560,2400);
//        img2file(bii,"jpg","F:\\dawn\\onepiece\\color\\ONE.PIECE.COLOR.HS.01-50[Chinese]海賊王彩漫中文版[高分辨率]\\处理后\\java\\test3.jpg");  //生成图片

        if(true){
            solo();
        }else {

            File indir = new File(str);
            File outdir = new File("F:\\java-test\\out2");
            String[] picturesName = indir.list();
            for (int i = 0; i < picturesName.length; i++) {
                try {
                    Rgb rgbStan = new Rgb();
                    rgbStan.setRgbR(255);
                    rgbStan.setRgbG(255);
                    rgbStan.setRgbB(255);
                    Rgb rgbStanBlack = new Rgb();
                    rgbStanBlack.setRgbR(0);
                    rgbStanBlack.setRgbG(0);
                    rgbStanBlack.setRgbB(0);
                    ArrayList<Rgb> rgbList = getImageGRB(indir.getPath() + File.separator + picturesName[i]);


                    String pictureName = picturesName[i].replace(".jpg", "");
                    String chapter = pictureName.split("_")[0];
                    String page = pictureName.split("_")[1];
                    boolean flag = false;
                    for (int i1 = 0; i1 < rgbList.size(); i1 = i1 + 100) {
                        if (!rgbList.get(i1).equals(rgbStan)) {
                            BufferedImage bi = file2img(indir.getPath() + File.separator + picturesName[i]);
                            BufferedImage bii = img_tailor(bi, 0, 0, 3120, 2400);
                            img2file(bii, "jpg", outdir.getAbsolutePath() + File.separator + pictureName + ".jpg");
                            flag = true;
                            continue;
                        }
                    }
                    if (flag) {
                        continue;
                    }

                    //左侧的图片为后值，右侧的图片为前值
                    String left = "";
                    String right = "";
                    try {
                        left = page.split("-")[1] + ".jpg";
                        right = page.split("-")[0] + ".jpg";
                    } catch (Exception e) {
                        if ("001".equals(page)) {
                        } else if ("002".equals(page)) {
                            BufferedImage bi = file2img(indir.getPath() + File.separator + picturesName[i]);
                            BufferedImage bii = img_tailor(bi, 0, 0, 1560, 2400);
                            img2file(bii, "jpg", outdir.getAbsolutePath() + File.separator + page);
                        } else {
                            System.out.println(picturesName[i] + "不是内容文件");
                        }
                    }


                    //处理左侧图片
                    BufferedImage bi = file2img(indir.getPath() + File.separator + picturesName[i]);
                    BufferedImage bii = img_tailor(bi, 0, 0, 1560, 2400);
                    img2file(bii, "jpg", outdir.getAbsolutePath() + File.separator + chapter + "_" + left);

                    //处理右侧图片
                    BufferedImage biii = img_tailor(bi, 1560, 0, 1560, 2400);
                    img2file(biii, "jpg", outdir.getAbsolutePath() + File.separator + chapter + "_" + right);

                } catch (Exception e) {
                    System.out.println(picturesName[i] + "不符合要求，不进行处理");
                }
            }

//        ArrayList<Rgb> rgbList = getImageGRB(str);
        }

    }
    static void solo(){
        File dir = new File("F:\\java-test\\out");
        String infilename = "01_135-136.jpg";
        String pictureName = infilename.replace(".jpg","");
        String chapter = pictureName.split("_")[0];
        String page = pictureName.split("_")[1];
        //左侧的图片为后值，右侧的图片为前值
        String left = "";
        String right = "";
        left = page.split("-")[1] + ".jpg";
        right = page.split("-")[0]+ ".jpg";

        //处理左侧图片
        BufferedImage bi=file2img(dir.getPath() + File.separator + infilename);
        BufferedImage bii=img_tailor(bi,0,0,1560,2400);
        img2file(bii,"jpg",dir.getAbsolutePath() +  File.separator + chapter + "_" + left);

        //处理右侧图片
        BufferedImage biii = img_tailor(bi,1560,0,1560,2400);
        img2file(biii,"jpg",dir.getAbsolutePath() +  File.separator + chapter + "_" + right);

    }
    public static BufferedImage img_tailor(BufferedImage src,int x,int y,int width,int height) {
        BufferedImage back=src.getSubimage(x,y,width,height);
        return back;
    }
    //读取图片
    public static BufferedImage file2img(String imgpath) {
        try {
            BufferedImage bufferedImage= ImageIO.read(new File(imgpath));
            return bufferedImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //保存图片,extent为格式，"jpg"、"png"等
    public static void img2file(BufferedImage img,String extent,String newfile) {
        try {
            ImageIO.write(img, extent, new File(newfile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**

     * 获取图片RGB数组
     * @param filePath
     * @return
     */
    public static  ArrayList<Rgb> getImageGRB(String filePath) {
        File file  = new File(filePath);
        int rgbR;
        int rgbG;
        int rgbB;
        ArrayList<Rgb> rgbList = new ArrayList<>();
        int[][] result = null;
        if (!file.exists()) {
            return null;
        }
        try {
            BufferedImage bufImg = ImageIO.read(file);
            int height = bufImg.getHeight();
            int width = bufImg.getWidth();
            result = new int[width][height];
            int i = 1560;
//            System.out.println(height);
                for (int j = 0; j < height; j=j+100) {
                    int pixel = bufImg.getRGB(i, j);
                    Rgb rgb = new Rgb();
                    rgb.setRgbR((pixel & 0xff0000) >> 16);
                    rgb.setRgbG((pixel & 0xff00) >> 8);
                    rgb.setRgbB((pixel & 0xff));
                    rgb.setX(i);
                    rgb.setY(j);
                    rgbList.add(rgb);
//                    System.out.println(rgb);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rgbList;
    }
 }
