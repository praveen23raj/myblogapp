package com.blogapp1;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestClass {
    public static void main(String[] args) {

        Employee e1 = new Employee();
        e1.setId(1);
        e1.setName("mike");
        Employee e2 = new Employee();
        e2.setId(2);
        e2.setName("stalin");

        List<Employee> list = Arrays.asList(e1, e2);
        List<EmployeeDto> dto = list.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        System.out.println(dto);

    }

     static EmployeeDto mapToDto(Employee e) {
        EmployeeDto dto=new EmployeeDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
         return dto;
     }
}
















