package com.thinkgem.jeesite.common.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.config.Global;

public class FileServlet
  extends HttpServlet
{
  private static final long serialVersionUID = -390872552876891185L;
  private String defaultFile;
  
  public FileServlet()
  {
    this.defaultFile = "/default/wutu.png";
  }
  
  private String getFilePath(HttpServletRequest request)
  {
    String URI = request.getRequestURI();
    String contextPath = request.getContextPath();
    String pattern = getInitParameter("url-pattern");
    return URI.substring(contextPath.length() + pattern.length());
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    doPost(request, response);
  }
  
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
	  String pattern = getInitParameter("url-pattern");
	  if("/a/file_upload_img".equals(pattern)){
		  fileUploadImg(request,response);
		  return;
	  }
    String filePath = getFilePath(request);
    File file = FileUtil.getFile(filePath, this.defaultFile);
    if (file != null)
    {
      String fileName = file.getName();
      response.reset();
//      response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
      response.addHeader("Content-Length", String.valueOf(file.length()));
//      response.setContentType("image/jpeg");
      OutputStream out = new BufferedOutputStream(response.getOutputStream());
      
      int length = 0;
      InputStream input = new FileInputStream(file);
      byte[] buffer = new byte[1024];
      while ((length = input.read(buffer)) != -1) {
        out.write(buffer, 0, length);
      }
      input.close();
      out.flush();
      out.close();
    }
  }
  
  public void fileUploadImg(HttpServletRequest request, HttpServletResponse response)throws IOException{
	  String basePath = Global.getConfig("filePath");
	  String urlPattern = getInitParameter("url-pattern");
	  String requestUri = request.getRequestURI();
	  String fileName = requestUri.substring(requestUri.indexOf(urlPattern)+urlPattern.length());
	  String filePath = basePath + fileName;
	  response.setContentType("image/jpeg");      //设置返回内容格式
      File file = new File(filePath);       //括号里参数为文件图片路径
      if(file.exists()){   //如果文件存在
    	  InputStream in = new FileInputStream(filePath);   //用该文件创建一个输入流
    	  OutputStream os = response.getOutputStream();  //创建输出流
    	  byte[] b = new byte[1024];  
    	  while( in.read(b)!= -1){  
    		  os.write(b);     
    	  }
    	  in.close(); 
    	  os.flush();
    	  os.close();
      }
  }
  
  
  public void init(ServletConfig config)
    throws ServletException
  {
    super.init(config);
  }
}
