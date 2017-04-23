package com.lt.shop.controller.upload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.common.controller.BaseController;
import com.common.utils.FileUtils;
import com.lt.shop.service.SysInfo;

/**
 * 
 * @ClassName: ApiUploadController
 * @Description: 文件上传
 * @author xiaoli
 * @date 2016年12月14日 下午12:53:58
 *
 */
@Controller
@Scope("prototype")
public class UploadController extends BaseController {
	
	String imgroot = SysInfo.IMA_ROOT;
	String imgdomain = "";//SysInfo.IMG_DOMAIN;

	@ResponseBody
	@RequestMapping(value = "/admin/upload", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String uploadMultipart(HttpServletRequest request,HttpServletResponse response)  {
		List<MultipartFile> mfiles= new ArrayList<MultipartFile>();
		 //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        int i =0;
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                	mfiles.add(file);
                	i++;
                }
            }
        }
		String uid = request.getParameter("uid");//参数
		if (mfiles == null || mfiles.size() == 0)
			return resp(2, "文件不能为空");
		imgroot = imgroot + "i/"+uid+"/temp/";
		imgdomain = imgdomain + "i/"+uid+"/temp/";
		FileUtils.createFolder(imgroot);
		List<String> list = new ArrayList<String>();
		String fnames = "";
		for (MultipartFile file : mfiles) {
			// 取得当前上传文件的文件名称
			String myFileName = file.getOriginalFilename();
			// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
			if (myFileName.trim() != "") {
				// 文件名
				String fileName = file.getOriginalFilename();
				String ext = FileUtils.getFileExt(fileName);
				String newFilename = System.currentTimeMillis() + "." + ext;
				// 定义上传路径
				String path = imgroot + newFilename;
				File localFile = new File(path);
				try {
					file.transferTo(localFile);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
				list.add(joinUrl(imgdomain, newFilename));
			}

		}
		if(list.size()==0){
			return resp(2,"上传失败");
		}
		return resp(0, list);
	}

	/**
	 * 
	 * @Title: joinName @Description: 拼接url @return String @throws
	 */
	private String joinUrl(String domain, String uri) {
		if (!domain.endsWith("/"))
			domain = domain + "/";
		return domain + uri;
	}

}
