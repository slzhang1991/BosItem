package com.ssh.bos.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.bos.action.base.BaseAction;
import com.ssh.bos.entity.Region;
import com.ssh.bos.service.RegionService;
import com.ssh.bos.utils.PinYin4jUtils;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 区域Action
 * 
 * @author slzhang
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private RegionService regionService;
	// 属性驱动接收文件
	private File regionFile;
	// 属性驱动 接收查询参数
	private String q;

	/**
	 * 区域信息批量导入
	 * 
	 * 工作薄（HSSFWorkbook）、工作表（HSSFSheet）、行（Row）、单元格（Cell）
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return none
	 */
	public String importXls() throws FileNotFoundException, IOException {
		// 创建区域对象集合
		Set<Region> regions = new HashSet<Region>();
		// 创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
		// 获得指定工作表
		HSSFSheet hssfSheet = workbook.getSheetAt(0);
		// 遍历行（一行对应一个对象（数据库中的一条记录））
		for (Row row : hssfSheet) {
			// 跳过首行
			if (row.getRowNum() == 0) {
				continue;
			}
			// 属性赋值
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			// 创建一个区域对象
			Region region = new Region(id, province, city, district, postcode, null, null, null);
			province = province.substring(0, province.length() - 1);
			city = city.substring(0, city.length() - 1);
			district = district.substring(0, district.length() - 1);
			String info = province + city + district;
			String[] headByString = PinYin4jUtils.getHeadByString(info);
			// 简码
			String shortCode = StringUtils.join(headByString);
			// 城市编码
			String cityCode = PinYin4jUtils.hanziToPinyin(city, "");
			// 设置简码和城市编码
			region.setShortCode(shortCode);
			region.setCityCode(cityCode);
			// 将区域对象添加到集合
			regions.add(region);
		}
		regionService.saveBatch(regions);
		return "none";
	}

	/**
	 * 区域列表（分页查询）
	 * 
	 * @throws IOException
	 * @return none
	 */
	public String queryPage() throws IOException {
		// 获取离线查询对象
		DetachedCriteria dc = pageBean.getDc();
		pageBean.setDc(dc);
		// 查询数据
		regionService.queryPage(pageBean);
		// 排除不需要传送到页面的字段（排除subAreas解决死循环问题）
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "currentPage", "pageSize", "dc", "subAreas" });
		// 将数据转换为json对象
		String json = JSONObject.fromObject(pageBean, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return "none";
	}

	/**
	 * 添加分区时选择所属的区域
	 * 
	 * 查询所有区域，写回json数据
	 * 
	 * @return none
	 */
	public String getRegionListByAjax() {
		List<Region> regionList = null;
		// 判断传入的参数是否为空
		if (StringUtils.isNotBlank(q)) {
			regionList = regionService.findListByQ(q);
		} else {
			regionList = regionService.findAll();
		}
		// 将List集合转换为json数据
		object2Json(regionList, new String[] { "subAreas" });
		return "none";
	}

	/**
	 * setter和getter方法
	 */
	public File getRegionFile() {
		return regionFile;
	}

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

}
