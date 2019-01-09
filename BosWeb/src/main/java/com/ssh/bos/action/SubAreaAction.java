package com.ssh.bos.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.bos.action.base.BaseAction;
import com.ssh.bos.entity.Region;
import com.ssh.bos.entity.SubArea;
import com.ssh.bos.service.SubAreaService;
import com.ssh.bos.utils.FileUtils;

/**
 * 分区Action
 * 
 * @author slzhang
 */
@Controller
@Scope("prototype")
public class SubAreaAction extends BaseAction<SubArea> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SubAreaService subAreaService;

	/**
	 * 添加分区
	 * 
	 * @return 分区列表
	 */
	public String addSubArea() {
		System.out.print(model);
		subAreaService.saveSubArea(model);
		return "list";
	}

	/**
	 * 分页查询
	 * 
	 * @return none
	 */
	public String queryPage() {
		DetachedCriteria dc = pageBean.getDc();
		// 动态添加过滤条件
		String addressKey = model.getAddressKey();
		if (StringUtils.isNotBlank(addressKey)) {
			// 根据关键字模糊查询
			dc.add(Restrictions.like("addressKey", "%" + addressKey + "%"));
		}
		Region region = model.getRegion();
		Boolean b = (region != null && (StringUtils.isNotBlank(region.getProvince())
				|| StringUtils.isNotBlank(region.getCity()) || StringUtils.isNotBlank(region.getDistrict())));
		if (b) {
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			dc.createAlias("region", "r");
			if (StringUtils.isNotBlank(province)) {
				dc.add(Restrictions.like("r.province", "%" + province + "%"));
			}
			if (StringUtils.isNotBlank(city)) {
				dc.add(Restrictions.like("r.city", "%" + city + "%"));
			}
			if (StringUtils.isNotBlank(district)) {
				dc.add(Restrictions.like("r.district", "%" + district + "%"));
			}
		}
		pageBean.setDc(dc);
		subAreaService.queryPage(pageBean);
		// 排除不需要传送到页面的属性，将数据转换为json对象
		object2Json(pageBean, new String[] { "currentPage", "pageSize", "dc", "decidedZone", "subAreas" });
		return "none";
	}

	/**
	 * 导出数据到Excel表格中
	 * 
	 * @return none
	 */
	public String exportXls() {
		// 查询分区列表
		List<SubArea> subAreaList = subAreaService.findAll();
		// 创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建工作表
		HSSFSheet sheet = workbook.createSheet("分区数据");
		// 设置指定列宽
		sheet.setColumnWidth(0, 10000);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(5, 6000);
		sheet.setColumnWidth(6, 6000);
		// 创建标题行
		HSSFRow headRow = sheet.createRow(0);
		headRow.setHeightInPoints((short) 30);
		// 获取标题样式
		HSSFCellStyle headStyle = getHeadStyle(workbook);
		// 设置标题信息
		String[] headArr = { "分区编码", "关键字", "起始号", "终止号", "单双号", "位置", "省市区" };
		for (int i = 0; i < 7; i++) {
			headRow.createCell(i).setCellValue(headArr[i]);
			// 设置标题样式
			headRow.getCell(i).setCellStyle(headStyle);
		}

		// 获取数据样式
		HSSFCellStyle dataStyle = getDataStyle(workbook);
		// 遍历集合
		for (SubArea subArea : subAreaList) {
			String[] dataArr = { subArea.getId(), subArea.getAddressKey(), subArea.getStartNum(), subArea.getEndNum(),
					subArea.getSingle(), subArea.getPosition(), subArea.getRegion().getName() };
			// 创建数据行
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.setHeightInPoints((short) 20);
			// 创建单元格
			for (int i = 0; i < 7; i++) {
				dataRow.createCell(i).setCellValue(dataArr[i]);
				// 设置数据样式
				dataRow.getCell(i).setCellStyle(dataStyle);
			}
		}

		// 获取文件类型
		String fileName = "分区数据.xls";
		String contentType = ServletActionContext.getServletContext().getMimeType(fileName);
		ServletActionContext.getResponse().setContentType(contentType);
		// 获取客户端浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		fileName = FileUtils.encodeDownloadFilename(fileName, agent);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + fileName);

		// 使用输出流下载文件
		ServletOutputStream outputStream;
		try {
			outputStream = ServletActionContext.getResponse().getOutputStream();
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "none";
	}
	
	/**
	 * 查询未分配的分区列表
	 * 
	 * @return none
	 */
	public String getUnallocatedSubAreaListByAjax() {
		List<SubArea> subAreas = subAreaService.findUnallocatedSubAreaList();
		object2Json(subAreas, new String[] {"region", "decidedZone"});
		return "none";
	}
	
	/**
	 * 获取标题样式
	 * 
	 * @return
	 */
	public HSSFCellStyle getHeadStyle(HSSFWorkbook workbook) {
		// 创建单元格样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		// 设置内容水平垂直居中
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		// 创建字体
		HSSFFont font = workbook.createFont();
		// 设置字体
		font.setBold(true); // 加粗
		font.setFontHeightInPoints((short) 12); // 字号
		font.setFontName("微软雅黑");
		// 将字体加入样式
		cellStyle.setFont(font);

		// 返回样式
		return cellStyle;
	}

	/**
	 * 获取数据样式
	 * 
	 * @return
	 */
	public HSSFCellStyle getDataStyle(HSSFWorkbook workbook) {
		// 创建单元格样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		// 设置内容水平垂直居中
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		// 创建字体
		HSSFFont font = workbook.createFont();
		// 设置字体
		// font.setBold(true); // 加粗
		font.setFontHeightInPoints((short) 10); // 字号
		font.setFontName("微软雅黑");
		// 将字体加入样式
		cellStyle.setFont(font);

		// 返回样式
		return cellStyle;
	}

}
