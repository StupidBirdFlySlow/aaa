package edu.hunau.base.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 验证码生成器
 * 
 * @author
 */
public class ImageUtils2 {
	// 图片的宽度。
	private static int width = 80;
	// 图片的高度。
	private static int height = 40;
	// 验证码字符个数
	private static int codeCount = 5;
	// 验证码干扰线数
	private static int lineCount = 50;
	// 验证码
	private static String code = null;
	// 验证码图片Buffer
	private static BufferedImage buffImg = null;

	// 验证码范围,去掉0(数字)和O(拼音)容易混淆的(小写的1和L也可以去掉,大写不用了)
	private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static void createCode() {
		int x = 0, fontHeight = 0, codeY = 0;
		int red = 0, green = 0, blue = 0;

		x = width / (codeCount + 2);// 每个字符的宽度(左右各空出一个字符)
		fontHeight = height-20;// 字体的高度
		codeY = height - 10;

		// 图像buffer
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		// 生成随机数
		Random random = new Random();
		// 将图像填充为白色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// 创建字体,可以修改为其它的
		Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
		// Font font = new Font("Times New Roman", Font.ROMAN_BASELINE,
		// fontHeight);
		g.setFont(font);

		for (int i = 0; i < lineCount; i++) {
			// 设置随机开始和结束坐标
			int xs = random.nextInt(width);// x坐标开始
			int ys = random.nextInt(height);// y坐标开始
			int xe = xs + random.nextInt(width / 8);// x坐标结束
			int ye = ys + random.nextInt(height / 8);// y坐标结束

			// 产生随机的颜色值，让输出的每个干扰线的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawLine(xs, ys, xe, ye);
		}

		// randomCode记录随机产生的验证码
		StringBuffer randomCode = new StringBuffer();
		// 随机产生codeCount个字符的验证码。
		for (int i = 0; i < codeCount; i++) {
			String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
			// 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawString(strRand, (i + 1) * x, codeY);
			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}
		// 将四位数字的验证码保存到Session中。
		code = randomCode.toString();
	}

	public static void write(String path) throws IOException {
		OutputStream sos = new FileOutputStream(path);
		write(sos);
	}

	public static void write(OutputStream sos) throws IOException {
		ImageIO.write(buffImg, "png", sos);
		sos.close();
	}

	/**
	 * 测试函数,默认生成到d盘
	 * 
	 * @param args
	 */
	public static String getYzm() {
		try {
			createCode();
			String path = "src/main/webapp/img/yzm.png";
			write(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}
}