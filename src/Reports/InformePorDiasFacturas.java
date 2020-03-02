package Reports;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

public class InformePorDiasFacturas {

	public static void main(String[] args) throws Exception {
		String outputFileName = "InformePorDiaPorFechas.pdf";
		if (args.length > 0)
			outputFileName = args[0];
		generateInforme(outputFileName,LocalDate.now(),LocalDate.now());
	}

	public static void generateInforme(String outFile, LocalDate FechaIn, LocalDate FechaFin) throws Exception {

		List<Lavados> listaLavados = Models.Lavados.listaFacturas(FechaIn, FechaFin);
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

		// Dummy Table
		float margin = 50;
		// starting y position is whole page height subtracted by top and bottom margin
		float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
		// we want table across whole page width (subtracted by left and right margin
		// ofcourse)
		float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

		boolean drawContent = true;
		float yStart = yStartNewPage;
		float bottomMargin = 70;
		// y position is your coordinate of top left corner of the table
		float yPosition = 800;

		BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page,
				true, drawContent);

		// COMIENZO TABLA

		// the parameter is the row height
		Row<PDPage> headerRow = table.createRow(50);
		// the first parameter is the cell width
		Cell<PDPage> cell = headerRow.createCell(100,  "Lista lavados por dia entre " + FechaIn + " y " + FechaFin);
		cell.setFont(fontBold);
		cell.setFontSize(20);
		// vertical alignment
		cell.setValign(VerticalAlignment.MIDDLE);
		// border style
		cell.setTopBorderStyle(new LineStyle(Color.BLACK, 10));
		// table.addHeaderRow(headerRow);

//		Row<PDPage> row = table.createRow(20);
//		cell = row.createCell(100, "A�o " + fechaActual.getYear());
//		cell.setFillColor(Color.gray);
//		cell.setFontSize(10);
//
//		row = table.createRow(20);
//		cell = row.createCell(100,
//				"Mes " + fechaActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase());
//		cell.setFillColor(Color.lightGray);
//		cell.setFontSize(10);
		
		//TODO Nuevas columnas
//		Row<PDPage> row = table.createRow(20);
//		cell = row.createCell(100,"Dia " + fechaActual);
//		cell.setFillColor(Color.cyan);
//		cell.setFontSize(10);

		Row<PDPage>row = table.createRow(20);
		cell = row.createCell(40, "Fecha");
		cell.setFont(fontBold);
		cell.setFontSize(10);

		cell = row.createCell(20, "Vehiculos Lavados");
		cell.setFont(fontBold);
		cell.setFontSize(10);

		cell = row.createCell(20, "Precio");
		cell.setFont(fontBold);
		cell.setFontSize(10);

		cell = row.createCell(20, "RangoID");
		cell.setFont(fontBold);
		cell.setFontSize(10);

		// Set up monthly and yearly counters
		//TODO contadores por dia
//		int lavadosAnioActual = 0;
//		double lavadosAnioTotal = 0;
//		int lavadosMesActual = 0;
//		double lavadosMesTotal = 0;

//		int numLavadosTotal = 0;
//		boolean cambioMes = false;
//		boolean cambioAnio = false;
		
		double numLavadosTotal = 0;
		double precioTotal = 0;
		
		boolean cambioDia = false;
		double precioDia = 0;
		int lavadosDiaTotal = 0;
		int idIni = listaLavados.get(0).getId();

		for (Lavados lavado : listaLavados) {
			int idFin = lavado.getId();

			if(!fechaActual.equals(lavado.getFecha())) {//Al cambiar de dia actualizamos campos
				row = table.createRow(20);

				cell = row.createCell(40, fechaActual+"");
				cell.setFontSize(10);
				   
				cell = row.createCell(20, lavadosDiaTotal+"");
			    cell.setFontSize(10);
			      
			    cell = row.createCell(20, precioDia+" �");
				cell.setFontSize(10);
				   
				if(lavadosDiaTotal == 1) {
					cell = row.createCell(20, idIni+"");
					cell.setFontSize(10);
				}else {
					cell = row.createCell(20, idIni + "-" + (idFin-1));
					cell.setFontSize(10);
				}
				
				
				fechaActual = lavado.getFecha();
				idFin = lavado.getId();
				idIni = lavado.getId();
				lavadosDiaTotal = 0;
				precioDia = 0;
//				cambioDia = true;
			}
				
			

			try {
				numLavadosTotal++;
				lavadosDiaTotal++;			
				double precio = lavado.getPrecio();
				precioDia += precio;
				precioTotal += precio;

			} catch (NullPointerException e) {
				cell = row.createCell(16, "");
				cell.setFontSize(10);

			}
			
			

		}

		row = table.createRow(20);

		cell = row.createCell(40, fechaActual+"");
		cell.setFontSize(10);
		   
		cell = row.createCell(20, lavadosDiaTotal+"");
	    cell.setFontSize(10);
	      
	    cell = row.createCell(20, precioDia+" €");
		cell.setFontSize(10);
		   
		if(lavadosDiaTotal == 1) {
			cell = row.createCell(20, idIni+"");
			cell.setFontSize(10);
		}else {
			cell = row.createCell(20, idIni + "-" + listaLavados.get(listaLavados.size()-1).getId());
			cell.setFontSize(10);
		}
		

		row = table.createRow(20);
		cell = row.createCell(40, "Total : ");
		cell.setFontSize(10);
		   
		cell = row.createCell(20, numLavadosTotal +"");
	    cell.setFontSize(10);
	      
	    cell = row.createCell(20, precioTotal +" �");
		cell.setFontSize(10);
		   
		cell = row.createCell(20, "");
		cell.setFontSize(10);
		
		table.draw();

		float tableHeight = table.getHeaderAndDataHeight();
		System.out.println("tableHeight = " + tableHeight);

		// close the content stream
		cos.close();

		// Save the results and ensure that the document is properly closed:
		document.save(outFile);
		document.close();
	}

}