package com.credit.utils;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @date：08
 * @author:yangxvhao
 *
 */
public class ExcelUtils {

    private static final Logger logger= LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * @param file
     * @param beanclass 要转化的实体类
     * @param titleExist 是否有表头
     * @return
     */

    public static List<Object> ExcelForList(MultipartFile file, Class<?>  beanclass, Boolean titleExist) {
        List<Object> list = new ArrayList<Object>();
        try {
            // IO流读取文件
            InputStream input = file.getInputStream();
            // 创建文档
            Workbook wb = new HSSFWorkbook(input);
            // 得到第一张工作表
            Sheet sheet = wb.getSheetAt(0);
            int i;
            if (titleExist) {
                i = 1;
            } else {
                i = 0;
            }
            // 行的遍历
            for (; i <= sheet.getLastRowNum(); i++) {
                // 得到行
                Row row = sheet.getRow(i);
                // 单元格的遍历
                // 实例化对象
                Object object = beanclass.newInstance();

                Field[] fields = beanclass.getDeclaredFields();
                int j = 0;
                for (Field field : fields) {
                    String fieldName = field.getName();
                    String fieldType = String.valueOf(field.getType());
                    logger.info("filedType" + fieldType);
                    PropertyDescriptor pd = new PropertyDescriptor(fieldName, beanclass);
                    Method getMethod = pd.getWriteMethod();
                    Cell cell = row.getCell(j++);
                    if (!cell.equals(null)) {
                        try {
                            int type = cell.getCellType();

                            if (type == cell.CELL_TYPE_BOOLEAN) {
                                // 返回布尔类型的值
                                boolean value = cell.getBooleanCellValue();
                                getMethod.invoke(object, value);
                                logger.info(object.toString());
                                logger.info(String.valueOf(value));
                            } else if (type == cell.CELL_TYPE_NUMERIC) {
                                // 返回数值类型的值,poi读取的excel文件数字都为double类型，
                                // 根据反射获取属性类型转换成相对应的类型
                                Double d = cell.getNumericCellValue();
                                if (fieldType.contains("java.lang.Double")) {
                                    getMethod.invoke(object, d);
                                    logger.info(object.toString());
                                    logger.info(String.valueOf(d));
                                } else {
                                    int value = d.intValue();
                                    getMethod.invoke(object, new Integer(value));
                                    logger.info(object.toString());
                                    logger.info(String.valueOf(value));
                                }

                            } else if (type == cell.CELL_TYPE_STRING) {
                                // 返回字符串类型的值
                                String value = cell.getStringCellValue();
                                getMethod.invoke(object, new String(value));
                                logger.info(object.toString());
                                logger.info(String.valueOf(value));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                list.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writer(String path, String fileName,String fileType,List<String[]> list,String titleRow[]) throws IOException {
        Workbook wb = null;
        String excelPath = path+ File.separator+fileName+"."+fileType;
        File file = new File(excelPath);
        Sheet sheet =null;
        //创建工作文档对象
        if (!file.exists()) {
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook();

            } else if(fileType.equals("xlsx")) {

                wb = new XSSFWorkbook();
            } else {
                throw new FileNotFoundException("文件格式不正确");
            }
            //创建sheet对象
            sheet = (Sheet) wb.createSheet("sheet1");
            OutputStream outputStream = new FileOutputStream(excelPath);
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();

        } else {
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook();

            } else if(fileType.equals("xlsx")) {
                wb = new XSSFWorkbook();

            } else {
                throw new FileNotFoundException("文件格式不正确");
            }
        }
        //创建sheet对象
        if (sheet==null) {
            sheet = (Sheet) wb.createSheet("sheet1");
        }

        //添加表头
        Row row = sheet.createRow(0);
        for (int i=0;i<titleRow.length;i++)
        {
            Cell cell = row.createCell(i);
            cell.setCellValue(titleRow[i]);
        }

//        CellStyle style = wb.createCellStyle(); // 样式对象
//
//        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直
//        style.setAlignment(CellStyle.ALIGN_CENTER);// 水平
//        style.setWrapText(true);// 指定当单元格内容显示不下时自动换行
//
//
//        Font font = wb.createFont();
//        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//        font.setFontName("宋体");
//        font.setFontHeight((short) 280);
//        style.setFont(font);
//        // 单元格合并
//        // 四个参数分别是：起始行，起始列，结束行，结束列
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
//        sheet.autoSizeColumn(5200);
        //添加表内容
        for (int i=1;i<list.size()+1;i++) {
            row=sheet.createRow(i);
            for (int j = 0; j < list.get(i-1).length; j++) {
                Cell cell=row.createCell(j);
                cell.setCellValue(list.get(i-1)[j]);
            }
        }

        //创建文件流
        OutputStream stream = new FileOutputStream(excelPath);
        //写入数据
        wb.write(stream);
        //关闭文件流
        stream.close();
    }

    public static void main(String[] args) {
        List<String []> list=new ArrayList<String[]>();
        String [] titles=new String[]{"标题","iiiiiii","ttttttttttt"};
        String [] strings=new String[]{"11111111","22222222","33333333"};
        list.add(strings);
        String path="E:\\";
        String fileName="test";

        try {
            ExcelUtils.writer(path,fileName,"xls",list,titles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
