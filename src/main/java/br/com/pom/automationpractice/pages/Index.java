package br.com.pom.automationpractice.pages;

import br.com.core.asserts.Verifications;
import br.com.core.report.ExtentReports;
import br.com.core.setup.DriverManager;
import br.com.core.view.Action;
import br.com.pom.automationpractice.constantes.Constantes;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class Index extends DriverManager implements Constantes {

    private By txtSearch = By.id("search_query_top");

    /**
     *  Valida se a tela index/inicial foi apresentada
     */
    public void validaTelaIncial(){
        Verifications.verifyElementIsClickable(getBrowser(),txtSearch,timeOut);
        ExtentReports.appendToReport(getBrowser());
    }

    /**
     * Escolhe produto basedo no valo passado na tabela via gherkin
     *
     * @param dataTable - valor representando por uma tabela, pode ser enviado ou não o valor da coluna.
     */
    public void escolheProduto(DataTable dataTable){


        List<Map<String,String>> table = dataTable.asMaps();

        for (Map<String,String> row : table){
            for(String campo : row.keySet()){
                String resposta = row.get(campo);
                switch (campo.toUpperCase()){
                    case "PRODUTO":
                        Action.clickOnElement(getBrowser(),By.xpath("//div[@class='product-container']//a[@title='"+resposta.trim()+"']"),timeOut);
                        break;
                    default:
                        Assert.fail("Coluna não encontrada!");
                }
            }
        }
    }
}
