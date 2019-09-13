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

public class InformeGeneral {

	public static void main (String[] args) throws Exception {
		String outputFileName = "InformeGeneral.pdf";
		if (args.length > 0)
			outputFileName = args[0];
		generateInforme(outputFileName);
	}

	public static void generateInforme(String outFile) throws Exception {

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
		Cell<PDPage> cell = headerRow.createCell(100, "Lista lavados");
		cell.setFont(fontBold);
		cell.setFontSize(20);
		// vertical alignment
		cell.setValign(VerticalAlignment.MIDDLE);
		// border style
		cell.setTopBorderStyle(new LineStyle(Color.BLACK, 10));
		// table.addHeaderRow(headerRow);

		Row<PDPage> row = table.createRow(20);
		cell = row.createCell(100, "A�o " + fechaActual.getYear());
		cell.setFillColor(Color.blue);
		cell.setFontSize(10);

		row = table.createRow(20);
		cell = row.createCell(100, "Mes " + fechaActual.getMonth());
		cell.setFillColor(Color.CYAN);
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

		// Set up monthly and yearly counters
		int lavadosAnioActual = 0;
		double lavadosAnioTotal = 0;
		int lavadosMesActual = 0;
		double lavadosMesTotal = 0;
		double lavadosTotal = 0;
		int numLavadosTotal = 0;
		boolean cambioMes = false;
		boolean cambioAnio = false;

		for (Lavados lavado : listaLavados) {

			if (fechaActual.getMonth() != lavado.getFecha().getMonth()) {

				cambioMes = true;

				row = table.createRow(20);
				cell = row.createCell(100, "Cantidad total de este mes: " + lavadosMesTotal);
				cell.setFont(fontBold);
				cell.setFontSize(10);
				lavadosMesTotal = 0;

				row = table.createRow(20);
				cell = row.createCell(100, "Numero Lavados de este mes: " + lavadosMesActual);
				cell.setFont(fontBold);
				cell.setFontSize(10);
				lavadosMesActual = 0;

			}

			if (fechaActual.getYear() != lavado.getFecha().getYear()) {

				cambioAnio = true;

				row = table.createRow(20);
				cell = row.createCell(100, "");
				
				row = table.createRow(20);
				cell = row.createCell(100, "Cantidad total de este a�o: " + lavadosAnioTotal);
				cell.setFont(fontBold);
				cell.setFontSize(10);
				lavadosAnioTotal = 0;

				row = table.createRow(20);
				cell = row.createCell(100, "Numero Lavados de este a�o: " + lavadosAnioActual);
				cell.setFont(fontBold);
				cell.setFontSize(10);
				lavadosAnioActual = 0;

			}

			if (cambioMes && !cambioAnio) {
				fechaActual = lavado.getFecha();
				row = table.createRow(20);
				cell = row.createCell(100, "Mes " + fechaActual.getMonth());
				cell.setFillColor(Color.CYAN);
				cell.setFontSize(10);
				cambioMes = false;
			}

			if (cambioAnio) {
				fechaActual = lavado.getFecha();
				row = table.createRow(20);
				cell = row.createCell(100, "A�o " + fechaActual.getYear());
				cell.setFillColor(Color.blue);
				cell.setFontSize(10);

				row = table.createRow(20);
				cell = row.createCell(100, "Mes " + fechaActual.getMonth());
				cell.setFillColor(Color.CYAN);
				cell.setFontSize(10);
				cambioAnio = false;
			}

			try {

				// Add one to counters
				lavadosAnioActual++;
				lavadosMesActual++;
				numLavadosTotal++;

				// Add cost tp counters
				double precio = lavado.getModelo().getPrecio();
				lavadosAnioTotal += precio;
				lavadosMesTotal += precio;
				lavadosTotal += precio;

				row = table.createRow(20);

				cell = row.createCell(16, lavado.getMatricula());
				cell.setFontSize(10);

				cell = row.createCell(20, lavado.getModelo().toString());
				cell.setFontSize(10);

				cell = row.createCell(16, lavado.getHora().toString());
				cell.setFontSize(10);

				cell = row.createCell(16, lavado.getFecha().toString());
				cell.setFontSize(10);

				cell = row.createCell(16, lavado.getTelefono().toString());
				cell.setFontSize(10);

				cell = row.createCell(16, lavado.getComp().toString());
				cell.setFontSize(10);

			} catch (NullPointerException e) {
				cell = row.createCell(16, "");
				cell.setFontSize(10);

			}

		}

		row = table.createRow(20);
		cell = row.createCell(100, "Cantidad total de este mes: " + lavadosMesTotal);
		cell.setFont(fontBold);
		cell.setFontSize(10);
		lavadosMesTotal = 0;

		row = table.createRow(20);
		cell = row.createCell(100, "Numero Lavados de este mes: " + lavadosMesActual);
		cell.setFont(fontBold);
		cell.setFontSize(10);
		lavadosMesActual = 0;
		
		row = table.createRow(20);
		cell = row.createCell(100, "");

		row = table.createRow(20);
		cell = row.createCell(100, "Cantidad total de este a�o: " + lavadosAnioTotal);
		cell.setFont(fontBold);
		cell.setFontSize(10);
		lavadosAnioTotal = 0;
		

		row = table.createRow(20);
		cell = row.createCell(100, "Numero Lavados de este a�o: " + lavadosAnioActual);
		cell.setFont(fontBold);
		cell.setFontSize(10);
		lavadosAnioActual = 0;

		//Fila en blanco
		row = table.createRow(20);
		cell = row.createCell(100, "");
		
		row = table.createRow(20);
		cell = row.createCell(100, "Cantidad total global: " + lavadosTotal + "�");
		cell.setFont(fontBold);
		cell.setFontSize(10);

		row = table.createRow(20);
		cell = row.createCell(100, "Numero Lavados global: " + numLavadosTotal);
		cell.setFont(fontBold);
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