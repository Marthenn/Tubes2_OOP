package Core;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PDFPrinter implements Runnable {
    private final BlockingQueue<String> queue;
    private final String fileName;

    public PDFPrinter(String fileName) {
        this.queue = new LinkedBlockingQueue<>();
        this.fileName = fileName;
    }

    public boolean addText(String text) {
        return queue.offer(text);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000); // simulate long PDF print time
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            String text = "";
            while ((text = queue.poll()) != null) {
                document.add(new Paragraph(text));
            }
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
