package Reports;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import Models.Lavados;
import UI.GUILavadosGeneral;
import be.quodlibet.boxable.*;
import be.quodlibet.boxable.line.LineStyle;
import be.quodlibet.boxable.text.WrappingFunction;

import UI.GUILavadosGeneral;

public class SimpleTable {

    public static void main (String[] args) throws Exception {
        String outputFileName = "SimpleTable.pdf";
        if (args.length > 0)
            outputFileName = args[0];
        
        
        List<Lavados> listaLavados = Models.Lavados.listaLavados();
        LocalDate fechaActual = listaLavados.get(0).getFecha();
        
        // Create a new font object selecting one of the PDF base fonts
        PDFont fontPlain = PDType1Font.HELVETICA;
        PDFont fontBold = PDType1Font.HELVETICA_BOLD;
        PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
        PDFont fontMono = PDType1Font.COURIER;

        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        // PDRectangle.LETTER and others are also possible
        PDRectangle rect = page.getMediaBox();
        // rect can be used to get the page width and height
        document.addPage(page);

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream cos = new PDPageContentStream(document, page);

       
        
        //Dummy Table
        float margin = 50;
        // starting y position is whole page height subtracted by top and bottom margin
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        // we want table across whole page width (subtracted by left and right margin ofcourse)
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

        boolean drawContent = true;
        float yStart = yStartNewPage;
        float bottomMargin = 70;
        // y position is your coordinate of top left corner of the table
        float yPosition = 800;

        BaseTable table = new BaseTable(yPosition, yStartNewPage,
            bottomMargin, tableWidth, margin, document, page, true, drawContent);

       //COMIENZO TABLA
        
        // the parameter is the row height
        Row<PDPage> headerRow = table.createRow(50);
        // the first parameter is the cell width
        Cell<PDPage> cell = headerRow.createCell(100, "Lista lavados");
        cell.setFont(fontBold);
        cell.setFontSize(20);
        // vertical alignment
        cell.setValign(VerticalAlignment.MIDDLE);
        // border style
        cell.setTopBorderStyle(new LineStyle(Color.BLACK, 10));
        table.addHeaderRow(headerRow);

        Row<PDPage> row = table.createRow(20);
        cell = row.createCell(100, "Año " + fechaActual.getYear() );
        cell.setFillColor(Color.blue);;
        cell.setFontSize(10);
        
        
        row = table.createRow(20);
        cell = row.createCell(16, "Matricula");
        cell.setFont(fontBold);
        cell.setFontSize(10);
        
        cell = row.createCell(20, "Modelo");
        cell.setFont(fontBold);
        cell.setFontSize(10);

        cell = row.createCell(16, "Hora");
        cell.setFont(fontBold);
        cell.setFontSize(10);
        
        cell = row.createCell(16, "Fecha");
        cell.setFont(fontBold);
        cell.setFontSize(10);

        cell = row.createCell(16, "Telefono");
        cell.setFont(fontBold);
        cell.setFontSize(10);
        
        cell = row.createCell(16, "Complemento");
        cell.setFont(fontBold);
        cell.setFontSize(10);
        
       
        
        for(int i = 0; i < listaLavados.size();i++) {
        	
            if(fechaActual.getYear() != listaLavados.get(i).getFecha().getYear()) {
            	fechaActual = listaLavados.get(i).getFecha();
            	row = table.createRow(20);
                cell = row.createCell(100, "Año " + fechaActual.getYear() );
                cell.setFillColor(Color.blue);;
                cell.setFontSize(10);
            }
        	
        	try {
        	
        	row = table.createRow(20);
        	
        	cell = row.createCell(16, listaLavados.get(i).getMatricula());
            cell.setFontSize(10);
            
            cell = row.createCell(20, listaLavados.get(i).getModelo().toString());
            cell.setFontSize(10);

            cell = row.createCell(16, listaLavados.get(i).getHora().toString());
            cell.setFontSize(10);
            
            cell = row.createCell(16, listaLavados.get(i).getFecha().toString());
            cell.setFontSize(10);

            cell = row.createCell(16, listaLavados.get(i).getTelefono().toString());
            cell.setFontSize(10);
            
            cell = row.createCell(16, listaLavados.get(i).getProp().toString());
            cell.setFontSize(10);
            
            	
            }catch(NullPointerException e) {
            	cell = row.createCell(16, "");
                cell.setFontSize(10);
            }
            
        }
        
        row = table.createRow(20);
        cell = row.createCell(100,"Cantidad total: " );
        cell.setFontSize(10);

        //cell.setFont(fontBold);->Letra en negrita

//        row = table.createRow(20);
//        cell = row.createCell(50, "red right mono");
//        cell.setTextColor(Color.RED);
//        cell.setFontSize(15);
//        cell.setFont(fontMono);
//        // horizontal alignment
//        cell.setAlign(HorizontalAlignment.RIGHT);
//        cell.setBottomBorderStyle(new LineStyle(Color.RED, 5));
//        cell = row.createCell(50, "green centered italic");
//        cell.setTextColor(Color.GREEN);
//        cell.setFontSize(15);
//        cell.setFont(fontItalic);
//        cell.setAlign(HorizontalAlignment.CENTER);
//        cell.setBottomBorderStyle(new LineStyle(Color.GREEN, 5));

//        row = table.createRow(20);
//        cell = row.createCell(40, "rotated");
//        cell.setFontSize(15);
//        // rotate the text
//        cell.setTextRotated(true);
//        cell.setAlign(HorizontalAlignment.RIGHT);
//        cell.setValign(VerticalAlignment.MIDDLE);
//        // long text that wraps
//        cell = row.createCell(30, "long text long text long text long text long text long text long text");
//        cell.setFontSize(12);
//        // long text that wraps, with more line spacing
//        cell = row.createCell(30, "long text long text long text long text long text long text long text");
//        cell.setFontSize(12);
//        cell.setLineSpacing(2);
        
        table.draw();
        

        float tableHeight = table.getHeaderAndDataHeight();
        System.out.println("tableHeight = "+tableHeight);
        
        // close the content stream 
        cos.close();

        // Save the results and ensure that the document is properly closed:
        document.save(outputFileName);
        document.close();
    }
    
    
    
    
	public static int obtenerAnio(LocalDate date) {
		if (null == date) {
			return 0;
		} else {
			String formato = "yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
			return Integer.parseInt(dateFormat.format(date));
		}
	}

	public static int obtenerMes(LocalDate date) {
		if (null == date) {
			return 0;
		} else {
			String formato = "MM";
			SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
			return Integer.parseInt(dateFormat.format(date));
		}
	}

	public static int obtenerDia(LocalDate date) {
		if (null == date) {
			return 0;
		} else {
			String formato = "dd";
			SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
			return Integer.parseInt(dateFormat.format(date));
		}
	}
    
    
    
    
    
    
    
    
    
}