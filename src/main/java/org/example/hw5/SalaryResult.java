package org.example.hw5;

public class SalaryResult {
    public static void main(String[] args) {
        Person person1 = new Person();
        person1.name = "Hazal";
        person1.no = 143532;
        person1.workday = 27;
        person1.dailycharge = 2000;
        CalculateSalary.calculateMonthlySalary(person1);
    }
}