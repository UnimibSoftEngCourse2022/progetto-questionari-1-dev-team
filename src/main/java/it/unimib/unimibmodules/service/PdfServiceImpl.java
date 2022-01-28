package it.unimib.unimibmodules.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import it.unimib.unimibmodules.controller.PdfService;
import it.unimib.unimibmodules.model.Answer;
import it.unimib.unimibmodules.model.CloseEndedAnswer;
import it.unimib.unimibmodules.model.QuestionType;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Service for sending emails.
 * @author Lorenzo Occhipinti
 * @version 0.4.0
 */
@Component("pdfService")
public class PdfServiceImpl implements PdfService {

    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 24,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 16,
            Font.BOLD);

    /**
     * Create A pdf file
     * @param	answers	 the answers given
     * @return           the generated pdf
     */
    @Override
    public byte[] createPDF(List<Answer> answers) throws DocumentException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();
        addMetaData(document, answers.get(0).getSurvey().getName());
        addTitle(document, answers.get(0).getSurvey().getName());
        addContent(document, answers);
        document.close();
        writer.close();
        return baos.toByteArray();
    }

    private static void addMetaData(Document document, String name) {
        document.addTitle("Compiled Survey: "+name);
        document.addAuthor("Unimib Modules");
        document.addCreator("Unimib Modules");
    }

    private static void addTitle(Document document, String name) throws DocumentException{
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(name, catFont));
        addEmptyLine(preface, 1);
        document.add(preface);
    }

    private static void addContent(Document document, List<Answer> answers) throws DocumentException{
        Paragraph anchor = new Paragraph();
        for (int i=0;i < answers.size();i++)
        {
            if(answers.get(i).getQuestion().getQuestionType()== QuestionType.OPEN) {
                anchor.add(new Paragraph(answers.get(i).getQuestion().getText(), subFont));
                anchor.add(new Paragraph(answers.get(i).getText()));
            }else if (answers.get(i).getQuestion().getQuestionType() == QuestionType.MULTIPLECLOSED){
                anchor.add(new Paragraph(answers.get(i).getQuestion().getText()+" (Multiple closed)", subFont));
                addClosedAnswers(anchor, answers.get(i));
            }
            else{
                anchor.add(new Paragraph(answers.get(i).getQuestion().getText()+" (Single closed)", subFont));
                addClosedAnswers(anchor, answers.get(i));
            }
            addEmptyLine(anchor, 1);
        }
        document.add(anchor);
    }

    private static void addClosedAnswers(Paragraph paragraph, Answer answer) {
        for (CloseEndedAnswer closeEndedAnswer : answer.getQuestion().getCloseEndedAnswerSet()){
            if(answer.getCloseEndedAnswers().contains(closeEndedAnswer))
                paragraph.add(new Paragraph("* "+closeEndedAnswer.getText()));
            else
                paragraph.add(new Paragraph("O "+closeEndedAnswer.getText()));
        }
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
