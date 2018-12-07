package vijay.poc.itext7;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.AreaBreakType;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PdfOutline is POC Class created to demonstrate PDF generation with
 * bookmarks/outline using iText7
 * 
 * @author Vijay (AM.Vijay@gmail.com)
 */
public class PdfOutlineExample {

	private static final String FILE_NAME = "PdfOutline.pdf";

	private static Document document;

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) {

		try {

			PdfWriter pdfWriter = new PdfWriter(FILE_NAME);
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);

			document = new Document(pdfDocument);

			createPdfPage1WithOutline();
			createPdfPage2WithOutline();

			pdfDocument.close();
			pdfWriter.close();
		} catch (IOException ex) {
			Logger.getLogger(PdfOutlineExample.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Method to create new Bookmark/Outline and new paragraph
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void createPdfPage1WithOutline() throws FileNotFoundException, IOException {

		createOutline("Page 1", "destination 1");

		Paragraph paragraph = new Paragraph("Hello, World1");
		paragraph.setDestination("destination 1");
		document.add(paragraph);
	}

	/**
	 * Method to create new Bookmark/outline and new paragraph in new page.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void createPdfPage2WithOutline() throws FileNotFoundException, IOException {

		document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

		createOutline("Page 2", "destination 2");
		Paragraph paragraph = new Paragraph("Hello, World2");
		paragraph.setDestination("destination 2");
		document.add(paragraph);
	}

	/**
	 * Method to create Bookmark/outline in PDF
	 * @param title
	 * @param name
	 */
	public static void createOutline(String title, String name) {
		// if (pdfOutline == null) {
		PdfOutline pdfOutline = document.getPdfDocument().getOutlines(false);
		pdfOutline = pdfOutline.addOutline(title);
		pdfOutline.addDestination(PdfDestination.makeDestination(new PdfString(name)));
		// }
		// PdfOutline kid = pdfOutline.addOutline(title);
		// kid.addDestination(PdfDestination.makeDestination(new PdfString(name)));
	}

}
