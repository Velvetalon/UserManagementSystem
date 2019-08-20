package checkimage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class CheckImageUtil {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 30;
    private static final int LINE_NUM = 6;

    private static final String CODE_STRING = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";

    private static final String contextType = "image/x-png";

    public static void writeCheckImage( String checkCode, OutputStream ops ){
        // 实现需要在内存中构建一张图片
        BufferedImage br = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        //首先 换背景
        // 拿到 可以操作图片的对象
        Graphics2D graphics = (Graphics2D) br.getGraphics();

        //设置 颜色
        graphics.setColor(Color.PINK);
        //填充颜色
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        //画个边框, 为蓝色
        graphics.setColor(Color.WHITE);

        graphics.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);

        Random rand = new Random();

        //设置画字符的颜色
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("宋体", Font.BOLD, 18));
        int m = 20;

        for (int i = 0; i < checkCode.length(); i++) {

            int jiaodu = rand.nextInt(60) - 30;

            double theta = jiaodu * Math.PI / 180;         //     / n *pi * r 180

            graphics.rotate(theta, m, 20);   // 旋转

            graphics.drawString(checkCode.charAt(i) + "", m, 20);

            graphics.rotate(-theta, m, 20);

            m += 20;
        }

        //指定线的颜色
        graphics.setColor(Color.GREEN);

        //画干扰线
        for (int i = 0; i < LINE_NUM; i++) {
            int x1 = rand.nextInt(WIDTH);
            int x2 = rand.nextInt(WIDTH);
            int y1 = rand.nextInt(HEIGHT);
            int y2 = rand.nextInt(HEIGHT);

            graphics.drawLine(x1, y1, x2, y2);
        }

        try {
            ImageIO.write(br, "png", ops);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getContextType(){
        return contextType;
    }

    public static String getCheckcode( int lengh ){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < lengh; i++) {
            sb.append(CODE_STRING.charAt(random.nextInt(CODE_STRING.length())));
        }
        return sb.toString();
    }

    public static String getCheckcode(){
        return getCheckcode(4);
    }
}
