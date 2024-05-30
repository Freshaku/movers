package mg.mowers.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import mg.mowers.entity.Invoice;
import mg.mowers.entity.Order;
import mg.mowers.entity.OrderItem;
import mg.mowers.repository.InvoiceRepository;
import mg.mowers.repository.OrderItemRepository;
import mg.mowers.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) throws IOException, DocumentException {
        Order savedOrder = orderRepository.save(order);
        for (OrderItem item : order.getOrderItems()) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }
        createInvoiceForOrder(savedOrder);
        return savedOrder;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private void createInvoiceForOrder(Order order) throws IOException, DocumentException {
        // Создаем PDF
        String pdfPath = "/path/to/your/upload/directory/" + "Invoice_" + order.getOrderNumber() + ".pdf";
        createPdf(pdfPath, order);

        // Создаем накладную
        Invoice invoice = new Invoice();
        invoice.setOrderId(order.getId());
        invoice.setInvoiceNumber("INV-" + order.getOrderNumber());
        invoice.setPdfPath(pdfPath);
        invoice.setSigned(true); // Подписано сотрудником
        invoice.setSignedBy(order.getPartner()); // Подписано контрагентом

        invoiceRepository.save(invoice);
    }

    private void createPdf(String pdfPath, Order order) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
        document.open();
        document.add(new Paragraph("Invoice for Order: " + order.getOrderNumber()));
        document.add(new Paragraph("Date: " + order.getDate()));
        document.add(new Paragraph("Total Amount: " + order.getTotalAmount()));
        document.add(new Paragraph("Partner: " + order.getPartner().getName()));
        document.add(new Paragraph("Created by: " + order.getCreatedBy().getName()));
        document.add(new Paragraph("Signatures:"));
        document.add(new Paragraph("Employee Signature: _______________"));
        document.add(new Paragraph("Partner Signature: _______________"));
        document.close();
    }
}
