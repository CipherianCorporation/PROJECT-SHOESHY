package com.edu.graduationproject.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.edu.graduationproject.entity.Order;
import com.edu.graduationproject.entity.Product;
import com.edu.graduationproject.entity.User;

public class CsvExporter<T> {

    private List<T> list;
    private String filename;

    public CsvExporter(List<T> list, String filename) {
        this.list = list;
        this.filename = filename;
    }

    public void export(Writer writer) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (T entity : list) {
                if (entity instanceof Product) {
                    Product product = (Product) entity;
                    csvPrinter.printRecord(product.getId(), product.getName(), product.getPrice(), product.getImage(),
                            product.getAvailable(), product.getCreated_at().toString(),
                            product.getCategory().getName());
                }
                if (entity instanceof User) {
                    User user = (User) entity;
                    csvPrinter.printRecord(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(),
                            user.getPhone(), user.getImage_url(), user.getEnabled(), user.getProvider().toString(),
                            "");
                }
                if (entity instanceof Order) {
                    Order order = (Order) entity;
                    csvPrinter.printRecord(order.getId(), order.getAddress(), order.getCreated_at().toString(),
                            order.getUser().getUsername());
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
