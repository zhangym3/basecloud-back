package com.base.common.core.pdf;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

public  class WaterMarkText implements IEventHandler {

    private String markText;

    public String getMarkText() {
        return markText;
    }

    public void setMarkText(String markText) {
        this.markText = markText;
    }
    public WaterMarkText() {
    }

    public WaterMarkText(String markText) {
        this.markText = markText;
    }

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
                        .showTextAligned(new Paragraph(markText), (50.5f + i * 100f), (40.0f + j * 100f), document.getPageNumber(page),
                                TextAlignment.CENTER, VerticalAlignment.MIDDLE, 19.5f
                        );
            }
        }
    }
}