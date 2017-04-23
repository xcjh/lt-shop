package com.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Expand;

/**
 * 文件工具类
 * 
 * @author xiaokx 2010-1-6下午02:14:41
 */
public class FileUtils {

	private FileUtils() {
	}

	/**
	 * 是否是允许上传文件
	 * 
	 * @param ex
	 * @return
	 */
	public static boolean isAllowUp(String logoFileName) {
		logoFileName = logoFileName.toLowerCase();
		String allowTYpe = "gif,jpg,bmp,swf,png,rar,doc,docx,xls,xlsx,pdf";
		if (!logoFileName.trim().equals("") && logoFileName.length() > 0) {
			String ex = logoFileName.substring(logoFileName.lastIndexOf(".") + 1, logoFileName.length());
			return allowTYpe.toString().indexOf(ex) >= 0;
		} else {
			return false;
		}
	}

	/**
	 * 把内容写入文件
	 * 
	 * @param filePath
	 * @param fileContent
	 */
	public static void write(String filePath, String fileContent) {

		try {
			FileOutputStream fo = new FileOutputStream(filePath);
			OutputStreamWriter out = new OutputStreamWriter(fo, "UTF-8");

			out.write(fileContent);

			out.close();
		} catch (IOException ex) {

			System.err.println("Create File Error!");
			ex.printStackTrace();
		}
	}

	/**
	 * 把内容叠加写入文件
	 * 
	 * @param filePath
	 * @param fileContent
	 */
	public static void writeAppend1(String filePath, String fileContent) {
		File logFile = new File(filePath);
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
			buf.append(fileContent);
			buf.newLine();
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把内容以UTF-8编码叠加写入文件 
	 * @param filePath  "E:\\user_sql.txt"
	 * @param fileContent
	 */
	public static void writeAppend(String filePath, String fileContent) {
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filePath,true);
			Writer out = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter buf = new BufferedWriter(out);
			buf.append(fileContent);
			buf.newLine();
			buf.close();
			out.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 读取文件内容 默认是UTF-8编码
	 * 
	 * @param filePath
	 * @return
	 */
	public static String read(String filePath, String code) {
		if (code == null || code.equals("")) {
			code = "UTF-8";
		}
		String fileContent = "";
		File file = new File(filePath);
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), code);
			BufferedReader reader = new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null) {
				fileContent = fileContent + line + "\n";
			}
			read.close();
			read = null;
			reader.close();
			read = null;
		} catch (Exception ex) {
			ex.printStackTrace();
			fileContent = "";
		}
		return fileContent;
	}

	/**
	 * 删除文件或文件夹
	 * 
	 * @param filePath
	 */
	public static void delete(String filePath) {
		try {
			File file = new File(filePath);
			if (file.exists()) {
				if (file.isDirectory()) {
					org.apache.commons.io.FileUtils.deleteDirectory(file);
				} else {
					file.delete();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 删除文件
	 * @param filePath
	 */
	public static void deleteFile(String filePath) {
		try {
			File file = new File(filePath);
			if (file.exists()) {
				if (!file.isDirectory()) {
					file.delete();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean exist(String filepath) {
		File file = new File(filepath);

		return file.exists();
	}

	/**
	 * 创建文件夹
	 * 
	 * @param filePath
	 */
	public static void createFolder(String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
		} catch (Exception ex) {
			System.err.println("Make Folder Error:" + ex.getMessage());
		}
	}

	/**
	 * 得到文件的扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {

		int potPos = fileName.lastIndexOf('.') + 1;
		String type = fileName.substring(potPos, fileName.length());
		return type;
	}

	/**
	 * 通过File对象创建文件
	 * 
	 * @param file
	 * @param filePath
	 */
	public static void createFile(File file, String filePath) {
		int potPos = filePath.lastIndexOf('/') + 1;
		String folderPath = filePath.substring(0, potPos);
		createFolder(folderPath);
		FileOutputStream outputStream = null;
		FileInputStream fileInputStream = null;
		try {
			outputStream = new FileOutputStream(filePath);
			fileInputStream = new FileInputStream(file);
			byte[] by = new byte[1024];
			int c;
			while ((c = fileInputStream.read(by)) != -1) {
				outputStream.write(by, 0, c);
			}
		} catch (IOException e) {
			e.getStackTrace().toString();
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createFile(InputStream in, String filePath) {
		if (in == null)
			throw new RuntimeException("create file error: inputstream is null");
		int potPos = filePath.lastIndexOf('/') + 1;
		String folderPath = filePath.substring(0, potPos);
		createFolder(folderPath);
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(filePath);
			byte[] by = new byte[1024];
			int c;
			while ((c = in.read(by)) != -1) {
				outputStream.write(by, 0, c);
			}
		} catch (IOException e) {
			e.getStackTrace().toString();
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readFile(String resource) {
		InputStream stream = getResourceAsStream(resource);
		String content = readStreamToString(stream);
		return content;

	}

	public static InputStream getResourceAsStream(String resource) {
		String stripped = resource.startsWith("/") ? resource.substring(1) : resource;

		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			stream = classLoader.getResourceAsStream(stripped);

		}

		return stream;
	}

	public static String readStreamToString(InputStream stream) {
		String fileContent = "";

		try {
			InputStreamReader read = new InputStreamReader(stream, "utf-8");
			BufferedReader reader = new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null) {
				fileContent = fileContent + line + "\n";
			}
			read.close();
			read = null;
			reader.close();
			read = null;
		} catch (Exception ex) {
			fileContent = "";
		}
		return fileContent;
	}

	/**
	 * delete file folder
	 * 
	 * @param path
	 */
	public static void removeFile(File path) {

		if (path.isDirectory()) {
			try {
				org.apache.commons.io.FileUtils.deleteDirectory(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	* @Title: copyFile 
	* @Description:复制文件
	* @return boolean 
	* @throws
	 */
	public static boolean copyFile(String srcFile, String destFile) {
		try {
			if (FileUtils.exist(srcFile)) {
				org.apache.commons.io.FileUtils.copyFile(new File(srcFile), new File(destFile));
			}
			return true;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return false;
	}

	public static void copyFolder(String sourceFolder, String destinationFolder) {
		// System.out.println("copy " + sourceFolder + " to " +
		// destinationFolder);
		try {
			File sourceF = new File(sourceFolder);
			if (sourceF.exists())
				org.apache.commons.io.FileUtils.copyDirectory(new File(sourceFolder), new File(destinationFolder));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("copy file error");
		}

	}

	public static void unZip(String zipPath, String targetFolder, boolean cleanZip) {
		File folderFile = new File(targetFolder);
		File zipFile = new File(zipPath);
		Project prj = new Project();
		Expand expand = new Expand();
		expand.setEncoding("UTF-8");
		expand.setProject(prj);
		expand.setSrc(zipFile);
		expand.setOverwrite(true);
		expand.setDest(folderFile);
		expand.execute();

		if (cleanZip) {
			// 清除zip包
			Delete delete = new Delete();
			delete.setProject(prj);
			delete.setDir(zipFile);
			delete.execute();
		}
	}

	/**
	 * InputStream 还原成File
	 * 
	 * @param file_name
	 * @param is
	 * @return
	 */
	public static File FileBackInputStream(String file_name, InputStream is) {
		File file = new File(file_name);
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			byte buffer[] = new byte[4 * 1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				os.write(buffer, 0, len);

			}
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	public static void main(String arg[]) {
		// Random rnd = new Random();
		// String content = FileUtils.read("E:\\user.txt", "UTF-8");
		// String[] arr = content.split("\\\n");
		// System.out.println(arr.length);
		// StringBuffer buffer = new StringBuffer();
		// for (int i = 0; i < arr.length; i++) {
		// int lvid = (rnd.nextInt(4) + 1);
		// int sex = rnd.nextInt(2);
		// String sql = "insert into es_member
		// (parentid,lv_id,uname,password,name,sex,advance,point,is_agent,logincount,is_cheked,mp,identity,nickname,recommend_point_state,info_full,logintype)
		// "
		// + "values (0,"
		// + lvid
		// + ",'"
		// + arr[i]
		// + "','0','"
		// + arr[i]
		// + "'," + sex + ",0,0,0,0,0,0,0,'" + arr[i] + "',0,0,0);";
		// buffer.append(sql);
		// if (i % 5000 == 0) {
		// FileUtils.write("E:\\user\\user_sql" + i + ".txt",
		// buffer.toString());
		// buffer = new StringBuffer();
		// }
		// }
		// FileUtils.write("E:\\user\\user_sql.txt", buffer.toString());
		// System.out.println("finish..");
		String str = FileUtils.read("D:\\job.txt","UTF-8");
		List<String> list = new ArrayList<String>();
		String[] arr = str.split("\n");
		for (String string : arr) {
			if(!list.contains(string)){
				list.add(string);
				String py = Pinyin4jUtils.toPinyin(string);
				String index = py.substring(0,1).toUpperCase();
				String sql = "insert into lb_job(name,name_pinyin,name_initials,note,add_time) values('"+string+"','"+py+"','"+index+"','"+string+"',1489736414078);\n";
				FileUtils.writeAppend("D:\\user_sql.txt", sql);
			}
		}
		System.out.println(arr.length);
		System.out.println("finish..");
	}

}
