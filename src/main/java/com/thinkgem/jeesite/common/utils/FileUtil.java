package com.thinkgem.jeesite.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

public class FileUtil
{
  private static String defaultPath = "";
  
  static
  {
    InputStream stream = FileUtil.class.getResourceAsStream("/jeesite.properties");
    try
    {
      Properties properties = new Properties();
      properties.load(stream);
      defaultPath = properties.getProperty("filePath");
      stream.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static String readTxtFile(InputStream inputStream)
  {
    StringBuffer buffer = new StringBuffer();
    try
    {
      InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line = null;
      while ((line = bufferedReader.readLine()) != null) {
        buffer.append(line + "\n");
      }
      bufferedReader.close();
      reader.close();
      inputStream.close();
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return buffer.toString();
  }
  
  public static String readTxtFile(File file)
  {
    StringBuffer buffer = new StringBuffer();
    if ((file.isFile()) && (file.exists())) {
      try
      {
        FileInputStream inputStream = new FileInputStream(file);
        buffer.append(readTxtFile(inputStream));
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
      }
    }
    return buffer.toString();
  }
  
  public static String readTxtFile(String filePath)
  {
    StringBuffer buffer = new StringBuffer();
    try
    {
      FileInputStream file = new FileInputStream(filePath);
      buffer.append(readTxtFile(file));
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    return buffer.toString();
  }
  
  private static String getExtension(String fileName)
  {
    String ret = "";
    if (fileName != null)
    {
      int index = fileName.lastIndexOf(".");
      if ((index > 0) && (index < fileName.length() - 1)) {
        ret = fileName.substring(index + 1);
      }
    }
    return ret;
  }
  
  public static boolean isProhibitType(String fileName)
  {
    if (fileName != null)
    {
      String extension = getExtension(fileName);
      if (("JSP".equalsIgnoreCase(extension)) || ("INI".equalsIgnoreCase(extension)) || ("EXE".equalsIgnoreCase(extension)) || ("COM".equalsIgnoreCase(extension)) || ("HTM".equalsIgnoreCase(extension)) || ("HTML".equalsIgnoreCase(extension)) || ("CSS".equalsIgnoreCase(extension)) || ("JS".equalsIgnoreCase(extension)) || ("SH".equalsIgnoreCase(extension))) {
        return true;
      }
      return false;
    }
    return true;
  }
  
  public static boolean isProhibitType(File file)
  {
    return isProhibitType(file.getName());
  }
  
  public static boolean isImageFile(String fileName)
  {
    if (fileName != null)
    {
      String extension = getExtension(fileName);
      if (("JPG".equalsIgnoreCase(extension)) || ("JPEG".equalsIgnoreCase(extension)) || ("PNG".equalsIgnoreCase(extension)) || ("GIF".equalsIgnoreCase(extension))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isImageFile(File file)
  {
    return isImageFile(file.getName());
  }
  
  public static String getRandomFileName(String fileName)
  {
    String extension = "";
    if ((fileName.lastIndexOf(".") != -1) && (fileName.lastIndexOf(".") != 0)) {
      extension = fileName.substring(fileName.lastIndexOf("."));
    }
    SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMM");
    
    String sMonth = sFormat.format(new Date());
    
    File folder = new File(defaultPath.concat(File.separator).concat(sMonth));
    if (!folder.exists())
    {
      folder.mkdir();
    }
    else if (!folder.isDirectory())
    {
      System.out.println("目录名被文件占用");
      return null;
    }
    sFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    String sFileName = sFormat.format(new Date()).concat("_").concat(String.valueOf(new Random().nextInt(10000))).concat(extension);
    return File.separator.concat(sMonth).concat(File.separator).concat(sFileName);
  }
  
  public static OutputStream getNewFileOutputStream(String filePath)
  {
    OutputStream out = null;
    File dest = new File(defaultPath.concat(filePath));
    try
    {
      out = new FileOutputStream(dest);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    return out;
  }
  
  private static boolean isExists(String path)
  {
    String sFileName = defaultPath.concat(File.separator).concat(path);
    File file = new File(sFileName);
    return file.exists();
  }
  
  public static String BASE64Encoder(String filePath)
  {
    byte[] data = null;
    try
    {
      InputStream in = new FileInputStream(filePath);
      data = new byte[in.available()];
      in.read(data);
      in.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    Base64 base64 = new Base64();
    return new String(base64.encode(data));
  }
  
  public static String saveBase64File(String content, String fileName)
  {
    if ((content == null) || (fileName == null)) {
      return null;
    }
    Base64 base64 = new Base64();
    try
    {
      byte[] bytes = base64.decode(content);
      for (int i = 0; i < bytes.length; i++) {
        if (bytes[i] < 0)
        {
          int tmp44_42 = i; byte[] tmp44_41 = bytes;tmp44_41[tmp44_42] = ((byte)(tmp44_41[tmp44_42] + 256));
        }
      }
      String filePath = null;
      for (;;)
      {
        filePath = getRandomFileName(fileName);
        if (!isExists(filePath)) {
          break;
        }
      }
      File dest = new File(defaultPath.concat(filePath));
      OutputStream out = new FileOutputStream(dest);
      out.write(bytes);
      out.flush();
      out.close();
      return filePath.replace("\\", "/");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  protected static void saveAbsoluteFile(InputStream stream, String filePath)
  {
    File dest = new File(filePath);
    try
    {
      OutputStream destStream = new FileOutputStream(dest);
      BufferedInputStream bis = new BufferedInputStream(stream);
      byte[] buffer = new byte[2048];
      int length = 0;
      while ((length = bis.read(buffer)) != -1) {
        destStream.write(buffer, 0, length);
      }
      destStream.close();
      bis.close();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static String saveFile(InputStream stream, String fileName)
  {
    String filePath = null;
    if (isProhibitType(fileName)) {
      return null;
    }
    for (;;)
    {
      filePath = getRandomFileName(fileName);
      if (!isExists(filePath)) {
        break;
      }
    }
    File dest = new File(defaultPath.concat(filePath));
    try
    {
      OutputStream destStream = new FileOutputStream(dest);
      BufferedInputStream bis = new BufferedInputStream(stream);
      byte[] buffer = new byte[2048];
      int length = 0;
      while ((length = bis.read(buffer)) != -1) {
        destStream.write(buffer, 0, length);
      }
      destStream.close();
      bis.close();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return filePath.replace("\\", "/");
  }
  
  public static String saveFile(File file)
  {
    String filePath = null;
    try
    {
      InputStream stream = new FileInputStream(file);
      filePath = saveFile(stream, file.getName());
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    return filePath;
  }
  
  public static File getFile(String filePath)
  {
    filePath = filePath.replace("/", File.separator);
    File file = new File(defaultPath.concat(filePath));
    if ((file.exists()) && (file.isFile())){
      return file;
    }
    return null;
  }
  
  public static File getFile(String filePath, String defaultFile)
  {
    File file = getFile(filePath);
    if (file == null) {
      file = getFile(defaultFile);
    }
    return file;
  }
  
  public static void main(String[] args)
  {
    System.out.println(BASE64Encoder("C:\\Users\\LY\\Downloads\\898441954111004-A001-20150113.csv"));
  }
}
