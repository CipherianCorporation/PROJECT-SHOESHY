package com.edu.graduationproject.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.edu.graduationproject.entity.Category;
import com.edu.graduationproject.entity.Order;
import com.edu.graduationproject.entity.Product;
import com.edu.graduationproject.entity.User;

public class ExcelExporter<T> {
    // Source:
    // https://www.codejava.net/frameworks/spring-boot/export-data-to-excel-example
    // Credit: Nam Ha Minh @ CodeJava.net

    private XSSFWorkbook wb;
    private XSSFSheet sheet;
    private List<T> list;
    private String[] headers;
    String[] userHeaders = { "ID", "Username", "Password", "Fullname","Email", "Phone", "Address", "ImageUrl", "Enabled", "Provider",
           "Verify_code", "Reset_pwd_token", "Authorities", };
    String[] categoryHeaders = { "ID", "Category Name" };
    String[] productHeaders = { "ID", "Name", "Image", "Price", "Available", "Color", "Size", "Sale off", "Sold",
            "Description", "Category", "SubCategory", "Created By", "Updated At", "Created At" };
    String[] orderHeaders = { "ID", "Address", "Create Date", "Username" };

    public ExcelExporter(List<T> list, String sheetName) {
        this.list = list;
        setHeaderByListType(list);
        wb = new XSSFWorkbook();
        sheet = wb.createSheet(sheetName);
    }

    // hàm này để kiểm tra xem type của list là gì để set header
    public void setHeaderByListType(List<T> list) {
        if (list.get(0) instanceof User) {
            this.headers = userHeaders;
        } else if (list.get(0) instanceof Category) {
            this.headers = categoryHeaders;
        } else if (list.get(0) instanceof Product) {
            this.headers = productHeaders;
        } else if (list.get(0) instanceof Order) {
            this.headers = orderHeaders;
        }
    }

    // hàm này để set sheet header
    private void writeHeaderRow() {
        Row row = sheet.createRow(0);

        CellStyle style = wb.createCellStyle();
        XSSFFont font = wb.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        // Create header cells
        if (headers != null && headers.length > 0) {
            for (int i = 0; i < headers.length; i++) {
                createCell(row, i, headers[i], style);
            }
        }
    }

    // hàm này để set data mỗi hàng
    private void writeDataRow() {
        int rowCount = 1;

        CellStyle style = wb.createCellStyle();
        XSSFFont font = wb.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (T item : list) {
            Row row = sheet.createRow(rowCount++);
            int colCount = 0;

            if (item instanceof User) {
                User user = (User) item;
                createCell(row, colCount++, user.getId(), style);
                createCell(row, colCount++, user.getUsername(), style);
                createCell(row, colCount++, user.getPassword(), style);
                createCell(row, colCount++, user.getFullname(), style);
                createCell(row, colCount++, user.getEmail(), style);
                createCell(row, colCount++, user.getPhone(), style);
                createCell(row, colCount++, user.getAddress(), style);
                createCell(row, colCount++, user.getImage_url(), style);
                createCell(row, colCount++, user.getEnabled(), style);
                createCell(row, colCount++, user.getProvider(), style);
                createCell(row, colCount++, user.getVerify_code(), style);
                createCell(row, colCount++, user.getReset_pwd_token(), style);
                createCell(row, colCount++, user.getAuthorities(), style);
            } else if (item instanceof Product) {
                Product product = (Product) item;
                createCell(row, colCount++, product.getId(), style);
                createCell(row, colCount++, product.getName(), style);
                createCell(row, colCount++, product.getImage(), style);
                createCell(row, colCount++, product.getPrice(), style);
                createCell(row, colCount++, product.getStock(), style);
                createCell(row, colCount++, product.getAvailable(), style);
                createCell(row, colCount++, product.getColor().getName(), style);
                createCell(row, colCount++, product.getSize() == null ? "" : product.getSize().getValue(), style);
                createCell(row, colCount++, product.getSale_off(), style);
                createCell(row, colCount++, product.getSold(), style);
                createCell(row, colCount++, product.getDescription() == null ? "" : product.getDescription(), style);
                createCell(row, colCount++, product.getCategory().getName(), style);
                createCell(row, colCount++, product.getSubCategory().getName(), style);
                createCell(row, colCount++, product.getUser().getUsername(), style);
                createCell(row, colCount++, DateUtils.formatDateTime(product.getUpdatedAt()), style);
                createCell(row, colCount++, DateUtils.formatDateTime(product.getCreatedAt()), style);
            } else if (item instanceof Category) {
                Category category = (Category) item;
                createCell(row, colCount++, category.getId(), style);
                createCell(row, colCount++, category.getName(), style);
            } else if (item instanceof Order) {
                Order order = (Order) item;
                createCell(row, colCount++, order.getId(), style);
                createCell(row, colCount++, order.getAddress(), style);
                createCell(row, colCount++, order.getCreatedAt(), style);
                createCell(row, colCount++, order.getUser().getUsername(), style);
            }
        }
    }

    // hàm này để tạo cell theo type của value
    private void createCell(Row row, int colCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(colCount);
        Cell cell = row.createCell(colCount);
        if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Date) {
            cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(value));
        } else if (value instanceof LocalDate) {
            cell.setCellValue(((LocalDate) value).format(DateTimeFormatter.ISO_DATE));
        } else if (value instanceof Enum) {
            cell.setCellValue("" + value);
        } else {
            cell.setCellValue("");
        }
        cell.setCellStyle(style);
    }

    public void export(HttpServletResponse resp) throws IOException {
        writeHeaderRow();
        writeDataRow();
        ServletOutputStream out = resp.getOutputStream();
        wb.write(out);
        wb.close();
        out.close();
    }
}