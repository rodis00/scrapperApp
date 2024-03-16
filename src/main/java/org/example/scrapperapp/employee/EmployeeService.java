package org.example.scrapperapp.employee;

import org.example.scrapperapp.scrapper.ScrapperService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private final ScrapperService scrapperService;

    public EmployeeService(ScrapperService scrapperService) {
        this.scrapperService = scrapperService;
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();

        List<Map<String, String>> employeesData = scrapperService.getEmployeesData();

        for (Map<String, String> mapRow : employeesData) {
            employees.add(mapToEmployee(mapRow));
        }

        return employees;
    }

    private Employee mapToEmployee(Map<String, String> mapRow) {

        Employee employee = new Employee();
        employee.setDegree(mapRow.get("degree"));
        employee.setFullName(mapRow.get("fullName"));
        employee.setEmail(mapRow.get("email"));
        employee.setPhone(mapRow.get("phone"));
        employee.setWebUrl(mapRow.get("webUrl"));
        employee.setUnitAbbr(mapRow.get("unitAbbr"));
        employee.setRoom(mapRow.get("room"));

        return employee;
    }
}
