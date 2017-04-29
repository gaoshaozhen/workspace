package cn.shop.base;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码生成
 * 
 * @author shaozhen
 */
public class ValidateCode
{
    /**
     * 生成验证码
     * 
     * @return
     */
    public static String createCode(int num)
    {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        
        for (int i = 0; i < num; i++)
        {
            sb.append(rand.nextInt(9));
        }
        return sb.toString();
    }
    
    public static BufferedImage createCodeImg(int num)
    {
        // 图片的宽度。
        int width = 160;
        // 图片的高度。
        int height = 40;
        // 验证码字符个数
        int codeCount = 5;
        // 验证码干扰线数
        int lineCount = 150;
        BufferedImage buffImg; 
        int fontWidth = 0, fontHeight = 0, codeY = 0;
        int red = 0, green = 0, blue = 0;  

        fontWidth = width / (codeCount + 2);// 每个字符的宽度
        fontHeight = height - 2;//字体的高度  
        codeY = height - 4;  
        buffImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  
        Graphics2D g = buffImg.createGraphics();
        Font font = new Font("黑体", Font.PLAIN, 25);
        g.setFont(font);
        g.fillRect(0, 0, width, height);
        Random random = new Random();  
        for (int i = 0; i < lineCount; i++)
        {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width / 8);
            int ye = ys + random.nextInt(height / 8);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            // g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }
        return buffImg;
    }
}
