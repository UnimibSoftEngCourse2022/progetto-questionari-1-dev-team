package it.unimib.unimibmodules.controller;

import com.itextpdf.text.DocumentException;
import it.unimib.unimibmodules.model.Answer;

import java.io.IOException;
import java.util.List;

/**
 * Service for sending emails.
 * @author Lorenzo Occhipinti
 * @version 0.4.0
 */
public interface PdfService {

    /**
     * Create A pdf file
     * @param	answers	 the answers given
     * @return           the generated pdf
     */
    byte[] createPDF(List<Answer> answers) throws DocumentException, IOException;
}
