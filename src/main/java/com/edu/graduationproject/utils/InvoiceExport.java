package com.edu.graduationproject.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.edu.graduationproject.entity.Order;
import com.edu.graduationproject.entity.OrderDetails;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.bouncycastle.util.encoders.UTF8;

public class InvoiceExport {
    private Order order;
    private List<OrderDetails> listOrderDetails;

    public InvoiceExport(List<OrderDetails> listOrderDetails, Order order) {
        this.listOrderDetails = listOrderDetails;
        this.order = order;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (OrderDetails order : listOrderDetails) {
            table.addCell(String.valueOf(order.getProduct().getId()));
            table.addCell(order.getProduct().getName());
            table.addCell(String.valueOf(order.getProduct().getPrice()));
            table.addCell(String.valueOf(order.getQuantity()));
            table.addCell(String.valueOf(order.getProduct().getPrice()*order.getQuantity()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);

        Paragraph p = new Paragraph("INVOICE \n SHOESHY", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font1.setSize(15);

        Paragraph p1 = new Paragraph("Code orders: "+ order.getId()
                +"\nRecipient's name: " + order.getUser().getFullname()
                +"\nAddress: "+order.getAddress(), font1);
        p1.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph p2 = new Paragraph("Total price: "+ order.getTotal(), font1);
        p2.setAlignment(Paragraph.ALIGN_RIGHT);

        document.add(p);
        document.add(p1);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.add(p2);

        document.close();

    }
}
