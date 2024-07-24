package org.example.TestNG;

import org.example.hw5.CalculateSalary;
import org.example.hw5.Person;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class SalaryTestWithDataProvider {
    Person person = new Person();
    @BeforeClass
    public void beforeClass(){
        person.name = "Hazal";
        person.no = 143532;
    }

    @Test(groups ="unit")
    public void testPersonInfo(){
        assertEquals("Hazaal", person.name);
        assertEquals(143532,person.no);
    }

    @DataProvider(name = "salaryData")
    public Object[][] salaryDataProvider() {
        return new Object[][] {
                {27, 2000.0, 54000.0},
                {30, 3000.0, 95000.0},
                {25, 1500.0, 37500.0},
                {26, 1500.0, 40000.0},
                {20, 1000.0, 20000.0},
                {28, 1200.0, 36600.0}
        };
    }

    @Test(dataProvider = "salaryData", groups = "unit")
    public void testCalculateSalary(int workday, double dailycharge, double expectedSalary) {

        person.workday = workday;
        person.dailycharge = dailycharge;

        double actualSalary = CalculateSalary.calculateMonthlySalary(person);

        Assert.assertEquals(actualSalary, expectedSalary, "Maaş hesaplaması hatalı!");
    }

    @AfterClass
    public void tearDown() {
        person = null;
    }
}
