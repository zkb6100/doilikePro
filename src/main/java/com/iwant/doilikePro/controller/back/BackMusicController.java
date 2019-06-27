package com.iwant.doilikePro.controller.back;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/backMusic")
public class BackMusicController {
			Logger logger = LoggerFactory.getLogger(BackMusicController.class);
			String separatpr = File.separator;
			//String fileSaveURL = "/data/potato/music";
			@Value("${file.path}")
			String fileSaveURL = "";
			@RequestMapping(value="/fileUpload",method=RequestMethod.POST)
			public void uploadMusicLoad(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
				JSONObject  json = new JSONObject();
				String name = request.getParameter("user_name");
				String pass = request.getParameter("user_pass");
				logger.info("音乐文件正在被转换···");
		          if(file .isEmpty()) {
		              json.put("code" , 500);
		              json.put("msg" , "没有上传的文件流" );
		             writeJSON( json, response );
		              return ;
		         }
		         String fileName = file.getOriginalFilename();
		         logger.info("接收文件保存路径-{}，文件大小为-{}" , fileSaveURL , file.getSize());
		         File localFile = new File(fileSaveURL);
		         
		         if(!localFile.exists()) {
		        	 localFile.mkdirs();
		         }
		         //重命名
		         if(fileName.contains(".")) {
		        	 long newName = System.nanoTime();
		        	 fileName = newName + fileName.substring(fileName.indexOf(".")-1);
		         }
		         try {
		        	 file.transferTo(new File(fileSaveURL + fileName));
		        	 json.put("code" , 200);
		        	 logger.info("文件上传成功");
		        	 //写入到数据库 将歌曲名等信息
		        	 
		         }catch (Exception e) {
		        	 json.put("code" , 500);
		        	 logger.error("上传文件发生异常，异常信息:" , e );
				}
		         writeJSON( json, response );
			}
			
			public void writeJSON(JSONObject  json, HttpServletResponse response) throws IOException {
				PrintWriter pw = response.getWriter();
				pw.write(json.toString());
				pw.flush();
				pw.close();
			}
			
			@RequestMapping(value="/uploadAjaxFile",method=RequestMethod.POST)
			public void uploadAjaxFile(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
				JSONObject  json = new JSONObject();
				String name = request.getParameter("user_name");
				String pass = request.getParameter("user_pass");
				logger.info("音乐文件正在被转换···");
		          if(file .isEmpty()) {
		              json.put("code" , 500);
		              json.put("msg" , "没有上传的文件流" );
		             writeJSON( json, response );
		              return ;
		         }
		         String fileName = file.getOriginalFilename();
		         logger.info("接收文件保存路径-{}，文件大小为-{}" , fileSaveURL , file.getSize());
		         File localFile = new File(fileSaveURL);
		         if(!localFile.exists()) {
		        	 localFile.mkdirs();
		         }
		         //重命名
		         if(fileName.contains(".")) {
		        	 long newName = System.nanoTime();
		        	 fileName = newName + fileName.substring(fileName.indexOf(".")-1);
		         }
		         try {
		        	 file.transferTo(new File(fileSaveURL + fileName));
		        	 json.put("code" , 200);
		        	 logger.info("文件上传成功");
		        	 //写入到数据库 将歌曲名等信息
		        	 
		         }catch (Exception e) {
		        	 json.put("code" , 500);
		        	 logger.error("上传文件发生异常，异常信息:" , e );
				}
		         writeJSON( json, response );
			}
			
			    @PostMapping("/uploadWithProgress")
			    @ResponseBody
			    public Map<String, Object> upload(MultipartFile file){
			        Map<String, Object> result = new HashMap<String, Object>();
			        if (file != null && !file.isEmpty()){
			            try {
			            	 String fileName = file.getOriginalFilename();
					         logger.info("接收文件保存路径-{}，文件大小为-{}" , fileSaveURL , file.getSize());
					         File localFile = new File(fileSaveURL);
					         if(!localFile.exists()) {
					        	 localFile.mkdirs();
					         }
					         //重命名
					         if(fileName.contains(".")) {
					        	 long newName = System.nanoTime();
					        	 fileName = newName + fileName.substring(fileName.indexOf(".")-1);
					         }
					         file.transferTo(new File(fileSaveURL + fileName));
					         logger.info("文件上传成功");
					         //写入到数据库 将歌曲名等信息
					         result.put("code", 1);
				             result.put("msg", "文件上传成功！");	 
			            } catch (IOException e) {
			                result.put("code", -1);
			                result.put("msg", "文件上传出错，请重新上传！");
			                e.printStackTrace();
			            }
			        } else {
			            result.put("code", -1);
			            result.put("msg", "未获取到有效的文件信息，请重新上传!");
			        }
			        return result;
			    }
			
			
			 /**
		     * 获取文件上传进度
		     * @param request
		     * @return
		     */
		   /* @RequestMapping("/getUploadProgress")
		    @ResponseBody
		    public ProgressEntity getUploadProgress(HttpServletRequest request){
		        return (ProgressEntity) request.getSession().getAttribute("uploadStatus");

		    }*/
			
}
