package com.base.system.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;

import com.base.common.core.pdf.WaterMarkText;
import com.base.common.core.utils.DateUtils;
import com.base.common.core.utils.StringUtils;
import com.base.system.service.MinioService;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.layout.element.AreaBreak;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.system.mapper.PatientImgInfoMapper;
import com.base.system.domain.PatientImgInfo;
import com.base.system.service.IPatientImgInfoService;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.io.InputStream;
import java.util.List;

/**
 * imginfoService业务层处理
 * 
 * @author walker
 * @date 2025-04-25
 */
@Service
public class PatientImgInfoServiceImpl implements IPatientImgInfoService 
{
    @Autowired
    private PatientImgInfoMapper patientImgInfoMapper;
    @Autowired
    private MinioService minioService;
    /**
     * 查询imginfo
     * 
     * @param id imginfo主键
     * @return imginfo
     */
    @Override
    public PatientImgInfo selectPatientImgInfoById(String id)
    {
        return patientImgInfoMapper.selectPatientImgInfoById(id);
    }
    /**
     * 查询imginfo
     *
     * @param seeDoctorId 就诊ID
     * @return imginfo
     */
    @Override
    public List<PatientImgInfo> selectPatientImgInfoByseeDoctorId(String seeDoctorId)
    {
        return patientImgInfoMapper.selectPatientImgInfoByseeDoctorId(seeDoctorId);
    }
    /**
     * 查询imginfo列表
     * 
     * @param patientImgInfo imginfo
     * @return imginfo
     */
    @Override
    public List<PatientImgInfo> selectPatientImgInfoList(PatientImgInfo patientImgInfo)
    {
        return patientImgInfoMapper.selectPatientImgInfoList(patientImgInfo);
    }

    /**
     * 新增imginfo
     * 
     * @param patientImgInfo imginfo
     * @return 结果
     */
    @Override
    public int insertPatientImgInfo(PatientImgInfo patientImgInfo)
    {
        patientImgInfo.setCreateTime(DateUtils.getNowDate());
        return patientImgInfoMapper.insertPatientImgInfo(patientImgInfo);
    }

    /**
     * 修改imginfo
     * 
     * @param patientImgInfo imginfo
     * @return 结果
     */
    @Override
    public int updatePatientImgInfo(PatientImgInfo patientImgInfo)
    {
        return patientImgInfoMapper.updatePatientImgInfo(patientImgInfo);
    }

    /**
     * 批量删除imginfo
     * 
     * @param ids 需要删除的imginfo主键
     * @return 结果
     */
    @Override
    public int deletePatientImgInfoByIds(String[] ids)
    {
        return patientImgInfoMapper.deletePatientImgInfoByIds(ids);
    }

    /**
     * 删除imginfo信息
     * 
     * @param id imginfo主键
     * @return 结果
     */
    @Override
    public int deletePatientImgInfoById(String id)
    {
        return patientImgInfoMapper.deletePatientImgInfoById(id);
    }


    public List<PatientImgInfo> selectPatientImgInfoByClassificationIds(String seeDoctorId, String ids){

        return patientImgInfoMapper.selectPatientImgInfoByClassificationIds(seeDoctorId,ids);
    }

    public List<PatientImgInfo> listDataByImgIds(String seeDoctorId, List<String> ids){

        return patientImgInfoMapper.listDataByImgIds(seeDoctorId,ids);
    }
    public String createPdf(List<PatientImgInfo> images,
                                            String watermarkText,
                                            String uploadBucket,
                                            String uploadObjectName) throws Exception {

        // 使用内存输出流替代文件路径
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfDocument pdf = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdf);

        if (StringUtils.isNotEmpty(watermarkText)) {
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new WaterMarkText(watermarkText));
        }

        PdfOutline rootOutline = pdf.getOutlines(false);
        Map<String, PdfOutline> categoryOutlines = new LinkedHashMap<>();
        Map<String, Integer> categoryCounters = new HashMap<>();
        int pageNumber = 1;

        for (PatientImgInfo imageInfo : images) {
            try (InputStream imageStream = minioService.getObject(imageInfo.getBucket(), imageInfo.getObjName())) {
                Image image = new Image(ImageDataFactory.create(IOUtils.toByteArray(imageStream)))
                        .setAutoScale(true)
                        .setMarginBottom(20);
                document.add(image);
                int index = categoryCounters.getOrDefault(imageInfo.getClassificationName(), 0) + 1;
                String bookmarkTitle = imageInfo.getClassificationName() + "-" + index;

                PdfOutline categoryOutline = categoryOutlines.computeIfAbsent(
                        imageInfo.getClassificationName(),
                        catName -> rootOutline.addOutline(catName)
                );
                PdfOutline imageOutline = categoryOutline.addOutline(bookmarkTitle);
                imageOutline.addDestination(PdfExplicitDestination.createFit(pdf.getPage(pageNumber)));

                categoryCounters.put(imageInfo.getClassificationName(), index);
                pageNumber++;
                if (pageNumber <= images.size()) {
                    document.add(new AreaBreak());
                }
            }
        }

        document.close();

        // 将 PDF 内容作为 InputStream 上传至 MinIO
        try (InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray())) {
            minioService.putObject(uploadBucket, uploadObjectName, inputStream, "application/pdf");
        }

        return minioService.getObjectUrl(uploadBucket, uploadObjectName);
    }

}
