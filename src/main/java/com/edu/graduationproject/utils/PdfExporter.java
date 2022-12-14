package com.edu.graduationproject.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.edu.graduationproject.entity.Order;
import com.edu.graduationproject.entity.Product;
import com.edu.graduationproject.entity.User;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfExporter<T> {
    // Source:
    // https://techblogstation.com/spring-boot/export-data-to-pdf-in-spring-boot/
    // Credit: TBS, techblogstation.com

    private List<T> list;
    private String title;
    private String[] headers;
    private String[] userHeaders = { "ID", "Username", "Password", "Fullname", "Email", "Phone", "Address", "ImageUrl",
            "Enabled", "Provider",
            "Verify_code", "Reset_pwd_token", "Authorities", };
    // private String[] categoryHeaders = { "ID", "Category Name" };
    private String[] productHeaders = { "ID", "Name", "Image", "Price", "Stock", "Available", "Color", "Size",
            "Sale off",
            "Sold", "Description", "Category", "SubCategory", "Created By", "Updated At", "Created At" };
    private String[] orderHeaders = { "ID", "Address", "Create Date", "Username" };

    public PdfExporter(List<T> list, String title) throws DocumentException, IOException {
        this.list = list;
        this.title = title;
        setHeaderByListType(list);
    }

    // hàm này để kiểm tra xem type của list là gì để set header
    public void setHeaderByListType(List<T> list) {
        if (list.get(0) instanceof User) {
            this.headers = userHeaders;
        } else if (list.get(0) instanceof Product) {
            this.headers = productHeaders;
        } else if (list.get(0) instanceof Order) {
            this.headers = orderHeaders;
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);

        // setting the relative width of the table
        final int[] relativeWidths = new int[headers.length];
        Arrays.fill(relativeWidths, 0, headers.length, 1);
        // set the password column to a bit bigger
        relativeWidths[2] = 2;

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // set title font and align it center
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD);
        Paragraph paragraph = new Paragraph("List of " + title, fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        // create table with length of headers.length
        PdfPTable table = new PdfPTable(headers.length);
        table.setWidthPercentage(100f);
        table.setWidths(relativeWidths);
        table.setSpacingBefore(5);

        // set table header
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.LIGHT_GRAY);
        cell.setPadding(5);
        // set table header font
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD);
        font.setColor(CMYKColor.WHITE);
        // create table header data
        for (String str : headers) {
            cell.setPhrase(new Paragraph(str, font));
            table.addCell(cell);
        }

        // create table data
        Font tableCellFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL);
        for (T item : list) {
            if (item instanceof User) {
                User user = (User) item;
                table.addCell(user.getId().toString());
                table.addCell(user.getUsername());
                table.addCell(user.getPassword());
                table.addCell(user.getFullname());
                table.addCell(user.getEmail());
                table.addCell(user.getPhone());
                table.addCell(user.getAddress());
                table.addCell(user.getImage_url());
                table.addCell(user.getEnabled().toString());
                table.addCell(user.getProvider().toString());
                table.addCell(user.getVerify_code());
                table.addCell(user.getReset_pwd_token());
                table.addCell("");
            } else if (item instanceof Product) {
                Product product = (Product) item;
                table.addCell(product.getId().toString());
                table.addCell(product.getName());
                table.addCell(product.getImage());
                table.addCell(product.getPrice().toString());
                table.addCell(product.getStock().toString());
                table.addCell(product.getAvailable().toString());
                table.addCell(String.valueOf(product.getColor().getName()));
                table.addCell(
                        product.getSize() == null ? "" : CommonUtils.integerToString(product.getSize().getValue()));
                table.addCell(String.valueOf(product.getSale_off()));
                table.addCell(String.valueOf(product.getSold()));
                table.addCell("" + product.getCategory().getName());
                table.addCell("" + product.getSubCategory().getName());
                table.addCell(product.getUser().getUsername());
                table.addCell(DateUtils.formatDateTime(product.getUpdatedAt()));
                table.addCell(DateUtils.formatDateTime(product.getCreatedAt()));
            } else if (item instanceof Order) {
                Order order = (Order) item;
                table.addCell(order.getId().toString());
                table.addCell(order.getAddress());
                table.addCell(order.getCreatedAt().toString());
                table.addCell(order.getUser().getId().toString());
            } else {
                System.out.println("Error");
            }
        }

        document.add(table);
        document.close();

    }
}
