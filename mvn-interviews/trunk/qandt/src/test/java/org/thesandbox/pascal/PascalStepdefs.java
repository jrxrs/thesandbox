package org.thesandbox.pascal;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class PascalStepdefs {

    private Pascal pascal;

    @Given("I want to calculate the value of column x and row y in Pascal's Triangle")
    public void I_want_to_calculate_the_value_of_column_x_and_row_y_in_Pascals_Triangle() {
        pascal = new Pascal();
    }

    @When("I pass a data table with a list of columns, rows and their expected results")
    public void I_pass_a_data_table_with_a_list_of_columns_rows_and_their_expected_results() {
        pascal.printPascalsTriangle(10);
    }

    @Then("^The results should all be correct$")
    public void The_results_should_all_be_correct(DataTable cr) {
        List<Map<String, String>> tableRows = cr.asMaps();
        for (Map<String, String> tr : tableRows) {
            Assert.assertEquals(Integer.parseInt(tr.get("result")), pascal.pascal(tr.get("c"), tr.get("r")));
        }
    }

}
