package cn.com.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;

import pojo.UserInfo;

import service.IUserService;

@Controller
public class TestController  {
	@Resource
	private IUserService userService;
   
	@RequestMapping("/test.action")
	public String getUser(Model model){
		List<UserInfo> list=userService.selectUserInfoList();
		model.addAttribute("users", list);
	   return "index";
   }
	@RequestMapping("/getJson.action")
	@ResponseBody
	public void myJson(HttpServletResponse response) {
		List<UserInfo> list = userService.selectUserInfoList();
		String json = null;
		Gson gson = new Gson();
		json = gson.toJson(list);
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			ServletOutputStream out = response.getOutputStream();
			byte[] reString = json.getBytes("utf-8");
			out.write(reString);
			out.flush();
			out.close();
		} catch (Exception e) {

		}
	}

	@RequestMapping(value = "/getDownload.action")
	public void myDownload(String filename, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("filename:" + filename);
		try {
			byte[] b = filename.getBytes("iso-8859-1");
			filename = new String(b, "utf-8");
			System.out.println(filename);
			String filepath = request.getRealPath("download/" + filename);
			System.out.println("filepath:" + filepath);
			File tempFile = new File(filepath);
			if (tempFile.exists()) {
				request.setCharacterEncoding("utf-8");
				String userAgent = request.getHeader("User-Agent");
				System.out.println("User-Agent:" + userAgent);
				response.setHeader(
						"Content-disposition",
						"attachment;filename*="
								+ URLEncoder.encode(filename, "utf-8"));
				// 设置文件输出类型
				response.setContentType("application/octet-stream; charset=utf-8");
				// //设置输出长度
				response.setHeader("Content-Length",
						String.valueOf(tempFile.length()));
				InputStream in = new BufferedInputStream(new FileInputStream(
						tempFile));
				FileCopyUtils.copy(in, response.getOutputStream());
				in.close();

			}

		} catch (Exception e) {

		}
	}

	@RequestMapping("/upload.action")
	public String myUpload(String testid, HttpServletRequest request) {
		String res = "error";
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartresolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartresolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();
			boolean flag=true;
			try{
				while(iter.hasNext()){
					System.out.println("ok");
					MultipartFile file=multiRequest.getFile(iter.next().toString());
					if(file!=null){
					String path=request.getRealPath("upload")+"\\"+file.getOriginalFilename();
					System.out.println("path:"+path);
					file.transferTo(new File(path));
					}
				}
				System.out.println("testid:"+testid);
			}catch(Exception e){
				System.out.println("上传文件异常！");
				flag=false;
			}
			if(flag){
				res="a";
			}
		}
		return res;
	}
}
