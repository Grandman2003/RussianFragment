package com.example.fragmentrussian1.dummy;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Rule> ITEMS = new ArrayList<Rule>();
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Rule> ITEM_MAP = new HashMap<String, Rule>();

    private static final int COUNT = 25;

    static {

       // addItem(new Rule("1","Правописание глаголов","не выполнено"));
       // addItem(new Rule("2","Правописание наречий","не выполнено"));
        //addItem(new Rule("3","Правописание деепричастий","поставьте 2 балла"));
       // addItem(new Rule("4","Правописание приставок","пожалуйста"));
       // addItem(new Rule("5","Правописание причастий","помогите юным ...."));
        //addItem(new Rule("6","Правописание имен числительных","программистам"));
       // addItem(new Rule("7","Правописание местоимений","возможно"));
       // addItem(new Rule("8","Правописание имен прилагательных","выполнено"));
       // addItem(new Rule("9","Правописание имен существительных","а вдруг"));
        //addItem(new Rule("10","Пунктуация при прямой речи","проверяется"));
        //addItem(new Rule("11","Пунктуация в сложном предложении","не проверяется"));
        //addItem(new Rule("12","Тире между подлежащим и сказуемым","система не определилась"));

    }

    static{
        //addpreItem(ITEMS.get(1).new PreRule("1","Уровень 1","не выполнено"));
       // addpreItem(ITEMS.get(1).new PreRule("2","Уровень 1","не выполнено"));
        //addpreItem(ITEMS.get(1).new PreRule("3","Уровень 1"," выполнено"));
    }


    public static void addItem(Rule item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }


    private static Rule createDummyItem(int position, String content, String details) {
        return new Rule(position+"", content, details);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Rule {
        public final String id;
        public final String content;
        public final String sign;
        public final String details;

        public Rule(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.sign=null;
        }
        public Rule(String id, String content, String details,String sign) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.sign=sign;
        }

        public String getSign() {
            return sign;
        }

        public String getDetails() {
            return details;
        }
        @Override
        public String toString() {
            return content;
        }
        }


    }

