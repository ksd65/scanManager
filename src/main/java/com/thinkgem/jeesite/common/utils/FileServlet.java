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
  
  public void init(ServletConfig config)
    throws ServletException
  {
    super.init(config);
  }
}
