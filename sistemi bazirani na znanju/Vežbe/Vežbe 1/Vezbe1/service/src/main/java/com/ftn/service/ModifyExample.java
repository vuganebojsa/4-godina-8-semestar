package com.ftn.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.model.Customer;
import com.ftn.model.Item;
import com.ftn.model.Order;
import com.ftn.model.OrderLine;
import com.ftn.util.DebugAgendaEventListener;
import com.ftn.util.KnowledgeSessionHelper;

public class ModifyExample {
	
	public static void main() {
		
		KieContainer kc = KnowledgeSessionHelper.createRuleBase();
		KieSession kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, "ksession-rules");
		
		kSession.addEventListener(new DebugAgendaEventListener());
		
		Customer c1 = new Customer();
        c1.setAge(21);
        c1.setCategory(Customer.Category.NA);
        c1.setCustomerId((long) 21);
        c1.setName("Pera");
        c1.setEmail("pera@pera.pera");
        
        Order o = new Order();
        o.setCustomer(c1);
        o.setDate(new Date());
        List<OrderLine> l1 = new LinkedList<OrderLine>();
        
        OrderLine ol1 = new OrderLine();
        ol1.setQuantity(3);
        ol1.setItem(new Item((long)1,"i1", 123.0, 123.0));
        OrderLine ol2 = new OrderLine();
        ol2.setItem(new Item((long)2,"i2", 223.0, 223.0));
        ol2.setQuantity(6);
        OrderLine ol3 = new OrderLine();
        ol3.setQuantity(6);
        ol3.setItem(new Item((long)3,"i3", 293.0, 213.0));
        OrderLine ol4 = new OrderLine();
        ol4.setItem(new Item((long)4,"i4", 993.0, 1213.0));
        ol4.setQuantity(6);
        OrderLine ol5 = new OrderLine();
        ol5.setItem(new Item((long)5,"i5", 793.0, 1013.0));
        ol5.setQuantity(6);
        OrderLine ol6 = new OrderLine();
        ol6.setQuantity(6);
        ol6.setItem(new Item((long)2,"i2", 223.0, 223.0));
        OrderLine ol7 = new OrderLine();
        ol7.setItem(new Item((long)3,"i3", 293.0, 213.0));
        ol7.setQuantity(6);
        OrderLine ol8 = new OrderLine();
        ol8.setQuantity(6);
        ol8.setItem(new Item((long)4,"i4", 993.0, 1213.0));
        OrderLine ol9 = new OrderLine();
        ol9.setQuantity(6);
        ol9.setItem(new Item((long)5,"i5", 793.0, 1013.0));
        OrderLine ol10 = new OrderLine();
        ol10.setQuantity(6);
        ol10.setItem(new Item((long)5,"i5", 793.0, 1013.0));
        OrderLine ol11 = new OrderLine();
        ol11.setQuantity(6);
        ol11.setItem(new Item((long)5,"i5", 793.0, 1013.0));
        OrderLine ol12 = new OrderLine();
        ol12.setQuantity(6);
        ol12.setItem(new Item((long)5,"i5", 793.0, 1013.0));
        
        
        l1.add(ol1);
        l1.add(ol2);
        l1.add(ol3);
        l1.add(ol4);
        l1.add(ol5);
        l1.add(ol6);
        l1.add(ol7);
        l1.add(ol8);
        l1.add(ol9);
        l1.add(ol10);
        l1.add(ol11);
        l1.add(ol12);
        
        o.setItems(l1);
        
        System.out.println("Category: " + c1.getCategory());
        
        
        kSession.insert(c1);
        kSession.insert(o);
        
        
        int fired = kSession.fireAllRules();
        System.out.println(fired);
        
        System.out.println("Category: " + c1.getCategory());
        
	}

}
