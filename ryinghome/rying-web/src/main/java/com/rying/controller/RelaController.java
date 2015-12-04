package com.rying.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rying.entity.InvestorRelations;
import com.rying.service.IInvestorRelationsService;
import com.rying.util.PropertiesUtil;

@Controller
@RequestMapping("/rela")
public class RelaController {
	private static final String UPLOAD_PATH_BASE = PropertiesUtil.get("upload_path") + "/";
	@Resource
	private IInvestorRelationsService investorRelationsService;

	@RequestMapping("/query")
	@ResponseBody
	public List<InvestorRelations> query(String d) {
		try {
			Date date = new SimpleDateFormat("yyyy").parse(d);
			return investorRelationsService.queryList(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/download")
	public void download(long id, boolean show, HttpServletResponse response) {
		InvestorRelations ir = this.investorRelationsService.getInvestorRelationsById(id);
		String filepath = ir.getFilePath();
		String fullName = UPLOAD_PATH_BASE + filepath;

		try {
			response.setCharacterEncoding("utf-8");
			if (!show) {
				response.setContentType("application/octet-stream");
				// 设定输出文件头
				String fileEnd = fullName.substring(fullName.lastIndexOf("."));
				response.setHeader("Content-disposition",
						"attachment; filename=" + new String((ir.getTitle() + fileEnd).getBytes("utf-8"), "ISO8859-1"));
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
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
}
