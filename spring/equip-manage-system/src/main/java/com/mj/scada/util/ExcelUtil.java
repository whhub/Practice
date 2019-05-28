package com.mj.scada.util;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-09 14:40
 */
public class ExcelUtil
{
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook wb)
    {
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null)
        {
            wb = new HSSFWorkbook();
        }
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for (int i = 0; i < title.length; i++)
        {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for (int i = 0; i < values.length; i++)
        {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++)
            {
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

    //创建表头(包括风格)
    public static void createTitle(HSSFWorkbook workbook, HSSFSheet sheet, List<String> titles)
    {
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
//        sheet.setColumnWidth(1, 12 * 256);
//        sheet.setColumnWidth(3, 17 * 256);


        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        short size=1;
        font.setBoldweight(size);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFCell cell;
        for (int i=0;i<titles.size();i++)
        {
            cell = row.createCell(i);
            cell.setCellValue(titles.get(i));
            cell.setCellStyle(style);
        }
    }

    //生成excel文件
    public static void buildExcelFile(String filename, HSSFWorkbook workbook) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    //浏览器下载excel
    public static void buildExcelDocument(String filename, HSSFWorkbook workbook, HttpServletResponse response) throws Exception
    {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
