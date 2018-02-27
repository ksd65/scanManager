package com.thinkgem.jeesite.modules.mem.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

/**
 * Created by jijuyuan on 2017/8/10.
 */
@Controller
public class FileController extends BaseController {

    @RequestMapping(value = "20{filePath1}/{filePath2}/{fileName}.{type}")
    public void getImages(HttpServletResponse response,
                          @PathVariable String filePath1,
                          @PathVariable String filePath2,
                          @PathVariable String fileName,
                          @PathVariable String type
    ) throws IOException {
        File file = new File(Global.getUserfilesBaseDir()+File.separator+"20"+filePath1
        +File.separator + filePath2 + File.separator + fileName + "." + type);
        response.setContentType("image/"+(type.equals("png")?"png":"jpeg"));
        OutputStream out = response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        byte[] b = new byte[fis.available()];
        fis.read(b);
        out.write(b);
        out.flush();
    }

    @RequestMapping(value = "${adminPath}/file/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String plupload(@RequestParam MultipartFile file, HttpServletRequest request, HttpSession session) {
        Map<String,Object> data = new HashMap<String, Object>();
        try {
            String name = request.getParameter("name");
            Integer chunk = 0, chunks = 0;
            if(null != request.getParameter("chunk") && !request.getParameter("chunk").equals("")){
                chunk = Integer.valueOf(request.getParameter("chunk"));
            }
            if(null != request.getParameter("chunks") && !request.getParameter("chunks").equals("")){
                chunks = Integer.valueOf(request.getParameter("chunks"));
            }
            logger.info("chunk:[" + chunk + "] chunks:[" + chunks + "]");
            //检查文件目录，不存在则创建
            String filePath = "";
            if(null != request.getParameter("memberId")&&!"".equals(request.getParameter("memberId"))){
            	filePath = DateUtils.formatDate(new Date(),"yyyyMMdd") + File.separator + String.valueOf(request.getParameter("memberId"));
            }else{
            	filePath = DateUtils.formatDate(new Date(),"yyyyMMdd") + File.separator + "qrcode";
            }
            String relativePath = Global.getUserfilesBaseDir() + File.separator + filePath;
            File folder = new File(relativePath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            //目标文件
            File destFile = new File(folder, name);
            //文件已存在删除旧文件（上传了同名的文件）
            if (chunk == 0 && destFile.exists()) {
                destFile.delete();
                destFile = new File(folder, name);
            }
            //合成文件
            appendFile(file.getInputStream(), destFile);
            if (chunk == chunks - 1) {
                logger.info("上传完成");
            }else {
                logger.info("还剩["+(chunks-1-chunk)+"]个块文件");
            }
            data.put("code",0);
            data.put("path",File.separator + filePath+File.separator+name);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            data.put("code",-1);
        }

        return JSONUtils.toJSONString(data);
    }

    private void appendFile(InputStream in, File destFile) {
        OutputStream out = null;
        try {
            // plupload 配置了chunk的时候新上传的文件append到文件末尾
            if (destFile.exists()) {
                out = new BufferedOutputStream(new FileOutputStream(destFile, true), BUFFER_SIZE);
            } else {
                out = new BufferedOutputStream(new FileOutputStream(destFile),BUFFER_SIZE);
            }
            in = new BufferedInputStream(in, BUFFER_SIZE);

            int len = 0;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
                if(null != out){
                    out.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
