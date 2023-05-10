//package com.example.couponsp2.clr;
//
//import com.example.couponsp2.advice.ErrorObj;
//import com.example.couponsp2.beans.*;
//import com.example.couponsp2.custom_exceptions.CompanyException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.List;
//
//
////@Component
//@RequiredArgsConstructor
//public class CompanyDemoRestTemplate implements CommandLineRunner {
//
//    public final RestTemplate restTemplate;
//
//    @Value("${api.url}" + "/company")
//    public final String url;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        System.out.println("---------------------RestTemplateDemo---------------------");
//
//        Company[] companies = restTemplate.getForObject(url, Company[].class);
//        assert companies != null;
//        List<Company> companyList = Arrays.asList(companies);
//        System.out.println("---------------------RestTemplate-Companies---------------------");
//        companyList.forEach(System.out::println);
//
//        Company company = new Company();
//        try {
//            company = restTemplate.getForObject(url + "/33", Company.class);
//            System.out.println("---------------------RestTemplate-Company---------------------");
//            System.out.println(company);
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//
//        assert company != null;
//        company.setEmail("RestTemplateUpdated@Email");
//        restTemplate.put(url + "/3", company);
//
//    }
//}
