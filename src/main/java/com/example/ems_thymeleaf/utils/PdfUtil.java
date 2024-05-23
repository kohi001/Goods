package com.example.ems_thymeleaf.utils;

import com.example.ems_thymeleaf.entity.Employee;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class PdfUtil {

    public static String fontPath = "C:/Windows/Fonts/msmincho.ttc,1";
    public static BaseFont baseFont = null;

    static {
        try {
            baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createPdf(String fileName, Employee employee) throws DocumentException, IOException {
        //创建文档实例 设置文档纸张为a4
        Document document = new Document(PageSize.A4);
        //设置pdf生成路径
        try {
            PdfWriter instance = PdfWriter.getInstance(document, new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        //打开文档
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
        Font font_m10 =
                new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED),10);
        Paragraph title = new Paragraph("员工信息", font);
        document.add(new Paragraph("员工信息", font_m10));
//        Font font = new Font(baseFont, 8);


        title.setFont(font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        // 4.获取表格信息
        PdfPTable table = createTable(employee);
        document.add(table);
        document.close();
    }

    private static PdfPTable createTable(Employee employee)
            throws DocumentException {
        PdfPTable table = doCreateTable();
        // 2.生成单元格
        PdfPCell cell11 = createSpecialCell("社員番号");
        table.addCell(cell11);
        PdfPCell cell12 = createCell(String.valueOf(employee.getEmplyee_id()));
        table.addCell(cell12);

        PdfPCell cell21 = createSpecialCell("社員名");
        table.addCell(cell21);
        PdfPCell cell22 = createCell(employee.getEmployee_name());
        table.addCell(cell22);

        PdfPCell cell31 = createSpecialCell("性別");
        table.addCell(cell31);
        PdfPCell cell32 = createCell(employee.getSex());
        table.addCell(cell32);

        PdfPCell cell41 = createSpecialCell("パスワード");
        table.addCell(cell41);
        PdfPCell cell42 = createCell(employee.getEmployee_password());
        table.addCell(cell42);

        PdfPCell cell51 = createSpecialCell("email");
        table.addCell(cell51);
        PdfPCell cell52 = createCell(employee.getMail_address());
        table.addCell(cell52);

        PdfPCell cell61 = createSpecialCell("電話番号");
        table.addCell(cell61);
        PdfPCell cell62 = createCell(String.valueOf(employee.getPhone_number()));
        table.addCell(cell62);

        PdfPCell cell71 = createSpecialCell("住所");
        table.addCell(cell71);
        PdfPCell cell72 = createCell(employee.getAddress());
        table.addCell(cell72);

        PdfPCell cell81 = createSpecialCell("job_title");
        table.addCell(cell81);
        PdfPCell cell82 = createCell(employee.getJob_title());
        table.addCell(cell82);

        PdfPCell cell91 = createSpecialCell("department");
        table.addCell(cell91);
        PdfPCell cell92 = createCell(String.valueOf(employee.getDepartment()));
        table.addCell(cell92);

        PdfPCell cell101 = createSpecialCell("employment_status");
        table.addCell(cell101);
        PdfPCell cell102 = createCell(employee.getEmployment_status());
        table.addCell(cell102);

        PdfPCell cell111 = createSpecialCell("position");
        table.addCell(cell111);
        PdfPCell cell112 = createCell(String.valueOf(employee.getPosition()));
        table.addCell(cell112);

        PdfPCell cell121 = createSpecialCell("empInsuranceNumber");
        table.addCell(cell121);
        PdfPCell cell122 = createCell(String.valueOf(employee.getInsurance_number()));
        table.addCell(cell122);

        PdfPCell cell131 = createSpecialCell("Annuity_number");
        table.addCell(cell131);
        PdfPCell cell132 = createCell(String.valueOf(employee.getAnnuity_number()));
        table.addCell(cell132);

        PdfPCell cell141 = createSpecialCell("birth_date");
        table.addCell(cell141);
        PdfPCell cell142 = createCell(String.valueOf(employee.getBirth_date()));
        table.addCell(cell142);

        PdfPCell cell151 = createSpecialCell("hire_date");
        table.addCell(cell151);
        PdfPCell cell152 = createCell(String.valueOf(employee.getHire_date()));
        table.addCell(cell152);

        return table;
    }

    private static PdfPCell createSpecialCell(String cellContent) {
        PdfPCell cell = createCell(cellContent);
        // 边框：蓝色
        cell.setBorderColor(BaseColor.BLUE);
        // 内边距：10
        cell.setPaddingLeft(10);
        // 水平居中
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }


    private static PdfPTable doCreateTable() throws DocumentException {
        int columns = 2;
        // 1.生成一个两列的表格
        PdfPTable table = new PdfPTable(columns);
        // 表格占比100%
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);
        // 2.每一个列宽
        float[] columnWidths = {2f, 5f};
        table.setWidths(columnWidths);
        return table;
    }

    public static PdfPCell createCell(String cellContent) {
//        Font font_m10 =
//                new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED),10);
//        Paragraph title = new Paragraph("Hello你好", font);
//        document.add(new Paragraph("Hello World 你好", font_m10));

        Font font_m10 = null;
        try {
            font_m10 =
                    new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED),10);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Phrase phrase = new Phrase(cellContent, font_m10);
//        phrase.setFont(font_m10);
        PdfPCell cell = new PdfPCell(phrase);
        return cell;
    }

}