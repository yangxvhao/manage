package com.credit.utils;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
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
}
