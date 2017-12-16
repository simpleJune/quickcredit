package com.free.platform.paginator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.free.platform.paginator.domain.PageRequest;
import com.free.platform.paginator.domain.PageResponse;
import com.free.platform.paginator.exception.BuilderExportFileException;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class PageExport {
	
	
	public static void buildFile(String fileName,String tempFile,int maxLimit,PageRequest page,PageSearchHandler handler) throws BuilderExportFileException {
		RandomAccessFile raf = null;
		try{
			raf = new RandomAccessFile(fileName, "rw");
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
			StringTemplateLoader stringLoader = new StringTemplateLoader();
			File file = new File(tempFile);
			BufferedReader br = null;
			StringBuffer strTemplate = new StringBuffer();
			StringBuffer endContent = new StringBuffer();
			try{
				br = new BufferedReader(new FileReader(file));
				String line = null;
				boolean markStart = false,markEnd = false;
				while((line = br.readLine())!=null){
					if(line.contains("<freemarker>")){
						markStart = true;
					}else if(line.contains("</freemarker>")){
						markEnd = true;
					}else{
						if(markStart&&!markEnd){
							strTemplate.append(line);
						}else if(!markStart&&!markEnd){
							raf.write(line.getBytes());
						}else{
							endContent.append(line);
						}
					}
				}
			}finally{
				br.close();
			}
			stringLoader.putTemplate("template", strTemplate.toString());
			cfg.setTemplateLoader(stringLoader);
			Template template = cfg.getTemplate("template", "utf-8");
			int pageNo = 1;
			page.setPage(pageNo);
			page.setRows(maxLimit);
			page.setContainsTotalCount(true);
			PageResponse datas = handler.getData(page);
			int total = datas.getTotal();
			double totalPages = new Double(total)/maxLimit;
			if(total>0){
				raf.write(getContent(datas,template).getBytes());
			}
			while(pageNo<totalPages){
				page.setContainsTotalCount(false);
				page.setPage(++pageNo);
				datas = handler.getData(page);
				raf.write(getContent(datas,template).getBytes());
			}
			raf.write(endContent.toString().getBytes());
		}catch(Exception e){
			throw new BuilderExportFileException("构建导出文件失败!",e);
		}finally{
			try {
				if(raf!=null)raf.close();
			} catch (IOException e) {
				throw new BuilderExportFileException("文件close失败!",e);
			}
		}
		
	}
	
	
	
	private static String getContent(Object obj,Template template) throws Exception {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("datas",obj);
		StringWriter writer = new StringWriter();
		template.process(root, writer);
		return writer.toString();
	}
	

}
