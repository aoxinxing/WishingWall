package com.action;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class PictureCheckCodeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Font mFont = new Font("Times New Rome", Font.BOLD, 24);
	private ByteArrayInputStream inputStream;

	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		int width =160;
		int height = 45;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Graphics2D g2d=(Graphics2D)g;
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width , height);
	
		BasicStroke bs=new BasicStroke(2f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs);
		g.setColor(Color.DARK_GRAY);
		int[] xPoints=new int[3];
		int[] yPoints=new int[3];
		for(int j=0;j<3;j++){
			xPoints[j]=random.nextInt(width - 1);
			yPoints[j]=random.nextInt(height - 1);
		}
		g.drawPolyline(xPoints, yPoints,3);
		g.setFont(mFont);		
		String sRand="";
		int itmp=0;

		for(int i=0;i<4;i++){
			if(random.nextInt(2)==1){
				itmp=random.nextInt(26)+65;	
			}else{
				itmp=random.nextInt(10)+48;	
			}
			char ctmp=(char)itmp;
			sRand+=String.valueOf(ctmp);
			Color color=new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110));
			g.setColor(color);

			Graphics2D g2d_word=(Graphics2D)g;
			AffineTransform trans=new AffineTransform();
			trans.rotate(random.nextInt(45)*3.14/180,15*i+10,7);
			float scaleSize=random.nextFloat()+0.5f;
			if(scaleSize<0.8 || scaleSize>1.1f) scaleSize=1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);

			g.drawString(String.valueOf(ctmp),25*i+20,15);

		}
		HttpSession session=request.getSession(true);
		session.setAttribute("randCheckCode",sRand);
		g.dispose();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", outputStream);
        ByteArrayInputStream input = new ByteArrayInputStream(outputStream.toByteArray());

        this.setInputStream(input);
        
        input.close();
        outputStream.close();

		
		ImageIO.write(image,"JPEG",response.getOutputStream());

		return SUCCESS;
	 }
	
	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) fc = 255;
		if (bc > 255) bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}
}
