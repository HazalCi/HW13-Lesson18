package org.example.TestNG;

import org.example.hw5.CalculateSalary;
import org.example.hw5.Person;
import org.testng.Assert;
import org.testng.annotations.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import static org.testng.AssertJUnit.assertEquals;

public class SalaryTest {
    Person person1 = new Person();
    Person person2 = new Person();


    @BeforeClass
    public void beforeClass(){
        person1.name = "Hazal";
        person1.no = 143532;
        person1.workday = 27;
        person1.dailycharge = 2000.0;
    }
    @BeforeMethod
    public void beforeMethod(){
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedName = new String(array, StandardCharsets.UTF_8);
        int generatedNo = new Random().nextInt(1000000);
        int generatedWorkday = new Random().nextInt(31);
        double generatedDailyCharge = new Random().nextDouble() * 3000;
        person2.name = generatedName;
        person2.no = generatedNo;
        person2.workday = generatedWorkday;
        person2.dailycharge = generatedDailyCharge;
    }
    @Test(groups ="unit")
    public void testPersonInfo(){
        Assert.assertEquals(person1.name, "Hazaal");
        Assert.assertEquals(person1.no, 143532);
        Assert.assertEquals(person1.workday, 27);
        Assert.assertEquals(person1.dailycharge, 2000.0);
    }


    @Test(groups ="unit")
    public void testCalculateMonthlySalary(){
        double expectedSalary = person1.workday * person1.dailycharge;
        expectedSalary += (person1.workday > 25) ? (person1.workday - 25) * 1000 : 0;

        double actualSalary = CalculateSalary.calculateMonthlySalary(person1);

        Assert.assertEquals(actualSalary, expectedSalary, "Bu çalışan için maaş hesaplaması doğru değil!");

        double expectedSalary2 = person2.workday * person2.dailycharge;
        expectedSalary2 += (person2.workday > 25) ? (person2.workday - 25) * 1000 : 0;

        double actualSalary2 = CalculateSalary.calculateMonthlySalary(person2);

        Assert.assertEquals(actualSalary2, expectedSalary2, "Bu çalışan için maaş hesaplaması doğru değil!");
    }

    @Test(groups ="unit")
    public void testBoundaryValues(){
        person2.workday = 0;
        person2.dailycharge = 0;
        double salary = CalculateSalary.calculateMonthlySalary(person2);
        assertEquals("Maaş hesaplaması hatalı! (Minimum değerler)", 0.0, salary);

        person2.workday = 30;
        person2.dailycharge = 3000;
        salary = CalculateSalary.calculateMonthlySalary(person2);
        double expectedSalary = 30 * 3000 + (30 - 25) * 1000;
        assertEquals("Maaş hesaplaması hatalı! (Maksimum değerler)", expectedSalary, salary);
    }

    @Test(groups = "unit")
    public void testBonusThreshold() {
        person2.workday = 25;
        double salary = CalculateSalary.calculateMonthlySalary(person2);
        assertEquals("Tam olarak 25 iş günü için bonus hesaplaması hatalı!",  person2.workday * person2.dailycharge, salary);

        person2.workday = 26;
        salary = CalculateSalary.calculateMonthlySalary(person2);
        assertEquals("26 iş günü için bonus hesaplaması hatalı!",  person2.workday * person2.dailycharge+(person2.workday-25)*1000,salary);
    }


    @Test(groups = "unit")
    public void testRandomPersonInfo() {
        Assert.assertNotNull(person2.name);
        Assert.assertTrue(person2.no >= 0 && person2.no < 1000000);
        Assert.assertTrue(person2.workday >= 0 && person2.workday <= 30);
        Assert.assertTrue(person2.dailycharge >= 0 && person2.dailycharge <= 3000);
    }


    @AfterClass
    public void tearDown() {
        person1 = null;
        person2 = null;
    }
}