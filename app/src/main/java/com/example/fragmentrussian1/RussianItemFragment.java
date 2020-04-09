package com.example.fragmentrussian1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentrussian1.dummy.DummyContent;
import com.example.fragmentrussian1.dummy.DummyContent.Rule;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class RussianItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 3;
    Rule rule;
    View veiwer;

    public void setVeiwer(View veiwer) {
        this.veiwer = veiwer;
    }

    public View getVeiwer() {
        return veiwer;
    }

    private OnListFragmentInteractionListener mListener;

    public OnListFragmentInteractionListener getmListener() {
        return mListener;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RussianItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RussianItemFragment newInstance(int columnCount) {
        RussianItemFragment fragment = new RussianItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    public static String texter(){
        String[] ster={"выполнено","не выполнено","выполняется"};
        Random rand=new Random();
        return ster[rand.nextInt(3)];
    }
    public static String signer(){
        String[] ster={"+","-",""};
        Random rand=new Random();
        return ster[rand.nextInt(3)];
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_russian_list, container, false);
        RussianItemFragment fragment = new RussianItemFragment();
        fragment.setVeiwer(view);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            parse(getActivity());
            recyclerView.setAdapter(new RussianRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }



    public static void parse(Context context){
        XmlPullParser parser = Xml.newPullParser();
        RussianItemFragment frag=null;
        String sr="";
        // TODO parser
       
Rule rule=new Rule("","","");
        try {
            String s="";
            InputStream stream =  context.getAssets().open("menu_list.xml");
            //InputStream stream = getApplicationContext().getAssets().open("lab_description_assets/lab3_description_assets.xml");
            try {
                parser.setInput(stream,"utf-8");
            } catch (XmlPullParserException ex) {
                ex.printStackTrace();
            }
            int index = 0;
            int numer=0;
            while (parser.getEventType()!= XmlPullParser.END_DOCUMENT){
                
                if(parser.getEventType() == XmlPullParser.START_TAG)
                    s = parser.getName();

                if(parser.getEventType() == XmlPullParser.END_TAG){
                    Log.v("Parsers", parser.getName()+"!");
                    s = "";
                    parser.next();
                    //continue;
                }
                if (parser.getEventType() == XmlPullParser.TEXT) {
                    // Toast.makeText(getApplicationContext(), "getEventType", Toast.LENGTH_SHORT).show();
                    switch (s) {
                        case "title":
                            Toast.makeText(context, parser.getText(), Toast.LENGTH_SHORT).show();
                            //textView.append(parser.getText());
                            //textView.append(Html.fromHtml(parser.getText()));
                            break;
                        case "item":
                            index++;
                            numer = 0;
                            sr = texter();
                            Log.v("Parsers", parser.getText());
                            rule= new Rule(index+"", parser.getText(), sr);
                            DummyContent.addItem(rule);
                            //textView.append(parser.getText());
                            break;
                        case "preitem":
                            numer++;
                            Log.v("Parsers", parser.getText());
                            if(sr=="выполнено"){
                                DummyContent.addItem(new Rule("      "+index+"."+numer, parser.getText(), "молодец!","+"));
                            }else if(sr=="не выполнено"){
                            DummyContent.addItem(new Rule("      "+index+"."+numer, parser.getText(), "задание не выполнено"));}
                            else{
                                String si=signer();
                                String he="";
                                switch(si){
                                    case "+":
                                        he="молодец!";
                                        break;
                                    case "-":
                                        he="переделать!";
                                        break;
                                        default:
                                            he="задание не выполнено";
                                            break;
                                }
                                DummyContent.addItem(new Rule("      "+index+"."+numer, parser.getText(), he,si));
                            }
                            break;
                        default:
                            break;
                    }

                }
                parser.next();
            }
    } catch (IOException e) {
            Toast.makeText(context, "IOException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();}
          catch (NullPointerException e) {
        Toast.makeText(context, "XmlPullParserException", Toast.LENGTH_SHORT).show();
         e.printStackTrace();
        }
        catch (XmlPullParserException e) {
            Toast.makeText(context, "XmlPullParserException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Rule item);
    }
}
