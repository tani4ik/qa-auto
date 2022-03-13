package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParsingZipArchive {

    private static final String
            CSV = "users.csv",
            XLSX = "users.xlsx",
            PDF = "users.pdf";

    @Test
    void readZip() throws Exception {
        ZipFile zip = new ZipFile("src/test/resources/files.zip");
        Enumeration<? extends ZipEntry> entries = zip.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (entry.getName().contains("csv")) {
                assertThat(entry.getName()).isEqualTo(CSV);
                parseCsvTest(zip.getInputStream(entry));
            } else if (entry.getName().contains("xlsx")) {
                assertThat(entry.getName()).isEqualTo(XLSX);
                parseXlsxTest(zip.getInputStream(entry));
            } else if (entry.getName().contains("pdf")) {
                assertThat(entry.getName()).isEqualTo(PDF);
                parsePdfTest(zip.getInputStream(entry));
            }
        }
    }

    void parseCsvTest(InputStream file) throws Exception {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file));) {
            List<String[]> str = reader.readAll();
            assertThat(str.get(1)).contains(
                    "Alex;Black;35"
            );
        }
    }

    void parseXlsxTest(InputStream file) throws Exception {
        XLS xls = new XLS(file);
        assertThat(xls.excel
                .getSheetAt(0)
                .getRow(3)
                .getCell(0)
                .getStringCellValue()).contains("Peter");

    }

    void parsePdfTest(InputStream file) throws Exception {
        PDF pdf = new PDF(file);
        assertThat(pdf.text).contains(
                "age"
        );

    }

}