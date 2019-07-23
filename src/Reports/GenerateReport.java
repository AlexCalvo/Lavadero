package Reports;

import Models.Lavados;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


import java.awt.Color;
import java.io.IOException;
import java.util.List;

public class GenerateReport {


    public static void main(String[] args) throws IOException {
        String filename = "sample.pdf";

        List<Lavados> lavados = Lavados.listaLavadosPorMatricula("1234");

        String message = "This is a sample PDF document created using PDFBox.";
        String message2 = lavados.get(0).toString();
        System.out.println(message2);

        PDDocument doc = new PDDocument();
        try {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDFont font = PDType1Font.HELVETICA_BOLD;

            PDPageContentStream contents = new PDPageContentStream(doc, page);


            contents.beginText();
            contents.setFont(font, 11);
            contents.newLineAtOffset(50, 700);
            contents.showText(message);
            contents.endText();


            contents.beginText();
            contents.newLineAtOffset(80, 700+30);
            contents.showText(message2);
            contents.endText();


            contents.setLineWidth(0.5f);
            contents.setStrokingColor(Color.BLACK);
            contents.moveTo(40f, 30f);
            contents.lineTo(570f, 30f);
            contents.closeAndStroke();


            contents.close();

            doc.save(filename);
        }
        finally {
            doc.close();
        }
    }
}
