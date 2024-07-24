package org.example.hw5;

public class CalculateSalary {
    public static double calculateMonthlySalary(Person person) {
        double salary = 0;
        if (person.workday < 0 || person.workday > 30) {
            System.out.println("Lütfen 1 aylık çalışma günü giriniz.");

        }  else {
            salary = person.workday * person.dailycharge;
            salary += person.workday > 25 ? (person.workday - 25) * 1000 : 0;
            System.out.println( "Personel İsmi: " + person.name +"\nPersonel No: " + person.no +"\nPersonel Aylık Çalışma Ücreti: " + salary +"TL");
        }
        return salary;
    }
}