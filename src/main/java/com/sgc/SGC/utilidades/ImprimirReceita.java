package com.sgc.SGC.utilidades;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import com.sgc.SGC.models.Paciente;
import com.sgc.SGC.models.Posologia;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class ImprimirReceita {


	// Path to the jrxml template
	private final String receita_template = "/pdf/receita.jrxml";

    public void generateInvoiceFor(Paciente paciente, List<Posologia> posologias) throws IOException {

        File pdfFile = File.createTempFile("my-invoice", ".pdf");

        try(FileOutputStream pos = new FileOutputStream(pdfFile))
        {
        	// Load the invoice jrxml template.
            final JasperReport report = loadTemplate();

              // Create parameters map.
            final Map<String, Object> parameters = parameters(paciente, posologias);

            // Create an empty datasource.
            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Invoice"));

            // Render the PDF file
            JasperReportsUtils.renderAsPdf(report, parameters, dataSource, pos);

        }
        catch (final Exception e)
        {
            System.out.println(String.format("An error occured during PDF creation: %s", e));
        }
    }
	    
	 // Fill template order parametres
    private Map<String, Object> parameters(Paciente paciente, List<Posologia> posologias) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("paciente",  paciente);
        parameters.put("posologia",  posologias);

        return parameters;
    }


     // Load invoice jrxml template
    private JasperReport loadTemplate() throws JRException {

    	System.out.println(String.format("Invoice template path : %s", receita_template));

        final InputStream reportInputStream = getClass().getResourceAsStream(receita_template);
        final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

        return JasperCompileManager.compileReport(jasperDesign);
    }
}