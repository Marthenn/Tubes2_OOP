package Core;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PDFPrinter implements Runnable {
    private final BlockingQueue<String> queue;
    private final String fileName;
    private float w, h;

    public PDFPrinter(String fileName, float w, float h) {
        this.queue = new LinkedBlockingQueue<>();
        this.fileName = fileName;
        this.w = w;
        this.h = h;
    }

    public boolean addText(String text) {
        return queue.offer(text);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10); // simulate long PDF print time
            Document document = new Document(new Rectangle(w, h));
            document.setMargins(10, 10, 5, 5);
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            String text = "";
            Font font = new Font(FontFamily.COURIER, 12, Font.NORMAL, BaseColor.BLACK);
            while ((text = queue.poll()) != null) {
                document.add(new Paragraph(text, font));
            }
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
