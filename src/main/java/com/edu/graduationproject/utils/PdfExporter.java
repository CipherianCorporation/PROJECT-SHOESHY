// package com.edu.graduationproject.utils;

// import java.io.IOException;
// import java.util.Arrays;
// import java.util.List;

// import javax.servlet.http.HttpServletResponse;

// import com.edu.entity.Account;
// import com.edu.entity.Category;
// import com.edu.entity.Order;
// import com.edu.entity.Product;
// import com.lowagie.text.Document;
// import com.lowagie.text.DocumentException;
// import com.lowagie.text.Font;
// import com.lowagie.text.FontFactory;
// import com.lowagie.text.PageSize;
// import com.lowagie.text.Paragraph;
// import com.lowagie.text.pdf.CMYKColor;
// import com.lowagie.text.pdf.PdfPCell;
// import com.lowagie.text.pdf.PdfPTable;
// import com.lowagie.text.pdf.PdfWriter;

// public class PdfExporter<T> {
//     // Source:
//     // https://techblogstation.com/spring-boot/export-data-to-pdf-in-spring-boot/
//     // Credit: TBS, techblogstation.com

//     private List<T> list;
//     private String[] headers;
//     String[] accountHeaders = { "Username", "Email", "Password", "Phone", "Address", "Is Admin", "Is Activated" };
//     String[] categoryHeaders = { "ID", "Category Name" };
//     String[] productHeaders = { "ID", "Name", "Price", "Image", "Available", "Create Date", "Category" };
//     String[] orderHeaders = { "ID", "Address", "Create Date", "User" };

//     public PdfExporter(List<T> list) throws DocumentException, IOException {
//         this.list = list;
//         setHeaderByListType(list);
//     }

//     // hàm này để kiểm tra xem type của list là gì để set header
//     public void setHeaderByListType(List<T> list) {
//         if (list.get(0) instanceof Account) {
//             this.headers = accountHeaders;
//         } else if (list.get(0) instanceof Category) {
//             this.headers = categoryHeaders;
//         } else if (list.get(0) instanceof Product) {
//             this.headers = productHeaders;
//         } else if (list.get(0) instanceof Order) {
//             this.headers = orderHeaders;
//         }
//     }

//     public void export(HttpServletResponse response) throws DocumentException, IOException {
//         Document document = new Document(PageSize.A4);

//         // setting the relative width of the table
//         final int[] relativeWidths = new int[headers.length];
//         Arrays.fill(relativeWidths, 0, headers.length, 1);
//         // set the password column to a bit bigger
//         relativeWidths[2] = 2;

//         PdfWriter.getInstance(document, response.getOutputStream());
//         document.open();

//         // set title font and align it center
//         Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD);
//         Paragraph paragraph = new Paragraph("List of Accounts", fontTitle);
//         paragraph.setAlignment(Paragraph.ALIGN_CENTER);
//         document.add(paragraph);

//         // create table with length of headers.length
//         PdfPTable table = new PdfPTable(headers.length);
//         table.setWidthPercentage(100f);
//         table.setWidths(relativeWidths);
//         table.setSpacingBefore(5);

//         // set table header
//         PdfPCell cell = new PdfPCell();
//         cell.setBackgroundColor(CMYKColor.LIGHT_GRAY);
//         cell.setPadding(5);
//         // set table header font
//         Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
//         font.setColor(CMYKColor.WHITE);
//         // create table header data
//         for (String str : headers) {
//             cell.setPhrase(new Paragraph(str, font));
//             table.addCell(cell);
//         }

//         //create table data
//         for (T item : list) {
//             if (item instanceof Account) {
//                 Account account = (Account) item;
//                 table.addCell(account.getId().toString());
//                 table.addCell(account.getEmail());
//                 table.addCell(account.getPassword());
//                 table.addCell(account.getPhone());
//                 table.addCell(account.getAddress());
//                 table.addCell(account.getAdmin().toString());
//                 table.addCell(account.getActivated().toString());
//             } else if (item instanceof Category) {
//                 Category category = (Category) item;
//                 table.addCell(category.getId().toString());
//                 table.addCell(category.getName());
//             } else if (item instanceof Product) {
//                 Product product = (Product) item;
//                 table.addCell(product.getId().toString());
//                 table.addCell(product.getName());
//                 table.addCell(product.getPrice().toString());
//                 table.addCell(product.getImage());
//                 table.addCell(product.getAvailable().toString());
//                 table.addCell(product.getCreatedate().toString());
//                 table.addCell(product.getCategoryid().getName());
//             } else if (item instanceof Order) {
//                 Order order = (Order) item;
//                 table.addCell(order.getId().toString());
//                 table.addCell(order.getAddress());
//                 table.addCell(order.getCreatedate().toString());
//                 table.addCell(order.getUsername().getId());
//             } else {
//                 System.out.println("Error");
//             }
//         }

//         document.add(table);
//         document.close();

//     }
// }
