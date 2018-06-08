package ross.goncharuk.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class ElementProcessing {

    public Element getElementById(File file, String format, String id){
        Element element = null;
        try {
            Document document = Jsoup.parse(file, format);
            element = document.getElementById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return element;
    }

    public Element getElementByQuery(File file, String format, String query){
        Element element = null;
        try {
            Document document = Jsoup.parse(file, format);
            element = document.select(query).last();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return element;
    }

    public String getElementsHtmlPath(Element element){
        StringBuilder elementsPath = new StringBuilder();
        Elements parentsTags = element.parents();

        parentsTags.stream()
                .map(Element::tagName)
                .map(el->el + " > ")
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator().forEachRemaining(elementsPath::append);

        String elementsTag = element.tagName();
        return elementsPath.append(elementsTag).toString();
    }
}
