package it.unimib.unimibmodules.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import it.unimib.unimibmodules.controller.AWSToken;
import it.unimib.unimibmodules.controller.PdfService;
import it.unimib.unimibmodules.model.Answer;
import it.unimib.unimibmodules.model.CloseEndedAnswer;
import it.unimib.unimibmodules.model.QuestionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Service for sending emails.
 * @author Lorenzo Occhipinti
 * @version 0.4.1
 */
@Component("pdfService")
public class PdfServiceImpl implements PdfService {

    private static final Logger logger = LogManager.getLogger(PdfServiceImpl.class);

    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 24,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 16,
            Font.BOLD);
    private static Font errorFont = new Font(Font.FontFamily.HELVETICA, 10,
            Font.UNDERLINE);

    /**
     * Create A pdf file
     * @param	answers	 the answers given
     * @return           the generated pdf
     */
    @Override
    public byte[] createPDF(List<Answer> answers) throws DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();
        addMetaData(document, answers.get(0).getSurvey().getName());
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

    private static void addContent(Document document, List<Answer> answers) throws DocumentException {

        float scaler = (document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
        for (int i=0;i < answers.size();i++)
        {
            Paragraph anchor = new Paragraph();
            if (i==0)
                addTitle(anchor, answers.get(i).getSurvey().getName());
            anchor.add(new Paragraph(answers.get(i).getQuestion().getText()+" "+answers.get(i).getQuestion().getQuestionType(), subFont));
            addImage(anchor, answers.get(i).getQuestion().getUrlImage(), scaler);
            if(answers.get(i).getQuestion().getQuestionType()== QuestionType.OPEN) {
                anchor.add(new Paragraph(answers.get(i).getText()));
            }else if (answers.get(i).getQuestion().getQuestionType() == QuestionType.MULTIPLECLOSED ||
                      answers.get(i).getQuestion().getQuestionType() == QuestionType.SINGLECLOSED){
                addClosedAnswers(anchor, answers.get(i));
            }
            addEmptyLine(anchor, 1);
            document.add(anchor);
            if (answers.get(i).getQuestion().getUrlImage()!=null || (i!=answers.size()-1 && answers.get(i+1).getQuestion().getUrlImage()!=null))
                document.newPage();
        }
    }

    private static void addTitle(Paragraph preface, String name){
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(name, catFont));
        addEmptyLine(preface, 1);
    }

    private static void addClosedAnswers(Paragraph paragraph, Answer answer) {
        for (CloseEndedAnswer closeEndedAnswer : answer.getQuestion().getCloseEndedAnswerSet()){
            if(answer.getCloseEndedAnswers().contains(closeEndedAnswer))
                paragraph.add(new Paragraph("* "+closeEndedAnswer.getText()));
            else
                paragraph.add(new Paragraph("O "+closeEndedAnswer.getText()));
        }
    }

    private static void addImage(Paragraph anchor, String url, float scaler) {
        if (url!=null) {
            AWSCredentials credentials = new BasicAWSCredentials(AWSToken.ACCESS_KEY_ID, AWSToken.ACCESS_KEY_VALUE);
            try {
                AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                        .withRegion(AWSToken.REGION.getName())
                        .withCredentials(new AWSStaticCredentialsProvider(credentials))
                        .build();
                S3Object fullObject = s3Client.getObject(AWSToken.BUCKET_NAME, url);
                S3ObjectInputStream inputStream = fullObject.getObjectContent();
                Image image = Image.getInstance(IOUtils.toByteArray(inputStream));

                image.scalePercent(scaler/image.getPlainWidth()*70);
                anchor.add(image);
                fullObject.close();
            } catch (SdkClientException | DocumentException | IOException e) {
                addEmptyLine(anchor, 2);
                anchor.add(new Paragraph("Error loading image", errorFont));
                addEmptyLine(anchor, 2);
            }
        }
    }


    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
