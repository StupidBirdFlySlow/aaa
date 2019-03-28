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
 * ��֤��������
 * 
 * @author
 */
public class ImageUtils2 {
	// ͼƬ�Ŀ�ȡ�
	private static int width = 80;
	// ͼƬ�ĸ߶ȡ�
	private static int height = 40;
	// ��֤���ַ�����
	private static int codeCount = 5;
	// ��֤���������
	private static int lineCount = 50;
	// ��֤��
	private static String code = null;
	// ��֤��ͼƬBuffer
	private static BufferedImage buffImg = null;

	// ��֤�뷶Χ,ȥ��0(����)��O(ƴ��)���׻�����(Сд��1��LҲ����ȥ��,��д������)
	private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static void createCode() {
		int x = 0, fontHeight = 0, codeY = 0;
		int red = 0, green = 0, blue = 0;

		x = width / (codeCount + 2);// ÿ���ַ��Ŀ��(���Ҹ��ճ�һ���ַ�)
		fontHeight = height-20;// ����ĸ߶�
		codeY = height - 10;

		// ͼ��buffer
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		// ���������
		Random random = new Random();
		// ��ͼ�����Ϊ��ɫ
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// ��������,�����޸�Ϊ������
		Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
		// Font font = new Font("Times New Roman", Font.ROMAN_BASELINE,
		// fontHeight);
		g.setFont(font);

		for (int i = 0; i < lineCount; i++) {
			// ���������ʼ�ͽ�������
			int xs = random.nextInt(width);// x���꿪ʼ
			int ys = random.nextInt(height);// y���꿪ʼ
			int xe = xs + random.nextInt(width / 8);// x�������
			int ye = ys + random.nextInt(height / 8);// y�������

			// �����������ɫֵ���������ÿ�������ߵ���ɫֵ������ͬ��
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawLine(xs, ys, xe, ye);
		}

		// randomCode��¼�����������֤��
		StringBuffer randomCode = new StringBuffer();
		// �������codeCount���ַ�����֤�롣
		for (int i = 0; i < codeCount; i++) {
			String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
			// �����������ɫֵ���������ÿ���ַ�����ɫֵ������ͬ��
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawString(strRand, (i + 1) * x, codeY);
			// ���������ĸ�����������һ��
			randomCode.append(strRand);
		}
		// ����λ���ֵ���֤�뱣�浽Session�С�
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
	 * ���Ժ���,Ĭ�����ɵ�d��
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