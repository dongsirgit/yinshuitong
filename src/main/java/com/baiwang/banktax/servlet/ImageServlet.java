package com.baiwang.banktax.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = -9120328480369022825L;
	Random random = new Random();
	// Initialize global variables
	public void init() throws ServletException {
		// 使服务器启动headless模式,在该模式下，系统以缺少显示设备、键盘或鼠标等设备状态下运行
		System.setProperty("java.awt.headless", "true");
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			int width = 60, height = 20;
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			OutputStream os = response.getOutputStream();
			Graphics g = image.getGraphics();

			g.setColor(new Color(rdInt(200 ,250), rdInt(200 ,250), rdInt(200 ,250)));
			g.fillRect(0, 0, width, height);
			g.setFont(new Font("Arial", Font.PLAIN, 18));
			g.setColor(new Color(rdInt(160 ,200), rdInt(160 ,200), rdInt(160 ,200)));
			for (int i = 0; i < 155; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				g.drawLine(x, y, x + xl, y + yl);
			}

			String sRand = "";
			for (int j = 0; j < 4; j++) {
				String rand = String.valueOf(random.nextInt(10));
				sRand += rand;
				g.setColor(new Color(rdInt(20 ,130), rdInt(20 ,130), rdInt(20 ,130)));
				g.drawString(rand, 13 * j + 6, 16);
			}
			request.getSession().setAttribute("imageCode", sRand);
			g.dispose();
			ImageIO.write(image, "JPEG", os);
			os.flush();
			os.close();
			os = null;
			response.flushBuffer();
			// out.clear();
			// out = pageContext.pushBody();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// Clean up resources
	public void destroy() {
		//
	}

	int rdInt(int begInt, int endInt) {
		return begInt + random.nextInt(endInt - begInt);
	}
}