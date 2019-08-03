package com.furesky.base.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 验证码图片工具类
 * 
 * @author jianda
 * @date 2018年1月20日
 */
public class VerifyImage {
	private static final Log logger = LogFactory.getLog(VerifyImage.class);
	private static Random random=new Random();
	
	//默认验证码字体
	private static final String CODE_FONT = "Algerian";
	
	/**
	 * 生成验证码图片
	 * 
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param outputFile 图片文件
	 * @param codeSize 验证码长度
	 * @return code 验证码
	 * @date 2018年1月20日
	 */
	public static String create(int width, int height, File outputFile, int codeSize){
		String code = RandomCode.get(codeSize);
		create(width, height, outputFile, code);
		return code;
	}
	
	/**
	 * 生成验证码图片
	 * 
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param outputFile 图片文件对象
	 * @param code 验证码
	 * @date 2018年1月20日
	 */
	public static void create(int width, int height, File outputFile, String code){
		if (outputFile == null) {
			return;
		}
		File dir = outputFile.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		FileOutputStream fos=null;
		try {
			outputFile.createNewFile();
			fos = new FileOutputStream(outputFile);
			create(width, height, fos, code);
		} catch (IOException e) {
			logger.error("【图片验证码】 --> 创建文件失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成验证码图片
	 * 
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param os 输出流
	 * @param codeSize 验证码长度
	 * @return code 验证码
	 * @date 2018年1月20日
	 */
	public static String create(int width, int height, OutputStream os, int codeSize){
		String code = RandomCode.get(codeSize);
		create(width, height, os, code);
		return code;
	}

	/**
	 * 生成验证码图片
	 * 
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param os 输出流
	 * @param code 验证码
	 * @date 2018年1月20日
	 */
	public static void create(int width, int height, OutputStream os, String code) {
		int verifySize = code.length();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Random rand = new Random();
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color[] colors = new Color[5];
		Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA,
				Color.ORANGE, Color.PINK, Color.YELLOW };
		float[] fractions = new float[colors.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
			fractions[i] = rand.nextFloat();
		}
		Arrays.sort(fractions);

		g2.setColor(Color.GRAY);// 设置边框色
		g2.fillRect(0, 0, width, height);

		Color c = getRandColor(200, 250);
		g2.setColor(c);// 设置背景色
		g2.fillRect(0, 2, width, height - 4);

		// 绘制干扰线
		Random random = new Random();
		g2.setColor(getRandColor(160, 200));// 设置线条的颜色
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g2.drawLine(x, y, x + xl + 40, y + yl + 20);
		}

		// 添加噪点
		float yawpRate = 0.05f;// 噪声率
		int area = (int) (yawpRate * width * height);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int rgb = getRandomIntColor();
			image.setRGB(x, y, rgb);
		}

		shear(g2, width, height, c);// 使图片扭曲

		g2.setColor(getRandColor(100, 160));
		int fontSize = height - 4;
		Font font = new Font(CODE_FONT, Font.ITALIC, fontSize);
		g2.setFont(font);
		char[] chars = code.toCharArray();
		for (int i = 0; i < verifySize; i++) {
			AffineTransform affine = new AffineTransform();
			affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
					(width / verifySize) * i + fontSize / 2, height / 2);
			g2.setTransform(affine);
			g2.drawChars(chars, i, 1, ((width - 10) / verifySize) * i + 5, height / 2 + fontSize / 2 - 10);
		}

		g2.dispose();
		
		try{
			ImageIO.write(image, "jpg", os);
		} catch (IOException e) {
			logger.error("【图片验证码】 --> 生成图片失败");
			e.printStackTrace();
		}finally {
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 在给定范围内，获取随机颜色
	 */
	private static Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private static int getRandomIntColor() {
		int[] rgb = getRandomRgb();
		int color = 0;
		for (int c : rgb) {
			color = color << 8;
			color = color | c;
		}
		return color;
	}

	private static int[] getRandomRgb() {
		int[] rgb = new int[3];
		for (int i = 0; i < 3; i++) {
			rgb[i] = random.nextInt(255);
		}
		return rgb;
	}

	private static void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	private static void shearX(Graphics g, int w1, int h1, Color color) {

		int period = random.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}

	}

	private static void shearY(Graphics g, int w1, int h1, Color color) {

		int period = random.nextInt(40) + 10; // 50;

		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}

		}

	}

}
