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
import Models.Modelo;
import UI.GUILavadosGeneral;
import be.quodlibet.boxable.*;
import be.quodlibet.boxable.line.LineStyle;
import be.quodlibet.boxable.text.WrappingFunction;

import UI.GUILavadosGeneral;

public class Tickets {

	public static void main(String[] args) throws Exception {
		String outputFileName = "Tickets.pdf";
		if (args.length > 0)
			outputFileName = args[0];
		generateTicket(outputFileName, LocalDate.now(), LocalDate.now());
	}

	private static String generateText(Lavados lavado) {
		String result = "  PARKING MARILYN MONROE" + "                                                                      "
				+ "C.I.F.: B-29.355.195                                                           "
				+ "          Promociones y Construcciones" + "                       " + "Puerto SL           "
				+ "          "
				+ "       C/Marilyn Monroe, 2"
				+ "                             " + "29004 Málaga"
				+ "                                                                                     "
				+ "TLF. 952.067.850"
				+ "                                                                                " + lavado.getHora()
				+ "      " + lavado.getFecha()
				+ "                                                                        " + "FACTURA SIMPLIFICADA"
				+ "                                                                                  ";
		int length = (lavado.getId() + "").length();
		try {
			result += new String(new char[5 - length]).replace("\0", " ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		result += lavado.getId()
				+ "                                                                                        "
				+ "Lavado Vehículo Matrícula" + "                                                                          ";
		length = lavado.getMatricula().length();
		try {
			result += new String(new char[7 - length]).replace("\0", " ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		result += lavado.getMatricula() + "     " + lavado.getPrecio() + "€";
		length = (lavado.getPrecio() + "").length();
		try {
			result += new String(new char[5 - length]).replace("\0", " ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		result += "                                                                                 "
				+ "21% IVA INCLUIDO";
		System.out.println(result.length());
		
		
		return result;
	}

	public static void generateTicket(String outFile, LocalDate fecIni, LocalDate fecFin) throws Exception {

		String textoTicket = " PARKING MARILYN MONROE" + "                                                             "
				+ "Promociones y Construcciones" + "                        " + "Puerto SL"
				+ "                                                                                     "
				+ "C/Marilyn Monroe, 2"
				+ "                                                                                  " + "29004 Málaga"
				+ "                                                                                  "
				+ "TLF. 952.067.850"
				+ "                                                                                    "
				+ "HORA      FECHA" + "                                                                          "
				+ "FACTURA SIMPLIFICADA" + "                             " + "ID"
				+ "                                                                                        "
				+ "Lavado Vehículo Matrícula" + "              " + "MATRICULA     TOTAL"
				+ "                                                                           " + "21% IVA INCLUIDO";
		
		List<Lavados> listaLavados = Models.Lavados.listaTickets(fecIni,fecFin);
		LocalDate fechaActual = listaLavados.get(0).getFecha();

		// Create a new font object selecting one of the PDF base fonts
		PDFont fontPlain = PDType1Font.COURIER;
		PDFont fontBold = PDType1Font.COURIER_BOLD;
		PDFont fontItalic = PDType1Font.COURIER_OBLIQUE;
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
		Cell<PDPage> cell = headerRow.createCell(99, "Lista tickets entre " + fecIni + " y " + fecFin);
		cell.setFont(fontBold);
		cell.setFontSize(17);
		// vertical alignment
		cell.setValign(VerticalAlignment.MIDDLE);
		// border style
		cell.setTopBorderStyle(new LineStyle(Color.BLACK, 10));
		// table.addHeaderRow(headerRow);

		Row<PDPage> row = null;
		for (int i = 0; i < listaLavados.size(); i++) {
			// PAGINA
			if (i % 3 == 0)
				row = table.createRow(200);
			cell = row.createCell(33, generateText(listaLavados.get(i)));
			cell.setFontSize(10);
			// FIN PAGINA
		}

		table.draw();

		float tableHeight = table.getHeaderAndDataHeight();
		System.out.println("tableHeight = " + tableHeight);

		// close the content stream
		cos.close();

		// Save the results and ensure that the document is properly closed:
		document.save(outFile);
		document.close();
	}

	// public static int obtenerAnio(LocalDate date) {
	// if (null == date) {
	// return 0;
	// } else {
	// String formato = "yyyy";
	// SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
	// return Integer.parseInt(dateFormat.format(date));
	// }
	// }
	//
	// public static int obtenerMes(LocalDate date) {
	// if (null == date) {
	// return 0;
	// } else {
	// String formato = "MM";
	// SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
	// return Integer.parseInt(dateFormat.format(date));
	// }
	// }
	//
	// public static int obtenerDia(LocalDate date) {
	// if (null == date) {
	// return 0;
	// } else {
	// String formato = "dd";
	// SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
	// return Integer.parseInt(dateFormat.format(date));
	// }
	// }

}