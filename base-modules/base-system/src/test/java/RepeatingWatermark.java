import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.font.*;
import com.itextpdf.kernel.geom.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class RepeatingWatermark {

    public static void main(String[] args) throws Exception {
        String src = "d://aaaaaaaaa.pdf";
        String dest = "d://output_repeating_watermark.pdf";

        setWaterMark(dest,src);

    }


    public static void setWaterMark(String newFileName, String readFileName) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(new File(newFileName));
        PdfWriter pdfWriter = new PdfWriter(outputStream);
        PdfDocument outDocument = new PdfDocument(pdfWriter);

        PdfReader pdfReader = new PdfReader(readFileName);
        PdfDocument redDocument = new PdfDocument(pdfReader);
        //文字水印
        WaterMarkText waterMark = new WaterMarkText();

        //图片水印
        //WaterMarkImage waterMark = new WaterMarkImage();
        outDocument.addEventHandler(PdfDocumentEvent.INSERT_PAGE, waterMark);

        int total = redDocument.getNumberOfPages() + 1;
        for (int i = 1; i < total; i++) {
            PdfPage page = redDocument.getPage(i);
            //复制每页内容添加到新的文件中
            outDocument.addPage(page.copyTo(outDocument));
        }

        outDocument.close();
        redDocument.close();
        pdfReader.close();
    }

    protected static class WaterMarkText implements IEventHandler {
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
            PdfDocument document = documentEvent.getDocument();
            PdfPage page = documentEvent.getPage();
            Rectangle pageSize = page.getPageSize();

            PdfFont pdfFont = null;
            try {
                // Constants.PDF_FONT 自定义字体  PDF_FONT = "classpath:pdf/MSYH.TTF"

                pdfFont = PdfFontFactory.createFont(ResourceUtils.getFile("classpath:fonts/SimSun.ttf").getPath(), PdfEncodings.IDENTITY_H, true);

            } catch (IOException e) {
                e.printStackTrace();
            }

            // 透明度
            PdfExtGState gs = new PdfExtGState();
            gs.setFillOpacity(0.5f);
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), document);
            canvas.setExtGState(gs);

            // 两个for循环 是多个水印 去掉之后就只有一个水印
            for (int i = 0; i < 10; i++) {     // i 从左往右 平铺水印
                for (int j = 0; j < 10; j++) { // j 从下往上 平铺水印
                    new Canvas(canvas, document, pageSize)
                            .setFontColor(Color.LIGHT_GRAY)   //颜色
                            .setFontSize(15)                //字体大小
                            .setFont(pdfFont)               //字体的格式   即导入的字体包
                            //水印的内容（中英文都支持）    坐标（例如：250f, 250f）  当前页数     最后的值为倾斜度（19.5f）
                            .showTextAligned(new Paragraph("广信玖逸"), (50.5f + i * 100f), (40.0f + j * 100f), document.getPageNumber(page),
                                    TextAlignment.CENTER, VerticalAlignment.MIDDLE, 19.5f
                            );
                }
            }
        }
    }

    protected static class WaterMarkImage implements IEventHandler {
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
            PdfDocument document = documentEvent.getDocument();
            PdfPage page = documentEvent.getPage();
            Rectangle pageSize = page.getPageSize();

            ImageData imageData = null;
            try{
            imageData = ImageDataFactory.create(ResourceUtils.getFile("classpath:pdf/WaterMark.png").getPath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 两个for循环 是多个水印 去掉之后就只有一个水印
            for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Image image = new Image(imageData);

                // 水印位置
                image.setFixedPosition((50.5f + i * 150f), (40.0f + j * 250f));
                // 水印倾斜度
                image.setRotationAngle(170.5f);

                //  setFillOpacity 透明度
                PdfExtGState gs = new PdfExtGState();
                gs.setFillOpacity(0.1f);
                PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), document);
                canvas.setExtGState(gs);

                new Canvas(canvas, document, pageSize)
                        .add(image);
            }
        }
    }
}

}
