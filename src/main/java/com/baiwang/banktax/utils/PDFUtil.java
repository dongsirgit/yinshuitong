/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.utils;

import java.io.FileOutputStream;

import com.baiwang.banktax.utils.file.FileUtil;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

/**
  * @ClassName: PDFUtil
  * @Description: 生成PDF报告
  * @author ldm
  * @date 2015年12月22日 下午5:12:50
  */
public class PDFUtil {
	
public static String generatePDF(Long uid) throws Exception {
		
		BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

		// 新建document对象 第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
		Document document = new Document(PageSize.A4, 36, 36, 36, 36);

		// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
//		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:\\征信报告.pdf"));
		String name4save = FileUtil.getUpFileName(uid, "pdf");
		String dstFilePathAbs = FileUtil.getUpDirPath()[0] + name4save;
		String dstFilePathRel = FileUtil.getUpDirPath()[1] + name4save;
		System.out.println("生成PDF文件位置："+dstFilePathAbs);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dstFilePathAbs));

		// 打开文件
		document.open();

		// 标题
		document.addTitle("征信报告");

		// 作者
		document.addAuthor("百望金融");

		//创建文档标题
		Paragraph title = new Paragraph();
		// 设置页面格式
		title.setSpacingBefore(8);
		title.setSpacingAfter(2);
		title.setAlignment(1);
		// 设置PDF标题
		title.add(new Chunk("企 业 贷 款 报 告", new Font(bf, 16, Font.BOLD)));
		document.add(title);
		Paragraph title0 = new Paragraph("报告说明", new Font(bf, 12, Font.BOLD));
		Paragraph title1 = new Paragraph("一、企业基本信息",  new Font(bf, 12, Font.BOLD));
		Paragraph title2 = new Paragraph("二、司法信息",  new Font(bf, 12, Font.BOLD));
		Paragraph title3 = new Paragraph("三、税务数据",  new Font(bf, 12, Font.BOLD));
		Paragraph title4 = new Paragraph("四、发票数据（购货单位）",  new Font(bf, 12, Font.BOLD));
		Paragraph title5 = new Paragraph("五、违法违章",  new Font(bf, 12, Font.BOLD));
		Paragraph title6 = new Paragraph("六、 稽查信息",  new Font(bf, 12, Font.BOLD));

		// 新建段落
		Paragraph para0 = new Paragraph(
				"1．本报告由百望股份百望金融事业部出具，依据截止报告时间企业征信系统记录的信息生成。除征信中心标注外，报告中的信息均由相关报数机构和信息主体提供，征信中心不保证其真实性和准确性，但承诺在信息整合、汇总、展示的全过程中保持客观、中立的地位。\n"
				+ "2．本报告中的身份信息、主要出资人信息、高管人员信息来源于信息主体在中国人民银行各分支机构办理贷款卡业务时所提供的相关资料。\n"
				+ "3．如无特别说明，本报告中的金额类数据项单位均为万元。\n"
				+ "4．如无特别说明，本报告中的金额类汇总数据项均为人民币计价。外币折人民币的计算依据国家外汇管理局当月公布的各种货币对美元折算率表。\n"
				+ "5．如信息记录斜体展示，则说明信息主体对此条记录存在异议。\n"
				+ "6．报数机构说明是报数机构对报告中的信息记录或对信息主体所作的补充说明。\n"
				+ "7．征信中心标注是征信中心对报告中的信息记录或对信息主体所作的说明。\n"
				+ "8．信息主体声明是信息主体对报数机构提供的信息记录所作的简要说明。\n"
				+ "9．信息主体有权对本报告中的内容提出异议。如有异议，可联系报告机构。\n"
				+ "10．本报告向合作单位提供，可作为金融机构参考的的授信依据，请妥善保管。\n"
				+ "11．更多咨询，请致电全国客户服务热线400-066-5858。\n"
				,new Font(bf, 8, Font.NORMAL));
		Paragraph para1 = new Paragraph(
				"1.1 基本信息\n"
				+ "企业名称：风轻云淡\n"
				+ "法定代表人：\n"
				+ "注册资金：\n"
				+ "成立日期：\n"
				+ "通信地址：\n"
				+ "联系电话：\n"
				+ "电子邮箱：\n"
				+ "注册号/统一社会信用代码：\n"
				+ "税务登记代码：\n"
				+ "组织机构代码：\n"
				+ "经营状态：\n"
				+ "企业类型：\n"
				+ "经营范围：\n"
				+ "住所：\n"
				+ "登记机关：\n"
				+ "营业时间起：\n"
				+ "营业时间止：\n"
				+ "1.2 股东信息\n"
				+ "股东姓名：\n"
				+ "股东类型：\n"
				+ "身份证（营业代码）：\n"
				+ "认缴出资额：\n"
				+ "认缴时间：\n"
				+ "实缴出资额：\n"
				+ "实缴时间：\n"
				+ "1.3 主要成员\n"
				+ "姓名：\n"
				+ "职务：\n"
				+ "身份类型：\n"
				+ "身份证号：\n"
				+ "固定电话：\n"
				+ "移动电话：\n"
				+ "1.4 分支机构\n"
				+ "名称：\n"
				+ "注册号：\n"
				+ "成立日期：\n"
				+ "通信地址：\n"
				+ "联系电话：\n"
				+ "1.5 变更记录\n"
				+ "变更事项：\n"
				+ "变更时间：\n"
				+ "1.6 经营异常\n"
				+ "书号：\n"
				+ "记录时间：\n"
				+ "类型：\n"
				+ "详情：\n"
				+ "1.7 行政处罚\n"
				+ "书号：\n"
				+ "记录时间：\n"
				+ "类型：\n"
				+ "详情：\n"
				+ "1.8 严重违法\n"
				+ "书号：\n"
				+ "记录时间：\n"
				+ "类型：\n"
				+ "详情：\n"
				+ "1.9 抽查检查\n"
				+ "检查类型：\n"
				+ "检查日期：\n"
				+ "检查结果：\n"
				,new Font(bf, 8, Font.NORMAL));
		Paragraph para2 = new Paragraph(
				"2.1 失信记录\n"
				+ "文号：\n"
				+ "立案时间：\n"
				+ "失效文书确定的义务：\n"
				+ "履行情况：\n"
				+ "2.2 劳动仲裁记录：\n"
				+ "文号：\n"
				+ "立案时间：\n"
				+ "仲裁确定的义务：\n"
				+ "履行情况：\n"
				+ "2.3 法院判决记录\n"
				+ "文号：\n"
				+ "立案时间：\n"
				+ "判决确定的义务：\n"
				+ "履行情况：\n"
				,new Font(bf, 8, Font.NORMAL));
		Paragraph para3 = new Paragraph(
				"3.1 增值税\n"
				+ "上上年增值税税额：\n"
				+ "上年增值税税额：\n"
				+ "上年比上上年增长情况：\n"
				+ "本年度增值税税额：\n"
				+ "一月：\n"
				+ "二月：\n"
				+ "....\n"
				+ "3.2营业税\n"
				+ "上上年营业税税额：\n"
				+ "上年营业税税额：\n"
				+ "上年比上上年增长情况：\n"
				+ "本年度营业税税额：\n"
				+ "一月：\n"
				+ "二月：\n"
				+ "....\n"
				+ "3.3 所得税\n"
				+ "上上年所得税税额：\n"
				+ "上年所得税税额：\n"
				+ "上年比上上年增长情况：\n"
				+ "本年度所得税税额：\n"
				+ "一月：\n"
				+ "二月：\n"
				+ "....\n"
				+ "3.4 企业资产\n"
				+ "上上年资产额：\n"
				+ "上年资产额：\n"
				+ "上年比上上年增长情况：\n"
				+ "本年度资产额：\n"
				+ "一月：\n"
				+ "二月：\n"
				+ "....\n"
				+ "3.4 企业利润\n"
				+ "上上年利润额：\n"
				+ "上年利润额：\n"
				+ "上年比上上年增长情况：\n"
				+ "本年度利润额：\n"
				+ "一月：\n"
				+ "二月：\n"
				+ "....\n"
				+ "3.4 企业流动资金余额\n"
				+ "上上年资金余额：\n"
				+ "上年资金余额：\n"
				+ "本年度资金余额：\n"
				+ "一月：\n"
				+ "二月：\n"
				+ "....\n"
				,new Font(bf, 8, Font.NORMAL));
		
		Paragraph para4 = new Paragraph(
				"企业名称：\n"
				+ "注册号：\n"
				+ "法定代表人：\n"
				+ "成立日期：\n"
				+ "联系电话：\n"
				,new Font(bf, 8, Font.NORMAL));
		Paragraph para5 = new Paragraph(
				"登记日期：\n"
				+ "违法违章事实：\n"
				+ "违法违章手段：\n"
				+ "违法违章状态：\n"
				+ "限改状态：\n"
				,new Font(bf, 8, Font.NORMAL));
		Paragraph para6 = new Paragraph(
				"案件编号：\n"
				+ "案源登记日期：\n"
				+ "主要违法：\n"
				+ "违章类型：\n"
				+ "稽查类型：\n"
				+ "稽查状态：\n"
				+ "处理处罚决定：\n"
				,new Font(bf, 8, Font.NORMAL));
		Paragraph para7 = new Paragraph(
				"——————————————————————————————————————————————————————————\n"
				+ "政策指导：国务院办公厅、国务院社会信用体系建设部际联席会 | 媒体支持：共青团中央   中国青年网人物频道\n"
				+ "主管部门：国务院征信业监督管理部门 | 信用监管：中国人民银行、全国性信用协会—中国信用协会\n"
				+ "指导文件：国办(2014)第21号《国务院社会信用体系建设规划纲要（2014-2020年）》通知\n"
				+ "国办(2007)第17号《国务院办公厅关于社会信用体系建设的若干意见》通知\n"
				+ "国务院办公厅《征信业管理条例》(中华人民共和国国务院令第631号)\n"
				,new Font(bf, 8, Font.NORMAL));
		
		
		document.add(title0);
		document.add(para0);
		document.add(title1);
		document.add(para1);
		document.add(title2);
		document.add(para2);
		document.add(title3);
		document.add(para3);
		document.add(title4);
		document.add(para4);
		document.add(title5);
		document.add(para5);
		document.add(title6);
		document.add(para6);
		document.add(para7);
		// 关闭文档
		document.close();
		
		return dstFilePathRel;
	}

//	public static void main(String[] args) {
//		try {
//			generatePDF(25);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
