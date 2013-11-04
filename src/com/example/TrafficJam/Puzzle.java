package com.example.TrafficJam;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Notandi
 * Date: 4.11.2013
 * Time: 09:57
 * To change this template use File | Settings | File Templates.
 */
public class Puzzle {
    ArrayList<Puzzle> puzzles = new ArrayList<Puzzle>();
    public String id;
    public String level;
    public String setup;
    public int currentPuzzle;

        Puzzle(){}

        Puzzle(String id, String level, String setup) {
            this.id = id;
            this.level = level;
            this.setup = setup;
        }

        String getId() {
            return id;
        }

        void setId(String id) {
            this.id = id;
        }

        String getLevel() {
            return level;
        }

        void setLevel(String level) {
            this.level = level;
        }

        String getSetup() {
            return setup;
        }

        void setSetup(String setup) {
            this.setup = setup;
        }

    public Puzzle getPuzzleById(int id){
        return puzzles.get(id);
    }

    public ArrayList<Puzzle> getAllPuzzles(){
        return puzzles;
    }


    public void LoadPuzzles(){
        //change if need be
        currentPuzzle = 1;
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(getXml()));
            int eventType = xpp.getEventType();
            Puzzle p = null;
            boolean incset = false;
            boolean inclel = false;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if(eventType == XmlPullParser.START_DOCUMENT) {
                } else if(eventType == XmlPullParser.START_TAG) {
                    if(xpp.getName().equals("puzzle")){
                        p = new Puzzle();
                    }
                    if(xpp.getName().equals("setup")){
                        incset = true;
                    }
                    if(xpp.getName().equals("level")){
                        inclel = true;
                    }
                } else if(eventType == XmlPullParser.END_TAG) {
                    if(xpp.getName().equals("puzzle")){
                        puzzles.add(p);
                    }
                    if(xpp.getName().equals("setup")){
                        incset = false;
                    }
                    if(xpp.getName().equals("level")){
                        inclel = false;
                    }
                } else if(eventType == XmlPullParser.TEXT) {
                    if(incset) {
                        assert p != null;
                        p.setSetup(xpp.getText());
                    }
                    if(inclel){
                        assert p != null;
                        p.setLevel(xpp.getText());
                    }
                }
                eventType = xpp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private String getXml() {

        String ret = "<?xml version=\"1.0\"?>\n" +
                "<challenge id=\"0\" name=\"Classic 40\">\n" +
                "\n" +
                "    <puzzle id=\"1\">\n" +
                "        <level>1</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (V 0 1 3), (H 0 0 2), (V 3 1 3), (H 2 5 3), (V 0 4 2), (H 4 4 2), (V 5 0 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"2\">\n" +
                "        <level>1</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (V 0 0 2), (H 0 3 3), (H 0 5 2), (V 2 4 2), (H 3 0 3), (V 3 1 2), (V 4 2 2), (H 4 4 2), (H 3 5 2), (V 5 1 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"3\">\n" +
                "        <level>1</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (H 1 3 2), (V 3 2 3), (V 1 4 2), (H 2 5 2), (V 5 3 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"4\">\n" +
                "        <level>1</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (V 2 3 2), (H 3 3 3), (V 0 0 3), (V 5 4 2), (V 3 0 3), (H 2 5 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"5\">\n" +
                "        <level>1</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (H 0 0 2), (V 4 1 3), (H 4 5 2), (V 0 4 2), (V 3 0 3), (V 5 0 2), (H 1 3 3), (H 4 4 2), (V 5 2 2), (V 0 1 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"6\">\n" +
                "        <level>1</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (H 0 0 2), (V 3 2 3), (V 0 4 2), (H 0 3 2), (V 4 1 3), (V 3 0 2), (H 0 1 2), (V 2 3 2), (H 3 5 3), (V 5 1 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"7\">\n" +
                "        <level>1</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (V 1 0 2), (V 3 4 2), (V 5 2 2), (V 5 0 2), (H 2 0 2), (V 4 0 2), (V 3 1 2), (H 2 3 2)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"8\">\n" +
                "        <level>1</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (H 3 0 2), (H 3 5 3), (H 0 3 2), (V 2 2 2), (V 5 0 3), (H 2 1 2), (V 4 1 2), (V 3 2 2), (H 4 3 2), (H 3 4 3), (H 0 4 2), (V 2 4 2), (H 0 5 2)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"9\">\n" +
                "        <level>1</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (V 1 0 2), (H 1 3 3), (V 5 2 2), (V 3 1 2), (V 4 2 3), (H 2 0 2), (H 4 0 2), (H 4 1 2), (V 2 4 2), (V 0 3 3), (V 5 4 2)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"10\">\n" +
                "        <level>1</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (H 0 0 2), (H 1 3 3), (H 4 4 2), (H 0 1 2), (V 5 1 3), (V 2 0 2), (H 4 0 2), (V 3 4 2), (H 0 5 2), (V 0 2 3), (H 4 5 2)</setup>\n" +
                "    </puzzle>\n" +
                "\n" +
                "    <puzzle id=\"11\">\n" +
                "        <level>2</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (H 1 0 2), (H 3 3 3), (H 2 5 3), (V 0 0 3), (V 2 3 2), (V 5 4 2), (V 3 0 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"12\">\n" +
                "        <level>2</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (V 0 0 2), (H 3 3 3), (H 0 5 3), (V 5 0 3), (H 1 0 2), (V 4 4 2), (V 2 1 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"13\">\n" +
                "        <level>2</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 3 2 2), (H 0 0 2), (H 4 4 2), (H 3 3 2), (V 2 1 2), (V 5 1 3), (H 2 0 2), (V 4 0 2), (V 1 2 2), (V 3 4 2), (V 0 3 3), (H 1 5 2), (H 4 5 2)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"14\">\n" +
                "        <level>2</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 2 2 2), (H 0 0 2), (H 4 4 2), (V 4 2 2), (V 0 2 2), (H 0 5 2), (V 2 0 2), (H 4 1 2), (V 1 2 2), (V 5 2 2), (V 2 4 2), (H 2 3 2)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"15\">\n" +
                "        <level>2</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 2 2 2), (H 1 0 2), (V 0 2 3), (V 3 3 2), (H 2 1 2), (V 4 1 3), (H 3 0 2), (H 0 1 2), (V 2 3 2), (H 4 4 2), (V 5 1 3), (V 1 2 3), (H 1 5 2), (H 3 5 2)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"16\">\n" +
                "        <level>2</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 3 2 2), (H 0 0 2), (H 3 3 3), (V 1 2 2), (V 0 1 2), (V 5 0 3), (H 2 0 2), (V 4 0 2), (H 2 1 2), (H 0 5 2), (V 2 2 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"17\">\n" +
                "        <level>2</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (V 0 0 2), (H 0 4 3), (V 4 4 2), (V 2 2 2), (H 1 0 3), (H 2 1 2), (H 4 1 2), (H 0 3 2), (V 5 4 2), (V 3 3 3), (H 0 5 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"18\">\n" +
                "        <level>2</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (H 0 0 2), (H 1 3 3), (H 0 5 3), (H 1 4 2), (V 3 0 3), (V 2 0 2), (H 0 1 2), (V 0 2 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"19\">\n" +
                "        <level>2</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 2 2 2), (V 2 0 2), (V 4 3 2), (V 1 2 2), (H 1 4 3), (H 3 0 2), (V 4 1 2), (H 2 3 2)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"20\">\n" +
                "        <level>2</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (V 0 0 2), (H 3 5 3), (H 3 4 2), (V 2 2 2), (H 3 0 3), (H 1 1 2), (V 3 1 2), (V 2 4 2), (V 5 2 3)</setup>\n" +
                "    </puzzle>\n" +
                "\n" +
                "    <puzzle id=\"21\">\n" +
                "        <level>3</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (H 0 0 2), (H 1 3 3), (H 3 5 3), (V 3 0 3), (V 2 0 2), (V 0 1 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"22\">\n" +
                "        <level>3</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (V 2 0 2), (H 1 5 3), (V 0 4 2), (V 1 3 2), (H 3 0 3), (V 0 1 2), (H 4 1 2), (H 4 3 2), (H 2 4 2), (V 3 1 3), (V 5 4 2)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"23\">\n" +
                "        <level>3</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 3 2 2), (V 2 1 2), (H 2 5 3), (H 4 4 2), (V 3 3 2), (H 2 0 3), (H 3 1 2), (V 2 3 2), (H 4 3 2), (V 5 0 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"24\">\n" +
                "        <level>3</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 2 2 2), (V 2 0 2), (H 0 5 2), (H 1 3 2), (V 0 2 2), (H 0 4 3), (H 3 0 2), (V 1 1 2), (V 4 2 2), (V 4 4 2)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"25\">\n" +
                "        <level>3</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (H 0 0 2), (H 1 3 3), (V 1 4 2), (H 0 1 2), (V 5 1 3), (V 2 0 2), (H 4 0 2), (V 4 2 2), (V 3 4 2), (V 0 2 3), (H 4 4 2), (H 4 5 2)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"26\">\n" +
                "        <level>3</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (V 1 0 2), (H 3 5 2), (V 2 4 2), (V 5 2 2), (H 3 0 3), (V 0 1 2), (V 3 1 2), (V 0 3 2), (V 5 4 2), (H 1 3 3), (V 4 1 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"27\">\n" +
                "        <level>3</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (V 0 0 2), (H 3 5 3), (V 2 4 2), (V 2 2 2), (V 3 0 3), (H 1 0 2), (H 1 1 2), (H 3 3 2), (V 5 2 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"28\">\n" +
                "        <level>3</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (V 3 0 2), (H 2 4 3), (H 0 5 2), (V 1 3 2), (H 0 0 3), (H 4 1 2), (V 0 3 2), (H 3 3 2), (H 2 5 2), (V 2 1 3), (V 5 3 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"29\">\n" +
                "        <level>3</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (V 2 1 2), (H 0 5 3), (H 1 4 2), (H 1 3 2), (H 0 0 3), (V 5 2 2), (V 0 3 2), (H 3 3 2), (V 3 4 2), (V 4 0 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"30\">\n" +
                "        <level>3</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (V 2 0 2), (V 5 3 3), (H 2 5 2), (H 2 3 2), (V 0 0 3), (V 3 1 2), (H 0 3 2), (H 0 5 2), (H 3 0 3)</setup>\n" +
                "    </puzzle>\n" +
                "\n" +
                "    <puzzle id=\"31\">\n" +
                "        <level>4</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (H 0 0 2), (V 2 3 3), (H 0 4 2), (V 0 2 2), (H 3 0 3), (V 3 1 2), (H 4 1 2), (H 3 3 2), (H 3 5 3), (V 5 2 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"32\">\n" +
                "        <level>4</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (H 0 0 2), (H 0 5 2), (H 3 3 2), (V 0 3 2), (V 2 0 3), (V 3 0 2), (H 4 0 2), (H 1 3 2), (V 3 4 2), (V 5 3 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"33\">\n" +
                "        <level>4</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (V 1 0 2), (H 0 5 3), (H 1 4 2), (H 1 3 2), (V 2 0 3), (H 4 0 2), (V 4 4 2), (H 3 3 2), (V 3 4 2), (V 5 3 3), (V 0 3 2)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"34\">\n" +
                "        <level>4</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (V 0 0 2), (H 0 3 3), (H 4 4 2), (V 3 3 2), (H 3 0 3), (V 3 1 2), (V 4 2 2), (V 2 4 2), (H 3 5 2), (V 5 1 3), (H 0 5 2)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"35\">\n" +
                "        <level>4</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (H 3 0 2), (H 1 3 3), (V 4 4 2), (H 1 4 2), (V 2 0 3), (V 3 1 2), (V 0 3 2), (V 3 4 2), (H 0 5 2), (V 5 0 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"36\">\n" +
                "        <level>4</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 2 2 2), (H 4 0 2), (V 5 1 3), (H 4 4 2), (V 3 3 2), (V 0 0 3), (V 1 1 2), (H 2 1 2), (V 2 4 2), (H 0 5 2), (H 1 0 3), (H 0 3 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"37\">\n" +
                "        <level>4</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 1 2 2), (H 0 0 2), (V 0 2 3), (H 4 4 2), (H 0 1 2), (V 4 1 3), (V 2 0 2), (H 4 0 2), (V 3 4 2), (H 0 5 2), (V 5 1 3), (H 1 3 3), (H 4 5 2)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"38\">\n" +
                "        <level>4</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (V 0 0 2), (H 3 5 3), (V 2 4 2), (V 2 2 2), (H 3 0 3), (H 1 1 2), (V 3 1 2), (H 3 3 2), (H 3 4 2), (V 5 2 3)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"39\">\n" +
                "        <level>4</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 0 2 2), (V 2 0 2), (V 5 2 3), (V 0 4 2), (H 0 3 2), (H 3 0 3), (V 3 1 2), (V 2 2 2), (H 3 3 2), (V 1 4 2), (H 2 4 2), (H 2 5 2)</setup>\n" +
                "    </puzzle>\n" +
                "    <puzzle id=\"40\">\n" +
                "        <level>4</level>\n" +
                "        <length>0</length>\n" +
                "        <setup>(H 3 2 2), (H 1 0 2), (H 0 3 3), (V 2 4 2), (V 2 1 2), (V 0 0 3), (V 4 0 2), (V 1 1 2), (V 3 3 2), (H 4 4 2), (V 5 1 3), (H 0 5 2), (H 3 5 2)</setup>\n" +
                "    </puzzle>\n" +
                "\n" +
                "</challenge>";

        return ret;
    }

}

