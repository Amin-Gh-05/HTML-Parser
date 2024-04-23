import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        sortedByName.sort(Comparator.comparing(Country::getName));
        return sortedByName;
    }

    public List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        sortedByPopulation.sort(Comparator.comparing(Country::getPopulation));
        return sortedByPopulation;
    }

    public List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        sortedByArea.sort(Comparator.comparing(Country::getArea));
        return sortedByArea;
    }

    public void setUp() throws IOException {
        File file = new File("src/Resources/country-list.html");
        Document doc = Jsoup.parse(file , "UTF-8");
        Elements countriesElements = doc.select("div.country");
        for (Element country : countriesElements) {
            String name = country.select(".country-name").text();
            String capital = country.select(".country-capital").text();
            int population = Integer.parseInt(country.select(".country-population").text());
            double area = Double.parseDouble(country.select(".country-area").text());
            countries.add(new Country(name, capital, population, area));
        }
    }

    public static void main(String[] args) {

    }
}
