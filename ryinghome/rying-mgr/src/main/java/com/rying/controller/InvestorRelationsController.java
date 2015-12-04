package com.rying.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.rying.easyui.model.Datagrid;
import com.rying.easyui.model.JsonMsg;
import com.rying.entity.InvestorRelations;
import com.rying.entity.SysUser;
import com.rying.exception.FileTypeNotAllowException;
import com.rying.service.IInvestorRelationsService;
import com.rying.util.PropertiesUtil;

@Controller
@RequestMapping("/ir")
@SessionAttributes({ "currentUser" })
public class InvestorRelationsController {
	// 文件保存目录路径
	private static final String UPLOAD_PATH_BASE = PropertiesUtil.get("upload_path") + "/";

	private static final String[] ALLOW_TYPE = new String[] { "png", "ppt", "pdf", "exe" };
	@Resource
	private IInvestorRelationsService investorRelationsService;

	@RequestMapping("/ir")
	public String investorRelations() {
		return "ir/ir";
	}

	@RequestMapping("/datagrid")
	@ResponseBody
	public Datagrid<InvestorRelations> datagrid(int page, int rows, String years) {
		Date date = null;
		try {
			date = years == null? null : new SimpleDateFormat("yyyy").parse(years);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Long count = this.investorRelationsService.countAll(date);
		if (count == 0) {
			return new Datagrid<InvestorRelations>(0, Collections.<InvestorRelations> emptyList());
		}
		List<InvestorRelations> pageList = this.investorRelationsService.pageList(page, rows, date);
		return new Datagrid<InvestorRelations>(count.intValue(), pageList);
	}

	@RequestMapping("/addIr")
	@ResponseBody
	public JsonMsg addIr(String title, MultipartFile file, @ModelAttribute("currentUser") SysUser sysUser) {
		try {
			String path = null;
			if (!file.isEmpty()) {
				CommonsMultipartFile _file = (CommonsMultipartFile) file;
				try {
					path = this.upload(_file);
				} catch (FileTypeNotAllowException e) {
					return new JsonMsg(false, "文件格式不正确");
				} catch (Exception e) {
					return new JsonMsg(false, "文件上传失败");
				}
			}
			InvestorRelations ir = new InvestorRelations();
			ir.setTitle(title);
			ir.setFilePath(path);
			ir.setCreatetime(new Date());
			ir.setPublishUser(sysUser);

			this.investorRelationsService.addInvestorRelations(ir);
			return new JsonMsg(true, "添加成功");
		} catch (Exception e) {
			return new JsonMsg(false, "添加失败，未知异常");
		}
	}

	@RequestMapping("/updateIr")
	@ResponseBody
	public JsonMsg updateIr(long id, String title, MultipartFile file) {
		try {
			String filepath = null;
			if (!file.isEmpty()) {
				try {
					filepath = this.upload((CommonsMultipartFile) file);
				} catch (FileTypeNotAllowException e) {
					return new JsonMsg(false, "文件格式不正确");
				} catch (Exception e) {
					return new JsonMsg(false, "文件上传失败");
				}
			}

			this.investorRelationsService.updateInvestorRelations(id, title, filepath);
			return new JsonMsg(true, "修改成功");
		} catch (Exception e) {
			return new JsonMsg(false, "修改失败，未知异常");
		}
	}

	@RequestMapping("/deleteIr")
	@ResponseBody
	public JsonMsg deleteIr(long id) {
		try {
			this.investorRelationsService.deleteIrById(id);
			return new JsonMsg(true, "删除成功");
		} catch (Exception e) {
			return new JsonMsg(false, "删除失败");
		}

	}

	@RequestMapping("/downloadIr")
	public void downloadIr(long id, HttpServletResponse response) {
		InvestorRelations ir = this.investorRelationsService.getInvestorRelationsById(id);
		String filepath = ir.getFilePath();
		String fullName = UPLOAD_PATH_BASE + filepath;

		try {
			InputStream inputStream = new FileInputStream(new File(fullName));

			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}

			os.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String upload(CommonsMultipartFile file) throws FileTypeNotAllowException {
		String fileName = file.getFileItem().getName();
		// 文件后缀
		String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (!isRealType(fileEnd)) {
			throw new FileTypeNotAllowException("文件格式不正确");
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());

		String result = ymd + "/";
		String uploadPath = UPLOAD_PATH_BASE + ymd + "/";
		// 检查目录
		File uploadDir = new File(uploadPath);
		if (!uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}
		String fName = UUID.randomUUID() + "." + fileEnd;
		result += fName;

		try {
			File f = new File(uploadPath, fName);
			if (!f.exists()) {
				f.createNewFile();
			}
			file.transferTo(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private boolean isRealType(String type) {
		for (String allow : ALLOW_TYPE) {
			if (type.equalsIgnoreCase(allow)) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping("/selectHtml")
	public String selectHtml() {
		return "ir/select";
	}
}
