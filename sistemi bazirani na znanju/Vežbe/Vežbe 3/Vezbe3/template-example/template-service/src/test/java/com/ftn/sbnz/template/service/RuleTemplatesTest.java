package com.ftn.sbnz.template.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.ObjectDataCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.junit.Test;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

import com.ftn.sbnz.template.model.models.ClassificationTemplateModel;
import com.ftn.sbnz.template.model.models.Customer;


public class RuleTemplatesTest {

	/**
     * Tests customer-classification-simple.drt template by manually creating
     * the corresponding DRL using a spreadsheet as the data source.
     */
    @Test
    public void testSimpleTemplateWithSpreadsheet2(){
        
        InputStream template = RuleTemplatesTest.class.getResourceAsStream("/templatetable/customer-classification-simple.drt");
        InputStream data = RuleTemplatesTest.class.getResourceAsStream("/templatetable/template-data.xls");
        
        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
        String drl = converter.compile(data, template, 3, 2);
        
        System.out.println(drl);
        
        KieSession ksession = this.createKieSessionFromDRL(drl);
        
        this.doTest(ksession);
    }

	/**
     * Tests customer-classification-simple.drt template by manually creating
     * the corresponding DRL using a bidimensional array of Strings 
     * as the data source.
     */
    @Test
    public void testSimpleTemplateWithArrays(){
        
        InputStream template = RuleTemplatesTest.class.getResourceAsStream("/templatetable/customer-classification-simple.drt");
        
        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
            new String[]{"18", "21", "NA", "NA"},
            new String[]{"22", "30", "NA", "BRONZE"},
            new String[]{"31", "40", "NA", "SILVER"},
            new String[]{"41", "150", "NA", "GOLD"},
        });
        
        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, template);
        
        System.out.println(drl);
        
        KieSession ksession = createKieSessionFromDRL(drl);
        
        doTest(ksession);
    }
	
	/**
     * Tests customer-classification-simple.drt template by manually creating
     * the corresponding DRL using a collection of Objects as the data source.
     */
    @Test
    public void testSimpleTemplateWithObjects(){
        
        InputStream template = RuleTemplatesTest.class.getResourceAsStream("/templatetable/customer-classification-simple.drt");

        
        List<ClassificationTemplateModel> data = new ArrayList<ClassificationTemplateModel>();
        
        data.add(new ClassificationTemplateModel(18, 21, Customer.Category.NA, Customer.Category.NA));
        data.add(new ClassificationTemplateModel(22, 30, Customer.Category.NA, Customer.Category.BRONZE));
        data.add(new ClassificationTemplateModel(31, 40, Customer.Category.NA, Customer.Category.SILVER));
        data.add(new ClassificationTemplateModel(41, 150, Customer.Category.NA, Customer.Category.GOLD));
        
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);
        
        System.out.println(drl);
        
        KieSession ksession = createKieSessionFromDRL(drl);
        
        doTest(ksession);
    }

	private void doTest(KieSession ksession){
        Customer customer1 = new Customer(1L, 19);
        Customer customer2 = new Customer(2L, 27);
        Customer customer3 = new Customer(3L, 32);
        Customer customer4 = new Customer(4L, 60);
        
        ksession.insert(customer1);
        ksession.insert(customer2);
        ksession.insert(customer3);
        ksession.insert(customer4);
        
        ksession.fireAllRules();
        
        assertThat(customer1.getCategory(), is(Customer.Category.NA));
        assertThat(customer2.getCategory(), is(Customer.Category.BRONZE));
        assertThat(customer3.getCategory(), is(Customer.Category.SILVER));
        assertThat(customer4.getCategory(), is(Customer.Category.GOLD));
    }
	
	private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        
        Results results = kieHelper.verify();
        
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }
            
            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        
        return kieHelper.build().newKieSession();
    }
}
